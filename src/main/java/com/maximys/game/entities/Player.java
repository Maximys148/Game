package com.maximys.game.entities;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class Player {
    private String id;
    private String nickName;
    private Integer indexMove;
    private int positionX;
    private int positionY;

    @Autowired
    public Player(String nickName) {
        this.nickName = nickName;
    }

    public Integer getIndexMove() {
        return indexMove;
    }

    public void setIndexMove(Integer indexMove) {
        this.indexMove = indexMove;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
