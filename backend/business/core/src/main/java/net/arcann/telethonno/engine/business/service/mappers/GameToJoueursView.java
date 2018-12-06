package net.arcann.telethonno.engine.business.service.mappers;

import net.arcann.telethonno.persist.entity.Game;
import net.arcann.telethonno.engine.business.api.view.JoueurView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GameToJoueursView implements Function<Game, List<JoueurView>> {

    @Autowired
    private JoueurToJoueurView joueurToJoueurView;

    @Override
    public List<JoueurView> apply(final Game game) {
        final List<JoueurView> resultat = new ArrayList<>();

        return game.getJoueurs().stream()
                .map(joueurToJoueurView)
                .collect(Collectors.toList());

    }

}
