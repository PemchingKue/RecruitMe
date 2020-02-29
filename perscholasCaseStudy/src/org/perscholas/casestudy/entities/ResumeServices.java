/**
 * 
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
 * @author pk
 *
 */
public class ResumeServices extends AbstractServices{

	public List<Resume> uploadData(InputStream inputStream, String fileName, long fileSize, String contentType) throws IOException, SerialException, SQLException {
		
		byte[] contents = IOUtils.toByteArray(inputStream);
		Resume r = new Resume(fileName, contents, fileSize, contentType);
		
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
		
		Query getFile = em.createNamedQuery("getResumeById")
				.setParameter("rId", r.getResumeId());
		
		List<Resume> rList = getFile.getResultList();
		
		disposeCon();
		
		return rList;
		
	}
	
	public List<Resume> getResumeById(Integer id){
		
		Query getResume = em.createNamedQuery("getResumeById").setParameter("rId", id);
		
		return getResume.getResultList();
		
	}
	
	public List<Resume> getAllResumes(){
		
		Query getAllResumes = em.createNamedQuery("getAllResumes");
		List<Resume> arr = getAllResumes.getResultList();
		
		return arr;
	}
}
