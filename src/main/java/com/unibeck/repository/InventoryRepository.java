package com.unibeck.repository;


import com.unibeck.model.Inventory;
import com.unibeck.model.Smartphone;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jbeckman on 11/18/16.
 */
@Repository
public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long> {
    List<Inventory> findBySmartphone(Smartphone smartphone);
}
