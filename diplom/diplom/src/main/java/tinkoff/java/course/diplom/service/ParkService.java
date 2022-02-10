package tinkoff.java.course.diplom.service;

import org.springframework.stereotype.Service;
import tinkoff.java.course.diplom.model.Sight;
import tinkoff.java.course.diplom.model.categories.Park;
import tinkoff.java.course.diplom.repository.ParkRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ParkService implements BaseService<Park> {
    private final ParkRepository parkRepository;

    public ParkService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    @Override
    public Park find(UUID id) {
        return parkRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Park> findAll() {
        return parkRepository.findAll();
    }

    @Override
    public List<Park> findAllRated() {
        return parkRepository.findAllRated();
    }

    @Override
    public void save(Park sight) {
        parkRepository.save(sight);
    }

    @Override
    public void update(Park sight) {
        parkRepository.update(sight);
    }

    @Override
    public void delete(UUID id) {
        parkRepository.delete(id);
    }
}
