package com.zoro.urlShortner.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection  = "urls")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Url {
    @Id
    String id;
    @JsonProperty("shortUrl")
    @Indexed(unique = true)
    String shortUrl;
    @JsonProperty("longUrl")
    String longUrl;
    LocalDateTime creatDate;
    LocalDateTime updatDate;
    Long visitCount;

}
