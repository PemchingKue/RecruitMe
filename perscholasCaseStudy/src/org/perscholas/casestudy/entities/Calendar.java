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
	@NamedQuery(query = "SELECT c FROM Calendar c WHERE c.recruiter.recruiterId = :rId", name="getAllEvents"),
	@NamedQuery(query = "SELECT c FROM Calendar c WHERE c.id = :id", name="getEventById")
})
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

}
