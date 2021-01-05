package com.felixseifert.kth.networkprogamming.project.coordinates.resource;

import com.felixseifert.kth.networkprogamming.project.coordinates.model.Coordinates;
import com.felixseifert.kth.networkprogamming.project.coordinates.model.Role;
import com.felixseifert.kth.networkprogamming.project.coordinates.service.CoordinatesService;
import com.felixseifert.kth.networkprogamming.project.coordinates.service.NoCoordinatesFoundException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/api/v1/coordinates")
@RolesAllowed(Role.USER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoordinatesResource {

    @Inject
    CoordinatesService coordinatesService;

    @GET
    public Response getCoordinatesForSpecificPlace(@QueryParam("city") String city,
                                                   @QueryParam("countryCode") String countryCode)
            throws IOException, InterruptedException {

        try {
            Coordinates coordinates = coordinatesService.getCoordinates(city, countryCode);
            return Response
                    .status(Response.Status.OK)
                    .entity(coordinates).build();
        } catch(NoCoordinatesFoundException e) {
            return Response
                    .status(Response.Status.BAD_GATEWAY)
                    .entity(e.getMessage()).build();
        }
    }
}
