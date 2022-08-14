package com.agon.controller;

import com.agon.dto.TournamentDTO;
import com.agon.model.Tournament;
import com.agon.service.PlayerService;
import com.agon.service.TournamentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;
    private final PlayerService playerService;

    @GetMapping("/tournament/{id}")
    ResponseEntity<?> getTournament(@PathVariable Integer id) {
        Tournament tournament = tournamentService.get(id).shuffle();
        return ResponseEntity.ok().body(TournamentDTO.fromTournament(tournament));
    }

    @PostMapping("/tournament")
    public ResponseEntity setNewTournament(@RequestBody TournamentDTO tDTO) throws URISyntaxException {
        Tournament tournament = tDTO.toTournament();
        tournamentService.save(tournament);
        tournament.getPlayers().forEach(playerService::save);
        tDTO.setId(tournament.getId());
        return ResponseEntity.created(new URI("http://localhost:8088/api/tournament/"+tDTO.getId())).body(tDTO);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public void handleProductCreateException(NoSuchElementException e) {
        log.error(e.getMessage());
    }
}
