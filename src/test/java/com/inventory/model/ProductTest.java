package com.inventory.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the Product model.
 * Ensures correct behavior of the Product entity, including creation and restocking.
 */
class ProductTest {

    /**
     * Tests the creation of a Product object and verifies that all attributes are set correctly.
     */
    @Test
    void testProductCreation() {
        // Create a new Product and set its attributes
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setStockLevel(10);
        product.setRestockThreshold(5);
        product.setRestockAmount(20);

        // Validate that the product attributes are correctly set
        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getName()).isEqualTo("Laptop");
        assertThat(product.getStockLevel()).isEqualTo(10);
        assertThat(product.getRestockThreshold()).isEqualTo(5);
        assertThat(product.getRestockAmount()).isEqualTo(20);
    }

    /**
     * Tests stock level modifications, ensuring that increasing and decreasing stock work correctly.
     */
    @Test
    void testStockModification() {
        // Create a new Product and set an initial stock level
        Product product = new Product();
        product.setStockLevel(15);

        // Decrease stock by 5
        product.setStockLevel(product.getStockLevel() - 5);

        // Verify stock decreased correctly
        assertThat(product.getStockLevel()).isEqualTo(10);

        // Increase stock by 10
        product.setStockLevel(product.getStockLevel() + 10);

        // Verify stock increased correctly
        assertThat(product.getStockLevel()).isEqualTo(20);
    }
}