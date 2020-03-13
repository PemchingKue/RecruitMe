/*
* Filename: CreateEventServlet.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.servlets;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.perscholas.casestudy.entities.CalendarServices;
import org.perscholas.casestudy.logger.RecruitMeLogger;

/**
 * Servlet implementation class CreateEventServlet
 */
@WebServlet("/CreateEventServlet")
public class CreateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEventServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// logger
		RecruitMeLogger rmLog = new RecruitMeLogger();

		// Get session, and user ID
		HttpSession session = request.getSession();
		int sessionUserId = (int) session.getAttribute("id");
			
		// get parameters sent from plugin and store in variables
		String title = request.getParameter("title");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		Boolean successful = null;
		
		CalendarServices cs = new CalendarServices();
		
		// invoke createData method from service class
		successful = cs.createData(title, start, end, sessionUserId);

		if(successful) {
			rmLog.logger.log(Level.INFO, "Success on creating event");
		}else {
			rmLog.logger.log(Level.WARNING, "Failed on creating event");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
