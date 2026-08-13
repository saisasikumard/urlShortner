package com.zoro.urlShortner.utility;

import org.slf4j.MDC;

import java.util.UUID;

public class TraceUtil {
    public static String  createSpan(){
        String spanId=UUID.randomUUID().toString().substring(0,8);
       MDC.put("spanId", spanId);
       return spanId;
    }
    public static String  modifySpan(String spanId){

        MDC.put("spanId", spanId);
        return spanId;
    }
    public static String  createTrace(){
        String traceId="T"+UUID.randomUUID().toString().substring(0,8);
        MDC.put("spanId", traceId);
        return traceId;
    }
}
