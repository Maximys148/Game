package com.maximys.game.components;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Component
public class Game {
    private List<Player> players;
    private Integer[][] map;
    private Integer indexMove;

    @Autowired
    public Game(Integer[][] map) {
        this.players = new ArrayList<Player>();
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

    public Player getPlayer(String nickName) {
        Player player = null;
        for (Player randomplayer : players) {
            if (randomplayer.getNickName().equals(nickName)) {
                player = randomplayer;
                break;
            }
        }
        return player;
    }
    public Integer getCountPlayers(){
        return players.size();
    }
    public Integer[][] printMap() {
        for(Integer[] str : map){
            System.out.println();
            for (Integer symbol : str)
                System.out.printf("%4d", symbol);
        }
        return map;
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
            player.setIndexMove(0);
        }else {
            player.setIndexMove(players.size());
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
    public PlayerInGame getGameInfo(String nickName){
        Player player = null;
        for (Player randomplayer : players) {
            if (randomplayer.getNickName().equals(nickName)) {
                player = randomplayer;
                break;
            }
        }
        PlayerInGame playerInGame = new PlayerInGame(player, this);
        return playerInGame;
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
        return "Вы успешно сделали шаг";
    }
}
