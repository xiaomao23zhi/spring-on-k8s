package com.springk8s.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springk8s.config.ApplicationConfig;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "ServerController", description = "Server Controller")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ServerController {

    private final ApplicationConfig applicationConfig;

    @Operation(summary = "message")
    @GetMapping("/message")
    public String message() {

        String message = applicationConfig.getMessage() + "|" + applicationConfig.getInfo();

        log.info("Current message from config: {}", message);
        return message;
    }
}
