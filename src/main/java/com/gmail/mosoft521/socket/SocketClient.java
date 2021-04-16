package com.gmail.mosoft521.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws InterruptedException {
        try {
            // 和服务器创建连接
            Socket socket = new Socket("localhost", 8080);
            // 写入给监听方的输出流
            OutputStream os = socket.getOutputStream();
            //...
            // 读取监听方的输入流
            InputStream is = socket.getInputStream();
            //...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}