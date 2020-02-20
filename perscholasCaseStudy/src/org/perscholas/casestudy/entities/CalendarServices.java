/**
 * 
 */
package org.perscholas.casestudy.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

/**
 * @author PK
 *
 */
public class CalendarServices extends AbstractServices{

	public List<Calendar> fetchData(int sessionUserId) {
		
		Query query = em.createNamedQuery("getAllEvents").setParameter("rId", sessionUserId);
		List<Calendar> arr = query.getResultList();
		disposeCon();
		
		return arr;
		
	}
	
	public Boolean createData(String title, String start, String end, int sessionUserId) {
		
		//get recruiter
		Query getRecruiter = em.createQuery("SELECT r FROM Recruiter r WHERE r.recruiterId = :rId").setParameter("rId", sessionUserId);
		List<Recruiter> rArr = getRecruiter.getResultList();
		
		//create new candidate object
		Calendar c = new Calendar(title, start, end, rArr.get(0));
		
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		
		disposeCon();
		
		return true;
		
	}
	
	public List<Calendar> updateData(String title, String start, String end, int eId) {
		
		Query fetchEvent = em.createNamedQuery("getEventById").setParameter("id", eId);
		List<Calendar> eList = fetchEvent.getResultList();
		
		for(Calendar c : eList) {
			c.setTitle(title);
			c.setStart(start);
			c.setEnd(end);
		}
		
		List<Calendar> arr = new ArrayList<Calendar>();
		arr.addAll(eList);
		
		String newTitle = null;
		String newStart = null;
		String newEnd = null;
		
		em.getTransaction().begin();
        
		for(Calendar c : arr) {
			newTitle = c.getTitle();
			newStart = c.getStart();
			newEnd = c.getEnd();
		}
		
		  Query query = em.createQuery(
			      "UPDATE Calendar c SET c.title= :title, c.start = :start, c.end = :end WHERE c.id = :id");
		
		int updateCount = query.setParameter("id", eId)
				.setParameter("title", newTitle)
				.setParameter("start", newStart)
				.setParameter("end", newEnd).executeUpdate();
		
		em.getTransaction().commit();
		
		disposeCon();
		
		if(updateCount > 0) {
			return arr;
		}else {
			System.out.println("update failed");
			return arr;
		}
		
	}
	
	public Boolean deleteData(int eId, int sessionUserId) {
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("DELETE FROM Calendar c WHERE c.id = :id AND c.recruiter.recruiterId = :userId");
		int deleteCount = query.setParameter("id", eId).setParameter("userId", sessionUserId).executeUpdate();
				
		em.getTransaction().commit();
		disposeCon();
		
		if(deleteCount > 1) {
			return true;
		}else {
			return false;
		}
		
		
	}
}
