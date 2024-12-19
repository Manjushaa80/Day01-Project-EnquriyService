package com.app.serviceimpl;

import org.springframework.stereotype.Service;

import com.app.entity.Cibil;
import com.app.repository.CibilRepository;
import com.app.service.CibilService;

@Service
public class CibilServiceImpl implements CibilService {

	private CibilRepository cibilRepository;

	public CibilServiceImpl(CibilRepository cibilRepository) {
		super();
		this.cibilRepository = cibilRepository;
	}
	@Override
	public Cibil saveEnquiry(Cibil cibil) {

		return cibilRepository.save(cibil);
	}

}
