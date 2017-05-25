package com.gmail.mosoft521.pic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Snap {
    public static void main(String[] args) throws MalformedURLException, IOException, URISyntaxException, AWTException,Exception {
        // 此方法仅适用于JdK1.6及以上版本
        Desktop.getDesktop().browse(new URL("http://map.baidu.com/").toURI());
        Process p = Runtime.getRuntime().exec("explorer http://map.baidu.com");
//        openURL("http://map.baidu.com/");

        Robot robot = new Robot();
        robot.delay(5000);
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
        // 最大化浏览器
        robot.keyRelease(KeyEvent.VK_F11);
//  robot.delay(2000);
        Image image = robot.createScreenCapture(new Rectangle(0, 0, width, height));

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        // 保存图片
        ImageIO.write(bi, "jpg", new File("D:/baidu.jpg"));

//        p.destroyForcibly();
        Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
        Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
        Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
        Runtime.getRuntime().exec("taskkill /F /IM safari.exe");
        Runtime.getRuntime().exec("taskkill /F /IM opera.exe");
    }

    /**
     * J2SE 5及之前可使用以下代码<br>
     */
    private  static  void openURL(String url) {
        String osName = System.getProperty("os.name");
        System.out.println("###osName:" + osName);
// System.gc();
        Process p = null;
        int exitCode = 1;

        try {
            if (osName.startsWith("Mac")) {// Mac OS
                Class fileMgr = Class.forName("com.apple.eio.FileManager");
                Method openURL = fileMgr.getDeclaredMethod("openURL",
                        new Class[] { String.class });
                openURL.invoke(null, new Object[] { url });
            } else if (osName.startsWith("Windows")) {// Windows
                p = Runtime.getRuntime().exec(
                        "rundll32 url.dll,FileProtocolHandler " + url);

// System.out.println("###p:" + p);
// System.out.println("###p.hashCode():" + p.hashCode());

                exitCode = p.waitFor();
                System.out.println("###exitCode:" + exitCode);
            } else { // Unix or Linux
                String[] browsers = { "firefox", "opera", "konqueror",
                        "epiphany", "mozilla", "netscape" };
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null; count++) {
                    if (Runtime.getRuntime().exec(
                            new String[] { "which", browsers[count] })
                            .waitFor() == 0) {
                        browser = browsers[count];
                    }
                }
                if (browser == null) {
                    throw new Exception("Could not find web browser");
                } else {
                    Runtime.getRuntime().exec(new String[] { browser, url });
                }
            }
            Thread.sleep(10000);
            p.destroy();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}