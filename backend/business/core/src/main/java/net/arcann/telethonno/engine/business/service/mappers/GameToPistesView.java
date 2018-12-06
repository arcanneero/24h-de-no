package net.arcann.telethonno.engine.business.service.mappers;

import net.arcann.telethonno.persist.entity.Game;
import net.arcann.telethonno.engine.business.api.view.PisteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GameToPistesView implements Function<Game, List<PisteView>> {

    @Autowired
    private PisteToPisteView pisteToPisteView;

    @Override
    public List<PisteView> apply(final Game game) {
        final List<PisteView> resultat = new ArrayList<>();

        return game.getPistes().stream()
                .map(pisteToPisteView)
                .collect(Collectors.toList());

    }

}
