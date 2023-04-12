package com.agon.dto;
import com.agon.model.Challenge;
import com.agon.model.Rival;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RoundDTO {
    private final List<Challenge> challenges;

    private RoundDTO(RoundDTOBuilder builder) {
        this.challenges = builder.challenges;
    }

    public static RoundDTO from(PreRoundDTO preRoundDTO) {
        return new RoundDTOBuilder()
                .challenges(preRoundDTO.getRivals().stream().map(Rival::getName).collect(Collectors.toList()))
                .build();
    }

    public static class RoundDTOBuilder
    {
        private final List<Challenge> challenges = new ArrayList<>();

        public RoundDTOBuilder challenges(List<String> rivalNames) {
            Collections.shuffle(rivalNames);
            ArrayList<String> tempList = new ArrayList<>();

            for (String name: rivalNames) {
                if (tempList.size() < 2) {
                    tempList.add(name);
                }
                if (tempList.size() == 2) {
                    Challenge tempChallenge = new Challenge(tempList.get(0), tempList.get(1));
                    challenges.add(tempChallenge);
                    tempList.clear();
                }
            }
            return this;
        }

        public RoundDTO build() {
            return new RoundDTO(this);
        }
    }
}
