package com.dj.javaCode.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    private CyclicBarrier cb = new CyclicBarrier(4);
    private Random rnd = new Random();

    class TaskDemo implements Runnable{
        private String id;
        TaskDemo(String id){
            this.id = id;
        }
        @Override
        public void run(){
            try {
                Thread.sleep(rnd.nextInt(1000));
                System.out.println("Thread " + id + " will wait");
                cb.await();
                System.out.println("-------Thread " + id + " is over");
            } catch (InterruptedException e) {

            } catch (BrokenBarrierException e) {

            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrierTest test = new CyclicBarrierTest();
        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(test.new TaskDemo("a"));
        es.submit(test.new TaskDemo("b"));
        es.submit(test.new TaskDemo("c"));
        es.submit(test.new TaskDemo("d"));
        es.shutdown();

    }

}
