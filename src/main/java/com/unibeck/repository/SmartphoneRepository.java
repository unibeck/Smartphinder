package com.unibeck.repository;


import com.unibeck.model.Brand;
import com.unibeck.model.NormalizedValue;
import com.unibeck.model.OS;
import com.unibeck.model.Smartphone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jbeckman on 11/18/16.
 */
@Repository
public interface SmartphoneRepository extends PagingAndSortingRepository<Smartphone, String> {
    List<Smartphone> findAll();

    List<Smartphone> findByOperatingSystem(OS os);
    @Query("select sp from Smartphone sp where sp.operatingSystem <> :operatingSystem")
    List<Smartphone> findByOperatingSystemNot(@Param("operatingSystem") OS operatingSystem);

    List<Smartphone> findByBrand(Brand brand);
    @Query("select sp from Smartphone sp where sp.brand <> :brand")
    List<Smartphone> findByBrandNot(@Param("brand") Brand brand);

    List<Smartphone> findByPriceGreaterThan(NormalizedValue price);
    List<Smartphone> findByPriceLessThan(NormalizedValue price);

    List<Smartphone> findByCameraGreaterThan(NormalizedValue camera);
    List<Smartphone> findByCameraLessThan(NormalizedValue camera);

    List<Smartphone> findByDisplaySizeGreaterThan(NormalizedValue displaySize);
    List<Smartphone> findByDisplaySizeLessThan(NormalizedValue displaySize);

    List<Smartphone> findByRamGreaterThan(NormalizedValue ram);
    List<Smartphone> findByRamLessThan(NormalizedValue ram);

    List<Smartphone> findByBatteryGreaterThan(NormalizedValue ram);
    List<Smartphone> findByBatteryLessThan(NormalizedValue ram);
}
