package org.feilong.reptiles.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {
    @Bean
    HttpClient httpClient() {
        return HttpClients.createDefault();
    }



    /**
     * http连接池
     *
     * @return
     */
    @Bean
    PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        // 创建连接池
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
        // 最大连接数
        clientConnectionManager.setMaxTotal(100);
        // 设置每个主机最大连接数（比如100个连接，不能都去爬百度，应该分配一些爬虫给知乎）
        clientConnectionManager.setDefaultMaxPerRoute(10);
        return clientConnectionManager;
    }

}
