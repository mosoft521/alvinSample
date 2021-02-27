// lowlevel/CaptureUncaughtException.java
package com.gmail.mosoft521.lowlevel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class ExceptionThread2 implements Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t.getName());
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        RuntimeException re = new RuntimeException();
        System.out.println("throw re: " + re);
        System.out.println("throw re: " + re.getClass().getName() + "@" + Integer.toHexString(re.hashCode()));
        throw re;
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
        System.out.println("caught re: " + e.getClass().getName() + "@" + Integer.toHexString(e.hashCode()));
    }
}

class HandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        return t;
    }
}

public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionThread2());
        exec.shutdown();
    }
}
/* Output:
HandlerThreadFactory@4e25154f creating new Thread
created Thread[Thread-0,5,main]
eh = MyUncaughtExceptionHandler@70dea4e
run() by Thread-0
eh = MyUncaughtExceptionHandler@70dea4e
caught java.lang.RuntimeException
-----------------------------
com.gmail.mosoft521.lowlevel.HandlerThreadFactory@eed1f14 creating new Thread
created Thread[Thread-0,5,main]
eh = com.gmail.mosoft521.lowlevel.MyUncaughtExceptionHandler@7229724f

run() by Thread-0
eh = com.gmail.mosoft521.lowlevel.MyUncaughtExceptionHandler@7229724f
caught java.lang.RuntimeException
-----------------------------
com.gmail.mosoft521.lowlevel.HandlerThreadFactory@eed1f14 creating new Thread
created Thread[Thread-0,5,main]
eh = com.gmail.mosoft521.lowlevel.MyUncaughtExceptionHandler@7229724f

run() by Thread-0
eh = com.gmail.mosoft521.lowlevel.MyUncaughtExceptionHandler@7229724f
throw re: java.lang.RuntimeException
throw re: java.lang.RuntimeException@27983662

caught java.lang.RuntimeException
caught re: java.lang.RuntimeException@27983662
*/
