package com.c20321466.distributedsystems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deposit")
@Entity
@Table(name = "deposits")
public class Deposit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int depositId;

	private double depositAmount;
	private String depositDate;
	private int loanId;
	
	public Deposit(double depositAmount, String depositDate) {
		this.setDepositAmount(depositAmount);
		this.setDepositDate(depositDate);
	}

	public Deposit() {
		
	}
	
	@XmlElement
	public String getDepositDate() {
		return depositDate;
	}
	
	public void setDepositDate(String depositDate) {
		this.depositDate = depositDate;
	}
	
	@XmlElement
	public double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
		
	}

}
