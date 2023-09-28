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

        List<Player> playerList = new ArrayList<>();
        this.players.forEach(playerName -> { //TODO refactor
            Player tempPlayer = new Player(playerName);
            tempPlayer.addTournament(tournament);
            playerList.add(tempPlayer);
        });

        tournament.setTitle(this.title);
        tournament.setGame(this.game);
        tournament.setPlayers(playerList);
        return tournament;
    }
}
