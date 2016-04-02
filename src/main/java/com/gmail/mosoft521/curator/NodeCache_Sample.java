package com.gmail.mosoft521.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 *典型使用场景(一)事件监听：NodeCache使用示例
 */
public class NodeCache_Sample {
    static String path = "/zk-book/nodeCache";
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
TODO:
 */