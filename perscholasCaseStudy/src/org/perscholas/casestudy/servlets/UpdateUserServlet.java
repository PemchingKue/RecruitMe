/*
* Filename: UpdateUserServlet.java
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

import org.perscholas.casestudy.customexceptions.UpdateFailedException;
import org.perscholas.casestudy.entities.RecruiterServices;
import org.perscholas.casestudy.logger.RecruitMeLogger;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// logger
		RecruitMeLogger rmLog = new RecruitMeLogger();
		
		// get session ID and store into variable
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		
		// store parameters sent from form into variables
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Boolean successful = null;
		
        RecruiterServices rs = new RecruiterServices();
        
        // update data in data base
        try {
			successful = rs.updateData(userId, firstName, lastName, email);
		} catch (UpdateFailedException e) {
			e.printStackTrace();
		}
        
        // if successful, update session attributes
        if(successful) {
        	session.setAttribute("firstName", firstName);
        	session.setAttribute("lastName", lastName);
        	session.setAttribute("email", email);
        	
        	response.sendRedirect(request.getContextPath() + "/jsp/settings.jsp");
        }else {
        	rmLog.logger.log(Level.WARNING, "Failed to update user information");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
