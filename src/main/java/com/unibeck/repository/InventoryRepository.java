package com.unibeck.repository;


import com.unibeck.model.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jbeckman on 11/18/16.
 */
@Repository
public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long> {

}
