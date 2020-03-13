/*
* Filename: DeleteEventServlet.java
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

import org.perscholas.casestudy.customexceptions.DeleteFailedException;
import org.perscholas.casestudy.entities.CalendarServices;
import org.perscholas.casestudy.logger.RecruitMeLogger;

/**
 * Servlet implementation class DeleteEventServlet
 */
@WebServlet("/DeleteEventServlet")
public class DeleteEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEventServlet() {
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
		
		// store parameter sent from plugin into variable
		int eId = Integer.parseInt(request.getParameter("id"));
		Boolean successful = null;
		
		CalendarServices cs = new CalendarServices();
		
		try {
			successful = cs.deleteData(eId, sessionUserId);
		} catch (DeleteFailedException e) {
			e.printStackTrace();
		}
		
		if(successful == true) {
			rmLog.logger.log(Level.INFO, "Success on deleting event");
		}else {
			rmLog.logger.log(Level.WARNING, "Failed on deleting event");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
