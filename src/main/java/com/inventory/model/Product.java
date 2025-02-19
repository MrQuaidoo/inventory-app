package com.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a product in the inventory system.
 * Stores product details such as name, stock levels and restock thresholds.
 */
@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Unique identifier for the product
    private Long id;

    // Name of the product
    private String name;

    // Current stock level of the product
    private int stockLevel;

    // Minimum stock level before restocking is triggered
    private int restockThreshold;

    // Quantity to restock when the threshold is reached
    private int restockAmount;
}
