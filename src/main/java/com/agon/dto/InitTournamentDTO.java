package com.agon.dto;

import com.agon.model.Player;
import com.agon.model.Tournament;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InitTournamentDTO {
    private Integer id;
    private String title;
    private String game;
    private List<String> players;

    public Tournament toTournament() {
        Tournament tournament = new Tournament();
        tournament.setTitle(getTitle());
        tournament.setGame(getGame());

        List<Player> players = new ArrayList<>();
        getPlayers().forEach(player -> {
            Player tempPlayer = new Player();
            tempPlayer.setNickName(player);
            tempPlayer.addTournament(tournament);
            players.add(tempPlayer);
        });
        tournament.setPlayers(players);

        return tournament;
    }
}
