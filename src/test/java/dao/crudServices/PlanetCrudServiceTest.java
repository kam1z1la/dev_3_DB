package dao.crudServices;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetCrudServiceTest {
    PlanetCrudService planetService = new PlanetCrudService();

    @Test
    @Order(1)
    void testCreate() {
        assertDoesNotThrow(() -> planetService.create("EARTH", "Земля"));
    }

    @Test
    void testShowAll() {
        assertDoesNotThrow(() -> planetService.showAll());
    }

    @Test
    @Order(2)
    void testCorrectMethodUpdate() {
        assertDoesNotThrow(() -> planetService.create("EARTH", "Земля"));
        assertDoesNotThrow(() -> planetService.update("EARTH",  "земля"));
    }

    @Test
    void testCorrectMethodDeleteById() {
        assertDoesNotThrow(() ->  planetService.deleteById("EARTH"));
    }
}