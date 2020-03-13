/*
* Filename: DeleteClientServlet.java
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

import org.perscholas.casestudy.customexceptions.DeleteFailedException;
import org.perscholas.casestudy.entities.Candidate;
import org.perscholas.casestudy.entities.CandidateServices;
import org.perscholas.casestudy.entities.Client;
import org.perscholas.casestudy.entities.ClientServices;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class DeleteClientServlet
 */
@WebServlet("/DeleteClientServlet")
public class DeleteClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteClientServlet() {
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
		
		// store parameter sent from plugin into variable
		int cId = Integer.parseInt(request.getParameter("id[]"));
		
		ClientServices cs = new ClientServices();
		
		// create new client array list
		List<Client> data = new ArrayList<Client>();
		
		try {
			// add array list returned from method into new array list
			data.addAll(cs.deleteData(cId, sessionUserId));
		} catch (DeleteFailedException e) {
			e.printStackTrace();
		}

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
