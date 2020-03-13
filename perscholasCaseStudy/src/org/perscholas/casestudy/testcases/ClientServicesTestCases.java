/*
* Filename: ClientServicesTestCases.java
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
import org.perscholas.casestudy.entities.Client;
import org.perscholas.casestudy.entities.ClientServices;

/**
 * @author pk
 *
 */
public class ClientServicesTestCases {

	private EntityManagerFactory emf;
	private EntityManager em;
	private ClientServices cs;
	private static int id;
	private List<Client> cList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("perscholasCaseStudy");
		em = emf.createEntityManager();
		cs = new ClientServices();
		cList = new ArrayList<Client>();
	}

	@Test
	public void testFetchData() {
		Query fetchData = em.createQuery("SELECT c FROM Client c WHERE c.recruiter.recruiterId = :rId")
				.setParameter("rId", 1);
		List<Client> expected = fetchData.getResultList();
				
		List<Client> actual = cs.fetchData(1);
		
		assertEquals(expected.get(0).getClientId(), actual.get(0).getClientId());
	}
	
	@Test
	public void testCreateData() {
		cList = cs.createData("testname", "testpos", 1, null);
		id = cList.get(0).getClientId();
		assertEquals("testname", cList.get(0).getClientName());
		
	}
	
	@Test
	public void testUpdateAndDelete() throws UpdateFailedException, DeleteFailedException {
		cList = cs.updateData("testname2", "testpos2", id, 1, null);
		assertEquals("testname2", cList.get(0).getClientName());
	}
	
	@Test
	public void testDeleteData() throws DeleteFailedException {
		cList = cs.deleteData(id, 1);
		
		assertTrue(cList.isEmpty());
	}

}
