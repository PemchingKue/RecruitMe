package org.perscholas.casestudy.entities;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Resume
 *
 */
@Entity
@Table(name = "resume")
@NamedQueries({
	@NamedQuery(query = "SELECT r FROM Resume r WHERE r.resumeId = :rId", name = "getResumeById"),
	@NamedQuery(query = "SELECT r FROM Resume r", name = "getAllResumes"),
	@NamedQuery(query = "DELETE FROM Resume r WHERE r.resumeId = :rId", name = "deleteResumeById")
}) 
public class Resume implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "resume_id")
	private int resumeId;
	
	@Column(name = "file_name")
	private String fileName;

	@Lob
	@Column(name = "resume_data")
	private byte[] resumeData;
	
	@Column(name = "file_size")
	private long fileSize;
	
	@Column(name = "content_type")
	private String contentType;
	
	private static final long serialVersionUID = 1L;

	public Resume(String fileName, byte[] resumeData, long fileSize, String contentType) {
		this.setFileName(fileName);
		this.setResumeData(resumeData);
		this.setFileSize(fileSize);
		this.setContentType(contentType);

	}
	
	public Resume() {
		super();
	}

	/**
	 * @return the resumeId
	 */
	public int getResumeId() {
		return resumeId;
	}

	/**
	 * @param resumeId the resumeId to set
	 */
	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * @return the resumeData
	 */
	public byte[] getResumeData() {
		return resumeData;
	}

	/**
	 * @param resumeData the resumeData to set
	 */
	public void setResumeData(byte[] resumeData) {
		this.resumeData = resumeData;
	}

	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
   
}
