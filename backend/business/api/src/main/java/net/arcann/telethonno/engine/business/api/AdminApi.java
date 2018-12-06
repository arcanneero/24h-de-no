package net.arcann.telethonno.engine.business.api;

import net.arcann.telethonno.engine.business.api.view.PisteView;

public interface AdminApi {

    void metAJourLeMontant(Integer montant);

    void initialiserPiste(PisteView piste);

    void initialiserCourse();

    void renseignerUnJoueur(PisteView piste, String joueur);

}