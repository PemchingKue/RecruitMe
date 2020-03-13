/*
* Filename: ResumeServicesTestCases.java
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
import org.perscholas.casestudy.entities.Resume;
import org.perscholas.casestudy.entities.ResumeServices;

/**
 * @author pk
 *
 */
public class ResumeServicesTestCases {

	private EntityManagerFactory emf;
	private EntityManager em;
	private ResumeServices rs;
	private static int id;
	private List<Resume> rList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("perscholasCaseStudy");
		em = emf.createEntityManager();
		rs = new ResumeServices();
		rList = new ArrayList<Resume>();
	}

	@Test
	public void testUploadData() throws IOException, SerialException, SQLException {
		InputStream testStream = IOUtils.toInputStream("some test data for my input stream", "UTF-8");
		
		rList = rs.uploadData(testStream, "testName", 999999, "testtype");
		id = rList.get(0).getResumeId();
		assertEquals("testName", rList.get(0).getFileName());
	}

	@Test
	public void testGetResumeById() {
		Resume rExpected = new Resume();
		rExpected.setFileName("resume_1.docx");
		rExpected.setResumeData(null);
		rExpected.setFileSize(26338);
		rExpected.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		
		rList = rs.getResumeById(49);
		
		assertEquals(rExpected.getFileName(), rList.get(0).getFileName());
	}
	
	@Test
	public void testGetAllResumes() {
		Query fetchData = em.createQuery("SELECT r FROM Resume r");
		List<Resume> expected = fetchData.getResultList();
		
		List<Resume> actual = rs.getAllResumes();

		assertEquals(expected.get(0).getResumeId(), actual.get(0).getResumeId());
	}
	
	@Test
	public void testDeleteResume() {
	em.getTransaction().begin();
	
	Query query = em.createNamedQuery("deleteResumeById")
			.setParameter("rId", id);
	
	int deleteCount = query.executeUpdate();
	
	em.getTransaction().commit();
	
	assertTrue(deleteCount > 0);
	
	}
	
}
