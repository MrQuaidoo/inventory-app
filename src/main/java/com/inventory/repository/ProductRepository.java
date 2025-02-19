package com.inventory.repository;

import com.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Product entities.
 * Provides CRUD operations and database interactions for products.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}