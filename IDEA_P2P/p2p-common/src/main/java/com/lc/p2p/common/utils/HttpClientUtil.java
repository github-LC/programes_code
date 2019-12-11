package com.lc.p2p.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.*;

public class HttpClientUtil {

    public static String doPost(String url, Map<String, Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {
            //创建httpClient实例
            httpClient = HttpClients.createDefault();
            //创建httpPost远程连接实例
            HttpPost httpPost = new HttpPost(url);
            //配置请求参数实例
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(35000)//设置连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)//设置连接请求超时时间
                    .setSocketTimeout(60000)//设置读取数据连接超时时间
                    .build();
            //为httpPost实例设置配置
            httpPost.setConfig(requestConfig);

            //封装post请求参数
            if (null != paramMap && paramMap.size() > 0) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                //通过map集成entrySet方法获取entity
                Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
                //循环遍历，获取迭代器
                Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> mapEntry = iterator.next();
                    nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
                }

                //为httpPost设置封装好的请求参数
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }

            //执行post请求得到返回对象
            response = httpClient.execute(httpPost);
            //通过返回对象获取数据
            HttpEntity entity = response.getEntity();
            //将返回的数据转换为字符串
            result = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String doPost(String url, String requestDateXml) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {
            //创建httpClient实例
            httpClient = HttpClients.createDefault();
            //创建httpPost远程连接实例
            HttpPost httpPost = new HttpPost(url);
            //配置请求参数实例
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(35000)//设置连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)//设置连接请求超时时间
                    .setSocketTimeout(60000)//设置读取数据连接超时时间
                    .build();
            //为httpPost实例设置配置
            httpPost.setConfig(requestConfig);

            httpPost.setHeader("Content-type","text/html");
            httpPost.setEntity(new StringEntity(requestDateXml,"UTF-8"));

            //执行post请求得到返回对象
            response = httpClient.execute(httpPost);
            //通过返回对象获取数据
            HttpEntity entity = response.getEntity();
            //将返回的数据转换为字符串
            result = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
