/*
* Filename: FetchClientServlet.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.perscholas.casestudy.entities.Client;
import org.perscholas.casestudy.entities.ClientServices;
import org.perscholas.casestudy.entities.JobRole;
import org.perscholas.casestudy.entities.JobRoleServices;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class FetchClientServlet
 */
@WebServlet("/FetchClientServlet")
public class FetchClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchClientServlet() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get session, and user ID
		HttpSession session = request.getSession();
		int sessionUserId = (int) session.getAttribute("id");
		
		PrintWriter out = response.getWriter();

		// Retrieve arguments sent from ajax request
		int draw = Integer.parseInt(request.getParameter("draw"));
		String search = request.getParameter("search[value]");
		int start = Integer.parseInt(request.getParameter("start"));
		int length  = Integer.parseInt(request.getParameter("length"));
		final int sortColumnIndex = Integer.parseInt(request.getParameter("order[0][column]"));
		final int sortDirection = request.getParameter("order[0][dir]").equals("asc") ? -1 : 1;

		// define response attributes that need to be sent back with json array
		int recordsTotal = 0;
		int recordsFiltered = 0;

		// retrieve all data from database then store in List
		ClientServices cs = new ClientServices();
		List<Client> client = cs.fetchData(sessionUserId);
		recordsTotal = client.size();

		// Create new array list to store data after filtering
		List<Client> data = new ArrayList<Client>();

		// Filtering implementation
		if(search == null) {
			data.addAll(client);
		}else {
			for (Client c : client) {
				if (c.getClientName().toLowerCase().contains(search.toLowerCase())
						|| c.getPosition().toLowerCase().contains(search.toLowerCase())) 
				{
					data.add(c);
				}
			}
		}

		// store filtered data count to recordsFiltered that needs to be sent back with json array
		recordsFiltered = data.size();
		
		// Sorting implementation
		Collections.sort(data, new Comparator<Client>(){
		    @Override
		    public int compare(Client c1, Client c2) {    
		        switch(sortColumnIndex){
		        case 0:
		            return c1.getClientName().compareTo(c2.getClientName()) * sortDirection;
		        case 1:
		            return c1.getPosition().compareTo(c2.getPosition()) * sortDirection;
		        }
		        return 0;
		    }
		});
		
		// Pagination implementation
		if(data.size() < start + length) {
			data = data.subList(start, data.size());
		}else {
		    data = data.subList(start, start + length);
		}


		Gson gson = new Gson();
		JsonObject jsonObj = new JsonObject();
		
		JobRoleServices jrs = new JobRoleServices();
		
		// get all job roles from database
		List<JobRole> jrList = jrs.getAllJobRole();
		JsonObject fileJsonObj = new JsonObject();
		JsonObject fileJsonObj2 = new JsonObject();
		
		// get job role IDs, and format json for plugin purposes
		for(JobRole jr : jrList) {
			String id = Integer.toString(jr.getRoleId());
			JsonElement test2 = gson.toJsonTree(jr);
			fileJsonObj2.add(id, test2);
		}
		
		fileJsonObj.add("role", fileJsonObj2);
		
		
		// add properties that need to be sent back to datatables
		jsonObj.addProperty("draw", draw);
		jsonObj.addProperty("recordsTotal", recordsTotal);
		jsonObj.addProperty("recordsFiltered", recordsFiltered);
		
		// convert arraylist to json array
		JsonArray jsonArray = gson.toJsonTree(data).getAsJsonArray();
		jsonObj.add("data", jsonArray);
		jsonObj.add("files", fileJsonObj);

		out.println(jsonObj.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
