package com.gmail.mosoft521.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "range")
@XmlType(name = "range", propOrder = {"group", "relation", "appoint"})
public class NoticeRangeVo {

    @XmlAttribute(name = "type")
    private String type;


    private NoticeGroupVo group;
    private String relation;
    private String appoint;

    public NoticeRangeVo() {
    }

    //    @XmlAccessorType(XmlAccessType.PROPERTY)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NoticeGroupVo getGroup() {
        return group;
    }

    public void setGroup(NoticeGroupVo group) {
        this.group = group;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getAppoint() {
        return appoint;
    }

    public void setAppoint(String appoint) {
        this.appoint = appoint;
    }
}
