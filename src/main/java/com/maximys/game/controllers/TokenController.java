package com.maximys.game.controllers;

import com.maximys.game.services.impls.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/token")
public class TokenController {
    private TokenService tokenService;
    public TokenController(TokenService tokenService){
        this.tokenService = tokenService;
    }
    @PostMapping(value = "/createToken")
    public ResponseEntity<String> createToken(){
        tokenService.createToken();
        return ResponseEntity.ok("Success");
        // return ResponseEntity.status(500).body("Ошибка, пользователь с таким никнеймом уже существует" + "\n" + "Или выполните Авторизацию http://localhost:8080/start/auth?nickname=Oleg12&password=1234");
    }
}
