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
@AllArgsConstructor
@Entity(name = "tournaments")
public class Tournament {
    @Id
    @Column(name="")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String title;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "player_tournament",
            joinColumns = { @JoinColumn(name = "player_id") },
            inverseJoinColumns = { @JoinColumn(name = "tournament_id") }
    )
    private List<Player> players;
}
