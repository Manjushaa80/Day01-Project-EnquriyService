package com.app.resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dto.EnquiryRequestDTO;
import com.app.dto.EnquiryResponseDTO;
import com.app.entity.Enquiry;
import com.app.entity.EnquiryStatus;
import com.app.service.CibilService;
import com.app.service.EnquiryService;

@Component
public class EnquiryResource {
	@Autowired
	private EnquiryService enquiryService;
	@Autowired
	private CibilService cibilService;
	@Autowired
	private ModelMapper modelMapper;

	public EnquiryResponseDTO saveEnquiry(EnquiryRequestDTO enquiryRequestDTO) {

		Enquiry enquiry = modelMapper.map(enquiryRequestDTO, Enquiry.class);

		enquiry.setStatus(EnquiryStatus.Register);

		Enquiry saveEnquiry = enquiryService.saveEnquiry(enquiry);
		EnquiryResponseDTO saveEnquiryResponseDTO = modelMapper.map(saveEnquiry, EnquiryResponseDTO.class);
		return saveEnquiryResponseDTO;

	}

}
