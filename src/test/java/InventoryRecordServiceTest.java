import com.inventory.model.InventoryRecord;
import com.inventory.repository.InventoryRecordRepository;
import com.inventory.service.InventoryRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the InventoryRecordService.
 * Ensures that inventory record retrieval operations work as expected.
 */
@ExtendWith(MockitoExtension.class)
class InventoryRecordServiceTest {
    // Mock repository for database interactions
    @Mock
    private InventoryRecordRepository inventoryRecordRepository;

    // Mock InventoryRecordService
    @InjectMocks
    private InventoryRecordService inventoryRecordService;

    /**
     * Tests if retrieving all inventory records returns the expected list.
     */
    @Test
    void testGetAllRecords() {
        // Mock behavior of inventory record retrieval
        when(inventoryRecordRepository.findAll()).thenReturn(Arrays.asList(
                new InventoryRecord(1L, "Product A", 5),
                new InventoryRecord(2L, "Product B", 10)
        ));

        // Validate that the service returns exactly two records
        assertThat(inventoryRecordService.getAllRecords()).hasSize(2);
    }
}