package sejong.capstone.team13.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import sejong.capstone.team13.model.DataName;
import sejong.capstone.team13.model.Power;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UpdatedDataRepository {
    DataSource dataSource;
    Connection con;
    Statement stmt;
    ResultSet rs;

    public UpdatedDataRepository(@Qualifier("primaryDataSource") DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Power getNgnPower(){
        Power power = new Power();
        con = DataSourceUtils.getConnection(dataSource);

        try {
            stmt = con.createStatement();

            // 전류
            rs = stmt.executeQuery("SELECT * FROM updated_data WHERE data_name = "
                    + DataName.ngn[0]);
            if(rs.next()){
                power.setI(rs.getDouble("value"));
            }

            // 전압
            rs = stmt.executeQuery("SELECT * FROM updated_data WHERE data_name = "
                    + DataName.ngn[1]);
            if(rs.next()){
                power.setV(rs.getDouble("value"));
                power.setTime(rs.getString("updated_time"));
                power.setP(power.getI() * power.getV());
            }

            return power;
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return power;
        }finally {
            // ResultSet을 닫습니다.
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Statement를 닫습니다.
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public List<Power> getLoadStatus(){
        List<Power> powerList = new ArrayList<>();
        con = DataSourceUtils.getConnection(dataSource);

        try {
            stmt = con.createStatement();

            for(int i=0; i<DataName.load.length; i+=2){
                Power p = new Power();
                rs = stmt.executeQuery("SELECT * FROM updated_data WHERE data_name = "
                        + DataName.load[i]);
                if(rs.next())
                    p.setI(rs.getDouble("value"));

                rs = stmt.executeQuery("SELECT * FROM updated_data WHERE data_name = "
                        + DataName.load[i+1]);

                if(rs.next())
                    p.setV(rs.getDouble("value"));

                powerList.add(p);
            }
//
//            // 1층부터 3층까지의 power 정보를 얻기 위해 3번 줄부터 8번 줄까지의 정보를 가져온다.
//            rs = stmt.executeQuery("SELECT * FROM updated_data WHERE updated_data_id BETWEEN 3 AND 8");
//
//            for(int i=0; i<3; i++){
//                Power p = new Power();
//
//                if(rs.next()) {
//                    rs.next();
//                    p.setI(rs.getDouble("value"));
//                }
//
//
//                if(rs.next()) {
//                    p.setV(rs.getDouble("value"));
//                    p.setTime(rs.getString("updated_time"));
//                }
//
//                powerList.add(p);
//            }

            return powerList;

        } catch (SQLException e) {
            return powerList;
        } finally {
            // ResultSet을 닫습니다.
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Statement를 닫습니다.
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public List<Power> getFloorPower(int floor){
        List<Power> powerList = new ArrayList<>();

        try {
            con = DataSourceUtils.getConnection(dataSource);
            stmt = con.createStatement();

            // 전류
            rs = stmt.executeQuery("SELECT * FROM updated_data WHERE data_name = " + DataName.load[floor-1] + " ");
            rs.next();
            double a = rs.getDouble("value");

            // 전압
            rs = stmt.executeQuery("SELECT * FROM updated_data WHERE data_name = " + DataName.load[floor] + " ");
            rs.next();
            String time = rs.getString("updated_time");
            double v = rs.getDouble("value");

            powerList.add(new Power(time, a, v));

            return powerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // ResultSet을 닫습니다.
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Statement를 닫습니다.
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
