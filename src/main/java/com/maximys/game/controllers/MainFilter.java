/*
package com.maximys.game.controllers;

import com.maximys.game.services.impls.TokenService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class MainFilter implements Filter {
    private final TokenService tokenService;
    @Autowired
    public MainFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse  = (HttpServletResponse) response;
        String token = httpRequest.getHeader("token");
        if (httpRequest.getRequestURI().equals("/token/createToken") || httpRequest.getRequestURI().equals("/game/updateGameInfo")) {
            filterChain.doFilter(request, response);
            return;
        }
        if(!tokenService.validateToken(token)){
            httpResponse.setStatus(500);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().println("Токен не действителен, запросите новый токен " + "\n" + "http://localhost:8090/token/createToken");
            httpResponse.getWriter().flush();
            return;
        }
        filterChain.doFilter(request, response);
    }
}
*/
