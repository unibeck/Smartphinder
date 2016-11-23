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


        List<Smartphone> remainder = smartphoneRepository.findAll();
        List<Smartphone> reserve;

        /* Constraint 1/2:
            If the brand is Apple, then ram can't be better than NormalizedValue.THREE
            AND the operatingSystem must be iOS

            Else find all phones that aren't iOS
        */
        if(userConstraint.getOperatingSystem() == OS.iOS) {
            reserve = smartphoneRepository.findByRamLessThan(NormalizedValue.FOUR);
            remainder.retainAll(reserve);

            reserve = smartphoneRepository.findByBrand(Brand.APPLE);
            remainder.retainAll(reserve);
        } else if (userConstraint.getOperatingSystem() == OS.ANDROID) {
            reserve = smartphoneRepository.findByBrandNot(Brand.MICROSOFT);
            remainder.retainAll(reserve);

            reserve = smartphoneRepository.findByBrandNot(Brand.APPLE);
            remainder.retainAll(reserve);
        } else {
            reserve = smartphoneRepository.findByBrand(Brand.MICROSOFT);
            remainder.retainAll(reserve);
        }
        if(remainder.size() <= 5) {
            return remainder;
        }

        /* Constraint 3:
            If price is in the lower 40 percentile, then the camera can't be better than NormalizedValue.TWO
        */
        if(price.compareTo(NormalizedValue.THREE) < 0) {
            reserve = smartphoneRepository.findByCameraLessThan(NormalizedValue.THREE);
            remainder.retainAll(reserve);
        }
        if(remainder.size() <= 5) {
            return remainder;
        }

        /* Constraint 4:
            If the displaySize is in the lower 40 percentile, then the battery can't be better than NormalizedValue.TWO
            If the displaySize is in the greater 60 percentile, then the battery must be better than NormalizedValue.TWO
         */
        if(displaySize.compareTo(NormalizedValue.THREE) < 0) {
            reserve = smartphoneRepository.findByBatteryLessThan(NormalizedValue.THREE);
            remainder.retainAll(reserve);
        } else {
            reserve = smartphoneRepository.findByBatteryGreaterThan(NormalizedValue.TWO);
            remainder.retainAll(reserve);
        }
        if(remainder.size() <= 5) {
            return remainder;
        }

        /* Constraint 5:
            If the resolution is in the lower 60 percentile, then the price can't be better than NormalizedValue.THREE
            If the resolution is in the greater 40 percentile, then the price must be better than NormalizedValue.THREE
         */
        if(resolution.compareTo(NormalizedValue.FOUR) < 0) {
            reserve = smartphoneRepository.findByPriceLessThan(NormalizedValue.FOUR);
            remainder.retainAll(reserve);
        } else {
            reserve = smartphoneRepository.findByPriceGreaterThan(NormalizedValue.THREE);
            remainder.retainAll(reserve);
        }
        if(remainder.size() <= 5) {
            return remainder;
        }

        /* Constraint 6:
            If the battery is in the greater 40 percentile, then the OS can't be Apple
         */
        if(battery.compareTo(NormalizedValue.THREE) < 0) {
            reserve = smartphoneRepository.findByBrandNot(Brand.APPLE);
            remainder.retainAll(reserve);
        }

        return remainder;
    }
}
