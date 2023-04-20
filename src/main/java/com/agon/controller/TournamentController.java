package com.agon.controller;

import com.agon.dto.*;
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

    @GetMapping("/ongoing/tournament/{id}")
    public ResponseEntity<?> getOngoingTournament(@PathVariable Integer id) {
        return ResponseEntity.ok().body(OngoingTournamentDTO.fromTournament(tournamentService.get(id)));
    }

    @PostMapping("/tournament")
    public ResponseEntity<?> createTournament(@RequestBody InitTournamentDTO initTournamentDTO) throws URISyntaxException {
        Tournament tournament = initTournamentDTO.toTournament();
        tournamentService.save(tournament);
        tournament.getPlayers().forEach(playerService::save); //TODO refactor?
        initTournamentDTO.setId(tournament.getId());
        return ResponseEntity.created(new URI("http://localhost:8088/api/tournament/"+initTournamentDTO.getId()))
                .body(initTournamentDTO);
    }

    @PostMapping("/next-round")
    public ResponseEntity<?> createNextRound(@RequestBody PreRoundDTO preRoundDTO) {
        return ResponseEntity.ok().body(RoundDTO.from(preRoundDTO));
    }

    @PostMapping("/podium")
    public ResponseEntity<?> setPodium(@RequestBody PodiumDTO podiumDTO) {
        Tournament tournament = tournamentService.get(Integer.parseInt(podiumDTO.getId()));
        tournament.setPodium(podiumDTO);
        tournamentService.save(tournament);
        return ResponseEntity.ok().body(podiumDTO.getId());
    }

    @GetMapping("/complete/tournament/{id}")
    public ResponseEntity<?> getCompleteTournament(@PathVariable Integer id) {
        return ResponseEntity.ok().body(CompleteTournamentDTO.fromTournament(tournamentService.get(id)));
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public void handleProductCreateException(NoSuchElementException e) {
        log.error(e.getMessage());
    }

}
