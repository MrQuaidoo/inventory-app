import com.inventory.controller.ReportController;
import com.inventory.model.InventoryRecord;
import com.inventory.model.Product;
import com.inventory.service.InventoryRecordService;
import com.inventory.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Unit tests for ReportController.
 * Ensures that the report endpoints return correct data.
 */
@ExtendWith(MockitoExtension.class)
class ReportControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Mock
    private InventoryRecordService inventoryService;

    @InjectMocks
    private ReportController reportController;

    /**
     * Sets up the test environment before each test.
     * Initializes the MockMvc instance with the ReportController.
     */
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    /**
     * Tests the retrieval of low-stock products.
     * Ensures that products below the restocking threshold are correctly identified.
     */
    @Test
    void testGetLowStockProducts() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Low Stock Product");
        product.setStockLevel(2);
        product.setRestockAmount(5);
        product.setRestockThreshold(10);

        when(productService.getLowStockProducts()).thenReturn(List.of(product));

        mockMvc.perform(get("/api/reports/low-stock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Low Stock Product"));
    }

    /**
     * Tests retrieval of all products in the report.
     * Ensures that all products are returned in the response.
     */
    @Test
    void testGetAllProductsReport() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product A");
        product1.setStockLevel(10);
        product1.setRestockAmount(5);
        product1.setRestockThreshold(20);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product B");
        product2.setStockLevel(20);
        product2.setRestockAmount(10);
        product2.setRestockThreshold(30);

        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/api/reports/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    /**
     * Tests the retrieval of all inventory records.
     * Ensures that inventory changes are correctly listed.
     */
    @Test
    void testListInventoryRecords() throws Exception {
        when(inventoryService.getAllRecords()).thenReturn(Arrays.asList(
                new InventoryRecord(1L, "Product A", 5),
                new InventoryRecord(2L, "Product B", 10)
        ));

        mockMvc.perform(get("/api/reports/inventory-records"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].productName").value("Product A"))
                .andExpect(jsonPath("$[1].productName").value("Product B"));
    }
}