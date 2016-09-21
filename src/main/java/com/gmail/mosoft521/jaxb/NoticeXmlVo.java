package com.gmail.mosoft521.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
@XmlType(name = "root", propOrder = {"range"})
public class NoticeXmlVo {

    private List<NoticeRangeVo> range = new ArrayList<NoticeRangeVo>();

    public NoticeXmlVo() {
    }

    public List<NoticeRangeVo> getRange() {
        return range;
    }

    public void setRange(List<NoticeRangeVo> range) {
        this.range = range;
    }
}
