package org.perscholas.casestudy.entities;

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

/**
 * Servlet implementation class UpdateEventServlet
 */
@WebServlet("/UpdateEventServlet")
public class UpdateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		// Retrieve arguments sent from ajax request
		int eId = Integer.parseInt(request.getParameter("id"));
//		System.out.println("THIS IS ID: " + eId);
		String title = request.getParameter("title");
		String start = request.getParameter("start").toString();
		String end = request.getParameter("end").toString();
		
		// retreive all data from database then store in List
		CalendarServices cs = new CalendarServices();
		
		List<Calendar> data = new ArrayList<Calendar>();
		data = cs.updateData(title, start, end, eId);
		
		if(!data.isEmpty()) {
			System.out.println("success!");
		}else {
			System.out.println("failed!");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
