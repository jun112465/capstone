package sejong.capstone.team13.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    boolean success;
    String message;
}
