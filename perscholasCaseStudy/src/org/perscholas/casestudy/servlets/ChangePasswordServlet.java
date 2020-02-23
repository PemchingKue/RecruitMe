package org.perscholas.casestudy.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.perscholas.casestudy.entities.RecruiterServices;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		
		String oldPassword = request.getParameter("oldpassword");
		String newPassword = request.getParameter("newpassword");
		String cNewPassword = request.getParameter("cnewpassword");
		
		Boolean successful = null;
		
		RecruiterServices rs = new RecruiterServices();
		
		if(newPassword.equals(cNewPassword)) {
			successful = rs.updatePassword(userId, oldPassword, cNewPassword);
			if(successful) {
				System.out.println("success!");
				response.sendRedirect(request.getContextPath() + "/jsp/settings.jsp");
			}else {
				System.out.println("failed");
			}
		}else {
			System.out.println("failed");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
