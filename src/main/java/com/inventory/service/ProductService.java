package com.inventory.service;

import com.inventory.model.InventoryRecord;
import com.inventory.model.Product;
import com.inventory.repository.InventoryRecordRepository;
import com.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing product-related operations in the inventory.
 * Provides CRUD functionalities and stock management.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryRecordRepository inventoryRecordRepository;

    /**
     * Constructor to inject dependencies.
     *
     * @param productRepository          Repository for managing products.
     * @param inventoryRecordRepository  Repository for managing inventory records.
     */
    ProductService(ProductRepository productRepository, InventoryRecordRepository inventoryRecordRepository) {
        this.productRepository = productRepository;
        this.inventoryRecordRepository = inventoryRecordRepository;
    }

    /**
     * Retrieves all products from the inventory.
     *
     * @return List of products.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product if found.
     * @throws RuntimeException If the product is not found.
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    /**
     * Saves a new product in the inventory.
     *
     * @param product The product to be saved.
     * @return The saved product.
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates an existing product in the inventory.
     *
     * @param id             The ID of the product to update.
     * @param updatedProduct The updated product details.
     * @return The updated product.
     * @throws RuntimeException If the product is not found.
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setStockLevel(updatedProduct.getStockLevel());
            product.setRestockThreshold(updatedProduct.getRestockThreshold());
            product.setRestockAmount(updatedProduct.getRestockAmount());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    /**
     * Deletes a product from the inventory.
     *
     * @param id The ID of the product to delete.
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Retrieves a list of products that have reached or fallen below the restocking threshold.
     *
     * @return List of low-stock products.
     */
    public List<Product> getLowStockProducts() {
        return productRepository.findAll().stream()
                .filter(product -> product.getStockLevel() <= product.getRestockThreshold())
                .toList();
    }

    /**
     * Removes a specified quantity of stock from a product.
     * Also records the inventory transaction.
     *
     * @param productId The ID of the product.
     * @param quantity  The quantity to remove.
     * @throws RuntimeException If stock is insufficient.
     */
    public void removeProductStock(Long productId, int quantity) {
        Product product = getProductById(productId);
        if (product.getStockLevel() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        // Deduct stock
        product.setStockLevel(product.getStockLevel() - quantity);
        productRepository.save(product);

        // Record the stock removal in inventory records
        InventoryRecord record = new InventoryRecord(productId, product.getName(), quantity);
        inventoryRecordRepository.save(record);
    }
}
