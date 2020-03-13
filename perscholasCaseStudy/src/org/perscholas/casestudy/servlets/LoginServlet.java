/*
* Filename: LoginServlet.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.perscholas.casestudy.entities.Recruiter;
import org.perscholas.casestudy.entities.RecruiterServices;
import org.perscholas.casestudy.interfaces.JspPages;
import org.perscholas.casestudy.logger.RecruitMeLogger;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet implements JspPages{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// logger
		RecruitMeLogger rmLog = new RecruitMeLogger();
		
		PrintWriter out = response.getWriter();
		
		// store parameters sent from form into variables
        String email = request.getParameter("email").toLowerCase();  
        String password = request.getParameter("password");
        
        RecruiterServices rs = new RecruiterServices();
        
        // get recruiter by email
        List<Recruiter> recruiter = rs.fetchRecruiterByEmail(email);
        
        // check if the recruiter exists
        if(!recruiter.isEmpty()) { 
        	
			if ( email.equals(recruiter.get(0).getEmail()) && password.equals(recruiter.get(0).getPassword())) {
				HttpSession session = request.getSession();
				session.setAttribute("id", recruiter.get(0).getRecruiterId());
				session.setAttribute("email", email);
				session.setAttribute("firstName", recruiter.get(0).getFirstName());
				session.setAttribute("lastName", recruiter.get(0).getLastName());
				
				response.sendRedirect(request.getContextPath() + DASHBOARD);
				
			} else {
				rmLog.logger.log(Level.WARNING, "Incorrect username or password");
				String badUserOrPass = "Incorrect username or password!";
				RequestDispatcher rd = request.getRequestDispatcher(LOGIN);
				request.setAttribute("error", badUserOrPass);
				
				rd.forward(request, response);
			}
        	
        } else {
        	out.println("User does not exist!");
			rmLog.logger.log(Level.WARNING, "User does not exist");
			String badUser = "User does not exist!";
			RequestDispatcher rd = request.getRequestDispatcher(LOGIN);
			request.setAttribute("error", badUser);
			
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
