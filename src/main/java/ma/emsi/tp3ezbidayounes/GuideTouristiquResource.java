package ma.emsi.tp3ezbidayounes;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/guide")
public class GuideTouristiquResource {

    @Inject
    private LlmClient llmClient;

    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces("application/json")
    public Response visit(@PathParam("ville_ou_pays") String ville_ou_pays) {
        return Response.ok(llmClient.envoyerMessage("Je veux visiter" + ville_ou_pays)).build();
    }



}
