package tinkoff.java.course.diplom.repository;

import org.apache.ibatis.annotations.Mapper;
import tinkoff.java.course.diplom.model.Sight;
import tinkoff.java.course.diplom.model.categories.Street;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface StreetRepository {
    List<Street> findAll();

    void save(Street sight);

    void update(Street sight);

    void delete(UUID uuid);

    Optional<Street> findById(UUID uuid);

    List<Street> findAllRated();
}
