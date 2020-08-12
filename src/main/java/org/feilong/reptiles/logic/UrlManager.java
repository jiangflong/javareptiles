package org.feilong.reptiles.logic;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * url管理器
 *
 * @Param already 已经取的url
 * @Param prepare 待爬取的url队列
 */
public class UrlManager {
    private volatile Set<String> already = new ConcurrentSkipListSet<>();
    private volatile ConcurrentSkipListSet<String> prepare = new ConcurrentSkipListSet<>();

    public boolean hasUrl() {
        return !prepare.isEmpty();
    }

    public int count() {
        return prepare.size();
    }

    /**
     * 放入url
     * @param url
     */
    public void putUrl(String url) {
        if (!this.already.contains(url) && !this.prepare.contains(url)) {
            synchronized (this) {
                if (!this.already.contains(url) && !this.prepare.contains(url))
                    this.prepare.add(url);
                //System.out.println("放入了 " + url);
            }
        }
    }

    /**
     * 取出url
     * @return url
     */
    public synchronized String takeUrl() {
        String url = this.prepare.first();
        this.prepare.remove(url);
        this.already.add(url);
        //System.out.println("取出了 " + url);
        return url;
    }
}
