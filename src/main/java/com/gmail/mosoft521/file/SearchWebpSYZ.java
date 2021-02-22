package com.gmail.mosoft521.file;

import java.io.File;
import java.util.Objects;

public class SearchWebpSYZ {
    private static volatile int countWebp = 0;
    private static volatile int countEmptyDir = 0;
    private static volatile int countDirWavyLine = 0;

    public static void main(String[] args) {
        String path = "F:\\pic_孙允珠\\";  //要遍历的路径
        File file = new File(path); //获取其file对象

        func(file);
        System.out.println("webp's count: " + countWebp);
        System.out.println("empty dir's count: " + countEmptyDir);
        System.out.println("wavy line dir's count: " + countDirWavyLine);
    }

    private static void func(File file) {
        boolean waveLine = false;
        File[] fs = file.listFiles();//当前目录的files
        if (Objects.requireNonNull(file.list()).length > 0) {
//            System.out.println("目录不为空!");
        } else {
//            System.out.println("目录为空!");
            ++countEmptyDir;
            System.out.println(file);
        }
        assert fs != null;
        for (File f : fs) {
            if (f.isDirectory()) {//若是目录，则递归打印该目录下的文件
                func(f);
            }
            if (f.isFile()) {        //若是文件，直接打印
                String fileName = f.getName();
                String[] fileNames = fileName.split("\\.");
                if ("webp".equalsIgnoreCase(fileNames[1])) {
                    ++countWebp;
//                    System.out.println(f);
                }
                if (!waveLine && fileNames[0].contains("~")) {
                    waveLine = true;
                    ++countDirWavyLine;
                    System.out.println("wave line's dir: " + f);
                }
            }
        }
    }
}
