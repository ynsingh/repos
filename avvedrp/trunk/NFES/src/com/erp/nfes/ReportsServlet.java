package com.erp.nfes;


import java.sql.*;
import java.text.SimpleDateFormat;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.*;
import java.util.Date;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Servlet implementation class StaffReport
 */
public class ReportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReportsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Connection con = null;
		try {			 
			 ConnectDB conObj=new ConnectDB(); 
			 con = conObj.getMysqlConnection();		      
			 File reportFile=null;
			 File sourceFile=null;
			 Map parameters = new HashMap();
			 String ReportFileName=null;
			 byte[] bytes=null;
			  if (request.getParameter("action").equals("FacultyList")){	
				  ReportFileName="staff_list";				  
				  parameters.put("univ_name",request.getParameter("searchbyUniversity_report")+"%");
				  parameters.put("inst_name",request.getParameter("searchbyInstitution_report")+"%");
				  parameters.put("dept_name",request.getParameter("searchbyDepartment_report")+"%");
				  parameters.put("first_name",request.getParameter("searchby_report")+"%");
				  parameters.put("title",request.getParameter("searchbytitle_report")+"%");
				  parameters.put("last_name",request.getParameter("searchbylastname_report")+"%");
				  parameters.put("header_image",getServletConfig().getServletContext().getRealPath("")+"/images/loginheader_logo.PNG");			  
				  reportFile = new File(getServletConfig().getServletContext().getRealPath("")+"/Reports/staff_list.jasper");		  
			  }	else if (request.getParameter("action").equals("FacultyReport")){
				  ReportFileName="staff_details";				  
				  GetRecordValue  getUserDetails= new GetRecordValue();		
				  if (getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){
					  parameters.put("university",getUserDetails.getUniversity(request.getUserPrincipal().getName())+"%");  
				  }else{
				  parameters.put("university",request.getParameter("searchbyUniversity")+"%");
				  }
				  parameters.put("institution",request.getParameter("searchbyInstitution")+"%");
				  parameters.put("department",request.getParameter("searchbyDepartment")+"%");				  
				  parameters.put("header_image",getServletConfig().getServletContext().getRealPath("")+"/images/loginheader_logo.PNG");
				  parameters.put("SUBREPORT_DIR",getServletConfig().getServletContext().getRealPath("")+"/Reports/");
				  reportFile = new File(getServletConfig().getServletContext().getRealPath("")+"/Reports/staff_details.jasper");
			  } else if (request.getParameter("action").equals("ApprovalReport")){
				  ReportFileName="approval_report";
				  GetRecordValue  getUserDetails= new GetRecordValue();		
				  if (getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){
					  parameters.put("university",getUserDetails.getUniversity(request.getUserPrincipal().getName())+"%");  
				  }else{
				  parameters.put("university",request.getParameter("searchbyUniversity")+"%");
				  }
				  String fromdate= "";
				  String todate="";
				  	try {
				  	
				  		SimpleDateFormat sdfSource = new SimpleDateFormat("dd-MM-yy");
				  		Date datefrom = sdfSource.parse(request.getParameter("datefrom"));
				  		Date dateto = sdfSource.parse(request.getParameter("dateto"));
				  		SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");
				  		fromdate = sdfDestination.format(datefrom);
				  		todate = sdfDestination.format(dateto);
				  	} 
				  	catch(Exception pe)
				    { 
				      System.out.println("Parse Exception : " + pe);
				    }
				  parameters.put("fromdate",fromdate);
				  parameters.put("todate",todate);
				  parameters.put("institution",request.getParameter("searchbyInstitution")+"%");
				  parameters.put("department",request.getParameter("searchbyDepartment")+"%");
				  parameters.put("usertitle",request.getParameter("searchbyTitle")+"%");
				  parameters.put("facultyname",request.getParameter("searchbyFirstname")+"%");
				  parameters.put("lastname",request.getParameter("searchbyLastname")+"%");
				  parameters.put("category",request.getParameter("category")+"%");
				  parameters.put("header_image", getServletConfig().getServletContext().getRealPath("")+"/images/loginheader_logo.PNG");
				  parameters.put("SUBREPORT_DIR",getServletConfig().getServletContext().getRealPath("")+"/Reports/");
				  reportFile = new File(getServletConfig().getServletContext().getRealPath("")+"/Reports/approval_report.jasper");
			  } else if (request.getParameter("action").equals("InstitutionTransferReport")){	
				  ReportFileName="InstitutionTranferReport";
				  GetRecordValue  getUserDetails= new GetRecordValue();	
				  if (getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){
					  parameters.put("university",getUserDetails.getUniversity(request.getUserPrincipal().getName())+"%");  
				  }else{
				  parameters.put("university",request.getParameter("university")+"%");
				  }
				  parameters.put("institution",request.getParameter("institution")+"%");
				  parameters.put("department",request.getParameter("department")+"%");
				  parameters.put("firstname",request.getParameter("firstname")+"%");
				  parameters.put("nametitle",request.getParameter("nametitle")+"%");
				  parameters.put("lastname",request.getParameter("lastname")+"%");
				  parameters.put("header_image",getServletConfig().getServletContext().getRealPath("")+"/images/loginheader_logo.PNG");
				  reportFile = new File(getServletConfig().getServletContext().getRealPath("")+"/Reports/InstitutionTranferReport.jasper");		  
			  }
			  String ReportFormat=request.getParameter("ReportFormat");
			  if (ReportFormat.equals("pdf")){
				   bytes = 	JasperRunManager.runReportToPdf(reportFile.getPath(),parameters,con);			    		    
				  response.setContentType("application/pdf");
			  }else if (ReportFormat.equals("csv")){	
				  ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
				  
				  JasperFillManager.fillReportToFile(getServletConfig().getServletContext().getRealPath("")+"/Reports/" + ReportFileName +".jasper" , parameters,con); //"Jasper report filled with data and Jasper.jrprint file created in path"
				  sourceFile = new File(getServletConfig().getServletContext().getRealPath("")+"/Reports/"+ReportFileName +".jrprint");
				  JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);				
				  JRXlsExporter xlsExporter = new JRXlsExporter();							
				  xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				  xlsExporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, xlsReport);					
				  xlsExporter.exportReport();//Excel file created using .jrprint Jasper file"				  
				  bytes = xlsReport.toByteArray();								
				  response.setContentType("application/vnd.ms-excel");
				  response.setHeader("Content-disposition", "attachment;fileName="+ ReportFileName +".csv" );
			  }
		  	ServletOutputStream ouputStream = response.getOutputStream();
		  	response.setContentLength(bytes.length);
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} 
		catch (JRException e) {			
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
