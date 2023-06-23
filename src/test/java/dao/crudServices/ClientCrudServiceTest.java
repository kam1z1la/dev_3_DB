package dao.crudServices;

import entity.Client;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientCrudServiceTest {
    ClientCrudService clientService = new ClientCrudService();
    Client client = new Client();

    @Test
    @Order(1)
    void testCreate() {
        client.setName("Anton Mychacho");
        assertDoesNotThrow(() -> clientService.create(client));
    }

    @Test
    void testShowAll() {
        assertDoesNotThrow(() -> clientService.showAll());
    }

    @Test
    void testWrongMethodDelete() {
        assertFalse(clientService.delete(new Client(-1, "Anatoliy")));
    }

    @Test
    void testCorrectMethodDelete() {
        client.setName("Anton Mychacho");
        assertDoesNotThrow(() -> clientService.create(client));
        assertTrue(clientService.delete(client));
    }

    @Test
    void testCorrectMethodUpdate() {
        client.setName("Stepan Mychacho");
        assertDoesNotThrow(() -> clientService.update(client));
    }
}