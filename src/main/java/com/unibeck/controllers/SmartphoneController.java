package com.unibeck.controllers;

import com.unibeck.model.Smartphone;
import com.unibeck.model.UserConstraint;
import com.unibeck.services.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@RequestMapping(value = "/smartphone/constraint", method = RequestMethod.POST)
	@ResponseStatus(value= HttpStatus.OK)
	public ResponseEntity<List<Smartphone>> findSmartphonesRelatedTo(@RequestBody @Valid UserConstraint userConstraint) {

		List<Smartphone> smartphones = smartphoneService.findClosestMatching(userConstraint);

		if(smartphones.size() == 0) {
			return new ResponseEntity<>((List<Smartphone>)null, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(smartphones, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/smartphones", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public List<Smartphone> getAllSmartphones() {
		return smartphoneService.getAllSmartphones();
	}
}
