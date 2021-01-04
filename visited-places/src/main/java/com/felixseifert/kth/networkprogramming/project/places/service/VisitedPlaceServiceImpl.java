package com.felixseifert.kth.networkprogramming.project.places.service;

import com.felixseifert.kth.networkprogramming.project.places.model.VisitedPlace;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@ApplicationScoped
public class VisitedPlaceServiceImpl implements VisitedPlaceService {

    @Override
    public List<VisitedPlace> getAllVisitedPlaces() {
        return VisitedPlace.listAll();
    }

    @Override
    public VisitedPlace getVisitedPlaceById(Long id) {
        return (VisitedPlace) VisitedPlace.findByIdOptional(id).orElseThrow();
    }

    @Override
    public VisitedPlace postVisitedPlace(VisitedPlace visitedPlace) {
        visitedPlace.persist();
        return visitedPlace;
    }

    @Override
    @Transactional
    public VisitedPlace putVisitedPlace(Long id, VisitedPlace newPlace) {
        VisitedPlace existingPlace = getVisitedPlaceById(id);
        return existingPlace.merge(newPlace);
    }

    @Override
    @Transactional
    public void deleteVisitedPlace(Long id) {
        VisitedPlace.deleteById(id);
    }
}
