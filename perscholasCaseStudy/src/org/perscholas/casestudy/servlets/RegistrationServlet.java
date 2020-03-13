/*
* Filename: RegistrationServlet.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.servlets;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.perscholas.casestudy.entities.RecruiterServices;
import org.perscholas.casestudy.interfaces.JspPages;
import org.perscholas.casestudy.logger.RecruitMeLogger;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet implements JspPages{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// logger
		RecruitMeLogger rmLog = new RecruitMeLogger();
		
		// store parameters sent from form into variables
        String email = request.getParameter("email").toLowerCase();  
        String password = request.getParameter("password");
		String firstName = request.getParameter("firstname").toLowerCase();
		String lastName = request.getParameter("lastname").toLowerCase();
		Boolean checkEmailExist = null;
		Boolean completeRegister = null;
		
		RecruiterServices rs = new RecruiterServices();
		
		// invoke checkEmail method
		checkEmailExist = rs.checkEmail(email);
		
		// check if the email exist
		if(checkEmailExist == false) {
			
			completeRegister = rs.addRecruiter(email, password, firstName, lastName);
			
			if (completeRegister) {
				response.sendRedirect(request.getContextPath() + LOGIN);
			}else {
				rmLog.logger.log(Level.WARNING, "Failed to register user");
			}
			
		}else {
			rmLog.logger.log(Level.WARNING, "Email already exists");
			String emailExist = "Email already exists!";
			RequestDispatcher rd = request.getRequestDispatcher(REGISTER);
			request.setAttribute("error", emailExist);
			
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
