package dao.crudServices;

import entity.Planet;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetCrudServiceTest {
    PlanetCrudService planetService = new PlanetCrudService();
    Planet planet = new Planet("EARTH", "Земля");

    @Test
    @Order(1)
    void testCreate() {
        assertDoesNotThrow(() -> planetService.create(planet));
    }

    @Test
    void testShowAll() {
        assertDoesNotThrow(() -> planetService.showAll());
    }

    @Test
    void testWrongMethodDelete() {
        assertFalse(planetService.delete(new Planet("ASD", "....")));
    }

    @Test
    @Order(2)
    void testCorrectMethodUpdate() {
        planet.setName("земля");
        assertDoesNotThrow(() -> planetService.update(planet));
    }
}