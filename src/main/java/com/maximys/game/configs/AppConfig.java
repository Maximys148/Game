package com.maximys.game.configs;

import com.maximys.game.entities.Game;
import com.maximys.game.generator.MapGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class AppConfig {
    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }
    @Bean
    public MapGenerator mapGenerator(){
        return new MapGenerator(20,20,20);
    }
    @Bean
    public Game game(){
        return new Game(null, null);
    }
}