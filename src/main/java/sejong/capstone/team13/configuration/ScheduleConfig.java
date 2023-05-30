package sejong.capstone.team13.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sejong.capstone.team13.repository.DataAttributeRepository;

import java.time.LocalDate;

@Component
@EnableAsync
public class ScheduleConfig {

    DataAttributeRepository dataAttributeRepository;

    @Autowired
    public ScheduleConfig(DataAttributeRepository dataAttributeRepository){
        this.dataAttributeRepository = dataAttributeRepository;
    }
    @Scheduled(cron = "0 0 0 0 * ?")
    public void updateRecentTable() {

        LocalDate localDate = LocalDate.now();
        // row 확인

        // 날짜 삭제
        dataAttributeRepository.deleteRecentDate(localDate.toString());
        // 날짜 추가
        dataAttributeRepository.addRecentDay(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
    }

    @Scheduled(cron = "0 1 0 0 * ?")
    public void updateMonthTable(){
        LocalDate localDate = LocalDate.now();
        // 날짜 삭제
        dataAttributeRepository.deleteMonth(localDate.getYear(), localDate.getMonthValue());
        // 날짜 추가
        dataAttributeRepository.addRecentMonth(localDate.getYear(), localDate.getMonthValue());
    }

}
