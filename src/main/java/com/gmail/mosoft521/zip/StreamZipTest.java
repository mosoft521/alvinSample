package com.gmail.mosoft521.zip;

import cn.hutool.core.io.FileUtil;
import lombok.Cleanup;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class StreamZipTest {
    //1. 把url的转成字节数据
    public static InputStream getFileUrlStream(String fileUrl) throws Exception {
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setConnectTimeout(6000);
            urlCon.setReadTimeout(6000);
            int code = urlCon.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                throw new Exception("文件读取失败");
            }
            return url.openStream();
        } catch (IOException e) {
//            log.error("文件下载异常: {}", ExceptionUtil.stacktraceToString(e));
            throw new Exception(String.format("文件%s下载失败, %s", fileUrl, e.getMessage()));
        }
    }

    //1-2 byte[]转临时文件
    public static File createTmpFile(byte[] bytes, String fileName) throws IOException {
        //todo:必需保证文件名里有.
        String[] fileNames = fileName.split("\\.");
        File tempFile = File.createTempFile(fileNames[0], "." + fileNames[1]);
        FileUtil.writeBytes(bytes, tempFile);
        //todo：输出一下文件名便于调试观察
        System.out.println(tempFile.getName());
        System.out.println(tempFile.getAbsolutePath());
        return tempFile;
    }


    //2.把多个文件流合成zip输出流
    public static ByteArrayOutputStream mergeZipStream(InputStream is1, String fileName1, InputStream is2, String fileName2) throws Exception {
        try {
            @Cleanup //使用该注解能够自动释放io资源
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            @Cleanup //使用该注解能够自动释放io资源
            ZipOutputStream zipOut = new ZipOutputStream(outputStream);
            // 如果有重复的名称, 则重命名

            try (
                    InputStream in1 = new BufferedInputStream(is1)
            ) {
                zipOut.putNextEntry(new ZipEntry(fileName1));
                int c;
                while ((c = in1.read()) != -1)
                    zipOut.write(c);
            }
            zipOut.flush();
            try (
                    InputStream in2 = new BufferedInputStream(is2)
            ) {
                zipOut.putNextEntry(new ZipEntry(fileName2));
                int c;
                while ((c = in2.read()) != -1)
                    zipOut.write(c);
            }
            zipOut.flush();
            zipOut.finish();
            outputStream.flush();
            return outputStream;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("下载异常");
        }

    }

    public static void main(String[] args) throws Exception {
        InputStream is1 = getFileUrlStream("https://www.baidu.com/img/flexible/logo/pc/result.png");
        InputStream is2 = getFileUrlStream("https://www.sina.com.cn/favicon.svg");
        ByteArrayOutputStream os = mergeZipStream(is1, "result.png", is2, "favicon.svg"); //可以从前面查询拿到

        byte[] b3 = os.toByteArray();
        //这里有可能不用转 因为有：CooperationFileInfo uploadFile(String businessLine, String businessId, byte[] bytes, String fileName);
        //这里businessId可以用b1和b2的businessId用-拼接起来，呵呵。fileName按下面规则生产吧
        //这里写出临时文件是为了便于观察
        File tmp = createTmpFile(b3, "配件采购单-流水号-OA审核.zip");//加上流水号
        System.out.println("end");
    }
}
