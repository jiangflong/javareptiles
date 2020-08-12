package org.feilong.reptiles.logic;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 调度器
 */
@Component
public class CpuService {

    @Autowired
    private HtmlDownload htmlsDownload;

    @Resource(name = "downloadService")
    protected ExecutorService service;
    private ConcurrentHashMap<Integer, Document> htmls = new ConcurrentHashMap<>();
    private Map<Integer, String> novel = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 == null || o2 == null)
                return 0;
            return o1 > 02 ? 0 : 1;
        }
    });

    private volatile CountDownLatch downLatch;

    public List<String> reptiles(UrlManager urlManager) {
        List<String> result = new ArrayList<>();
        int size = urlManager.count();
        downLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            String url = urlManager.takeUrl();
            final Integer index = i;
            service.submit(() -> {
                Integer flg = index;
                System.out.println("Kaishi第"+ flg +"个下载");
                Document html = htmlsDownload.download(url);
                if (html != null) {
                    String str = new Parser(html).getNovel();
                    novel.put(flg, str);
                    System.out.println("第" + index + "章下载wan");
                }else {
                    System.out.println("第" + index + "章下载失败");
                }
                downLatch.countDown();
                System.out.println("第"+ flg +"个下载结束");
            });
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (novel.size() != 0) {
            try {
                Files.write(Paths.get("小说.txt"), new ArrayList<>(novel.values()), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
