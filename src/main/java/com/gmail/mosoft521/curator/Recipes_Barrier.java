package com.gmail.mosoft521.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Curator典型使用场景(五)分布式Barrier：
 */
public class Recipes_Barrier {
    static String barrier_path = "/curator_recipes_barrier_path";
    static DistributedBarrier distributedBarrier;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("localhost:2181").retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
                        client.start();
                        distributedBarrier = new DistributedBarrier(client, barrier_path);
                        System.out.println(Thread.currentThread().getName() + "号barrier设置");
                        distributedBarrier.setBarrier();
                        distributedBarrier.waitOnBarrier();
                        System.out.println(Thread.currentThread().getName() + "号barrier启动...");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            Thread.sleep(8000);
            distributedBarrier.removeBarrier();
        }
    }
}
/*
2016-04-04 16:10:32,428 - INFO  - [Thread-0:CuratorFrameworkImpl@235] - Starting
2016-04-04 16:10:32,448 - INFO  - [Thread-0:Environment@100] - Client environment:zookeeper.version=3.4.8--1, built on 02/06/2016 03:18 GMT
2016-04-04 16:10:32,451 - INFO  - [Thread-0:Environment@100] - Client environment:host.name=Alvin-PC
2016-04-04 16:10:32,451 - INFO  - [Thread-0:Environment@100] - Client environment:java.version=1.8.0_74
2016-04-04 16:10:32,451 - INFO  - [Thread-0:Environment@100] - Client environment:java.vendor=Oracle Corporation
2016-04-04 16:10:32,451 - INFO  - [Thread-0:Environment@100] - Client environment:java.home=D:\tools\Java\jdk1.8.0_74\jre
2016-04-04 16:10:32,451 - INFO  - [Thread-0:Environment@100] - Client environment:java.class.path=D:\tools\Java\jdk1.8.0_74\jre\lib\charsets.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\deploy.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\access-bridge-64.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\cldrdata.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\dnsns.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\jaccess.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\jfxrt.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\localedata.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\nashorn.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunec.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunjce_provider.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunmscapi.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunpkcs11.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\zipfs.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\javaws.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jce.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jfr.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jfxswt.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jsse.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\management-agent.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\plugin.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\resources.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\rt.jar;E:\ws_ij_alvin\alvinSample\target\classes;E:\java\repository\org\slf4j\slf4j-api\1.7.20\slf4j-api-1.7.20.jar;E:\java\repository\org\apache\zookeeper\zookeeper\3.4.8\zookeeper-3.4.8.jar;E:\java\repository\org\slf4j\slf4j-log4j12\1.6.1\slf4j-log4j12-1.6.1.jar;E:\java\repository\log4j\log4j\1.2.16\log4j-1.2.16.jar;E:\java\repository\jline\jline\0.9.94\jline-0.9.94.jar;E:\java\repository\io\netty\netty\3.7.0.Final\netty-3.7.0.Final.jar;E:\java\repository\org\apache\curator\curator-recipes\2.10.0\curator-recipes-2.10.0.jar;E:\java\repository\org\apache\curator\curator-framework\2.10.0\curator-framework-2.10.0.jar;E:\java\repository\org\apache\curator\curator-client\2.10.0\curator-client-2.10.0.jar;E:\java\repository\com\google\guava\guava\16.0.1\guava-16.0.1.jar;E:\java\repository\org\apache\curator\curator-test\2.10.0\curator-test-2.10.0.jar;E:\java\repository\org\javassist\javassist\3.18.1-GA\javassist-3.18.1-GA.jar;E:\java\repository\org\apache\commons\commons-math\2.2\commons-math-2.2.jar;D:\Program Files (x86)\JetBrains\IntelliJ IDEA 2016.1\lib\idea_rt.jar
2016-04-04 16:10:32,451 - INFO  - [Thread-0:Environment@100] - Client environment:java.library.path=D:\tools\Java\jdk1.8.0_74\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;D:\tools\Java\jdk1.8.0_74\bin;D:\tools\Java\jdk1.8.0_74\jre\bin;D:\tools\maven\apache-maven-3.3.9\bin;C:\ProgramData\Oracle\Java\javapath;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;d:\Program Files\AMD\ATI.ACE\Core-Static;;d:\tools\Docker Toolbox;.
2016-04-04 16:10:32,452 - INFO  - [Thread-0:Environment@100] - Client environment:java.io.tmpdir=C:\Users\Alvin\AppData\Local\Temp\
2016-04-04 16:10:32,452 - INFO  - [Thread-0:Environment@100] - Client environment:java.compiler=<NA>
2016-04-04 16:10:32,452 - INFO  - [Thread-0:Environment@100] - Client environment:os.name=Windows 7
2016-04-04 16:10:32,452 - INFO  - [Thread-0:Environment@100] - Client environment:os.arch=amd64
2016-04-04 16:10:32,452 - INFO  - [Thread-0:Environment@100] - Client environment:os.version=6.1
2016-04-04 16:10:32,452 - INFO  - [Thread-0:Environment@100] - Client environment:user.name=Alvin
2016-04-04 16:10:32,452 - INFO  - [Thread-0:Environment@100] - Client environment:user.home=C:\Users\Alvin
2016-04-04 16:10:32,453 - INFO  - [Thread-0:Environment@100] - Client environment:user.dir=E:\ws_ij_alvin\alvinSample
2016-04-04 16:10:32,457 - INFO  - [Thread-0:ZooKeeper@438] - Initiating client connection, connectString=localhost:2181 sessionTimeout=60000 watcher=org.apache.curator.ConnectionState@531e79a8
Thread-0号barrier设置
2016-04-04 16:10:32,578 - INFO  - [Thread-0-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@1032] - Opening socket connection to server 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181. Will not attempt to authenticate using SASL (unknown error)
2016-04-04 16:10:32,580 - INFO  - [Thread-0-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@876] - Socket connection established to 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181, initiating session
2016-04-04 16:10:32,630 - INFO  - [Thread-0-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@1299] - Session establishment complete on server 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181, sessionid = 0x153e03ecf1b000b, negotiated timeout = 40000
2016-04-04 16:10:32,637 - INFO  - [Thread-0-EventThread:ConnectionStateManager@228] - State change: CONNECTED
2016-04-04 16:10:40,329 - INFO  - [Thread-2:CuratorFrameworkImpl@235] - Starting
2016-04-04 16:10:40,330 - INFO  - [Thread-2:ZooKeeper@438] - Initiating client connection, connectString=localhost:2181 sessionTimeout=60000 watcher=org.apache.curator.ConnectionState@ed6dd66
Thread-2号barrier设置
Thread-0号barrier启动...
2016-04-04 16:10:40,333 - INFO  - [Thread-2-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1032] - Opening socket connection to server 127.0.0.1/127.0.0.1:2181. Will not attempt to authenticate using SASL (unknown error)
2016-04-04 16:10:40,334 - INFO  - [Thread-2-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@876] - Socket connection established to 127.0.0.1/127.0.0.1:2181, initiating session
2016-04-04 16:10:40,376 - INFO  - [Thread-2-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1299] - Session establishment complete on server 127.0.0.1/127.0.0.1:2181, sessionid = 0x153e03ecf1b000c, negotiated timeout = 40000
2016-04-04 16:10:40,377 - INFO  - [Thread-2-EventThread:ConnectionStateManager@228] - State change: CONNECTED
2016-04-04 16:10:48,592 - INFO  - [Thread-3:CuratorFrameworkImpl@235] - Starting
Thread-2号barrier启动...
2016-04-04 16:10:48,594 - INFO  - [Thread-3:ZooKeeper@438] - Initiating client connection, connectString=localhost:2181 sessionTimeout=60000 watcher=org.apache.curator.ConnectionState@572ff49f
Thread-3号barrier设置
2016-04-04 16:10:48,600 - INFO  - [Thread-3-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1032] - Opening socket connection to server 127.0.0.1/127.0.0.1:2181. Will not attempt to authenticate using SASL (unknown error)
2016-04-04 16:10:48,601 - INFO  - [Thread-3-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@876] - Socket connection established to 127.0.0.1/127.0.0.1:2181, initiating session
2016-04-04 16:10:48,740 - INFO  - [Thread-3-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1299] - Session establishment complete on server 127.0.0.1/127.0.0.1:2181, sessionid = 0x153e03ecf1b000d, negotiated timeout = 40000
2016-04-04 16:10:48,740 - INFO  - [Thread-3-EventThread:ConnectionStateManager@228] - State change: CONNECTED
Thread-3号barrier启动...
2016-04-04 16:10:56,959 - INFO  - [Thread-4:CuratorFrameworkImpl@235] - Starting
2016-04-04 16:10:56,961 - INFO  - [Thread-4:ZooKeeper@438] - Initiating client connection, connectString=localhost:2181 sessionTimeout=60000 watcher=org.apache.curator.ConnectionState@66ffd5b0
Thread-4号barrier设置
2016-04-04 16:10:56,965 - INFO  - [Thread-4-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1032] - Opening socket connection to server 127.0.0.1/127.0.0.1:2181. Will not attempt to authenticate using SASL (unknown error)
2016-04-04 16:10:56,967 - INFO  - [Thread-4-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@876] - Socket connection established to 127.0.0.1/127.0.0.1:2181, initiating session
2016-04-04 16:10:57,042 - INFO  - [Thread-4-SendThread(127.0.0.1:2181):ClientCnxn$SendThread@1299] - Session establishment complete on server 127.0.0.1/127.0.0.1:2181, sessionid = 0x153e03ecf1b000e, negotiated timeout = 40000
2016-04-04 16:10:57,043 - INFO  - [Thread-4-EventThread:ConnectionStateManager@228] - State change: CONNECTED
Thread-4号barrier启动...
2016-04-04 16:11:05,078 - INFO  - [Thread-5:CuratorFrameworkImpl@235] - Starting
2016-04-04 16:11:05,080 - INFO  - [Thread-5:ZooKeeper@438] - Initiating client connection, connectString=localhost:2181 sessionTimeout=60000 watcher=org.apache.curator.ConnectionState@cd7a17f
Thread-5号barrier设置
2016-04-04 16:11:05,087 - INFO  - [Thread-5-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@1032] - Opening socket connection to server 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181. Will not attempt to authenticate using SASL (unknown error)
2016-04-04 16:11:05,088 - INFO  - [Thread-5-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@876] - Socket connection established to 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181, initiating session
2016-04-04 16:11:05,127 - INFO  - [Thread-5-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@1299] - Session establishment complete on server 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181, sessionid = 0x153e03ecf1b000f, negotiated timeout = 40000
2016-04-04 16:11:05,127 - INFO  - [Thread-5-EventThread:ConnectionStateManager@228] - State change: CONNECTED
Thread-5号barrier启动...
 */