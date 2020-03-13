/*
* Filename: JobRole.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: JobRole
 *
 */
@Entity
@Table(name = "job_role")
@NamedQueries({ @NamedQuery(query = "SELECT jr FROM JobRole jr WHERE jr.roleId = :rId", name = "getJobRoleById"),
		@NamedQuery(query = "SELECT jr FROM JobRole jr", name = "getAllJobRoles"),
		@NamedQuery(query = "DELETE FROM JobRole jr WHERE jr.roleId = :rId", name = "deleteJobRoleById") })
public class JobRole implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "file_name")
	private String fileName;

	@Lob
	@Column(name = "role_data")
	private byte[] roleData;

	@Column(name = "file_size")
	private long fileSize;

	@Column(name = "content_type")
	private String contentType;

	private static final long serialVersionUID = 1L;

	/**
	 * @param fileName
	 * @param roleData
	 * @param fileSize
	 * @param contentType
	 */
	public JobRole(String fileName, byte[] roleData, long fileSize, String contentType) {
		this.setFileName(fileName);
		this.setRoleData(roleData);
		this.setFileSize(fileSize);
		this.setContentType(contentType);
	}

	public JobRole() {
		super();
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the roleData
	 */
	public byte[] getRoleData() {
		return roleData;
	}

	/**
	 * @param roleData the roleData to set
	 */
	public void setRoleData(byte[] roleData) {
		this.roleData = roleData;
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
		result = prime * result + Arrays.hashCode(roleData);
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		JobRole other = (JobRole) obj;
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
		if (!Arrays.equals(roleData, other.roleData))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobRole [roleId=");
		builder.append(roleId);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", roleData=");
		builder.append(Arrays.toString(roleData));
		builder.append(", fileSize=");
		builder.append(fileSize);
		builder.append(", contentType=");
		builder.append(contentType);
		builder.append("]");
		return builder.toString();
	}

}
