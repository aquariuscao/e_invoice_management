package com.example.nhanct.controller;

import com.example.nhanct.consts.MenuConstant;
import com.example.nhanct.entity.CustomerEntity;
import com.example.nhanct.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/customer")
public class CustomerController extends FunctionCommon {

	@Autowired
	private CustomerService invoiceTypeService;

	@GetMapping("")
	public String index(ModelMap model) {
		//kiem tra vai tro cua user co quyen truy cap chuc nang nay hay khong
		if(hasRoleAuthor(MenuConstant.CUSTOMER) == false) {
			return "deny/deny";
		}
		//tao danh sach menu dua tren vai tro cua user
		menuListRole(model);
		return listPage(model,1);
	}

	@GetMapping("/page/{pageNumber}")
	public String listPage(ModelMap model, @PathVariable("pageNumber") int currentPage) {
		if(hasRoleAuthor(MenuConstant.CUSTOMER) == false) {
			return "deny/deny";
		}
		menuListRole(model);
		Page<CustomerEntity> page = invoiceTypeService.findAll(currentPage);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();

		List<CustomerEntity> listCustomer = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);

		model.addAttribute("listCustomer", listCustomer);
		return "customer/index";
	}

}