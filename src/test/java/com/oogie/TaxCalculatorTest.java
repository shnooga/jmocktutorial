package com.oogie;

import java.math.BigDecimal;
import java.util.HashMap;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class TaxCalculatorTest {
	private	TaxCalculator calculator;

	private TaxCalculator getCalculator() {
		if (calculator != null) return calculator;
		HashMap<Category, BigDecimal> taxMap = new HashMap<Category, BigDecimal>();
		taxMap.put(Category.GROCERY, new BigDecimal("0.00"));
		taxMap.put(Category.MEDICAL, new BigDecimal("0.00"));
		taxMap.put(Category.PROCESSED_FOOD, new BigDecimal("0.06"));
		taxMap.put(Category.TOBACCO, new BigDecimal("0.25"));
		return new SacCountyTaxCalculator(taxMap);
	}

	private final Item[] items = new Item[] {
		new Item(1, "rice", Category.GROCERY, new BigDecimal("5.22")),
		new Item(2, "aspirin", Category.MEDICAL, new BigDecimal("8.75")),
		new Item(3, "fried chicken", Category.PROCESSED_FOOD, new BigDecimal("10.00")),
		new Item(4, "marlboro", Category.TOBACCO, new BigDecimal("5.00")),
	};
	
	@Test
	public void getTobaccoTax() {
		BigDecimal tax = CurrencyHelper.roundUp(getCalculator().getTax(items[3]));
		assertThat(tax, equalTo(new BigDecimal("1.25")));
	}

	@Test
	public void getProcessFoodTax() {
		BigDecimal tax = CurrencyHelper.roundUp(getCalculator().getTax(items[2]));
		assertThat(tax, equalTo(new BigDecimal("0.60")));
	}

	@Test
	public void getMedicinalTax() {
		BigDecimal tax = CurrencyHelper.roundUp(getCalculator().getTax(items[1]));
		assertThat(tax, equalTo(new BigDecimal("0.00")));
	}

	@Test
	public void getGroceryTax() {
		BigDecimal tax = CurrencyHelper.roundUp(getCalculator().getTax(items[0]));
		assertThat(tax, equalTo(new BigDecimal("0.00")));
	}
}
