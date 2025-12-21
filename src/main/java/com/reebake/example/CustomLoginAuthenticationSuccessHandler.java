package com.reebake.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feiniaojin.gracefulresponse.api.ResponseFactory;
import com.reebake.mwc.security.dto.AuthResponse;
import com.reebake.mwc.security.handler.LoginAuthenticationSuccessHandler;
import com.reebake.mwc.security.model.User;
import com.reebake.mwc.security.service.AuthenticationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public class CustomLoginAuthenticationSuccessHandler extends LoginAuthenticationSuccessHandler {
    private final ResponseFactory responseFactory;

    public CustomLoginAuthenticationSuccessHandler(AuthenticationService authenticationService, ObjectMapper objectMapper, ResponseFactory responseFactory) {
        super(authenticationService, objectMapper);
        this.responseFactory = responseFactory;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        AuthResponse authResponse = authenticationService.generateAuthResponse(user);

        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(responseFactory.newSuccessInstance(authResponse)));
    }


}
