package com.felixseifert.kth.networkprogramming.project.places.resource;

import com.felixseifert.kth.networkprogramming.project.places.model.Role;
import com.felixseifert.kth.networkprogramming.project.places.model.VisitedPlace;
import com.felixseifert.kth.networkprogramming.project.places.service.VisitedPlaceService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/v1/places")
@RolesAllowed(Role.USER)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VisitedPlaceResource {

    @Inject
    VisitedPlaceService visitedPlaceService;

    @GET
    public List<VisitedPlace> getAllVisitedPlaces() {
        return visitedPlaceService.getAllVisitedPlaces();
    }

    @GET
    @Path("/{id}")
    public VisitedPlace getVisitedPlaceById(@PathParam Long id) {
        return visitedPlaceService.getVisitedPlaceById(id);
    }

    @POST
    public VisitedPlace postVisitedPlace(VisitedPlace visitedPlace) {
        return visitedPlaceService.postVisitedPlace(visitedPlace);
    }

    @PUT
    @Path("/{id}")
    public VisitedPlace putVisitedPlace(@PathParam Long id, VisitedPlace visitedPlace) {
        return visitedPlaceService.putVisitedPlace(id, visitedPlace);
    }

    @DELETE
    @Path("{id}")
    public void deleteVisitedPlace(@PathParam Long id) {
        visitedPlaceService.deleteVisitedPlace(id);
    }
}
