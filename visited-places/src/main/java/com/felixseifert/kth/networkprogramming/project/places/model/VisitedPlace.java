package com.felixseifert.kth.networkprogramming.project.places.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "visited_places")
@RegisterForReflection
public class VisitedPlace extends PanacheEntity {

    @NotBlank(message = "City '${validatedValue}' of visited place must not be blank")
    @Column(nullable = false)
    public String city;

    @NotBlank(message = "Country code '${validatedValue}' of visited place must not be blank")
    @Size(min = 1, max = 2, message = "Country code '${validatedValue}' must have between {min} and {max} characters")
    @Column(length = 2, nullable = false)
    public String countryCode;

    @Size(max = 36, message = "ID of visitor '${validatedValue}' must maximally have {max} characters")
    @Column(length = 36, nullable = false)
    public String visitorId;

    @NotNull(message = "Date '${validatedValue}' when place visit started must not be null")
    @Column(nullable = false)
    public LocalDate fromDate;

    @NotNull(message = "Date '${validatedValue}' when place visit ended must not be null")
    @Column(nullable = false)
    public LocalDate toDate;

    public static void add(String city, String countryCode, String visitorId, LocalDate fromDate, LocalDate toDate) {
        VisitedPlace visitedPlace = new VisitedPlace();
        visitedPlace.city = city;
        visitedPlace.countryCode = countryCode;
        visitedPlace.visitorId = visitorId;
        visitedPlace.fromDate = fromDate;
        visitedPlace.toDate = toDate;
        visitedPlace.persist();
    }

    public static List<VisitedPlace> listByVisitorId(String visitorId) {
        return list("visitorId", visitorId);
    }

    public static VisitedPlace findByIdAndVisitorId(Long id, String visitorId) {
        return find("id = ?1 and visitorId = ?2", id, visitorId).singleResult();
    }

    public static void deleteByIdAndVisitorId(Long id, String visitorId) {
        delete("id = ?1 and visitorId = ?2", id, visitorId);
    }

    public VisitedPlace merge(VisitedPlace that) {
        // Do not update id and visitorId!
        this.city = that.city;
        this.countryCode = that.countryCode;
        this.fromDate = that.fromDate;
        this.toDate = that.toDate;
        return this;
    }
}
