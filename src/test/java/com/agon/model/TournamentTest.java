package com.agon.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TournamentTest {

    @Test
    void shuffleHappyPath() {
        //GIVEN
        Tournament tournament = new Tournament();
        List<Player> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Player p = new Player();
            p.setId(i);
            list.add(p);
        }
        tournament.setPlayers(new ArrayList<>(list));
        //WHEN
        tournament.shuffle();
        //THEN
        assertNotEquals(list, tournament.getPlayers());
        assertEquals(list.size(), tournament.getPlayers().size());
    }

    @Test
    void shuffleEmptyPlayers() {
        //GIVEN
        Tournament tournament = new Tournament();
        List<Player> list = new ArrayList<>();
        tournament.setPlayers(list);
        //WHEN
        tournament.shuffle();
        //THEN
        assertEquals(list, tournament.getPlayers());
        assertEquals(list.size(), tournament.getPlayers().size());
    }

    @Test
    void shuffleOnePlayerList() {
        //GIVEN
        Tournament tournament = new Tournament();
        List<Player> list = new ArrayList<>();
        Player p = new Player();
        p.setId(122);
        list.add(p);
        tournament.setPlayers(list);
        //WHEN
        tournament.shuffle();
        //THEN
        assertEquals(list, tournament.getPlayers());
        assertEquals(list.size(), tournament.getPlayers().size());
    }

    @Test
    void shuffleNullList() {
        //GIVEN
        Tournament tournament = new Tournament();
        tournament.setPlayers(null);
        //THEN
        assertThrows(IllegalStateException.class, tournament::shuffle);
    }
}