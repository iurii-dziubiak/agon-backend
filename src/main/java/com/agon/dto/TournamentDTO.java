package com.agon.dto;

import com.agon.model.Player;
import com.agon.model.Tournament;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class TournamentDTO {
    private Integer id;
    private final String title;
    private final String game;
    private final List<String> players;

    private TournamentDTO(TournamentDTOBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.game = builder.game;
        this.players = builder.players;
    }

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

    public static TournamentDTO fromTournament(Tournament tournament) {
        TournamentDTO tournamentDTO = new TournamentDTO.TournamentDTOBuilder()
                .id(tournament.getId())
                .title(tournament.getTitle())
                .game(tournament.getGame())
                .players(tournament.getPlayers().stream().map(Player::getNickName).collect(Collectors.toList()))
                .build();
        return tournamentDTO;
    }

    public static class TournamentDTOBuilder
    {
        private Integer id;
        private String title;
        private String game;
        private List<String> players;

        public TournamentDTOBuilder id(int id) {
            this.id = id;
            return this;
        }
        public TournamentDTOBuilder title(String title) {
            this.title = title;
            return this;
        }
        public TournamentDTOBuilder game(String game) {
            this.game = game;
            return this;
        }
        public TournamentDTOBuilder players(List<String> players) {
            this.players = players;
            return this;
        }
        public TournamentDTO build() {
            return new TournamentDTO(this);
        }
    }
}
