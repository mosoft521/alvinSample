package com.gmail.mosoft521.httpclient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageDownloader {
    private static final String USER_AGENT = "Mozilla/5.0 Firefox/26.0";

    private static Logger logger = LoggerFactory.getLogger(ImageDownloader.class);

    private static final int TIMEOUT_SECONDS = 120;

    private static final int POOL_SIZE = 120;

    private static CloseableHttpClient httpclient;

    public static void main(String[] args) throws ClientProtocolException, IOException {

        ImageDownloader imageDownloader = new ImageDownloader();
        imageDownloader.initApacheHttpClient();

        String imageUrl = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png";
        String filePath = "D:\\baidu.png";

        imageDownloader.fetchContent(imageUrl, filePath);

        imageDownloader.destroyApacheHttpClient();
    }

    public void initApacheHttpClient() {
        // Create global request configuration
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_SECONDS * 1000)
                .setConnectTimeout(TIMEOUT_SECONDS * 1000).build();

        // Create an HttpClient with the given custom dependencies and
        // configuration.
        httpclient = HttpClients.custom().setUserAgent(USER_AGENT).setMaxConnTotal(POOL_SIZE)
                .setMaxConnPerRoute(POOL_SIZE).setDefaultRequestConfig(defaultRequestConfig).build();
    }

    private void destroyApacheHttpClient() {
        try {
            httpclient.close();
        } catch (IOException e) {
            logger.error("httpclient close fail", e);
        }
    }

    public void fetchContent(String imageUrl, String filePath) throws ClientProtocolException, IOException {

        HttpGet httpget = new HttpGet(imageUrl);
        httpget.setHeader("Referer", "http://www.baidu.com");

        System.out.println("executing request " + httpget.getURI());
        CloseableHttpResponse response = httpclient.execute(httpget);

        try {
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() >= 400) {
                throw new IOException("Got bad response, error code = " + response.getStatusLine().getStatusCode()
                        + " imageUrl: " + imageUrl);
            }
            if (entity != null) {
                InputStream input = entity.getContent();
                OutputStream output = new FileOutputStream(new File(filePath));
                IOUtils.copy(input, output);
                output.flush();
            }
        } finally {
            response.close();

        }

    }
}
