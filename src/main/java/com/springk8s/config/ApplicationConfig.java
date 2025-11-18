package com.springk8s.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class ApplicationConfig {

    private String message = "Application default message";

    private String info = "Application info";
}
