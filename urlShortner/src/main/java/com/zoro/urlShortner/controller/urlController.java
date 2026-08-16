package com.zoro.urlShortner.controller;

import com.zoro.urlShortner.dto.UrlRequest;
import com.zoro.urlShortner.dto.UrlResponse;
import com.zoro.urlShortner.entity.Url;
import com.zoro.urlShortner.service.UrlService;
import com.zoro.urlShortner.utility.TraceUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/url")
@Slf4j
@Tag(name = "URL Shortener", description = "Endpoints for creating, retrieving, and deleting shortened URLs")

public class urlController {
    @Autowired
    UrlService urlService;

    @Operation(
            summary = "Create a short URL",
            description = "Accepts a long URL and a custom short code (3-15 characters), and saves the mapping."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Short URL created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UrlResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed (e.g. missing longUrl, shortUrl not in 3-15 chars)")
    })
    @PostMapping
    public UrlResponse shortUrl(@Valid @RequestBody UrlRequest urlReq)
    {
        TraceUtil.createTrace();
        TraceUtil.createSpan();
        log.info("Received object: {}", urlReq);
        try {
            log.info("Controller received the request");
            return urlService.saveUrl(urlReq);
        }
        finally {
            MDC.clear();
        }
    }
    @Operation(
            summary = "Fetch Url information",
            description = "Input is short url and fetches related LongUrl"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Actual Url fetched.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation  =UrlResponse.class))),
        @ApiResponse(responseCode = "400",description = "Validation failed (e.g missing logUrl, ShortUrl not in 3-15 chars)")
    })
    @GetMapping
    public ResponseEntity geturl(
            @NotBlank(message = "Short Url is Mandatory")
            @RequestParam("shortUrl") String shortUrl){
        try{
            TraceUtil.createTrace();
            TraceUtil.createSpan();
            log.info("Entered controller with Short URl :{}",shortUrl);
            return ResponseEntity.ok(urlService.getUrl(shortUrl));
        }
        finally {
            MDC.clear();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUrl(@NotBlank(message = "Short Url is Mandatory") @RequestParam("shortUrl") String shortUrl){
        try{
            TraceUtil.createTrace();
            TraceUtil.createTrace();
            log.info("Entered Controller Request");
            return ResponseEntity.ok(urlService.deleteUrl(shortUrl));
        }
        finally {
            MDC.clear();
        }
    }
}
