package com.maximys.game.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "player")
public class Player {
    @Id
    @GenericGenerator(name = "id_player", strategy = "com.maximys.game.generator.StringIdGenerator")
    @GeneratedValue(generator = "id_player", strategy = GenerationType.AUTO)
    private String id;
    @Column(unique = true, nullable = false)
    private String nickName;
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
