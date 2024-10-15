package com.maximys.game.services.impls;

import com.maximys.game.entities.Game;
import com.maximys.game.entities.Player;
import com.maximys.game.generator.MapGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private MapGenerator mapGenerator;
    private List<Player> players = new ArrayList<>();
    private Integer[][] map;
    @Autowired
    public GameService(MapGenerator mapGenerator, Integer[][] map) {
        this.map = map;
        this.mapGenerator = new MapGenerator(10, 10);
    }

    public Integer[][] createMap(){
        Integer[][] newMap = mapGenerator.generateMaze();
        map = newMap;
        return map;
    }
    public Integer countPlayer(){
        return players.size();
    }
    public boolean addPlayer(String nickname){
        for (Player player: players) {
            if(player.getNickName().equals(nickname))
                return false;
        }
        Player player = new Player(nickname);
        player.setIndexMove(players.size());
        players.add(player);
        return true;
    }

    public List<Player> getPlayers(){
        return players;
    }
    public Object getGameInfo(){
        Integer[][] map = this.createMap();
        Object gameInfo = map + "\n" + countPlayer();
        return gameInfo;
    }
}
