package com.maximys.game.components;

import lombok.Data;

@Data
public class PlayerInGame {
    private Player player;
    private Game game;

    public PlayerInGame(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
