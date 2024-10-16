package com.example.nhanct.service.Impl;

import com.example.nhanct.entity.InvoiceTypeEntity;
import com.example.nhanct.repository.InvoiceTypeRepository;
import com.example.nhanct.service.InvoiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceTypeServiceImpl implements InvoiceTypeService {

	@Autowired
	private InvoiceTypeRepository InvoiceTypeRepository;
    
	@Override
	public Page<InvoiceTypeEntity> findAll(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 10);
		return InvoiceTypeRepository.findAll(pageable);
	}

	@Override
	public List<InvoiceTypeEntity> findAll() {
		return InvoiceTypeRepository.findAll();
	}
}