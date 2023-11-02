package com.c20321466.distributedsystems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "loan")
@Entity
public class Loan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int loanId;

	private double loanAmount;
	private String loanDescription;
	
	@OneToMany
	private Deposit deposit;
	
	public Loan(double loanAmount, String loanDescription, Deposit deposit) {
		this.setLoanAmount(loanAmount);
		this.setLoanDescription(loanDescription);
		this.deposit = deposit;
	}
	
	public Loan() {
		
	}

	@XmlElement
	public String getLoanDescription() {
		return loanDescription;
	}

	public void setLoanDescription(String loanDescription) {
		this.loanDescription = loanDescription;
	}

	@XmlElement
	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	
	@XmlElement
	public Deposit getDeposit() {
		return deposit;
	}
	
	public void setDeposit(Deposit deposit){
		this.deposit = deposit;
	}
}
	