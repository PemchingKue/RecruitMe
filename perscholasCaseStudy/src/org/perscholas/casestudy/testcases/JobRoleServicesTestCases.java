/*
* Filename: JobRoleServicesTestCases.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.testcases;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.perscholas.casestudy.entities.ClientServices;
import org.perscholas.casestudy.entities.JobRole;
import org.perscholas.casestudy.entities.JobRoleServices;

/**
 * @author pk
 *
 */
public class JobRoleServicesTestCases {

	private EntityManagerFactory emf;
	private EntityManager em;
	private JobRoleServices jrs;
	private static int id;
	private List<JobRole> jrList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("perscholasCaseStudy");
		em = emf.createEntityManager();
		jrs = new JobRoleServices();
		jrList = new ArrayList<JobRole>();
	}

	@Test
	public void testUploadData() throws SerialException, IOException, SQLException {
		
		InputStream testStream = IOUtils.toInputStream("some test data for my input stream", "UTF-8");
		
		jrList = jrs.uploadData(testStream, "testName", 999999, "testtype");
		id = jrList.get(0).getRoleId();
		assertEquals("testName", jrList.get(0).getFileName());
		
	}
	
	@Test
	public void testGetJobRoleById() {
		JobRole jExpected = new JobRole();
		jExpected.setFileName("jobdes_1.docx");
		jExpected.setRoleData(null);
		jExpected.setFileSize(12707);
		jExpected.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		
		jrList = jrs.getJobRoleById(17);
		
		assertEquals(jExpected.getFileName(), jrList.get(0).getFileName());
	}
	
	@Test
	public void testGetAllJobRole() {
		Query fetchData = em.createQuery("SELECT jr FROM JobRole jr");
		List<JobRole> expected = fetchData.getResultList();
		
		List<JobRole> actual = jrs.getAllJobRole();

		assertEquals(expected.get(0).getRoleId(), actual.get(0).getRoleId());
	}
	
	@Test
	public void testDeleteJobRole() {
		
		em.getTransaction().begin();
		
		Query query = em.createNamedQuery("deleteJobRoleById")
				.setParameter("rId", id);
		
		int deleteCount = query.executeUpdate();
		
		em.getTransaction().commit();
		
		assertTrue(deleteCount > 0);
	}

}
