package com.oogie;

import java.math.BigDecimal;

public class CurrencyHelper {
	public static BigDecimal roundUp(BigDecimal value) {
		return value.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
