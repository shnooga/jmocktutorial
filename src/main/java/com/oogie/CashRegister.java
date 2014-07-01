package com.oogie;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author hueyng
 */
public class CashRegister implements Register {
	private final ArrayList<Item> items = new ArrayList<Item>();
	private final TaxCalculator taxCalculator;

	public CashRegister(TaxCalculator taxCalculator) {
		this.taxCalculator = taxCalculator;
	}

	public void scan(Item item) {
		items.add(item);
	}

	public void scan(Item[] items) {
		this.items.addAll(Arrays.asList(items));
	}

	public BigDecimal preTaxTotal() {
		BigDecimal sum = new BigDecimal("0.00");
		for(Item item : items) {
			sum = sum.add(item.getPrice());
		}
		return sum;
	}

	public BigDecimal postTaxTotal() {
		BigDecimal sum = new BigDecimal("0.00");
		for(Item item : items) {
			sum = sum.add(item.getPrice()).add(taxCalculator.getTax(item));
		}
		return sum;
	}

	public void clearItems() {
		items.clear();
	}

	public int getItemCount() {
		return items.size();
	}
	
}
