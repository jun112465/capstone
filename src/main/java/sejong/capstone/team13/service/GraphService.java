package sejong.capstone.team13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sejong.capstone.team13.model.Power;
import sejong.capstone.team13.repository.DataAttributeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GraphService {


    DataAttributeRepository dataAttributeRepository;

    @Autowired
    GraphService(DataAttributeRepository dataAttributeRepository){
        this.dataAttributeRepository = dataAttributeRepository;
    }

    public ArrayList<Power> GetListData(){
        CompletableFuture<List<Power>> ampereList = dataAttributeRepository.getFloorAmpereList(1);
        CompletableFuture<List<Power>> voltList = dataAttributeRepository.getFloorVoltList(1);

        CompletableFuture<Object> powerList = ampereList.thenCombine(voltList, (listByAmpere, listByVolt)-> {
            List<Power> newList = new ArrayList<>(listByAmpere);
            for(int i=0; i<listByVolt.size(); i++){
                newList.get(i).setV(listByVolt.get(i).getV());
            }
            return newList;
        });

        return (ArrayList<Power>) powerList.join();
    }

}
