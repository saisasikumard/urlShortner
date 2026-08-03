package com.zoro.urlShortner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UrlShortnerApplication {
    @Value("${spring.data.mongodb.uri}")
    private String uri;
    public static void main(String[] args) {
		SpringApplication.run(UrlShortnerApplication.class, args);
	}
    @Bean
    CommandLineRunner runner() {
        return args -> System.out.println(uri);
    }
}
