/**
 * 
 */
package org.perscholas.casestudy.entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * @author PK
 *
 */
public class RecruiterServices extends AbstractServices{

	public List<Recruiter> fetchRecruiterByEmail(String rEmail) {
		
		Query getRecruiter = em.createQuery("SELECT r FROM Recruiter r WHERE r.email = :email").setParameter("email", rEmail);
		List<Recruiter> arr = getRecruiter.getResultList();
		    
		disposeCon();
		
		return arr;
		
	}
	
	public Boolean checkEmail(String rEmail) {
		
		Query checkRecruiter = em.createQuery("SELECT r FROM Recruiter r WHERE r.email = :email").setParameter("email", rEmail);
		List<Recruiter> arr = checkRecruiter.getResultList();
		
		if(arr.isEmpty()) {
			return false;
		}else {
			return true;
		}

	}
	
	public Boolean addRecruiter(String email, String password, String firstName, String lastName) {
		
		//need to add password encryption

		//create new recruiter object
		Recruiter r = new Recruiter(email, password, firstName, lastName);
		System.out.println(r);
		
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
			
		disposeCon();
		
		return true;

	}
}
