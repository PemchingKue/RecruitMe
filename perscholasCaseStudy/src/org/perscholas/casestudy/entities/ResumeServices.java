/*
* Filename: ResumeServices.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.entities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;

/**
 * @author PK
 *
 */
public class ResumeServices extends AbstractServices {

	public List<Resume> uploadData(InputStream inputStream, String fileName, long fileSize, String contentType)
			throws IOException, SerialException, SQLException {

		// convert inputStream to byte array
		byte[] contents = IOUtils.toByteArray(inputStream);

		// create new object with the new attributes
		Resume r = new Resume(fileName, contents, fileSize, contentType);

		// persist object into database
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();

		// retrieve newly created object/row from database
		Query getFile = em.createNamedQuery("getResumeById").setParameter("rId", r.getResumeId());

		List<Resume> rList = getFile.getResultList();

		disposeCon();

		return rList;

	}

	public List<Resume> getResumeById(Integer id) {

		// fetch resume description by ID
		Query getResume = em.createNamedQuery("getResumeById").setParameter("rId", id);

		return getResume.getResultList();

	}

	public List<Resume> getAllResumes() {

		// fetch all resumes
		Query getAllResumes = em.createNamedQuery("getAllResumes");
		List<Resume> arr = getAllResumes.getResultList();

		return arr;
	}
}
