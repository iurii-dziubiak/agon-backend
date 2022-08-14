package com.agon.service;

import com.agon.model.Tournament;
import com.agon.repository.TournamentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TournamentServiceIT {

    @Autowired
    TournamentService sut;

    @Autowired
    TournamentRepository repository;

    @Test
    void save() {
        //GIVEN
        Tournament tournament = new Tournament();
        tournament.setTitle("My new tournament #3");
        //WHEN
        Tournament result = sut.save(tournament);
        //THEN
        Optional<Tournament> optionalTournament = repository.findById(result.getId());
        assertThat(optionalTournament).isPresent();
        assertThat(result.getTitle()).isEqualTo(optionalTournament.get().getTitle());
    }
}