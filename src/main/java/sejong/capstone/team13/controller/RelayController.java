package sejong.capstone.team13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sejong.capstone.team13.model.request.RelayStatusRequest;
import sejong.capstone.team13.model.response.RelayStatus;
import sejong.capstone.team13.repository.RelayRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RelayController {

    RelayRepository relayRepository;

    @Autowired
    public RelayController(RelayRepository relayRepository){
        this.relayRepository = relayRepository;
    }

    /**
     *
     * @return
     */
    @GetMapping("/relay-status")
    @ResponseBody
    public List<RelayStatus> getRelayStatus(){
        // 각 층의 relay 상태를 return 해준다.
        List<RelayStatus> relayStatusList = relayRepository.getRelayStatus();
        return relayStatusList;
    }

    @GetMapping("/switch-status")
    @ResponseBody
    public Integer getSwitchStatus(){
        return relayRepository.getSwitchStatus();
    }

    /**
     * @param relayStatusRequest
     * contains the value of which floor to control and the current status of the relay
     */
    @PostMapping("/relay-control")
    public void setRelayStatus(@RequestBody RelayStatusRequest relayStatusRequest){
        // relay 상태 바꾸는 코드 작성하기
        //
    }
}
