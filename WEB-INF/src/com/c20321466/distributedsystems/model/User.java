package com.c20321466.distributedsystems.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	
	private String userName;
	private String userPhone;
	private String userAddress;
	private double userSalary;
	
	@OneToOne(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Loan loan;
	
	public User(String userName, String userPhone, String userAddress, double userSalary, Loan loan) {

		this.userName = userName;
		this.userPhone = userPhone;
		this.userAddress = userAddress;
		this.userSalary = userSalary;
		this.loan = loan;
		
	}
	
	public User() {
		
	}

	@XmlElement
	public int getUserId() {
		return userId;
	}
	
	@XmlElement
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@XmlElement
	public String getUserPhone() {
		return userPhone;
	}
	
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	@XmlElement
	public String getUserAddress() {
		return userAddress;
	}
	
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	@XmlElement
	public double getUserSalary() {
		return userSalary;
	}
	
	public void setUserSalary(double userSalary) {
		this.userSalary = userSalary;
	}
	
	public Loan getLoan() {
		return loan;
	}
	
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
}
