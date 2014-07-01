package com.oogie;

import java.math.BigDecimal;
import java.util.HashMap;

public class SacCountyTaxCalculator implements TaxCalculator {
	private HashMap<Category, BigDecimal> taxMap = new HashMap<Category, BigDecimal>();

	public SacCountyTaxCalculator(HashMap<Category, BigDecimal> taxMap) {
		this.taxMap = taxMap;
	}

	/*
	public SacCountyTaxCalculator() {
		taxMap.put(Category.GROCERY, new BigDecimal("0.00"));
		taxMap.put(Category.MEDICAL, new BigDecimal("0.00"));
		taxMap.put(Category.PROCESSED_FOOD, new BigDecimal("0.06"));
		taxMap.put(Category.TOBACCO, new BigDecimal("0.25"));
	}
	*/

	public BigDecimal getTax(Item item) {
		BigDecimal tax = taxMap.get(item.getCategory());
		if (tax == null) tax = new BigDecimal("0.00");
		return tax.multiply(item.getPrice());
	}
	
}
