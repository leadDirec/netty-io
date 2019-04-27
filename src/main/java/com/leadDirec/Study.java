package com.leadDirec;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Study {

    public static Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/spring/applicationContext-gate.xml");
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                lock.lock();
                applicationContext.close();
                condition.signalAll();
                lock.unlock();
            }

        });
        lock.lock();
        try {
            condition.await();
            System.out.println("=======project is exit==========");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.exit(0);
        }
    }

}

