package com.gmail.mosoft521.runtime;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;


public class StreamGobblerOld extends Thread {
    InputStream is;
    String type;
    OutputStream os;

    StreamGobblerOld(InputStream is, String type) {
        this(is, type, null);
    }

    StreamGobblerOld(InputStream is, String type, OutputStream redirect) {
        this.is = is;
        this.type = type;
        this.os = redirect;
    }

    public static void main(String args[]) {
        try {
            FileOutputStream fos = new FileOutputStream("d:/logs/a.log");
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("cmd.exe /C fmpp -S D:\\tools\\fmpp\\docs\\examples\\qtour_step1\\src\\ -O D:\\tools\\fmpp\\docs\\examples\\qtour_step1\\out\\");

            // 重定向输出流和错误流
            StreamGobblerOld errorGobbler = new StreamGobblerOld(proc.getErrorStream(), "ERROR");
            StreamGobblerOld outputGobbler = new StreamGobblerOld(proc.getInputStream(), "OUTPUT", fos);

            errorGobbler.start();
            outputGobbler.start();
            int exitVal = proc.waitFor();
            System.out.println("ExitValue: " + exitVal);
            fos.flush();
            fos.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void run() {
        try {
            PrintWriter pw = null;
            if (os != null)
                pw = new PrintWriter(os);

            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (pw != null)
                    pw.println(line);
                System.out.println(type + ">" + line);
            }
            if (pw != null)
                pw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}