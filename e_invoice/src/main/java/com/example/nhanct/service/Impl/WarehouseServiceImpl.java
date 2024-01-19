package com.example.nhanct.service.Impl;

import com.example.nhanct.entity.MenuEntity;
import com.example.nhanct.entity.WarehouseEntity;
import com.example.nhanct.repository.WarehouseRepository;
import com.example.nhanct.service.WarehouseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

	@Autowired
	private WarehouseRepository warehouseRepository;
    
	@Override
	public Page<WarehouseEntity> findAll(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 10);
		return warehouseRepository.findAll(pageable);
	}

	@Override
	public List<WarehouseEntity> findAll() {
		return warehouseRepository.findAll();
	}

	@Override
	public void add(WarehouseEntity warehouse) {
		warehouse.setWarehouseCode(warehouse.getWarehouseCode().trim());
		warehouseRepository.save(warehouse);
	}

	@Override
	public void update(WarehouseEntity warehouse) {
		ModelMapper modelMapper = new ModelMapper();
		WarehouseEntity warehouseEntity=modelMapper.map(warehouse, WarehouseEntity.class);
		warehouseRepository.save(warehouseEntity);
	}

	@Override
	public boolean delete(int id) {
		warehouseRepository.deleteById(id);
		return true;
	}

	@Override
	public WarehouseEntity getById(int id) {
		return warehouseRepository.findById(id).get();
	}

}