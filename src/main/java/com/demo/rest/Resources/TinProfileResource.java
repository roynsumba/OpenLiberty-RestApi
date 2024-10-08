package com.demo.rest.Resources;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import com.demo.rest.Models.TinProfileRequest;
import com.demo.rest.Services.TinDetailsService;
import com.demo.rest.Models.TinProfilePayload;

@Path("/TINRegistration")
public class TinProfileResource {


    @Inject
    private TinDetailsService tinDetailsService;

    @POST
    @Path("/get-tin-profile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTINDetails2(TinProfileRequest payload) {
        try {
            TinProfilePayload apiResponseBody = tinDetailsService.FetchTinProfile(payload);
            return Response.ok(apiResponseBody).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity(e.getMessage())
                           .build();
        }
    }
}
