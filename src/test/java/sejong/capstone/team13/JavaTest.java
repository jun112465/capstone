package sejong.capstone.team13;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class JavaTest {

    @Test
    public void dateTest(){
        LocalDate localDate = LocalDate.now();
        System.out.println("test");
        System.out.println(localDate);
        System.out.println("test over");
    }
}
