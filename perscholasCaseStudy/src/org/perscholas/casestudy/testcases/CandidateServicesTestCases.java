/*
* Filename: CandidateServicesTestCases.java
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
import org.perscholas.casestudy.entities.Candidate;
import org.perscholas.casestudy.entities.CandidateServices;

/**
 * @author pk
 *
 */
public class CandidateServicesTestCases {

	private EntityManagerFactory emf;
	private EntityManager em;
	private CandidateServices cs;
	private static int id;
	private List<Candidate> cList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("perscholasCaseStudy");
		em = emf.createEntityManager();
		cs = new CandidateServices();
		cList = new ArrayList<Candidate>();
	}

	@Test
	public void testFetchData() {
		Query fetchData = em.createQuery("SELECT c FROM Candidate c WHERE c.recruiter.recruiterId = :rId")
				.setParameter("rId", 1);
		List<Candidate> expected = fetchData.getResultList();
				
		List<Candidate> actual = cs.fetchData(1);
		
		assertEquals(expected.get(0).getCandidateId(), actual.get(0).getCandidateId());
	}
	
	@Test
	public void testFetchDataById() {
		Candidate cExpected = new Candidate();
		cExpected.setFirstName("paul");
		cExpected.setLastName("vue");
		cExpected.setEmail("hfuds@gmail.com");
		cExpected.setPhone("213-435-2345");
		cExpected.setRecruiter(null);
		cExpected.setClient(null);
		cExpected.setResume(null);
		
		cList = cs.fetchDataById(1);
		
		assertEquals(cExpected.getEmail(), cList.get(0).getEmail());
	}

	@Test
	public void testCreateData() {
		cList = cs.createData("testfirst", "testlast", "testemail", "testphone", 1, "ford", "software engineer", null);
		id = cList.get(0).getCandidateId();
		assertEquals("testemail", cList.get(0).getEmail());
		
	}
	
	@Test
	public void testUpdateAndDelete() throws UpdateFailedException, DeleteFailedException {
		cList = cs.updateData(id, "testfirst2", "testlast2", "ford", "software engineer", "testemail2", "testphone2", 1, null);
		assertEquals("testemail2", cList.get(0).getEmail());
		
	}
	
	@Test
	public void testDeleteData() throws DeleteFailedException {

		cList = cs.deleteData(id, 1);
		
		assertTrue(cList.isEmpty());
		
	}

	

}
