package com.agon.dto;
import com.agon.model.Rival;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PreRoundDTO {
    private List<Rival> rivals;
}
