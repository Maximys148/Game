package com.maximys.game.dto;

import com.maximys.game.enums.BodyType;
import com.maximys.game.enums.ResponseStatus;

import java.util.List;

public class GameResponseDTO {
    private ResponseStatus status; // Используем Enum для статуса ответа
    private String message;
    private String stringJSON; // Можно заменить Object на конкретный тип данных, если необходимо
    private BodyType bodyType;
    private List<String> errors; // Список ошибок, если они есть

    // Конструкторы
    public GameResponseDTO(ResponseStatus status, String message, String data, BodyType bodyType) {
        this.status = status;
        this.message = message;
        this.stringJSON = data;
        this.bodyType = bodyType;
    }

    public GameResponseDTO(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public GameResponseDTO() {
    }

    // Геттеры и сеттеры
    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getStringJSON() {
        return stringJSON;
    }

    public void setStringJSON(String stringJSON) {
        this.stringJSON = stringJSON;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }
}
