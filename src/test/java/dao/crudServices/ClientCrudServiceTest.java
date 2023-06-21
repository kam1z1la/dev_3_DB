package dao.crudServices;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientCrudServiceTest {
    ClientCrudService clientService = new ClientCrudService();

    @Test
    @Order(1)
    void testCreate() {
        assertDoesNotThrow(() -> clientService.create("Anton Mychacho"));
    }

    @Test
    void testShowAll() {
        assertDoesNotThrow(() -> clientService.showAll());
    }

    @Test
    void testCorrectMethodGetIdByName() {
        assertDoesNotThrow(() ->  clientService.getIdByName("Anton Mychacho"));
    }

    @Test
    void testWrongMethodGetIdByName() {
        assertEquals(clientService.getIdByName(" "), -1);
    }

    @Test
    void testCorrectMethodDeleteById() {
        assertDoesNotThrow(() -> clientService.deleteById(clientService.getIdByName("Stepan Mychacho")));
    }

    @Test
    void testCorrectMethodUpdate() {
        assertDoesNotThrow(() -> clientService.update(clientService.getIdByName("Anton Mychacho"),
                "Stepan Mychacho"));
    }
}