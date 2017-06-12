package com.gmail.mosoft521.jaxb;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by Alvin on 2016/4/30 0030.
 */
public class TestJAXB {
    public static String convertToXml(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

//            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);//作为XML片段

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        return result;
        return result.replace("standalone=\"yes\"", ""); //删除standalone="yes"
    }

    @Test
    public void testXml2Obj() throws Exception {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("employee.xml");
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        String xmlStr = new String(bytes);
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Employee emp = (Employee) unmarshaller.unmarshal(new StringReader(xmlStr));
        System.out.println(emp);
    }

    @Test
    public void testObj2Xml() {
        Employee emp = new Employee();
        emp.setAge(10);
        emp.setGender("Male");
        emp.setName("Jane");
//        emp.setRole("Teacher");
        emp.setRole("老师");
        String xmlStr = TestJAXB.convertToXml(emp, "utf-8");
//        String xmlStr = TestJAXB.convertToXml(emp, "GBK");
        System.out.println(xmlStr);
    }
}
/*
>>testXml2Obj
Employee:: Name=Pankaj Age=29 Gender=Male Role=Java Developer
>>testObj2Xml
<?xml version="1.0" encoding="GBK" standalone="yes"?>
<employee>
    <name>Jane</name>
    <age>10</age>
    <role>老师</role>
    <gender>Male</gender>
</employee>
>>
<?xml version="1.0" encoding="GBK" ?>
<employee>
    <name>Jane</name>
    <age>10</age>
    <role>老师</role>
    <gender>Male</gender>
</employee>
 */
