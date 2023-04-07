package sejong.capstone.team13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sejong.capstone.team13.model.Power;
import sejong.capstone.team13.repository.UpdatedDataRepository;

import java.util.List;

@Controller
public class PowerController {

    UpdatedDataRepository udr;

    @Autowired
    public PowerController(UpdatedDataRepository udr){
        this.udr = udr;
    }

    @GetMapping("/load-status")
    @ResponseBody
    public List<Power> getLoadStatus(){
        return udr.getPowers();
    }

    @GetMapping("/ngn-status")
    @ResponseBody
    public Power getNgnStatus(){
        return udr.getNgnPower();
    }
}
