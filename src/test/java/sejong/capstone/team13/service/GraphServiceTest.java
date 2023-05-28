package sejong.capstone.team13.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sejong.capstone.team13.model.Power;
import sejong.capstone.team13.repository.DataAttributeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
public class GraphServiceTest {

    @Autowired
    GraphService graphService;

    @Autowired
    DataAttributeRepository dr;

    @Test
    public void testListDataBring(){
        graphService.getPowerListData();
    }

    @Test
    public void testFloorDate(){

        System.out.println(graphService.getFloorRecentPower(1));

        List<Power> al = dr.getFloorRecentAmpere(1);
        List<Power> vl = dr.getFloorRecentVolt(1);

        String recent = "";
        Iterator<Power> iterator = al.iterator();
        while (iterator.hasNext()) {
            Power data = iterator.next();
            String date = data.getTime();

            if(date.equals(recent))
                iterator.remove();

            recent = date;
        }

        recent = "";
        iterator = vl.iterator();
        while (iterator.hasNext()) {
            Power data = iterator.next();
            String date = data.getTime();

            if(date.equals(recent))
                iterator.remove();

            recent = date;
        }

        System.out.println(al);
        System.out.println(vl);

        for(int i=0; i<al.size(); i++){
            al.get(i).setV(vl.get(i).getV());
        }

        System.out.println(al.size());
        System.out.println(al);
    }

    @Test
    public void testNgn(){
        List<Power> ampereList = dr.getNgnRecentAmpere();
        List<Power> voltList = dr.getNgnRecentVolt();

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

        System.out.println(ampereList);
        System.out.println(voltList);
        // 합치기
//        for(int i=0; i<ampereList.size(); i++){
//            ampereList.get(i).setV(voltList.get(i).getV());
//            ampereList.get(i).setP(ampereList.get(i).getI() * ampereList.get(i).getV());
//        }

        System.out.println(ampereList);
    }

    @Test
    public void setDate(){
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);

        List<Power> ampereList = dr.getNgnMonthAmpere();
        List<Power> voltList = dr.getNgnMonthVolt();

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
        today = LocalDate.now();
        for(int i=voltList.size()-1; i>=0; i--){
            LocalDate tmpDate = LocalDate.parse(voltList.get(i).getTime());
            if(today.minusMonths(cnt).equals(tmpDate)) {
                newVoltList.add(0, ampereList.get(i) );
                cnt++;
            }
        }
        voltList = newVoltList;

        System.out.println(ampereList);
        System.out.println(voltList);
    }
}
