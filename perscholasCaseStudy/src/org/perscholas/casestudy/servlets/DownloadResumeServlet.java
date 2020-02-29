package org.perscholas.casestudy.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.perscholas.casestudy.entities.Resume;
import org.perscholas.casestudy.entities.ResumeServices;

/**
 * Servlet implementation class DownloadResumeServlet
 */
@WebServlet("/DownloadResumeServlet")
public class DownloadResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadResumeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));
		//System.out.println("THIS IS THE ID OF THE RESUME: " + id);
		
		ResumeServices rs = new ResumeServices();
		List<Resume> getResume = rs.getResumeById(id);
		
        // Setting The Content Attributes For The Response Object
		String mimeType = getResume.get(0).getContentType();
		
		if(mimeType == null) {
	        mimeType = "application/octet-stream";
	        response.setContentType(mimeType);
		}else {
			response.setContentType(mimeType);
		}

        //Setting The Headers For The Response Object
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", getResume.get(0).getFileName());
        response.setHeader(headerKey, headerValue);
		
        //Output the byte[] to the response object
        OutputStream outStream = response.getOutputStream();
        
        byte[] buffer = getResume.get(0).getResumeData();
        
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
