package net.arcann.telethonno.endpoint.rest.endpoint;

import lombok.extern.slf4j.Slf4j;
import net.arcann.telethonno.endpoint.model.JoueurModel;
import net.arcann.telethonno.engine.business.api.AdminApi;
import net.arcann.telethonno.engine.business.api.CourseApi;
import net.arcann.telethonno.engine.business.api.view.PisteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Slf4j
@Component
@Path("/pistes")
public class PisteEndpoint {


    private final CourseApi courseApi;
    private final AdminApi adminApi;

    private PisteEndpoint(
            @Autowired CourseApi courseApi,
            @Autowired AdminApi adminApi) {

        this.courseApi = courseApi;
        this.adminApi = adminApi;
    }


    @Path("")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void reset() {

        adminApi.initialiserCourse();
    }

    @Path("/{ligne}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void reset(@PathVariable("ligne") Integer ligne) {

        PisteView piste = new PisteView();
        piste.setNumero(ligne);

        adminApi.initialiserPiste(piste);

    }

    @Path("/{ligne}/joueur")
    @POST
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void nouveauJoueur(@PathVariable("ligne") Integer ligne, String nom) {

        PisteView piste = new PisteView();
        piste.setNumero(ligne);

        adminApi.renseignerUnJoueur(piste, nom);

    }

    @Path("/{ligne}")
    @POST
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void nouveauTour(@PathVariable("ligne") Integer ligne) {

        PisteView piste = new PisteView();
        piste.setNumero(ligne);

        courseApi.nouveauTour(piste);

    }

}