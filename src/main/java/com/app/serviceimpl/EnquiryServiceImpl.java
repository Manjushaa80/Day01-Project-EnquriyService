package com.app.serviceimpl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import java.util.List;
import com.app.entity.Enquiry;
import com.app.entity.EnquiryStatus;
import com.app.repository.EnquiryRepository;
import com.app.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	private EnquiryRepository enquiryRepository;

	public EnquiryServiceImpl(EnquiryRepository homeLoanRepository) {
		super();
		this.enquiryRepository = homeLoanRepository;
	}

	@Override

	public Boolean deleteEnquiry(Integer customerID) {
		if (enquiryRepository.existsById(customerID)) {
			enquiryRepository.deleteById(customerID);
			return true;
		}
		return false;
	}

	public Enquiry saveEnquiry(Enquiry enquiry) {

		return enquiryRepository.save(enquiry);
	}

	@Override
	public List<Enquiry> getAllEnquiry() {
		return enquiryRepository.findAll();
	}

	@Override
	public Enquiry addData(Enquiry enquiry) {

		// enquiry.setStatus(EnquiryStatus.Register);

		return enquiryRepository.save(enquiry);
	}

	@Override
	public Enquiry getEnquiry(Integer customerID) {
		Optional<Enquiry> enquiryid = enquiryRepository.findById(customerID);
		if (enquiryid.isPresent()) {
			return enquiryid.get();
		}
		return null;
	}

	@Override
	public Enquiry editEnquiry(int customerID, Enquiry enquiryDetails) {

		if (enquiryRepository.existsById(customerID)) {
			enquiryDetails.setCustomerID(customerID);

			return enquiryRepository.save(enquiryDetails);
		}
		return null;
	}

	@Override
	public Enquiry updateEnquiry(int customerID, Enquiry enquiryDetails) {

		if (enquiryRepository.existsById(customerID)) {

			Enquiry existingsavedData = enquiryRepository.findById(customerID).get();

			if (enquiryDetails.getFirstName() != null) {
				existingsavedData.setFirstName(enquiryDetails.getFirstName());
			}
			if (enquiryDetails.getLastName() != null) {
				existingsavedData.setLastName(enquiryDetails.getLastName());
			}
			if (enquiryDetails.getMobileNo() != null) {
				existingsavedData.setMobileNo(enquiryDetails.getMobileNo());
			}
			if (enquiryDetails.getPancardNo() != null) {
				existingsavedData.setPancardNo(enquiryDetails.getPancardNo());
			}
			if (enquiryDetails.getEmail() != null) {
				existingsavedData.setEmail(enquiryDetails.getEmail());
			}
			if (enquiryDetails.getStatus() != null) {
				existingsavedData.setStatus(enquiryDetails.getStatus());
			}

			return enquiryRepository.save(existingsavedData);
		}

		return null;
	}

//	@Override
//	public List<Enquiry> searchEnquiryByStatus(String status) {
//
//		return enquiryRepository.findAllByStatus(status);
//	}

	@Override
	public List<Enquiry> searchEnquiryByStatus(EnquiryStatus enquiryStatus) {

		return enquiryRepository.findAllByStatus(enquiryStatus);
	}
}
