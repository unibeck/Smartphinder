package com.unibeck.controllers;

import com.unibeck.model.Inventory;
import com.unibeck.model.Location;
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

	@RequestMapping(value = "/smartphone/constraint", method = RequestMethod.POST, params = {"city", "state"})
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Inventory>> findSmartphonesRelatedTo(@RequestBody UserConstraint userConstraint,
																	@RequestParam(value = "city") String city,
																	@RequestParam(value = "state") String state) {

		List<Inventory> remainder = smartphoneService.findMatchingInventory(userConstraint, city, state);

		if (remainder.size() == 0) {
			return new ResponseEntity<>((List<Inventory>) null, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(remainder, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/smartphone/{smartphoneId}/buy", method = RequestMethod.POST, params = {"city", "state"})
	public void buySmartphone(@PathVariable long smartphoneId,
							  @RequestParam(value = "city") String city,
							  @RequestParam(value = "state") String state) {

		smartphoneService.buySmartphone(smartphoneId, city, state);
	}

	@RequestMapping(value = "/smartphones", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Smartphone> getAllSmartphones() {
		return smartphoneService.getAllSmartphones();
	}
}
