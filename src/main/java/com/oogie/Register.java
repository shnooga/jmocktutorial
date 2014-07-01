/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oogie;

import java.math.BigDecimal;

/**
 *
 * @author hueyng
 */
public interface Register {
	void scan(Item item);
	void scan(Item[] items);
	BigDecimal preTaxTotal();
	BigDecimal postTaxTotal();
	void clearItems();
	int getItemCount();
}
