// lowlevel/SwallowedException.java
package com.gmail.mosoft521.lowlevel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SwallowedException {
    public static void main(String[] args)
            throws InterruptedException {
        ExecutorService exec =
                Executors.newSingleThreadExecutor();
        exec.submit(() -> {
//        exec.execute(() -> {
            throw new RuntimeException();
        });
        exec.shutdown();
    }
}
/*
这个程序什么也不输出（然而，如果你用 execute 方法替换 submit() 方法，你就将会看到异常抛出。这说明在线程中抛出异常是很棘手的，需要特别注意的事情。
submit() //No exception
execute() //Exception in thread "pool-1-thread-1" java.lang.RuntimeException
你无法捕获到从线程逃逸的异常。一旦异常越过了任务的 run() 方法，它就会传递至控制台，除非你采取特殊步骤来捕获此类错误异常。
 */