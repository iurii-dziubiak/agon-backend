package com.agon.service;

import com.agon.model.Player;
import com.agon.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository repository;

    public void savePlayer(Player player) {
        repository.save(player);
    }
}
