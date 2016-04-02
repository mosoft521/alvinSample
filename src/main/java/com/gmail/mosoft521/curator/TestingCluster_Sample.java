package com.gmail.mosoft521.curator;

import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingZooKeeperServer;

/**
 * Created by Alvin on 2016/3/31 0031.
 */
public class TestingCluster_Sample {
    public static void main(String[] args) throws Exception {
        TestingCluster testingCluster = new TestingCluster(3);
        testingCluster.start();
        Thread.sleep(2000);

        TestingZooKeeperServer testingZooKeeperServer = null;
        for (TestingZooKeeperServer tmpTestingZooKeeperServer : testingCluster.getServers()) {
            System.out.print(tmpTestingZooKeeperServer.getInstanceSpec().getServerId() + "-");
            System.out.print(tmpTestingZooKeeperServer.getQuorumPeer().getServerState() + "-");
            System.out.println(tmpTestingZooKeeperServer.getInstanceSpec().getDataDirectory().getAbsolutePath());

            if (tmpTestingZooKeeperServer.getQuorumPeer().getServerState().equals("leading")) {
                testingZooKeeperServer = tmpTestingZooKeeperServer;
            }
        }
        testingZooKeeperServer.kill();
        System.out.println("--After leader kill:");
        for (TestingZooKeeperServer tmpTestingZooKeeperServer : testingCluster.getServers()) {
            System.out.print(tmpTestingZooKeeperServer.getInstanceSpec().getServerId() + "-");
            System.out.print(tmpTestingZooKeeperServer.getQuorumPeer().getServerState() + "-");
            System.out.println(tmpTestingZooKeeperServer.getInstanceSpec().getDataDirectory().getAbsolutePath());
        }
        testingCluster.stop();
    }
}
/*
1-following-C:\Users\Alvin\AppData\Local\Temp\1459397005015-0
2-following-C:\Users\Alvin\AppData\Local\Temp\1459397005017-0
3-leading-C:\Users\Alvin\AppData\Local\Temp\1459397005018-0
--After leader kill:
1-leaderelection-C:\Users\Alvin\AppData\Local\Temp\1459397005015-0
2-leaderelection-C:\Users\Alvin\AppData\Local\Temp\1459397005017-0
3-leaderelection-C:\Users\Alvin\AppData\Local\Temp\1459397005018-0
 */