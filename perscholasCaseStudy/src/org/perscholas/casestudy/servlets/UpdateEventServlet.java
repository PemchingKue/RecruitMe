/*
* Filename: UpdateEventServlet.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.perscholas.casestudy.customexceptions.UpdateFailedException;
import org.perscholas.casestudy.entities.Calendar;
import org.perscholas.casestudy.entities.CalendarServices;
import org.perscholas.casestudy.logger.RecruitMeLogger;


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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// logger
		RecruitMeLogger rmLog = new RecruitMeLogger();
		
		// Retrieve arguments sent from ajax request
		int eId = Integer.parseInt(request.getParameter("id"));
		System.out.println("THIS IS ID: " + eId);
		String title = request.getParameter("title");
		String start = request.getParameter("start").toString();
		String end = request.getParameter("end").toString();
		
		// retrieve all data from database then store in List
		CalendarServices cs = new CalendarServices();
		
		// update data
		List<Calendar> data = new ArrayList<Calendar>();
		try {
			data = cs.updateData(title, start, end, eId);
		} catch (UpdateFailedException e) {
			e.printStackTrace();
		}
		
		// log event
		if(!data.isEmpty()) {
			rmLog.logger.log(Level.INFO, "Success on updating event");
		}else {
			rmLog.logger.log(Level.WARNING, "Failed on updating event");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
