package com.gmail.mosoft521.gc;

import com.google.common.base.FinalizableReferenceQueue;
import com.google.common.base.FinalizableWeakReference;

import java.io.File;
import java.lang.ref.Reference;
import java.net.URL;
import java.net.URLClassLoader;

public class SimpleMonitorClassLoaderByGuava {
    public static void main(String args[]) throws Exception {
        try (final FinalizableReferenceQueue rq = new FinalizableReferenceQueue();) {
            ClassLoader cl = newLoader();
            Class cls = cl.loadClass("com.gmail.mosoft521.gc.Foo");
            Object obj = cls.newInstance();

            Reference<ClassLoader> weakReference = new FinalizableWeakReference<ClassLoader>(cl, rq) {
                //在引用对象被GC回收以后执行一些订制的业务逻辑
                @Override
                public void finalizeReferent() {
                    System.out.println("GC回收了:ClassLoader URLClassLoader");
                }
            };

            Reference<Class> weakReference1 = new FinalizableWeakReference<Class>(cls, rq) {
                //在引用对象被GC回收以后执行一些订制的业务逻辑
                @Override
                public void finalizeReferent() {
                    System.out.println("GC回收了:Class classloader.test.Foo");
                }
            };

            Reference<Object> weakReference2 = new FinalizableWeakReference<Object>(obj, rq) {
                //在引用对象被GC回收以后执行一些订制的业务逻辑
                @Override
                public void finalizeReferent() {
                    System.out.println("GC回收了:Instance of Foo");
                }
            };

            obj = null;
            System.out.println("Set instance null and execute gc!");
            System.gc();
            Thread.sleep(3000);
            cls = null;
            System.out.println("Set class null and execute gc!");
            System.gc();
            Thread.sleep(3000);
            cl = null;
            System.out.println("Set classloader null and execute gc!");
            System.gc();
            Thread.sleep(3000);
        }
    }

    static URLClassLoader newLoader() throws Exception {
        URL url = new File("E:\\ws_ij_alvin\\alvinSample\\target\\classes").toURI().toURL();
        URLClassLoader ucl = new URLClassLoader(new URL[]{url}, null);
        return ucl;
    }
}
/*
Set instance null and execute gc!
GC回收了:Instance of Foo
Set class null and execute gc!
Set classloader null and execute gc!
GC回收了:ClassLoader URLClassLoader
GC回收了:Class classloader.test.Foo
 */