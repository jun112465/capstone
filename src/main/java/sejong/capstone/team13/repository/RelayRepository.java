package sejong.capstone.team13.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import sejong.capstone.team13.model.response.RelayStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RelayRepository {


    DataSource dataSource;
    Connection con;
    Statement stmt;
    ResultSet rs;

    @Autowired
    public RelayRepository(@Qualifier("primaryDataSource") DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<RelayStatus> getRelayStatus(){
        List<RelayStatus> rsList = new ArrayList<>();
        con = DataSourceUtils.getConnection(dataSource);

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM relay WHERE relay_id BETWEEN 2 AND 4");

            // 3층까지 있는 빌딩이므로 2행 ~ 4행 데이터를 받으면 된다.
            while(rs.next()){
                RelayStatus relayStatus = new RelayStatus();
                relayStatus.setFloor(rs.getInt("relay_id")-1);
                relayStatus.setStatus(rs.getInt("relay_is_using") == 1);
                rsList.add(relayStatus);
            }

            return rsList;
        } catch (SQLException e) {
            return rsList;
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

    public List<RelayStatus> getDRStatus(){
        List<RelayStatus> rsList = new ArrayList<>();
        con = DataSourceUtils.getConnection(dataSource);

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM relay WHERE relay_id BETWEEN 5 AND 7");

            // 3층까지 있는 빌딩이므로 2행 ~ 4행 데이터를 받으면 된다.
            while(rs.next()){
                RelayStatus relayStatus = new RelayStatus();
                relayStatus.setFloor(rs.getInt("relay_id")-1);
                relayStatus.setStatus(rs.getInt("relay_is_using") == 1);
                rsList.add(relayStatus);
            }

            return rsList;
        } catch (SQLException e) {
            return rsList;
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

    public Integer getSwitchStatus(){
        Integer relayStatus;
        con = DataSourceUtils.getConnection(dataSource);
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM relay WHERE relay_id=1");

            rs.next();
            relayStatus = rs.getInt("relay_is_using");

            return relayStatus;
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
