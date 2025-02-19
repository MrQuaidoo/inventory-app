import com.inventory.common.RestockScheduler;
import com.inventory.model.Product;
import com.inventory.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RestockScheduler class.
 * Ensures that products are restocked correctly when stock levels are low.
 */
@ExtendWith(MockitoExtension.class)
class RestockSchedulerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private RestockScheduler restockScheduler;

    /**
     * Tests that products below the restocking threshold are restocked correctly.
     */
    @Test
    void testRestockProducts() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Low Stock Product");
        product.setStockLevel(2);
        product.setRestockThreshold(5);
        product.setRestockAmount(10);

        when(productService.getAllProducts()).thenReturn(Collections.singletonList(product));

        restockScheduler.restockProducts();

        // Verify that the stock level has increased
        assertThat(product.getStockLevel()).isEqualTo(12);
        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    /**
     * Tests that products with sufficient stock are not restocked.
     */
    @Test
    void testRestockProductsNoLowStock() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Sufficient Stock Product");
        product.setStockLevel(10);
        product.setRestockThreshold(5);
        product.setRestockAmount(10);

        when(productService.getAllProducts()).thenReturn(Collections.singletonList(product));

        restockScheduler.restockProducts();

        // Verify that the stock level remains unchanged
        assertThat(product.getStockLevel()).isEqualTo(10);
        verify(productService, never()).saveProduct(any(Product.class));
    }
}