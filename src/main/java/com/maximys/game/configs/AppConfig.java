package com.maximys.game.configs;

import com.maximys.game.entities.Game;
import com.maximys.game.generator.MapGenerator;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class AppConfig {
    private final MapGenerator mapGenerator;
    Integer[][] generatedMap;

    public AppConfig() {
        this.mapGenerator = new MapGenerator(20, 20, 5);
    }

    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }
    @Bean
    public MapGenerator mapGenerator(){
        return this.mapGenerator;
    }
    @PostConstruct
    public void init(){
        this.generatedMap = mapGenerator.generateMaze();
    }
    @Bean
    public Game game(){
        return new Game(null, generatedMap);
    }
}