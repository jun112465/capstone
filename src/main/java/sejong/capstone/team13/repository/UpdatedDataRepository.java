package sejong.capstone.team13.repository;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import sejong.capstone.team13.model.DataName;
import sejong.capstone.team13.model.Power;

import javax.sql.DataSource;
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

    public UpdatedDataRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Power getNgnPower(){
        Power power = null;

        try {
            con = DataSourceUtils.getConnection(dataSource);
            stmt = con.createStatement();

            // 1층부터 3층까지의 power 정보를 얻기 위해 3번 줄부터 8번 줄까지의 정보를 가져온다.
            rs = stmt.executeQuery("SELECT * FROM updated_data WHERE updated_data_id BETWEEN 1 AND 2");

            rs.next();
            double a = rs.getDouble("value");
            rs.next();
            double v = rs.getDouble("value");
            String time = rs.getString("updated_time");

            power = new Power(time, a,v);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

        return power;
    }


    public List<Power> getPowers(){
        List<Power> powerList = new ArrayList<>();

        try {
            con = DataSourceUtils.getConnection(dataSource);
            stmt = con.createStatement();

            // 1층부터 3층까지의 power 정보를 얻기 위해 3번 줄부터 8번 줄까지의 정보를 가져온다.
            rs = stmt.executeQuery("SELECT * FROM updated_data WHERE updated_data_id BETWEEN 3 AND 8");

            for(int i=0; i<3; i++){
                rs.next();
                double a = rs.getDouble("value");

                rs.next();
                double v = rs.getDouble("value");
                String time = rs.getString("updated_time");

                powerList.add(new Power(time, a,v));
            }

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

        return powerList;
    }

    public List<Power> getFloorPower(int floor){
        List<Power> powerList = new ArrayList<>();

        try {
            con = DataSourceUtils.getConnection(dataSource);
            stmt = con.createStatement();

            // 전류
            rs = stmt.executeQuery("SELECT * FROM updated_data WHERE data_name = \'" + DataName.arr[floor-1] + "\'");
            rs.next();
            double a = rs.getDouble("value");

            // 전압
            rs = stmt.executeQuery("SELECT * FROM updated_data WHERE data_name = \'" + DataName.arr[floor] + "\'");
            rs.next();
            String time = rs.getString("updated_time");
            double v = rs.getDouble("value");

            powerList.add(new Power(time, a, v));
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

        return powerList;
    }
}
