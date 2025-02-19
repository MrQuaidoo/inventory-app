import com.inventory.InventoryApplication;
import com.inventory.model.Product;
import com.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the ProductRepository.
 * Ensures that database operations for Product entities work correctly.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InventoryApplication.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Tests saving a product to the database.
     * Ensures that a product can be persisted and has a generated ID.
     */
    @Test
    void testSaveProduct() {
        // Create a new product
        Product product = new Product();
        product.setName("Test Product");
        product.setStockLevel(10);
        product.setRestockThreshold(5);
        product.setRestockAmount(20);

        // Save the product to the database
        Product savedProduct = productRepository.save(product);

        // Verify that the product was saved and has an ID
        assertThat(savedProduct.getId()).isNotNull();
    }

    /**
     * Tests retrieving a product by its ID from the database.
     * Ensures that the correct product details are returned.
     */
    @Test
    void testFindProductById() {
        // Create and save a product
        Product product = new Product();
        product.setName("Test Product");
        product.setStockLevel(10);
        product.setRestockThreshold(5);
        product.setRestockAmount(20);

        Product savedProduct = productRepository.save(product);

        // Retrieve the product from the database by ID
        Product foundProduct = productRepository.findById(savedProduct.getId()).orElse(null);

        // Verify that the retrieved product matches the expected details
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getName()).isEqualTo("Test Product");
    }
}