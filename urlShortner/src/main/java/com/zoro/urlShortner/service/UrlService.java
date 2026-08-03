package com.zoro.urlShortner.service;

import com.zoro.urlShortner.entity.Url;
import com.zoro.urlShortner.repos.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.time.LocalDateTime;

@Service
public class UrlService {
    @Autowired
    UrlRepo urlRepo;
    public String saveUrl(Url urlRequest){


        String shortCode = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8);
        Url urlObj=Url.builder()
                .id(shortCode)
                .shortUrl(urlRequest.getShortUrl())
                .longUrl(urlRequest.getLongUrl())
                .creatDate(LocalDateTime.now())
                .updatDate(LocalDateTime.now())
                .build();
       Url savedUrl=urlRepo.save(urlObj);
       return savedUrl.getId();

    }
}
