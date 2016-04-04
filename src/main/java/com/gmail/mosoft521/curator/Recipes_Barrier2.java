package com.gmail.mosoft521.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Curator典型使用场景(五)分布式Barrier：另一种方式
 */
public class Recipes_Barrier2 {
    static String barrier_path = "/curator_recipes_barrier_path2";

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("localhost:2181").retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
                        client.start();
                        DistributedDoubleBarrier distributedDoubleBarrier = new DistributedDoubleBarrier(client, barrier_path, 5);
                        Thread.sleep(Math.round(Math.random() * 3000));//模拟业务
                        System.out.println(Thread.currentThread().getName() + "号进入barrier");
                        distributedDoubleBarrier.enter();
                        System.out.println(Thread.currentThread().getName() + "启动...");
                        Thread.sleep(Math.round(Math.random() * 3000));//模拟业务
                        distributedDoubleBarrier.leave();
                        System.out.println(Thread.currentThread().getName() + "退出...");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
/*
2016-04-04 16:17:49,412 - INFO  - [Thread-4:CuratorFrameworkImpl@235] - Starting
2016-04-04 16:17:49,412 - INFO  - [Thread-2:CuratorFrameworkImpl@235] - Starting
2016-04-04 16:17:49,412 - INFO  - [Thread-0:CuratorFrameworkImpl@235] - Starting
2016-04-04 16:17:49,412 - INFO  - [Thread-3:CuratorFrameworkImpl@235] - Starting
2016-04-04 16:17:49,412 - INFO  - [Thread-1:CuratorFrameworkImpl@235] - Starting
2016-04-04 16:17:49,425 - INFO  - [Thread-0:Environment@100] - Client environment:zookeeper.version=3.4.8--1, built on 02/06/2016 03:18 GMT
2016-04-04 16:17:49,426 - INFO  - [Thread-0:Environment@100] - Client environment:host.name=Alvin-PC
2016-04-04 16:17:49,426 - INFO  - [Thread-0:Environment@100] - Client environment:java.version=1.8.0_74
2016-04-04 16:17:49,426 - INFO  - [Thread-0:Environment@100] - Client environment:java.vendor=Oracle Corporation
2016-04-04 16:17:49,426 - INFO  - [Thread-0:Environment@100] - Client environment:java.home=D:\tools\Java\jdk1.8.0_74\jre
2016-04-04 16:17:49,426 - INFO  - [Thread-0:Environment@100] - Client environment:java.class.path=D:\tools\Java\jdk1.8.0_74\jre\lib\charsets.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\deploy.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\access-bridge-64.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\cldrdata.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\dnsns.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\jaccess.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\jfxrt.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\localedata.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\nashorn.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunec.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunjce_provider.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunmscapi.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunpkcs11.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\zipfs.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\javaws.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jce.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jfr.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jfxswt.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jsse.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\management-agent.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\plugin.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\resources.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\rt.jar;E:\ws_ij_alvin\alvinSample\target\classes;E:\java\repository\org\slf4j\slf4j-api\1.7.20\slf4j-api-1.7.20.jar;E:\java\repository\org\apache\zookeeper\zookeeper\3.4.8\zookeeper-3.4.8.jar;E:\java\repository\org\slf4j\slf4j-log4j12\1.6.1\slf4j-log4j12-1.6.1.jar;E:\java\repository\log4j\log4j\1.2.16\log4j-1.2.16.jar;E:\java\repository\jline\jline\0.9.94\jline-0.9.94.jar;E:\java\repository\io\netty\netty\3.7.0.Final\netty-3.7.0.Final.jar;E:\java\repository\org\apache\curator\curator-recipes\2.10.0\curator-recipes-2.10.0.jar;E:\java\repository\org\apache\curator\curator-framework\2.10.0\curator-framework-2.10.0.jar;E:\java\repository\org\apache\curator\curator-client\2.10.0\curator-client-2.10.0.jar;E:\java\repository\com\google\guava\guava\16.0.1\guava-16.0.1.jar;E:\java\repository\org\apache\curator\curator-test\2.10.0\curator-test-2.10.0.jar;E:\java\repository\org\javassist\javassist\3.18.1-GA\javassist-3.18.1-GA.jar;E:\java\repository\org\apache\commons\commons-math\2.2\commons-math-2.2.jar;D:\Program Files (x86)\JetBrains\IntelliJ IDEA 2016.1\lib\idea_rt.jar
2016-04-04 16:17:49,426 - INFO  - [Thread-0:Environment@100] - Client environment:java.library.path=D:\tools\Java\jdk1.8.0_74\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;D:\tools\Java\jdk1.8.0_74\bin;D:\tools\Java\jdk1.8.0_74\jre\bin;D:\tools\maven\apache-maven-3.3.9\bin;C:\ProgramData\Oracle\Java\javapath;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;d:\Program Files\AMD\ATI.ACE\Core-Static;;d:\tools\Docker Toolbox;.
2016-04-04 16:17:49,426 - INFO  - [Thread-0:Environment@100] - Client environment:java.io.tmpdir=C:\Users\Alvin\AppData\Local\Temp\
2016-04-04 16:17:49,426 - INFO  - [Thread-0:Environment@100] - Client environment:java.compiler=<NA>
2016-04-04 16:17:49,427 - INFO  - [Thread-0:Environment@100] - Client environment:os.name=Windows 7
2016-04-04 16:17:49,427 - INFO  - [Thread-0:Environment@100] - Client environment:os.arch=amd64
2016-04-04 16:17:49,429 - INFO  - [Thread-0:Environment@100] - Client environment:os.version=6.1
2016-04-04 16:17:49,429 - INFO  - [Thread-0:Environment@100] - Client environment:user.name=Alvin
2016-04-04 16:17:49,429 - INFO  - [Thread-0:Environment@100] - Client environment:user.home=C:\Users\Alvin
2016-04-04 16:17:49,429 - INFO  - [Thread-0:Environment@100] - Client environment:user.dir=E:\ws_ij_alvin\alvinSample
2016-04-04 16:17:49,430 - INFO  - [Thread-0:ZooKeeper@438] - Initiating client connection, connectString=localhost:2181 sessionTimeout=60000 watcher=org.apache.curator.ConnectionState@4479a47b
2016-04-04 16:17:49,430 - INFO  - [Thread-1:ZooKeeper@438] - Initiating client connection, connectString=localhost:2181 sessionTimeout=60000 watcher=org.apache.curator.ConnectionState@6fdd696d
2016-04-04 16:17:49,430 - INFO  - [Thread-4:ZooKeeper@438] - Initiating client connection, connectString=localhost:2181 sessionTimeout=60000 watcher=org.apache.curator.ConnectionState@742c24bc
2016-04-04 16:17:49,431 - INFO  - [Thread-3:ZooKeeper@438] - Initiating client connection, connectString=localhost:2181 sessionTimeout=60000 watcher=org.apache.curator.ConnectionState@e738b55
2016-04-04 16:17:49,430 - INFO  - [Thread-2:ZooKeeper@438] - Initiating client connection, connectString=localhost:2181 sessionTimeout=60000 watcher=org.apache.curator.ConnectionState@ae6365b
2016-04-04 16:17:49,516 - INFO  - [Thread-4-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1032] - Opening socket connection to server 127.0.0.1/127.0.0.1:2181. Will not attempt to authenticate using SASL (unknown error)
2016-04-04 16:17:49,516 - INFO  - [Thread-3-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@1032] - Opening socket connection to server 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181. Will not attempt to authenticate using SASL (unknown error)
2016-04-04 16:17:49,516 - INFO  - [Thread-2-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1032] - Opening socket connection to server 127.0.0.1/127.0.0.1:2181. Will not attempt to authenticate using SASL (unknown error)
2016-04-04 16:17:49,516 - INFO  - [Thread-1-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1032] - Opening socket connection to server 127.0.0.1/127.0.0.1:2181. Will not attempt to authenticate using SASL (unknown error)
2016-04-04 16:17:49,518 - INFO  - [Thread-3-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@876] - Socket connection established to 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181, initiating session
2016-04-04 16:17:49,519 - INFO  - [Thread-1-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@876] - Socket connection established to 127.0.0.1/127.0.0.1:2181, initiating session
2016-04-04 16:17:49,518 - INFO  - [Thread-4-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@876] - Socket connection established to 127.0.0.1/127.0.0.1:2181, initiating session
2016-04-04 16:17:49,518 - INFO  - [Thread-2-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@876] - Socket connection established to 127.0.0.1/127.0.0.1:2181, initiating session
2016-04-04 16:17:49,518 - INFO  - [Thread-0-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@1032] - Opening socket connection to server 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181. Will not attempt to authenticate using SASL (unknown error)
2016-04-04 16:17:49,520 - INFO  - [Thread-0-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@876] - Socket connection established to 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181, initiating session
2016-04-04 16:17:49,641 - INFO  - [Thread-3-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@1299] - Session establishment complete on server 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181, sessionid = 0x153e03ecf1b0010, negotiated timeout = 40000
2016-04-04 16:17:49,642 - INFO  - [Thread-4-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1299] - Session establishment complete on server 127.0.0.1/127.0.0.1:2181, sessionid = 0x153e03ecf1b0011, negotiated timeout = 40000
2016-04-04 16:17:49,643 - INFO  - [Thread-1-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1299] - Session establishment complete on server 127.0.0.1/127.0.0.1:2181, sessionid = 0x153e03ecf1b0012, negotiated timeout = 40000
2016-04-04 16:17:49,644 - INFO  - [Thread-2-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1299] - Session establishment complete on server 127.0.0.1/127.0.0.1:2181, sessionid = 0x153e03ecf1b0013, negotiated timeout = 40000
2016-04-04 16:17:49,646 - INFO  - [Thread-0-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@1299] - Session establishment complete on server 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181, sessionid = 0x153e03ecf1b0014, negotiated timeout = 40000
2016-04-04 16:17:49,654 - INFO  - [Thread-4-EventThread:ConnectionStateManager@228] - State change: CONNECTED
2016-04-04 16:17:49,655 - INFO  - [Thread-0-EventThread:ConnectionStateManager@228] - State change: CONNECTED
2016-04-04 16:17:49,654 - INFO  - [Thread-1-EventThread:ConnectionStateManager@228] - State change: CONNECTED
2016-04-04 16:17:49,655 - INFO  - [Thread-2-EventThread:ConnectionStateManager@228] - State change: CONNECTED
2016-04-04 16:17:49,657 - INFO  - [Thread-3-EventThread:ConnectionStateManager@228] - State change: CONNECTED
Thread-2号进入barrier
2016-04-04 16:17:50,540 - WARN  - [Thread-2:ZKPaths$CreateModeHolder@76] - The version of ZooKeeper being used doesn't support Container nodes. CreateMode.PERSISTENT will be used instead.
Thread-1号进入barrier
Thread-3号进入barrier
Thread-4号进入barrier
Thread-0号进入barrier
Thread-1启动...
Thread-2启动...
Thread-3启动...
Thread-0启动...
Thread-4启动...
Thread-1退出...
Thread-2退出...
Thread-3退出...
Thread-4退出...
Thread-0退出...
 */