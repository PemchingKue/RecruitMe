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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		// Retrieve arguments sent from ajax request
		int cId = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("data[firstName]");
		String lastName = request.getParameter("data[lastName]");
		String email = request.getParameter("data[email]");
		String phone = request.getParameter("data[phone]");
		
		// retreive all data from database then store in List
		CandidateServices cs = new CandidateServices();
		List<Candidate> candidate = cs.fetchDataById(cId);
		
		for(Candidate c : candidate) {
			c.setFirstName(firstName);
			c.setLastName(lastName);
			c.setEmail(email);
			c.setPhone(phone);
		}
		List<Candidate> data = new ArrayList<Candidate>();
		data = cs.updateData(candidate);

		
		Gson gson = new Gson();
		JsonObject jsonObj = new JsonObject();
			
		// convert arraylist to json array
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
