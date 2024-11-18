package com.maximys.game.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maximys.game.dto.GameResponseDTO;
import com.maximys.game.dto.MoveDTO;
import com.maximys.game.dto.RegistrationDTO;
import com.maximys.game.enums.BodyType;
import com.maximys.game.enums.ResponseStatus;
import com.maximys.game.services.impls.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/game")
public class GameController {
    private final GameService gameService;
    private final ObjectMapper mapper;

    @Autowired
    public GameController(GameService gameService, ObjectMapper mapper){
        this.gameService = gameService;
        this.mapper = mapper;
    }
    @PostMapping(value = "/registration")
    public ResponseEntity<GameResponseDTO> registration(@RequestBody RegistrationDTO registrationDTO){
    if(!gameService.addPlayer(registrationDTO.getNickName()))
        return ResponseEntity.status(400).body(new GameResponseDTO(ResponseStatus.ERROR_PLAYER_ALREADY_EXISTS,"Игрок с таким никнеймом уже существует"));
    return ResponseEntity.ok(new GameResponseDTO(ResponseStatus.SUCCESS, "Вы зарегистрированы!"));
    }

    @PostMapping(value = "/getGameInfo")
    public ResponseEntity<GameResponseDTO> getGameInfo(@RequestBody RegistrationDTO registrationDTO) throws JsonProcessingException {
        return ResponseEntity.ok(new GameResponseDTO( ResponseStatus.SUCCESS, null, mapper.writeValueAsString(gameService.getGame()), BodyType.GAME));
    }

    @PostMapping(value = "/validateStart")
    public ResponseEntity<GameResponseDTO> updateGameInfo(@RequestBody RegistrationDTO registrationDTO) throws JsonProcessingException {
        if(gameService.countPlayer() <= 1)
            return ResponseEntity.status(400).body(new GameResponseDTO(ResponseStatus.ERROR_NOT_ENOUGH_PLAYERS, "Сервер ещё не заполнен(не хватает игроков), подождите и обновите страницу http://localhost:1111/game/validateStart"));
        return ResponseEntity.ok(new GameResponseDTO(ResponseStatus.SUCCESS, null, mapper.writeValueAsString(gameService.getMap()), BodyType.MAP));
    }
    @PostMapping(value = "/move")
    public ResponseEntity<GameResponseDTO> move(@RequestBody MoveDTO moveDTO) throws JsonProcessingException {
        String moveInfo = gameService.move(moveDTO.getNickname(), moveDTO.getX(), moveDTO.getY());
        if(moveInfo.equals("Вы успешно сделали шаг")){
            return ResponseEntity.ok(new GameResponseDTO(ResponseStatus.SUCCESS, null, mapper.writeValueAsString(gameService.getMap()), BodyType.MAP));
        }
        if(moveInfo.equals("Вы успешно сделали шаг и покушали)")){
            return ResponseEntity.ok(new GameResponseDTO(ResponseStatus.SUCCESS, null, mapper.writeValueAsString(gameService.getMap()), BodyType.MAP));
        }
        return ResponseEntity.status(400).body(new GameResponseDTO(ResponseStatus.ERROR_PLAYER_MOVE, moveInfo));

    }
}
