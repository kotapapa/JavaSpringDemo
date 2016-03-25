package com.inventory.domain;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = CollectionNames.Items)
public class Items {

	@Id
	private String id;

	@Indexed(name = "pk_items", direction = IndexDirection.ASCENDING, unique = true)
	@Field("itemCode")
	private String itemCode;

	private String itemName;

	private String itemScale;

	private String itemMaker;

	private String itemDescription;

	private BigDecimal itemPrice;

	public String getId() {
	  return id;
	}

	public void setId(String id) {
	  this.id = id;
	}

	public String getItemCode() {
	  return itemCode;
	}

	public void setItemCode(String itemCode) {
	  this.itemCode = itemCode;
	}

	public String getItemName() {
	  return itemName;
	}

	public void setItemName(String itemName) {
	  this.itemName = itemName;
	}

	public String getItemScale() {
	  return itemScale;
	}

	public void setItemScale(String itemScale) {
	  this.itemScale = itemScale;
	}

	public String getItemDescription() {
	  return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
	  this.itemDescription = itemDescription;
	}

	public String getItemMaker() {
		return itemMaker;
	}

	public void setItemMaker(String itemMaker) {
		this.itemMaker = itemMaker;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

}
