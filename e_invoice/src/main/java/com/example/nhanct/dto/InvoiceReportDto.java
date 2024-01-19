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
public class InvoiceReportDto {

	/* _______ Header _________ */
	private String companyName;
	private String mstCompany;
	private String addressCompany;
	private String phoneCompany;

	private String customerName;
	private String mstCustomer;
	private String addressCustomer;
	private String phoneCustomer;
	private String email;

	private String numberOxTax;
	private String symbol;

	/* _______ body _________ */
	List<InvoiceReportChildDto> listData;

	/* _______ body _________ */
	private String sumPriceAfterOfTax;
}
