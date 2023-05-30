package sejong.capstone.team13.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sejong.capstone.team13.model.ForecastData;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ForecastRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public ForecastRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<ForecastData> getPredictedData(){
        String sql = "SELECT * FROM forecast_data ORDER BY updated_time DESC LIMIT 1";
        return jdbcTemplate.query(sql, (rs, rowNum)->{
            ForecastData data = new ForecastData();
            data.setLoad(rs.getInt("forecast_Load"));
            data.setGen(rs.getInt("forecast_Gen"));
            data.setTime(rs.getString("updated_time"));
            return data;
        });
    }

}
