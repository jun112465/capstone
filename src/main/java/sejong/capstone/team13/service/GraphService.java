package sejong.capstone.team13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sejong.capstone.team13.model.Power;
import sejong.capstone.team13.repository.DataAttributeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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


    public ArrayList<Power> getNgnRecentPower(){
        List<Power> ampereList = dataAttributeRepository.getNgnRecentAmpere();
        List<Power> voltList = dataAttributeRepository.getNgnRecentVolt();

        // 중복 날짜 제거
        String recent = "";
        Iterator<Power> iterator = ampereList.iterator();
        while (iterator.hasNext()) {
            Power data = iterator.next();
            String date = data.getTime();

            if(date.equals(recent))
                iterator.remove();

            recent = date;
        }

        recent = "";
        iterator = voltList.iterator();
        while (iterator.hasNext()) {
            Power data = iterator.next();
            String date = data.getTime();

            if(date.equals(recent))
                iterator.remove();

            recent = date;
        }

        while(ampereList.size() > voltList.size())
            ampereList.remove(0);
        while(ampereList.size() < voltList.size())
            voltList.remove(0);

        // 합치기
        for(int i=0; i<ampereList.size(); i++){
            ampereList.get(i).setV(voltList.get(i).getV());
            ampereList.get(i).setP(ampereList.get(i).getI() * ampereList.get(i).getV());
        }

        return (ArrayList<Power>) ampereList;
    }

    public ArrayList<Power> getFloorRecentPower(int floor){
        List<Power> ampereList = dataAttributeRepository.getFloorRecentAmpere(floor);
        List<Power> voltList = dataAttributeRepository.getFloorRecentVolt(floor);

        // 중복 날짜 제거
        String recent = "";
        Iterator<Power> iterator = ampereList.iterator();
        while (iterator.hasNext()) {
            Power data = iterator.next();
            String date = data.getTime();

            if(date.equals(recent))
                iterator.remove();

            recent = date;
        }

        recent = "";
        iterator = voltList.iterator();
        while (iterator.hasNext()) {
            Power data = iterator.next();
            String date = data.getTime();

            if(date.equals(recent))
                iterator.remove();

            recent = date;
        }

        // 합치기
        for(int i=0; i<ampereList.size(); i++){
            ampereList.get(i).setV(voltList.get(i).getV());
            ampereList.get(i).setP(ampereList.get(i).getI() * ampereList.get(i).getV());
        }

        return (ArrayList<Power>) ampereList;
    }

    public ArrayList<Power> getNgnRecentYearPower(){
        List<Power> ampereList = dataAttributeRepository.getNgnMonthAmpere();
        List<Power> voltList = dataAttributeRepository.getNgnMonthVolt();

        int cnt = 0;
        List<Power> newAmpereList = new ArrayList<>();
        newAmpereList.add(ampereList.get(ampereList.size()-1));
        LocalDate today = LocalDate.now();
        for(int i=ampereList.size()-1; i>=0; i--){
            LocalDate tmpDate = LocalDate.parse(ampereList.get(i).getTime());
            if(tmpDate.isBefore(today.minusMonths(cnt))){
                newAmpereList.add(0, ampereList.get(i));
                cnt++;
            }
        }
        ampereList = newAmpereList;

        cnt = 0;
        List<Power> newVoltList = new ArrayList<>();
        newVoltList.add(ampereList.get(ampereList.size()-1));
        today = LocalDate.now();
        for(int i=voltList.size()-1; i>=0; i--){
            LocalDate tmpDate = LocalDate.parse(voltList.get(i).getTime());
            if(tmpDate.isBefore(today.minusMonths(cnt))){
                newVoltList.add(0, voltList.get(i));
                cnt++;
            }
        }
        voltList = newVoltList;

        // 중복 날짜 제거
        String recent = "";
        Iterator<Power> iterator = ampereList.iterator();
        while (iterator.hasNext()) {
            Power data = iterator.next();
            String date = data.getTime();

            if(date.equals(recent))
                iterator.remove();

            recent = date;
        }

        recent = "";
        iterator = voltList.iterator();
        while (iterator.hasNext()) {
            Power data = iterator.next();
            String date = data.getTime();

            if(date.equals(recent))
                iterator.remove();

            recent = date;
        }

        while(ampereList.size() > voltList.size())
            ampereList.remove(0);
        while(ampereList.size() < voltList.size())
            voltList.remove(0);

        // 합치기
        for(int i=0; i<ampereList.size(); i++){
            ampereList.get(i).setV(voltList.get(i).getV());
            ampereList.get(i).setP(ampereList.get(i).getI() * ampereList.get(i).getV());
        }

        return (ArrayList<Power>) ampereList;
    }

    public ArrayList<Power> getFloorRecentYearPower(int floor){
        List<Power> ampereList = dataAttributeRepository.getFloorMonthAmpere(floor);
        List<Power> voltList = dataAttributeRepository.getFloorMonthVolt(floor);

        int cnt = 0;
        List<Power> newAmpereList = new ArrayList<>();
        newAmpereList.add(ampereList.get(ampereList.size()-1));
        LocalDate today = LocalDate.now();
        for(int i=ampereList.size()-1; i>=0; i--){
            LocalDate tmpDate = LocalDate.parse(ampereList.get(i).getTime());
            if(tmpDate.isBefore(today.minusMonths(cnt))){
                newAmpereList.add(0, ampereList.get(i));
                cnt++;
            }
        }
        ampereList = newAmpereList;

        cnt = 0;
        List<Power> newVoltList = new ArrayList<>();
        newVoltList.add(ampereList.get(ampereList.size()-1));
        today = LocalDate.now();
        for(int i=voltList.size()-1; i>=0; i--){
            LocalDate tmpDate = LocalDate.parse(voltList.get(i).getTime());
            if(tmpDate.isBefore(today.minusMonths(cnt))){
                newVoltList.add(0, voltList.get(i));
                cnt++;
            }
        }
        voltList = newVoltList;

        // 중복 날짜 제거
        String recent = "";
        Iterator<Power> iterator = ampereList.iterator();
        while (iterator.hasNext()) {
            Power data = iterator.next();
            String date = data.getTime();

            if(date.equals(recent))
                iterator.remove();

            recent = date;
        }

        recent = "";
        iterator = voltList.iterator();
        while (iterator.hasNext()) {
            Power data = iterator.next();
            String date = data.getTime();

            if(date.equals(recent))
                iterator.remove();

            recent = date;
        }

        while(ampereList.size() > voltList.size())
            ampereList.remove(0);
        while(ampereList.size() < voltList.size())
            voltList.remove(0);

        // 합치기
        for(int i=0; i<ampereList.size(); i++){
            ampereList.get(i).setV(voltList.get(i).getV());
            ampereList.get(i).setP(ampereList.get(i).getI() * ampereList.get(i).getV());
        }

        return (ArrayList<Power>) ampereList;
    }
}
