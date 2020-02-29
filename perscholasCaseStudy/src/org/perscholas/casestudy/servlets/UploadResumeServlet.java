package org.perscholas.casestudy.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.perscholas.casestudy.entities.Resume;
import org.perscholas.casestudy.entities.ResumeServices;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class UploadResumeServlet
 */
@WebServlet("/UploadResumeServlet")
@MultipartConfig
public class UploadResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadResumeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		List<Resume> rList = new ArrayList<Resume>();
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
    				// prints out some information for debugging
    	            System.out.println(part.getName());
    	            System.out.println(part.getSize());
    	            System.out.println(part.getContentType());
    	            
    	            //get file size and content type
    	            fileSize = part.getSize();
    	            contentType = part.getContentType();
    			}
                
    			// obtains input stream of the upload file
    			inputStream = part.getInputStream();
    		}
        }
        
        ResumeServices rs = new ResumeServices();
        
        try {
			rList = rs.uploadData(inputStream, fileName, fileSize, contentType);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		Gson gson = new Gson();
		JsonObject jsonObj = new JsonObject();
		JsonObject uploadIdObj = new JsonObject();
		JsonObject uploadFileObj = new JsonObject();
		JsonObject fileJsonObj2 = new JsonObject();
		
		//create the file ID in json object
		uploadIdObj.addProperty("id", rList.get(0).getResumeId());
		
		// convert arraylist to json array, then store in json object
		//JsonArray jsonFileArray = gson.toJsonTree(rList).getAsJsonArray();
	
		for(Resume r : rList) {
			String id = Integer.toString(r.getResumeId());
			JsonElement test2 = gson.toJsonTree(r);
			fileJsonObj2.add(id, test2);
		}
		
		uploadFileObj.add("resume", fileJsonObj2);
		
		//Create required json format for datatables plugin 
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
