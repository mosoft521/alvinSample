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
public class TestJAXB2 {
    @Test
    public void testXml2Obj() throws Exception {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("notice.xml");
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        String xmlStr = new String(bytes);
        JAXBContext context = JAXBContext.newInstance(NoticeXmlVo.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        NoticeXmlVo noticeXmlVo = (NoticeXmlVo) unmarshaller.unmarshal(new StringReader(xmlStr));
        System.out.println(noticeXmlVo);
    }

    @Test
    public void testObj2Xml() {
        NoticeXmlVo noticeXmlVo = new NoticeXmlVo();

        NoticeRangeVo specialMemeber = new NoticeRangeVo();
        specialMemeber.setType("specialMemeber");
        NoticeGroupVo noticeGroupVo = new NoticeGroupVo();
        noticeGroupVo.setIsNotAllCustomers("Y");
        noticeGroupVo.setSpecialMemeber("1111,2222");
        specialMemeber.setGroup(noticeGroupVo);
        noticeXmlVo.getRange().add(specialMemeber);

        NoticeRangeVo member = new NoticeRangeVo();
        member.setType("member");
        noticeGroupVo = new NoticeGroupVo();
        noticeGroupVo.setIsNotAllMember("Y");
        noticeGroupVo.setMember("3333,4444");
        member.setGroup(noticeGroupVo);
        member.setRelation("Y");
        noticeXmlVo.getRange().add(member);

        NoticeRangeVo trader = new NoticeRangeVo();
        trader.setType("trader");
        noticeGroupVo = new NoticeGroupVo();
        noticeGroupVo.setIsNotAllCustomers("Y");
        noticeGroupVo.setMember("5555,6666");
        trader.setGroup(noticeGroupVo);
        trader.setAppoint("'7777','8888'");
        noticeXmlVo.getRange().add(trader);

        String xmlStr = TestJAXB2.convertToXml(noticeXmlVo, "utf-8");
//        String xmlStr = TestJAXB.convertToXml(emp, "GBK");
        System.out.println(xmlStr);
    }

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
