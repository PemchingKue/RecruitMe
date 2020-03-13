/*
* Filename: CalendarServicesTestCases.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.perscholas.casestudy.customexceptions.DeleteFailedException;
import org.perscholas.casestudy.customexceptions.UpdateFailedException;
import org.perscholas.casestudy.entities.Calendar;
import org.perscholas.casestudy.entities.CalendarServices;

/**
 * @author pk
 *
 */
public class CalendarServicesTestCases {

	private EntityManagerFactory emf;
	private EntityManager em;
	private CalendarServices cs;
	private static int id;
	private List<Calendar> cList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("perscholasCaseStudy");
		em = emf.createEntityManager();
		cs = new CalendarServices();
		cList = new ArrayList<Calendar>();
	}

	@Test
	public void testFetchData() {
		
		Query fetchData = em.createQuery("SELECT c FROM Calendar c WHERE c.recruiter.recruiterId = :rId")
				.setParameter("rId", 1);
		List<Calendar> expected = fetchData.getResultList();
				
		List<Calendar> actual = cs.fetchData(1);
		
		assertEquals(expected.get(0).getId(), actual.get(0).getId());
	}
	
	@Test
	public void testCreateData() {
		
	    cs.createData("test1", "test2", "test3", 1);
		Query fetchData = em.createNamedQuery("getByTitleAndrId")
				.setParameter("rId", 1)
				.setParameter("title", "test1");
		cList = fetchData.getResultList();
		
		//set id value for next test cases
		id = cList.get(0).getId();
		
	    assertEquals("test1", cList.get(0).getTitle());
	    
	}
	
	@Test
	public void testUpdateAndDelete() throws UpdateFailedException {
		
	    cs.updateData("test4", "test5", "test6", id);
		Query query = em.createNamedQuery("getEventById")
				.setParameter("id", id);
		cList = query.getResultList();
		
		assertEquals("test4", cList.get(0).getTitle());		
		
	}
	
	@Test
	public void testDelete() throws DeleteFailedException {

		assertTrue(cs.deleteData(id, 1));
	}

}
