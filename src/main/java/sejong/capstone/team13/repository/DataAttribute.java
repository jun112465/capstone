package sejong.capstone.team13.repository;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class DataAttribute {
    DataSource dataSource;
    Connection con;
    Statement stmt;
    ResultSet rs;

    public DataAttribute(DataSource dataSource){
        this.dataSource = dataSource;
    }


}
