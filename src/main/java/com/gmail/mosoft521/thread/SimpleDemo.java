package com.gmail.mosoft521.thread;

public class SimpleDemo {
    static class DemoClass {
        private ThreadLocal<Integer> i = new ThreadLocal<Integer>() {
            Integer integer;

            protected Integer initialvalue() {
                return 0;
            }

        };

        public Integer get() {
            return i.get();
        }

        public void set(Integer integer) {
            i.set(i.get() + integer);
        }
    }

    public static void main(String[] args) throws Exception {
        DemoClass demoClass = new DemoClass();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 100; j++) demoClass.set(j);
                System.out.println("demoClass.get() =" + demoClass.get());
            }).start();
        }
        Thread.sleep(Integer.MAX_VALUE);
    }
}
