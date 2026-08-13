package com.zoro.urlShortner.service;



import com.zoro.urlShortner.customExceptions.DuplicateUrlException;
import com.zoro.urlShortner.dto.UrlRequest;
import com.zoro.urlShortner.dto.UrlResponse;
import com.zoro.urlShortner.entity.Url;
import com.zoro.urlShortner.repos.UrlRepo;
import com.zoro.urlShortner.utility.TraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.time.LocalDateTime;

@Service
@Slf4j
public class UrlService {
    @Autowired
    UrlRepo urlRepo;
    public UrlResponse saveUrl(UrlRequest urlRequest){
        try {
            //replcing with utility method
            /*String spanId = UUID.randomUUID().toString().substring(0,8);
            MDC.put("spanId", spanId);*/

            String serviceSpanId =TraceUtil.createSpan();
            log.info("Request entered into  Service  method");

            String shortCode = UUID.randomUUID()
                    .toString()
                    .replace("-", "")
                    .substring(0, 8);
            log.info("Created short code");
            log.info("shortUrl from request: {}", urlRequest.getShortUrl());
            log.info("longUrl from request: {}", urlRequest.getLongUrl());
            Url urlObj = Url.builder()
                    .id(shortCode)
                    .shortUrl(urlRequest.getShortUrl())
                    .longUrl(urlRequest.getLongUrl())
                    .creatDate(LocalDateTime.now())
                    .updatDate(LocalDateTime.now())
                    .build();

            TraceUtil.createSpan();

                log.info("saving into db");
                Url savedUrl = urlRepo.save(urlObj);
                log.info("saved in DB");
                TraceUtil.modifySpan(serviceSpanId);
                log.info("Url saved successfully");
                return UrlResponse.builder().
                        id(savedUrl.getId())
                        .shortUrl(savedUrl.getShortUrl())
                        .longUrl(savedUrl.getLongUrl()).build();


        }
        catch (DuplicateKeyException e) {
            log.error("Duplicate shortUrl found for {}", urlRequest.getShortUrl());

            throw new DuplicateUrlException(
                    "Short URL already exists: " + urlRequest.getShortUrl()
            );
        }
        catch(Exception e){
            log.error("Exception occur while creating short Url",e);
            throw new RuntimeException("Exception occur while creating short Url",e);
        }

    }

    public Url getUrl(String shortUrl){
        try {
            String serviceSpan = TraceUtil.createSpan();
            log.info("service method entered with short URl:{}", shortUrl);

            TraceUtil.createSpan();
            log.info("entering into repo to fetch longurl for :{}", shortUrl);
            Url url = urlRepo.findByShortUrl(shortUrl);
            log.info("gethered longurl information for {}", shortUrl);

            TraceUtil.modifySpan(serviceSpan);
            log.info("service method completed.");
            return url;
        }
        catch (Exception e){
            log.error("Exception occur while fetching the longUrl",e);
            throw new RuntimeException("Exception occur while fetching the longUrl",e);
        }
    }
}
