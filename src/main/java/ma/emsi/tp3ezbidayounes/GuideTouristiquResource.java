package ma.emsi.tp3ezbidayounes;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/guide")
public class GuideTouristiquResource {

    @Inject
    private LlmClient llmClient;

    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces("text/plain")
    public String visit(@PathParam("ville_ou_pays") String ville_ou_pays) {
        return ville_ou_pays;
    }



}
