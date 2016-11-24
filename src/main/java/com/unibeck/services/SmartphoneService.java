package com.unibeck.services;

import com.unibeck.model.*;
import com.unibeck.repository.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public ConstraintSatisfactionResult findClosestMatching(UserConstraint uC) {
        Percentiles per = new Percentiles();
        NormalizedValue price = convertFromWithInt(uC.getPrice(), per.getPricePercentile());
        NormalizedValue battery = convertFromWithInt(uC.getBattery(), per.getBatteryPercentile());
        NormalizedValue camera = convertFromWithInt(uC.getCamera(), per.getCameraPercentile());
        NormalizedValue ram = convertFromWithDouble(uC.getRam(), per.getRamPercentile());
        NormalizedValue storage = convertFromWithInt(uC.getStorage(), per.getStoragePercentile());
        NormalizedValue resolution = convertFromWithInt(uC.getResolution(), per.getDisplayResolutionPercentile());
        NormalizedValue displaySize = convertFromWithDouble(uC.getDisplaySize(), per.getDisplaySizePercentile());

        ConstraintSatisfactionResult constraintSatisfactionResult = reduceSearchSpace(
                uC.getBrand(),
                uC.getOperatingSystem(),
                price,
                battery,
                camera,
                ram,
                storage,
                resolution,
                displaySize
        );

        List<Smartphone> csSmartphones = constraintSatisfactionResult.getRemainder();
        // Let's search this reduce search space and find any smartphone within a padding of 1
        List<Smartphone> remainder = csSmartphones
                  .stream()
                  .filter(sp ->
                          Math.abs(sp.getPrice().compareTo(price)) <= 1 &&
                          Math.abs(sp.getBattery().compareTo(battery)) <= 1 &&
                          Math.abs(sp.getRam().compareTo(ram)) <= 1 &&
                          Math.abs(sp.getStorage().compareTo(storage)) <= 1 &&
                          Math.abs(sp.getDisplayResolution().compareTo(displaySize)) <= 1 &&
                          Math.abs(sp.getDisplaySize().compareTo(displaySize)) <= 1
                  )
                  .collect(Collectors.toList());

        return new ConstraintSatisfactionResult(remainder, constraintSatisfactionResult.getConstraintsUsed());
    }

    private ConstraintSatisfactionResult reduceSearchSpace(Brand brand,
                                                           OS operatingSystem,
                                                           NormalizedValue price,
                                                           NormalizedValue battery,
                                                           NormalizedValue camera,
                                                           NormalizedValue ram,
                                                           NormalizedValue storage,
                                                           NormalizedValue resolution,
                                                           NormalizedValue displaySize) {

        List<Smartphone> remainder = smartphoneRepository.findAll();
        List<Smartphone> reserve;

        boolean[] constraintsUsed = {false, false, false, false, false, false};
        List<Smartphone> backTrack = remainder;

        /* Constraint 0/1:
            If the operatingSystem is iOS, then ram can't be better than NormalizedValue.THREE
            AND the brand must be Apple

            If the operatingSystem is Android, then brand can't be Microsoft
            AND the brand can't be Apple

            Else the operatingSystem must be Microsoft, then the brand must be Microsoft
        */
        if(operatingSystem == OS.iOS) {
            reserve = smartphoneRepository.findByRamLessThan(NormalizedValue.FOUR);
            remainder.retainAll(reserve);

            reserve = smartphoneRepository.findByBrand(Brand.APPLE);
            remainder.retainAll(reserve);
        } else if (operatingSystem == OS.ANDROID) {
            reserve = smartphoneRepository.findByBrandNot(Brand.MICROSOFT);
            remainder.retainAll(reserve);

            reserve = smartphoneRepository.findByBrandNot(Brand.APPLE);
            remainder.retainAll(reserve);
        } else {
            reserve = smartphoneRepository.findByBrand(Brand.MICROSOFT);
            remainder.retainAll(reserve);
        }
        constraintsUsed[0] = true; constraintsUsed[1] = true;

        if(remainder.isEmpty()) {
            constraintsUsed[0] = false; constraintsUsed[1] = false;
            remainder = backTrack;
        } else if(remainder.size() <= 5) {
            return new ConstraintSatisfactionResult(remainder, constraintsUsed);
        } else {
            backTrack = remainder;
        }

        /* Constraint 2:
            If price is in the lower 40 percentile, then the camera can't be better than NormalizedValue.TWO
        */
        if(price.compareTo(NormalizedValue.THREE) < 0) {
            reserve = smartphoneRepository.findByCameraLessThan(NormalizedValue.THREE);
            remainder.retainAll(reserve);
        }
        constraintsUsed[2] = true;

        if(remainder.isEmpty()) {
            constraintsUsed[2] = false;
            remainder = backTrack;
        } else if(remainder.size() <= 5) {
            return new ConstraintSatisfactionResult(remainder, constraintsUsed);
        } else {
            backTrack = remainder;
        }

        /* Constraint 3:
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
        constraintsUsed[3] = true;

        if(remainder.isEmpty()) {
            constraintsUsed[3] = false;
            remainder = backTrack;
        } else if(remainder.size() <= 5) {
            return new ConstraintSatisfactionResult(remainder, constraintsUsed);
        } else {
            backTrack = remainder;
        }

        /* Constraint 4:
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
        constraintsUsed[4] = true;

        if(remainder.isEmpty()) {
            constraintsUsed[4] = false;
            remainder = backTrack;
        } else if(remainder.size() <= 5) {
            return new ConstraintSatisfactionResult(remainder, constraintsUsed);
        } else {
            backTrack = remainder;
        }

        /* Constraint 5:
            If the battery is in the greater 40 percentile, then the OS can't be Apple
         */
        if(battery.compareTo(NormalizedValue.THREE) < 0) {
            reserve = smartphoneRepository.findByBrandNot(Brand.APPLE);
            remainder.retainAll(reserve);
        }
        constraintsUsed[5] = true;

        if(remainder.isEmpty()) {
            constraintsUsed[5] = false;
            remainder = backTrack;
        }

        return new ConstraintSatisfactionResult(remainder, constraintsUsed);
    }
}

//    List<Smartphone> remainder = csSmartphones
//            .stream()
//            .filter(sp ->
//                    sp.getPrice().ordinal() + sp.getCamera().ordinal() == price.ordinal() + camera.ordinal() &&
//                            sp.getDisplaySize().ordinal() + sp.getBattery().ordinal() == displaySize.ordinal() + battery.ordinal() &&
//                            sp.getDisplayResolution().ordinal() + sp.getPrice().ordinal() == resolution.ordinal() + price.ordinal()
//            )
//            .collect(Collectors.toList());