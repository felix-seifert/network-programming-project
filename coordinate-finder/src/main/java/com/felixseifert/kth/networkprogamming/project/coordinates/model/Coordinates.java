package com.felixseifert.kth.networkprogamming.project.coordinates.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

@Entity
@RegisterForReflection
public class Coordinates extends PanacheEntity {

    @NotBlank(message = "City '${validatedValue}' of visited place must not be blank")
    @Column(nullable = false)
    public String city;

    @NotBlank(message = "Country code '${validatedValue}' of visited place must not be blank")
    @Size(min = 1, max = 2, message = "Country code '${validatedValue}' must have between {min} and {max} characters")
    @Column(length = 2, nullable = false)
    public String countryCode;

    @NotNull(message = "Longitude must not be null")
    public Double longitude;

    @NotNull(message = "Latitude must not be null")
    public Double latitude;

    public static Optional<Coordinates> findByCityAndCountryCode(String city, String countryCode) {
        return find("city = ?1 and countryCode = ?2", city, countryCode).singleResultOptional();
    }
}
