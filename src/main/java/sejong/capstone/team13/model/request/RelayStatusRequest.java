package sejong.capstone.team13.model.request;

import lombok.Data;

@Data
public class RelayStatusRequest {
    int floor;
    Boolean before;
    Boolean after;
}
