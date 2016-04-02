package com.gmail.mosoft521.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import java.io.File;

/**
 * Created by Alvin on 2016/3/31 0031.
 */
public class TestingServer_Sample {
    static String path = "/zookeeper";

    public static void main(String[] args) throws Exception {
        TestingServer testingServer = new TestingServer(2181, new File("d://temp/zk-book-data"));

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(testingServer.getConnectString()).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

        curatorFramework.start();

        System.out.println(curatorFramework.getChildren().forPath(path));

        testingServer.close();
    }
}
/*
[quota]
 */