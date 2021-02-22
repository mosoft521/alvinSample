package com.gmail.mosoft521.file;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Copy2ParentSYZ {
    public static void main(String[] args) {
        String path = "F:\\pic_孙允珠2\\";  //要遍历的路径
        File file = new File(path); //获取其file对象

        try {
            func(file);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static String leftPad(String string) {
        return StringUtils.leftPad(string, 6, "0");
    }

    private static void func(File file) throws IOException {
        File[] fs = file.listFiles();//当前目录的files
        assert fs != null;
        for (File f : fs) {
            if (f.isDirectory()) {//若是目录，则递归打印该目录下的文件
                func(f);
            }
            if (f.isFile()) {        //若是文件，直接打印
                String fileName = f.getName();
                System.out.println("fileName: " + fileName);
                String fileParentName = f.getParentFile().getName();
                System.out.println("fileParentName: " + fileParentName);
                String fileParentAbsolutePath = f.getParentFile().getAbsolutePath();
//                System.out.println("fileParentAbsolutePath: " + fileParentAbsolutePath + "-" + leftPad(fileName));//ok
//                System.out.println("F:\\pic_孙允珠2\\" + fileParentName + "-" + leftPad(fileName));//ok
//                String[] fileNames = fileName.split("\\.");
                Files.copy(f.toPath(), new File("F:\\pic_孙允珠All\\" + fileParentName + "-" + leftPad(fileName)).toPath());
            }
        }
    }
}
/**
 * fileName: 8.jpg
 * fileParentName: 黛黑玫瑰宫廷抹胸簇花拼搭裙
 * fileParentAbsolutePath: F:\pic_孙允珠\黛黑玫瑰宫廷抹胸簇花拼搭裙-08.jpg
 * F:\pic_孙允珠\黛黑玫瑰宫廷抹胸簇花拼搭裙-08.jpg
 */
