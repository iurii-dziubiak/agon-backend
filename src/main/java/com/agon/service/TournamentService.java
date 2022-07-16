package com.agon.service;

import com.agon.model.Tournament;
import com.agon.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final TournamentRepository repository;

    public void saveTournament(Tournament tournament) {
        repository.save(tournament);
    }
}
