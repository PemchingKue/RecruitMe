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
	@NamedQuery(query = "SELECT c FROM Client c WHERE c.recruiter.recruiterId = :rId", name="getAllClients"),
	@NamedQuery(query = "SELECT c FROM Client c WHERE c.clientId = :id", name="getClientById")
})
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private int clientId;
	
	@Column(name = "client_name")
	private String clientName;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "role_des")
	private String roleDes;
	
	@ManyToOne
	@JoinColumn(name = "recruiter_id")
	private Recruiter recruiter;
	
	private static final long serialVersionUID = 1L;

	public Client(String clientName, String position, String roleDes, Recruiter recruiter) {
		this.setClientName(clientName);
		this.setPosition(position);
		this.setRoleDes(roleDes);
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
	public String getRoleDes() {
		return roleDes;
	}

	/**
	 * @param roleDes the roleDes to set
	 */
	public void setRoleDes(String roleDes) {
		this.roleDes = roleDes;
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
   
}
