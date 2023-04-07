package sejong.capstone.team13.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Power {
    String time;
    Double I;
    Double V;
    Double P;

    public Power(String time, Double I, Double V){
        this.time = time;
        this.I = I;
        this.V = V;
        this.P = V*I;
    }
}
