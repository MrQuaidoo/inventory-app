import com.inventory.InventoryApplication;
import com.inventory.model.InventoryRecord;
import com.inventory.repository.InventoryRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the InventoryRecordRepository.
 * Ensures database operations for inventory records work as expected.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InventoryApplication.class)
class InventoryRecordRepositoryTest {

    @Autowired
    private InventoryRecordRepository inventoryRecordRepository;

    /**
     * Tests if an inventory record can be saved successfully in the database.
     */
    @Test
    void testSaveInventoryRecord() {
        // Create an inventory record and save it
        InventoryRecord record = new InventoryRecord(1L, "Test Product", 5);
        InventoryRecord savedRecord = inventoryRecordRepository.save(record);

        // Validate that the saved record has a generated ID
        assertThat(savedRecord.getId()).isNotNull();
    }

    /**
     * Tests if retrieving all inventory records returns the expected list of records.
     */
    @Test
    void testFindAllInventoryRecords() {
        // Create sample inventory records
        InventoryRecord record1 = new InventoryRecord(1L, "Product A", 5);
        InventoryRecord record2 = new InventoryRecord(2L, "Product B", 10);

        // Save records to the database
        inventoryRecordRepository.save(record1);
        inventoryRecordRepository.save(record2);

        // Retrieve all inventory records
        List<InventoryRecord> records = inventoryRecordRepository.findAll();

        // Validate that at least two records exist in the database
        assertThat(records.size()).isGreaterThanOrEqualTo(2);
    }
}