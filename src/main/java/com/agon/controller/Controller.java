package com.agon.controller;

import com.agon.dto.TournamentDTO;
import com.agon.model.Game;
import com.agon.model.Player;
import com.agon.model.Tournament;
import com.agon.service.GameService;
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
public class Controller {
    private final GameService gService;
    private final TournamentService tService;
    private final PlayerService pService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gService.getAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/tournament")
    public ResponseEntity setNewTournament(@RequestBody TournamentDTO tDTO) {
        Tournament tournament = new Tournament();
        tournament.setTitle(tDTO.getTitle());
        tournament.setGame(tDTO.getGame());

        List<Player> players = new ArrayList<>();
        tDTO.getPlayers().forEach(player -> {
            Player tempPlayer = new Player();
            tempPlayer.setNickName(player);
            tempPlayer.setTournaments(new ArrayList<>());
            tempPlayer.addTournament(tournament);
            pService.savePlayer(tempPlayer);
            players.add(tempPlayer);
        });

        tournament.setPlayers(players);
        tService.saveTournament(tournament);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
