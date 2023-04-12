package com.agon.dto;

import com.agon.model.Challenge;
import com.agon.model.Player;
import com.agon.model.Tournament;
import lombok.Getter;

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
                .challenges(tournament.getPlayers().stream().map(Player::getNickname).collect(Collectors.toList()))
                .build();
    }

    public static class TournamentDTOBuilder
    {
        private Integer id;
        private String title;
        private String game;
        private List<Challenge> challenges;

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
        public TournamentDTOBuilder challenges(List<String> playerNames) {
            this.challenges = new RoundDTO.RoundDTOBuilder().challenges(playerNames).build().getChallenges();
            return this;
        }
        public OngoingTournamentDTO build() {
            return new OngoingTournamentDTO(this);
        }
    }
}
