package com.unibeck.repository;

import com.unibeck.model.Location;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jbeckman on 11/18/16.
 */
@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {
    List<Location> findAll();
}
