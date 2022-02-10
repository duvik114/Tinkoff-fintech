package tinkoff.java.course.diplom.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Sight {
    @NotNull
    protected UUID id;

    @NotEmpty
    protected String name;

    @NotNull
    protected String category;

    @NotNull
    protected Double latitude;

    @NotNull
    protected Double longShot;

    @NotEmpty
    protected String description;

    @NotNull
    protected LocalTime startTime;

    @NotNull
    protected LocalTime closeTime;

    @NotNull
    @Max(1000000)
    protected Double price;

    @NotNull
    protected String site;

    @Min(0)
    @Max(10)
    @NotNull
    protected Double rating;

    public Sight() {
        id = UUID.randomUUID();
        startTime = LocalTime.parse("00:00:00");
        closeTime = LocalTime.parse("00:00:00");
        price = 0d;
        site = "";
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }
// enough, may be more?

}
