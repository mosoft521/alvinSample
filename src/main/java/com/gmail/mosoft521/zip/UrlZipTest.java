package com.gmail.mosoft521.zip;

import cn.hutool.core.io.FileUtil;
import com.google.common.collect.Sets;
import lombok.Cleanup;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class UrlZipTest {
    private static int BYTE_LENGTH = 1024;

    //1. 把url的转成字节数据
    public static byte[] getFileUrlByte(String fileUrl) throws Exception {
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setConnectTimeout(6000);
            urlCon.setReadTimeout(6000);
            int code = urlCon.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                throw new Exception("文件读取失败");
            }
            InputStream is = url.openStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] bytes = new byte[BYTE_LENGTH];
            int length = 0;
            while ((length = is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
            }
            outputStream.close();
            is.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
//            log.error("文件下载异常: {}", ExceptionUtil.stacktraceToString(e));
            throw new Exception(String.format("文件%s下载失败, %s", fileUrl, e.getMessage()));
        }
    }

    //1-2 byte[]转临时文件
    public static File createTmpFile(byte[] bytes, String fileName) throws IOException {
        String[] fileNames = fileName.split("\\.");
        File tempFile = File.createTempFile(fileNames[0], "." + fileNames[1]);
        FileUtil.writeBytes(bytes, tempFile);
        return tempFile;
    }

    //2.把多个文件流合成zip输出流
    public static byte[] mergeZipByte(byte[] b1, String fileName1, byte[] b2, String fileName2) throws Exception {
        byte[] arr = new byte[0];
        try {
            @Cleanup //使用该注解能够自动释放io资源
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            @Cleanup //使用该注解能够自动释放io资源
            ZipOutputStream zipOut = new ZipOutputStream(outputStream);
            // 如果有重复的名称, 则重命名

            try (
                    InputStream in = new BufferedInputStream(
                            new ByteArrayInputStream(b1))
            ) {
                zipOut.putNextEntry(new ZipEntry(fileName1));
                int c;
                while ((c = in.read()) != -1)
                    zipOut.write(c);
            }
            zipOut.flush();
            try (
                    InputStream in = new BufferedInputStream(
                            new ByteArrayInputStream(b2))
            ) {
                zipOut.putNextEntry(new ZipEntry(fileName2));
                int c;
                while ((c = in.read()) != -1)
                    zipOut.write(c);
            }
            zipOut.flush();
            zipOut.finish();
            outputStream.flush();
            arr = outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("下载异常");
        }
        return arr;
    }


    public static void main(String[] args) throws Exception {
        byte[] b1 = getFileUrlByte("http://yonbip-biz-dev.yonyouauto.com/iuap-apcom-file/rest/v1/server/file/6267d8f26da925003ece065c/ocstream?downThumb=false&token=KkuwudqZwFyRFH-f5o-UVzRIoJ1vp7rEtVJNXYFRdr6uqurwAX_TF26AQNPPgzCn8uBGNgHXFjwCDZMfH7PE8kfApuJhmlKVMj0G7YALrCO40LJBjKo07q5D1xCN9AwpAR_r3CHpLhzqqFfPGBXrJ_Eevdw9qZglMdg6RGyp96rQYmEjUUElLgDt94yIKb7GxgoMNk6PrA4Osq1bQ-sAqc-Tv3dlCd4XDBVv9YqN8NWxSFr722uJpsbi62Z1cN9mX63IjueQ8ZAaenTjpfk-0gmVFWFuC3wcBAYxJaIQ2vnHHumPrAMtWlWwLY4pcfsEjwDZdyuajlRnfA1b6OaJOQ&isWaterMark=false");
        byte[] b2 = getFileUrlByte("http://yonbip-biz-dev.yonyouauto.com/iuap-apcom-file/rest/v1/server/file/6267d92e6da925003ece065d/ocstream?downThumb=false&token=NwBhD5jQ08m2a9L7XKNge3gfmLhvWBv0aGfbPQwMj28cqV-vP4WFwKCROFLiL90uEcDrcK3Noct6N4HnCVnJMi7onLHVixjFg04iYjAJ1SvWypFhcXVmmyCspnSIfuHOfVeHUbnqRET2O4Yvinq0fTdxSanzUIXJVaJPBZZO-abJxQ2ZZOGH4ApyUoU4JJAdiD_0YG9K3_UpIg8eo_j8vbvat8QtA_eR7MGgaM1N7puNqdsiX1Prv9FdX0cGQAQ2C3Hy8b02hHUh2LDYGNxLv-ZF0F9l_kwCBdCbeSsmSGZl324zNydNXKxIjFz-WBDssvKCVpBksu3sMifb3btYjQ&isWaterMark=false");
        byte[] b3 = mergeZipByte(b1, "MySQL军规升级版.docx", b2, "学生名册.xlsx"); //可以从前面查询拿到
        File tmp = createTmpFile(b3, "配件采购单-流水号-OA审核.zip");//加上流水号 这里有可能不用转
        System.out.println(tmp.getName());
        System.out.println(tmp.getAbsolutePath());
    }
}
