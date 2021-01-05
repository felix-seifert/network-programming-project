package com.felixseifert.kth.networkprogramming.project.places.resource;

import com.felixseifert.kth.networkprogramming.project.places.model.Role;
import com.felixseifert.kth.networkprogramming.project.places.model.VisitedPlace;
import com.felixseifert.kth.networkprogramming.project.places.service.VisitedPlaceService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/places")
@RolesAllowed(Role.USER)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VisitedPlaceResource {

    @Inject
    VisitedPlaceService visitedPlaceService;

    @Inject
    JsonWebToken jsonWebToken;

    @GET
    public Response getAllVisitedPlaces() {
        List<VisitedPlace> visitedPlaceList = visitedPlaceService.getVisitedPlacesByUserId(getUserIdFromToken());
        if(visitedPlaceList.isEmpty()) {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .entity(visitedPlaceList).build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(visitedPlaceList).build();
    }

    @GET
    @Path("/{id}")
    public Response getVisitedPlaceById(@PathParam("id") Long id) {
        try {
            VisitedPlace visitedPlace = visitedPlaceService.getVisitedPlaceByIdAndUserId(id, getUserIdFromToken());
            return Response
                    .status(Response.Status.OK)
                    .entity(visitedPlace).build();
        } catch(NoResultException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(String.format("No VisitedPlace with id %d found for user %s with id %s",
                            id, jsonWebToken.getName(), getUserIdFromToken())).build();
        }
    }

    @POST
    public Response postVisitedPlace(VisitedPlace visitedPlace) {
        try {
            VisitedPlace createdVisitedPlace = visitedPlaceService
                    .postVisitedPlace(visitedPlace, getUserIdFromToken());
            return Response
                    .status(Response.Status.CREATED)
                    .entity(createdVisitedPlace).build();
        } catch(ConstraintViolationException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response putVisitedPlace(@PathParam("id") Long id, VisitedPlace visitedPlace) {
        try {
            VisitedPlace updatedVisitedPlace = visitedPlaceService
                    .putVisitedPlace(id, visitedPlace, getUserIdFromToken());
            return Response
                    .status(Response.Status.OK)
                    .entity(updatedVisitedPlace).build();
        } catch(ConstraintViolationException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch(NoResultException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(String.format("VisitedPlace with id %d cannot be updated because it is not found " +
                            "for user %s with id %s", id, jsonWebToken.getName(), getUserIdFromToken())).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteVisitedPlace(@PathParam("id") Long id) {
        try {
            visitedPlaceService.deleteVisitedPlace(id, getUserIdFromToken());
            return Response
                    .status(Response.Status.NO_CONTENT).build();
        } catch(NoResultException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(String.format("VisitedPlace with id %d cannot be deleted because it is not found " +
                            "for user %s with id %s", id, jsonWebToken.getName(), getUserIdFromToken())).build();
        }
    }

    private String getUserIdFromToken() {
        return jsonWebToken.getClaim("sub");
    }
}
