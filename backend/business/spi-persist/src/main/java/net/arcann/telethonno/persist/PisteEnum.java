package net.arcann.telethonno.persist;

public enum PisteEnum {

    UN (1, 13.70f),
    DEUX (2, 13.14f),
    TROIS (3, 12.57f),
    QUATRE (4, 12.01f);

    private final Float longueur;

    private final Integer indice;

    PisteEnum(Integer indice, Float longueur) {
        this.longueur = longueur;
        this.indice = indice;
    }

    public Float getLongueur() {
        return longueur;
    }

    public Integer getIndice() {
        return indice;
    }
}
