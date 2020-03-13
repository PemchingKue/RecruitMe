/*
* Filename: DownloadJobRoleServlet.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.perscholas.casestudy.entities.JobRole;
import org.perscholas.casestudy.entities.JobRoleServices;

/**
 * Servlet implementation class DownloadJobRoleServlet
 */
@WebServlet("/DownloadJobRoleServlet")
public class DownloadJobRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadJobRoleServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// store parameter sent from plugin into variable
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		JobRoleServices jrs = new JobRoleServices();
		
		// retrieve job role description by ID
		List<JobRole> getRole = jrs.getJobRoleById(id);
		
		// setting the content attributes for the response object
		String mimeType = getRole.get(0).getContentType();
		
		if(mimeType == null) {
	        mimeType = "application/octet-stream";
	        response.setContentType(mimeType);
		}else {
			response.setContentType(mimeType);
		}

        // setting the headers for the response object
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", getRole.get(0).getFileName());
        response.setHeader(headerKey, headerValue);

        // output the byte[] to the response object
        OutputStream outStream = response.getOutputStream();
        
        byte[] buffer = getRole.get(0).getRoleData();
        
        outStream.write(buffer);
        outStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
