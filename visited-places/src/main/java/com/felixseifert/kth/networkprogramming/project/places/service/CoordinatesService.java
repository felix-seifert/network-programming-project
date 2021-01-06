package com.felixseifert.kth.networkprogramming.project.places.service;

import com.felixseifert.kth.networkprogramming.project.places.model.Coordinates;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/coordinates")
@ApplicationScoped
@RegisterClientHeaders
@RegisterRestClient(configKey = "coordinate-finder")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CoordinatesService {

    @GET
    @Path("/")
    Coordinates getCoordinatesForSpecificPlace(@QueryParam("city") String city,
                                               @QueryParam("countryCode") String countryCode);
}
