package sejong.capstone.team13.model.request;

import lombok.Data;

@Data
public class FloorEnergyRequest {
    int floor;
    //0 -> 10초
    //1 -> 1일
    //2 -> 30일
    int interval;
}
