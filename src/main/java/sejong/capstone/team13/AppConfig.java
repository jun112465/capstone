package sejong.capstone.team13;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class AppConfig {
    DataSource dataSource;

    public AppConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
}
