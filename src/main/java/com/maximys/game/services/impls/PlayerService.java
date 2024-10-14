package com.maximys.game.services.impls;

import com.maximys.game.generator.MapGenerator;
import com.maximys.game.services.implsInterface.PlayerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements PlayerServiceInterface {
    private MapGenerator mapGenerator;
    @Autowired
    public PlayerService(MapGenerator mapGenerator) {
        this.mapGenerator = new MapGenerator(10, 10);
    }
    public Object startGame(){
        char[][] chars = mapGenerator.generateMaze();
        return mapGenerator.printMap(chars);
    }
}
