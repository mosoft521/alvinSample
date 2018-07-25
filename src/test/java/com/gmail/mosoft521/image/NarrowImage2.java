package com.gmail.mosoft521.image;


import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

public class NarrowImage2 {
    public static void main(String[] args) {

        // 这儿填写你存放要缩小图片的文件夹全地址
        String inputFoler = "C:\\Users\\zhangjiawen\\Pictures\\sign111.jpg";
        // 这儿填写你转化后的图片存放的文件夹
        String outputFolder = "C:\\Users\\zhangjiawen\\Pictures\\sign222.jpg";

        NarrowImage2 narrowImage2 = new NarrowImage2();
        narrowImage2.writeHighQuality(narrowImage2.zoomImage(inputFoler, 300, 100), outputFolder);
    }

    /**
     * @param src 原始图像地址
     * @param toW 变换后的宽度
     * @param toH 变换后的高度
     * @return 返回处理后的图像
     */
    public BufferedImage zoomImage(String src, int toW, int toH) {

        BufferedImage result = null;

        try {
            File srcfile = new File(src);
            if (!srcfile.exists()) {
                System.out.println("文件不存在");
                return null;
            }
            BufferedImage im = ImageIO.read(srcfile);

            /* 原始图像的宽度和高度 */
            int width = im.getWidth();
            int height = im.getHeight();



            /* 调整后的图片的宽度和高度 */
            float resizeTimesWidth = (float) toW / width;
            float resizeTimesHeight = (float) toH / height;

            //压缩计算
            float resizeTimes = resizeTimesWidth > resizeTimesHeight ? resizeTimesHeight : resizeTimesWidth;  /*这个参数是要转化成的倍数,如果是1就是转化成1倍*/

            /* 调整后的图片的宽度和高度 */
            int toWidth = (int) (width * resizeTimes);
            int toHeight = (int) (height * resizeTimes);


            /* 新生成结果图片 */
            result = new BufferedImage(toWidth, toHeight,
                    BufferedImage.TYPE_INT_RGB);

            result.getGraphics().drawImage(
                    im.getScaledInstance(toWidth, toHeight,
                            java.awt.Image.SCALE_SMOOTH), 0, 0, null);


        } catch (Exception e) {
            System.out.println("创建缩略图发生异常" + e.getMessage());
        }

        return result;

    }

    public boolean writeHighQuality(BufferedImage im, String fileFullPath) {
        try {
            /*输出到文件流*/
            FileOutputStream newimage = new FileOutputStream(fileFullPath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(im);
            /* 压缩质量 */
            jep.setQuality(0.9f, true);
            encoder.encode(im, jep);
            /*近JPEG编码*/
            newimage.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
