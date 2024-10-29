package com.maximys.game.dto;

import com.maximys.game.enums.ResponseStatus;

import java.util.List;

public class GameResponseDTO {
    private ResponseStatus status; // Используем Enum для статуса ответа
    private String message;
    private Object data; // Можно заменить Object на конкретный тип данных, если необходимо
    private List<String> errors; // Список ошибок, если они есть

    // Конструкторы
    public GameResponseDTO(ResponseStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public GameResponseDTO(ResponseStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
