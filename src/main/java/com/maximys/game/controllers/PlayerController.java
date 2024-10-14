package com.maximys.game.controllers;

import com.maximys.game.services.impls.PlayerService;
import com.maximys.game.services.impls.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/player")
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }
    @PostMapping(value = "/startGame")
    public Object startGame(){
        Object o = playerService.startGame();
        return o;
    }
}
