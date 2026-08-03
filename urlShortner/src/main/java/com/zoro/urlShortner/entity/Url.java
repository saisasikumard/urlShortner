package com.zoro.urlShortner.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Document(collation = "urls")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Url {
    @Id
    String id;
    String shortUrl;
    String longUrl;
    LocalDateTime creatDate;
    LocalDateTime updatDate;
    Long visitCount;

}
