package sejong.capstone.team13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sejong.capstone.team13.model.Power;
import sejong.capstone.team13.model.request.FloorEnergyRequest;
import sejong.capstone.team13.repository.UpdatedDataRepository;

import java.util.ArrayList;
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

    @PostMapping("/floor")
    @ResponseBody
    public List<Power> getFloorPowerGenerated(@RequestBody FloorEnergyRequest floorEnergyRequest){
        List<Power> powerList = new ArrayList<>();

        // 서비스 레이어 코드
        for(int i=0; i<10; i++){
            // 레포에서 data 를 뽑아와서 리스트에 추가하기
        }

        return powerList;
    }
}
