package com.gmail.mosoft521.socket;

import java.net.Socket;

public class ServerThread extends Thread {
    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}
