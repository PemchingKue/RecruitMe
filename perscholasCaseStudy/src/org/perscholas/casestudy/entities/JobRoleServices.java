/*
* Filename: JobRoleServices.java
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
public class JobRoleServices extends AbstractServices {

	public List<JobRole> uploadData(InputStream inputStream, String fileName, long fileSize, String contentType)
			throws IOException, SerialException, SQLException {

		// convert inputStream to byte array
		byte[] contents = IOUtils.toByteArray(inputStream);

		// create new object with the new attributes
		JobRole jr = new JobRole(fileName, contents, fileSize, contentType);

		// persist object into database
		em.getTransaction().begin();
		em.persist(jr);
		em.getTransaction().commit();

		// retrieve newly created object/row from database
		Query getFile = em.createNamedQuery("getJobRoleById").setParameter("rId", jr.getRoleId());

		List<JobRole> jrList = getFile.getResultList();

		disposeCon();

		return jrList;

	}

	public List<JobRole> getJobRoleById(Integer id) {

		// fetch job role description by ID
		Query getJobRole = em.createNamedQuery("getJobRoleById").setParameter("rId", id);

		return getJobRole.getResultList();

	}

	public List<JobRole> getAllJobRole() {

		// fetch all job role descriptions
		Query getAllJobRoles = em.createNamedQuery("getAllJobRoles");
		List<JobRole> arr = getAllJobRoles.getResultList();

		return arr;
	}
}
