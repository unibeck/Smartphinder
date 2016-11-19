package com.unibeck.services;

import com.unibeck.model.Smartphone;
import com.unibeck.repository.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Smartphone> findClosestMatching(Smartphone optimalSmartphone) {
        List<Smartphone> closestSmartphones = new ArrayList<>();

        //TODO: create a constraint satisfaction algorithm here and find ~top 3 smartphones

        closestSmartphones.add(optimalSmartphone);
        return closestSmartphones;
    }
}
