package com.gmail.mosoft521.socket;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
        try {
            // 创建服务端socket
            ServerSocket serverSocket = new ServerSocket(8080);
            //循环监听等待客户端的连接
            while (true) {
                //监听到客户端连接，传输套接字被动开启
                Socket socket = serverSocket.accept();
                //开启线程进行连接的IO处理
                ServerThread thread = new ServerThread(socket);
                thread.start();
                //......
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }
}