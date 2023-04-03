package sejong.capstone.team13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sejong.capstone.team13.domain.SolarPower;
import sejong.capstone.team13.repository.SolarPanelRepository;

@RestController
public class HomeController {

    SolarPanelRepository solarPanelRepository;


    @Autowired
    public HomeController(SolarPanelRepository solarPanelRepository){
        this.solarPanelRepository = solarPanelRepository;
    }


    @GetMapping("/")
    public String home(){
        System.out.println(solarPanelRepository.getElectricCurrent());
        System.out.println(solarPanelRepository.getVoltage());

        SolarPower sp = new SolarPower(solarPanelRepository.getElectricCurrent(), solarPanelRepository.getVoltage());
        return "Hello";

    }
}
