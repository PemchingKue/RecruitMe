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
	
	public Boolean updateData(int userId, String firstName, String lastName, String email) {
		
		Query getRecruiter = em.createNamedQuery("getById").setParameter("rid", userId);
		List<Recruiter> arr = getRecruiter.getResultList();
		
		for(Recruiter r : arr) {
			r.setFirstName(firstName);
			r.setLastName(lastName);
			r.setEmail(email);
		}
		
		String newFirstName = null;
		String newLastName = null;
		String newEmail = null;
		
		em.getTransaction().begin();
        
		for(Recruiter r : arr) {
			newFirstName = r.getFirstName();
			newLastName = r.getLastName();
			newEmail = r.getEmail();
		}
		
		Query query = em.createQuery(
			      "UPDATE Recruiter r SET r.firstName = :firstName, r.lastName = :lastName, r.email = :email WHERE r.recruiterId = :id");
		
		int updateCount = query.setParameter("id", userId)
				.setParameter("firstName", newFirstName)
				.setParameter("lastName", newLastName)
				.setParameter("email", newEmail).executeUpdate();
		
		em.getTransaction().commit();
		
		disposeCon();
		
		if(updateCount > 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public Boolean updatePassword(int userId, String oldPassword, String newPassword) {
		
		Query getRecruiter = em.createNamedQuery("getById").setParameter("rid", userId);
		List<Recruiter> arr = getRecruiter.getResultList();		
		
		if(arr.get(0).getPassword().equals(oldPassword)) {
			em.getTransaction().begin();
			Query query = em.createQuery(
				      "UPDATE Recruiter r SET r.password = :password WHERE r.recruiterId = :id");
			
			int updateCount = query.setParameter("id", userId)
					.setParameter("password", newPassword).executeUpdate();
			
			em.getTransaction().commit();
			
			if(updateCount > 0) {
				System.out.println("success on changing password");
				return true;
			}else {
				System.out.println("failed on changing password");
				return false;
			}
			
		}else {
			System.out.println("password does not match!");
			return false;
		}
	
	}
}
