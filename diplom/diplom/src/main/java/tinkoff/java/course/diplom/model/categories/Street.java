package tinkoff.java.course.diplom.model.categories;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import tinkoff.java.course.diplom.model.Sight;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Street extends Sight {
    @NotEmpty
    private String sightList;

    @Size(max = 9000)
    private Double length;

    public Street(UUID id, String name, Double latitude, Double longShot, String description,
                /*LocalTime startTime, LocalTime closeTime, Double price, String site,*/
                  Double rating, String sightList, Double length) {
        super(id, name, "Street", latitude, longShot, description, LocalTime.parse("00:00:00"), LocalTime.parse("00:00:00"),
                0d, "", rating);
        this.sightList = sightList;
        this.length = length;
    }

    public Street() {
        category = "Street";
    }
}
