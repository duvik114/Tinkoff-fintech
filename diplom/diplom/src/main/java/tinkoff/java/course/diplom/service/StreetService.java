package tinkoff.java.course.diplom.service;

import org.springframework.stereotype.Service;
import tinkoff.java.course.diplom.model.Sight;
import tinkoff.java.course.diplom.model.categories.Street;
import tinkoff.java.course.diplom.repository.StreetRepository;

import java.util.List;
import java.util.UUID;

@Service
public class StreetService implements BaseService<Street> {
    private final StreetRepository streetRepository;

    public StreetService(StreetRepository cafeRepository) {
        this.streetRepository = cafeRepository;
    }

    @Override
    public Street find(UUID id) {
        return streetRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Street> findAll() {
        return streetRepository.findAll();
    }

    @Override
    public List<Street> findAllRated() {
        return streetRepository.findAllRated();
    }

    @Override
    public void save(Street sight) {
        streetRepository.save(sight);
    }

    @Override
    public void update(Street sight) {
        streetRepository.update(sight);
    }

    @Override
    public void delete(UUID id) {
        streetRepository.delete(id);
    }
}
