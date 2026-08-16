package com.zoro.urlShortner.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UrlRequest {
    @NotBlank(message = "Long Url is mandatory")
    @Size(max = 1000 ,message = "Actual url should max 1000 characters only")
    String longUrl;

    @NotBlank(message = "Short Url is mandatory")
    @Size(min = 3,max = 15,message = "short Url should size in between 3 to 15 characters only")
    String shortUrl;
}
