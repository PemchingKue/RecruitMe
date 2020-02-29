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
	
	public List<Candidate> updateData(int cId, String firstName, String lastName, String company, String position, String email, String phone, int sessionUserId, Integer resumeId) {
		
		//get candidate
		Query getCandidate = em.createNamedQuery("getCandidateById").setParameter("id", cId);
		List<Candidate> arr = getCandidate.getResultList();
		
		//get resume
		Query getResume = em.createQuery("SELECT r FROM Resume r WHERE r.resumeId = :rId").setParameter("rId", resumeId);
		List<Resume> resumeArr = getResume.getResultList();
		
		//delete resume because if null and exist
		if(resumeId == null) {
			deleteResume(cId, sessionUserId);
		}
		
		//check client if exists
		Query getClient = em.createQuery("SELECT c FROM Client c WHERE c.clientName = :company AND c.position = :position AND c.recruiter.recruiterId = :rId")
				.setParameter("rId", sessionUserId)
				.setParameter("company", company)
				.setParameter("position", position);
		List<Client> cArr = getClient.getResultList();
		
		if(cArr.isEmpty()) {
			System.out.println("Company and position does not exist!");
		}
		
		int updateCount = 0;
		String nFirstName = firstName;
		String nLastName = lastName;
		String nEmail = email;
		String nPhone = phone;
		em.getTransaction().begin();
		
		
        if(resumeArr.isEmpty()) {
    		for(Candidate c : arr) {
    			c.setFirstName(firstName);
    			c.setLastName(lastName);
    			c.setEmail(email);
    			c.setPhone(phone);
    			c.setClient(cArr.get(0));
    		}
    		
    		Query query = em.createQuery(
  			      "UPDATE Candidate c SET c.firstName = :firstName, c.lastName = :lastName, c.email = :email, c.phone = :phone, c.client = :client WHERE c.candidateId = :id");
  		
	  		updateCount = query.setParameter("id", cId)
	  				.setParameter("firstName", nFirstName)
	  				.setParameter("lastName", nLastName)
	  				.setParameter("email", nEmail)
	  				.setParameter("phone", nPhone)
	  				.setParameter("client", cArr.get(0)).executeUpdate();
        }else {
    		for(Candidate c : arr) {
    			c.setFirstName(firstName);
    			c.setLastName(lastName);
    			c.setEmail(email);
    			c.setPhone(phone);
    			c.setClient(cArr.get(0));
    			c.setResume(resumeArr.get(0));
    		}
    		
    		Query query = em.createQuery(
    			      "UPDATE Candidate c SET c.firstName = :firstName, c.lastName = :lastName, c.email = :email, c.phone = :phone, c.client = :client, c.resume = :resume WHERE c.candidateId = :id");
    		
  	  		updateCount = query.setParameter("id", cId)
  	  				.setParameter("firstName", nFirstName)
  	  				.setParameter("lastName", nLastName)
  	  				.setParameter("email", nEmail)
  	  				.setParameter("phone", nPhone)
  	  				.setParameter("client", cArr.get(0))
  	  				.setParameter("resume", resumeArr.get(0)).executeUpdate();
        }


		em.getTransaction().commit();
		
		disposeCon();
		
		if(updateCount > 0) {
			return arr;
		}else {
			//return null, then catch, if update data != null, then do something, if null then throw popup modal, replace sys.out with a logging statement
			System.out.println("update failed");
			return arr;
		}
		
	}

	public List<Candidate> createData(String firstName, String lastName, String email, String phone, int sessionUserId, String company, String position, Integer resumeId) {
		
		//get recruiter
		Query getRecruiter = em.createQuery("SELECT r FROM Recruiter r WHERE r.recruiterId = :rId").setParameter("rId", sessionUserId);
		List<Recruiter> rArr = getRecruiter.getResultList();
		
		//get resume
		Query getResume = em.createQuery("SELECT r FROM Resume r WHERE r.resumeId = :rId").setParameter("rId", resumeId);
		List<Resume> resumeArr = getResume.getResultList();
		
		//get client
		Query getClient = em.createQuery("SELECT c FROM Client c WHERE c.clientName = :company AND c.position = :position AND c.recruiter.recruiterId = :rId")
					.setParameter("rId", sessionUserId)
					.setParameter("company", company)
					.setParameter("position", position);
		List<Client> cArr = getClient.getResultList();
		
		if(cArr.isEmpty()) {
			System.out.println("Company and position does not exist!");
		}
		
		Candidate c = new Candidate();
		
		if(resumeArr.isEmpty()) {
			//create new candidate object without resume
			c = new Candidate(firstName, lastName, email, phone, rArr.get(0), cArr.get(0));
		}else {
			//create new candidate object with resume
			c = new Candidate(firstName, lastName, email, phone, rArr.get(0), cArr.get(0), resumeArr.get(0));	
		}
		
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		
		//fetch new candidate into arraylist, add check for email must be unique!
		Query getNewCandidate = em.createQuery("SELECT c FROM Candidate c WHERE c.candidateId = :cId").setParameter("cId", c.getCandidateId());
		List<Candidate> arr = getNewCandidate.getResultList();
		
		disposeCon();
		
		return arr;
		
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
	
	public void deleteResume(int cId, int sessionUserId) {
		
		em.getTransaction().begin();
		
		//delete resume in the resume table
		Query getCandidateById = em.createNamedQuery("getCandidateById").setParameter("id", cId);
		List<Candidate> cList = getCandidateById.getResultList();
		
		Query removeResume = em.createNamedQuery("deleteResumeById")
				.setParameter("rId", cList.get(0).getResume().getResumeId());
		
		removeResume.executeUpdate();
		
		//set resume field to NULL in the candidate table
//		Query query = em.createQuery("UPDATE Candidate c SET c.resume = NULL WHERE c.candidateId = :id AND c.recruiter.recruiterId = :userId");
//		query.setParameter("id", cId).setParameter("userId", sessionUserId).executeUpdate();
		
		em.getTransaction().commit();
		
	}
	

	
}
