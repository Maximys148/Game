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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/game")
public class GameController {
    Logger logger = LoggerFactory.getLogger(GameController.class);
    private final GameService gameService;
    private final ObjectMapper mapper;

    @Autowired
    public GameController(GameService gameService, ObjectMapper mapper){
        this.gameService = gameService;
        this.mapper = mapper;
    }
    @PostMapping(value = "/registration")
    public ResponseEntity<GameResponseDTO> registration(@RequestBody RegistrationDTO registrationDTO){
        logger.info("Пользователь " + registrationDTO.getNickName() + " пытается зарегистрироваться на сервер");
        if(!gameService.addPlayer(registrationDTO.getNickName())) {
            GameResponseDTO gameResponseDTO = new GameResponseDTO(ResponseStatus.ERROR_PLAYER_ALREADY_EXISTS, "Игрок с таким никнеймом уже существует");
            logger.info("Пользователю " + registrationDTO.getNickName() + " не одобрена регистрация по причине - " + gameResponseDTO.getMessage());
            return ResponseEntity.status(400).body(gameResponseDTO);
        }
        logger.info("Пользователь " + registrationDTO.getNickName() + " зарегистрирован");
        return ResponseEntity.ok(new GameResponseDTO(ResponseStatus.SUCCESS, "Вы зарегистрированы!"));
    }

    @PostMapping(value = "/getGameInfo")
    public ResponseEntity<GameResponseDTO> getGameInfo(@RequestBody RegistrationDTO registrationDTO) throws JsonProcessingException {
        logger.info("Игроку " + registrationDTO.getNickName() + " выдана информация об игре");
        return ResponseEntity.ok(new GameResponseDTO( ResponseStatus.SUCCESS, null, mapper.writeValueAsString(gameService.getGame()), BodyType.GAME));
    }

    @PostMapping(value = "/validateStart")
    public ResponseEntity<GameResponseDTO> updateGameInfo(@RequestBody RegistrationDTO registrationDTO) throws JsonProcessingException {
        if(gameService.countPlayer() <= 1) {
            GameResponseDTO gameResponseDTO = new GameResponseDTO(ResponseStatus.ERROR_NOT_ENOUGH_PLAYERS, "Сервер ещё не заполнен(не хватает игроков), подождите и обновите страницу http://localhost:1111/game/validateStart");
            logger.info("Игроку " + registrationDTO.getNickName() + " не присоединился к игре по причине - " + gameResponseDTO.getMessage());
            return ResponseEntity.status(400).body(gameResponseDTO);
        }
        logger.info("Игрок " + registrationDTO.getNickName() + " присоединился к игре");
        return ResponseEntity.ok(new GameResponseDTO(ResponseStatus.SUCCESS, null, mapper.writeValueAsString(gameService.getMap()), BodyType.MAP));
    }
    @PostMapping(value = "/move")
    public ResponseEntity<GameResponseDTO> move(@RequestBody MoveDTO moveDTO) throws JsonProcessingException {
        String moveInfo = gameService.move(moveDTO.getNickname(), moveDTO.getX(), moveDTO.getY());
        if(moveInfo.equals("Вы успешно сделали шаг")){
            logger.info("Игрок " + moveDTO.getNickname() + " " + moveInfo);
            return ResponseEntity.ok(new GameResponseDTO(ResponseStatus.SUCCESS, null, mapper.writeValueAsString(gameService.getMap()), BodyType.MAP));
        }
        if(moveInfo.equals("Вы успешно сделали шаг и покушали)")){
            logger.info("Игрок " + moveDTO.getNickname() + " "  + moveInfo);
            return ResponseEntity.ok(new GameResponseDTO(ResponseStatus.SUCCESS, null, mapper.writeValueAsString(gameService.getMap()), BodyType.MAP));
        }
        logger.info("Игрок " + moveDTO.getNickname() + " ваш ход не защитан по причине - " + moveInfo);
        return ResponseEntity.status(400).body(new GameResponseDTO(ResponseStatus.ERROR_PLAYER_MOVE, moveInfo));
    }
}
