package com.maximys.game.services.impls;

import com.maximys.game.entities.Player;
import com.maximys.game.generator.MapGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameService {
    private MapGenerator mapGenerator;
    private List<Player> players = new ArrayList<>();
    private Integer[][] map;
    @Autowired
    public GameService(MapGenerator mapGenerator, Integer[][] map) {
        this.map = mapGenerator.generateMaze();
        this.mapGenerator = new MapGenerator(10, 10);
    }

    public Integer[][] getMap() {
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
        player.setIndexMove(players.size() + 1);
        Random random = new Random();
        while (true) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            if(map[x][y] == 0){
                player.setPositionX(x);
                player.setPositionY(y);
                map[x][y] = player.getIndexMove() * 11;
                break;
            }
        }
        players.add(player);
        return true;
    }

    public List<Player> getPlayers(){
        return players;
    }
    public Object getGameInfo(){
        Object gameInfo = map + "\n" + countPlayer();
        return gameInfo;
    }
    public String move(String nickName, int x, int y){
        return "Вы сделали успешный ход, ждите своей очереди";
    }
}
