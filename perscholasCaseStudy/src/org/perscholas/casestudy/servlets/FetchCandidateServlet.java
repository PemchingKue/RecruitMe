package org.perscholas.casestudy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
 * Servlet implementation class FetchCandidateServlet
 */
@WebServlet("/FetchCandidateServlet")
public class FetchCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchCandidateServlet() {
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

		// Retrieve arguments sent from ajax request
		int draw = Integer.parseInt(request.getParameter("draw"));
		String search = request.getParameter("search[value]");
		int start = Integer.parseInt(request.getParameter("start"));
		int length  = Integer.parseInt(request.getParameter("length"));
		final int sortColumnIndex = Integer.parseInt(request.getParameter("order[0][column]"));
		final int sortDirection = request.getParameter("order[0][dir]").equals("asc") ? -1 : 1;
		
		
		//
		System.out.println("THIS IS THE SESSION ID " + sessionUserId);

		// define response attributes that need to be sent back with json array
		int recordsTotal = 0;
		int recordsFiltered = 0;

		// retreive all data from database then store in List
		CandidateServices cs = new CandidateServices();
		List<Candidate> candidate = cs.fetchData(sessionUserId);
		recordsTotal = candidate.size();

//		// Create new arraylist to store data after filtering
		List<Candidate> data = new ArrayList<Candidate>();

		// Filtering
		if(search == null) {
			data.addAll(candidate);
		}else {
			for (Candidate c : candidate) {
				if (c.getFirstName().toLowerCase().contains(search.toLowerCase())
						|| c.getLastName().toLowerCase().contains(search.toLowerCase())
						|| c.getEmail().toLowerCase().contains(search.toLowerCase())
						|| c.getPhone().toLowerCase().contains(search.toLowerCase())) 
				{
					data.add(c);
				}
			}
		}

		// store filtered data count to recordsFiltered that needs to be sent back with json array
		recordsFiltered = data.size();
		
		//Sorting
		Collections.sort(data, new Comparator<Candidate>(){
		    @Override
		    public int compare(Candidate c1, Candidate c2) {    
		        switch(sortColumnIndex){
		        case 0:
		            return c1.getFirstName().compareTo(c2.getFirstName()) * sortDirection;
		        case 1:
		            return c1.getLastName().compareTo(c2.getLastName()) * sortDirection;
		        case 2:
		            return c1.getEmail().compareTo(c2.getEmail()) * sortDirection;
		        case 3:
		            return c1.getPhone().compareTo(c2.getPhone()) * sortDirection;
		        }
		        return 0;
		    }
		});
		
		// Pagination
		if(data.size() < start + length) {
			data = data.subList(start, data.size());
		}else {
		    data = data.subList(start, start + length);
		}


		Gson gson = new Gson();
		JsonObject jsonObj = new JsonObject();
		
		// add properties that need to be sent back to datatables
		jsonObj.addProperty("draw", draw);
		jsonObj.addProperty("recordsTotal", recordsTotal);
		jsonObj.addProperty("recordsFiltered", recordsFiltered);
		
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
