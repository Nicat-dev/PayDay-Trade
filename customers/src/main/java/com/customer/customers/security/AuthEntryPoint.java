package com.customer.customers.security;

import com.customer.customers.model.response.MetaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
@RequiredArgsConstructor
public class AuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException{

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");

        MetaResponse metaResponse = new MetaResponse(Boolean.FALSE,
                HttpStatus.UNAUTHORIZED.value(),authException.getLocalizedMessage());

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        OutputStream out = response.getOutputStream();
        mapper.writeValue(out, metaResponse);
        out.flush();
    }
}
