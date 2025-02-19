package com.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a record of inventory changes.
 * Stores details of stock transactions such as product ID, name and quantity removed.
 */
@Setter
@Getter
@Entity
public class InventoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Unique identifier for the inventory record
    private Long id;

    // The ID of the product associated with this record
    private Long productId;

    // The name of the product
    private String productName;

    // The quantity of the product that was removed from inventory
    private int quantityRemoved;

    public InventoryRecord() {}

    /**
     * Parameterized constructor to create an inventory record.
     *
     * @param productId       The ID of the product.
     * @param productName     The name of the product.
     * @param quantityRemoved The quantity removed from inventory.
     */
    public InventoryRecord(Long productId, String productName, int quantityRemoved) {
        this.productId = productId;
        this.productName = productName;
        this.quantityRemoved = quantityRemoved;
    }
}