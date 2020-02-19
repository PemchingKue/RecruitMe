/**
 * 
 */
package org.perscholas.casestudy.entities;

import java.util.*;

import javax.persistence.Query;


/**
 * @author PK
 *
 */
public class CandidateServices extends AbstractServices{

	public List<Candidate> fetchData(int sessionUserId) {
			
		Query query = em.createNamedQuery("getAllCandidates").setParameter("rId", sessionUserId);
		List<Candidate> arr = query.getResultList();
		disposeCon();
		
		return arr;
		
	}
	
	public List<Candidate> fetchDataById(int cId) {
		
		Query query = em.createNamedQuery("getCandidateById").setParameter("id", cId);
		List<Candidate> arr = query.getResultList();
		
		
		return arr;
		
	}
	
	public List<Candidate> updateData(List<Candidate> cList) {
		
		List<Candidate> arr = new ArrayList<Candidate>();
		arr.addAll(cList);
		int cId = 0;
		String firstName = null;
		String lastName = null;
		String email = null;
		String phone = null;
		em.getTransaction().begin();
        
		for(Candidate c : arr) {
			cId = c.getCandidateId();
			firstName = c.getFirstName();
			lastName = c.getLastName();
			email = c.getEmail();
			phone = c.getPhone();
		}
		  Query query = em.createQuery(
			      "UPDATE Candidate c SET c.firstName = :firstName, c.lastName = :lastName, c.email = :email, c.phone = :phone WHERE c.candidateId = :id");
		
		int updateCount = query.setParameter("id", cId)
				.setParameter("firstName", firstName)
				.setParameter("lastName", lastName)
				.setParameter("email", email)
				.setParameter("phone", phone).executeUpdate();
		em.getTransaction().commit();
		disposeCon();
		
		if(updateCount > 0) {
			return arr;
		}else {
			System.out.println("update failed");
			return arr;
		}
		
	}
	
	public List<Candidate> deleteData(int cId, int sessionUserId) {
		
		List<Candidate> arr = new ArrayList<Candidate>();
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("DELETE FROM Candidate c WHERE c.candidateId = :id AND c.recruiter.recruiterId = :userId");
		int deleteCount = query.setParameter("id", cId).setParameter("userId", sessionUserId).executeUpdate();
				
		em.getTransaction().commit();
		disposeCon();
		
		return arr;
		
	}
	
	public List<Candidate> createData(String firstName, String lastName, String email, String phone, int sessionUserId) {
		
		//get recruiter
		Query getRecruiter = em.createQuery("SELECT r FROM Recruiter r WHERE r.recruiterId = :rId").setParameter("rId", sessionUserId);
		List<Recruiter> rArr = getRecruiter.getResultList();
		
		//create new candidate object
		Candidate c = new Candidate(firstName, lastName, email, phone, rArr.get(0));
		List<Candidate> arr = new ArrayList<Candidate>();
		
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		
		//fetch new candidate into arraylist, add check for email must be unique!
		Query getNewCandidate = em.createQuery("SELECT c FROM Candidate c WHERE c.candidateId = :cId").setParameter("cId", c.getCandidateId());
		arr = getNewCandidate.getResultList();
		
		disposeCon();
		
		return arr;
		
	}
	
}
