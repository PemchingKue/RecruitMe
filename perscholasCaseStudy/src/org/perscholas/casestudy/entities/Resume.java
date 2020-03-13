/*
* Filename: Resume.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.entities;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Arrays;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Resume
 *
 */
@Entity
@Table(name = "resume")
@NamedQueries({ @NamedQuery(query = "SELECT r FROM Resume r WHERE r.resumeId = :rId", name = "getResumeById"),
		@NamedQuery(query = "SELECT r FROM Resume r", name = "getAllResumes"),
		@NamedQuery(query = "DELETE FROM Resume r WHERE r.resumeId = :rId", name = "deleteResumeById") })
public class Resume implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "resume_id")
	private Integer resumeId;

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

	/**
	 * @param fileName
	 * @param resumeData
	 * @param fileSize
	 * @param contentType
	 */
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
	public Integer getResumeId() {
		return resumeId;
	}

	/**
	 * @param resumeId the resumeId to set
	 */
	public void setResumeId(Integer resumeId) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + (int) (fileSize ^ (fileSize >>> 32));
		result = prime * result + Arrays.hashCode(resumeData);
		result = prime * result + ((resumeId == null) ? 0 : resumeId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resume other = (Resume) obj;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (fileSize != other.fileSize)
			return false;
		if (!Arrays.equals(resumeData, other.resumeData))
			return false;
		if (resumeId == null) {
			if (other.resumeId != null)
				return false;
		} else if (!resumeId.equals(other.resumeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Resume [resumeId=");
		builder.append(resumeId);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", resumeData=");
		builder.append(Arrays.toString(resumeData));
		builder.append(", fileSize=");
		builder.append(fileSize);
		builder.append(", contentType=");
		builder.append(contentType);
		builder.append("]");
		return builder.toString();
	}

}
