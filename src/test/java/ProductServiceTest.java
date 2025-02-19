import com.inventory.model.InventoryRecord;
import com.inventory.model.Product;
import com.inventory.repository.InventoryRecordRepository;
import com.inventory.repository.ProductRepository;
import com.inventory.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ProductService.
 * Ensures that product-related business logic functions correctly.
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    // Mock ProductRepository
    @Mock
    private ProductRepository productRepository;

    // Mock InventoryRecordRepository
    @Mock
    private InventoryRecordRepository inventoryRecordRepository;

    // Mock ProductService
    @InjectMocks
    private ProductService productService;

    private Product product;

    /**
     * Initializes a sample product before each test.
     */
    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setStockLevel(10);
        product.setRestockAmount(5);
        product.setRestockThreshold(20);
    }

    /**
     * Tests retrieval of all products.
     * Ensures that the service fetches all available products.
     */
    @Test
    void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
        assertThat(productService.getAllProducts()).hasSize(1);
    }

    /**
     * Tests retrieval of a product by its ID.
     * Ensures that the correct product is returned.
     */
    @Test
    void testGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        assertThat(productService.getProductById(1L)).isEqualTo(product);
    }

    /**
     * Tests saving a product.
     * Ensures that a new product can be persisted successfully.
     */
    @Test
    void testSaveProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);
        assertThat(productService.saveProduct(product)).isEqualTo(product);
    }

    /**
     * Tests updating an existing product.
     * Ensures that the update logic correctly modifies the product.
     */
    @Test
    void testUpdateProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");
        updatedProduct.setStockLevel(15);
        updatedProduct.setRestockAmount(5);
        updatedProduct.setRestockThreshold(10);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        assertThat(productService.updateProduct(1L, updatedProduct)).isEqualTo(updatedProduct);
    }

    /**
     * Tests deleting a product.
     * Ensures that the product is removed from the repository.
     */
    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    /**
     * Tests retrieval of low-stock products.
     * Ensures that products below the restock threshold are correctly identified.
     */
    @Test
    void testGetLowStockProducts() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
        assertThat(productService.getLowStockProducts()).hasSize(1);
    }

    /**
     * Tests removing stock from a product.
     * Ensures that stock levels decrease correctly.
     */
    @Test
    void testRemoveProductStock() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(inventoryRecordRepository.save(any(InventoryRecord.class))).thenReturn(new InventoryRecord(1L, "Test Product", 5));
        productService.removeProductStock(1L, 5);
        assertThat(product.getStockLevel()).isEqualTo(5);
    }

    /**
     * Tests attempting to remove stock when there is insufficient inventory.
     * Ensures that an exception is thrown if the requested quantity exceeds available stock.
     */
    @Test
    void testRemoveProductStockInsufficientStock() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        assertThrows(RuntimeException.class, () -> productService.removeProductStock(1L, 15));
    }
}