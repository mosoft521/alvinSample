package com.gmail.mosoft521.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 分布式锁
 */
public class Recipes_Lock {
    static String lock_path = "/curator_recipes_lock_path";
    static CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString("localhost:2181").retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) throws Exception {
        curatorFramework.start();
        final InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, lock_path);
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        interProcessMutex.acquire();
                    } catch (Exception e) {

                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.err.println("生成的订单号是：" + orderNo);
                    try {
                        interProcessMutex.release();
                    } catch (Exception e) {

                    }
                }
            }).start();
        }
        countDownLatch.countDown();
    }
}
/*
2016-04-02 18:55:58,335 - INFO  - [main:CuratorFrameworkImpl@235] - Starting
2016-04-02 18:55:58,505 - INFO  - [main:Environment@100] - Client environment:zookeeper.version=3.4.8--1, built on 02/06/2016 03:18 GMT
2016-04-02 18:55:58,532 - INFO  - [main:Environment@100] - Client environment:host.name=192.168.1.3
2016-04-02 18:55:58,532 - INFO  - [main:Environment@100] - Client environment:java.version=1.8.0_74
2016-04-02 18:55:58,532 - INFO  - [main:Environment@100] - Client environment:java.vendor=Oracle Corporation
2016-04-02 18:55:58,532 - INFO  - [main:Environment@100] - Client environment:java.home=D:\tools\Java\jdk1.8.0_74\jre
2016-04-02 18:55:58,532 - INFO  - [main:Environment@100] - Client environment:java.class.path=D:\tools\Java\jdk1.8.0_74\jre\lib\charsets.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\deploy.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\access-bridge-64.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\cldrdata.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\dnsns.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\jaccess.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\jfxrt.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\localedata.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\nashorn.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunec.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunjce_provider.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunmscapi.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunpkcs11.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\zipfs.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\javaws.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jce.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jfr.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jfxswt.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jsse.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\management-agent.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\plugin.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\resources.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\rt.jar;E:\ws_ij_alvin\alvinSample\target\classes;E:\java\repository\org\slf4j\slf4j-api\1.7.20\slf4j-api-1.7.20.jar;E:\java\repository\org\apache\zookeeper\zookeeper\3.4.8\zookeeper-3.4.8.jar;E:\java\repository\org\slf4j\slf4j-log4j12\1.6.1\slf4j-log4j12-1.6.1.jar;E:\java\repository\log4j\log4j\1.2.16\log4j-1.2.16.jar;E:\java\repository\jline\jline\0.9.94\jline-0.9.94.jar;E:\java\repository\io\netty\netty\3.7.0.Final\netty-3.7.0.Final.jar;E:\java\repository\org\apache\curator\curator-recipes\2.10.0\curator-recipes-2.10.0.jar;E:\java\repository\org\apache\curator\curator-framework\2.10.0\curator-framework-2.10.0.jar;E:\java\repository\org\apache\curator\curator-client\2.10.0\curator-client-2.10.0.jar;E:\java\repository\org\apache\curator\curator-test\2.10.0\curator-test-2.10.0.jar;E:\java\repository\org\javassist\javassist\3.18.1-GA\javassist-3.18.1-GA.jar;E:\java\repository\org\apache\commons\commons-math\2.2\commons-math-2.2.jar;E:\java\repository\com\google\guava\guava\16.0.1\guava-16.0.1.jar;D:\Program Files (x86)\JetBrains\IntelliJ IDEA 15.0.2\lib\idea_rt.jar
2016-04-02 18:55:58,533 - INFO  - [main:Environment@100] - Client environment:java.library.path=D:\tools\Java\jdk1.8.0_74\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;D:\tools\Java\jdk1.8.0_74\bin;D:\tools\Java\jdk1.8.0_74\jre\bin;D:\tools\maven\apache-maven-3.3.9\bin;C:\ProgramData\Oracle\Java\javapath;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\ATI Technologies\ATI.ACE\Core-Static;D:\Program Files\EmEditor;D:\tools\tomcat\apache-tomcat-8.0.32\lib;D:\tools\tomcat\apache-tomcat-8.0.32\bin;D:\Program Files\AMD\ATI.ACE\Core-Static;D:\tools\Java\jdk1.7.0_80\bin;D:\tools\Java\jdk1.7.0_80\jre\bin;D:\tools\maven\apache-maven-3.3.3\bin;C:\ProgramData\Oracle\Java\javapath;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\ATI Technologies\ATI.ACE\Core-Static;D:\Program Files\EmEditor;D:\tools\tomcat\apache-tomcat-8.0.23\lib;D:\tools\tomcat\apache-tomcat-8.0.23\bin;.
2016-04-02 18:55:58,533 - INFO  - [main:Environment@100] - Client environment:java.io.tmpdir=C:\Users\Alvin\AppData\Local\Temp\
2016-04-02 18:55:58,533 - INFO  - [main:Environment@100] - Client environment:java.compiler=<NA>
2016-04-02 18:55:58,533 - INFO  - [main:Environment@100] - Client environment:os.name=Windows 7
2016-04-02 18:55:58,534 - INFO  - [main:Environment@100] - Client environment:os.arch=amd64
2016-04-02 18:55:58,534 - INFO  - [main:Environment@100] - Client environment:os.version=6.1
2016-04-02 18:55:58,534 - INFO  - [main:Environment@100] - Client environment:user.name=Alvin
2016-04-02 18:55:58,534 - INFO  - [main:Environment@100] - Client environment:user.home=C:\Users\Alvin
2016-04-02 18:55:58,534 - INFO  - [main:Environment@100] - Client environment:user.dir=E:\ws_ij_alvin\alvinSample
2016-04-02 18:55:58,537 - INFO  - [main:ZooKeeper@438] - Initiating client connection, connectString=localhost:2181 sessionTimeout=60000 watcher=org.apache.curator.ConnectionState@546a03af
2016-04-02 18:55:58,741 - INFO  - [main-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1032] - Opening socket connection to server 127.0.0.1/127.0.0.1:2181. Will not attempt to authenticate using SASL (unknown error)
2016-04-02 18:55:58,749 - INFO  - [main-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@876] - Socket connection established to 127.0.0.1/127.0.0.1:2181, initiating session
2016-04-02 18:55:58,853 - INFO  - [main-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1299] - Session establishment complete on server 127.0.0.1/127.0.0.1:2181, sessionid = 0x153d689bd360005, negotiated timeout = 40000
2016-04-02 18:55:58,880 - INFO  - [main-EventThread:ConnectionStateManager@228] - State change: CONNECTED
2016-04-02 18:55:59,832 - WARN  - [Thread-2:ZKPaths$CreateModeHolder@76] - The version of ZooKeeper being used doesn't support Container nodes. CreateMode.PERSISTENT will be used instead.
生成的订单号是：18:56:00|231
生成的订单号是：18:56:00|332
生成的订单号是：18:56:00|412
生成的订单号是：18:56:00|538
生成的订单号是：18:56:00|729
生成的订单号是：18:56:00|806
生成的订单号是：18:56:00|936
生成的订单号是：18:56:00|970
生成的订单号是：18:56:01|104
生成的订单号是：18:56:01|268
生成的订单号是：18:56:01|386
生成的订单号是：18:56:01|459
生成的订单号是：18:56:01|534
生成的订单号是：18:56:01|627
生成的订单号是：18:56:01|694
生成的订单号是：18:56:01|799
生成的订单号是：18:56:01|883
生成的订单号是：18:56:02|004
生成的订单号是：18:56:02|076
生成的订单号是：18:56:02|146
生成的订单号是：18:56:02|198
生成的订单号是：18:56:02|245
生成的订单号是：18:56:02|300
生成的订单号是：18:56:02|368
生成的订单号是：18:56:02|453
生成的订单号是：18:56:02|553
生成的订单号是：18:56:02|603
生成的订单号是：18:56:02|719
生成的订单号是：18:56:02|802
生成的订单号是：18:56:02|856

Process finished with exit code 0
 */