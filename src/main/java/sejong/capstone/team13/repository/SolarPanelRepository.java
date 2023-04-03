package sejong.capstone.team13.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class SolarPanelRepository {

    DataSource dataSource;
    Connection con;
    Statement stmt;
    ResultSet rs;

    @Autowired
    public SolarPanelRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Connection getCon() {
        return DataSourceUtils.getConnection(dataSource);
    }

    public double getElectricCurrent() {
        Double current;

        try {
            con = getCon();
            stmt = con.createStatement();
            rs = stmt.executeQuery( "select * from data_attribute " +
                    "where data_name = 'MX.A.phsA.instCVal.mag.f ' " +
                    "order by data_attribute_id " +
                    "desc limit 1");
            rs.next();
            current = Double.parseDouble(rs.getString("value"));

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return current;
    }

    public double getVoltage(){
        Double voltage;

        try {
            con = getCon();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from data_attribute " +
                    "where data_name='MX.A.phsB.instCVal.mag.f ' "+
                    "order by data_attribute_id " +
                    "desc limit 1");
            rs.next();
            voltage = Double.parseDouble(rs.getString("value"));

            rs.close();
            stmt.close();
            con.close();

            return voltage;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}