package com.agon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rival {
    private String name;
    private final boolean won = false;
    private final boolean lose = false;

    public Rival(String name) {
        this.name = name;
    }
}
