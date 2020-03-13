/*
* Filename: CandidateServices.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.entities;

import java.util.*;
import java.util.logging.Level;

import javax.persistence.Query;

import org.perscholas.casestudy.customexceptions.DeleteFailedException;
import org.perscholas.casestudy.customexceptions.UpdateFailedException;
import org.perscholas.casestudy.logger.RecruitMeLogger;

/**
 * @author PK
 *
 */
public class CandidateServices extends AbstractServices {

	public List<Candidate> fetchData(int sessionUserId) {

		// fetch all candidates under the recruiter
		Query query = em.createNamedQuery("getAllCandidates").setParameter("rId", sessionUserId);
		List<Candidate> arr = query.getResultList();
		disposeCon();

		return arr;

	}

	public List<Candidate> fetchDataById(int cId) {

		// fetch candidate by ID
		Query query = em.createNamedQuery("getCandidateById").setParameter("id", cId);
		List<Candidate> arr = query.getResultList();

		return arr;

	}

	public List<Candidate> updateData(int cId, String firstName, String lastName, String company, String position,
			String email, String phone, int sessionUserId, Integer resumeId)
			throws UpdateFailedException, DeleteFailedException {

		// logger
		RecruitMeLogger rmLog = new RecruitMeLogger();

		// get candidate
		Query getCandidate = em.createNamedQuery("getCandidateById").setParameter("id", cId);
		List<Candidate> arr = getCandidate.getResultList();

		// get resume
		Query getResume = em.createQuery("SELECT r FROM Resume r WHERE r.resumeId = :rId").setParameter("rId",
				resumeId);
		List<Resume> resumeArr = getResume.getResultList();

		// delete resume because if null and exist
		if (resumeId == null && arr.get(0).getResume() != null) {
			deleteResume(cId);
		}

		// check client if exists
		Query getClient = em.createQuery(
				"SELECT c FROM Client c WHERE c.clientName = :company AND c.position = :position AND c.recruiter.recruiterId = :rId")
				.setParameter("rId", sessionUserId).setParameter("company", company).setParameter("position", position);
		List<Client> cArr = getClient.getResultList();

		// check client list, if empty log event
		if (cArr.isEmpty()) {
			rmLog.logger.log(Level.WARNING, "Company and position does not exist!");
		}

		// define new attribute variables
		int updateCount = 0;
		String nFirstName = firstName;
		String nLastName = lastName;
		String nEmail = email;
		String nPhone = phone;

		em.getTransaction().begin();

		// check if candidate has a resume or not, then perform specific update
		if (resumeArr.isEmpty()) {
			for (Candidate c : arr) {
				c.setFirstName(firstName);
				c.setLastName(lastName);
				c.setEmail(email);
				c.setPhone(phone);
				c.setClient(cArr.get(0));
			}

			Query query = em.createQuery(
					"UPDATE Candidate c SET c.firstName = :firstName, c.lastName = :lastName, c.email = :email, c.phone = :phone, c.client = :client WHERE c.candidateId = :id");

			updateCount = query.setParameter("id", cId).setParameter("firstName", nFirstName)
					.setParameter("lastName", nLastName).setParameter("email", nEmail).setParameter("phone", nPhone)
					.setParameter("client", cArr.get(0)).executeUpdate();
		} else {
			for (Candidate c : arr) {
				c.setFirstName(firstName);
				c.setLastName(lastName);
				c.setEmail(email);
				c.setPhone(phone);
				c.setClient(cArr.get(0));
				c.setResume(resumeArr.get(0));
			}

			Query query = em.createQuery(
					"UPDATE Candidate c SET c.firstName = :firstName, c.lastName = :lastName, c.email = :email, c.phone = :phone, c.client = :client, c.resume = :resume WHERE c.candidateId = :id");

			updateCount = query.setParameter("id", cId).setParameter("firstName", nFirstName)
					.setParameter("lastName", nLastName).setParameter("email", nEmail).setParameter("phone", nPhone)
					.setParameter("client", cArr.get(0)).setParameter("resume", resumeArr.get(0)).executeUpdate();
		}

		em.getTransaction().commit();

		disposeCon();

		// check if object/row updated, if not throw exception
		if (updateCount > 0) {
			return arr;
		} else {
			throw new UpdateFailedException("update failed");
		}

	}

	public List<Candidate> createData(String firstName, String lastName, String email, String phone, int sessionUserId,
			String company, String position, Integer resumeId) {

		// logger
		RecruitMeLogger rmLog = new RecruitMeLogger();

		// get recruiter
		Query getRecruiter = em.createQuery("SELECT r FROM Recruiter r WHERE r.recruiterId = :rId").setParameter("rId",
				sessionUserId);
		List<Recruiter> rArr = getRecruiter.getResultList();

		// get resume
		Query getResume = em.createQuery("SELECT r FROM Resume r WHERE r.resumeId = :rId").setParameter("rId",
				resumeId);
		List<Resume> resumeArr = getResume.getResultList();

		// get client
		Query getClient = em.createQuery(
				"SELECT c FROM Client c WHERE c.clientName = :company AND c.position = :position AND c.recruiter.recruiterId = :rId")
				.setParameter("rId", sessionUserId).setParameter("company", company).setParameter("position", position);
		List<Client> cArr = getClient.getResultList();

		// check client list, if empty log event
		if (cArr.isEmpty()) {
			rmLog.logger.log(Level.WARNING, "Company and position does not exist!");
		}

		// create new candidate object reference
		Candidate c = new Candidate();

		if (resumeArr.isEmpty()) {
			// create new candidate object without resume
			c = new Candidate(firstName, lastName, email, phone, rArr.get(0), cArr.get(0));
		} else {
			// create new candidate object with resume
			c = new Candidate(firstName, lastName, email, phone, rArr.get(0), cArr.get(0), resumeArr.get(0));
		}

		// persist object into database
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();

		// fetch new candidate into arraylist
		Query getNewCandidate = em.createQuery("SELECT c FROM Candidate c WHERE c.candidateId = :cId")
				.setParameter("cId", c.getCandidateId());
		List<Candidate> arr = getNewCandidate.getResultList();

		disposeCon();

		return arr;

	}

	public List<Candidate> deleteData(int cId, int sessionUserId) throws DeleteFailedException {

		List<Candidate> arr = new ArrayList<Candidate>();

		em.getTransaction().begin();

		Query query = em
				.createQuery("DELETE FROM Candidate c WHERE c.candidateId = :id AND c.recruiter.recruiterId = :userId");
		int deleteCount = query.setParameter("id", cId).setParameter("userId", sessionUserId).executeUpdate();

		em.getTransaction().commit();
		disposeCon();

		// check if object/row is delete, if not, throw exception
		if (deleteCount > 0) {
			// return an empty array because of plugin specifications
			return arr;
		} else {
			throw new DeleteFailedException("failed to delete");
		}

	}

	public Boolean deleteResume(int cId) throws DeleteFailedException {

		em.getTransaction().begin();

		// delete resume in the resume table
		Query getCandidateById = em.createNamedQuery("getCandidateById").setParameter("id", cId);
		List<Candidate> cList = getCandidateById.getResultList();

		Query removeResume = em.createNamedQuery("deleteResumeById").setParameter("rId",
				cList.get(0).getResume().getResumeId());

		int deleteCount = removeResume.executeUpdate();

		em.getTransaction().commit();

		// check if object/row is delete, if not, throw exception
		if (deleteCount > 0) {
			return true;
		} else {
			throw new DeleteFailedException("failed to delete");
		}
	}

}
