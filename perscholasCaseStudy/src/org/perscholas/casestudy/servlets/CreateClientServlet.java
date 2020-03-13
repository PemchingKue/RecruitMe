/*
* Filename: CreateClientServlet.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.perscholas.casestudy.entities.Candidate;
import org.perscholas.casestudy.entities.CandidateServices;
import org.perscholas.casestudy.entities.Client;
import org.perscholas.casestudy.entities.ClientServices;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class CreateClientServlet
 */
@WebServlet("/CreateClientServlet")
public class CreateClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClientServlet() {
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
		
		// store all parameters sent from the plugin into variables
		String clientName = request.getParameter("data[clientName]").toLowerCase();
		String position = request.getParameter("data[position]").toLowerCase();
		Integer roleId = null;
		
		// check if roleId empty or not
		if(request.getParameter("data[role][roleId]").compareTo("") == 0) {
			roleId = null;
		}else {
			roleId = Integer.parseInt(request.getParameter("data[role][roleId]"));
		}
		
		ClientServices cs = new ClientServices();
		
		// create new array list and invoke createData from service class, the add the contents of the return array list into a new array list
		List<Client> data = new ArrayList<Client>();
		data.addAll(cs.createData(clientName, position, sessionUserId, roleId));

		Gson gson = new Gson();
		JsonObject jsonObj = new JsonObject();
			
		// convert array list to json array
		JsonArray jsonArray = gson.toJsonTree(data).getAsJsonArray();
		jsonObj.add("data", jsonArray);

		out.println(jsonObj.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
