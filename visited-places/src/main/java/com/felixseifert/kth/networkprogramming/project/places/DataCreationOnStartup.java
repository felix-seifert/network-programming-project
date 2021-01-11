package com.felixseifert.kth.networkprogramming.project.places;

import com.felixseifert.kth.networkprogramming.project.places.model.VisitedPlace;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Singleton
public class DataCreationOnStartup {

    @Transactional
    public void addPlaces(@Observes StartupEvent startupEvent) {
        VisitedPlace.deleteAll();

        VisitedPlace.add("Karlsruhe", "DE", "7f323e83-7bca-4f58-8e1e-2de1ab7a8621",
                LocalDate.of(2018, 6, 15), LocalDate.of(2018, 6, 19));
        VisitedPlace.add("Stockholm", "SE", "7f323e83-7bca-4f58-8e1e-2de1ab7a8621",
                LocalDate.of(2019, 8, 11), LocalDate.of(2019, 9, 2));
    }
}
