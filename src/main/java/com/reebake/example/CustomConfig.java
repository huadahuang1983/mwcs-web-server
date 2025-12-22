package com.reebake.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feiniaojin.gracefulresponse.api.ResponseFactory;
import com.reebake.mwcs.security.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfig {

    @Bean
    public CustomLoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler(AuthenticationService authenticationService
        , ObjectMapper objectMapper, ResponseFactory responseFactory) {
        return new CustomLoginAuthenticationSuccessHandler(authenticationService, objectMapper, responseFactory);
    }
}
