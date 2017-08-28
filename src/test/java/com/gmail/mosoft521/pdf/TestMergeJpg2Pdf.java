package com.gmail.mosoft521.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import org.junit.Test;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestMergeJpg2Pdf {


    @Test
    public void test() throws IOException, DocumentException {
        PdfReader reader = new PdfReader("D:/删除后重建template填充后.pdf");
//        PdfReader reader = new PdfReader("D:/未命名 1.pdf");
        int n = reader.getNumberOfPages();
        Document document = new Document(reader.getPageSize(n));
        float width = document.getPageSize().getWidth();
        float height = document.getPageSize().getHeight();
        // Create a stamper that will copy the document to a new file
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream("D:/删除后重建template填充后添加图片.pdf"));
        PdfContentByte over;
        Image img = Image.getInstance("C:/Users/zhangjiawen/Pictures/sign111.jpg");
        width = width - img.getWidth();
        height = height/2 - img.getHeight();
        System.out.println("width:" + width);
        System.out.println("height:" + height);
        img.setAbsolutePosition(width, height);
        img.setAlignment(Image.ALIGN_LEFT);
        if (n > 0) {
            // Text over the existing page
            over = stamp.getOverContent(n);
            over.addImage(img);
        }
        stamp.close();
    }

    @Test
    public void test2() throws IOException, DocumentException {

//        PdfReader reader = new PdfReader("D:/删除后重建template填充后.pdf");
        PdfReader reader = new PdfReader("D:/未命名 1.pdf");
        int n = reader.getNumberOfPages();
        Document document = new Document(reader.getPageSize(n));
        Image img = Image.getInstance("C:/Users/zhangjiawen/Pictures/sign123.png");
        img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        img.setBorder(Image.BOX);
        img.setBorderWidth(10);
        img.setBorderColor(Color.WHITE);
        img.scaleToFit(1000, 72);//大小
//        img.setRotationDegrees(-30);//旋转
        document.add(img);

    }

    @Test
    public void readPDF() throws IOException, DocumentException {

    }
}
