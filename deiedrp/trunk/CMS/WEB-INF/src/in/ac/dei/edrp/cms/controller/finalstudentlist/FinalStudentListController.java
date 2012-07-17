/*
 * @(#) FinalStudentListController.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.cms.controller.finalstudentlist;

import in.ac.dei.edrp.cms.dao.enrollment.EnrollmentService;
import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.daoimpl.universityreservation.UniversityReservationServiceImpl;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * this is Server side controller class for generate Final Student List
 * @version 1.0 
 * @author Devendra
 * @Date 7 April 2012
 */
public class FinalStudentListController extends MultiActionController{
	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(UniversityReservationServiceImpl.class);

	/** creating object of enrollmentService interface */
	private EnrollmentService enrollmentService;
	
	/** defining setter method for object of interface */
	 public void setFinalStudentService(EnrollmentService enrollmentSErvice) {
	        this.enrollmentService = enrollmentSErvice;	        	  
	 }
	 /** creating object of ReportDao interface */
	 private ReportDao reportDao;
	 /** defining setter method for object of interface ReportDao */
		public void setReportDao(ReportDao reportDao) {
			this.reportDao = reportDao;
	}
	
	 /**
	 * Method to set get data to generate final student report
	 * @param request
	 * @param response
	 * @return ModelAndView containing info(String message)
	 */
	public ModelAndView generateFinalStudentReport(HttpServletRequest request,
		HttpServletResponse response) throws Exception {		
		HttpSession session = request.getSession(true);
		String universityId=(String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		EnrollmentInfo input=new EnrollmentInfo();	
		String reportCode=request.getParameter("reportCode");
		input.setSessionStartDate(request.getParameter("fromSession"));
		input.setSessionEndDate(request.getParameter("toSession"));
		input.setEntity(request.getParameter("entityId"));
		input.setProgram(request.getParameter("programId"));
		input.setBranch(request.getParameter("branchId"));
		input.setSpecialization(request.getParameter("specializationId"));
		input.setSemesterCode(request.getParameter("semesterCode"));
		input.setSemesterStartDate(request.getParameter("semesterStartDate"));
		input.setSemesterEndDate(request.getParameter("semesterEndDate"));
		
		String message=generateFinalStudentReport(request,session,input,reportCode);
		return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
	}
	/**
	 * Method to generate Final Student List
	 * @param HttpServletRequest request
	 * @param HttpSession session
	 * @param Object of EnrollmentInfo input
	 * @param String reportCode
	 * @return String message 
	*/
	
	public String generateFinalStudentReport(HttpServletRequest request,HttpSession session,EnrollmentInfo input,String reportCode){
		String logResult="";
		String reportResult="";
		int reportGenerated=0;
		try {			
			//**********************Making directory for storing the file**************************	
			ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
					request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
					request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
					
			String reportPath = ReportPath.getPath(reportPathBean);							
			String filePath = this.getServletContext().getRealPath("/");
			filePath=filePath+reportPath;
			File file = new File(filePath);
			file.mkdirs();
		//*************************************************************************************	
		String sep = System.getProperty("file.separator");	
		Document document = new Document(PageSize.A4.rotate());
		RtfWriter2.getInstance(document, new FileOutputStream(filePath + sep + request.getParameter("reportDescription")+".doc"));
		
		String reportSession=input.getSessionStartDate().substring(0, 4)+"-"+input.getSessionEndDate().substring(2,4);	
		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10,Font.BOLD, new Color(0, 0, 0));
		Font hindiFont = new Font(BaseFont.createFont(this.getServletContext()
				.getRealPath("/") + "fonts"+sep+"ARIALUNI.TTF",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);		
		Font hindiFont1 = new Font(BaseFont.createFont(this.getServletContext()
				.getRealPath("/") + "fonts"+sep+"K010.TTF",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED),15);	
		Font hindiFont2 = new Font(BaseFont.createFont(this.getServletContext()
				.getRealPath("/") + "fonts"+sep+"K010.TTF",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 15,Font.BOLD);
		String headerData="\t    FINAL DEI STUDENT LIST: "+reportSession+"\t\t\t\t"+"CLASS: "+request.getParameter("programName").toUpperCase()+" "+request.getParameter("semesterName")+"\n";
		
		Table headerTable = new Table(5);
		int headerWidth[]={3,8,20,18,8};
		headerTable.setWidth(100f);
		headerTable.setAlignment(Element.ALIGN_LEFT);	
		headerTable.setWidths(headerWidth);
		Cell c1 = new Cell(new Phrase("Sl.No.", headerFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setWidth(.5f);
		headerTable.addCell(c1);
		c1=new Cell(new Phrase("Roll Number", headerFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setWidth(1f);		
		headerTable.addCell(c1);
		c1=new Cell(new Phrase("Name in English", headerFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setWidth(5f);		
		headerTable.addCell(c1);
		c1=new Cell(new Phrase("Name in Hindi", headerFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setWidth(5f);		
		headerTable.addCell(c1);
		c1=new Cell(new Phrase("Enrolment Number", headerFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setWidth(1f);		
		headerTable.addCell(c1);
		Phrase headerTablePhrase=new Phrase();
		headerTablePhrase.add(headerTable);
		Phrase headerPhrase=new Phrase(headerData,headerFont);

		Phrase hindiPhrase=new Phrase("vko';d%",hindiFont2);
		Phrase hindiPhrase1=new Phrase(" fo|kfFkZ;ksa dh vadrkfydk,W ",hindiFont1);
		Phrase hindiPhrase2=new Phrase("(MARKSHEET) ",headerFont);
		Phrase hindiPhrase3=new Phrase("o mikf/k izek.k&i= ",hindiFont1);
		Phrase hindiPhrase4=new Phrase("(Degree Certification), ",headerFont);
		Phrase hindiPhrase5=new Phrase("fuEuor fy[ksa ukeksa ds vuqlkj gh rS;kj fd;s tk;saxs",hindiFont1);
		Phrase hindiPhrase6=new Phrase("] vr% ;fn fuEu rkfydk esa fn;s x;s ukeksa dh o.kZ&jpuk ",hindiFont1);
		Phrase hindiPhrase7=new Phrase("(Spelling) ",headerFont);
		Phrase hindiPhrase8=new Phrase(",oa fdlh vU; fooj.k esa dksbZ =qfV gks rks bldh lwpuk ijh{kk foHkkx dks rkfydk izn”kZu ds ,d lIrkg ds Hkhrj vo”; nsa A \n",hindiFont1);
		Phrase mainHindiPhrase=new Phrase();
		mainHindiPhrase.add(hindiPhrase);
		mainHindiPhrase.add(hindiPhrase1);
		mainHindiPhrase.add(hindiPhrase2);
		mainHindiPhrase.add(hindiPhrase3);
		mainHindiPhrase.add(hindiPhrase4);
		mainHindiPhrase.add(hindiPhrase5);
		mainHindiPhrase.add(hindiPhrase6);
		mainHindiPhrase.add(hindiPhrase7);
		mainHindiPhrase.add(hindiPhrase8);
		
		Phrase mainHeaderphrase=new Phrase();
		mainHeaderphrase.add(headerPhrase);		
		mainHeaderphrase.add(mainHindiPhrase);
		mainHeaderphrase.add(headerTablePhrase);
		HeaderFooter header=new HeaderFooter(mainHeaderphrase, true);	
		document.setHeader(header);
		
		String toDay = new SimpleDateFormat("dd MMM yyyy").format(new Date());
		Table footerTable = new Table(4);
		int footerwidth[]={8,8,8,12};
		footerTable.setWidths(footerwidth);
		footerTable.setWidth(100f);
		footerTable.setAlignment(Element.ALIGN_LEFT);		
		Cell c2 = new Cell(new Phrase("Run Date: "+toDay, headerFont));		
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		c2.setBorder(Rectangle.NO_BORDER);
		c2.setWidth(2f);
		footerTable.addCell(c2);
		c2=new Cell(new Phrase("Prepared by", headerFont));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		c2.setBorder(Rectangle.NO_BORDER);
		c2.setWidth(4f);		
		footerTable.addCell(c2);
		c2=new Cell(new Phrase("Checked by", headerFont));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		c2.setBorder(Rectangle.NO_BORDER);
		c2.setWidth(10f);		
		footerTable.addCell(c2);
		c2=new Cell(new Phrase("Assistant Registrar(Acad.)", headerFont));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		c2.setBorder(Rectangle.NO_BORDER);
		c2.setWidth(8f);		
		footerTable.addCell(c2);	
		Phrase footerTablePhrase=new Phrase();
		footerTablePhrase.add(footerTable);
		HeaderFooter footer=new HeaderFooter(footerTablePhrase, false);
		document.setFooter(footer);
		
		document.open();
		List<EnrollmentInfo> dataList=enrollmentService.getStudentFinalList(input);
		Table dataTable = new Table(5);
		dataTable.setWidth(100f);
		dataTable.setWidths(headerWidth);
		dataTable.setAlignment(Element.ALIGN_LEFT);	
		EnrollmentInfo enrollmentInfo=new EnrollmentInfo();
		if(dataList.size()>0){
			for(int i=0;i<dataList.size();i++){
				enrollmentInfo.setStudentName(dataList.get(i).getStudentName());
				enrollmentInfo.setStudentFirstName(dataList.get(i).getStudentFirstName());
				enrollmentInfo.setStudentMiddleName(dataList.get(i).getStudentMiddleName());
				enrollmentInfo.setStudentLastName(dataList.get(i).getStudentLastName());
				enrollmentInfo.setEntity(request.getParameter("entityId"));
				enrollmentInfo.setUniversityId((String) session.getAttribute("universityId"));
				List<EnrollmentInfo>countList=enrollmentService.getStudentChackedList(enrollmentInfo);				
				if(countList.get(0).getCount()>1){	
					Font font=new Font(BaseFont.createFont(this.getServletContext()
							.getRealPath("/") + "fonts"+sep+"K010.TTF",
							BaseFont.IDENTITY_H, BaseFont.EMBEDDED),12,Font.BOLD);
					addCell(headerFont, dataTable, String.valueOf(i+1)+".", "");
					addCell(headerFont, dataTable,dataList.get(i).getRollNumber(), "");
					if(dataList.get(i).getGender().toString().equals("F")){
						addCell(headerFont, dataTable,dataList.get(i).getStudentName()+" ( D/O "+dataList.get(i).getFatherName()+" )", "left");
						Phrase cellPhrase=new Phrase(URLDecoder.decode(dataList.get(i).getHindiName()==null?"":dataList.get(i).getHindiName(),"utf-8"),hindiFont);
						Phrase cellPhrase1=new Phrase(URLDecoder.decode(dataList.get(i).getFatherHindiName()==null?"":dataList.get(i).getFatherHindiName(),"utf-8"),hindiFont);						
						Phrase mainCellPhrase=new Phrase();
						Phrase phrase=new Phrase(" ¼ vkRetk ",font);	
						Phrase phrase2=new Phrase(" ½",font);
						mainCellPhrase.add(cellPhrase);	
						mainCellPhrase.add(phrase);
						mainCellPhrase.add(cellPhrase1);
						mainCellPhrase.add(phrase2);
						Cell c3 = new Cell(mainCellPhrase);
						c3.setHorizontalAlignment(Element.ALIGN_LEFT);
						c3.setVerticalAlignment(Element.ALIGN_TOP);
						dataTable.addCell(c3);										
					}
					else if(dataList.get(i).getGender().toString().equals("M")){
						addCell(headerFont, dataTable,dataList.get(i).getStudentName()+" ( S/O "+dataList.get(i).getFatherName()+" )", "left");
						Phrase cellPhrase=new Phrase(URLDecoder.decode(dataList.get(i).getHindiName()==null?"":dataList.get(i).getHindiName(),"utf-8"),hindiFont);
						Phrase cellPhrase1=new Phrase(URLDecoder.decode(dataList.get(i).getFatherHindiName()==null?"":dataList.get(i).getFatherHindiName(),"utf-8"),hindiFont);
						
						Phrase mainCellPhrase=new Phrase();						
						Phrase phrase=new Phrase(" ¼ vkRet ",font);							
						Phrase phrase2=new Phrase(" ½",font);
						mainCellPhrase.add(cellPhrase);						
						mainCellPhrase.add(phrase);						
						mainCellPhrase.add(cellPhrase1);
						mainCellPhrase.add(phrase2);
						Cell c3 = new Cell(mainCellPhrase);
						c3.setHorizontalAlignment(Element.ALIGN_LEFT);
						c3.setVerticalAlignment(Element.ALIGN_TOP);
						dataTable.addCell(c3);						
					}
					addCell(headerFont, dataTable,dataList.get(i).getEnrollmentNo(), "");
				}
				else{					
					addCell(headerFont, dataTable, String.valueOf(i+1)+".", "");
					addCell(headerFont, dataTable,dataList.get(i).getRollNumber(), "");
					addCell(headerFont, dataTable,dataList.get(i).getStudentName(), "left");
					addCell(hindiFont, dataTable,URLDecoder.decode(dataList.get(i).getHindiName()==null?"":dataList.get(i).getHindiName(),"utf-8"), "left");
					addCell(headerFont, dataTable,dataList.get(i).getEnrollmentNo(), "");
				}
				
			}
			reportResult="true";
			document.add(dataTable);
		}
		else{
			reportResult="false-Record Not Found";
		}		
	
		document.close();
		} catch (Exception e) {
			reportResult="false";
			logger.error("Error in FinalStudentControll inside method : generateFinalStudentReport"+e);
			ReportLogBean reportErrorBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
					request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("reportCode"),"",request.getParameter("rollNumber"),
					request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
					logResult = reportDao.insertReportErrorLog(reportErrorBean);
					System.out.println("report error log result "+logResult);
					System.out.println("inside the exception "+e);				
		}
		try{
			if(reportResult.equalsIgnoreCase("true")){
				reportGenerated = reportGenerated+1;
				ReportLogBean reportControlBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
						request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
						request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
						request.getParameter("reportCode"),String.valueOf(reportGenerated),"No",
						request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"),
						session.getAttribute("userName").toString());
				reportControlBean.setIsGenerated(request.getParameter("isGenerated")==null?"no":"yes");				
				logResult = reportDao.insertReportLog(reportControlBean);
		}
		}catch(Exception e){
			System.out.println("some exception in log entry "+e);
			logObj.error("Error in FinalStudentControll inside method : generateFinalStudentReport"+e);
		}
		return reportResult;
	}
	/**
	 * This method add a cell to Table
	 * 
	 * @param cellFont
	 *            , Font used
	 * @param table
	 *            , PdfPTable in which cell is added
	 * @param value
	 *            , Text string in cell
	 * @param String align for Alignment of Cell
	 */
	public static final void addCell(Font cellFont, Table table, String value,String align) {
		try {
			Cell c1 = new Cell(new Phrase(value, cellFont));
			if(align.equals("left")){
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			}
			else{
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			}
			c1.setVerticalAlignment(Element.ALIGN_TOP);
			table.addCell(c1);
		} catch (BadElementException e) {
			logObj.error("Error in FinalStudentControll inside method : addCell"+e);
		}
	}




/**
 * Method to set get data to generate component whose marks changed report
 * @author Ashish Mohan
 * @param request
 * @param response
 * @return ModelAndView containing info(String message)
 */
public ModelAndView modifiedMarksWithComponentReport(HttpServletRequest request,
	HttpServletResponse response) throws Exception {		
	HttpSession session = request.getSession(true);
	String universityId=(String) session.getAttribute("universityId");
	if (universityId == null) {
		return new ModelAndView("general/SessionInactive",
				"sessionInactive", true);
	}
	EnrollmentInfo input=new EnrollmentInfo();	
	input.setUniversityId(universityId);
	input.setEntity(request.getParameter("entityId"));
	String semesterType = request.getParameter("semesterType").toString();
	if(semesterType.contains("Even")){
		input.setCount(0);//to set semester wise
	}
	else{
		input.setCount(1);
	}

	input.setSessionStartDate(request.getParameter("fromSession"));
	input.setSessionEndDate(request.getParameter("toSession"));
	input.setStatus("ACT");
	input.setAddress("usedForComponentReport");

	
	String message=generateComponentReport(request,session,input);
	return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
}




/**
 * Method to generate component whose marks changed
 * @param HttpServletRequest request
 * @param HttpSession session
 * @param Object of EnrollmentInfo input
 * @param String reportCode
 * @return String message
 * @author Ashish Mohan 
*/

public String generateComponentReport(HttpServletRequest request,HttpSession session,EnrollmentInfo input){
	String status = "false";
	String logResult="";		
	int reportGenerated = 0;
	
	String reportSession = input.getSessionStartDate().substring(0, 4) + "-" + input.getSessionEndDate().substring(0, 4);
	
	try {
		
		//calling method to get data for the pdf
		List<EnrollmentInfo> dataList=enrollmentService.getStudentFinalList(input);
		
		//check size of data
		if(dataList.size()==0){
			return "false-No Record Found Whose Marks Modified By Selected Entity Employees For This Semester Type";
		}
		
		
		int semType = input.getCount();
		String semesterType = semType==0?"Even Semesters":"Odd Semesters";
		String sep = System.getProperty("file.separator");
		
		//********* generating report path ashish code *******************
		ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
				request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),semesterType);
		
		String reportPath = ReportPath.getPath(reportPathBean);				
		System.out.println("here in controller of component chart after path generation "+reportPath);
		String filePath = this.getServletContext().getRealPath("/");
		filePath=filePath+reportPath;
		File file = new File(filePath);
		file.mkdirs();
		//***************************************
		
		Document document = new Document(PageSize.A4.rotate());

		String oddEven = "Even-Semester";
		if (input.getCount() % 2 == 1) {
			oddEven = "Odd-Semester";
		}


		PdfWriter.getInstance(document, new FileOutputStream(filePath+sep+request.getParameter("reportDescription")+".pdf"));


		Phrase headerPhrase = new Phrase(
				"LIST OF MARKS CORRECTION BY "+request.getParameter("entityName").toUpperCase()+" - "
						+ reportSession
						+ " ("
						+ oddEven.replace('-', ' ').toUpperCase() + ")",
				FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0)));

		PdfPTable contentTable = new PdfPTable(new float[] { 4,2, 2, 2, 2, 2,3, 2,3});
		contentTable.setWidthPercentage(100);
		contentTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		//set font of header line in table
		Font headerFont=new Font(Font.BOLD);
		
		//adding header line of table
		
		PdfPCell cell = new PdfPCell(new Phrase("Program Offered By",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		contentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Program",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Course Code",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Evaluation Component",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Marks",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Old Marks",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Roll Number",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Modifier Employee Code",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Marks Changed Date",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);
		
		//setting no. of row of table as header
		contentTable.setHeaderRows(1);
			
		//adding all data in pdf
		for (int j = 0; j <dataList.size(); j++) {
			
				contentTable.addCell(new Phrase(dataList.get(j).getEntityCode()));
				contentTable.addCell(new Phrase(dataList.get(j).getProgram()));
				contentTable.addCell(new Phrase(dataList.get(j).getCategoryCode()));
				contentTable.addCell(new Phrase(dataList.get(j).getCode()));
				contentTable.addCell(new Phrase(dataList.get(j).getCity()));
				contentTable.addCell(new Phrase(dataList.get(j).getState()));
				contentTable.addCell(new Phrase(dataList.get(j).getRollNumber()));
				contentTable.addCell(new Phrase(dataList.get(j).getFacRegNo()));
				contentTable.addCell(new Phrase(dataList.get(j).getSeqNum()));
		}
		
		
		HeaderFooter header = new HeaderFooter(headerPhrase, false);
		header.setBorder(0);
		header.setAlignment(Element.ALIGN_CENTER);
		
		HeaderFooter footer = new HeaderFooter(new Phrase("Page "),true);
		footer.setBorder(0);
		footer.setAlignment(Element.ALIGN_RIGHT);
		
		document.setHeader(header);
		document.setFooter(footer);
		document.open();
		document.add(contentTable);
		document.close();
		status = "true";
		
	} catch (Exception e) {
		status = "false";			
		ReportLogBean reportErrorBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
				request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("reportCode"),"",request.getParameter("rollNumber"),
				request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
		logResult = reportDao.insertReportErrorLog(reportErrorBean);						
		System.out.println("nupur : some exception in report generation");
		System.out.println("exception :"+e);			
		logObj.error(e.getMessage());
	}
	try{
		if(status.equalsIgnoreCase("false")){
			reportGenerated = reportGenerated+1;
			ReportLogBean reportControlBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
					request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("reportCode"),String.valueOf(reportGenerated),"No",
					request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"),
					session.getAttribute("userName").toString());			
			System.out.println("before control log insert");		
			logResult = reportDao.insertReportLog(reportControlBean);
			System.out.println("report control log result "+logResult);
	}
	}catch(Exception e){
		System.out.println("some exception in log entry "+e);
	}
	return status;
}

}
