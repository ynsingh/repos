package com.erp.nfes;


import java.sql.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;


import java.io.*;
import java.util.*;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 * Servlet implementation class StaffReport
 */
public class ReportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReportsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Connection con = null;
		try {			 
			 ConnectDB conObj=new ConnectDB(); 
			 con = conObj.getMysqlConnection();		      
			 File reportFile=null;
			 Map parameters = new HashMap();
			 //System.out.println(request.getParameter("action"));
			 
			  if (request.getParameter("action").equals("FacultyList")){			  
				  parameters.put("univ_name",request.getParameter("searchbyUniversity_report")+"%");
				  parameters.put("inst_name",request.getParameter("searchbyInstitution_report")+"%");
				  parameters.put("dept_name",request.getParameter("searchbyDepartment_report")+"%");
				  parameters.put("user_name",request.getParameter("searchby_report")+"%");
				  parameters.put("header_image",getServletConfig().getServletContext().getRealPath("")+"/images/Report_Header.jpg");			  
				  reportFile = new File(getServletConfig().getServletContext().getRealPath("")+"/Reports/staff_list.jasper");			  
			  }	else if (request.getParameter("action").equals("FacultyReport")){	
				  GetRecordValue  getUserDetails= new GetRecordValue();		
				  if (getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){
					  parameters.put("university",getUserDetails.getUniversity(request.getUserPrincipal().getName())+"%");  
				  }else{
				  parameters.put("university",request.getParameter("searchbyUniversity")+"%");
				  }
				  parameters.put("institution",request.getParameter("searchbyInstitution")+"%");
				  parameters.put("department",request.getParameter("searchbyDepartment")+"%");				  
				  parameters.put("header_image",getServletConfig().getServletContext().getRealPath("")+"/images/Report_Header.jpg");
				  parameters.put("SUBREPORT_DIR",getServletConfig().getServletContext().getRealPath("")+"/Reports/");
				  reportFile = new File(getServletConfig().getServletContext().getRealPath("")+"/Reports/staff_details.jasper");			  
			  }  
			  
			    byte[] bytes = 	JasperRunManager.runReportToPdf(reportFile.getPath(),parameters,con);
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(bytes, 0, bytes.length);
				con.close();
				ouputStream.flush();
				ouputStream.close();
		
		} 
		catch (SQLException e) {		
			e.printStackTrace();
		} catch (JRException e) {			
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }	
	}

}
