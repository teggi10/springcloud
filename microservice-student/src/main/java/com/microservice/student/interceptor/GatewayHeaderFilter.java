package com.microservice.student.interceptor;

import org.springframework.stereotype.Component;

import java.io.IOException;


import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GatewayHeaderFilter extends OncePerRequestFilter {

    private static final String EXPECTED_SECRET = "mateo-123";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws java.io.IOException, ServletException {
        String gatewaySecret = request.getHeader("X-GATEWAY-SECRET");
        log.info("Invalid gateway secret: {}", gatewaySecret);
        if (!EXPECTED_SECRET.equals(gatewaySecret)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }
        filterChain.doFilter(request, response);
    }

}
