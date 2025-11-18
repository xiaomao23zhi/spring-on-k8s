package com.springk8s.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "ClientController", description = "Client Controller")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ClientController {

    private final WebClient webClient_server;

    @Operation(summary = "message")
    @GetMapping("/getMessage")
    public String message() {

        return webClient_server
                .get()
                .uri("/message")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
