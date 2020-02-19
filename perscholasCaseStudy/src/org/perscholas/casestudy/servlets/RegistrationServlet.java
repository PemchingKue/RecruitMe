package org.perscholas.casestudy.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.perscholas.casestudy.entities.RecruiterServices;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
        String email = request.getParameter("email").toLowerCase();  
        String password = request.getParameter("password");
		String firstName = request.getParameter("firstname").toLowerCase();
		String lastName = request.getParameter("lastname").toLowerCase();
		Boolean checkEmailExist = null;
		Boolean compRegister = null;
		
		RecruiterServices rs = new RecruiterServices();
		
		checkEmailExist = rs.checkEmail(email);
		
		if(checkEmailExist == false) {
			
			compRegister = rs.addRecruiter(email, password, firstName, lastName);
			
			if (compRegister) {
				response.sendRedirect("/perscholasCaseStudy/jsp/login.jsp");
			}else {
				out.println("Failed to insert object to database");
			}
			
		}else {
			out.println("User email already exists!");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
