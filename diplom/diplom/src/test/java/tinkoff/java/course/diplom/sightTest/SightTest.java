package tinkoff.java.course.diplom.sightTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;
import tinkoff.java.course.diplom.DiplomApplication;
import tinkoff.java.course.diplom.model.Sight;
import tinkoff.java.course.diplom.model.categories.Cafe;
import tinkoff.java.course.diplom.model.categories.Park;
import tinkoff.java.course.diplom.model.categories.Street;
import tinkoff.java.course.diplom.service.BaseService;
import tinkoff.java.course.diplom.service.SightService;

import java.time.LocalTime;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DiplomApplication.class)
@AutoConfigureMockMvc
public class SightTest {
    private final ObjectMapper jackson = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SightService sightService;

    @BeforeEach
    void setUp() {
        for (BaseService<? extends Sight> service : sightService.getServiceMap().values()) {
            for (var sight : service.findAll()) {
                service.delete(sight.getId());
            }
        }
        jackson.registerModule(new JavaTimeModule());
        jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // StdDateFormat is ISO8601 since jackson 2.9
        jackson.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
    }

    @Test
    void setSightSuccessTest() throws Exception {
        Cafe cafe = new Cafe(null, "name", 0.4d, 0.9d, "description", LocalTime.MIN,
                LocalTime.MAX, 4.9, "", 9.4, "menu");
        String cafeJson = jackson.writeValueAsString(cafe);
        mockMvc.perform(post("/sights")
                        .header(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46cm9vdHJvb3Q=")
                        .contentType("application/json")
                        .content(cafeJson))
                .andExpect(status().isOk());
        Cafe cafe2 = sightService.getCafeService().findAll().get(0);
        assertEquals(cafe, cafe2);
    }

    @Test
    void getSightSuccessTest() throws Exception {
        Park park = new Park(null, "name", 0.4d, 0.9d, "description", LocalTime.MIN,
                LocalTime.MAX, 4.9, "", 9.4, "sightList");
        sightService.getParkService().save(park);

        mockMvc.perform(get("/sights")
                        .header(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46cm9vdHJvb3Q=")
                        .contentType("application/json")
                        .param("id", park.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(jackson.writeValueAsString(park)));
    }

    @Test
    void updateSightSuccessTest() throws Exception {
        Street street1 = new Street(null, "name", 0.4d, 0.9d, "description", 4.9,
                "sightList", 9.9);
        sightService.getStreetService().save(street1);
        Street street2 = sightService.getStreetService().find(street1.getId());
        street2.setLength(10d);
                mockMvc.perform(put("/sights")
                        .header(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46cm9vdHJvb3Q=")
                        .contentType("application/json")
                        .content(jackson.writeValueAsString(street2)))
                .andExpect(status().isOk());
        street2 = sightService.getStreetService().find(street2.getId());

        assertEquals(street1.getId(), street2.getId());
        assertEquals(street1.getName(), street2.getName());
        assertEquals(street1.getLatitude(), street2.getLatitude());
        assertEquals(street1.getLongShot(), street2.getLongShot());
        assertEquals(street1.getDescription(), street2.getDescription());
        assertEquals(street1.getRating(), street2.getRating());
        assertEquals(street1.getSightList(), street2.getSightList());
        assertNotEquals(street1.getLength(), street2.getLength());
    }

    @Test
    void deleteSightSuccessTest() throws Exception {
        Street street1 = new Street(null, "name", 0.4d, 0.9d, "description", 4.9,
                "sightList", 9.9);
        sightService.getStreetService().save(street1);
        mockMvc.perform(delete("/sights")
                        .header(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46cm9vdHJvb3Q=")
                        .contentType("application/json")
                        .param("id", street1.getId().toString()))
                .andExpect(status().isOk());
        assertThrows(NoSuchElementException.class, () -> sightService.getStreetService().find(street1.getId()));
    }
}
