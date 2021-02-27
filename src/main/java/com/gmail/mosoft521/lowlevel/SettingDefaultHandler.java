// lowlevel/SettingDefaultHandler.java
package com.gmail.mosoft521.lowlevel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SettingDefaultHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new ExceptionThread());
        es.shutdown();
    }
}
/* Output:
caught java.lang.RuntimeException
*/
