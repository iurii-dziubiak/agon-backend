package com.agon.dto;

import com.agon.model.Challenge;
import com.agon.model.Player;
import com.agon.model.Tournament;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OngoingTournamentDTO {
    private final Integer id;
    private final String title;
    private final String game;
    private final List<Challenge> challenges;

    private OngoingTournamentDTO(TournamentDTOBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.game = builder.game;
        this.challenges = builder.challenges;
    }

    public static OngoingTournamentDTO fromTournament(Tournament tournament) {
        return new TournamentDTOBuilder()
                .id(tournament.getId())
                .title(tournament.getTitle())
                .game(tournament.getGame())
                .challenges(tournament.getPlayers().stream().map(Player::getNickName).collect(Collectors.toList()))
                .build();
    }

    public static class TournamentDTOBuilder
    {
        private Integer id;
        private String title;
        private String game;
        private final List<Challenge> challenges = new ArrayList<>();

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
        public TournamentDTOBuilder challenges(List<String> players) {
            ArrayList<String> tempRivals = new ArrayList<>();
            for (String player: players) {
                if (tempRivals.size() < 2) {
                    tempRivals.add(player);
                }
                if (tempRivals.size() == 2) {
                    Challenge tempChallenge = new Challenge(tempRivals.get(0), tempRivals.get(1));
                    challenges.add(tempChallenge);
                    tempRivals.clear();
                }
            }
            return this;
        }
        public OngoingTournamentDTO build() {
            return new OngoingTournamentDTO(this);
        }
    }
}
