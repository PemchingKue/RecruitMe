/*
* Filename: ClientServices.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.perscholas.casestudy.customexceptions.DeleteFailedException;
import org.perscholas.casestudy.customexceptions.UpdateFailedException;
import org.perscholas.casestudy.logger.RecruitMeLogger;

/**
 * @author PK
 *
 */
public class ClientServices extends AbstractServices {

	public List<Client> fetchData(int sessionUserId) {

		// fetch all clients under the recruiter
		Query query = em.createNamedQuery("getAllClients").setParameter("rId", sessionUserId);
		List<Client> arr = query.getResultList();
		disposeCon();

		return arr;

	}

	public List<Client> updateData(String clientName, String position, int cId, int sessionUserId, Integer roleId)
			throws UpdateFailedException, DeleteFailedException {

		// logger
		RecruitMeLogger rmLog = new RecruitMeLogger();

		// get client
		Query fetchClient = em.createNamedQuery("getClientById").setParameter("id", cId);
		List<Client> cList = fetchClient.getResultList();

		// get job role
		Query getJobRole = em.createQuery("SELECT jr FROM JobRole jr WHERE jr.roleId = :rId").setParameter("rId",
				roleId);
		List<JobRole> jobRoleArr = getJobRole.getResultList();

		// delete job role description because if null and exist
		if (roleId == null && cList.get(0).getRole() != null) {
			deleteJobRole(cId);
		}

		// define new attribute variables
		int updateCount = 0;
		int newCId = cId;
		String newClientName = clientName;
		String newPosition = position;

		em.getTransaction().begin();

		// check if client has a job role description or not, then perform specific
		// update
		if (jobRoleArr.isEmpty()) {
			for (Client c : cList) {
				c.setClientName(clientName);
				c.setPosition(position);
			}

			Query query = em.createQuery(
					"UPDATE Client c SET c.clientName = :clientName, c.position = :position WHERE c.clientId = :id");

			updateCount = query.setParameter("id", newCId).setParameter("clientName", newClientName)
					.setParameter("position", newPosition).executeUpdate();

		} else {
			for (Client c : cList) {
				c.setClientName(clientName);
				c.setPosition(position);
				c.setRole(jobRoleArr.get(0));
			}

			Query query = em.createQuery(
					"UPDATE Client c SET c.clientName = :clientName, c.position = :position, c.role = :role WHERE c.clientId = :id");

			updateCount = query.setParameter("id", newCId).setParameter("clientName", newClientName)
					.setParameter("position", newPosition).setParameter("role", jobRoleArr.get(0)).executeUpdate();
		}

		em.getTransaction().commit();

		disposeCon();

		// check if object/row updated, if not throw exception
		if (updateCount > 0) {
			return cList;
		} else {
			throw new UpdateFailedException("update failed");
		}

	}

	public List<Client> createData(String clientName, String position, int sessionUserId, Integer roleId) {

		// get recruiter
		Query getRecruiter = em.createQuery("SELECT r FROM Recruiter r WHERE r.recruiterId = :rId").setParameter("rId",
				sessionUserId);
		List<Recruiter> rArr = getRecruiter.getResultList();

		// get job role
		Query getJobRole = em.createQuery("SELECT jr FROM JobRole jr WHERE jr.roleId = :rId").setParameter("rId",
				roleId);
		List<JobRole> jobRoleArr = getJobRole.getResultList();

		// create new client object reference
		Client c = new Client();

		if (jobRoleArr.isEmpty()) {
			// create new client object without job role
			c = new Client(clientName, position, rArr.get(0));
		} else {
			// create new client object with job role
			c = new Client(clientName, position, jobRoleArr.get(0), rArr.get(0));
		}

		// persist object into database
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();

		// fetch new client into arraylist
		Query getNewClient = em.createQuery("SELECT c FROM Client c WHERE c.clientId = :cId").setParameter("cId",
				c.getClientId());
		List<Client> arr = getNewClient.getResultList();

		disposeCon();

		return arr;

	}

	public List<Client> deleteData(int cId, int sessionUserId) throws DeleteFailedException {

		List<Client> arr = new ArrayList<Client>();

		em.getTransaction().begin();

		Query query = em
				.createQuery("DELETE FROM Client c WHERE c.clientId = :id AND c.recruiter.recruiterId = :userId");
		int deleteCount = query.setParameter("id", cId).setParameter("userId", sessionUserId).executeUpdate();

		em.getTransaction().commit();
		disposeCon();

		// check if object/row is delete, if not, throw exception
		if (deleteCount > 0) {
			// return an empty array because of plugin specifications
			return arr;
		} else {
			throw new DeleteFailedException("failed to delete");
		}

	}

	public Boolean deleteJobRole(int cId) throws DeleteFailedException {

		em.getTransaction().begin();

		// delete job role in job role table
		Query getClientById = em.createNamedQuery("getClientById").setParameter("id", cId);
		List<Client> cList = getClientById.getResultList();

		Query removeRole = em.createNamedQuery("deleteJobRoleById").setParameter("rId",
				cList.get(0).getRole().getRoleId());

		int deleteCount = removeRole.executeUpdate();

		em.getTransaction().commit();

		// check if object/row is delete, if not, throw exception
		if (deleteCount > 0) {
			return true;
		} else {
			throw new DeleteFailedException("failed to delete");
		}
	}

}
