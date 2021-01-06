package com.felixseifert.kth.networkprogramming.project.places.service;

import com.felixseifert.kth.networkprogramming.project.places.model.Coordinates;
import com.felixseifert.kth.networkprogramming.project.places.model.VisitedPlace;
import com.felixseifert.kth.networkprogramming.project.places.model.VisitedPlaceWithCoordinates;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class VisitedPlaceServiceImpl implements VisitedPlaceService {

    @Inject
    @RestClient
    CoordinatesService coordinatesService;

    @Override
    public List<VisitedPlaceWithCoordinates> getVisitedPlacesByUserId(String userId) {
        return VisitedPlace.listByVisitorId(userId).stream()
                .map(this::getCoordinatesForPlace)
                .collect(Collectors.toList());
    }

    @Override
    public VisitedPlaceWithCoordinates getVisitedPlaceByIdAndUserId(Long id, String userId) {
        VisitedPlace visitedPlace = VisitedPlace.findByIdAndVisitorId(id, userId);
        return getCoordinatesForPlace(visitedPlace);
    }

    private VisitedPlaceWithCoordinates getCoordinatesForPlace(VisitedPlace visitedPlace) {
        Coordinates coordinates = coordinatesService.getCoordinatesForSpecificPlace(
                visitedPlace.city, visitedPlace.countryCode);

        VisitedPlaceWithCoordinates placeWithCoordinates = new VisitedPlaceWithCoordinates();
        placeWithCoordinates.copyFromVisitedPlace(visitedPlace);
        placeWithCoordinates.copyFromCoordinates(coordinates);

        return placeWithCoordinates;
    }

    @Override
    @Transactional
    public VisitedPlace postVisitedPlace(VisitedPlace visitedPlace, String userId) {
        visitedPlace.id = null;
        visitedPlace.visitorId = userId;
        visitedPlace.persist();
        return visitedPlace;
    }

    @Override
    @Transactional
    public VisitedPlace putVisitedPlace(Long id, VisitedPlace newPlace, String userId) {
        VisitedPlace existingPlace = VisitedPlace.findByIdAndVisitorId(id, userId);
        return existingPlace.merge(newPlace);
    }

    @Override
    @Transactional
    public void deleteVisitedPlace(Long id, String userId) {
        VisitedPlace.deleteByIdAndVisitorId(id, userId);
    }
}
