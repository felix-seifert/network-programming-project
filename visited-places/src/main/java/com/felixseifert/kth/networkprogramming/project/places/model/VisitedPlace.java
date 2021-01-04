package com.felixseifert.kth.networkprogramming.project.places.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "visited_places")
public class VisitedPlace extends PanacheEntity {

    @NotBlank(message = "City '${validatedValue}' of visited place must not be blank")
    @Column(nullable = false)
    public String city;

    @NotBlank(message = "Country code '${validatedValue}' of visited place must not be blank")
    @Size(min = 1, max = 2, message = "Country code '${validatedValue}' must be between {min} and {mac}")
    @Column(length = 2, nullable = false)
    public String countryCode;

    @NotNull(message = "ID of visitor '${validatedValue}' of visited place must not be blank")
    @Column(nullable = false)
    public Long visitorId;

    @NotNull(message = "Date '${validatedValue}' when place visit started must not be blank")
    @Column(nullable = false)
    public LocalDate fromDate;

    @NotNull(message = "Date '${validatedValue}' when place visit ended must not be blank")
    @Column(nullable = false)
    public LocalDate toDate;

    public static void add(String city, String countryCode, Long visitorId, LocalDate fromDate, LocalDate toDate) {
        VisitedPlace visitedPlace = new VisitedPlace();
        visitedPlace.city = city;
        visitedPlace.countryCode = countryCode;
        visitedPlace.visitorId = visitorId;
        visitedPlace.fromDate = fromDate;
        visitedPlace.toDate = toDate;
        visitedPlace.persist();
    }

    public VisitedPlace merge(VisitedPlace that) {
        this.city = that.city;
        this.countryCode = that.countryCode;
        this.visitorId = that.visitorId;
        this.fromDate = that.fromDate;
        this.toDate = that.toDate;
        return this;
    }
}
