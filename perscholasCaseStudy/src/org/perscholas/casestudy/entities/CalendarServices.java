/*
* Filename: CalendarServices.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.perscholas.casestudy.customexceptions.DeleteFailedException;
import org.perscholas.casestudy.customexceptions.UpdateFailedException;

/**
 * @author PK
 *
 */
public class CalendarServices extends AbstractServices {

	public List<Calendar> fetchData(int sessionUserId) {

		// get all events from recruiter
		Query query = em.createNamedQuery("getAllEvents").setParameter("rId", sessionUserId);
		List<Calendar> arr = query.getResultList();
		disposeCon();

		return arr;

	}

	public Boolean createData(String title, String start, String end, int sessionUserId) {

		// get recruiter
		Query getRecruiter = em.createQuery("SELECT r FROM Recruiter r WHERE r.recruiterId = :rId").setParameter("rId",
				sessionUserId);
		List<Recruiter> rArr = getRecruiter.getResultList();

		// create new candidate object
		Calendar c = new Calendar(title, start, end, rArr.get(0));

		// persist candidate object into database
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();

		disposeCon();

		return true;

	}

	public List<Calendar> updateData(String title, String start, String end, int eId) throws UpdateFailedException {

		// fetch event by Id
		Query fetchEvent = em.createNamedQuery("getEventById").setParameter("id", eId);
		List<Calendar> eList = fetchEvent.getResultList();

		// replace object attributes with attributes passed in
		for (Calendar c : eList) {
			c.setTitle(title);
			c.setStart(start);
			c.setEnd(end);
		}

		// create new array list of calendar object references
		List<Calendar> arr = new ArrayList<Calendar>();
		arr.addAll(eList);

		String newTitle = null;
		String newStart = null;
		String newEnd = null;

		em.getTransaction().begin();

		// take object reference attributes and store it in new variables for easier use
		// in update query
		for (Calendar c : arr) {
			newTitle = c.getTitle();
			newStart = c.getStart();
			newEnd = c.getEnd();
		}

		// update the object/row
		Query query = em
				.createQuery("UPDATE Calendar c SET c.title= :title, c.start = :start, c.end = :end WHERE c.id = :id");

		int updateCount = query.setParameter("id", eId).setParameter("title", newTitle).setParameter("start", newStart)
				.setParameter("end", newEnd).executeUpdate();

		em.getTransaction().commit();

		disposeCon();

		// check if the row was updated, if not throw exception
		if (updateCount > 0) {
			return arr;
		} else {
			throw new UpdateFailedException("update failed");
		}

	}

	public Boolean deleteData(int eId, int sessionUserId) throws DeleteFailedException {

		em.getTransaction().begin();

		// delete object/row
		Query query = em.createQuery("DELETE FROM Calendar c WHERE c.id = :id AND c.recruiter.recruiterId = :userId");
		int deleteCount = query.setParameter("id", eId).setParameter("userId", sessionUserId).executeUpdate();

		em.getTransaction().commit();
		disposeCon();

		// check if row was deleted, if not throw exception
		if (deleteCount > 0) {
			return true;
		} else {
			throw new DeleteFailedException("failed to delete");
		}

	}
}
