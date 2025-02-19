package com.inventory.controller;

import com.inventory.model.InventoryRecord;
import com.inventory.model.Product;
import com.inventory.service.InventoryRecordService;
import com.inventory.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for generating inventory reports.
 * Provides endpoints for retrieving reports related to low stock products and inventory records.
 */
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ProductService productService;
    private final InventoryRecordService inventoryService;

    /**
     * Constructor to inject dependencies.
     *
     * @param productService   Service for handling product-related operations.
     * @param inventoryService Service for handling inventory records.
     */
    ReportController(ProductService productService, InventoryRecordService inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    /**
     * Retrieves a list of products that are low in stock.
     *
     * @return List of products that have reached the restocking threshold.
     */
    @GetMapping("/low-stock")
    public List<Product> getLowStockProducts() {
        return productService.getLowStockProducts();
    }

    /**
     * Retrieves a report of all products in the inventory.
     *
     * @return List of all products.
     */
    @GetMapping("/all")
    public List<Product> getAllProductsReport() {
        return productService.getAllProducts();
    }

    /**
     * Retrieves a list of all inventory records.
     *
     * @return List of inventory transactions.
     */
    @GetMapping("/inventory-records")
    public List<InventoryRecord> listInventoryRecords() {
        return inventoryService.getAllRecords();
    }
}
