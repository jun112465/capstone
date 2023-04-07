package sejong.capstone.team13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sejong.capstone.team13.model.Power;

@RestController
public class SolarController {

    @GetMapping
    @ResponseBody
    public Power getSolarPower(){
        Power sp = new Power();
        return sp;
    }
}
