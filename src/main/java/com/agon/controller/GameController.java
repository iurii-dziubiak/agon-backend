package com.agon.controller;

import com.agon.model.Game;
import com.agon.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GameController {
    private final GameService gService;

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gService.getAll();
    }
}
