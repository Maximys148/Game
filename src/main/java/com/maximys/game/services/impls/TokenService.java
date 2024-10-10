package com.maximys.game.services.impls;

import com.maximys.game.entities.Token;
import com.maximys.game.repositories.TokenRepository;
import com.maximys.game.services.implsInterface.TokenServiceInterface;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class TokenService implements TokenServiceInterface {
    private final TokenRepository repository;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int DEFAULT_LENGTH = 16;
    private final int length;
    private final SecureRandom random;

    private String generateToken() {
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length()); // Генерируем случайный индекс
            token.append(CHARACTERS.charAt(index)); // Добавляем символ в токен
        }
        return token.toString(); // Возвращаем сгенерированный токен
    }


    public TokenService(TokenRepository repository) {
        this.repository = repository;
        this.length = DEFAULT_LENGTH; // Устанавливаем заданную длину токена
        this.random = new SecureRandom(); // Используем SecureRandom для повышения безопасности
    }

    public void createToken(){
        Token token = new Token();
        token.setValue(this.generateToken());
        repository.save(token);
    }
    public boolean validateToken(String token){
        return repository.findAll().contains(token);
    }
}
