package com.maximys.game.controllers;

import com.maximys.game.entities.Game;
import com.maximys.game.services.impls.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/game")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }
    @PostMapping(value = "/getGameInfo")
    public ResponseEntity<Object> getGameInfo(@RequestParam ("nickname") String nickname){
        if(!gameService.addPlayer(nickname))
            return ResponseEntity.status(500).body("Игрок с таким никнеймом уже существует");
        if(gameService.countPlayer() <= 1)
            return ResponseEntity.status(500).body("Сервер ещё не заполнен(не хватает игроков), подождите и обновите страницу http://localhost:8090/game/updateGameInfo");
        return ResponseEntity.ok(new Game(gameService.getPlayers(), gameService.getMap()));
    }

    @PostMapping(value = "/updateGameInfo")
    public ResponseEntity<Object> updateGameInfo(){
        if(gameService.countPlayer() <= 1)
            return ResponseEntity.status(500).body("Сервер ещё не заполнен(не хватает игроков), подождите и обновите страницу http://localhost:8090/game/updateGameInfo");
        return ResponseEntity.ok(new Game(gameService.getPlayers(), gameService.getMap()));
    }
    @PostMapping(value = "/move")
    public ResponseEntity<Object> move(@RequestParam ("nickname") String nickname, @RequestParam("x") int x, @RequestParam("y") int y){
        String move = gameService.move(nickname, x, y);
        return ResponseEntity.ok(new Game(gameService.getPlayers(), gameService.getMap()));
    }


}
