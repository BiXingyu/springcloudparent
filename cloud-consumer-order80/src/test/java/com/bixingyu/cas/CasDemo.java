package com.bixingyu.cas;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author BiXingyu
 * @create 2022-12-25 0:28
 */
@Slf4j
public class CasDemo {
    AtomicInteger atomicInteger = new AtomicInteger(0);
    Integer ticket = 0;
    @Test
    public void CasTest() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Thread1 thread1 = new Thread1();
            threads.add(thread1);
        }
        for(Thread thread : threads){
            System.out.println(thread.getName() + "\t START....");
            thread.start();
        }

        Thread.sleep(5000);
        System.out.println(atomicInteger);

    }

    class Thread1 extends Thread{

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {



//                synchronized (atomicInteger){
//
//                    if(ticket > 0) {
//                        System.out.println("1 " + ticket );
//                        ticket--;
//                    }else {
//                        break;
//                    }
//                }
//                for(;;){

                    int j = atomicInteger.get();

                    boolean b = atomicInteger.compareAndSet(j, j + 1);
                    System.out.println(Thread.currentThread().getName() + "\t" + b);
//                    if(b){
//                        break;
//                    }
//                }
            }
        }
    }
    class Thread2 extends Thread{
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {


//                synchronized (atomicInteger){
//
//                    if(ticket > 0) {
//                        System.out.println("1 " + ticket );
//                        ticket--;
//                    }else {
//                        break;
//                    }
//                }
                ticket++;
            }
        }
    }

}


