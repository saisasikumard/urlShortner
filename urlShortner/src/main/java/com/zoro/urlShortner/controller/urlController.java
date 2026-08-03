package com.zoro.urlShortner.controller;

import com.zoro.urlShortner.entity.Url;
import com.zoro.urlShortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/url")
public class urlController {
    @Autowired
    UrlService urlService;
    @PostMapping
    public String shortUrl(Url urlReq){
        return urlService.saveUrl(urlReq);
    }
}
