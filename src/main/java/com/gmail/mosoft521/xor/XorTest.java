package com.gmail.mosoft521.xor;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class XorTest {
    private static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // nothing
            }
        }
    }

    /**
     * 对文件异或算法加密/解密
     *
     * @param inFile  输入文件（密文/明文）
     * @param outFile 结果输出文件
     * @param key     密钥
     */
    public static void encryptFile(File inFile, File outFile, byte[] key) throws Exception {
        InputStream in = null;
        OutputStream out = null;

        try {
            // 文件输入流
            in = new FileInputStream(inFile);
            // 结果输出流, 异或运算时, 字节是一个一个读取和写入, 这里必须使用缓冲流包装,
            // 等缓冲到一定数量的字节（10240字节）后再写入磁盘（否则写磁盘次数太多, 速度会非常慢）
            out = new BufferedOutputStream(new FileOutputStream(outFile), 10240);

            int b = -1;
            long i = 0;

            // 每次循环读取文件的一个字节, 使用密钥字节数组循环加密或解密
            while ((b = in.read()) != -1) {
                // 数据与密钥异或, 再与循环变量的低8位异或（增加复杂度）
                b = b ^ key[(int) (i % key.length)];
                // 写入一个加密/解密后的字节
                out.write(b);
                // 循环变量递增
                i++;
            }
            out.flush();

        } finally {
            close(in);
            close(out);
        }
    }

    public static void main(String[] args) throws Exception {
        String key = "z";                  // XOR 加密/解密用的原始密码
        String pathName = "E:\\English\\dict\\12000\\4\\";
        String inFileName = ".mp3";
        String outFileName = "x.mp3";
        int start = 20;
        int end = 26;
        for (int i = start; i <= end; i++) {
            String inAllFileName = pathName + i + inFileName;
            String outAllFileName = pathName + i + outFileName;
            System.out.println(inAllFileName);
            System.out.println(outAllFileName);

            encryptFile(new File(inAllFileName), new File(outAllFileName), key.getBytes());
        }


    }
}
