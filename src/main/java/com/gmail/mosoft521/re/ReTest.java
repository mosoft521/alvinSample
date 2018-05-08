package com.gmail.mosoft521.re;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReTest {
    public static void main(String[] args) {
        // (?=) 正向前查找    (?!) 负向前查找
        // (?<=) 正向后查找   (?<!) 负向后查找
        //向前匹配?=
        String a = "https://mail.huawei.com ";
        Pattern p = Pattern.compile(".+(?=:)");
        Matcher m = p.matcher(a);
        while (m.find()) {
            String group = m.group();
            System.out.println(group);
        }
        //https
        System.out.println("===============================");

        //向后匹配?<=
        a = "I paid $90 for 10 oranges, 12 pears and 8 APPLES. I saved $5 on ";
        p = Pattern.compile("(?<=\\$)\\d+");
        m = p.matcher(a);
        while (m.find()) {
            String group = m.group();
            System.out.println(group);
        }

        //90
        //5
        System.out.println("===============================");

        //向后匹配?<=
        a = "I paid $90 for 10 oranges, 12 pears and 8 apples. I saved $5 on this order.";
        p = Pattern.compile("(?<=\\$)\\d+");//只查找钱款
        m = p.matcher(a);
        while (m.find()) {
            String group = m.group();
            System.out.println(group);
        }
        //90
        //5
        System.out.println("===============================");
        p = Pattern.compile("\\b(?<!\\$)\\d+\\b");//只查找数量
        m = p.matcher(a);
        while (m.find()) {
            String group = m.group();
            System.out.println(group);
        }

        //10
        //2
        //8
        System.out.println("===============================");
        a = "_id\n" +
                "deploy_id\n" +
                "sc_id\n" +
                "sc_guid\n" +
                "sys_id\n" +
                "sys_guid\n" +
                "group_id\n" +
                "group_guid\n" +
                "org_id\n" +
                "org_guid\n" +
                "dept_id\n" +
                "dept_guid\n" +
                "user_id\n" +
                "user_guid\n" +
                "sys_user_id\n" +
                "sys_userguid\n" +
                "site_parent_id\n" +
                "site_parent_guid\n" +
                "web_site_id\n" +
                "web_site_guid\n" +
                "e_pur_id\n" +
                "e_pur_guid\n" +
                "e_pur_no\n" +
                "e_pur_date\n" +
                "e_pur_remark\n" +
                "e_client_type\n" +
                "pur_id\n" +
                "pur_guid\n" +
                "pur_no\n" +
                "pur_qty\n" +
                "pur_amount\n" +
                "pur_amount_tax\n" +
                "pur_amount_discount\n" +
                "pur_amount_freight\n" +
                "contacts_name\n" +
                "contacts_phone\n" +
                "contacts_tel\n" +
                "contacts_address\n" +
                "contacts_memo\n" +
                "receive_date\n" +
                "receive_time\n" +
                "urgent_flag\n" +
                "_deploy_id\n" +
                "_sc_id\n" +
                "_sc_guid\n" +
                "_sys_id\n" +
                "_sys_guid\n" +
                "_group_id\n" +
                "_group_guid\n" +
                "_org_id\n" +
                "_org_guid\n" +
                "_dept_id\n" +
                "_dept_guid\n" +
                "_user_id\n" +
                "_user_guid\n" +
                "_sys_user_id\n" +
                "_sys_userguid\n" +
                "_client_id\n" +
                "_client_guid\n" +
                "_client_no\n" +
                "_client_name\n" +
                "add_by\n" +
                "add_name\n" +
                "add_time\n" +
                "last_edit_by\n" +
                "last_edit_name\n" +
                "last_edit_time\n" +
                "valid_by\n" +
                "valid_name\n" +
                "valid_time\n" +
                "stop_by\n" +
                "stop_name\n" +
                "stop_time\n" +
                "stop_note\n" +
                "valid_flag\n" +
                "del_flag\n" +
                "qr_code\n" +
                "qr_code1\n";
//        p = Pattern.compile("\\b_(?!id).+");//以_开头但不是_id
        p = Pattern.compile("\\b_(?!id).+");//以_开头但不是_id
//        p = Pattern.compile("\\b_(?!id.+)");//以_开头但不是_id的_
        m = p.matcher(a);
        while (m.find()) {
            String group = m.group();
            System.out.println(group);
        }
    }
}
