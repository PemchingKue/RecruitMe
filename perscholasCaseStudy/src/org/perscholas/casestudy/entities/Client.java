/*
* Filename: Client.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Client
 *
 */
@Entity
@Table(name = "client")
@NamedQueries({
		@NamedQuery(query = "SELECT c FROM Client c WHERE c.recruiter.recruiterId = :rId", name = "getAllClients"),
		@NamedQuery(query = "SELECT c FROM Client c WHERE c.clientId = :id", name = "getClientById") })
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private int clientId;

	@Column(name = "client_name")
	private String clientName;

	@Column(name = "position")
	private String position;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private JobRole role;

	@ManyToOne
	@JoinColumn(name = "recruiter_id")
	private Recruiter recruiter;

	private static final long serialVersionUID = 1L;

	/**
	 * @param clientName
	 * @param position
	 * @param role
	 * @param recruiter
	 */
	public Client(String clientName, String position, JobRole role, Recruiter recruiter) {
		this.setClientName(clientName);
		this.setPosition(position);
		this.setRole(role);
		this.setRecruiter(recruiter);
	}

	// constructor for client without a job role description
	/**
	 * @param clientName
	 * @param position
	 * @param recruiter
	 */
	public Client(String clientName, String position, Recruiter recruiter) {
		this.setClientName(clientName);
		this.setPosition(position);
		this.setRecruiter(recruiter);
	}

	public Client() {
		super();
	}

	/**
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the roleDes
	 */
	public JobRole getRole() {
		return role;
	}

	/**
	 * @param roleDes the roleDes to set
	 */
	public void setRole(JobRole role) {
		this.role = role;
	}

	/**
	 * @return the recruiter
	 */
	public Recruiter getRecruiter() {
		return recruiter;
	}

	/**
	 * @param recruiter the recruiter to set
	 */
	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
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
		result = prime * result + clientId;
		result = prime * result + ((clientName == null) ? 0 : clientName.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((recruiter == null) ? 0 : recruiter.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		Client other = (Client) obj;
		if (clientId != other.clientId)
			return false;
		if (clientName == null) {
			if (other.clientName != null)
				return false;
		} else if (!clientName.equals(other.clientName))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (recruiter == null) {
			if (other.recruiter != null)
				return false;
		} else if (!recruiter.equals(other.recruiter))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [clientId=");
		builder.append(clientId);
		builder.append(", clientName=");
		builder.append(clientName);
		builder.append(", position=");
		builder.append(position);
		builder.append(", role=");
		builder.append(role);
		builder.append(", recruiter=");
		builder.append(recruiter);
		builder.append("]");
		return builder.toString();
	}

}
