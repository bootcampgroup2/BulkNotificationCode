package com.casestudy.basedomain.dto;

import java.util.Arrays;

public class OrderEvent {
    private String message;
    private String status;
    private Order order;
    private String email;
    private Boolean isInstantEmail;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String emailIds) {
		this.email = emailIds;
	}
	public Boolean getIsInstantEmail() {
		return isInstantEmail;
	}
	public void setIsInstantEmail(Boolean isInstantEmail) {
		this.isInstantEmail = isInstantEmail;
	}
	@Override
	public String toString() {
		return "OrderEvent [message=" + message + ", status=" + status + ", order=" + order + ", email="
				+ (email) + ", isInstantEmail=" + isInstantEmail + "]";
	}
	public OrderEvent(String message, String status, Order order, String email, Boolean isInstantEmail) {
		super();
		this.message = message;
		this.status = status;
		this.order = order;
		this.email = email;
		this.isInstantEmail = isInstantEmail;
	}
	public OrderEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    /*
    public OrderEvent() {}
    
	@Override
	public String toString() {
		return "OrderEvent [message=" + message + ", status=" + status + ", order=" + order + ", email=" + email
				+ ", isInstantEmail=" + isInstantEmail + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsInstantEmail() {
		return isInstantEmail;
	}
	public void setIsInstantEmail(Boolean isInstantEmail) {
		this.isInstantEmail = isInstantEmail;
	}
	public OrderEvent(String message, String status, Order order, String email, Boolean isInstantEmail) {
		super();
		this.message = message;
		this.status = status;
		this.order = order;
		this.email = email;
		this.isInstantEmail = isInstantEmail;
	}
*/
}
