package com.c20321466.distributedsystems.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "loans")
@Entity
@Table(name = "loans")
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;

	private double loanAmount;
	private String loanDescription;

	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "loans")
	List<Deposit> deposits = new ArrayList<Deposit>();

	public Loan(double loanAmount, String loanDescription, List<Deposit> deposits) {
		super();
		this.setLoanAmount(loanAmount);
		this.setLoanDescription(loanDescription);
		this.setDeposits(deposits);
	}

	public Loan() {

	}

	public int getLoanId() {
		return loanId;
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
	public List<Deposit> getDeposits() {
		return deposits;
	}

	public void addDeposit(Deposit deposit) {
		this.deposits.add(deposit);
	}

	public void setDeposits(List<Deposit> deposits) {
		this.deposits = deposits;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
