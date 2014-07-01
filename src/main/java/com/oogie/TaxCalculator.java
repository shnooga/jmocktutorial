package com.oogie;

import java.math.BigDecimal;

public interface TaxCalculator {
	public BigDecimal getTax(Item item);
}
