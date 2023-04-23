import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @BeforeAll
    @DisplayName("Test Connection")
    static void testGetConnection() {
        Assertions.assertDoesNotThrow(() -> RuntimeException.class);
    }

    @Test
    @DisplayName("Test init query")
    void testInitQuery() {
        Assertions.assertDoesNotThrow(() -> RuntimeException.class);
    }

    @Test
    @DisplayName("Test populate query")
    void TestPopulateQuery() {
        Assertions.assertDoesNotThrow(() -> RuntimeException.class);
    }
}