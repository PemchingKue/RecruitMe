/**
 * 
 */
package org.perscholas.casestudy.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

/**
 * @author PK
 *
 */
public class ClientServices extends AbstractServices{

	public List<Client> fetchData(int sessionUserId) {
		
		Query query = em.createNamedQuery("getAllClients").setParameter("rId", sessionUserId);
		List<Client> arr = query.getResultList();
		disposeCon();
		
		return arr;
		
	}
	
	public List<Client> updateData(String clientName, String position, int cId) {
		
		Query fetchClient = em.createNamedQuery("getClientById").setParameter("id", cId);
		List<Client> cList = fetchClient.getResultList();
		
		for(Client c : cList) {
			c.setClientName(clientName);
			c.setPosition(position);
		}
		
		List<Client> arr = new ArrayList<Client>();
		arr.addAll(cList);
		
		int newCId = 0;
		String newClientName = null;
		String newPosition = null;
		
		em.getTransaction().begin();
        
		for(Client c : arr) {
			newCId = c.getClientId();
			newClientName = c.getClientName();
			newPosition = c.getPosition();
		}
		
		  Query query = em.createQuery(
			      "UPDATE Client c SET c.clientName = :clientName, c.position = :position WHERE c.clientId = :id");
		
		int updateCount = query.setParameter("id", newCId)
				.setParameter("clientName", newClientName)
				.setParameter("position", newPosition).executeUpdate();
		
		em.getTransaction().commit();
		
		disposeCon();
		
		if(updateCount > 0) {
			return arr;
		}else {
			System.out.println("update failed");
			return arr;
		}
		
	}
	
	public List<Client> deleteData(int cId, int sessionUserId) {
		
		List<Client> arr = new ArrayList<Client>();
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("DELETE FROM Client c WHERE c.clientId = :id AND c.recruiter.recruiterId = :userId");
		int deleteCount = query.setParameter("id", cId).setParameter("userId", sessionUserId).executeUpdate();
				
		em.getTransaction().commit();
		disposeCon();
		
		return arr;
		
	}
	
	public List<Client> createData(String clientName, String position, int sessionUserId) {
		
		//get recruiter
		Query getRecruiter = em.createQuery("SELECT r FROM Recruiter r WHERE r.recruiterId = :rId").setParameter("rId", sessionUserId);
		List<Recruiter> rArr = getRecruiter.getResultList();
		
		//create new candidate object
		Client c = new Client(clientName, position, null, rArr.get(0));
		List<Client> arr = new ArrayList<Client>();
		
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		
		//test purposes to check if clientID got auto incremented
		System.out.println("THIS IS THE NEW ID: " + c.getClientId());
		
		//fetch new candidate into arraylist, add check for email must be unique!
		Query getNewClient = em.createQuery("SELECT c FROM Client c WHERE c.clientId = :cId").setParameter("cId", c.getClientId());
		arr = getNewClient.getResultList();
		
		disposeCon();
		
		return arr;
		
	}
	
}
