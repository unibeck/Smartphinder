package com.unibeck.services;

import com.unibeck.model.*;
import com.unibeck.repository.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.unibeck.model.Percentiles.convertFromWithDouble;
import static com.unibeck.model.Percentiles.convertFromWithInt;

/**
 * Created by jbeckman on 11/18/16.
 */
@Service
public class SmartphoneService {

    private SmartphoneRepository smartphoneRepository;

    @Autowired
    public SmartphoneService(SmartphoneRepository smartphoneRepository) {
        this.smartphoneRepository = smartphoneRepository;
    }

    public List<Smartphone> getAllSmartphones() {
        return smartphoneRepository.findAll();
    }

    public List<Smartphone> findClosestMatching(UserConstraint userConstraint) {
        Percentiles per = new Percentiles();
        NormalizedValue price = convertFromWithInt(userConstraint.getPrice(), per.getPricePercentile());
        NormalizedValue battery = convertFromWithInt(userConstraint.getBattery(), per.getBatteryPercentile());
        NormalizedValue camera = convertFromWithInt(userConstraint.getCamera(), per.getCameraPercentile());
        NormalizedValue ram = convertFromWithDouble(userConstraint.getRam(), per.getRamPercentile());
        NormalizedValue storage = convertFromWithInt(userConstraint.getStorage(), per.getStoragePercentile());
        NormalizedValue resolution = convertFromWithInt(userConstraint.getResolution(), per.getDisplayResolutionPercentile());
        NormalizedValue displaySize = convertFromWithDouble(userConstraint.getDisplaySize(), per.getDisplaySizePercentile());

        //Let's first filter out all other operating systems
        List<Smartphone> remainder = smartphoneRepository.findAll();
        List<Smartphone> reserve;

        /* Constraint 1:
            If the brand is Apple, then ram can't be better than NormalizedValue.THREE
        */
        if(userConstraint.getBrand() == Brand.APPLE) {
            reserve = smartphoneRepository.findByRamLessThan(NormalizedValue.FOUR);
            remainder.retainAll(reserve);
        }

        /* Constraint 2:
            If price is in the lower 40 percentile, then the camera can't be better than NormalizedValue.TWO
        */
        if(price.compareTo(NormalizedValue.THREE) < 0) {
            reserve = smartphoneRepository.findByCameraLessThan(NormalizedValue.THREE);
            remainder.retainAll(reserve);
        }

        /* Constraint 3:
            If the battery is in the lower 40 percentile, then the displaySize can't be better than NormalizedValue.TWO
            If the battery is in the greater 60 percentile, then the displaySize must be better than NormalizedValue.TWO
         */
        if(battery.compareTo(NormalizedValue.THREE) < 0) {
            reserve = smartphoneRepository.findByDisplaySizeLessThan(NormalizedValue.THREE);
            remainder.retainAll(reserve);
        } else {
            reserve = smartphoneRepository.findByDisplaySizeGreaterThan(NormalizedValue.TWO);
            remainder.retainAll(reserve);
        }

        return remainder;
    }
}
