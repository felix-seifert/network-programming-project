package com.felixseifert.kth.networkprogamming.project.coordinates.service;

import com.felixseifert.kth.networkprogamming.project.coordinates.model.Coordinates;

import java.io.IOException;

public interface CoordinatesService {

    Coordinates getCoordinates(String city, String countryCode) throws IOException, InterruptedException;

    // Todo: Allow to get coordinates in batch mode
}
