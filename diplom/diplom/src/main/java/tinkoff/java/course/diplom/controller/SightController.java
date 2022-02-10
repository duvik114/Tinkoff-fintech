package tinkoff.java.course.diplom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tinkoff.java.course.diplom.model.Sight;
import tinkoff.java.course.diplom.model.clientData.ClientData;
import tinkoff.java.course.diplom.service.BaseService;
import tinkoff.java.course.diplom.service.SightService;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class SightController {

    private final SightService sightService;

    public SightController(SightService sightService) {
        this.sightService = sightService;
    }

    @GetMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/sights/suitable"
    )
    public List<Object> getSuitableSights(@Valid @RequestBody ClientData clientData) {
        return sightService.findSuitableSights(clientData);
    }

    @GetMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/sights"
    )
    public Object getSightById(@Valid @RequestParam("id") UUID id, @Valid @RequestParam("category") String category) {
        return sightService.find(id, category);
    }

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/sights"
    )
    public void saveSight(@Valid @RequestBody Object sight) {
        try {
            sightService.save(new ObjectMapper().convertValue(sight, Map.class));
        } catch (InvocationTargetException e) {
            e.printStackTrace(); //
        } catch (IllegalAccessException e) {
            e.printStackTrace(); //
        }
    }

    @PutMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/sights"
    )
    public void updateSight(@Valid @RequestBody HashMap<String, Object> sight) {
        try {
            sightService.update(new ObjectMapper().convertValue(sight, Map.class));
        } catch (InvocationTargetException e) {
            e.printStackTrace(); //
        } catch (IllegalAccessException e) {
            e.printStackTrace(); //
        }
    }

    @DeleteMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/sights"
    )
    public void deleteSightById(@Valid @RequestParam("id") UUID uuid, @Valid @RequestParam("category") String category) {
        sightService.delete(uuid, category);
    }
}
