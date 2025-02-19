package com.inventory.common;

import com.inventory.model.Product;
import com.inventory.service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Scheduled task to automatically restock products when their stock levels fall below the threshold.
 * This ensures that inventory levels are maintained without manual intervention.
 */
@Component
public class RestockScheduler {

    private final ProductService service;

    /**
     * Constructor to inject the ProductService dependency.
     *
     * @param service Service for handling product-related operations.
     */
    RestockScheduler(ProductService service) {
        this.service = service;
    }

    /**
     * Scheduled task that runs every 60 seconds to check and restock products.
     * If a product's stock level is at or below the restocking threshold, additional stock is added.
     */
    @Scheduled(fixedRate = 60000) // Executes the method every 60 seconds (60000 milliseconds)
    public void restockProducts() {
        // Retrieve all products
        List<Product> products = service.getAllProducts();

        for (Product product : products) {

            // Check if the product's stock level is below or equal to the restocking threshold
            if (product.getStockLevel() <= product.getRestockThreshold()) {
                // Increase stock level by the restocking amount
                product.setStockLevel(product.getStockLevel() + product.getRestockAmount());

                // Save the updated product details
                service.saveProduct(product);
            }
        }
    }
}
