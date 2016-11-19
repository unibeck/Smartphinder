package com.unibeck.controllers;

import com.unibeck.model.Smartphone;
import com.unibeck.services.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Created by jbeckman on 11/10/2016.
 */
@RestController
public class SmartphoneController {

	private SmartphoneService smartphoneService;

	@Autowired
	public SmartphoneController(SmartphoneService smartphoneService) {
		this.smartphoneService = smartphoneService;
	}

	@RequestMapping(value = "/smartphone/preferences", method = RequestMethod.POST)
	@ResponseStatus(value= HttpStatus.OK)
	public List<Smartphone> setSmartphonePrefernces(@RequestBody @Valid Smartphone optimalSmartphone) {
		return smartphoneService.findClosestMatching(optimalSmartphone);
	}

	@RequestMapping(value = "/smartphone/preferences", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public List<Smartphone> getAllSmartphones() {
		return smartphoneService.getAllSmartphones();
	}
}
