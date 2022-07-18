package com.agon.controller;

import com.agon.dto.TournamentDTO;
import com.agon.model.Player;
import com.agon.model.Tournament;
import com.agon.service.PlayerService;
import com.agon.service.TournamentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;
    private final PlayerService playerService;

    @PostMapping("/tournament")
    public ResponseEntity setNewTournament(@RequestBody TournamentDTO tDTO) {
        Tournament tournament = new Tournament();
        tournament.setTitle(tDTO.getTitle());
        tournament.setGame(tDTO.getGame());

        List<Player> players = new ArrayList<>();
        tDTO.getPlayers().forEach(player -> {
            Player tempPlayer = new Player();
            tempPlayer.setNickName(player);
            tempPlayer.addTournament(tournament);
            playerService.savePlayer(tempPlayer);
            players.add(tempPlayer);
        });

        tournament.setPlayers(players);
        tournamentService.saveTournament(tournament);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
