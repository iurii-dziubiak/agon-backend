package com.agon.dto;

import com.agon.model.Tournament;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompleteTournamentDTO {
    private final Integer id;
    private final String title;
    private final String game;
    private final String firstPlace;
    private final String secondPlace;
    private final String thirdPlace;

    public static CompleteTournamentDTO fromTournament(Tournament tournament) {
        return CompleteTournamentDTO.builder()
                .id(tournament.getId())
                .title(tournament.getTitle())
                .game(tournament.getGame())
                .firstPlace(tournament.getFirstPlace())
                .secondPlace(tournament.getSecondPlace())
                .thirdPlace(tournament.getThirdPlace())
                .build();
    }
}
