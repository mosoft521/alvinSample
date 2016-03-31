package com.gmail.mosoft521.ts;

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
        TestingServer server = new TestingServer(2181, new File("d://temp/zk-book-data"));

        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(server.getConnectString()).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

        client.start();

        System.out.println(client.getChildren().forPath(path));

        server.close();
    }
}
/*
[quota]
 */