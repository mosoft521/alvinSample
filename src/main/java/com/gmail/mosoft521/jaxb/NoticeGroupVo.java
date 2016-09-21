package com.gmail.mosoft521.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "group")
@XmlType(name = "group", propOrder = {"specialMemeber", "member"})
public class NoticeGroupVo {
    @XmlAttribute(name = "isNotAllSpecialMemeber")
    private String isNotAllSpecialMemeber;
    @XmlAttribute(name = "isNotAllMember")
    private String isNotAllMember;
    @XmlAttribute(name = "isNotAllCustomers")
    private String isNotAllCustomers;
    private String specialMemeber;
    private String member;

    public NoticeGroupVo() {
    }

    public String getIsNotAllSpecialMemeber() {
        return isNotAllSpecialMemeber;
    }

    public void setIsNotAllSpecialMemeber(String isNotAllSpecialMemeber) {
        this.isNotAllSpecialMemeber = isNotAllSpecialMemeber;
    }

    public String getIsNotAllMember() {
        return isNotAllMember;
    }

    public void setIsNotAllMember(String isNotAllMember) {
        this.isNotAllMember = isNotAllMember;
    }

    public String getIsNotAllCustomers() {
        return isNotAllCustomers;
    }

    public void setIsNotAllCustomers(String isNotAllCustomers) {
        this.isNotAllCustomers = isNotAllCustomers;
    }

    public String getSpecialMemeber() {
        return specialMemeber;
    }

    public void setSpecialMemeber(String specialMemeber) {
        this.specialMemeber = specialMemeber;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
