/*
* Filename: AbstractServices.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.perscholas.casestudy.interfaces.PersistenceUnitName;

/**
 * @author PK
 *
 */
public abstract class AbstractServices implements PersistenceUnitName{
	
	protected EntityManagerFactory emf;
	protected EntityManager em;
	
	public AbstractServices() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}
	
	public void disposeCon() {
		em.close();
		emf.close();
	}
}
