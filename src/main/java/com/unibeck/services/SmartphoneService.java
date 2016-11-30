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

    public List<Smartphone> findClosestMatching(UserConstraint uC) {
        Percentiles per = new Percentiles();
        NormalizedValue price = convertFromWithInt(uC.getPrice(), per.getPricePercentile());
        NormalizedValue battery = convertFromWithInt(uC.getBattery(), per.getBatteryPercentile());
        NormalizedValue camera = convertFromWithInt(uC.getCamera(), per.getCameraPercentile());
        NormalizedValue ram = convertFromWithDouble(uC.getRam(), per.getRamPercentile());
        NormalizedValue storage = convertFromWithInt(uC.getStorage(), per.getStoragePercentile());
        NormalizedValue resolution = convertFromWithInt(uC.getResolution(), per.getDisplayResolutionPercentile());
        NormalizedValue displaySize = convertFromWithDouble(uC.getDisplaySize(), per.getDisplaySizePercentile());

        List<Smartphone> allSmartphones = smartphoneRepository.findAll();



        /*
            Constraint Zero
        */
        List<Smartphone> brandConstraint = allSmartphones
                  .parallelStream()
                  .filter(sp ->
                      sp.getBrand().equals(uC.getBrand())
                  )
                  .collect(Collectors.toList());

        /*
            Constraint Zero
        */
        List<Smartphone> osConstraint = brandConstraint
                  .parallelStream()
                  .filter(sp ->
                      sp.getOperatingSystem().equals(uC.getOperatingSystem())
                  )
                  .collect(Collectors.toList());

        /*
            Constraint Zero
        */
        List<Smartphone> priceConstraint = osConstraint
                  .parallelStream()
                  .filter(sp ->
                      Math.abs(sp.getPrice().compareTo(price)) <= 1
                  )
                  .collect(Collectors.toList());

        /*
            Constraint One
        */
        List<Smartphone> batteryConstraint = priceConstraint
                .parallelStream()
                .filter(sp ->
                      Math.abs(sp.getBattery().compareTo(battery)) <= 1
                )
                .collect(Collectors.toList());

        /*
            Constraint Two
        */
        List<Smartphone> cameraConstraint = batteryConstraint
                .parallelStream()
                .filter(sp ->
                      Math.abs(sp.getCamera().compareTo(camera)) <= 1
                )
                .collect(Collectors.toList());

        /*
            Constraint Three
        */
        List<Smartphone> ramConstraint = cameraConstraint
                .parallelStream()
                .filter(sp ->
                      Math.abs(sp.getRam().compareTo(ram)) <= 1
                )
                .collect(Collectors.toList());

        /*
            Constraint Four
        */
        List<Smartphone> storageConstraint = ramConstraint
                .parallelStream()
                .filter(sp ->
                      Math.abs(sp.getStorage().compareTo(storage)) <= 1
                )
                .collect(Collectors.toList());

        /*
            Constraint Five
        */
        List<Smartphone> resolutionConstraint = storageConstraint
                .parallelStream()
                .filter(sp ->
                      Math.abs(sp.getDisplayResolution().compareTo(resolution)) <= 1
                )
                .collect(Collectors.toList());

        /*
            Constraint Six
        */
        List<Smartphone> displaySizeConstraint = resolutionConstraint
                .parallelStream()
                .filter(sp ->
                      Math.abs(sp.getDisplaySize().compareTo(displaySize)) <= 1
                )
                .collect(Collectors.toList());

        return displaySizeConstraint;
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