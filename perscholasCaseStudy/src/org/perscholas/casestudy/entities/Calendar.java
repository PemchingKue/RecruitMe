/*
* Filename: Calendar.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: calendar
 *
 */
@Entity
@Table(name = "calendar")
@NamedQueries({
		@NamedQuery(query = "SELECT c FROM Calendar c WHERE c.recruiter.recruiterId = :rId", name = "getAllEvents"),
		@NamedQuery(query = "SELECT c FROM Calendar c WHERE c.id = :id", name = "getEventById"),
		@NamedQuery(query = "SELECT c FROM Calendar c WHERE c.recruiter.recruiterId = :rId AND c.title = :title", name = "getByTitleAndrId") })
public class Calendar implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private int id;

	@Column(name = "event_name")
	private String title;

	@Column(name = "start_date")
	private String start;

	@Column(name = "end_date")
	private String end;

	@ManyToOne
	@JoinColumn(name = "recruiter_id")
	private Recruiter recruiter;

	private static final long serialVersionUID = 1L;

	/**
	 * @param title
	 * @param start
	 * @param end
	 * @param recuiter
	 */
	public Calendar(String title, String start, String end, Recruiter recuiter) {
		this.setTitle(title);
		this.setStart(start);
		this.setEnd(end);
		this.setRecruiter(recuiter);
	}

	public Calendar() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int scheduleId) {
		this.id = scheduleId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
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
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + id;
		result = prime * result + ((recruiter == null) ? 0 : recruiter.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Calendar other = (Calendar) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (id != other.id)
			return false;
		if (recruiter == null) {
			if (other.recruiter != null)
				return false;
		} else if (!recruiter.equals(other.recruiter))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Calendar [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", start=");
		builder.append(start);
		builder.append(", end=");
		builder.append(end);
		builder.append(", recruiter=");
		builder.append(recruiter);
		builder.append("]");
		return builder.toString();
	}

}
