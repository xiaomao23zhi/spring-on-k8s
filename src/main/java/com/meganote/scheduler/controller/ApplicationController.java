package com.meganote.scheduler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meganote.scheduler.config.ApplicationConfig;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "SchedulerController", description = "Scheduler Controller")
@RestController
public class ApplicationController {

    private final ApplicationConfig applicationConfig;

    public ApplicationController(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Operation(summary = "message")
    @GetMapping("/message")
    public String message() {

        return String.format(applicationConfig.getMessage(), "1", "2");
    }
}
