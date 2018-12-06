package net.arcann.telethonno.persist.persist;

import net.arcann.telethonno.persist.entity.Game;

public interface GamePort {

    Game addTurn(Integer numero);

    Game setCagnotte(Integer montant);

    Game resetPistes();

    Game resetPiste(Integer numero);

    Game setJoueur(Integer numero, String joueur);

    Game get();
}