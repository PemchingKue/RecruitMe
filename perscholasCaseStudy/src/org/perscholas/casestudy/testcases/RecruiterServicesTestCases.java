/*
* Filename: RecruiterServicesTestCases.java
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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.perscholas.casestudy.customexceptions.UpdateFailedException;
import org.perscholas.casestudy.entities.Recruiter;
import org.perscholas.casestudy.entities.RecruiterServices;

/**
 * @author pk
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecruiterServicesTestCases {

	private EntityManagerFactory emf;
	private EntityManager em;
	private RecruiterServices rs;
	private static int id;
	private List<Recruiter> rList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("perscholasCaseStudy");
		em = emf.createEntityManager();
		rs = new RecruiterServices();
		rList = new ArrayList<Recruiter>();
	}

	@Test
	public void testFetchRecruiterByEmail() {
		rList = rs.fetchRecruiterByEmail("kwmpk12@gmail.com");
		
		assertEquals("kwmpk12@gmail.com", rList.get(0).getEmail());
	}
	
	@Test
	public void testCheckEmail() {
		assertTrue(rs.checkEmail("kwmpk12@gmail.com"));
		assertFalse(rs.checkEmail("tetsdf@gmail.com"));
	}

	@Test
	public void testAddRecruiter() {
		Recruiter rExpected = new Recruiter();
		rExpected.setEmail("testemail@gmail.com");
		rExpected.setFirstName("testname");
		rExpected.setLastName("testlast");
		rExpected.setPassword("testpass");
		
		assertTrue(rs.addRecruiter("testemail@gmail.com", "testpass", "testname", "testlast"));
		
		Query query = em.createQuery("SELECT r FROM Recruiter r WHERE r.email = :email")
				.setParameter("email", "testemail@gmail.com");
		
		rList = query.getResultList();
		id = rList.get(0).getRecruiterId();
		
		assertEquals(rExpected.getEmail(), rList.get(0).getEmail());
		
	}
	
	@Test
	public void testUpdateRecruiter() throws UpdateFailedException {
		Recruiter rExpected = new Recruiter();
		rExpected.setEmail("testemail2@gmail.com");
		rExpected.setFirstName("testname");
		rExpected.setLastName("testlast");
		rExpected.setPassword("testpass");
		
		assertTrue(rs.updateData(id, "testname", "testlast", "testemail2@gmail.com"));
		
		Query query = em.createQuery("SELECT r FROM Recruiter r WHERE r.email = :email")
				.setParameter("email", "testemail2@gmail.com");
		
		rList = query.getResultList();
		
		//Delete test account
		em.getTransaction().begin();
		Query delete = em.createQuery("DELETE FROM Recruiter r WHERE r.recruiterId = :rId")
				.setParameter("rId", id);
		delete.executeUpdate();
		em.getTransaction().commit();
		
		assertEquals(rExpected.getEmail(), rList.get(0).getEmail());
		
	}
	
	@Test
	public void testUpdatePassword() throws UpdateFailedException {
		Recruiter rExpected = new Recruiter();
		rExpected.setEmail("testemail2@gmail.com");
		rExpected.setFirstName("testname");
		rExpected.setLastName("testlast");
		rExpected.setPassword("newpass");
		
		assertTrue(rs.updatePassword(id, "testpass", "newpass"));
		
		Query query = em.createQuery("SELECT r FROM Recruiter r WHERE r.recruiterId = :id")
				.setParameter("id", id);
		
		rList = query.getResultList();
		
		assertEquals(rExpected.getPassword(), rList.get(0).getPassword());
	}
	
}
