package net.arcann.telethonno.engine.business.service.mappers;

import net.arcann.telethonno.persist.entity.Joueur;
import net.arcann.telethonno.engine.business.api.view.JoueurView;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JoueurToJoueurView implements Function<Joueur, JoueurView> {

    @Override
    public JoueurView apply(final Joueur joueur) {
        final JoueurView resultat = new JoueurView();

        resultat.setNbTour(joueur.getNbTour());
        resultat.setDistance(joueur.getDistance());
        resultat.setNom(joueur.getNom());

        return resultat;
    }

}
