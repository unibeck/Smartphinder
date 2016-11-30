package com.unibeck.controllers;

import com.unibeck.model.ConstraintSatisfactionResult;
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
	public ResponseEntity<ConstraintSatisfactionResult> findSmartphonesRelatedTo(@RequestBody @Valid UserConstraint userConstraint) {

		ConstraintSatisfactionResult csr = smartphoneService.findClosestMatching(userConstraint);

		if(csr.getRemainder().size() == 0) {
			return new ResponseEntity<>((ConstraintSatisfactionResult) null, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(csr, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/smartphones", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public List<Smartphone> getAllSmartphones() {
		return smartphoneService.getAllSmartphones();
	}
}
