package net.arcann.telethonno.engine.business.api.view;


import lombok.Data;

@Data
public class JoueurView {

    private String nom;

    private Integer nbTour;

    private Float distance;

}