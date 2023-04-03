package sejong.capstone.team13.domain;

import lombok.Data;

@Data
public class SolarPower {
    Double I;
    Double V;
    Double P;

    public SolarPower(Double I, Double V){
        this.I = I;
        this.V = V;
        this.P = I*V;
    }
}
