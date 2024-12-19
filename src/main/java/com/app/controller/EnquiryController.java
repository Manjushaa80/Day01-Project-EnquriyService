package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.EnquiryRequestDTO;
import com.app.dto.EnquiryResponseDTO;
import com.app.entity.Enquiry;
import com.app.exception.EnquiryNotFoundException;
import com.app.resource.EnquiryResource;
import com.app.service.EnquiryService;

import java.util.List;

@RestController
@RequestMapping(value = "/enquiry")

public class EnquiryController {

	@Autowired
	private EnquiryService enquiryService;

	 private EnquiryResource enquiryResource;

	public EnquiryController(EnquiryResource enquiryResource) {
		super();
		this.enquiryResource = enquiryResource;
	}

	@PutMapping(value = "/edit-enquiry/{customerID}")
	public ResponseEntity<Enquiry> editEnquiry(@PathVariable int customerID, @RequestBody Enquiry enquiryDetails) {

		Enquiry editedData = enquiryService.editEnquiry(customerID, enquiryDetails);

		if (editedData != null) {
			return new ResponseEntity<Enquiry>(editedData, HttpStatus.OK);
		}

		return new ResponseEntity<Enquiry>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping(value = "/update-enquiry-status/{customerID}")
	public ResponseEntity<Enquiry> updateEnquiry(@PathVariable int customerID, @RequestBody Enquiry enquiryDetails) {

		Enquiry updateEnquiry = enquiryService.updateEnquiry(customerID, enquiryDetails);

		if (updateEnquiry != null) {
			return new ResponseEntity<Enquiry>(updateEnquiry, HttpStatus.OK);
		}
		return new ResponseEntity<Enquiry>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/delete-enquiry/{customerID}")
	public ResponseEntity<Void> deleteEnquiry(@PathVariable Integer customerID) {
		Boolean flag = enquiryService.deleteEnquiry(customerID);
		if (flag) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/expose-enquiries")
	public ResponseEntity<List<Enquiry>> getAllEnquiry() {
		List<Enquiry> enquiryList = enquiryService.getAllEnquiry();
		if (enquiryList.isEmpty()) {
			return new ResponseEntity<List<Enquiry>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Enquiry>>(enquiryList, HttpStatus.OK);

	}

	@GetMapping(value = "/expose-enquiry/{customerID}")
	public ResponseEntity<Enquiry> getEnquiry(@PathVariable Integer customerID) throws EnquiryNotFoundException {
		Enquiry enquiry2 = enquiryService.getEnquiry(customerID);
		if(enquiry2!=null)
		{
			return new ResponseEntity<Enquiry>(enquiry2, HttpStatus.OK);
		
		}
		 throw new EnquiryNotFoundException("Enquiry Not Found for Customer ID: " + customerID);
		

	}

	@PostMapping(value = "/save-enquiry")
	public ResponseEntity<EnquiryResponseDTO> saveEnquiry(@RequestBody EnquiryRequestDTO enquiryRequestDTO) {
		EnquiryResponseDTO saveEnquiryResponseDTO = enquiryResource.saveEnquiry(enquiryRequestDTO);

		return new ResponseEntity<EnquiryResponseDTO>(saveEnquiryResponseDTO, HttpStatus.CREATED);
	}

}
