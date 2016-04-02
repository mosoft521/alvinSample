package com.gmail.mosoft521.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用Curator的异步接口
 */
public class Create_Node_Background_Sample {
    static String path = "/zk-book";
    static CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString("localhost:2181").sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
    static CountDownLatch countDownLatch = new CountDownLatch(2);
    static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        curatorFramework.start();
        System.out.println("Main thread: " + Thread.currentThread().getName());

        //此处传入了自定义的Executor
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println("event[code: " + event.getResultCode() + ", type: " + event.getType() + "]");
                System.out.println("Thread of processResult: " + Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        }, executorService).forPath(path, "init".getBytes());

        //此处没有传入自定义的Executor
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println("event[code: " + event.getResultCode() + ", type: " + event.getType() + "]");
                System.out.println("Thread of processResult: " + Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        }).forPath(path, "init".getBytes());

        countDownLatch.await();
        executorService.shutdown();
    }
}
/*
2016-04-02 21:15:05,952 - WARN  - [main:CuratorZookeeperClient@95] - session timeout [5000] is less than connection timeout [15000]
2016-04-02 21:15:06,009 - INFO  - [main:CuratorFrameworkImpl@235] - Starting
2016-04-02 21:15:06,033 - INFO  - [main:Environment@100] - Client environment:zookeeper.version=3.4.8--1, built on 02/06/2016 03:18 GMT
2016-04-02 21:15:06,033 - INFO  - [main:Environment@100] - Client environment:host.name=192.168.1.3
2016-04-02 21:15:06,033 - INFO  - [main:Environment@100] - Client environment:java.version=1.8.0_74
2016-04-02 21:15:06,033 - INFO  - [main:Environment@100] - Client environment:java.vendor=Oracle Corporation
2016-04-02 21:15:06,033 - INFO  - [main:Environment@100] - Client environment:java.home=D:\tools\Java\jdk1.8.0_74\jre
2016-04-02 21:15:06,033 - INFO  - [main:Environment@100] - Client environment:java.class.path=D:\tools\Java\jdk1.8.0_74\jre\lib\charsets.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\deploy.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\access-bridge-64.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\cldrdata.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\dnsns.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\jaccess.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\jfxrt.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\localedata.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\nashorn.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunec.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunjce_provider.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunmscapi.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunpkcs11.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\zipfs.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\javaws.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jce.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jfr.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jfxswt.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jsse.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\management-agent.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\plugin.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\resources.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\rt.jar;E:\ws_ij_alvin\alvinSample\target\classes;E:\java\repository\org\slf4j\slf4j-api\1.7.20\slf4j-api-1.7.20.jar;E:\java\repository\org\apache\zookeeper\zookeeper\3.4.8\zookeeper-3.4.8.jar;E:\java\repository\org\slf4j\slf4j-log4j12\1.6.1\slf4j-log4j12-1.6.1.jar;E:\java\repository\log4j\log4j\1.2.16\log4j-1.2.16.jar;E:\java\repository\jline\jline\0.9.94\jline-0.9.94.jar;E:\java\repository\io\netty\netty\3.7.0.Final\netty-3.7.0.Final.jar;E:\java\repository\org\apache\curator\curator-recipes\2.10.0\curator-recipes-2.10.0.jar;E:\java\repository\org\apache\curator\curator-framework\2.10.0\curator-framework-2.10.0.jar;E:\java\repository\org\apache\curator\curator-client\2.10.0\curator-client-2.10.0.jar;E:\java\repository\org\apache\curator\curator-test\2.10.0\curator-test-2.10.0.jar;E:\java\repository\org\javassist\javassist\3.18.1-GA\javassist-3.18.1-GA.jar;E:\java\repository\org\apache\commons\commons-math\2.2\commons-math-2.2.jar;E:\java\repository\com\google\guava\guava\16.0.1\guava-16.0.1.jar;D:\Program Files (x86)\JetBrains\IntelliJ IDEA 15.0.2\lib\idea_rt.jar
2016-04-02 21:15:06,033 - INFO  - [main:Environment@100] - Client environment:java.library.path=D:\tools\Java\jdk1.8.0_74\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;D:\tools\Java\jdk1.8.0_74\bin;D:\tools\Java\jdk1.8.0_74\jre\bin;D:\tools\maven\apache-maven-3.3.9\bin;C:\ProgramData\Oracle\Java\javapath;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\ATI Technologies\ATI.ACE\Core-Static;D:\Program Files\EmEditor;D:\tools\tomcat\apache-tomcat-8.0.32\lib;D:\tools\tomcat\apache-tomcat-8.0.32\bin;D:\Program Files\AMD\ATI.ACE\Core-Static;D:\tools\Java\jdk1.7.0_80\bin;D:\tools\Java\jdk1.7.0_80\jre\bin;D:\tools\maven\apache-maven-3.3.3\bin;C:\ProgramData\Oracle\Java\javapath;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\ATI Technologies\ATI.ACE\Core-Static;D:\Program Files\EmEditor;D:\tools\tomcat\apache-tomcat-8.0.23\lib;D:\tools\tomcat\apache-tomcat-8.0.23\bin;.
2016-04-02 21:15:06,034 - INFO  - [main:Environment@100] - Client environment:java.io.tmpdir=C:\Users\Alvin\AppData\Local\Temp\
2016-04-02 21:15:06,034 - INFO  - [main:Environment@100] - Client environment:java.compiler=<NA>
2016-04-02 21:15:06,034 - INFO  - [main:Environment@100] - Client environment:os.name=Windows 7
2016-04-02 21:15:06,036 - INFO  - [main:Environment@100] - Client environment:os.arch=amd64
2016-04-02 21:15:06,036 - INFO  - [main:Environment@100] - Client environment:os.version=6.1
2016-04-02 21:15:06,036 - INFO  - [main:Environment@100] - Client environment:user.name=Alvin
2016-04-02 21:15:06,036 - INFO  - [main:Environment@100] - Client environment:user.home=C:\Users\Alvin
2016-04-02 21:15:06,038 - INFO  - [main:Environment@100] - Client environment:user.dir=E:\ws_ij_alvin\alvinSample
2016-04-02 21:15:06,039 - INFO  - [main:ZooKeeper@438] - Initiating client connection, connectString=localhost:2181 sessionTimeout=5000 watcher=org.apache.curator.ConnectionState@721e0f4f
2016-04-02 21:15:06,199 - INFO  - [main-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@1032] - Opening socket connection to server 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181. Will not attempt to authenticate using SASL (unknown error)
2016-04-02 21:15:06,203 - INFO  - [main-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@876] - Socket connection established to 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181, initiating session
Main thread: main
2016-04-02 21:15:06,260 - INFO  - [main-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@1299] - Session establishment complete on server 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181, sessionid = 0x153d717bfff0003, negotiated timeout = 5000
2016-04-02 21:15:06,268 - INFO  - [main-EventThread:ConnectionStateManager@228] - State change: CONNECTED
event[code: 0, type: CREATE]
Thread of processResult: pool-3-thread-1
event[code: -110, type: CREATE]
Thread of processResult: main-EventThread

Process finished with exit code 0
 */