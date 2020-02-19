/**
 * 
 */
package org.perscholas.casestudy.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author CTStudent
 *
 */
public abstract class AbstractServices {
	
	protected EntityManagerFactory emf;
	protected EntityManager em;
	
	public AbstractServices() {
		emf = Persistence.createEntityManagerFactory("perscholasCaseStudy");
		em = emf.createEntityManager();
	}
	
	public void disposeCon() {
		em.close();
		emf.close();
	}
}
