package com.inventory.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = CollectionNames.Inventorylogs)
public class Inventorylogs {

	@Id
	private String id;

	@Indexed(name = "pk_inventory_logs", direction = IndexDirection.DESCENDING, unique = true)
	@Field("invlogNumber")
	private Long invlogNumber;

	private Date invlogDate;

	private String invlogStatus;

	private String itemCode;

	private Integer quantityInput;

	private Integer quantityOutput;

	private Integer quantityStock;

	private String userId;

	private String status;

	private String comments;

	@Indexed(name = "idx1_inventory_logs", direction = IndexDirection.ASCENDING)
	private Long customerNumber;

	public String getId() {
	  return id;
	}

	public void setId(String id) {
	  this.id = id;
	}

	public Long getInvlogNumber() {
	  return invlogNumber;
	}

	public void setInvlogNumber(Long invlogNumber) {
	  this.invlogNumber = invlogNumber;
	}

	public Date getInvlogDate() {
	  return invlogDate;
	}

	public void setInvlogDate(Date invlogDate) {
	  this.invlogDate = invlogDate;
	}

	public String getInvlogStatus() {
		return invlogStatus;
	}

	public void setInvlogStatus(String invlogStatus) {
		this.invlogStatus = invlogStatus;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Integer getQuantityInput() {
		return quantityInput;
	}

	public void setQuantityInput(Integer quantityInput) {
		this.quantityInput = quantityInput;
	}

	public Integer getQuantityOutput() {
		return quantityOutput;
	}

	public void setQuantityOutput(Integer quantityOutput) {
		this.quantityOutput = quantityOutput;
	}

	public Integer getQuantityStock() {
		return quantityStock;
	}

	public void setQuantityStock(Integer quantityStock) {
		this.quantityStock = quantityStock;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
