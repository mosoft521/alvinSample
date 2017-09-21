package com.gmail.mosoft521.swing;
//代码虽然写出来了，但是打开某些文件时还会产生乱码

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

class Note implements ActionListener {
    JFrame frame = new JFrame("My Note");
    JTextArea textArea = new JTextArea(8, 10);//定义文本区
    JPanel butp = new JPanel();   //创建一个面板，用于加载按钮组件
    JButton open = new JButton("打开文件");
    JButton save = new JButton("保存文件");
    JLabel label = new JLabel("现在没有打开的文件");

    public Note() {
        butp.add(this.open);//向面板中加入打开按钮
        butp.add(this.save);//向面板中加入保存按钮
        this.frame.setLayout(new BorderLayout(3, 3));//设置窗体的布局，并指定组件边框之间的间距
        this.frame.add(this.label, BorderLayout.NORTH);
        this.frame.add(this.butp, BorderLayout.SOUTH);
        this.frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        this.frame.setSize(330, 180);
        this.frame.setVisible(true);
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        this.open.addActionListener(this);//为保存按钮添加事件监听
        this.save.addActionListener(this);//为保存按钮添加事件监听
    }

    public void actionPerformed(ActionEvent e) {
        File file = null; //接收文件
        int result;   //接收操作状态
        JFileChooser fileChooser = new JFileChooser();//创建文件选择框
        if (e.getSource() == this.open) {//表示执行的是打开文件的操作
            this.textArea.setText("");//将文本域中的内容清空
            fileChooser.setApproveButtonText("确定");
            result = fileChooser.showOpenDialog(this.frame);
            if (result == JFileChooser.APPROVE_OPTION) {//选择的是确定按钮
                file = fileChooser.getSelectedFile();//得到选择的文件
                this.label.setText("打开的文件名称为：" + file.getName());
            } else if (result == JFileChooser.CANCEL_OPTION) {//选择的是取消按钮
                this.label.setText("没有选择任何文件");
            } else {
                this.label.setText("操作出现错误");
            }
            if (file != null) {
                try {
                    Scanner scanner = new Scanner(new FileInputStream(file));
                    scanner.useDelimiter("/n");
                    while (scanner.hasNext()) {
                        this.textArea.append(scanner.next());
                        this.textArea.append("/n");
                    }
                    scanner.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

public class JFileChooserDemo {
    public static void main(String args[]) {
        new Note();
    }
}