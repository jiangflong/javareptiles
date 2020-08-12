package org.feilong.reptiles.logic;

import org.jsoup.nodes.Document;

import java.util.concurrent.BlockingQueue;

/**
 * 网页下载器
 */

public interface IHtmlDownload {
    Document download(String url);
}
