/*
* Filename: Candidate.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Candidate
 *
 */
@Entity
@Table(name = "candidate")
@NamedQueries({
		@NamedQuery(query = "SELECT c FROM Candidate c WHERE c.recruiter.recruiterId = :rId", name = "getAllCandidates"),
		@NamedQuery(query = "SELECT c FROM Candidate c WHERE c.candidateId = :id", name = "getCandidateById") })
public class Candidate implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_id")
	private int candidateId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@ManyToOne
	@JoinColumn(name = "recruiter_id")
	private Recruiter recruiter;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "resume_id")
	private Resume resume;

	private static final long serialVersionUID = 1L;

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param recruiter
	 * @param client
	 * @param resume
	 */
	public Candidate(String firstName, String lastName, String email, String phone, Recruiter recruiter, Client client,
			Resume resume) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setRecruiter(recruiter);
		this.setClient(client);
		this.setResume(resume);
	}

	// Third constructor to handle client that doesn't have a resume
	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param recruiter
	 * @param client
	 */
	public Candidate(String firstName, String lastName, String email, String phone, Recruiter recruiter,
			Client client) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setRecruiter(recruiter);
		this.setClient(client);
	}

	public Candidate() {
		super();
	}

	/**
	 * @return the candidateId
	 */
	public int getCandidateId() {
		return candidateId;
	}

	/**
	 * @param candidateId the candidateId to set
	 */
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the recruiter
	 */
	public Recruiter getRecruiter() {
		return recruiter;
	}

	/**
	 * @param recruiter_id the recruiter_id to set
	 */
	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the resume
	 */
	public Resume getResume() {
		return resume;
	}

	/**
	 * @param resume the resume to set
	 */
	public void setResume(Resume resume) {
		this.resume = resume;
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
		result = prime * result + candidateId;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((recruiter == null) ? 0 : recruiter.hashCode());
		result = prime * result + ((resume == null) ? 0 : resume.hashCode());
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
		Candidate other = (Candidate) obj;
		if (candidateId != other.candidateId)
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (recruiter == null) {
			if (other.recruiter != null)
				return false;
		} else if (!recruiter.equals(other.recruiter))
			return false;
		if (resume == null) {
			if (other.resume != null)
				return false;
		} else if (!resume.equals(other.resume))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Candidate [candidateId=");
		builder.append(candidateId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", recruiter=");
		builder.append(recruiter);
		builder.append(", client=");
		builder.append(client);
		builder.append(", resume=");
		builder.append(resume);
		builder.append("]");
		return builder.toString();
	}

}
