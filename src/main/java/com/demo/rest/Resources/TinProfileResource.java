package com.demo.rest.Resources;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import com.demo.rest.Services.TinDetailsService;
import java.util.List;

import com.demo.rest.Data.TinProfilePayloadEntity;
import com.demo.rest.Models.TinProfilePayload;

@Path("v1/tinprofile")
public class TinProfileResource {


    @Inject
    private TinDetailsService tinDetailsService;

    @POST
    @Path("/fetch-tin-profile/{tin}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTINDetails2(@PathParam("tin") String tin) {
        try {
            TinProfilePayload apiResponseBody = tinDetailsService.FetchTinProfile(tin);
            tinDetailsService.createTinProfile(apiResponseBody);
            return Response.ok(apiResponseBody).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity(e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/tin-profile-details/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTinProfile(@PathParam("id") Long id) {
        TinProfilePayloadEntity profile = tinDetailsService.getTinProfile(id);
            return Response.ok(profile).build();
      
    }

    @GET
    @RolesAllowed("user") 
    @Path("/all-tin-profile-details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTinProfiles() {
        List<TinProfilePayloadEntity> profiles = tinDetailsService.getTinProfiles();
        return Response.ok(profiles).build();
    } 

    @PUT
    @Path("/update-tin-profile-details/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void updateTinProfile(@PathParam("id") Long id, TinProfilePayloadEntity payload) {
     tinDetailsService.updateTinProfile(id, payload);
       
     }

    @DELETE
    @Path("/delete-tin-profile/{id}")
    public Response deleteTinProfile(@PathParam("id") Long id) {
        tinDetailsService.deleteTinProfile(id);
        return Response.noContent().build();
       
    }
}
