package com.zoro.urlShortner.repos;

import com.zoro.urlShortner.entity.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepo extends MongoRepository<Url,String> {
    public  Url findByShortUrl(String shortUrl);
}
