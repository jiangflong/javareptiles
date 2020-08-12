package org.feilong.reptiles.controller;

import org.feilong.reptiles.logic.CpuService;
import org.feilong.reptiles.logic.UrlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private CpuService cpuService;

    @RequestMapping(value = "reptiles", method = RequestMethod.GET)
    public Object reptiles(@RequestParam String url) {
        UrlManager urlManager = new UrlManager();
        for (int i = 0; i < 10; i++) {
            String index = String.format("%03d",i);
            urlManager.putUrl("http://www.guoxue123.com/xiaosuo/jd/xyj/" + index + ".htm");
        }
        return cpuService.reptiles(urlManager);
    }
}
