package ru.tinkoff.fintech.lesson4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// Изначально сделал вместо Object - T (generic), но с T в конструкторе KafkaProducerService возникают raw types,
// которых, вроде как, желательно избегать.
public class KafkaStudent {
    private Object studentOrId;
    private boolean isDeleted;
}
