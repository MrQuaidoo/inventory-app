package com.inventory.controller;

import com.inventory.model.Product;
import com.inventory.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for the ProductController.
 * Ensures that product-related API endpoints behave as expected.
 */
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private MockMvc mockMvc;

    // Mocked ProductService
    @Mock
    private ProductService productService;

    // Mock ProductController
    @InjectMocks
    private ProductController productController;

    /**
     * Sets up MockMvc before each test.
     */
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    /**
     * Tests retrieving all products.
     */
    @Test
    void testGetAllProducts() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product A");
        product1.setStockLevel(10);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product B");
        product2.setStockLevel(20);

        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        // Verifies that two products are returned
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    /**
     * Tests retrieving a product by ID.
     */
    @Test
    void testGetProductById() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setStockLevel(10);

        when(productService.getProductById(1L)).thenReturn(product);

        // Verifies the returned product name
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    /**
     * Tests creating a new product.
     */
    @Test
    void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("New Product");
        product.setStockLevel(15);

        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        // Validates that the product was created
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New Product\", \"stockLevel\": 15, \"restockThreshold\": 5, \"restockAmount\": 10}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Product"));
    }

    /**
     * Tests updating an existing product.
     */
    @Test
    void testUpdateProduct() throws Exception {
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");
        updatedProduct.setStockLevel(20);

        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(updatedProduct);

        // Validates that the product was updated
        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Product\", \"stockLevel\": 20, \"restockThreshold\": 5, \"restockAmount\": 10}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"));
    }

    /**
     * Tests deleting a product by ID.
     */
    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        // Verifies that the product was deleted
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isOk());
    }

    /**
     * Tests removing stock from a product.
     */
    @Test
    void testRemoveProductStock() throws Exception {
        doNothing().when(productService).removeProductStock(1L, 5);

        // Validates that stock was removed
        mockMvc.perform(post("/api/products/1/remove")
                        .param("quantity", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}