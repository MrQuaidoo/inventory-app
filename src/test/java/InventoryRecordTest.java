import com.inventory.model.InventoryRecord;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the InventoryRecord model.
 * Ensures that inventory records are correctly created and updated.
 */
class InventoryRecordTest {

    /**
     * Tests if an InventoryRecord object is correctly created with the given parameters.
     */
    @Test
    void testInventoryRecordCreation() {
        // Create an inventory record
        InventoryRecord record = new InventoryRecord(1L, "Laptop", 5);

        // Validate that the fields are correctly set
        assertThat(record.getProductId()).isEqualTo(1L);
        assertThat(record.getProductName()).isEqualTo("Laptop");
        assertThat(record.getQuantityRemoved()).isEqualTo(5);
    }

    /**
     * Tests if the quantity removed field can be set correctly.
     */
    @Test
    void testSetQuantityRemoved() {
        // Create an inventory record and update quantity removed
        InventoryRecord record = new InventoryRecord();
        record.setQuantityRemoved(10);

        // Validate that the quantity removed is updated
        assertThat(record.getQuantityRemoved()).isEqualTo(10);
    }
}