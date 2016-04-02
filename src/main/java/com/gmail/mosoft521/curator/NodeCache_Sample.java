package com.gmail.mosoft521.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 典型使用场景(一)事件监听：NodeCache使用示例
 */
public class NodeCache_Sample {
    static String path = "/zk-book/nodeCache123";
    static CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString("localhost:2181").sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) throws Exception {
        curatorFramework.start();
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());
        final NodeCache nodeCache = new NodeCache(curatorFramework, path, false);
        nodeCache.start(true);

        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("Node data update, new data: " + new String(nodeCache.getCurrentData().getData()));
            }
        });
        curatorFramework.setData().forPath(path, "u".getBytes());
        Thread.sleep(1000);
        curatorFramework.delete().deletingChildrenIfNeeded().forPath(path);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
/*
D:\tools\Java\jdk1.8.0_74\bin\java -Didea.launcher.port=7533 "-Didea.launcher.bin.path=D:\Program Files (x86)\JetBrains\IntelliJ IDEA 15.0.2\bin" -Dfile.encoding=UTF-8 -classpath "D:\tools\Java\jdk1.8.0_74\jre\lib\charsets.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\deploy.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\access-bridge-64.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\cldrdata.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\dnsns.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\jaccess.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\jfxrt.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\localedata.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\nashorn.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunec.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunjce_provider.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunmscapi.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunpkcs11.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\zipfs.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\javaws.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jce.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jfr.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jfxswt.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jsse.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\management-agent.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\plugin.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\resources.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\rt.jar;E:\ws_ij_alvin\alvinSample\target\classes;E:\java\repository\org\slf4j\slf4j-api\1.7.20\slf4j-api-1.7.20.jar;E:\java\repository\org\apache\zookeeper\zookeeper\3.4.8\zookeeper-3.4.8.jar;E:\java\repository\org\slf4j\slf4j-log4j12\1.6.1\slf4j-log4j12-1.6.1.jar;E:\java\repository\log4j\log4j\1.2.16\log4j-1.2.16.jar;E:\java\repository\jline\jline\0.9.94\jline-0.9.94.jar;E:\java\repository\io\netty\netty\3.7.0.Final\netty-3.7.0.Final.jar;E:\java\repository\org\apache\curator\curator-recipes\2.10.0\curator-recipes-2.10.0.jar;E:\java\repository\org\apache\curator\curator-framework\2.10.0\curator-framework-2.10.0.jar;E:\java\repository\org\apache\curator\curator-client\2.10.0\curator-client-2.10.0.jar;E:\java\repository\org\apache\curator\curator-test\2.10.0\curator-test-2.10.0.jar;E:\java\repository\org\javassist\javassist\3.18.1-GA\javassist-3.18.1-GA.jar;E:\java\repository\org\apache\commons\commons-math\2.2\commons-math-2.2.jar;E:\java\repository\com\google\guava\guava\16.0.1\guava-16.0.1.jar;D:\Program Files (x86)\JetBrains\IntelliJ IDEA 15.0.2\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMain com.gmail.mosoft521.curator.NodeCache_Sample
2016-04-02 21:54:17,484 - WARN  - [main:CuratorZookeeperClient@95] - session timeout [5000] is less than connection timeout [15000]
2016-04-02 21:54:17,532 - INFO  - [main:CuratorFrameworkImpl@235] - Starting
2016-04-02 21:54:17,547 - INFO  - [main:Environment@100] - Client environment:zookeeper.version=3.4.8--1, built on 02/06/2016 03:18 GMT
2016-04-02 21:54:17,547 - INFO  - [main:Environment@100] - Client environment:host.name=192.168.1.3
2016-04-02 21:54:17,547 - INFO  - [main:Environment@100] - Client environment:java.version=1.8.0_74
2016-04-02 21:54:17,547 - INFO  - [main:Environment@100] - Client environment:java.vendor=Oracle Corporation
2016-04-02 21:54:17,547 - INFO  - [main:Environment@100] - Client environment:java.home=D:\tools\Java\jdk1.8.0_74\jre
2016-04-02 21:54:17,548 - INFO  - [main:Environment@100] - Client environment:java.class.path=D:\tools\Java\jdk1.8.0_74\jre\lib\charsets.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\deploy.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\access-bridge-64.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\cldrdata.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\dnsns.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\jaccess.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\jfxrt.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\localedata.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\nashorn.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunec.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunjce_provider.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunmscapi.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\sunpkcs11.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\ext\zipfs.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\javaws.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jce.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jfr.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jfxswt.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\jsse.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\management-agent.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\plugin.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\resources.jar;D:\tools\Java\jdk1.8.0_74\jre\lib\rt.jar;E:\ws_ij_alvin\alvinSample\target\classes;E:\java\repository\org\slf4j\slf4j-api\1.7.20\slf4j-api-1.7.20.jar;E:\java\repository\org\apache\zookeeper\zookeeper\3.4.8\zookeeper-3.4.8.jar;E:\java\repository\org\slf4j\slf4j-log4j12\1.6.1\slf4j-log4j12-1.6.1.jar;E:\java\repository\log4j\log4j\1.2.16\log4j-1.2.16.jar;E:\java\repository\jline\jline\0.9.94\jline-0.9.94.jar;E:\java\repository\io\netty\netty\3.7.0.Final\netty-3.7.0.Final.jar;E:\java\repository\org\apache\curator\curator-recipes\2.10.0\curator-recipes-2.10.0.jar;E:\java\repository\org\apache\curator\curator-framework\2.10.0\curator-framework-2.10.0.jar;E:\java\repository\org\apache\curator\curator-client\2.10.0\curator-client-2.10.0.jar;E:\java\repository\org\apache\curator\curator-test\2.10.0\curator-test-2.10.0.jar;E:\java\repository\org\javassist\javassist\3.18.1-GA\javassist-3.18.1-GA.jar;E:\java\repository\org\apache\commons\commons-math\2.2\commons-math-2.2.jar;E:\java\repository\com\google\guava\guava\16.0.1\guava-16.0.1.jar;D:\Program Files (x86)\JetBrains\IntelliJ IDEA 15.0.2\lib\idea_rt.jar
2016-04-02 21:54:17,548 - INFO  - [main:Environment@100] - Client environment:java.library.path=D:\tools\Java\jdk1.8.0_74\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;D:\tools\Java\jdk1.8.0_74\bin;D:\tools\Java\jdk1.8.0_74\jre\bin;D:\tools\maven\apache-maven-3.3.9\bin;C:\ProgramData\Oracle\Java\javapath;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\ATI Technologies\ATI.ACE\Core-Static;D:\Program Files\EmEditor;D:\tools\tomcat\apache-tomcat-8.0.32\lib;D:\tools\tomcat\apache-tomcat-8.0.32\bin;D:\Program Files\AMD\ATI.ACE\Core-Static;D:\tools\Java\jdk1.7.0_80\bin;D:\tools\Java\jdk1.7.0_80\jre\bin;D:\tools\maven\apache-maven-3.3.3\bin;C:\ProgramData\Oracle\Java\javapath;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\ATI Technologies\ATI.ACE\Core-Static;D:\Program Files\EmEditor;D:\tools\tomcat\apache-tomcat-8.0.23\lib;D:\tools\tomcat\apache-tomcat-8.0.23\bin;.
2016-04-02 21:54:17,548 - INFO  - [main:Environment@100] - Client environment:java.io.tmpdir=C:\Users\Alvin\AppData\Local\Temp\
2016-04-02 21:54:17,548 - INFO  - [main:Environment@100] - Client environment:java.compiler=<NA>
2016-04-02 21:54:17,548 - INFO  - [main:Environment@100] - Client environment:os.name=Windows 7
2016-04-02 21:54:17,549 - INFO  - [main:Environment@100] - Client environment:os.arch=amd64
2016-04-02 21:54:17,549 - INFO  - [main:Environment@100] - Client environment:os.version=6.1
2016-04-02 21:54:17,549 - INFO  - [main:Environment@100] - Client environment:user.name=Alvin
2016-04-02 21:54:17,550 - INFO  - [main:Environment@100] - Client environment:user.home=C:\Users\Alvin
2016-04-02 21:54:17,552 - INFO  - [main:Environment@100] - Client environment:user.dir=E:\ws_ij_alvin\alvinSample
2016-04-02 21:54:17,553 - INFO  - [main:ZooKeeper@438] - Initiating client connection, connectString=localhost:2181 sessionTimeout=5000 watcher=org.apache.curator.ConnectionState@7907ec20
2016-04-02 21:54:17,654 - INFO  - [main-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@1032] - Opening socket connection to server 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181. Will not attempt to authenticate using SASL (unknown error)
2016-04-02 21:54:17,659 - INFO  - [main-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@876] - Socket connection established to 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181, initiating session
2016-04-02 21:54:17,729 - INFO  - [main-SendThread(0:0:0:0:0:0:0:1:2181):ClientCnxn$SendThread@1299] - Session establishment complete on server 0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1:2181, sessionid = 0x153d717bfff000a, negotiated timeout = 5000
2016-04-02 21:54:17,736 - INFO  - [main-EventThread:ConnectionStateManager@228] - State change: CONNECTED
Node data update, new data: u
2016-04-02 21:54:19,135 - ERROR - [main-EventThread:NodeCache$4@315] - Calling listener
java.lang.NullPointerException
	at com.gmail.mosoft521.curator.NodeCache_Sample$1.nodeChanged(NodeCache_Sample.java:26)
	at org.apache.curator.framework.recipes.cache.NodeCache$4.apply(NodeCache.java:310)
	at org.apache.curator.framework.recipes.cache.NodeCache$4.apply(NodeCache.java:304)
	at org.apache.curator.framework.listen.ListenerContainer$1.run(ListenerContainer.java:93)
	at com.google.common.util.concurrent.MoreExecutors$SameThreadExecutorService.execute(MoreExecutors.java:297)
	at org.apache.curator.framework.listen.ListenerContainer.forEach(ListenerContainer.java:85)
	at org.apache.curator.framework.recipes.cache.NodeCache.setNewData(NodeCache.java:302)
	at org.apache.curator.framework.recipes.cache.NodeCache.processBackgroundResult(NodeCache.java:278)
	at org.apache.curator.framework.recipes.cache.NodeCache.access$300(NodeCache.java:56)
	at org.apache.curator.framework.recipes.cache.NodeCache$3.processResult(NodeCache.java:122)
	at org.apache.curator.framework.imps.CuratorFrameworkImpl.sendToBackgroundCallback(CuratorFrameworkImpl.java:749)
	at org.apache.curator.framework.imps.CuratorFrameworkImpl.processBackgroundOperation(CuratorFrameworkImpl.java:522)
	at org.apache.curator.framework.imps.ExistsBuilderImpl$1.processResult(ExistsBuilderImpl.java:135)
	at org.apache.zookeeper.ClientCnxn$EventThread.processEvent(ClientCnxn.java:563)
	at org.apache.zookeeper.ClientCnxn$EventThread.run(ClientCnxn.java:505)
 */