package com.example.nhanct.controller;

import com.example.nhanct.consts.MenuConstant;
import com.example.nhanct.entity.BusinessEntity;
import com.example.nhanct.entity.MenuEntity;
import com.example.nhanct.entity.UserEntity;
import com.example.nhanct.service.BusinessService;
import com.example.nhanct.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("admin/business")
public class BusinessController extends FunctionCommon {

	@Autowired
	private BusinessService businessService;

	@Autowired
	private ImageService imageService;

	@GetMapping("")
	public String index(ModelMap model) {
		if(hasRoleAuthor(MenuConstant.BUSINESS) == false) {
			return "deny/deny";
		}
		menuListRole(model);
		return listPage(model,1);
	}

	@GetMapping("/page/{pageNumber}")
	public String listPage(ModelMap model, @PathVariable("pageNumber") int currentPage) {
		if(hasRoleAuthor(MenuConstant.BUSINESS) == false) {
			return "deny/deny";
		}
		menuListRole(model);
		Page<BusinessEntity> page = businessService.findAll(currentPage);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();

		List<BusinessEntity> listBusiness = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);

		model.addAttribute("listBusiness", listBusiness);
		return "business/index";
	}
	@GetMapping("edit/{id}")
	public String edit(@PathVariable(value = "id") int id, ModelMap model) {
//		if(hasRoleAuthor(MenuConstant.MENU) == false) {
//			return "deny/deny";
//		}
		menuListRole(model);
			model.addAttribute("businesss", businessService.findAll());
			model.addAttribute("business",businessService.getByID(id));
			return "business/edit";
	}

	@PostMapping("edit")
	public String edit(@Valid @ModelAttribute("business") BusinessEntity business, BindingResult errors,
					   @RequestParam("fileUpload") MultipartFile file, ModelMap model) {
		if (!file.isEmpty()) {
			try {
				imageService.delete(business.getLogo());
				business.setSampleSignature(imageService.upload(file));
			} catch (Exception e) {
				errors.rejectValue("image", "sign", "Can't upload files");
			}
		}
		if (errors.hasErrors()) {
			menuListRole(model);
			model.addAttribute("business", businessService.findAll());
			return "business/edit";
		}

		businessService.update(business);
		return "redirect:/admin/business";
	}

}