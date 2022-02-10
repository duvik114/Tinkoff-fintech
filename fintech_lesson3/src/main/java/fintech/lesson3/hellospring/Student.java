package fintech.lesson3.hellospring;

import java.time.LocalTime;
import java.util.UUID;

public record Student(UUID id, String name, Integer age, Integer startTime, Integer endTime) {

    public Student(String name, Integer age, Integer startTime, Integer endTime) {
        this(UUID.randomUUID(), name, age, startTime, endTime);
    }

    public boolean checkCurrentTime() {
        int currentTime = LocalTime.now().getHour();
        return currentTime >= startTime && currentTime <= endTime;
    }
}
