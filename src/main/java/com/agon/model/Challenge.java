package com.agon.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Challenge {
    private final List<Rival> rivals;
    private final boolean isPlayed = false;

    public Challenge(String rival1, String rival2) {
        rivals = new ArrayList<>(Arrays.asList(new Rival(rival1), new Rival(rival2)));
    }
}
