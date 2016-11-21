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
        List<Smartphone> closestSmartphones = new ArrayList<>();
        List<Constraint> constraints = buildConstraints(userConstraint);

        //TODO: create a constraint satisfaction algorithm here and find ~top 3 smartphones

//        closestSmartphones.add(optimalSmartphone);
        return closestSmartphones;
    }

    private List<Constraint> buildConstraints(UserConstraint userConstraint) {
        Percentiles per = new Percentiles();
        NormalizedValue price = convertFromWithInt(userConstraint.getPrice(), per.getPricePercentile());
        NormalizedValue battery = convertFromWithInt(userConstraint.getBattery(), per.getBatteryPercentile());
        NormalizedValue camera = convertFromWithInt(userConstraint.getCamera(), per.getCameraPercentile());
        NormalizedValue ram = convertFromWithDouble(userConstraint.getRam(), per.getRamPercentile());
        NormalizedValue storage = convertFromWithInt(userConstraint.getStorage(), per.getStoragePercentile());
        NormalizedValue resolution = convertFromWithInt(userConstraint.getResolution(), per.getDisplayResolutionPercentile());
        NormalizedValue displaySize = convertFromWithDouble(userConstraint.getDisplaySize(), per.getDisplaySizePercentile());

        List<Constraint> constraints = new ArrayList<>();
        try {
            if(price.compareTo(NormalizedValue.THREE) < 0) {
                //So if price is of magnitude ONE or TWO
                constraints.add(new Constraint(Smartphone.class.getDeclaredField("primary"), NormalizedValue.TWO));
            } else {
                constraints.add(new Constraint(Smartphone.class.getDeclaredField("primary"), NormalizedValue.FIVE));
            }

            if(battery.compareTo(NormalizedValue.THREE) < 0) {
                constraints.add(new Constraint(Smartphone.class.getDeclaredField("screenSize"), NormalizedValue.TWO));
            } else {
                constraints.add(new Constraint(Smartphone.class.getDeclaredField("screenSize"), NormalizedValue.FIVE));
            }

            //TODO: Add more constraints, but let's test these two first

        } catch (NoSuchFieldException e) {
            System.out.println("Could not find a declared field " + e);
        }

        return constraints;
    }
}
