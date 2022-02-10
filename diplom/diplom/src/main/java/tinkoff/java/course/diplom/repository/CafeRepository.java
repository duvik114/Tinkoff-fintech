package tinkoff.java.course.diplom.repository;

import org.apache.ibatis.annotations.Mapper;
import tinkoff.java.course.diplom.model.categories.Cafe;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface CafeRepository {
    List<Cafe> findAll();

    void save(Cafe sight);

    void update(Cafe sight);

    void delete(UUID uuid);

    Optional<Cafe> findById(UUID uuid);

    List<Cafe> findAllRated();
}
