package net.arcann.telethonno.endpoint.rest.endpoint;

import lombok.extern.slf4j.Slf4j;
import net.arcann.telethonno.endpoint.model.PisteModel;
import net.arcann.telethonno.engine.business.api.AdminApi;
import net.arcann.telethonno.engine.business.api.BoardApi;
import net.arcann.telethonno.engine.business.api.view.JoueurView;
import net.arcann.telethonno.engine.business.api.view.ResultatView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Slf4j
@Component
@Path("/admin")
public class AdminEndpoint {

    private final AdminApi adminApi;
    private final BoardApi boardApi;

    private AdminEndpoint(
            @Autowired AdminApi adminApi,
            @Autowired BoardApi boardApi) {
        this.adminApi = adminApi;
        this.boardApi = boardApi;
    }

    @Path("")
    @POST
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateMontant(@RequestBody ResultatView resultats) {
        adminApi.metAJourLeMontant(resultats.getCagnotte());
    }

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultatView getResultats() {
        return boardApi.recupererResultats();
    }

    @Path("/joueurs")
    @GET
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<JoueurView> getJoueurs() {
        return boardApi.recupererJoueurs();
    }

}