package com.maximys.game.entities;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class Game {
    private List<Player> players;
    private Integer[][] map;
    private Integer indexMove;

    @Autowired
    public Game(List<Player> players, Integer[][] map) {
        this.players = players;
        this.map = map;
        this.indexMove = 0;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Integer[][] getMap() {
        return map;
    }

    public void setMap(Integer[][] map) {
        this.map = map;
    }

    public Integer getIndexMove() {
        return indexMove;
    }

    public void setIndexMove(Integer indexMove) {
        this.indexMove = indexMove;
    }
}
