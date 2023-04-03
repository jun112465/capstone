package sejong.capstone.team13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sejong.capstone.team13.domain.Power;
import sejong.capstone.team13.domain.SolarPower;
import sejong.capstone.team13.repository.FloorRepository;
import sejong.capstone.team13.repository.SolarPanelRepository;

@RestController
public class PowerController {

    FloorRepository floorRepository;
    SolarPanelRepository solarPanelRepository;

    @Autowired
    PowerController(SolarPanelRepository solarPanelRepository, FloorRepository floorRepository){
        this.solarPanelRepository = solarPanelRepository;
        this.floorRepository = floorRepository;
    }

    @GetMapping("/power")
    public Power getPowers(){
//        FloorPower fp = new FloorPower();
        SolarPower sp = new SolarPower(solarPanelRepository.getElectricCurrent(), solarPanelRepository.getVoltage());
        Power p = new Power();
        p.setSp(sp);

        return p;
    }
}
