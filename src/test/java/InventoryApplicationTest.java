import com.inventory.InventoryApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Unit test for the InventoryApplication class.
 * Ensures that the Spring Boot application loads without any issues.
 */
@SpringBootTest(classes = InventoryApplication.class)
class InventoryApplicationTest {

    /**
     * Tests if the Spring application context loads successfully without throwing exceptions.
     */
    @Test
    void contextLoads() {
        // Verify that the main method of the application does not throw any exceptions when executed.
        assertDoesNotThrow(() -> InventoryApplication.main(new String[]{}));
    }
}