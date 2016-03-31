package com.gmail.mosoft521.tc;

import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingZooKeeperServer;

/**
 * Created by Alvin on 2016/3/31 0031.
 */
public class TestingCluster_Sample {
    public static void main(String[] args) throws Exception {
        TestingCluster cluster = new TestingCluster(3);
        cluster.start();
        Thread.sleep(2000);

        TestingZooKeeperServer leader = null;
        for (TestingZooKeeperServer zs : cluster.getServers()) {
            System.out.print(zs.getInstanceSpec().getServerId() + "-");
            System.out.print(zs.getQuorumPeer().getServerState() + "-");
            System.out.println(zs.getInstanceSpec().getDataDirectory().getAbsolutePath());

            if (zs.getQuorumPeer().getServerState().equals("leading")) {
                leader = zs;
            }
        }
        leader.kill();
        System.out.println("--After leader kill:");
        for (TestingZooKeeperServer zs : cluster.getServers()) {
            System.out.print(zs.getInstanceSpec().getServerId() + "-");
            System.out.print(zs.getQuorumPeer().getServerState() + "-");
            System.out.println(zs.getInstanceSpec().getDataDirectory().getAbsolutePath());
        }
        cluster.stop();
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