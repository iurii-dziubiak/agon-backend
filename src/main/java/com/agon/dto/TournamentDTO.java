package com.agon.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class TournamentDTO {
    private final String title;
    private final String game;
    private final List<String> players;
}
