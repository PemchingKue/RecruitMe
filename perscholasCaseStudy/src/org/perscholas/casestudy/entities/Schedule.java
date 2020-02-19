package org.perscholas.casestudy.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: schedule
 *
 */
@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scheduleId;

	@Column(name = "event_name")
	private String eventName;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	@ManyToOne
	@JoinColumn(name = "recruiter")
	private Recruiter recruiter;

	private static final long serialVersionUID = 1L;

	public Schedule(String eventName, String startDate, String endDate, Recruiter recuiter) {
		this.setEventName(eventName);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setRecruiter(recuiter);
	}

	public Schedule() {
		super();
	}

	/**
	 * @return the scheduleId
	 */
	public int getScheduleId() {
		return scheduleId;
	}

	/**
	 * @param scheduleId the scheduleId to set
	 */
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
