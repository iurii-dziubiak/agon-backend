package com.agon.controller;

import com.agon.dto.InitTournamentDTO;
import com.agon.dto.OngoingTournamentDTO;
import com.agon.dto.RoundDTO;
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
    public ResponseEntity<?> getTournament(@PathVariable Integer id) {
        Tournament tournament = tournamentService.get(id); //TODO: service should return DTO
        return ResponseEntity.ok().body(OngoingTournamentDTO.fromTournament(tournament));
    }

    @PostMapping("/tournament")
    public ResponseEntity<?> createTournament(@RequestBody InitTournamentDTO initTournamentDTO) throws URISyntaxException {
        Tournament tournament = initTournamentDTO.toTournament().shuffle();
        tournamentService.save(tournament);
        tournament.getPlayers().forEach(playerService::save);
        initTournamentDTO.setId(tournament.getId());
        return ResponseEntity.created(new URI("http://localhost:8088/api/tournament/"+initTournamentDTO.getId()))
                .body(initTournamentDTO);
    }

    @PostMapping("/next-round")
    public ResponseEntity<?> createSecondRound(@RequestBody RoundDTO roundDTO) throws URISyntaxException {
        roundDTO.setChallenges();
        return ResponseEntity.ok().body(roundDTO);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public void handleProductCreateException(NoSuchElementException e) {
        log.error(e.getMessage());
    }

//    TODO: NOTES => field type Version for updates, @LastModifiedDate/By, @CreatedBy, @CreateDate, JpaAuditing
}
