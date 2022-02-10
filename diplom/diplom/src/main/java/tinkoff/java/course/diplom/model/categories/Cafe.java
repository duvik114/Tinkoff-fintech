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
public class Cafe extends Sight {
    @NotEmpty
    private String menu;

    public Cafe(UUID id, String name, Double latitude, Double longShot, String description,
                LocalTime startTime, LocalTime closeTime, Double price, String site, Double rating, String menu) {
        super(id, name, "Cafe", latitude, longShot, description, startTime, closeTime, price, site, rating);
        this.menu = menu;
    }

    public Cafe() {
        category = "Cafe";
    }
}
