package net.arcann.telethonno.persist.entity;

import net.arcann.telethonno.persist.PisteEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Piste {

    private PisteEnum numero;

    private Integer nbTour = 0;

    private Float distance = 0f;

    private Joueur joueur;

}