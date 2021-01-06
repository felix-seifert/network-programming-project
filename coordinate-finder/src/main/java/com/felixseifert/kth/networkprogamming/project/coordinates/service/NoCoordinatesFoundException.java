package com.felixseifert.kth.networkprogamming.project.coordinates.service;

public class NoCoordinatesFoundException extends RuntimeException {
    public NoCoordinatesFoundException(String errorMessage) {
        super(errorMessage);
    }
}
