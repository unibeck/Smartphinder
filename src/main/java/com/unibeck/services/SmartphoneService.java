package com.unibeck.services;

import com.unibeck.model.Inventory;
import com.unibeck.model.Location;
import com.unibeck.model.Smartphone;
import com.unibeck.model.UserConstraint;
import com.unibeck.repository.InventoryRepository;
import com.unibeck.repository.LocationRepository;
import com.unibeck.repository.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jbeckman on 1/18/17.
 */
@Service
public class SmartphoneService {

    private ConstraintSatisfactionAlgorithm constraintSatisfactionAlgorithm;
    private SmartphoneRepository smartphoneRepository;
    private InventoryRepository inventoryRepository;
    private LocationRepository locationRepository;

    @Autowired
    public SmartphoneService(ConstraintSatisfactionAlgorithm constraintSatisfactionAlgorithm,
                             SmartphoneRepository smartphoneRepository,
                             InventoryRepository inventoryRepository,
                             LocationRepository locationRepository) {

        this.constraintSatisfactionAlgorithm = constraintSatisfactionAlgorithm;
        this.smartphoneRepository = smartphoneRepository;
        this.inventoryRepository = inventoryRepository;
        this.locationRepository = locationRepository;
    }

    public List<Smartphone> getAllSmartphones() {
        return smartphoneRepository.findAll();
    }

    public List<Inventory> findMatchingInventory(UserConstraint userConstraint, String city, String state) {
        List<Smartphone> allSmartphones = smartphoneRepository.findAll();
        List<Smartphone> remainder = constraintSatisfactionAlgorithm.findClosestMatching(userConstraint, allSmartphones);

        Location userLocation = locationRepository.findByCityAndState(city, state);

        return remainder.stream()
                .map(smartphone -> determineInventory(smartphone, userLocation))
                .collect(Collectors.toList());
    }

    private Inventory determineInventory(Smartphone smartphone, Location userLocation) {
        List<Inventory> warehousesWithSmartphone = inventoryRepository.findBySmartphone(smartphone);

        Inventory closestInventory = warehousesWithSmartphone.stream()
                .reduce((inventory1, inventory2) -> {
                    double distance1 = Math.sqrt(
                            Math.pow((inventory1.getLocation().getLatitude() - userLocation.getLatitude()), 2) +
                            Math.pow((inventory1.getLocation().getLongitude() - userLocation.getLongitude()), 2));

                    double distance2 = Math.sqrt(
                            Math.pow((inventory2.getLocation().getLatitude() - userLocation.getLatitude()), 2) +
                            Math.pow((inventory2.getLocation().getLongitude() - userLocation.getLongitude()), 2));

                    return distance1 > distance2 ? inventory1 : inventory2;
                })
                .get();

        return closestInventory;
    }

    public void buySmartphone(long smartphoneId, String city, String state) {
        Smartphone smartphone = smartphoneRepository.findOne(smartphoneId);
        Location location = locationRepository.findByCityAndState(city, state);

        if (smartphone == null || location == null) {
            throw new IllegalStateException("Won't be able to find the inventory of smartphone [" + smartphone +
                    "] and location [" + location + "]");
        }

        Inventory smartphoneInventory = inventoryRepository.findBySmartphoneAndLocation(smartphone, location);
        smartphoneInventory.withStock(smartphoneInventory.getStock() - 1);
        inventoryRepository.save(smartphoneInventory);
    }
}
