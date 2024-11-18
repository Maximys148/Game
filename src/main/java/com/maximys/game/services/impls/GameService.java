package com.maximys.game.services.impls;

import com.maximys.game.components.Game;
import com.maximys.game.components.GameMap;
import com.maximys.game.components.Player;
import com.maximys.game.components.PlayerInGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private Game game;
    @Autowired
    public GameService(Game game) {
        this.game = game;
    }

    public GameMap getMap() {
        return game.getMap();
    }
    public Game getGame(){
        return game;
    }
    public Integer[][] printMap() {
        return game.printMap();
    }

    public Integer countPlayer(){
        return game.getCountPlayers();
    }
    public boolean addPlayer(String nickname){
        return game.addPlayer(nickname);
    }

    public List<Player> getPlayers(){
        return game.getPlayers();
    }
    public PlayerInGame getGameInfo(String nickName){
        return game.getGameInfo(nickName);
    }
    public Player getPlayer(String nickName){
        return game.getPlayer(nickName);
    }
    public String move(String nickName, int newX, int newY) {
        return game.move(nickName, newX, newY);
    }
}
