package com.wwx.zero2one.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwx.zero2one.util.ReturnData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        ReturnData data = ReturnData.fail(401, null, "未登录，请登录");
        writer.write(new ObjectMapper().writeValueAsString(data));
        writer.flush();
        writer.close();
    }
}
