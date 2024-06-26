package com.customer.customers.security;

import com.customer.customers.model.response.MetaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper;

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        response.sendError(SC_FORBIDDEN,"Forbidden");

        MetaResponse metaResponse = new MetaResponse(Boolean.FALSE,
                HttpStatus.FORBIDDEN.value(),
                accessDeniedException.getLocalizedMessage());

        response.setContentType("application/json");
        response.setStatus(SC_FORBIDDEN);

        OutputStream outputStream = response.getOutputStream();

        mapper.writeValue(outputStream,metaResponse);
        outputStream.flush();

    }
}
