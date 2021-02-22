package com.gmail.mosoft521.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Ku137Del {
    private static volatile int countOfDelTxt = 0;

    public static void main(String[] args) {
        String path = "F:\\pic_ku137\\";  //要遍历的路径
        File file = new File(path); //获取其file对象
        func(file);
        System.out.println("countOfDelTxt: " + countOfDelTxt);
    }

    private static void func(File file) {
        File[] fs = file.listFiles();//当前目录的files
        for (File f : fs) {
            if (f.isDirectory()) {//若是目录，则递归打印该目录下的文件
                func(f);
            }
            if (f.isFile()) {        //若是文件，直接打印
                //System.out.println(f.getName());
                String fileName = f.getName();
                String[] fileNames = fileName.split("\\.");
                //顺便删除带波浪线的png
                if (fileNames[1].equalsIgnoreCase("txt")) {
                    ++countOfDelTxt;
                    System.out.println(countOfDelTxt);
                    f.delete();
                }
            }
        }
    }
}
