package com.example.nhanct.service;

import com.example.nhanct.entity.MenuEntity;
import com.example.nhanct.entity.WarehouseEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WarehouseService {
	Page<WarehouseEntity> findAll(int pageNumber);
	List<WarehouseEntity> findAll();
	void add(WarehouseEntity warehouse);
	void update(WarehouseEntity warehouse);
	boolean delete(int id);
	WarehouseEntity getById(int id);
}