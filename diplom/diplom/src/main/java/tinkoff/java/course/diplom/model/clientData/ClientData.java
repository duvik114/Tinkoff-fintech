package tinkoff.java.course.diplom.model.clientData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientData {
    private List<String> suitableCategories;
    private LocalTime suitableStartTime;
    private LocalTime suitableEndTime;
    private Double suitablePrice;
}
