package com.maximys.game.entities;

import lombok.Data;

import java.util.List;

@Data
public class Game {
    private List<Player> players;
    private Integer[][] map;

    public Game(List<Player> players, Integer[][] map) {
        this.players = players;
        this.map = map;
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
}
