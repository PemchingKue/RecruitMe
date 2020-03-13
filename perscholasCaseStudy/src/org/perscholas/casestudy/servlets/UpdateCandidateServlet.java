/*
* Filename: UpdateCandidateServlet.java
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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.perscholas.casestudy.customexceptions.DeleteFailedException;
import org.perscholas.casestudy.customexceptions.UpdateFailedException;
import org.perscholas.casestudy.entities.Candidate;
import org.perscholas.casestudy.entities.CandidateServices;

/**
 * Servlet implementation class UpdateCandidateServlet
 */
@WebServlet("/UpdateCandidateServlet")
public class UpdateCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCandidateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// get session and store session ID into variable
		HttpSession session = request.getSession();
		int sessionUserId = (int) session.getAttribute("id");
		
		PrintWriter out = response.getWriter();
		
		// Retrieve arguments sent from ajax request
		int cId = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("data[firstName]").toLowerCase();
		String lastName = request.getParameter("data[lastName]").toLowerCase();
		String company = request.getParameter("data[client][clientName]").toLowerCase();
		String position = request.getParameter("data[client][position]").toLowerCase();
		String email = request.getParameter("data[email]");
		String phone = request.getParameter("data[phone]");
		Integer resumeId = null;
		
		// check if resumeId empty or not
		if(request.getParameter("data[resume][resumeId]").compareTo("") == 0) {
			resumeId = null;
		}else {
			resumeId = Integer.parseInt(request.getParameter("data[resume][resumeId]"));
		}
		
		// retrieve all data from database then store in List
		CandidateServices cs = new CandidateServices();

		List<Candidate> data = new ArrayList<Candidate>();
		try {
			data = cs.updateData(cId, firstName, lastName, company, position, email, phone, sessionUserId, resumeId);
		} catch (UpdateFailedException | DeleteFailedException e) {
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
