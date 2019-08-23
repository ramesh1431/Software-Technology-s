package com.rs.fer.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="expense_table")


public class Expense {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
	private int id;
	@Column
	private String expensetype;
	@Column
	private String date;
	@Column
	private String price;
	@Column
	private String numberofitems;
	@Column
	private String total;
	@Column
	private String bywhom;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExpensetype() {
		return expensetype;
	}
	public void setExpensetype(String expensetype) {
		this.expensetype = expensetype;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNumberofitems() {
		return numberofitems;
	}
	public void setNumberofitems(String numberofitems) {
		this.numberofitems = numberofitems;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getBywhom() {
		return bywhom;
	}
	public void setBywhom(String bywhom) {
		this.bywhom = bywhom;
	}
	
}
	
	