package org.feilong.reptiles;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"zh-CN\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>ç\u0099¾åº¦å®\u0089å\u0085¨éª\u008Cè¯\u0081</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n" +
                "    <meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0\">\n" +
                "    <meta name=\"format-detection\" content=\"telephone=no, email=no\">\n" +
                "    <link rel=\"shortcut icon\" href=\"https://www.baidu.com/favicon.ico\" type=\"image/x-icon\">\n" +
                "    <link rel=\"icon\" sizes=\"any\" mask href=\"https://www.baidu.com/img/baidu.svg\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\n" +
                "    <meta http-equiv=\"Content-Security-Policy\" content=\"upgrade-insecure-requests\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://wappass.bdimg.com/static/touch/css/api/mkdjump_8befa48.css\" />\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"timeout hide\">\n" +
                "        <div class=\"timeout-img\"></div>\n" +
                "        <div class=\"timeout-title\">ç½\u0091ç»\u009Cä¸\u008Dç»\u0099å\u008A\u009Bï¼\u008Cè¯·ç¨\u008Då\u0090\u008Eé\u0087\u008Dè¯\u0095</div>\n" +
                "        <button type=\"button\" class=\"timeout-button\">è¿\u0094å\u009B\u009Eé¦\u0096é¡µ</button>\n" +
                "    </div>\n" +
                "    <div class=\"timeout-feedback hide\">\n" +
                "        <div class=\"timeout-feedback-icon\"></div>\n" +
                "        <p class=\"timeout-feedback-title\">é\u0097®é¢\u0098å\u008F\u008Dé¦\u0088</p>\n" +
                "    </div>\n" +
                "\n" +
                "<script src=\"https://wappass.baidu.com/static/machine/js/api/mkd.js\"></script>\n" +
                "<script src=\"https://wappass.bdimg.com/static/touch/js/mkdjump_1448d18.js\"></script>\n" +
                "</body>\n" +
                "</html>";
        // html = html.replaceAll("\\\\s*|\\t|\\r|\\n","");
        // System.out.println(html);
        // String reg = "<p.*?>([\\s\\S]*)</p>"; 匹配标签

        CountDownLatch downLatch = new CountDownLatch(1);
        for(int i =0; i< 3; i++){
            downLatch.countDown();
        }
        downLatch.await();
        Document doc = Jsoup.parse(html);
        Elements rows = doc.select("div[class=timeout-feedback hide]");
        for (Element row : rows) {
            System.out.println(row.select("div").get(0).text());
        }
    }
}
