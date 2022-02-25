package com.wwx.zero2one.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwx.zero2one.util.ReturnData;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        ReturnData returnData = ReturnData.fail(403, null, "权限不足，请联系管理员");
        writer.write(new ObjectMapper().writeValueAsString(returnData));
        writer.flush();
        writer.close();
    }
}
