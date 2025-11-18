// package com.springk8s.config;

// import java.util.List;

// import org.springframework.cloud.client.DefaultServiceInstance;
// import
// org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;

// @Configuration
// @Profile("local")
// public class LocalServiceInstanceConfig {

// @Bean
// ServiceInstanceListSupplier serviceInstanceListSupplier() {
// return new FixedServiceInstanceListSupplier(
// "spring-on-k8s",
// List.of(
// new DefaultServiceInstance(
// "spring-on-k8s-1",
// "spring-on-k8s",
// "localhsot",
// "8080",
// false)));
// }
// }
