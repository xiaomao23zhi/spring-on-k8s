package com.springk8s.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("services")
public class ServiceEndpoints {

    private Map<String, String> urls = new HashMap<>();

    public Map<String, String> getUrls() {
        return urls;
    }

    public void setUrls(Map<String, String> urls) {
        this.urls = urls;
    }

    public String getUrl(String serviceName) {
        return urls.getOrDefault(serviceName, "http://" + serviceName);
    }
}