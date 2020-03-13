/*
* Filename: RecruiterServices.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.entities;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.perscholas.casestudy.customexceptions.UpdateFailedException;
import org.perscholas.casestudy.logger.RecruitMeLogger;

/**
 * @author PK
 *
 */
public class RecruiterServices extends AbstractServices {

	public List<Recruiter> fetchRecruiterByEmail(String rEmail) {

		// fetch recruiter by email
		Query getRecruiter = em.createQuery("SELECT r FROM Recruiter r WHERE r.email = :email").setParameter("email",
				rEmail);
		List<Recruiter> arr = getRecruiter.getResultList();

		disposeCon();

		return arr;

	}

	public Boolean checkEmail(String rEmail) {

		// check if the email already exists the in database
		Query checkRecruiter = em.createQuery("SELECT r FROM Recruiter r WHERE r.email = :email").setParameter("email",
				rEmail);
		List<Recruiter> arr = checkRecruiter.getResultList();

		if (arr.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public Boolean addRecruiter(String email, String password, String firstName, String lastName) {

		// create new recruiter object
		Recruiter r = new Recruiter(email, password, firstName, lastName);
		System.out.println(r);

		// persist object into database
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();

		disposeCon();

		return true;

	}

	public Boolean updateData(int userId, String firstName, String lastName, String email)
			throws UpdateFailedException {

		// retrieve recruiter object/row by ID
		Query getRecruiter = em.createNamedQuery("getById").setParameter("rid", userId);
		List<Recruiter> arr = getRecruiter.getResultList();

		// set the object attributes to new attributes
		for (Recruiter r : arr) {
			r.setFirstName(firstName);
			r.setLastName(lastName);
			r.setEmail(email);
		}

		// create new attribute variables for easier use in query
		String newFirstName = null;
		String newLastName = null;
		String newEmail = null;

		em.getTransaction().begin();

		for (Recruiter r : arr) {
			newFirstName = r.getFirstName();
			newLastName = r.getLastName();
			newEmail = r.getEmail();
		}

		Query query = em.createQuery(
				"UPDATE Recruiter r SET r.firstName = :firstName, r.lastName = :lastName, r.email = :email WHERE r.recruiterId = :id");

		int updateCount = query.setParameter("id", userId).setParameter("firstName", newFirstName)
				.setParameter("lastName", newLastName).setParameter("email", newEmail).executeUpdate();

		em.getTransaction().commit();

		disposeCon();

		// check if object/row updated, if not, throw exception
		if (updateCount > 0) {
			return true;
		} else {
			throw new UpdateFailedException("update failed");
		}

	}

	public Boolean updatePassword(int userId, String oldPassword, String newPassword) throws UpdateFailedException {
		// for logging purposes
		RecruitMeLogger rmLog = new RecruitMeLogger();

		// get recruiter by id
		Query getRecruiter = em.createNamedQuery("getById").setParameter("rid", userId);
		List<Recruiter> arr = getRecruiter.getResultList();

		/*
		 * check if password in database is equivalent to password passed in parameter
		 * if so, then update password
		 * 
		 */
		if (arr.get(0).getPassword().equals(oldPassword)) {
			em.getTransaction().begin();
			Query query = em.createQuery("UPDATE Recruiter r SET r.password = :password WHERE r.recruiterId = :id");

			int updateCount = query.setParameter("id", userId).setParameter("password", newPassword).executeUpdate();

			em.getTransaction().commit();

			// check if password updated, if not, throw exception
			if (updateCount > 0) {
				return true;
			} else {
				throw new UpdateFailedException("update password failed");
			}

		} else {
			// if old password does not match current database password, log event and
			// return false
			rmLog.logger.log(Level.WARNING, "Password does not match");
			return false;
		}

	}
}
