package com.c20321466.distributedsystems;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.c20321466.distributedsystems.model.Loan;

public class LoanDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("moneylenders"); 	
	
	public LoanDAO() {
		
	}
	
	public void persist(Loan loan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(loan);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Loan loan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(loan));
		em.getTransaction().commit();
		em.close();
	}
	
	public Loan merge(Loan loan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Loan updatedLoan = em.merge(loan);
		em.getTransaction().commit();
		em.close();
		return updatedLoan;
	}
	
	
	public List<Loan> getAllLoans() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Loan> loansFromDB = new ArrayList<Loan>();
		loansFromDB = em.createQuery("from loans").getResultList();
		em.getTransaction().commit();
		em.close();
		return loansFromDB;
	}
	
	public Loan getLoanById(int loanId){
		EntityManager em = emf.createEntityManager();
		Loan loan = em.createQuery("SELECT p FROM loans p WHERE p.loanId = :loanId", Loan.class).setParameter("loanId", loanId).getSingleResult();
		em.getTransaction().commit();
		em.close();
		return loan;
	}

}
