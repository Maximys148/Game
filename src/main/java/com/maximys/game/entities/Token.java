package com.maximys.game.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Data
@Table(name = "token")
public class Token {
    @Id
    @GenericGenerator(name = "value_token", strategy = "com.maximys.game.generator.StringIdGenerator")
    @GeneratedValue(generator = "value_token", strategy = GenerationType.AUTO)
    private String id;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
