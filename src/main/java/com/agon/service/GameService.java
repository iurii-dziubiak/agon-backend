package com.agon.service;

import com.agon.model.Game;
import com.agon.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public List<Game> getAll() {
        return gameRepository.findAll();
    }
}
