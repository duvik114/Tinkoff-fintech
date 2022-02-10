package tinkoff.java.course.diplom.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import tinkoff.java.course.diplom.model.Sight;
import tinkoff.java.course.diplom.model.categories.Cafe;
import tinkoff.java.course.diplom.model.categories.Park;
import tinkoff.java.course.diplom.model.categories.Street;
import tinkoff.java.course.diplom.model.clientData.ClientData;

import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
public class SightService {

    private Map<String, BaseService<? extends Sight>> serviceMap = new HashMap<>();
    private CafeService cafeService;
    private ParkService parkService;
    private StreetService streetService;

    public SightService(CafeService cafeService, ParkService parkService, StreetService streetService) {
        serviceMap.put("Cafe", cafeService);
        serviceMap.put("Park", parkService);
        serviceMap.put("Street", streetService);

        this.cafeService = cafeService;
        this.parkService = parkService;
        this.streetService = streetService;
    }

    public List<Object> findSuitableSights(ClientData clientData) {
        List<SightObject> bestSights = new ArrayList<>();
        for (String category : clientData.getSuitableCategories()) {
            BaseService<? extends Sight> service = serviceMap.get(category.substring(0, 1).toUpperCase() +
                    category.substring(1).toLowerCase());
            if (service == null) {
                continue; //
            }

            List<? extends Sight> categorySights = service.findAllRated();
            for (var sight : categorySights) {
                if (sight.getStartTime().equals(sight.getCloseTime())) {
                    bestSights.add(new SightObject(sight, Time.valueOf("00:00:00"), Time.valueOf("00:00:00")));
                    clientData.setSuitablePrice(clientData.getSuitablePrice() - sight.getPrice());
                    continue;
                }

                Time sightStartTime = Time.valueOf(sight.getStartTime());
                Time sightCloseTime = Time.valueOf(sight.getCloseTime());
                if (sight.getStartTime().compareTo(sight.getCloseTime()) > 0) {
                    sightCloseTime.setTime(sightCloseTime.getTime() + 86400000);
                }

                Time clientStartTime = Time.valueOf(clientData.getSuitableStartTime());
                Time clientCloseTime = Time.valueOf(clientData.getSuitableEndTime());
                if (clientData.getSuitableStartTime().compareTo(clientData.getSuitableEndTime()) > 0) {
                    clientCloseTime.setTime(clientCloseTime.getTime() + 86400000);
                }

                if (sight.getPrice() <= clientData.getSuitablePrice()
                        && ((sightStartTime.compareTo(clientStartTime) > 0 && sightStartTime.compareTo(clientCloseTime) < 0)
                        || (sightCloseTime.compareTo(clientStartTime) > 0 && sightCloseTime.compareTo(clientCloseTime) < 0)
                        || (sightStartTime.compareTo(clientStartTime) < 0 && sightCloseTime.compareTo(clientCloseTime) > 0))) {
                    clientData.setSuitablePrice(clientData.getSuitablePrice() - sight.getPrice());
                    bestSights.add(new SightObject(sight, sightStartTime, sightCloseTime));
                }
            }
        }
        bestSights.sort(Comparator.comparing((SightObject o) -> o.timeStart));
        return bestSights.stream().map(sightObject -> sightObject.sight).collect(Collectors.toList());
    }

    @Getter
    @Setter
    private class SightObject {

        private Object sight;
        private Time timeStart, timeClose;

        public SightObject(Object sight, Time timeStart, Time timeClose) {
            this.sight = sight;
            this.timeStart = timeStart;
            this.timeClose = timeClose;
        }
    }

    public Object find(UUID id, String category) {
        BaseService<? extends Sight> service = serviceMap.get(category.substring(0, 1).toUpperCase() +
                category.substring(1).toLowerCase());
        if (service == null) {
            return null;
        }
        return service.find(id);
    }

    public List<Object> findAll() {
        List<Object> res = new ArrayList<>();
        for (BaseService<? extends Sight> service : serviceMap.values()) {
            res.addAll(service.findAll());
        }
        return res;
    }

    public List<Object> findAll(String category) {
        BaseService<? extends Sight> service = serviceMap.get(category.substring(0, 1).toUpperCase() +
                category.substring(1).toLowerCase());
        if (service == null) {
            return null;
        }
        return Arrays.asList(service.findAll().toArray());
    }

    public void save(Map<String, Object> o) throws InvocationTargetException, IllegalAccessException {
        Sight sight = new Sight();

        if (o.containsKey("startTime")) {
            LocalTime startTime = LocalTime.parse((String) o.get("startTime"));
            o.put("startTime", startTime);
        }
        if (o.containsKey("closeTime")) {
            LocalTime closeTime = LocalTime.parse((String) o.get("closeTime"));
            o.put("closeTime", closeTime);
        }

        BeanUtils.populate(sight, o);
        switch (sight.getCategory().toLowerCase()) {
            case "cafe" -> {
                Cafe cafe = new Cafe();
                BeanUtils.populate(cafe, o);
                cafeService.save(cafe);
            }
            case "park" -> {
                Park park = new Park();
                BeanUtils.populate(park, o);
                parkService.save(park);
            }
            case "street" -> {
                Street street = new Street();
                BeanUtils.populate(street, o);
                streetService.save(street);
            }
            // default -> throw new Exception("sas");
        }
    }

    public void update(Map<String, Object> o) throws InvocationTargetException, IllegalAccessException {
        Sight sight = new Sight();

        if (o.containsKey("startTime")) {
            LocalTime startTime = LocalTime.parse((String) o.get("startTime"));
            o.put("startTime", startTime);
        }
        if (o.containsKey("closeTime")) {
            LocalTime closeTime = LocalTime.parse((String) o.get("closeTime"));
            o.put("closeTime", closeTime);
        }

        BeanUtils.populate(sight, o);
        switch (sight.getCategory().toLowerCase()) {
            case "cafe" -> {
                Cafe cafe = new Cafe();
                BeanUtils.populate(cafe, o);
                cafeService.update(cafe);
            }
            case "park" -> {
                Park park = new Park();
                BeanUtils.populate(park, o);
                parkService.update(park);
            }
            case "street" -> {
                Street street = new Street();
                BeanUtils.populate(street, o);
                streetService.update(street);
            }
        }
    }

    public void delete(UUID id, String category) {
        BaseService<? extends Sight> service = serviceMap.get(category.substring(0, 1).toUpperCase() +
                category.substring(1).toLowerCase());
        if (service != null) {
            service.delete(id);
        }
    }
}
