package com.demo.rest.Resources;

// import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import com.demo.rest.Services.TinDetailsService;
import java.util.List;

import com.demo.rest.Data.TaxInfo;
import com.demo.rest.Models.TinDetailsPayload;


@Path("/TINDetails")
public class TinDetailsResource {


    @Inject
    private TinDetailsService tinDetailsService;
    

    @POST
    @Path("/tin/{tin}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTINDetails(@PathParam("tin") String tin) {
        try {
            TinDetailsPayload rpone = tinDetailsService.FetchTinDetails(tin);
            tinDetailsService.saveTinDetails(rpone);
            return Response.ok(rpone).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity(e.getMessage())
                           .build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEvent( TinDetailsPayload req, 
        @PathParam("id") int id) {
       tinDetailsService.updateTaxInfo(id, req);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvents() {
        List<TaxInfo> events = tinDetailsService.getAllTaxInfo();
        return Response.ok(events).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvent(@PathParam("id") int eventId) {
        TaxInfo event = tinDetailsService.getTaxInfo(eventId);
        return Response.ok(event).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteEvent(@PathParam("id") int id) {
        tinDetailsService.deleteTaxInfo(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
