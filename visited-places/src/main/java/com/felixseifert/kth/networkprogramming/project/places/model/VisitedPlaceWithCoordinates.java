package com.felixseifert.kth.networkprogramming.project.places.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDate;

@RegisterForReflection
public class VisitedPlaceWithCoordinates {

    public String city;

    public String countryCode;

    public LocalDate fromDate;

    public LocalDate toDate;

    public Double longitude;

    public Double latitude;

    public void copyFromVisitedPlace(VisitedPlace visitedPlace) {
        this.city = visitedPlace.city;
        this.countryCode = visitedPlace.countryCode;
        this.fromDate = visitedPlace.fromDate;
        this.toDate = visitedPlace.toDate;
    }

    public void copyFromCoordinates(Coordinates coordinates) {
        this.longitude = coordinates.longitude;
        this.latitude = coordinates.latitude;
    }
}
