package com.agon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String title;
    private String game;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "tournament_player",
            joinColumns = { @JoinColumn(name = "tournament_id") },
            inverseJoinColumns = { @JoinColumn(name = "player_id") }
    )
    private List<Player> players;

    public Tournament shuffle() {
        if (players == null) {
            throw new IllegalStateException();
        }
        //FIXME Collections.shuffle(players);
        players = players.stream()
                .sorted((player1, player2) -> new Random().nextInt(3) - 1)
                .collect(Collectors.toList());
        return this;
    }
}
