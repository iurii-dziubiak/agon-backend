package com.agon.controller;

import com.agon.model.Game;
import com.agon.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {
    private final GameService gameService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameService.getAll();
    }
}
