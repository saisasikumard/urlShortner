package com.zoro.urlShortner.customExceptions;

public class UrlNotFoundException extends RuntimeException{
    public UrlNotFoundException(String msg){
        super(msg);
    }
}
