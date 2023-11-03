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
		depositsFromDB = em.createNamedQuery("Deposit.findAll").getResultList();
		em.getTransaction().commit();
		em.close();
		return depositsFromDB;
	}
	
	public Deposit getDepositById(String depositId){
		EntityManager em = emf.createEntityManager();
		List<Deposit> deposits = (List<Deposit>) 
				em.createNamedQuery("Deposit.findById").
				setParameter("depositId", depositId).getResultList();
		em.close();
		//Do whatever you want with the Deposit(l) with that id
		//Here we just return the first one
		Deposit deposit = new Deposit();
		for(Deposit d: deposits) {
			deposit = d;
		}
		return deposit;
	}

}

