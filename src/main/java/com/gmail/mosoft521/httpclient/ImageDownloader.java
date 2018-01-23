package com.gmail.mosoft521.httpclient;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageDownloader {

    private static final String USER_AGENT = "Mozilla/5.0 Firefox/26.0";
    private static final int SIZE = 2;
    private static final String EXT = ".jpg";
    private static final char PAD_CHAR = '0';
    private static final String DRIVER = "E:\\picDown\\02b\\";
    private static final int START = 1;

    private static final int END = 65;
    private static final String REFERER_HEADER = "http://www.mzitu.com/83881";
    private static final String IMAGE_URL_HEADER = "http://i.meizitu.net/2017/04/";
    private static final String GROUP = "02b";
    private static final boolean NEED_GROUP = true;


    private static Logger logger = LoggerFactory.getLogger(ImageDownloader.class);

    private static final int TIMEOUT_SECONDS = 120;

    private static final int POOL_SIZE = 120;

    private static CloseableHttpClient httpclient;

    public static void main(String[] args) throws ClientProtocolException, IOException {

        ImageDownloader imageDownloader = new ImageDownloader();
        imageDownloader.initApacheHttpClient();

        for (int i = START; i <= END; i++) {
            String imageUrl = IMAGE_URL_HEADER + (NEED_GROUP ? GROUP : "") + StringUtils.leftPad(Integer.toString(i), SIZE, PAD_CHAR) + EXT;//"http://i.meizitu.net/2017/10/06c01.jpg";
            String filePath = DRIVER + (NEED_GROUP ? GROUP : "") + StringUtils.leftPad(Integer.toString(i), SIZE, PAD_CHAR) + EXT;//"D:\\06c01.jpg";
            imageDownloader.fetchContent(i + 1, imageUrl, filePath);
        }

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

    public void fetchContent(int i, String imageUrl, String filePath) throws ClientProtocolException, IOException {

        HttpGet httpget = new HttpGet(imageUrl);
        httpget.setHeader("Referer", REFERER_HEADER + i);

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
