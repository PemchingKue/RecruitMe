package org.perscholas.casestudy.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.perscholas.casestudy.entities.Recruiter;
import org.perscholas.casestudy.entities.RecruiterServices;

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

		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Boolean successful = null;
		
        RecruiterServices rs = new RecruiterServices();
        
        successful = rs.updateData(userId, firstName, lastName, email);
        
        if(successful) {
        	System.out.println("success!");
        	session.setAttribute("firstName", firstName);
        	session.setAttribute("lastName", lastName);
        	session.setAttribute("email", email);
        	
        	response.sendRedirect(request.getContextPath() + "/jsp/settings.jsp");
        }else {
        	System.out.println("failed!");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
