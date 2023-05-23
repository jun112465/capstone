package sejong.capstone.team13.configuration;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class ScheduleConfig {
    @Scheduled(cron = "0 0 0 0 * ?")
    public void updateRecentTable() {
        // row 확인

        // 날짜 삭제

        // 날짜 추가
    }

    @Scheduled(cron = "0 1 0 0 * ?")
    public void updateMonthTable(){
        // row 확인

        // 날짜 삭제

        // 날짜 추가
    }

}
