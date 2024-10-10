package com.maximys.game.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

public class StringIdGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        String newId = String.valueOf(Math.random());
        UUID uuid = UUID.nameUUIDFromBytes(newId.getBytes(StandardCharsets.UTF_8));
        return uuid.toString().toUpperCase();
    }
    /*
     1. Создаётся сам сервер и его сокет.
     2. Генерируется бд с токенами(Ключом доступа) который привзан к пользователю
     3. Создаётся сам игрока (Player) который делает REST запрос на сервер на регистрацию
     4. Сервер проверяет игрока на наличие токена и в случае успеха регистрирует его в бд Player сообщает об этом пользователю
     5. Пользователь делает REST запрос на подключение к игре.
     6. Сервер проверяет чтобы у пользователя была карта и закидывает его в сессию с игрой(не знаю что насчёт сессии и как её реализовать)
     7. Игроки подключаются к одной игре и играют, а сервер проверяет их действия(управляет игрой) и завершают её.
     */

}
