package com.gmail.mosoft521.caffine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.LoadingCache;
import edu.umd.cs.findbugs.annotations.NonNull;
import org.checkerframework.checker.index.qual.NonNegative;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * https://zhuanlan.zhihu.com/p/142667371
 */
public class CaffineTest001 {

    private static Logger logger = LoggerFactory.getLogger(CaffineTest001.class);

    @Test
    public void test000() {
        Cache<String, Object> cache = Caffeine.newBuilder()
                .initialCapacity(100)//初始大小
                .maximumSize(200)//最大数量
                .expireAfterWrite(3, TimeUnit.SECONDS)//过期时间
                .build();
    }

    @Test
    public void test001() {
        Cache<String, String> cache = Caffeine.newBuilder().build();
        cache.put("java金融", "java金融");
        logger.info(cache.getIfPresent("java金融"));//java金融
    }

    @Test
    public void test002() {
        Cache<String, String> cache = Caffeine.newBuilder()
                .build();
        // 1.如果缓存中能查到，则直接返回
        // 2.如果查不到，则从我们自定义的getValue方法获取数据，并加入到缓存中
        String val = cache.get("java金融", k -> getValue(k));
        logger.info(val);//java金融:value
    }

    /**
     * 缓存中找不到，则会进入这个方法。一般是从数据库获取内容
     *
     * @param k
     * @return
     */
    private static String getValue(String k) {
        return k + ":value";
    }

    /**
     * 基于大小（size-based）
     */
    @Test
    public void test003() {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                // 最大容量为1
                .maximumSize(1)
                .build(k -> getValue(k));
        cache.put("java金融1", "java金融1");
        cache.put("java金融2", "java金融2");
        cache.put("java金融3", "java金融3");
        cache.cleanUp();
        logger.info(cache.getIfPresent("java金融1"));//null
        logger.info(cache.getIfPresent("java金融2"));//null
        logger.info(cache.getIfPresent("java金融3"));//java金融3
    }

    /**
     * 基于时间（time-based）
     * expireAfterWrite 在最后一次写入缓存后开始计时，在指定的时间后过期。
     */
    @Test
    public void test004() throws InterruptedException {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                // 最大容量为1
                .maximumSize(1)
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .build(k -> getValue(k));
        cache.put("java金融", "java金融");
        Thread.sleep(1 * 1000);
        logger.info(cache.getIfPresent("java金融"));//java金融
        Thread.sleep(1 * 1000);
        logger.info(cache.getIfPresent("java金融"));//java金融
        Thread.sleep(1 * 1000);
        logger.info(cache.getIfPresent("java金融"));//null
    }

    /**
     * expireAfterAccess 在最后一次读或者写入后开始计时，在指定的时间后过期。假如一直有请求访问该key，那么这个缓存将一直不会过期。
     */
    @Test
    public void test005() throws InterruptedException {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                // 最大容量为1
                .maximumSize(1)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build(k -> getValue(k));
        cache.put("java金融", "java金融");
        Thread.sleep(1 * 1000);
        logger.info(cache.getIfPresent("java金融"));//java金融
        Thread.sleep(1 * 1000);
        logger.info(cache.getIfPresent("java金融"));//java金融
        Thread.sleep(1 * 1000);
        logger.info(cache.getIfPresent("java金融"));//java金融
        Thread.sleep(3001);
        logger.info(cache.getIfPresent("java金融"));//null
    }

    /**
     * 在expireAfter中需要自己实现Expiry接口，这个接口支持expireAfterCreate,expireAfterUpdate,以及expireAfterRead了之后多久过期。注意这个是和expireAfterAccess、expireAfterAccess是互斥的。这里和expireAfterAccess、expireAfterAccess不同的是，需要你告诉缓存框架，他应该在具体的某个时间过期，获取具体的过期时间。
     */
    @Test
    public void test006() {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                // 最大容量为1
                .maximumSize(1)
                .removalListener((key, value, cause) ->
                        logger.info("key:" + key + ",value:" + value + ",删除原因:" + cause))
                .expireAfter(new Expiry<String, String>() {
                    @Override
                    public long expireAfterCreate(@NonNull String key, @NonNull String value, long currentTime) {
                        return currentTime;
                    }

                    @Override
                    public long expireAfterUpdate(@NonNull String key, @NonNull String value, long currentTime, @NonNegative long currentDuration) {
                        return currentTime;
                    }

                    @Override
                    public long expireAfterRead(@NonNull String key, @NonNull String value, long currentTime, @NonNegative long currentDuration) {
                        return currentTime;
                    }
                })
                .build(k -> getValue(k));
    }
}
