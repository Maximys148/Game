package com.maximys.game.services.impls;

import com.maximys.game.entities.Token;
import com.maximys.game.repositories.TokenRepository;
import com.maximys.game.services.implsInterface.TokenServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class TokenService implements TokenServiceInterface {
    private final TokenRepository repository;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final SecureRandom random;

    @Autowired
    public TokenService(TokenRepository repository, SecureRandom random){
        this.repository = repository;
        this.random = random;
    }

    private String generateToken() {
        StringBuilder token = new StringBuilder(20);
        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(CHARACTERS.length()); // Генерируем случайный индекс
            token.append(CHARACTERS.charAt(index)); // Добавляем символ в токен
        }
        return token.toString(); // Возвращаем сгенерированный токен
    }

    public void createToken(){
        Token token = new Token();
        token.setValue(this.generateToken());
        repository.save(token);
    }
    public boolean validateToken(String token){
        return repository.findAll().stream()
                .anyMatch(t -> t.getValue().equals(token));
    }

}
