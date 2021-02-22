package com.gmail.mosoft521.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Repeat2DelSYZ {
//    public static void main(String[] args) {
//        String path = "F:\\pic_孙允珠2\\";
//        //遍历文件夹
//        File file = new File(path); //获取其file对象
//        File[] fs = file.listFiles(); //遍历path下的文件和目录，放在File数组中
//        for (File f : fs) { //遍历File[]数组
//            if (f.isDirectory()) //若非目录(即文件)，则打印
//                System.out.println(f);
//        }
//    }

    private static volatile int countOfDelPng = 0;
    private static volatile int countOfDelWavyLine = 0;
    private static volatile int countOfDelJpgLessThan8K = 0;

    public static void main(String[] args) {
        String path = "F:\\pic_孙允珠\\";  //要遍历的路径
        File file = new File(path); //获取其file对象
        func(file);
        System.out.println("countOfDelPng: " + countOfDelPng);
        System.out.println("countOfDelWavyLine: " + countOfDelWavyLine);
        System.out.println("countOfDelPngLessThan8K: " + countOfDelJpgLessThan8K);
    }

    private static void func(File file) {
        File[] fs = file.listFiles();//当前目录的files
        Map<String, File> pngMap = new HashMap<>();
        Map<String, File> jpgMap = new HashMap<>();
        for (File f : fs) {
            if (f.isDirectory()) {//若是目录，则递归打印该目录下的文件
                func(f);
            }
            if (f.isFile()) {        //若是文件，直接打印
                //System.out.println(f.getName());
                String fileName = f.getName();
                String[] fileNames = fileName.split("\\.");
                if ("png".equalsIgnoreCase(fileNames[1])) {
                    pngMap.put(fileNames[0], f);
                } else if ("jpg".equalsIgnoreCase(fileNames[1])) {
                    //顺便删除带波浪线的png
                    if (fileNames[0].contains("~")) {
                        ++countOfDelWavyLine;
                        System.out.println(f.getName());
                        f.delete();
                    } else {
                        jpgMap.put(fileNames[0], f);
                    }
                }
            }
        }
        //分类完毕，把在pngList存在的webp文件删除
        for (Map.Entry<String, File> entry : jpgMap.entrySet()) {
            String key = entry.getKey();
            File pngWebp = pngMap.get(key);
            if (null != pngWebp) {
                if (pngWebp.exists()) {
                    ++countOfDelPng;
                    System.out.println(pngWebp.getName());
                    pngWebp.delete();
                }
            }
            //顺便判断jpg是否小于8K，若小于则删除之
            File jpgFile = entry.getValue();
            if (jpgFile.length() < 8192) {
                ++countOfDelJpgLessThan8K;
                System.out.println(jpgFile.getName());
                jpgFile.delete();
            }
        }
    }
}
