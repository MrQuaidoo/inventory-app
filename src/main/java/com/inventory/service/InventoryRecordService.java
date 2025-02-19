package com.inventory.service;

import com.inventory.model.InventoryRecord;
import com.inventory.repository.InventoryRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing inventory records.
 * Handles retrieval of all inventory transactions.
 */
@Service
public class InventoryRecordService {

    private final InventoryRecordRepository repository;

    /**
     * Constructor to inject the InventoryRecordRepository dependency.
     *
     * @param repository Repository for performing CRUD operations on inventory records.
     */
    InventoryRecordService(InventoryRecordRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all inventory records from the database.
     *
     * @return List of inventory records.
     */
    public List<InventoryRecord> getAllRecords() {
        return repository.findAll();
    }
}
