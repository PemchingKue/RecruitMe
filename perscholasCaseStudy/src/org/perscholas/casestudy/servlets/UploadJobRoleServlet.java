/*
* Filename: UploadJobRoleServlet.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialException;

import org.perscholas.casestudy.entities.JobRole;
import org.perscholas.casestudy.entities.JobRoleServices;
import org.perscholas.casestudy.logger.RecruitMeLogger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class UploadJobRoleServlet
 */
@WebServlet("/UploadJobRoleServlet")
@MultipartConfig
public class UploadJobRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadJobRoleServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// logger
		RecruitMeLogger rmLog = new RecruitMeLogger();
		
		PrintWriter out = response.getWriter();
		
		// define variables and array lists
		List<JobRole> jrList = new ArrayList<JobRole>();
		InputStream inputStream = null;
		String fileName = null;
		long fileSize = 0;
		String contentType = null;
		
		// obtains the upload file part in this multipart request
        Collection<Part> fileParts = request.getParts();
        if (!fileParts.isEmpty()) {
    		for (Part part : request.getParts()) {
    			fileName = part.getSubmittedFileName();
    			
    			if(part.getName().compareTo("upload") == 0) {
    				// log some information for debugging
    				rmLog.logger.log(Level.INFO, "File: " + part.getName());
    				rmLog.logger.log(Level.INFO, "File: " + part.getSize());
    				rmLog.logger.log(Level.INFO, "File: " + part.getContentType());
    	            
    	            // get file size and content type
    	            fileSize = part.getSize();
    	            contentType = part.getContentType();
    			}
                
    			// obtains input stream of the upload file
    			inputStream = part.getInputStream();
    		}
        }
        
        JobRoleServices jrs = new JobRoleServices();
        
        // upload data to data base
        try {
			jrList = jrs.uploadData(inputStream, fileName, fileSize, contentType);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        // define necessary json objects
		Gson gson = new Gson();
		JsonObject jsonObj = new JsonObject();
		JsonObject uploadIdObj = new JsonObject();
		JsonObject uploadFileObj = new JsonObject();
		JsonObject fileJsonObj2 = new JsonObject();
		
		// create the file ID in json object
		uploadIdObj.addProperty("id", jrList.get(0).getRoleId());
	
		for(JobRole r : jrList) {
			String id = Integer.toString(r.getRoleId());
			JsonElement test2 = gson.toJsonTree(r);
			fileJsonObj2.add(id, test2);
		}
		
		uploadFileObj.add("role", fileJsonObj2);
		
		// Create required json format for datatables plugin 
		jsonObj.add("files", uploadFileObj);
		jsonObj.add("upload", uploadIdObj);
		
		out.println(jsonObj.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
