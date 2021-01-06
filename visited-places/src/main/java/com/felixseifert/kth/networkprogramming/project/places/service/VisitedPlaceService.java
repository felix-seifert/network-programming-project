package com.felixseifert.kth.networkprogramming.project.places.service;

import com.felixseifert.kth.networkprogramming.project.places.model.VisitedPlace;
import com.felixseifert.kth.networkprogramming.project.places.model.VisitedPlaceWithCoordinates;

import javax.validation.Valid;
import java.util.List;

public interface VisitedPlaceService {

    List<VisitedPlaceWithCoordinates> getVisitedPlacesByUserId(String userId);

    VisitedPlaceWithCoordinates getVisitedPlaceByIdAndUserId(Long id, String userId);

    VisitedPlace postVisitedPlace(@Valid VisitedPlace visitedPlace, String userId);

    VisitedPlace putVisitedPlace(Long id, @Valid VisitedPlace visitedPlace, String userId);

    void deleteVisitedPlace(Long id, String userId);
}
