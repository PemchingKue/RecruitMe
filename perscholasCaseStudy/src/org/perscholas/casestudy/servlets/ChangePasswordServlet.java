/*
* Filename: ChangePasswordServlet.java
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
		
		// logger
		RecruitMeLogger rmLog = new RecruitMeLogger();
		
		// get/create session
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		
		// retrieve parameters passed from the form
		String oldPassword = request.getParameter("oldpassword");
		String newPassword = request.getParameter("newpassword");
		String cNewPassword = request.getParameter("cnewpassword");
		
		Boolean successful = null;
		
		RecruiterServices rs = new RecruiterServices();
		
		// check if new password variable is equivalent to confirm password variable
		if(newPassword.equals(cNewPassword)) {
			try {
				// update password
				successful = rs.updatePassword(userId, oldPassword, cNewPassword);
			} catch (UpdateFailedException e) {
				e.printStackTrace();
			}
			if(successful) {
				rmLog.logger.log(Level.INFO, "Success on changing password");
				response.sendRedirect(request.getContextPath() + "/jsp/settings.jsp");
			}else {
				rmLog.logger.log(Level.INFO, "Failed on changing password");
			}
		}else {
			rmLog.logger.log(Level.WARNING, "failed, new password is not equivalent to confirmed password");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
