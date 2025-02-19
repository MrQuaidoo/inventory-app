package com.inventory.controller;

import com.inventory.model.Product;
import com.inventory.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing products in the inventory.
 * Provides CRUD operations and stock management functionalities.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    /**
     * Constructor to inject the product service dependency.
     *
     * @param service Service for handling product operations.
     */
    ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Retrieves a list of all products in the inventory.
     *
     * @return List of products.
     */
    @GetMapping
    public List<Product> listAllProducts() {
        return service.getAllProducts();
    }

    /**
     * Retrieves a specific product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product if found.
     */
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return service.getProductById(id);
    }

    /**
     * Creates a new product in the inventory.
     *
     * @param product The product to be created.
     * @return The created product.
     */
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    /**
     * Updates an existing product.
     *
     * @param id            The ID of the product to update.
     * @param updatedProduct The updated product details.
     * @return The updated product.
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return service.updateProduct(id, updatedProduct);
    }

    /**
     * Deletes a product from the inventory.
     *
     * @param id The ID of the product to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
    }

    /**
     * Removes a specified quantity from a product's stock.
     *
     * @param id       The ID of the product.
     * @param quantity The quantity to remove.
     */
    @PostMapping("/{id}/remove")
    public void removeProductStock(@PathVariable Long id, @RequestParam int quantity) {
        service.removeProductStock(id, quantity);
    }
}
