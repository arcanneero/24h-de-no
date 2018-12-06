package net.arcann.telethonno.persist.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {

    private Integer cagnotte = 0;

    private Integer nbTour = 0;

    private Float distance = 0f;

    private final List<Piste> pistes = new ArrayList<>();

    private final List<Joueur> joueurs = new ArrayList<>();

}