package com.gmail.mosoft521.uuid;

import java.util.UUID;

public class UuidTest {
    /**
     * guid生成
     *
     * @return
     * @date 2016-12-24 15:08:34
     */
    public static String getGUID() {
        return UUID.randomUUID().toString();
    }

    public static String getGUID2() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(getGUID());
        }
    }
}
