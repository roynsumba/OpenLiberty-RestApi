package com.demo.rest.Resources;

// import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;

import com.demo.rest.Models.TinDetailsRequest;
import com.demo.rest.Services.TinDetailsService;
import com.demo.rest.Models.TinDetailsPayload;

@Path("/TINDetails")
public class TinDetailsResource {


    @Inject
    private TinDetailsService tinDetailsService;
    

    @POST
    @Path("/tin/{tin}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTINDetails(@PathParam("tin") String tin, TinDetailsRequest payload) {
        try {
            TinDetailsPayload rpone = tinDetailsService.FetchTinDetails( tin, payload);
            return Response.ok(rpone).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity(e.getMessage())
                           .build();
        }
    }
}
