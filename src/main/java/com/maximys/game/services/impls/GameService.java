package com.maximys.game.services.impls;

import com.maximys.game.entities.Game;
import com.maximys.game.entities.Player;
import com.maximys.game.generator.MapGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameService {
    private Game game;
    private Integer[][] map;
    private List<Player> players;
    private Integer indexMove;
    @Autowired
    public GameService(Game game) {
        this.game = game;
        this.map = game.getMap();
        this.players = game.getPlayers();
        this.indexMove = game.getIndexMove();
    }

    public Integer[][] getMap() {
        return map;
    }
    public Integer countPlayer(){
        return players.size();
    }
    public boolean addPlayer(String nickname){
        if(players != null) {
            for (Player player : players) {
                if (player.getNickName().equals(nickname))
                    return false;
            }
        }
        Player player = new Player(nickname);
        if (players == null){
            player.setIndexMove(1);
        }else {
            player.setIndexMove(players.size() + 1);
        }
        Random random = new Random();
        //выдаю игроку стартовую позицию
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
    public String move(String nickName, int newX, int newY) {
        Player player = null;
        for (Player randomplayer : players) {
            if (randomplayer.getNickName().equals(nickName)) {
                player = randomplayer;
                break;
            }
        }
        if (player == null)
            return "Такого игрока нет в игре";
        if(player.getIndexMove() != indexMove % players.size()){
            return "Не ваша очередь";
        }
        indexMove++;
        int x = player.getPositionX();
        int y = player.getPositionY();
        if (newX < 0 || newX >= map.length || newY < 0 || newY >= map[0].length) {
            return "Выход за границы карты";
        }
        // Проверяем, что движение разрешено (т.е. только на одну клетку вверх, вниз, влево или вправо)
        int deltaX = Math.abs(x - newX);
        int deltaY = Math.abs(y - newY);
        if ((deltaX + deltaY) != 1) {
            return "Недопустимое движение. Вы можете двигаться только на одну клетку.";
        }
        if(map[newX][newY] == 5){
            Integer countFood = player.getCountFood();
            player.setCountFood(countFood + 1);
        }
        if (map[newX][newY] != 0){
            return "На этом месте есть препятствие или игрок";
        }
        //После успешного прохождения всех условий обновляем карту и позицию игрока в его параметрах
        map[x][y] = 0;
        player.setPositionX(newX);
        player.setPositionY(newY);
        map[newX][newY] = player.getIndexMove() * 11;
        return "Вы успешно походили";
    }
}
