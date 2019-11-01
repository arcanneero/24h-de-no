package net.arcann.telethonno.engine.business.service;

import net.arcann.telethonno.persist.entity.Game;
import net.arcann.telethonno.persist.persist.GamePort;
import net.arcann.telethonno.engine.business.api.CourseApi;
import net.arcann.telethonno.engine.business.api.events.RaceEvents;
import net.arcann.telethonno.engine.business.api.events.ResultatEvents;
import net.arcann.telethonno.engine.business.api.view.PisteView;
import net.arcann.telethonno.engine.business.api.view.ResultatView;
import net.arcann.telethonno.engine.business.service.mappers.GameToPistesView;
import net.arcann.telethonno.engine.business.service.mappers.GameToResultatView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CourseService implements CourseApi {

    private final ApplicationEventPublisher publisher;
    private final GamePort gamePort;
    private final GameToResultatView gameToResultatView;
    private final GameToPistesView gameToPistesView;

    public CourseService(
            @Autowired ApplicationEventPublisher publisher,
            @Autowired GameToResultatView gameToResultatView,
            @Autowired GameToPistesView gameToPistesView,
            @Autowired GamePort gamePort) {

        this.publisher = publisher;
        this.gamePort = gamePort;
        this.gameToResultatView = gameToResultatView;
        this.gameToPistesView = gameToPistesView;
    }

    @Override
    public PisteView nouveauTour(PisteView piste) {

        Game game = gamePort.addTurn(piste.getNumero());

        publishResultats(gameToResultatView.apply(game));
        publishCourse(gameToPistesView.apply(game));

        return piste;
    }

    @Override
    public List<PisteView> get() {

        Game game = gamePort.get();

        return gameToPistesView.apply(game);
    }

    private void publishResultats(ResultatView view) {
        publisher.publishEvent(new ResultatEvents(view));
    }

    private void publishCourse(List<PisteView> view) {
        publisher.publishEvent(new RaceEvents(view));
    }

}
