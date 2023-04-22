package sejong.capstone.team13.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sejong.capstone.team13.model.DataName;
import sejong.capstone.team13.model.Power;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public class DataAttributeRepository {

    private JdbcTemplate template;


    @Autowired
    public DataAttributeRepository(JdbcTemplate template){
        this.template = template;
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
                "AND date >= DATE_SUB(NOW(), INTERVAL 40 DAY) " +
                "AND date <= NOW() " +
                "AND data_name=\'" + DataName.arr[floor-1] + "\'", AmpereRowMapper()));
    }

    public CompletableFuture<List<Power>> getFloorVoltList(int floor){
        return CompletableFuture.supplyAsync(()-> template.query(
                "SELECT * FROM " +
                "( SELECT *, DATE_FORMAT(updated_time, '%y-%m-%d') as date, DATE_FORMAT(updated_time, '%T') as time " +
                "FROM data_attribute) B " +
                "WHERE time>=date_format(NOW(), '%T') " +
                "AND time<=date_format(date_add(NOW(), INTERVAL 1 SECOND), '%T') " +
                "AND date >= DATE_SUB(NOW(), INTERVAL 40 DAY) " +
                "AND date <= NOW() " +
                "AND data_name=\'" + DataName.arr[floor-1] + "\'", VoltRowMapper()));
    }
}
