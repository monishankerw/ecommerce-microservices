package com.ecommerce.gateway.filter;

import com.ecommerce.common.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;

import org.springframework.core.Ordered;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.server.reactive.ServerHttpRequest;

import org.springframework.stereotype.Component;

import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter
        implements GlobalFilter, Ordered {

    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        String path =
                exchange.getRequest()
                        .getURI()
                        .getPath();

        System.out.println("PATH: " + path);

        // Skip auth endpoints
        if (path.contains("/auth/")) {

            return chain.filter(exchange);
        }

        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(
                                HttpHeaders.AUTHORIZATION
                        );

        System.out.println("HEADER: " + authHeader);

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            exchange.getResponse()
                    .setStatusCode(
                            HttpStatus.UNAUTHORIZED
                    );

            return exchange.getResponse()
                    .setComplete();
        }

        try {

            String token =
                    authHeader.substring(7);

            System.out.println("TOKEN: " + token);

            String username =
                    jwtService.extractUsername(token);

            String role =
                    jwtService.extractRole(token);

            System.out.println(username);

            ServerHttpRequest request =
                    exchange.getRequest()

                            .mutate()

                            .header(
                                    "X-User",
                                    username
                            )

                            .header(
                                    "X-Role",
                                    role
                            )

                            .build();

            return chain.filter(

                    exchange.mutate()

                            .request(request)

                            .build()
            );

        } catch (Exception e) {

            e.printStackTrace();

            exchange.getResponse()
                    .setStatusCode(
                            HttpStatus.UNAUTHORIZED
                    );

            return exchange.getResponse()
                    .setComplete();
        }
    }

    @Override
    public int getOrder() {

        return -1;
    }
}