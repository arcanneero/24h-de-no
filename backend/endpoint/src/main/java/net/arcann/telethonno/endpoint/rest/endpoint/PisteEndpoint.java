package net.arcann.telethonno.endpoint.rest.endpoint;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import net.arcann.telethonno.endpoint.model.JoueurModel;
import net.arcann.telethonno.engine.business.api.AdminApi;
import net.arcann.telethonno.engine.business.api.CourseApi;
import net.arcann.telethonno.engine.business.api.view.PisteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;


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

    @DELETE
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void reset() {

        adminApi.initialiserCourse();
    }

    @Path("/{ligne}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void reset(@PathParam("ligne") Integer ligne) {

        PisteView piste = new PisteView();
        piste.setNumero(ligne);

        adminApi.initialiserPiste(piste);

    }

    @Path("/{ligne}/joueur")
    @POST
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void nouveauJoueur(@PathParam("ligne") Integer ligne, JoueurModel joueurModel) {


        PisteView piste = new PisteView();
        piste.setNumero(ligne);

        adminApi.renseignerUnJoueur(piste, "");
    }

    @Path("/{ligne}")
    @POST
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void nouveauTour(@PathParam("ligne") Integer ligne) {

        PisteView piste = new PisteView();
        piste.setNumero(ligne);

        courseApi.nouveauTour(piste);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<PisteView> getState() {

        return courseApi.get();

    }

}