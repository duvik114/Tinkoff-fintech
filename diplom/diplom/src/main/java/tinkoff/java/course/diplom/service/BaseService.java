package tinkoff.java.course.diplom.service;

import tinkoff.java.course.diplom.model.Sight;
import java.util.List;
import java.util.UUID;

public interface BaseService<T extends Sight> {
    List<T> findAll();

    List<T> findAllRated();

    void save(T sight);

    void update(T sight);

    void delete(UUID uuid);

     T find(UUID uuid);
}
