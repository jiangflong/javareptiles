package org.feilong.reptiles.logic;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class Parser {
    private Document html;

    public Parser(Document html) {
        this.html = html;
    }

    public Elements getById(String id) {
        Elements rows = html.select("div[id=" + id + "]");
        List<String> result = new ArrayList<String>();
        return rows;
    }

    public Elements getByClass(String cla) {
        Elements rows = html.select("div[class=" + cla + "]");
        List<String> result = new ArrayList<String>();
        return rows;
    }

    public String getNovel() {
        StringBuilder novel = new StringBuilder();
        try{
            String title = this.getByClass("title").get(0).text();
            String ps = this.getByClass("content").select("p").text();
            novel.append(title + "\n");
            novel.append(ps + "\n");
        }catch (Exception e){
            System.out.println("页面解析出错");
        }
        return novel.toString();
    }
}
