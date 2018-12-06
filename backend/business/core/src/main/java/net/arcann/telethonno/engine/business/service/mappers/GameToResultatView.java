package net.arcann.telethonno.engine.business.service.mappers;

import net.arcann.telethonno.persist.entity.Game;
import net.arcann.telethonno.engine.business.api.view.ResultatView;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GameToResultatView implements Function<Game, ResultatView> {

    @Override
    public ResultatView apply(final Game game) {
        final ResultatView resultat = new ResultatView();

        resultat.setNbTour(game.getNbTour());
        resultat.setDistance(game.getDistance());
        resultat.setCagnotte(game.getCagnotte());

        return resultat;
    }

}
