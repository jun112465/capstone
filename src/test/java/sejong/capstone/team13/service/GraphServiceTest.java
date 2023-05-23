package sejong.capstone.team13.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GraphServiceTest {

    @Autowired
    GraphService graphService;

    @Test
    public void testListDataBring(){
        graphService.getPowerListData();
    }
}
