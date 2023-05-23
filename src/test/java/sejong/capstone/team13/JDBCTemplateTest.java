package sejong.capstone.team13;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sejong.capstone.team13.model.Power;
import sejong.capstone.team13.repository.DataAttributeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
public class JDBCTemplateTest {

    @Autowired
    DataAttributeRepository dataAttributeRepository;

    @Test
    public void getAmperes(){
        long startTime = System.nanoTime();




        CompletableFuture<List<Power>> ampereList = dataAttributeRepository.getFloorAmpereList(1);
        CompletableFuture<List<Power>> voltList = dataAttributeRepository.getFloorVoltList(1);

        CompletableFuture<Object> powerList = ampereList.thenCombine(voltList, (listByAmpere, listByVolt)-> {
            List<Power> newList = new ArrayList<>(listByAmpere);
            for(int i=0; i<listByVolt.size(); i++){
                newList.get(i).setV(listByVolt.get(i).getV());
            }
            return newList;
        });

        System.out.println(powerList.join());
        System.out.println(powerList.join().getClass());

        long endTime = System.nanoTime();
        // 두 나노 시간 값의 차이를 얻습니다.
        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }

}
