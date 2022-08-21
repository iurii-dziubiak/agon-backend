package com.agon.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Rival {
    private final String name;
    private final boolean isWon = false;
}
