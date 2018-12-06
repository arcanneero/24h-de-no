package net.arcann.telethonno.persist;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.arcann.telethonno.persist.entity.Game;
import net.arcann.telethonno.persist.entity.Joueur;
import net.arcann.telethonno.persist.entity.Piste;
import net.arcann.telethonno.persist.persist.GamePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Repository()
public class GameRepository implements GamePort {

    @Value("${file.persist.rules}")
    private File jsonFile;

    private final Game game = new Game();

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        // deserialize contents of each file into an object of type
        Game tmp = jsonMapper.readValue(jsonFile, new TypeReference<Game>() {

        });
        if (tmp != null) {
            this.game.setCagnotte(tmp.getCagnotte());
            this.game.setDistance(tmp.getDistance());
            this.game.setNbTour(tmp.getNbTour());

            this.game.getJoueurs().addAll(tmp.getJoueurs());
            this.game.getPistes().addAll(tmp.getPistes());

            if (this.game.getPistes().isEmpty()) {
                this.game.getPistes().add(new Piste(PisteEnum.UN, 0, 0f, null));
                this.game.getPistes().add(new Piste(PisteEnum.DEUX, 0, 0f, null));
                this.game.getPistes().add(new Piste(PisteEnum.TROIS, 0, 0f, null));
                this.game.getPistes().add(new Piste(PisteEnum.QUATRE, 0, 0f, null));
            }

        }
    }

    public Game get() {
        return this.game;
    }

    public Game resetPiste(Integer number) {

        synchronized (this.game) {
            this.game.setCagnotte(number);

            return  save();
        }
    }

    public Game setCagnotte(Integer number) {

        synchronized (this.game) {
            this.game.setCagnotte(number);

            return  save();
        }
    }

    public Game setJoueur(Integer numero, String nom) {

        synchronized (this.game) {

            Optional<Joueur> joueurOpt = this.game.getJoueurs().stream()
                    .filter(joueur -> joueur.getNom().equalsIgnoreCase(nom))
                    .findFirst();

            Joueur joueur = joueurOpt.orElse(new Joueur(nom, 0, 0f));

            if (!joueurOpt.isPresent()) {
                this.game.getJoueurs().add(joueur);
            }

            this.game.getPistes().stream()
                    .filter(piste -> piste.getNumero().getIndice().equals(numero))
                    .forEach(piste -> {
                        piste.setNbTour(0);
                        piste.setDistance(0f);
                        piste.setJoueur(joueur);
                    });

            return  save();
        }
    }

    public Game resetPistes() {

        synchronized (this.game) {

            this.game.getPistes().forEach(piste -> {
                        piste.setDistance(0f);
                        piste.setNbTour(0);
                    });

            return  save();
        }
    }

    public Game addTurn(Integer number){

        synchronized (this.game) {


            // Récupération de la piste
            Piste piste = this.game.getPistes().stream()
                    .filter(piste1 -> piste1.getNumero().getIndice().equals(number))
                    .findFirst().get();

            // Récupération du joueur (anonyme si non renseigné)
            Joueur joueur = this.game.getJoueurs().stream()
                    .filter(joueur1 -> piste.getJoueur()!= null && joueur1.getNom().equalsIgnoreCase(piste.getJoueur().getNom()))
                    .findFirst().orElse(
                            this.game.getJoueurs().stream()
                                    .filter(joueur1 -> joueur1.getNom().equalsIgnoreCase("Anonyme"))
                                    .findFirst().orElse(new Joueur("Anonyme", 0, 0f))
                    );

            // Mise à jour des nombre de tour
            this.game.setNbTour(this.game.getNbTour() + 1);
            piste.setNbTour(piste.getNbTour() + 1);
            joueur.setNbTour(joueur.getNbTour() + 1);

            // Mise à jour des distances
            this.game.setDistance(addDistance( this.game.getDistance(), piste.getNumero().getLongueur()));
            piste.setDistance(addDistance(piste.getDistance(), piste.getNumero().getLongueur()));
            joueur.setDistance(addDistance(joueur.getDistance(), piste.getNumero().getLongueur()));

            return  save();
        }

    }

    private Float addDistance(Float distance, Float longueur) {
        return  new BigDecimal(distance).add(new BigDecimal(longueur)).floatValue();
    }


    private Game save() {

        log.warn("Risque de perte de performance. Réécriture du fichier complet");
        persist();

        return this.game;
    }

    private void persist() {

        String url = null;

        url = jsonFile.getAbsolutePath();
        jsonFile.delete();

        try (OutputStream outputStream = new FileOutputStream(new File(url))) {
            ObjectMapper mapper = new ObjectMapper();
            JsonGenerator g = mapper.getFactory().createGenerator(outputStream);
            mapper.writeValue(g, this.game);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            log.warn("Ecriture impossible");
        }
    }
}
