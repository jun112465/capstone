package sejong.capstone.team13.repository;

import ch.qos.logback.classic.jul.JULHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class PasswordRepository {

    DataSource dataSource;
    Connection con;
    Statement stmt;
    ResultSet rs;

    JdbcTemplate jdbcTemplate;

    PasswordRepository(@Qualifier("secondaryDataSource")DataSource dataSource){
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getPwd(){
        try {
            con = DataSourceUtils.getConnection(dataSource);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM password");
            rs.next();
            return rs.getString("pwd");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePwd(String newPwd){
        String sql = "UPDATE password SET pwd = ?";
        jdbcTemplate.update(sql, newPwd);
    }
}
