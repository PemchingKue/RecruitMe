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
	@NamedQuery(query = "SELECT c FROM Candidate c WHERE c.recruiter.recruiterId = :rId", name="getAllCandidates"),
	@NamedQuery(query = "SELECT c FROM Candidate c WHERE c.candidateId = :id", name="getCandidateById")
})
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
	
	private static final long serialVersionUID = 1L;

	public Candidate(String firstName, String lastName, String email, String phone, Recruiter recruiter) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setRecruiter(recruiter);
//		this.setClient(client);
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
   
}
