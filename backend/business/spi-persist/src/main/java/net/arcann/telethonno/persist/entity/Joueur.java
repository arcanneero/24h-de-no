package net.arcann.telethonno.persist.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Joueur {

    private String nom;

    private Integer nbTour = 0;

    private Float distance = 0f;

}