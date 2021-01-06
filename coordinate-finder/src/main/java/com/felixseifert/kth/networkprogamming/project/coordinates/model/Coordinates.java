package com.felixseifert.kth.networkprogamming.project.coordinates.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.Entity;
import java.util.Optional;

@Entity
@RegisterForReflection
public class Coordinates extends PanacheEntity {

    // Todo: Add column restrictions and Hibernate Validator infos

    public String city;

    public String countryCode;

    public Double longitude;

    public Double latitude;

    public static Optional<Coordinates> findByCityAndCountryCode(String city, String countryCode) {
        return find("city = ?1 and countryCode = ?2", city, countryCode).singleResultOptional();
    }
}
