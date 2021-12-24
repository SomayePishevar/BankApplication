package com.faraz.interview.service;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpHandler.class);

    public static String sendPostRequest(String url, String input) throws IOException {
        LOGGER.info("sendPostRequest method in HttpHandler class is started...");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        if (input != null) {
            StringEntity stringEntity = new StringEntity(input);
            httpPost.setEntity(stringEntity);
        }
        CloseableHttpResponse response = httpclient.execute(httpPost);
        int status = response.getStatusLine().getStatusCode();
        if (status == 200){
            LOGGER.info("Post request done successfully with status code {}",status);
            return String.valueOf(status);
        }else {
            LOGGER.info("HttpResponseException has been occurred, status code {}",status);
            throw new HttpResponseException(status, "Unexpected response status: " + status);
        }
    }
//    public static String sendPOST(String url, String jsonData) throws IOException {
//
//        String result = "";
//        HttpPost post = new HttpPost(url);
//
//        StringBuilder data = new StringBuilder(jsonData);
//
//        post.setEntity(new StringEntity(data.toString()));
//
//        try (CloseableHttpClient httpClient = HttpClients.createDefault();
//             CloseableHttpResponse response = httpClient.execute(post)) {
//
//            result = String.valueOf(response.getStatusLine().getStatusCode());
//                  //  EntityUtils.toString(response.getEntity());
//        }
//
////        try (CloseableHttpClient httpClient = HttpClients.createDefault();
////             CloseableHttpResponse response = httpClient.execute(post)) {
////
////            result = String.valueOf(response.getStatusLine().getStatusCode());
////            //  EntityUtils.toString(response.getEntity());
////        }
//
//        return result;
//    }
}
