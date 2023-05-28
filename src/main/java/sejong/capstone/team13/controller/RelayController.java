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

    @GetMapping("/relay")
    @ResponseBody
    public List<Integer> getRelayStatus(@RequestParam(value = "relay-id") Integer relayId){
        return relayRepository.getRelayStatus(relayId);
    }

    @GetMapping("/switch-status")
    @ResponseBody
    public Integer getSwitchStatus(){
        return relayRepository.getSwitchStatus();
    }

    @GetMapping("/dr-status")
    @ResponseBody
    public List<RelayStatus> getDRStatus(){
        return relayRepository.getDRStatus();
    }

    /**
     * @param relayStatusRequest
     * contains the value of which floor to control and the current status of the relay
     */
    @PostMapping("/relay-control")
    @ResponseBody
    public boolean setRelayStatus(@RequestBody RelayStatusRequest relayStatusRequest){
        // relay 상태 바꾸는 코드 작성하기
        relayRepository.setRelayStatus(relayStatusRequest);
        return relayStatusRequest.getAfter();
    }
}
