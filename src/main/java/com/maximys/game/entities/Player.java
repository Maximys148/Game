package com.maximys.game.entities;

import lombok.Data;

@Data
public class Player {
    private String id;
    private String nickName;
    private Integer indexMove;

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

}
