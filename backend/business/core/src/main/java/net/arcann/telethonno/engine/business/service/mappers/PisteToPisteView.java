package net.arcann.telethonno.engine.business.service.mappers;

import net.arcann.telethonno.persist.entity.Piste;
import net.arcann.telethonno.engine.business.api.view.PisteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PisteToPisteView implements Function<Piste, PisteView> {

    @Autowired
    private JoueurToJoueurView joueurToJoueurView;

    @Override
    public PisteView apply(final Piste piste) {
        final PisteView resultat = new PisteView();

        resultat.setNumero(piste.getNumero().getIndice());
        resultat.setNbTour(piste.getNbTour());
        resultat.setDistance(piste.getDistance());
        resultat.setJoueur(joueurToJoueurView.apply(piste.getJoueur()));

        return resultat;
    }

}
