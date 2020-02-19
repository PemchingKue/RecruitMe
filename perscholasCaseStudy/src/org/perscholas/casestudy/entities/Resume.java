package org.perscholas.casestudy.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Resume
 *
 */
@Entity
@Table(name = "resume")
public class Resume implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "resume_id")
	private int resumeId;
	
	@Column(name = "resume_data")
	private String resumeData;
	
	@ManyToOne
	@JoinColumn(name = "candidate")
	private Candidate candidate;
	
	private static final long serialVersionUID = 1L;

	public Resume(String resumeData, Candidate candidate) {
		this.setResumeData(resumeData);
		this.setCandidate(candidate);
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

	/**
	 * @return the resumeData
	 */
	public String getResumeData() {
		return resumeData;
	}

	/**
	 * @param resumeData the resumeData to set
	 */
	public void setResumeData(String resumeData) {
		this.resumeData = resumeData;
	}

	/**
	 * @return the candidate
	 */
	public Candidate getCandidate() {
		return candidate;
	}

	/**
	 * @param candidate the candidate to set
	 */
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
   
}
