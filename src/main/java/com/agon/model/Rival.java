package com.agon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rival {
    private String name;
    private boolean won = false;

    public Rival(String name) {
        this.name = name;
    }
}
