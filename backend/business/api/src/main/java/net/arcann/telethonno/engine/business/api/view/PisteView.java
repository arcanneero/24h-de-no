package net.arcann.telethonno.engine.business.api.view;

import lombok.Data;

@Data
public class PisteView {

    private Integer numero;

    private Integer nbTour;

    private Float distance;

    private JoueurView joueur;

}