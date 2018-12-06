package net.arcann.telethonno.engine.business.service;

import net.arcann.telethonno.persist.entity.Game;
import net.arcann.telethonno.persist.persist.GamePort;
import net.arcann.telethonno.engine.business.api.BoardApi;
import net.arcann.telethonno.engine.business.api.view.JoueurView;
import net.arcann.telethonno.engine.business.api.view.ResultatView;
import net.arcann.telethonno.engine.business.service.mappers.GameToJoueursView;
import net.arcann.telethonno.engine.business.service.mappers.GameToResultatView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BoardService implements BoardApi {


    private final GamePort gamePort;
    private final GameToResultatView gameToResultatView;
    private final GameToJoueursView gameToJoueursView;

    public BoardService(
            @Autowired GameToResultatView gameToResultatView,
            @Autowired GameToJoueursView gameToJoueursView,
            @Autowired GamePort gamePort) {

        this.gamePort = gamePort;
        this.gameToResultatView = gameToResultatView;
        this.gameToJoueursView = gameToJoueursView;
    }

    @Override
    public ResultatView recupererResultats() {

        Game game = gamePort.get();

        return gameToResultatView.apply(game);
    }

    @Override
    public List<JoueurView> recupererJoueurs() {

        Game game = gamePort.get();

        return gameToJoueursView.apply(game);
    }
}
