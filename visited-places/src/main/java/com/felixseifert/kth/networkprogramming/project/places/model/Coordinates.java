package com.felixseifert.kth.networkprogramming.project.places.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {

    public Double longitude;

    public Double latitude;
}
