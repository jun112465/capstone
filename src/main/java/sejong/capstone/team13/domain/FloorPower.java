package sejong.capstone.team13.domain;

import lombok.Data;

@Data
public class FloorPower {
    Integer f;
    Double I;
    Double V;
    Double P;

    public FloorPower(Integer f, Double I, Double V){
        this.f = f;
        this.I = I;
        this.V = V;
        this.P = V*I;
    }
}
