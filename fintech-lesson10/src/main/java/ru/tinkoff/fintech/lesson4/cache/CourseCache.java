package ru.tinkoff.fintech.lesson4.cache;

import org.springframework.stereotype.Component;
import ru.tinkoff.fintech.lesson4.model.Course;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CourseCache {
    private final ConcurrentHashMap<Integer, CoursePlusTime> courseIdMap = new ConcurrentHashMap<>();

    public Course find(int id) {
        if (courseIdMap.get(id) == null) {
            return null;
        }
        return courseIdMap.get(id).getCourse();
    }

    public void putIfAbsent(CoursePlusTime coursePlusTime) {
        if (coursePlusTime != null) {
            courseIdMap.putIfAbsent(coursePlusTime.getCourse().getId(), coursePlusTime);
        }
    }

    public void put(CoursePlusTime coursePlusTime) {
        if (coursePlusTime != null) {
            courseIdMap.computeIfPresent(coursePlusTime.getCourse().getId(),
                    (k, v) -> v.getTime() < coursePlusTime.getTime() ? v = coursePlusTime : v);
        }
    }

    public void delete(int id, Long time) {
        courseIdMap.computeIfPresent(id, (k, v) -> v.getTime() < time ? v = new CoursePlusTime(null, time) : v);
    }

    public void clearCache() {
        courseIdMap.clear();
    }
}