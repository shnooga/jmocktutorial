package com.oogie;

import java.math.BigDecimal;
import static org.hamcrest.CoreMatchers.*;
import org.jmock.Expectations;
import static org.jmock.Expectations.returnValue;
import org.jmock.Mockery;
import org.jmock.auto.Auto;
import org.jmock.auto.Mock;
import org.junit.*;
import static org.junit.Assert.*;

public class CashRegisterTest {
	private final Item[] items = new Item[] {
		new Item(1, "rice", Category.GROCERY, new BigDecimal("5.22")),
		new Item(2, "aspirin", Category.MEDICAL, new BigDecimal("8.75")),
		new Item(3, "fried chicken", Category.PROCESSED_FOOD, new BigDecimal("10.00")),
		new Item(4, "marlboro", Category.TOBACCO, new BigDecimal("5.00")),
	};

	//private Mockery context = new Mockery();
	@Mock Mockery context = new Mockery();

	//private TaxCalculator taxCalculator = context.mock(TaxCalculator.class);
	//@Auto TaxCalculator taxCalculator = context.mock(TaxCalculator.class);
	@Mock TaxCalculator taxCalculator = context.mock(TaxCalculator.class);

	@Test
	public void scanSingleItem() {
		Register register = new CashRegister(null);

		register.scan(items[0]);
		assertThat(register.getItemCount(), equalTo(1));
	}

	@Test
	public void scanMultiItems() {
		Register register = new CashRegister(null);

		register.scan(items);
		assertThat(register.getItemCount(), equalTo(items.length));
	}

	@Test
	public void clearItems() {
		Register register = new CashRegister(null);

		register.scan(items);
		register.clearItems();
		assertThat(register.getItemCount(), equalTo(0));
	}

	//@Mock TaxCalculator taxCalculator;

	@Test
	public void preTaxTotal() {
		// set up
		//final TaxCalculator taxCalculator = context.mock(TaxCalculator.class);
		Register register = new CashRegister(taxCalculator);
		final Item item = items[0];

		// expectations
		context.checking(new Expectations() {
			{
				never(taxCalculator).getTax(item);
			}
		});

		register.scan(item);
		// execute
		BigDecimal total = CurrencyHelper.roundUp(register.preTaxTotal());

		// verify
		context.assertIsSatisfied();
		assertThat(total, equalTo(item.getPrice()));
	}

	@Test
	public void postTaxTotal() {
		// set up
		//final TaxCalculator taxCalculator = context.mock(TaxCalculator.class);
		Register register = new CashRegister(taxCalculator);
		final Item item = items[0];

		// expectations
		context.checking(new Expectations() {
			{
				oneOf(taxCalculator).getTax(item);
				will(returnValue(new BigDecimal("5.55"))); // Stub to always return $5.55
			}
		});

		register.scan(item);
		// execute
		BigDecimal total = CurrencyHelper.roundUp(register.postTaxTotal());

		// verify
		context.assertIsSatisfied();
		assertThat(total, equalTo(new BigDecimal("10.77")));
	}
}
