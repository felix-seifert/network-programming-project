package com.felixseifert.kth.networkprogramming.project.places.service;

import com.felixseifert.kth.networkprogramming.project.places.model.VisitedPlace;

import javax.validation.Valid;
import java.util.List;

public interface VisitedPlaceService {

    List<VisitedPlace> getAllVisitedPlaces();

    VisitedPlace getVisitedPlaceById(Long id);

    VisitedPlace postVisitedPlace(@Valid VisitedPlace visitedPlace);

    VisitedPlace putVisitedPlace(Long id, @Valid VisitedPlace visitedPlace);

    void deleteVisitedPlace(Long id);
}
