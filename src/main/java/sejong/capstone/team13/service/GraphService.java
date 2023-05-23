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

    public ArrayList<Power> getPowerListData(){
        CompletableFuture<List<Power>> ampereList = dataAttributeRepository.getFloorAmpereList(1);
        CompletableFuture<List<Power>> voltList = dataAttributeRepository.getFloorVoltList(1);

        ampereList.thenAccept(s -> {
            System.out.println("ampere 요청 종료");
        });
        voltList.thenAccept(s -> {
            System.out.println("volt 요청 종료");
        });


        CompletableFuture<Object> powerList = ampereList.thenCombine(voltList, (listByAmpere, listByVolt)-> {
            List<Power> newList = new ArrayList<>(listByAmpere);
            for(int i=0; i<listByVolt.size(); i++){
                newList.get(i).setV(listByVolt.get(i).getV());
                newList.get(i).setP(newList.get(i).getI() * newList.get(i).getV());
            }
            return newList;
        });

        powerList.thenAccept(s -> {
            System.out.println(s);
        });

        return (ArrayList<Power>) powerList.join();
    }


    public ArrayList<Power> getNgnListData(){
        CompletableFuture<List<Power>> ampereList = dataAttributeRepository.getNgnAmpereList();
        CompletableFuture<List<Power>> voltList = dataAttributeRepository.getNgnVoltList();

        CompletableFuture<Object> powerList = ampereList.thenCombine(voltList, (listByAmpere, listByVolt)-> {
            List<Power> newList = new ArrayList<>(listByAmpere);
            for(int i=0; i<listByVolt.size(); i++){
                newList.get(i).setV(listByVolt.get(i).getV());
                newList.get(i).setP(newList.get(i).getI() * newList.get(i).getV());
            }
            return newList;
        });

        powerList.thenAccept(s -> System.out.println(s));


        return (ArrayList<Power>) powerList.join();
    }

}
