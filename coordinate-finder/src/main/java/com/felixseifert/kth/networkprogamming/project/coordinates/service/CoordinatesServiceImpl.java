package com.felixseifert.kth.networkprogamming.project.coordinates.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felixseifert.kth.networkprogamming.project.coordinates.model.Coordinates;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class CoordinatesServiceImpl implements CoordinatesService {

    private static final String GEOCODING_RESOURCE = "https://geocode.search.hereapi.com/v1/geocode";

    @ConfigProperty(name = "geocoding.key")
    String geocodingApiKey;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public Coordinates getCoordinates(String city, String countryCode)
            throws IOException, InterruptedException, NoCoordinatesFoundException {

        Optional<Coordinates> coordinatesFromDB = Coordinates.findByCityAndCountryCode(city, countryCode);
        if(coordinatesFromDB.isPresent()) {
            return coordinatesFromDB.get();
        }
        return geocodeAndPersist(city, countryCode);
    }

    private Coordinates geocodeAndPersist(String city, String countryCode)
            throws IOException, InterruptedException, NoCoordinatesFoundException {

        String response = performGeocodingRequest(city, countryCode);
        JsonNode responseJsonNode = new ObjectMapper().readTree(response);

        JsonNode firstItem = responseJsonNode.get("items").get(0);

        if(firstItem == null) {
            throw new NoCoordinatesFoundException(String.format("No coordinates for city %s and countryCode %s " +
                    "where found.", city, countryCode));
        }

        Coordinates coordinates = new Coordinates();
        coordinates.city = city;
        coordinates.countryCode = countryCode;
        coordinates.longitude = firstItem.get("position").get("lng").asDouble();
        coordinates.latitude = firstItem.get("position").get("lat").asDouble();

        Set<ConstraintViolation<Coordinates>> violations = validator.validate(coordinates);
        if(!violations.isEmpty()) {
            throw new NoCoordinatesFoundException(violations.toString());
        }
        coordinates.persist();

        return coordinates;
    }

    private String performGeocodingRequest(String city, String countryCode)
            throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();

        String encodedQuery = URLEncoder.encode(city + ", " + countryCode, StandardCharsets.UTF_8);
        String requestUri = GEOCODING_RESOURCE + "?apiKey=" + geocodingApiKey + "&q=" + encodedQuery;

        HttpRequest geocodingRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();

        HttpResponse geocodingResponse = httpClient.send(geocodingRequest, HttpResponse.BodyHandlers.ofString());

        return geocodingResponse.body().toString();
    }
}
