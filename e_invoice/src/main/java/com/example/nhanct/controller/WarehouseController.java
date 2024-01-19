package com.example.nhanct.controller;

import com.example.nhanct.consts.MenuConstant;
import com.example.nhanct.entity.MenuEntity;
import com.example.nhanct.entity.WarehouseEntity;
import com.example.nhanct.service.WarehouseService;
import com.example.nhanct.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin/warehouse")
public class WarehouseController extends FunctionCommon {

	@Autowired
	private WarehouseService warehouseService;
	@Value("${adress.404}")
	private String adress404;

	@Autowired
	SecurityUtils myMenu;
	@GetMapping("")
	public String index(ModelMap model) {
		if(hasRoleAuthor(MenuConstant.WAREHOUSE) == false) {
			return "deny/deny";
		}
		menuListRole(model);
		return listPage(model,1);
	}

	@GetMapping("/page/{pageNumber}")
	public String listPage(ModelMap model, @PathVariable("pageNumber") int currentPage) {
		if(hasRoleAuthor(MenuConstant.WAREHOUSE) == false) {
			return "deny/deny";
		}
		menuListRole(model);
		Page<WarehouseEntity> page = warehouseService.findAll(currentPage);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();

		List<WarehouseEntity> listWarehouse = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);

		model.addAttribute("listWarehouse", listWarehouse);
		return "warehouse/index";
	}


	@GetMapping("add")
	public String add(ModelMap model, @RequestParam(value = "message", required = false) String message) {

		if(hasRoleAuthor(MenuConstant.MENU) == false) {
			return "deny/deny";
		}
		menuListRole(model);

		model.addAttribute("message", message);
		model.addAttribute("warehouse", new WarehouseEntity());
		return "warehouse/add";
	}

	@PostMapping("add")
	public String add(@Valid @ModelAttribute("warehouse") WarehouseEntity warehouse, BindingResult errors, ModelMap model) {

		String message = "";

		if (errors.hasErrors()) {
			menuListRole(model);
			model.addAttribute("warehouses", warehouseService.findAll());
			return "warehouse/add";
		}

		warehouseService.add(warehouse);
		return "redirect:/admin/warehouse";
	}

	@GetMapping("edit")
	public String edit(@RequestParam("id") int id, ModelMap model) {
		if(hasRoleAuthor(MenuConstant.MENU) == false) {
			return "deny/deny";
		}
		menuListRole(model);
		try {
			model.addAttribute("warehouses", warehouseService.findAll());
			model.addAttribute("warehouse", warehouseService.getById(id));
			return "warehouse/edit";
		} catch (Exception e) {
			return "redirect:" + adress404;
		}
	}

	@PostMapping("edit")
	public String edit(@Valid @ModelAttribute("warehouse") WarehouseEntity warehouse, BindingResult errors, ModelMap model) {
		if (errors.hasErrors() ) {
			menuListRole(model);
			model.addAttribute("warehouses", warehouseService.findAll());
			return "warehouse/edit";
		}

		warehouseService.update(warehouse);
		return "redirect:/admin/warehouse";
	}

	@GetMapping("confirm-delete")
	public String delete(@RequestParam("id") int id, ModelMap model,
						 @RequestParam(value = "message", required = false) String message) {
		if(hasRoleAuthor(MenuConstant.MENU) == false) {
			return "deny/deny";
		}
		menuListRole(model);

		try {
			WarehouseEntity warehouse=warehouseService.getById(id);
			model.addAttribute("warehouse", warehouse);
			model.addAttribute("message", message);
			return "warehouse/confirm-delete";
		} catch (Exception e) {
			return "redirect:"+adress404;
		}
	}

	@GetMapping("confirm-delete-post")
	public String delete1(@RequestParam("id") int id, ModelMap model) {
		if(hasRoleAuthor(MenuConstant.MENU) == false) {
			return "deny/deny";
		}
		menuListRole(model);
		String message = "";
		try {
			if(id != myMenu.getPrincipal().getId() && warehouseService.delete(id) ) {
				return "redirect:/admin/warehouse";
			}else {
				message = "F";
				return "redirect:/admin/warehouse/confirm-delete?id=" + id + "&&message="+message;
			}

		} catch (DataIntegrityViolationException e) {
			message = "F";
			System.out.println("/admin/warehouse/confirm-delete?id=" + id + " &&message= "+message);
			return "redirect:/admin/warehouse/confirm-delete?id=" + id + "&&message="+message;
		} catch(Exception e) {
			message = "F";
			System.out.println("/admin/warehouse/confirm-delete?id=" + id + " &&message= "+message);
			return "redirect:/admin/warehouse/confirm-delete?id=" + id + "&&message="+message;
		}
	}


}