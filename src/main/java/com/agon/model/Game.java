package com.agon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "games")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Game {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;
    private String description;
    private String image;

    public Game(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
