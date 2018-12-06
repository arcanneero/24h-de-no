package net.arcann.telethonno.engine.business.service;

import net.arcann.telethonno.persist.entity.Game;
import net.arcann.telethonno.persist.persist.GamePort;
import net.arcann.telethonno.engine.business.api.AdminApi;
import net.arcann.telethonno.engine.business.api.events.HallEvents;
import net.arcann.telethonno.engine.business.api.events.RaceEvents;
import net.arcann.telethonno.engine.business.api.events.ResultatEvents;
import net.arcann.telethonno.engine.business.api.view.JoueurView;
import net.arcann.telethonno.engine.business.api.view.PisteView;
import net.arcann.telethonno.engine.business.api.view.ResultatView;
import net.arcann.telethonno.engine.business.service.mappers.GameToJoueursView;
import net.arcann.telethonno.engine.business.service.mappers.GameToPistesView;
import net.arcann.telethonno.engine.business.service.mappers.GameToResultatView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class AdminService implements AdminApi {


    private final ApplicationEventPublisher publisher;
    private final GamePort gamePort;
    private final GameToResultatView gameToResultatView;
    private final GameToPistesView gameToPistesView;
    private final GameToJoueursView gameToJoueursView;

    public AdminService(
            @Autowired ApplicationEventPublisher publisher,
            @Autowired GameToResultatView gameToResultatView,
            @Autowired GameToPistesView gameToPistesView,
            @Autowired GameToJoueursView gameToJoueursView,
            @Autowired GamePort gamePort) {

        this.publisher = publisher;
        this.gamePort = gamePort;
        this.gameToResultatView = gameToResultatView;
        this.gameToPistesView = gameToPistesView;
        this.gameToJoueursView = gameToJoueursView;
    }

    @Override
    public void metAJourLeMontant(Integer montant) {

        Game game = gamePort.setCagnotte(montant);
        publishResultats(gameToResultatView.apply(game));

    }

    @Override
    public void initialiserPiste(PisteView piste) {

        Game game = gamePort.resetPiste(piste.getNumero());
        publishCourse(gameToPistesView.apply(game));

    }

    @Override
    public void initialiserCourse() {

        Game game = gamePort.resetPistes();
        publishCourse(gameToPistesView.apply(game));

    }

    @Override
    public void renseignerUnJoueur(PisteView piste, String joueur) {

        Game game = gamePort.setJoueur(piste.getNumero(), joueur);

        publishCourse(gameToPistesView.apply(game));
        publishHall(gameToJoueursView.apply(game));

    }

    private void publishResultats(ResultatView view) {
        publisher.publishEvent(new ResultatEvents(view));
    }

    private void publishCourse(List<PisteView> view) {
        publisher.publishEvent(new RaceEvents(view));
    }

    private void publishHall(List<JoueurView> view) {
        publisher.publishEvent(new HallEvents(view));
    }
}
