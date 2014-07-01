package com.oogie;

import java.math.BigDecimal;
import static org.hamcrest.CoreMatchers.*;
import org.jmock.Expectations;
import static org.jmock.Expectations.returnValue;
import org.jmock.Mockery;
import org.jmock.auto.Mock;
import org.junit.*;
import static org.junit.Assert.*;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

public class CashRegisterLegacyTest {
	@Mock Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
	// This is a Mock of an actual Class not an Interface.
	@Mock SacCountyTaxCalculator taxCalculator = context.mock(SacCountyTaxCalculator.class);


	@Test
	public void postTaxTotal() {
		Register register = new CashRegister(taxCalculator);
		final Item item = new Item(1, "rice", Category.GROCERY, new BigDecimal("5.22"));

		context.checking(new Expectations() {
			{
				oneOf(taxCalculator).getTax(item);
				will(returnValue(new BigDecimal("5.55"))); // Stub to always return $5.55
			}
		});
		register.scan(item);
		BigDecimal total = CurrencyHelper.roundUp(register.postTaxTotal());

		context.assertIsSatisfied();
		assertThat(total, equalTo(new BigDecimal("10.77")));
	}
}
