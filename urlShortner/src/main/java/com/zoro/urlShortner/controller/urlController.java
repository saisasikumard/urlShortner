package com.zoro.urlShortner.controller;

import com.zoro.urlShortner.dto.UrlRequest;
import com.zoro.urlShortner.dto.UrlResponse;
import com.zoro.urlShortner.entity.Url;
import com.zoro.urlShortner.service.UrlService;
import com.zoro.urlShortner.utility.TraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/url")
@Slf4j
public class urlController {
    @Autowired
    UrlService urlService;
    @PostMapping
    public UrlResponse shortUrl(@RequestBody UrlRequest urlReq){
        TraceUtil.createTrace();
        TraceUtil.createSpan();
        log.info("Received object: {}", urlReq);
        log.info("shortUrl: {}", urlReq.getShortUrl());
        log.info("longUrl: {}", urlReq.getLongUrl());
    try {
        log.info("Controller received the request");

        return urlService.saveUrl(urlReq);
    } finally {
        MDC.clear();
    }

    }

    @GetMapping
    public ResponseEntity geturl(@RequestParam("shortUrl") String shortUrl){
        try{
            TraceUtil.createTrace();
            TraceUtil.createSpan();
            log.info("Entered controller with Short URl:{}",shortUrl);
            return ResponseEntity.ok(urlService.getUrl(shortUrl));
        }
        finally {
            MDC.clear();
        }
    }
}
