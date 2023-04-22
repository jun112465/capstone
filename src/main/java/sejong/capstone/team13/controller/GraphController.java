package sejong.capstone.team13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sejong.capstone.team13.model.Power;
import sejong.capstone.team13.model.request.FloorEnergyRequest;
import sejong.capstone.team13.repository.UpdatedDataRepository;
import sejong.capstone.team13.service.GraphService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GraphController {

    UpdatedDataRepository updatedDataRepository;
    GraphService graphService;

    @Autowired
    public GraphController(UpdatedDataRepository updatedDataRepository,
                           GraphService graphService){
        this.updatedDataRepository = updatedDataRepository;
        this.graphService = graphService;
    }

    @PostMapping("/graph")
    public List<Power> getGraphData(@RequestBody FloorEnergyRequest floorEnergyRequest){

        List<Power> powerList = null;

        switch (floorEnergyRequest.getInterval()){
            case 0: {
                // 10초 간격으로 데이터를 불러와야하는 경우
                powerList = updatedDataRepository.getFloorPower(floorEnergyRequest.getFloor());
                break;
            }
            case 1: {
                // 1일 간격으로 데이터를 불러와야하는 경우
                powerList = graphService.GetListData();
                break;
            }
            default: {
                powerList = null;
                break;
            }
        }

        return powerList;
    }
}
