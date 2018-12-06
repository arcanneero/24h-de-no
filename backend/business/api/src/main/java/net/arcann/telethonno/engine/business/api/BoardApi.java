package net.arcann.telethonno.engine.business.api;

import net.arcann.telethonno.engine.business.api.view.JoueurView;
import net.arcann.telethonno.engine.business.api.view.ResultatView;

import java.util.List;

public interface BoardApi {

    ResultatView recupererResultats();

    List<JoueurView> recupererJoueurs();

}