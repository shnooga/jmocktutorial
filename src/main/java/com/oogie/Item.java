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
public class Item {
	private final int id;
	private final String description;
	private BigDecimal price;
	private Category category = Category.GROCERY;

	public Item(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public Item(int id, String description, Category category, BigDecimal price) {
		this.id = id;
		this.description = description;
		this.category = category;
		this.price = price;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}
