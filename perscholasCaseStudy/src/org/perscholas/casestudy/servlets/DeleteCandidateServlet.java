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

import org.perscholas.casestudy.entities.Candidate;
import org.perscholas.casestudy.entities.CandidateServices;

/**
 * Servlet implementation class DeleteCandidateServlet
 */
@WebServlet("/DeleteCandidateServlet")
public class DeleteCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCandidateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Get session, and user ID
		HttpSession session = request.getSession();
		int sessionUserId = (int) session.getAttribute("id");
		
		PrintWriter out = response.getWriter();
		
		int cId = Integer.parseInt(request.getParameter("id[]"));
		
		CandidateServices cs = new CandidateServices();
		List<Candidate> data = new ArrayList<Candidate>();
		data.addAll(cs.deleteData(cId, sessionUserId));

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