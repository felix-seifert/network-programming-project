package com.felixseifert.kth.networkprogramming.project.places.service;

import com.felixseifert.kth.networkprogramming.project.places.model.VisitedPlace;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class VisitedPlaceServiceImpl implements VisitedPlaceService {

    @Override
    public List<VisitedPlace> getVisitedPlacesByUserId(String userId) {
        return VisitedPlace.listByVisitorId(userId);
    }

    @Override
    public VisitedPlace getVisitedPlaceByIdAndUserId(Long id, String userId) {
        return VisitedPlace.findByIdAndVisitorId(id, userId);
    }

    @Override
    @Transactional
    public VisitedPlace postVisitedPlace(VisitedPlace visitedPlace, String userId) {
        visitedPlace.visitorId = userId;
        visitedPlace.persist();
        return visitedPlace;
    }

    @Override
    @Transactional
    public VisitedPlace putVisitedPlace(Long id, VisitedPlace newPlace, String userId) {
        VisitedPlace existingPlace = getVisitedPlaceByIdAndUserId(id, userId);
        return existingPlace.merge(newPlace);
    }

    @Override
    @Transactional
    public void deleteVisitedPlace(Long id, String userId) {
        VisitedPlace.deleteByIdAndVisitorId(id, userId);
    }
}
