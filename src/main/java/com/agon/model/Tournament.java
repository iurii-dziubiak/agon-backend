package com.agon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
}
