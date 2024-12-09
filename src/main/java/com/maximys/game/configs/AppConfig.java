package com.maximys.game.configs;

import com.maximys.game.components.Game;
import com.maximys.game.components.GameMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    public AppConfig() {
    }
    @Bean
    public Game game(){
        return new Game(new GameMap(20, 20, 2));
    }
}