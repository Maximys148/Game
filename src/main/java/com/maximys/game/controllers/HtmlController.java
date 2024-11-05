package com.maximys.game.controllers;

import com.maximys.game.components.Player;
import com.maximys.game.services.impls.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping(value = "/main365")
public class HtmlController {
    private final GameService gameService;

    @Autowired
    public HtmlController(GameService gameService){
        this.gameService = gameService;
    }
    @GetMapping(value = "")
    public ModelAndView game(ModelAndView model){
        Integer[][] map = gameService.getMap();
        List<Player> players = gameService.getPlayers();
        model.setViewName("game");
        model.addObject("map", map);
        model.addObject("players", players);
        return model;
    }
}
