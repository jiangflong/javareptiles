package org.feilong.reptiles.logic;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Component
public class HtmlDownload implements IHtmlDownload {
    @Autowired
    private HttpClient client;

    private HttpGet httpGet;

    public Document download(String url) {
        HttpResponse response = null;
        HttpGet httpGet = new HttpGet(url);
        //设置请求头，将爬虫伪装成浏览器
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.87 Safari/537.36");
        //HttpHost proxy = new HttpHost("27.43.191.129", 9999);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1500) // 创建连接的最长时间，毫秒
                .setSocketTimeout(1500) // 数据传输的最长时间，毫秒
                .setConnectionRequestTimeout(1000) /// 获取连接的最长时间，毫秒
                //      .setProxy(proxy)
                .build();
        httpGet.setConfig(requestConfig);
        try {
            response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    Document html = Jsoup.parse(EntityUtils.toString(entity), "utf-8");
                    return html;
                }
            } else {
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
                //RequestConfig config =  httpGet.getConfig();
            }

        } catch (ClientProtocolException e) {
            System.out.println("下载失败url = " + url);//e.printStackTrace();
        } catch (IOException e) {
            System.out.println("连接超时 url = " + url);
         //   e.printStackTrace();
        } finally {
            //6.关闭
            HttpClientUtils.closeQuietly(response);
        }
        return null;
    }

}
