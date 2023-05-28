package sejong.capstone.team13.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sejong.capstone.team13.model.Power;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class DataAttributeRepositoryTest {
    @Autowired
    DataAttributeRepository dataAttributeRepository;

    @Test
    public void asyncTest(){

        CompletableFuture<List<Power>> volt = dataAttributeRepository.getFloorVoltList(1);
        volt.thenAccept(s-> System.out.println("hi"));

        CompletableFuture<List<Power>> ampere = dataAttributeRepository.getFloorAmpereList(1);
        ampere.thenAccept(s -> System.out.println("hello"));


        for(int i=0; i<10; i++){
            System.out.println(i);
            for(int j=0; j<1000000; j++);
        }
        // 비동기로 데이터 받아서 출력하기
//        ampereList.thenAccept()

//        CompletableFuture<Object> powerList = ampereList.thenCombine(voltList, (listByAmpere, listByVolt)-> {
//            List<Power> newList = new ArrayList<>(listByAmpere);
//            for(int i=0; i<listByVolt.size(); i++){
//                newList.get(i).setV(listByVolt.get(i).getV());
//            }
//            return newList;
//        });

//        System.out.println(powerList.join());
//        System.out.println(powerList.join().getClass());
    }

    @Test
    void supplyAsync() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            for(int i=0; i<100000000; i++);
            return "Hello : Thread: " + Thread.currentThread().getName();
        });


        System.out.println(1);
        System.out.println(future.get());
        System.out.println(2);
        System.out.println("Thread: " + Thread.currentThread().getName());
        System.out.println(3);
    }

    @Test
    void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            for(int i=0; i<10000000; i++);
            return "Thread1: " + Thread.currentThread().getName();
        }).thenAccept(s -> {
            System.out.println(s + " 1");
        });

        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> {
            return "Thread2: " + Thread.currentThread().getName();
        }).thenAccept(s -> {
            System.out.println(s + " 2");
        });


        System.out.println(future2.get());
        System.out.println(future.get());
        System.out.println("1");
    }

    @Test
    public void testRecent(){
        List<Power> al = dataAttributeRepository.getFloorRecentAmpere(1);
        List<Power> vl = dataAttributeRepository.getFloorRecentVolt(1);

        System.out.println("AL");
        System.out.println(al.size());
        for(Power p : al){
            System.out.println(p);
        }

        System.out.println("VL");
        System.out.println(vl.size());
        for(Power p : vl){
            System.out.println(p);
        }
    }

    @Test
    public void testNgnRecent(){
        List<Power> al = dataAttributeRepository.getNgnRecentAmpere();
        List<Power> vl = dataAttributeRepository.getNgnRecentVolt();

        System.out.println("AL");
        System.out.println(al.size());
        for(Power p : al){
            System.out.println(p);
        }

        System.out.println("VL");
        System.out.println(vl.size());
        for(Power p : vl){
            System.out.println(p);
        }
    }
}
