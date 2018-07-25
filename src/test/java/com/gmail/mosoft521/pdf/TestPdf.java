package com.gmail.mosoft521.pdf;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class TestPdf {
    @Test
    public void test() throws IOException, DocumentException {
        String fileName = "D:/删除后重建template.pdf"; // pdf模板
        PdfReader reader = new PdfReader(fileName);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, bos);
        AcroFields fields = ps.getAcroFields();
        fillData(fields, data());
        ps.setFormFlattening(true);
        ps.close();
        OutputStream fos = new FileOutputStream("D:/删除后重建template填充后.pdf");
        fos.write(bos.toByteArray());
    }

    public void fillData(AcroFields fields, Map<String, String> data) throws IOException, DocumentException {
        for (String key : data.keySet()) {
            String value = data.get(key);
            fields.setField(key, value);
        }
    }

    public Map<String, String> data() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("p1f1", "2017-08-27");
        data.put("p1f2", "Alvin");
        data.put("p1f3", "同学Zhang");
        data.put("p1f4", "zhang同学");
        data.put("p1f5", "张同学");
        data.put("p2f1", "2017-08-27");
        data.put("p2f2", "合同签名");
        data.put("p3f1", "zhong英文7723");
        data.put("p3f2", "合同签名");
        return data;
    }
}
