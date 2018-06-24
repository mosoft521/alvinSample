package com.gmail.mosoft521.httpclient;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class HttpClientMosoft {

    private static String URL = "http://www.sina.com.cn/";

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException {

        //连接池
        RegistryBuilder<ConnectionSocketFactory> schemeRegistry = RegistryBuilder.create();
        schemeRegistry.register("http", PlainConnectionSocketFactory.getSocketFactory());

        //对Https的支持
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(new KeyManager[0], null, null);//todo:
        SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext);
        schemeRegistry.register("https", sf);

        //连接池配置
        PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager(schemeRegistry.build());
        pool.setMaxTotal(1000);//最大连接数支持
        pool.setDefaultMaxPerRoute(100);//每一个路由的最大连接数
        pool.setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(5000).build());


        //http method
        StringEntity entity = new StringEntity("{\"name\";\"test\"}", "UTF-8");
        entity.setContentType("application/json");
        HttpPost method = new HttpPost(URL);
        method.setEntity(entity);

        //Cookie
        CookieStore cookieStore = new BasicCookieStore();
        //cookieStore.addCookie(Cookie cookie);//todo
        List<Cookie> cookies = cookieStore.getCookies();

        //HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClients.custom().setDefaultCookieStore(cookieStore);//Cookie支持
        httpClientBuilder.setConnectionManager(pool);//设置连接池
        httpClientBuilder.setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(5000).build());
        //对KeepAlive策略配置
        httpClientBuilder.setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement headerElement = it.nextElement();
                    String param = headerElement.getName();
                    String value = headerElement.getValue();
                    if (null != value && param.equalsIgnoreCase("timeout")) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch (NumberFormatException e) {

                        }
                    }
                }
                //否则保持活动5s
                return 5 * 1000;
            }
        });
        HttpClient httpClient = httpClientBuilder.build();

        //使用
        method.setProtocolVersion(HttpVersion.HTTP_1_1);//设置使用HTTP 1.1
        method.addHeader("User-Agent", "Mozilla/5.0 Firefox/26.0");//设置UA
        method.addHeader("Connection", "keep-alive");//为了Keep Alive支持HTTP 1.0

        HttpResponse res = httpClient.execute(method);

        byte[] bytes = EntityUtils.toByteArray(res.getEntity());

        System.out.println("ori: " + new ByteArrayInputStream(bytes));//java.io.ByteArrayInputStream@49fc609f

        //Convert back to String
        String s = new String(bytes);

        //Check converted string against original String
        System.out.println("Decoded String : " + s);
    }
}
/*
ori: java.io.ByteArrayInputStream@49fc609f
Decoded String : <html>
<head><title>302 Found</title></head>
<body bgcolor="white">
<center><h1>302 Found</h1></center>
<hr><center>c62f0889830ce6fe4a42bf467d3fe65de563ec2e
Time : Mon Jun 11 19:18:15 CST 2018</center>
</body>
</html>
 */