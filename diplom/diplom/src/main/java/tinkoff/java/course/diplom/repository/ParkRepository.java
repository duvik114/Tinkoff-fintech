package tinkoff.java.course.diplom.repository;

import org.apache.ibatis.annotations.Mapper;
import tinkoff.java.course.diplom.model.categories.Park;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface ParkRepository {
    List<Park> findAll();

    void save(Park sight);

    void update(Park sight);

    void delete(UUID uuid);

    Optional<Park> findById(UUID uuid);

    List<Park> findAllRated();
}
