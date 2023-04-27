package sejong.capstone.team13.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sejong.capstone.team13.model.Power;
import sejong.capstone.team13.model.request.FloorEnergyRequest;

import java.util.List;

@SpringBootTest
public class GraphControllerTest {

    @Autowired
    GraphController gc;

    @Test
    public void testRowError(){
        FloorEnergyRequest fer = new FloorEnergyRequest();
        fer.setFloor(1);
        fer.setInterval(0);
        List<Power> list = gc.getGraphData(fer);

        for(int i=0; i<list.size(); i++)
            System.out.println(list.get(i));
    }

}
