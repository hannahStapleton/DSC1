package com.c20321466.distributedsystems;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.c20321466.distributedsystems.model.User;


	public class UserDAO {
		
		protected static EntityManagerFactory emf = 
		Persistence.createEntityManagerFactory("moneylenders");

		public UserDAO() {
		}
		 	

		public void persist(User user) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			em.close();
		}
		
		public void removeUser(User user) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(user));
			em.getTransaction().commit();
			em.close();
		}
		
		public User merge(User user) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			User updatedUser = em.merge(user);
			em.getTransaction().commit();
			em.close();
			return updatedUser;
		}
		
		
		public List<User> getAllUsers() {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			List<User> users = new ArrayList<User>();
			users = em.createQuery("from users").getResultList();
			em.getTransaction().commit();
			em.close();
			return users;
		}

		public User getUserById(int userId) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			User e = em.createQuery("SELECT p FROM users p WHERE p.userId = :userId", User.class)
	                .setParameter("userId", userId)
	                .getSingleResult();
			em.getTransaction().commit();
			em.close();
			return e;
		}

	}
