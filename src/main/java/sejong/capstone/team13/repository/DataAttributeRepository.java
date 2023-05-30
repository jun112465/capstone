package sejong.capstone.team13.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sejong.capstone.team13.model.DataName;
import sejong.capstone.team13.model.Power;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Repository
public class DataAttributeRepository {

    private JdbcTemplate template;
//    private DataSource dataSource;
    private int limit;


    @Autowired
    public DataAttributeRepository(@Qualifier("primaryDataSource") DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<Power> AmpereRowMapper() {
        return (rs, rowNum) -> {
            Power power = new Power();
            power.setI(rs.getDouble("value"));
            power.setTime(rs.getString("updated_time"));
            return power;
        };
    }

    private RowMapper<Power> VoltRowMapper() {
        return (rs, rowNum) -> {
            Power power = new Power();
            power.setV(rs.getDouble("value"));
            power.setTime(rs.getString("updated_time"));
            return power;
        };
    }

    public CompletableFuture<List<Power>> getFloorAmpereList(int floor){
        return CompletableFuture.supplyAsync(()-> template.query(
                "SELECT * FROM " +
                "( SELECT *, DATE_FORMAT(updated_time, '%y-%m-%d') as date, DATE_FORMAT(updated_time, '%T') as time " +
                "FROM data_attribute) B " +
                "WHERE time>=date_format(NOW(), '%T') " +
                "AND time<=date_format(date_add(NOW(), INTERVAL 1 SECOND), '%T') " +
                "AND date >= DATE_SUB(NOW(), INTERVAL 100 DAY) " +
                "AND date <= NOW() " +
                "AND data_name=\'" + DataName.load[floor-1] + "\'", AmpereRowMapper()));
    }

    public CompletableFuture<List<Power>> getFloorVoltList(int floor){
        return CompletableFuture.supplyAsync(()-> template.query(
                "SELECT * FROM " +
                "( SELECT *, DATE_FORMAT(updated_time, '%y-%m-%d') as date, DATE_FORMAT(updated_time, '%T') as time " +
                "FROM data_attribute) B " +
                "WHERE time>=date_format(NOW(), '%T') " +
                "AND time<=date_format(date_add(NOW(), INTERVAL 1 SECOND), '%T') " +
                "AND date >= DATE_SUB(NOW(), INTERVAL 100 DAY) " +
                "AND date <= NOW() " +
                "AND data_name=\'" + DataName.load[floor-1] + "\'", VoltRowMapper()));
    }

    public CompletableFuture<List<Power>> getNgnAmpereList(){
        return CompletableFuture.supplyAsync(()-> template.query(
                "SELECT * FROM " +
                        "( SELECT *, DATE_FORMAT(updated_time, '%y-%m-%d') as date, DATE_FORMAT(updated_time, '%T') as time " +
                        "FROM data_attribute) B " +
                        "WHERE time>=date_format(NOW(), '%T') " +
                        "AND time<=date_format(date_add(NOW(), INTERVAL 1 SECOND), '%T') " +
                        "AND date >= DATE_SUB(NOW(), INTERVAL 100 DAY) " +
                        "AND date <= NOW() " +
                        "AND data_name=\'" + DataName.ngn[0] + "\'", AmpereRowMapper()));
    }

    public CompletableFuture<List<Power>> getNgnVoltList(){
        return CompletableFuture.supplyAsync(()-> template.query(
                "SELECT * FROM " +
                        "( SELECT *, DATE_FORMAT(updated_time, '%y-%m-%d') as date, DATE_FORMAT(updated_time, '%T') as time " +
                        "FROM data_attribute) B " +
                        "WHERE time>=date_format(NOW(), '%T') " +
                        "AND time<=date_format(date_add(NOW(), INTERVAL 1 SECOND), '%T') " +
                        "AND date >= DATE_SUB(NOW(), INTERVAL 100 DAY) " +
                        "AND date <= NOW() " +
                        "AND data_name=\'" + DataName.ngn[1] + "\'", VoltRowMapper()));
    }

    public void updateRecentTable(){
        template.execute("- CREATE TABLE recent AS\n" +
                "- SELECT data_attribute_id, data_name, value, updated_time\n" +
                "- FROM data_attribute\n" +
                "- WHERE date(updated_time)='2023-03-22' OR date(updated_time)='2023-03-21';");
    }

    public Integer checkRecentRowCnt(){
        template.execute("SELECT COUNT(data_attribute_id) FROM data_attribute");
        List<Integer> rowCnt = template.query("SELECT COUNT(data_attribute_id) AS cnt FROM data_attribute", (rs, rowNum)->{
            Integer cnt;
            cnt = rs.getInt("cnt");
            return cnt;
        });

        return rowCnt.get(0);
    }

    public List<String> getRecentDates(){
        List<String> dates = template.query(
                "SELECT DISTINCT(DATE(updated_time)) AS date FROM data_attribute ORDER BY dates ASC",
                (rs, rowNum)-> rs.getString("date"));
        return dates;
    }
    public void deleteRecentDate(String date){
        String sql = "DELETE FROM recent WHERE DATE(updated_time) = \'" + date + "\'";
        template.execute(sql);

    }

    // 매일 실행되는 코드
    public void addRecentDay(int year, int month, int day){
        String sql = "INSERT INTO recent_day(data_attribute_id, data_name, value, updated_time) " +
                "SELECT data_attribute_id, data_name, value, updated_time" +
                "FROM data_attribute" +
                "WHERE YEAR(updated_time) = " +
                "AND MONTH(updated_time) = " +
                "AND DAY(updated_time) = " +
                "AND SECOND(updated_time) = ";
        template.execute(sql);
    }

    // 매달 실행되는 코드
    public void addRecentMonth(int year, int month){
        String sql = "INSERT INTO recent_year(data_attribute_id, data_name, value, updated_time) " +
                "SELECT data_attribute_id, data_name, value, updated_time" +
                "FROM data_attribute" +
                "WHERE YEAR(updated_time) = " +
                "AND MONTH(updated_time) = " +
                "AND SECOND(updated_time) = ";
        template.execute(sql);
    }

    public void deleteMonth(int year, int month){
        String sql = "DELETE FROM recent_year " +
                "WHERE YEAR(updated_time) = " +
                "AND MONTH(updated_time) = ";
        template.execute(sql);
    }


    public CompletableFuture<List<Power>> getFloorVoltList2(int floor){
        return CompletableFuture.supplyAsync(()-> template.query(
                "SELECT * FROM " +
                        "( SELECT *, DATE_FORMAT(updated_time, '%y-%m-%d') as date, DATE_FORMAT(updated_time, '%T') as time " +
                        "FROM data_attribute) B " +
                        "WHERE time>=date_format(NOW(), '%T') " +
                        "AND time<=date_format(date_add(NOW(), INTERVAL 1 SECOND), '%T') " +
                        "AND date >= DATE_SUB(NOW(), INTERVAL 100 DAY) " +
                        "AND date <= NOW() " +
                        "AND data_name=\'" + DataName.load[floor-1] + "\'", VoltRowMapper()));
    }

    public List<Power> getFloorRecentAmpere(int floor){
        // 전류

        String sql = "select data_attribute_id,data_name, value, date_format(updated_time, \'%Y-%m-%d\') as date " +
                "from recent where data_name=" + DataName.load[floor-1] + " " +
                "and date_format(updated_time, \'%H:%i:00\') = date_format(now(), \"%H:%i:00\") " +
                "OR date_add(date_format(updated_time, \'%H:%i:00\'), interval 1 second) = date_format(now(), \"%H:%i:01\")";
        // 전압
        return template.query(sql, (rs, rowNum)->{
            Power p = new Power();
            p.setI(rs.getDouble("value"));
            p.setTime(rs.getString("date"));
            return p;
        });
    }

    public List<Power> getFloorRecentVolt(int floor){
        // 전압

        String sql = "select data_attribute_id,data_name, value, date_format(updated_time, \'%Y-%m-%d\') as date " +
                "from recent where data_name=" + DataName.load[floor] + " " +
                "and date_format(updated_time, \'%H:%i:00\') = date_format(now(), \"%H:%i:00\") " +
                "OR date_add(date_format(updated_time, \'%H:%i:00\'), interval 1 second) = date_format(now(), \'%H:%i:01\')";

        return template.query(sql, (rs, rowNum)->{
            Power p = new Power();
            p.setV(rs.getDouble("value"));
            p.setTime(rs.getString("date"));
            return p;
        });
    }

    public List<Power> getNgnRecentAmpere(){
        // 전압

        String sql = "select data_attribute_id,data_name, value, date_format(updated_time, \'%Y-%m-%d\') as date " +
                "from recent where data_name=" + DataName.ngn[0] + " " +
                "and date_format(updated_time, \'%H:%i:00\') = date_format(now(), \"%H:%i:00\") " +
                "OR date_add(date_format(updated_time, \'%H:%i:00\'), interval 1 second) = date_format(now(), \'%H:%i:01\')";

        return template.query(sql, (rs, rowNum)->{
            Power p = new Power();
            p.setI(rs.getDouble("value"));
            p.setTime(rs.getString("date"));
            return p;
        });
    }
    public List<Power> getNgnRecentVolt(){
        // 전압

        String sql = "select data_attribute_id,data_name, value, date_format(updated_time, \'%Y-%m-%d\') as date " +
                "from recent where data_name=" + DataName.ngn[1] + " " +
                "and date_format(updated_time, \'%H:%i:00\') = date_format(now(), \"%H:%i:00\") " +
                "OR date_add(date_format(updated_time, \'%H:%i:00\'), interval 1 second) = date_format(now(), \'%H:%i:01\')";

        return template.query(sql, (rs, rowNum)->{
            Power p = new Power();
            p.setV(rs.getDouble("value"));
            p.setTime(rs.getString("date"));
            return p;
        });
    }

    public List<Power> getFloorMonthAmpere(int floor){
        // 전류

        String sql = "select data_attribute_id,data_name, value, date_format(updated_time, \'%Y-%m-%d\') as date " +
                "from recent_year where data_name=" + DataName.load[floor-1] + " " +
                "and date_format(updated_time, \'%H:%i:00\') = date_format(now(), \"%H:%i:00\") " +
                "OR date_add(date_format(updated_time, \'%H:%i:00\'), interval 1 second) = date_format(now(), \"%H:%i:01\")";
        // 전압
        return template.query(sql, (rs, rowNum)->{
            Power p = new Power();
            p.setI(rs.getDouble("value"));
            p.setTime(rs.getString("date"));
            return p;
        });
    }

    public List<Power> getFloorMonthVolt(int floor){
        // 전압

        String sql = "select data_attribute_id,data_name, value, date_format(updated_time, \'%Y-%m-%d\') as date " +
                "from recent_year where data_name=" + DataName.load[floor] + " " +
                "and date_format(updated_time, \'%H:%i:00\') = date_format(now(), \"%H:%i:00\") " +
                "OR date_add(date_format(updated_time, \'%H:%i:00\'), interval 1 second) = date_format(now(), \'%H:%i:01\')";

        return template.query(sql, (rs, rowNum)->{
            Power p = new Power();
            p.setV(rs.getDouble("value"));
            p.setTime(rs.getString("date"));
            return p;
        });
    }

    public List<Power> getNgnMonthAmpere(){
        // 전압

        String sql = "select data_attribute_id,data_name, value, date_format(updated_time, \'%Y-%m-%d\') as date " +
                "from recent_year where data_name=" + DataName.ngn[0] + " " +
                "and date_format(updated_time, \'%H:%i:00\') = date_format(now(), \"%H:%i:00\") " +
                "OR date_add(date_format(updated_time, \'%H:%i:00\'), interval 1 second) = date_format(now(), \'%H:%i:01\')";

        return template.query(sql, (rs, rowNum)->{
            Power p = new Power();
            p.setI(rs.getDouble("value"));
            p.setTime(rs.getString("date"));
            return p;
        });
    }
    public List<Power> getNgnMonthVolt(){
        // 전압

        String sql = "select data_attribute_id,data_name, value, date_format(updated_time, \'%Y-%m-%d\') as date " +
                "from recent_year where data_name=" + DataName.ngn[1] + " " +
                "and date_format(updated_time, \'%H:%i:00\') = date_format(now(), \"%H:%i:00\") " +
                "OR date_add(date_format(updated_time, \'%H:%i:00\'), interval 1 second) = date_format(now(), \'%H:%i:01\')";

        return template.query(sql, (rs, rowNum)->{
            Power p = new Power();
            p.setV(rs.getDouble("value"));
            p.setTime(rs.getString("date"));
            return p;
        });
    }

    public void addData(){
        LocalDateTime date = LocalDateTime.now();
        for(int i=0; i<100; i++) {
            Random rand = new Random();
            Integer load = rand.nextInt(1000) + 90000;
            Integer gen = rand.nextInt(300) + 6000;
            String sql = String.format("INSERT INTO forecast_data(forecast_Load, forecast_Gen, updated_time) " +
                    "VALUES(%s,%s,\'%s\')", load, gen , date.minusHours(i));
            template.execute(sql);
        }
    }
}
