package tinkoff.java.course.diplom.service;

import org.springframework.stereotype.Service;
import tinkoff.java.course.diplom.model.categories.Cafe;
import tinkoff.java.course.diplom.repository.CafeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CafeService implements BaseService<Cafe> {
    private final CafeRepository cafeRepository;

    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @Override
    public Cafe find(UUID id) {
        return cafeRepository.findById(id).orElseThrow();
        /*HashMap<String, Object> resultMap = cafeRepository.findById(id).orElseThrow();
        return new Cafe((UUID) resultMap.get("id"),
                (String) resultMap.get("name"),
                "Cafe",
                (Double) resultMap.get("latitude"),
                (Double) resultMap.get("longshot"),
                (String) resultMap.get("description"),
                (LocalTime) resultMap.get("starttime"),
                (LocalTime) resultMap.get("closetime"),
                (Double) resultMap.get("price"),
                (String) resultMap.get("site"),
                (Double) resultMap.get("rating"),
                (String) resultMap.get("menu"));*/
    }

    @Override
    public List<Cafe> findAll() {
        return cafeRepository.findAll();
    }

    @Override
    public List<Cafe> findAllRated() {
        return cafeRepository.findAllRated();
    }

    @Override
    public void save(Cafe sight) {
        cafeRepository.save(sight);
    }

    @Override
    public void update(Cafe sight) {
        cafeRepository.update(sight);
    }

    @Override
    public void delete(UUID id) {
        cafeRepository.delete(id);
    }
}
