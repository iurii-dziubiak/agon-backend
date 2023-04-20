package com.agon.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;
    private String nickname;

    @ManyToMany(mappedBy = "players")
    private final List<Tournament> tournaments;

    public Player () {
        this.tournaments = new ArrayList<>();
    }

    public Player(String nickname) {
        this.nickname = nickname;
        this.tournaments = new ArrayList<>();
    }

    public void addTournament(Tournament tournament) {
        tournaments.add(tournament);
    }
}
