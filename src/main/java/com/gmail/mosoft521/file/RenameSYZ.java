package com.gmail.mosoft521.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class RenameSYZ {
    private static String FILE_DIR = "F:\\pic_孙允珠All";

    public static void main(String[] args) {
        File file = new File(FILE_DIR); //获取其file对象
        try {
            func(file);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
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
                System.out.println(fileParentAbsolutePath);

                if(fileName.contains("孙允珠")) {
                    String[] fileNames = fileName.split("孙允珠");
                    System.out.println(fileNames);
                }

            }
        }
    }

    private static void renameFile(final String fileNameOld, final String fileNameNew) {
        //值得注意的是，更名文件的前面的父路径必须相同
        new File("f:/a/a.xlsx").renameTo(new File("f:/a/b.xlsx"));
    }

    private static void renameFolder(final String folderNameOld, final String fileNameNew) {
        //想命名的原文件的路径
        File file = new File("f:/a/a.xlsx");
        //将原文件更改为f:\a\b.xlsx，其中路径是必要的。注意
        file.renameTo(new File("f:/a/b.xlsx"));
        //想命名的原文件夹的路径
        File file1 = new File("f:/A");
        //将原文件夹更改为A，其中路径是必要的。注意
        file1.renameTo(new File("f:/B"));
    }
}
