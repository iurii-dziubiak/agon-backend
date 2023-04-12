package com.agon.model;

import com.agon.dto.PodiumDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

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
    private String firstPlace;
    private String secondPlace;
    private String thirdPlace;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "tournament_player",
            joinColumns = { @JoinColumn(name = "tournament_id") },
            inverseJoinColumns = { @JoinColumn(name = "player_id") }
    )
    private List<Player> players;

    public void shuffle() {
        if (players == null) {
            throw new IllegalStateException();
        }

        Collections.shuffle(players);
    }

    public void setPodium(PodiumDTO podiumDTO) {
        this.firstPlace = podiumDTO.getFirst();
        this.secondPlace = podiumDTO.getSecond();
        this.thirdPlace = podiumDTO.getThird();
    }
}
