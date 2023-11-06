package com.c20321466.distributedsystems;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.c20321466.distributedsystems.model.Deposit;

public class DepositDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("moneylenders"); 	
	
	public DepositDAO() {
		
	}
	
	public void persist(Deposit deposit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(deposit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Deposit deposit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(deposit));
		em.getTransaction().commit();
		em.close();
	}
	
	public Deposit merge(Deposit deposit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Deposit updatedDeposit = em.merge(deposit);
		em.getTransaction().commit();
		em.close();
		return updatedDeposit;
	}
	
	
	public List<Deposit> getAllDeposits() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Deposit> depositsFromDB = new ArrayList<Deposit>();
		depositsFromDB = em.createQuery("from deposits").getResultList();
		em.getTransaction().commit();
		em.close();
		return depositsFromDB;
	}
	
	public List<Deposit> getDepositById(int loanId){
		EntityManager em = emf.createEntityManager();
		List<Deposit> deposits = em.createNamedQuery("Deposit.findByLoanId")
		.setParameter("loanId", loanId)
		.getResultList();

		em.close();
		return deposits;
	}

}

