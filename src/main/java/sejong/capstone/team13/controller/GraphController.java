package sejong.capstone.team13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
                // 1초 간격으로 데이터를 불러와야하는 경우
                powerList = updatedDataRepository.getFloorPower(floorEnergyRequest.getFloor());
                break;
            }
            case 1: {
                // 1일 간격으로 데이터를 불러와야하는 경우
                powerList = graphService.getFloorRecentPower(floorEnergyRequest.getFloor());
                break;
            }
            case 2: {
                // 30일 간격으로 데이터를 불러와야하는 경우
                powerList = graphService.getFloorRecentYearPower(floorEnergyRequest.getFloor());
                break;
            }
            default: {
                powerList = null;
                break;
            }
        }

        return powerList;
    }

    @PostMapping("/solar-graph")
    public List<Power> getSolarGraphData(@RequestBody FloorEnergyRequest floorEnergyRequest){

        List<Power> powerList = null;

        switch (floorEnergyRequest.getInterval()){
            case 0: {
                // 1초 간격으로 데이터를 불러와야하는 경우
                powerList = new ArrayList<>();
                powerList.add(updatedDataRepository.getNgnPower());
                break;
            }
            case 1: {
                // 1일 간격으로 데이터를 불러와야하는 경우
                powerList = graphService.getNgnRecentPower();
                break;
            }
            case 2: {
                powerList = graphService.getNgnRecentYearPower();
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
