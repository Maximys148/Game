package com.maximys.game.controllers;

import com.maximys.game.components.Player;
import com.maximys.game.dto.GameResponseDTO;
import com.maximys.game.enums.ResponseStatus;
import com.maximys.game.services.impls.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<GameResponseDTO> getGameInfo(@RequestParam ("nickname") String nickname){
    if(!gameService.addPlayer(nickname))
        return ResponseEntity.status(400).body(new GameResponseDTO(ResponseStatus.ERROR_PLAYER_ALREADY_EXISTS,"Игрок с таким никнеймом уже существует", null));
    if(gameService.countPlayer() <= 1)
        return ResponseEntity.status(400).body(new GameResponseDTO(ResponseStatus.ERROR_NOT_ENOUGH_PLAYERS, "Сервер ещё не заполнен(не хватает игроков), подождите и обновите страницу http://localhost:8090/game/updateGameInfo", null));
    return ResponseEntity.ok(new GameResponseDTO(ResponseStatus.SUCCESS, null, gameService.getGameInfo(nickname)));
    }

    @PostMapping(value = "/updateGameInfo")
    public ResponseEntity<GameResponseDTO> updateGameInfo(){
        if(gameService.countPlayer() <= 1)
            return ResponseEntity.status(400).body(new GameResponseDTO(ResponseStatus.ERROR_NOT_ENOUGH_PLAYERS, "Сервер ещё не заполнен(не хватает игроков), подождите и обновите страницу http://localhost:8090/game/updateGameInfo", null));
        return ResponseEntity.ok(new GameResponseDTO(ResponseStatus.SUCCESS, null, gameService.printMap()));
    }
    @PostMapping(value = "/move")
    public ResponseEntity<GameResponseDTO> move(@RequestParam ("nickname") String nickname, @RequestParam("x") int x, @RequestParam("y") int y){
        String moveInfo = gameService.move(nickname, x, y);
        if(!moveInfo.equals("Вы успешно сделали шаг")){
            return ResponseEntity.status(400).body(new GameResponseDTO(ResponseStatus.ERROR_PLAYER_MOVE, moveInfo, null));
        }
        return ResponseEntity.ok(new GameResponseDTO(ResponseStatus.SUCCESS, null, gameService.getMap()));
    }
}
