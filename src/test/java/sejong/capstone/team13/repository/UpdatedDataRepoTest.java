package sejong.capstone.team13.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdatedDataRepoTest {

    @Autowired
    UpdatedDataRepository updatedDataRepository;

    @Test
    public void testEndOfResultSetError(){
        for(int i=0; i<100; i++)
            updatedDataRepository.getFloorPower(1);
    }

}
