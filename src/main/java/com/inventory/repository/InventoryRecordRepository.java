package com.inventory.repository;

import com.inventory.model.InventoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing InventoryRecord entities.
 * Provides CRUD operations and database interactions for inventory records.
 */
public interface InventoryRecordRepository extends JpaRepository<InventoryRecord, Long> {
}