package com.agon.dto;
import com.agon.model.Challenge;
import com.agon.model.Rival;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class SecondRoundDTO {
    private List<Rival> rivals;
    private List<Challenge> challenges = new ArrayList<>();

    public void setChallenges() {
        shuffle();
        ArrayList<Rival> tempRivals = new ArrayList<>();
        for (Rival player: rivals) {
            if (tempRivals.size() < 2) {
                tempRivals.add(player);
            }
            if (tempRivals.size() == 2) {
                Challenge tempChallenge = new Challenge(tempRivals.get(0), tempRivals.get(1));
                challenges.add(tempChallenge);
                tempRivals.clear();
            }
        }
    }

    private void shuffle() {
        if (rivals == null) {
            throw new IllegalStateException();
        }
        Collections.shuffle(rivals);
    }
}
