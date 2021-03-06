package com.gmail.mosoft521.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Repeat2DelSYZ2 {
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

    private static volatile int countOfDelWebp = 0;
    private static volatile int countOfDelWavyLine = 0;
    private static volatile int countOfDelPngLessThan8K = 0;

    public static void main(String[] args) {
        String path = "F:\\pic_孙允珠2\\";  //要遍历的路径
        File file = new File(path); //获取其file对象
        func(file);
        System.out.println("countOfDelWebp: " + countOfDelWebp);
        System.out.println("countOfDelWavyLine: " + countOfDelWavyLine);
        System.out.println("countOfDelPngLessThan8K: " + countOfDelPngLessThan8K);
    }

    private static void func(File file) {
        File[] fs = file.listFiles();//当前目录的files
        Map<String, File> webpMap = new HashMap<>();
        Map<String, File> pngMap = new HashMap<>();
        for (File f : fs) {
            if (f.isDirectory()) {//若是目录，则递归打印该目录下的文件
                func(f);
            }
            if (f.isFile()) {        //若是文件，直接打印
                //System.out.println(f.getName());
                String fileName = f.getName();
                String[] fileNames = fileName.split("\\.");
                if ("webp".equalsIgnoreCase(fileNames[1])) {
                    webpMap.put(fileNames[0], f);
                } else if ("png".equalsIgnoreCase(fileNames[1]) || "jpg".equalsIgnoreCase(fileNames[1])) {
                    //顺便删除带波浪线的png
                    if (fileNames[0].contains("~")) {
                        ++countOfDelWavyLine;
                        f.delete();
                    } else {
                        pngMap.put(fileNames[0], f);
                    }
                }
            }
        }
        //分类完毕，把在pngList存在的webp文件删除
        for (Map.Entry<String, File> entry : pngMap.entrySet()) {
            String key = entry.getKey();
            File fileWebp = webpMap.get(key);
            if (null != fileWebp) {
                if (fileWebp.exists()) {
                    ++countOfDelWebp;
                    fileWebp.delete();
                }
            }
            //顺便判断png是否小于8K，若小于则删除之
            File pngFile = entry.getValue();
            if (pngFile.length() < 8192) {
                ++countOfDelPngLessThan8K;
                pngFile.delete();
            }
        }
    }
}
