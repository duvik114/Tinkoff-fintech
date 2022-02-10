package tinkoff.java.course.diplom.model.categories;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import tinkoff.java.course.diplom.model.Sight;

import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Park extends Sight {
    @NotEmpty
    private String sightList;

    public Park(UUID id, String name, Double latitude, Double longShot, String description,
                LocalTime startTime, LocalTime closeTime, Double price, String site, Double rating, String sightList) {
        super(id, name, "Park", latitude, longShot, description, startTime, closeTime, price, site, rating);
        this.sightList = sightList;
    }

    public Park() {
        category = "Park";
    }
}
