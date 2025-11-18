package com.springk8s.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

        private final ServiceEndpoints serviceEndpoints;

        @Bean
        public ExchangeStrategies exchangeStrategies() {
                return ExchangeStrategies.builder()
                                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(100 * 1024 * 1024))
                                .build();
        }

        @Bean
        public HttpClient httpClient() {
                ConnectionProvider provider = ConnectionProvider.builder("custom")
                                .maxConnections(500)
                                .pendingAcquireTimeout(Duration.ofMillis(200))
                                .maxIdleTime(Duration.ofSeconds(10, 000))
                                .build();

                return HttpClient.create(provider)
                                .responseTimeout(Duration.ofSeconds(10))
                                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                                .option(ChannelOption.SO_KEEPALIVE, true)
                                .doOnConnected(connection -> connection
                                                .addHandlerLast(new ReadTimeoutHandler(30, TimeUnit.SECONDS))
                                                .addHandlerLast(new WriteTimeoutHandler(30, TimeUnit.SECONDS)));
        }

        @Bean
        @LoadBalanced
        public WebClient.Builder loadBalancedWebClientBuilder(HttpClient httpClient) {
                return WebClient.builder()
                                .clientConnector(new ReactorClientHttpConnector(httpClient))
                                .exchangeStrategies(exchangeStrategies());
        }

        @Bean
        public WebClient webClient_server(WebClient.Builder loadBalancedWebClientBuilder) {
                String baseUrl = serviceEndpoints.getUrl("spring-on-k8s");

                return loadBalancedWebClientBuilder
                                .clone()
                                .baseUrl(baseUrl)
                                .build();
        }
}
