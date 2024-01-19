package com.example.nhanct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceReportChildDto {

	private String itemName;
	private String dvt;
	private String quantity;
	private String price;
	private String priceBeforeTax;
	private String ratio;
	private String priceOfTax;
	private String priceAfterTax;

}
