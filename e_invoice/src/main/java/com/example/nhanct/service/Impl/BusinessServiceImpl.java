package com.example.nhanct.service.Impl;

import com.example.nhanct.entity.BusinessEntity;
import com.example.nhanct.repository.BusinessRepository;
import com.example.nhanct.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private BusinessRepository businessRepository;
    
	@Override
	public Page<BusinessEntity> findAll(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 10);
		return businessRepository.findAll(pageable);
	}

	@Override
	public List<BusinessEntity> findAll() {
		return businessRepository.findAll();
	}

	@Override
	public BusinessEntity getByID(int id) {
		return businessRepository.findById(id).get();
	}

	@Override
	public void update(BusinessEntity business) {
		BusinessEntity entity = businessRepository.findById(business.getId()).get();
		if(entity != null) {
			entity.setBusinessName(business.getBusinessName());
			entity.setMst(business.getMst());
			entity.setPhone(business.getPhone());
			entity.setAddress(business.getPhone());
			if(business.getLogo()!=null){
				entity.setLogo(business.getLogo());
			}
			if(business.getSampleSignature()!=null){
				entity.setSampleSignature(business.getSampleSignature());
			}
			businessRepository.save(business);
		}
	}
}