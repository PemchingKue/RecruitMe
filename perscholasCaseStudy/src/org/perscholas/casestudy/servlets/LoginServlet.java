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

import org.perscholas.casestudy.entities.Recruiter;
import org.perscholas.casestudy.entities.RecruiterServices;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
		PrintWriter out = response.getWriter();
		
        String email = request.getParameter("email").toLowerCase();  
        String password = request.getParameter("password");
        
        RecruiterServices rs = new RecruiterServices();
        
        List<Recruiter> recruiter = rs.fetchRecruiterByEmail(email);
        		
        if(!recruiter.isEmpty()) { 
        	
			if ( email.equals(recruiter.get(0).getEmail()) && password.equals(recruiter.get(0).getPassword())) {
				HttpSession session = request.getSession();
				session.setAttribute("id", recruiter.get(0).getRecruiterId());
				session.setAttribute("email", email);
				session.setAttribute("firstName", recruiter.get(0).getFirstName());
				session.setAttribute("lastName", recruiter.get(0).getLastName());
				
				response.sendRedirect(request.getContextPath() + "/jsp/dashboard.jsp");
				
//				RequestDispatcher rd = request.getRequestDispatcher("/jsp/dashboard.jsp");
//				rd.forward(request, response);
				
			} else {
				out.print("Sorry, username or password error!");
//				RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
//				rd.forward(request, response);
			}
        	
        } else {
        	out.println("User does not exist!");
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
