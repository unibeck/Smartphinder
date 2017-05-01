package com.unibeck;

import com.unibeck.model.*;
import com.unibeck.repository.CustomerRepository;
import com.unibeck.repository.InventoryRepository;
import com.unibeck.repository.LocationRepository;
import com.unibeck.repository.SmartphoneRepository;
import com.unibeck.services.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.unibeck.model.Percentiles.convertFromWithDouble;
import static com.unibeck.model.Percentiles.convertFromWithInt;

/**
 * Created by jbeckman on 11/20/16.
 */
@Component
@Profile("prod")
public class SeedDatabase implements CommandLineRunner {
    private CustomerRepository customerRepository;
    private SmartphoneRepository smartphoneRepository;
    private InventoryRepository inventoryRepository;
    private LocationRepository locationRepository;
    private UserService userService;

    @Autowired
    public SeedDatabase(CustomerRepository customerRepository,
                        SmartphoneRepository smartphoneRepository,
                        InventoryRepository inventoryRepository,
                        LocationRepository locationRepository,
                        UserService userService) {

        this.customerRepository = customerRepository;
        this.smartphoneRepository = smartphoneRepository;
        this.inventoryRepository = inventoryRepository;
        this.locationRepository = locationRepository;
        this.userService = userService;
    }

    public void run(String... args) {
        seedCustomers();
        seedLocation();
        seedSmartphones();
        seedInventory();
    }

    public void seedTables() {
        run("");
    }

    private void seedCustomers() {
        Customer newCustomer = new Customer()
                .withDisplayName("Jonathan")
                .withUsername("xXjonXx")
                .withPassword(userService.passwordEncoder().encode("jon"));
        customerRepository.save(newCustomer);

        newCustomer = new Customer()
                .withDisplayName("Jed")
                .withUsername("xXjedXx")
                .withPassword(userService.passwordEncoder().encode("jed"));
        customerRepository.save(newCustomer);

        newCustomer = new Customer()
                .withDisplayName("Sau")
                .withUsername("xXsauXx")
                .withPassword(userService.passwordEncoder().encode("sau"));
        customerRepository.save(newCustomer);

        newCustomer = new Customer()
                .withDisplayName("Addison")
                .withUsername("xXaddisonXx")
                .withPassword(userService.passwordEncoder().encode("addison"));
        customerRepository.save(newCustomer);
    }

    private void seedLocation() {
        Location newLocation = new Location()
                .withCity("Phoenix")
                .withState("Arizona")
                .withLongitude(33.45)
                .withLatitude(-122.07);
        locationRepository.save(newLocation);

        newLocation = new Location()
                .withCity("Stockton")
                .withState("California")
                .withLongitude(37.97)
                .withLatitude(-121.30);
        locationRepository.save(newLocation);

        newLocation = new Location()
                .withCity("Davenport")
                .withState("Florida")
                .withLongitude(28.16)
                .withLatitude(-81.60);
        locationRepository.save(newLocation);

        newLocation = new Location()
                .withCity("Indianapolis")
                .withState("Indiana")
                .withLongitude(39.77)
                .withLatitude(-86.15);
        locationRepository.save(newLocation);

        newLocation = new Location()
                .withCity("Louisville")
                .withState("Kentucky")
                .withLongitude(38.22)
                .withLatitude(-85.74);
        locationRepository.save(newLocation);

        newLocation = new Location()
                .withCity("Nashua")
                .withState("New Hampshire")
                .withLongitude(42.75)
                .withLatitude(-71.46);
        locationRepository.save(newLocation);

        newLocation = new Location()
                .withCity("Durham")
                .withState("North Carolina")
                .withLongitude(35.98)
                .withLatitude(-78.90);
        locationRepository.save(newLocation);

        newLocation = new Location()
                .withCity("Avenel")
                .withState("New Jersey")
                .withLongitude(40.58)
                .withLatitude(-74.27);
        locationRepository.save(newLocation);

        newLocation = new Location()
                .withCity("Kenosha")
                .withState("Wisconsin")
                .withLongitude(42.58)
                .withLatitude(-87.84);
        locationRepository.save(newLocation);
    }

    private void seedSmartphones() {
        Smartphone newPhone;
        Percentiles per = new Percentiles();

        JSONParser parser = new JSONParser();
        JSONArray arr;

        try {
            arr = (JSONArray) parser.parse(new FileReader("src/main/resources/smartphone.json"));
        } catch (Exception e) {
            throw new IllegalStateException();
        }

        for (Object obj : arr) {
            JSONObject p = (JSONObject) obj;

            newPhone = new Smartphone()
                    .withName((String) p.get("device-name"))
                    .withBrand(Brand.findByAbbr((String) p.get("brand")))
                    .withOperatingSystem(OS.findByAbbr((String) p.get("os")))
                    .withPrice(convertFromWithInt(Integer.valueOf((String) p.get("price")),
                            per.getPricePercentile()))
                    .withDisplaySize(convertFromWithDouble(Double.valueOf((String) p.get("display-size")),
                            per.getDisplaySizePercentile()))
                    .withDisplayResolution(convertFromWithInt(Integer.valueOf((String) p.get("resolution")),
                            per.getDisplayResolutionPercentile()))
                    .withRam(convertFromWithDouble(Double.valueOf((String) p.get("ram")),
                            per.getRamPercentile()))
                    .withStorage(convertFromWithInt(Integer.valueOf((String) p.get("storage")),
                            per.getStoragePercentile()))
                    .withBattery(convertFromWithInt(Integer.valueOf((String) p.get("battery")),
                            per.getBatteryPercentile()))
                    .withCamera(convertFromWithInt(Integer.valueOf((String) p.get("camera")),
                            per.getCameraPercentile()));

            smartphoneRepository.save(newPhone);
        }
    }

    private void seedInventory() {
        List<Location> locations = locationRepository.findAll();

        for (Smartphone smartphone : smartphoneRepository.findAll()) {
            int numberOfLocations = new Random().ints(1, 4).limit(10).findAny().getAsInt();
            Collections.shuffle(locations);

            while (numberOfLocations-- > 0) {
                int stockNum = new Random().ints(1, 10).limit(10).findAny().getAsInt();

                Inventory inventory = new Inventory()
                        .withStock(stockNum)
                        .withLocation(locations.get(numberOfLocations))
                        .withSmartphone(smartphone);

                inventoryRepository.save(inventory);
            }
        }
    }
}
