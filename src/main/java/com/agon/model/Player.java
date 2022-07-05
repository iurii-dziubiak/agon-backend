package com.agon.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String nickName;

    @ManyToMany(mappedBy = "players")
    private List<Tournament> tournaments;
}
