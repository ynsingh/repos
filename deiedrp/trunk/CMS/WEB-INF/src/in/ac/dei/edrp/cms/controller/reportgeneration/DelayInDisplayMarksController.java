/*
 * @(#) DelayInDisplayMarksController.java
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
package in.ac.dei.edrp.cms.controller.reportgeneration;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.dao.reportgeneration.CourseWisePanelOfExaminersDao;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.domain.reportgeneration.FinalSemesterResultStatistics;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
/**
 * this is Server side controller class for generate Delay In Display Marks Report
 * @version 1.0 
 * @author Mandeep Sodhi
 * @Date 15 April 2012
 */
public class DelayInDisplayMarksController extends MultiActionController{
	private CourseWisePanelOfExaminersDao courseWisePanelOfExaminersDao;
	public static Logger logObj=Logger.getLogger(DelayInDisplayMarksController.class);
	/**
	 * @param courseWisePanelOfExaminersDao the courseWisePanelOfExaminersDao to set
	 */
	public void setCourseWisePanelOfExaminersDao(
			CourseWisePanelOfExaminersDao courseWisePanelOfExaminersDao) {
		this.courseWisePanelOfExaminersDao = courseWisePanelOfExaminersDao;
	}
	
	private ReportDao reportDao;
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	/**
	 * Method to get the Details For Report
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
    public ModelAndView delayInDisplayComponentMarksPdf(HttpServletRequest request,
    		HttpServletResponse response)throws Exception{
    	
		HttpSession session = request.getSession(true);
		String universityId=(String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
    	
    	FinalSemesterResultStatistics input=new FinalSemesterResultStatistics();
    	input.setEntityId(request.getParameter("entityId"));
    	input.setSessionStartDate(request.getParameter("fromSession"));
    	input.setSessionEndDate(request.getParameter("toSession"));
    	input.setProgramId(request.getParameter("programId"));
    	input.setBranchId(request.getParameter("branchId"));
    	input.setSpecializationId(request.getParameter("specializationId"));
    	input.setSemesterCode(request.getParameter("semesterCode"));
    	input.setProgramName(request.getParameter("programName"));
    	input.setBranchName(request.getParameter("branchName"));
    	input.setSpecializationName(request.getParameter("specializationName"));
    	input.setSemesterName(request.getParameter("semesterName"));
    	input.setEntityName(request.getParameter("entityName"));
    	input.setUniversityId(session.getAttribute("universityId").toString());
    	input.setReportCode(request.getParameter("reportCode"));
    	input.setReportType(request.getParameter("reportType"));
    	input.setReportDescription(request.getParameter("reportDescription"));

    	List<FinalSemesterResultStatistics>programCorseKeyList=courseWisePanelOfExaminersDao.getProgramCourseKey(input);
    	 List<FinalSemesterResultStatistics>delayDetail=new ArrayList<FinalSemesterResultStatistics>();
    	 
    	if(programCorseKeyList.size()==0){
		    return new ModelAndView("ExternalExaminerCourse/insert",
		    		"info", delayDetail.size());
    	}
    	else{
    		String resultPdf=generatedelayInDisplayMarksPdf(request,input,delayDetail,programCorseKeyList);
    	  
    	    return new ModelAndView("ExternalExaminerCourse/insert",
    	    		"info", resultPdf);	
    	}

    }
	/**
	 * Method to generate Delay In display Marks Report
	 * @param HttpServletRequest request
	 * @param HttpSession session
	 * @param Object of EnrollmentInfo input
	 * @param String reportCode
	 * @return String status 
	*/
	private String generatedelayInDisplayMarksPdf(
			HttpServletRequest request, FinalSemesterResultStatistics input,
			List<FinalSemesterResultStatistics> delayDetail, List<FinalSemesterResultStatistics> programCorseKeyList) {
		File file;
		String status = "false";
		String reportPath=null;
		String sep=System.getProperty("file.separator");
		HttpSession session=request.getSession(true);
	    String filePath = this.getServletContext().getRealPath("/");
		Document document=new Document(PageSize.A4.rotate());
		try{

		//**********************Making directory for storing the file**************************	
			ReportPathBean reportPathBean =new ReportPathBean();
			if(request.getParameter("branchName").equalsIgnoreCase("All")||request.getParameter("specializationName").equalsIgnoreCase("All")
					||request.getParameter("semesterName").equalsIgnoreCase("All")){
		        reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchName"),request.getParameter("specializationName"),
				request.getParameter("semesterName"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
				request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));	
	                 
	            reportPath=ReportPath.getPath(reportPathBean);
			}
			else{
				 reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
				request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
				reportPath=ReportPath.getPath(reportPathBean);
			}
		//*************************************************************************************	
		System.out.println("here in controller of Delay In Display Marks after path generation "+reportPath);

		filePath=filePath+reportPath;
		 file = new File(filePath);
		file.mkdirs();
	    PdfWriter.getInstance(document,new FileOutputStream(filePath+sep+request.getParameter("reportDescription")+".pdf"));
		
		Paragraph reportName=new Paragraph("                                                                                 "+"DELAY IN DISPLAY OF AWARD BLANK MARKS"+"\n",FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,
				new Color(0, 0, 0)));
		Font cellFont = new Font(Font.HELVETICA,12);
		Font cellFont1=new Font(Font.HELVETICA,10,Font.BOLD);
		
		String program=input.getProgramName();
		String branch=input.getBranchName();
		String specialization=input.getSpecializationName();
		String semester=input.getSemesterName();
		String entity=input.getEntityName();
		String sessionDate  =input.getSessionStartDate().substring(0,4)+"-"+input.getSessionEndDate().substring(2,4);
		
		Phrase head=new Phrase();
		
    if(specialization.equalsIgnoreCase("None") && !branch.equalsIgnoreCase("None")){
    	head =new Phrase("Class:"+program+"                                   "+"Branch:"+branch+"                                                                                                         "+semester,cellFont);
    }
    else if(branch.equalsIgnoreCase("None") && !specialization.equalsIgnoreCase("None")){
    	head =new Phrase("Class:"+program+"                                  "+"Specialization:"+specialization+"                                                                                          "+semester,cellFont);
    }
    else if(specialization.equalsIgnoreCase("None") && branch.equalsIgnoreCase("None")){
    	head =new Phrase("Class:"+program+"                                                                                                                                                                "+semester,cellFont);
    }
    else{
        head =new Phrase("Class:"+program+"                                   "+"Branch:"+branch+"                                  "+"Specialization:"+specialization+"                                  "+semester,cellFont);
    }
		Phrase entityname=new Phrase(entity+"                                                                                                                                                    "+"Session:"+sessionDate+"\n");

		
		PdfPTable headerTable=new PdfPTable(new float[]{1,4,2,2,3,2,2,2});
		headerTable.setWidthPercentage(100f);
		headerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    
		Chunk line = new Chunk(
				"___________________________________________________________________________________________________________________");
		PdfPCell cell=new PdfPCell(new Phrase("S.No.",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		
		cell=new PdfPCell(new Phrase("Teacher",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);


		cell=new PdfPCell(new Phrase("Course Code",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		
		cell=new PdfPCell(new Phrase("Roll No.",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		
		cell=new PdfPCell(new Phrase("Evaluation Component",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		
		cell=new PdfPCell(new Phrase("Marks Display Date",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		
		cell=new PdfPCell(new Phrase("Actual Display Date ",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		
		cell=new PdfPCell(new Phrase("Delay In Days",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		 line = new Chunk(
				"___________________________________________________________________________________________________________________");
		
		
		
		
	PdfPTable contentTable=new PdfPTable(new float[]{1,4,2,2,3,2,2,2});
		contentTable.setWidthPercentage(100f);
		contentTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		int k=0;
		for (int j=0;j<programCorseKeyList.size();j++){
    		input.setProgramCourseKey(programCorseKeyList.get(j).getProgramCourseKey());
        	input.setYear(programCorseKeyList.get(j).getYear());
        	
			delayDetail=courseWisePanelOfExaminersDao.getDelayDetails(input); 
			if(delayDetail.size()==0){
				continue;
			}
		for(int i=0;i<delayDetail.size();i++){
			
			k++;
			String count=Integer.toString(k);
			PdfPCell cell1=new PdfPCell(new Phrase(count));
			cell1.setBorderWidth(0);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(cell1);
			
			cell1=new PdfPCell(new Phrase(delayDetail.get(i).getTeacher()));
			cell1.setBorderWidth(0);
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			contentTable.addCell(cell1);
			
			cell1=new PdfPCell(new Phrase(delayDetail.get(i).getCourseCode()));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorderWidth(0);
			contentTable.addCell(cell1);
			
			cell1=new PdfPCell(new Phrase(delayDetail.get(i).getRollNumber()));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorderWidth(0);
			contentTable.addCell(cell1);
			
			cell1=new PdfPCell(new Phrase(delayDetail.get(i).getEvaluationId()));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setBorderWidth(0);
			contentTable.addCell(cell1);
			
			cell1=new PdfPCell(new Phrase(delayDetail.get(i).getCompDisplaydays()));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorderWidth(0);
			contentTable.addCell(cell1);
			
			cell1=new PdfPCell(new Phrase(delayDetail.get(i).getActualDisplayDates()));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorderWidth(0);
			contentTable.addCell(cell1);
			
			cell1=new PdfPCell(new Phrase(delayDetail.get(i).getDelayDays()));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorderWidth(0);
			contentTable.addCell(cell1);
                   }

		}


		Phrase headerPhrase = new Phrase();
		headerPhrase.add(reportName);
		headerPhrase.add("\n");
		headerPhrase.add(entityname);
		headerPhrase.add(head);
		headerPhrase.add(line);
		headerPhrase.add(headerTable);
		headerPhrase.add(line);
		headerPhrase.add("\n");
	
		HeaderFooter header=new HeaderFooter(headerPhrase,false);
		header.setBorder(0);
		header.setAlignment(Element.ALIGN_LEFT);
		HeaderFooter footer=new HeaderFooter(new Phrase("Page "),true);
		footer.setBorderWidth(0);
		footer.setAlignment(Element.ALIGN_RIGHT);
		
		document.setHeader(header);
		document.setFooter(footer);
		document.open();
		document.add(contentTable);
		document.close();
		status = "true";

		}
		catch (Exception e) {
			status = "false";			
			System.out.println("exception :"+e);			
			logObj.error(e.getMessage());
		}
		
		String result=status+reportPath;
		return result;
	}

	/**
	 * This method get list of inputs and generate the path according to report type
	 * @param request
	 * @param response
	 * @return ModelAndView result
	 */
	public ModelAndView getReportPath(HttpServletRequest request,
    		HttpServletResponse response)throws Exception{
		
		String reportPath=null;
		String result=null;
		HttpSession session=request.getSession(true);		
		ReportPathBean reportPathBean =new ReportPathBean();
		if(request.getParameter("branchName").equalsIgnoreCase("All")||request.getParameter("specializationName").equalsIgnoreCase("All")
				||request.getParameter("semesterName").equalsIgnoreCase("All")){
	        reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
			request.getParameter("programId"),request.getParameter("branchName"),request.getParameter("specializationName"),
			request.getParameter("semesterName"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
			request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
			request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));	
 
            reportPath=ReportPath.getPath(reportPathBean);
		}
		else{
			 reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
			request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
			request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
			request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
			request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
			reportPath=ReportPath.getPath(reportPathBean);
		}
		String sep=System.getProperty("file.separator");
		String initialPath = getServletContext().getRealPath(sep);
		File fileVerify=new File(initialPath+reportPath);
		if(fileVerify.exists()){
			result=reportPath;

		}
		else{
			result="false-Please Generate the report first to proceed with Download/Print";
		}
		return new ModelAndView("ExternalExaminerCourse/insert",
	    		"info",result);
	}
	
/*
 * DELAY IN DISPLAY AWARD BLANK MARKS  ENTITY-SEMESTER TYPE WISE REPORT
 */
	/**
	 * Method to generate Delay In display Marks Report Entity Wise 
	 * @param HttpServletRequest request
	 * @param HttpSession session
	 * @return String status 
	*/	
	public ModelAndView DelayInDisplayMarksEntityWise(HttpServletRequest request,
    		HttpServletResponse response)throws Exception{
	  	
		HttpSession session = request.getSession(true);
		String universityId=(String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
    	
    	FinalSemesterResultStatistics input=new FinalSemesterResultStatistics();
    	input.setEntityId(request.getParameter("entityId"));
    	input.setSessionStartDate(request.getParameter("fromSession"));
    	input.setSessionEndDate(request.getParameter("toSession"));
    	input.setEntityName(request.getParameter("entityName"));
    	input.setUniversityId(session.getAttribute("universityId").toString());
    	input.setReportCode(request.getParameter("reportCode"));
    	input.setReportType(request.getParameter("reportType"));
    	input.setReportDescription(request.getParameter("reportDescription"));
    	
    	if(request.getParameter("semesterType").equalsIgnoreCase("Even Semesters")){
    		input.setPercent("0");
    	}
    	else{
    		input.setPercent("1");
    	}
    	List<FinalSemesterResultStatistics>programCorseKeyList=courseWisePanelOfExaminersDao.getProgramCourseKeyEntityWise(input);

    	 
    	if(programCorseKeyList.size()==0){
    		String message="false-No Record Found For The Selected Entity And Semester Type";
    		return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);

    	}
    	else{
    		String resultPdf=generatedelayInDisplayMarksEntityWisePdf(request,input,programCorseKeyList);
    	  
    		if(resultPdf=="true"){
    			return new ModelAndView("activitymaster/SubmitSuccesful", "message", resultPdf);

    		}
    		else{
    			String message = "false-There is some error in PDF Generation kindly view the error log for more information";
    			return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);

    		}
    	}
	}
	/**
	 * Method to generate Delay In display Marks Report
	 * @param HttpServletRequest request
	 * @param HttpSession session
	 * @param Object of EnrollmentInfo input
	 * @param String reportCode
	 * @return String status 
	*/
	private String generatedelayInDisplayMarksEntityWisePdf(
			HttpServletRequest request, FinalSemesterResultStatistics input,
			List<FinalSemesterResultStatistics> programCorseKeyList) {
		File file;
		String status = "false";
		String logResult="";		
		int reportGenerated = 0;
		String sep=System.getProperty("file.separator");
		HttpSession session=request.getSession(true);
		Document document=new Document(PageSize.A4.rotate());
		try{

		//**********************Making directory for storing the file**************************	

			ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
					request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
					request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
			
			String reportPath = ReportPath.getPath(reportPathBean);				
			System.out.println("here in controller of consolidated chart after path generation "+reportPath);
			String filePath = this.getServletContext().getRealPath("/");
			filePath=filePath+reportPath;
			file = new File(filePath);
			file.mkdirs();

		//*************************************************************************************	

	    PdfWriter.getInstance(document,new FileOutputStream(filePath+sep+request.getParameter("reportDescription")+".pdf"));
		Paragraph reportName=new Paragraph("                                                      "+"DELAY IN DISPLAY OF AWARD BLANK MARKS"+"-"+"("+request.getParameter("semesterType")+")"+"\n",FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD,
				new Color(0, 0, 0)));
		Font cellFont1=new Font(Font.HELVETICA,12,Font.BOLD);
		
		String entity=request.getParameter("entityName");
		String sessionDate  =input.getSessionStartDate().substring(0,4)+"-"+input.getSessionEndDate().substring(2,4);

		Phrase entityname=new Phrase("        "+entity+"                                                                                                                                                                 "+"Session:"+sessionDate+"\n");
		PdfPTable headerTable=new PdfPTable(new float[]{1,2,2,2,3,2,2,2});
		headerTable.setWidthPercentage(100f);
		headerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    
		Chunk line = new Chunk(
				"___________________________________________________________________________________________________________________");
		PdfPCell cell=new PdfPCell(new Phrase("S.No.",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		
		cell=new PdfPCell(new Phrase("Teacher",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);

		cell=new PdfPCell(new Phrase("Class",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		
		cell=new PdfPCell(new Phrase("Course Code",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		
		
		cell=new PdfPCell(new Phrase("Evaluation Component",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		
		cell=new PdfPCell(new Phrase("Marks Display Date",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		
		cell=new PdfPCell(new Phrase("Actual Display Date ",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		
		cell=new PdfPCell(new Phrase("Delay In Days",cellFont1));
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0);
		headerTable.addCell(cell);
		 line = new Chunk(
				"___________________________________________________________________________________________________________________");
		
		
		
		
	PdfPTable contentTable=new PdfPTable(new float[]{1,2,2,2,3,2,2,2});
		contentTable.setWidthPercentage(100f);
		contentTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		int k=0;

	for (int j=0;j<programCorseKeyList.size();j++){
			input.setProgramCourseKey(programCorseKeyList.get(j).getProgramCourseKey());
			input.setYear(programCorseKeyList.get(j).getYear());
			input.setProgramName(programCorseKeyList.get(j).getProgramName());
			List<FinalSemesterResultStatistics>delayDetailList=courseWisePanelOfExaminersDao.getDelayDetailEntityWise(input);
			System.out.println(delayDetailList.size()+"size");
			if(delayDetailList.size()==0){
				continue;
			}
		for(int i=0;i<delayDetailList.size();i++){
			
			k++;
			String count=Integer.toString(k);
			PdfPCell cell1=new PdfPCell(new Phrase(count));
			cell1.setBorderWidth(0);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(cell1);
			
			cell1=new PdfPCell(new Phrase(delayDetailList.get(i).getTeacher()));
			cell1.setBorderWidth(0);
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			contentTable.addCell(cell1);
			
			cell1=new PdfPCell(new Phrase(delayDetailList.get(i).getProgramName()));
			cell1.setBorderWidth(0);
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			contentTable.addCell(cell1);
			
			cell1=new PdfPCell(new Phrase(delayDetailList.get(i).getCourseCode()));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorderWidth(0);
			contentTable.addCell(cell1);
			
			
			cell1=new PdfPCell(new Phrase(delayDetailList.get(i).getEvaluationId()));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setBorderWidth(0);
			contentTable.addCell(cell1);
			
			cell1=new PdfPCell(new Phrase(delayDetailList.get(i).getCompDisplaydays()));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorderWidth(0);
			contentTable.addCell(cell1);
			
			cell1=new PdfPCell(new Phrase(delayDetailList.get(i).getActualDisplayDates()));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorderWidth(0);
			contentTable.addCell(cell1);
			
			cell1=new PdfPCell(new Phrase(delayDetailList.get(i).getDelayDays()));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorderWidth(0);
			contentTable.addCell(cell1);
                   }
	}
		Phrase headerPhrase = new Phrase();
		headerPhrase.add(reportName);
		headerPhrase.add("\n");
		headerPhrase.add(entityname);
		headerPhrase.add(line);
		headerPhrase.add(headerTable);
		headerPhrase.add(line);
		headerPhrase.add("\n");
	
		HeaderFooter header=new HeaderFooter(headerPhrase,false);
		header.setBorder(0);
		header.setAlignment(Element.ALIGN_LEFT);
		HeaderFooter footer=new HeaderFooter(new Phrase("Page "),true);
		footer.setBorderWidth(0);
		footer.setAlignment(Element.ALIGN_RIGHT);
		
		document.setHeader(header);
		document.setFooter(footer);
		document.open();
		document.add(contentTable);
		document.close();
		status = "true";

		}
		catch(Exception e){
			status = "false";
			ReportLogBean reportErrorBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
					request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("reportCode"),"",request.getParameter("rollNumber"),
					request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
			logResult = reportDao.insertReportErrorLog(reportErrorBean);						
			System.out.println("nupur : some exception in report generation");
			System.out.println("exception :"+e);
		}
		try{
			if(status.equalsIgnoreCase("true")){
				reportGenerated = reportGenerated+1;
				ReportLogBean reportControlBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
						request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
						request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
						request.getParameter("reportCode"),String.valueOf(reportGenerated),"No",
						request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"),
						session.getAttribute("userName").toString());
				reportControlBean.setIsGenerated(request.getParameter("isGenerated")==null?"no":"yes");			
				System.out.println("before control log insert");		
				logResult = reportDao.insertReportLog(reportControlBean);
				System.out.println("report control log result "+logResult);
		}
		}
		catch(Exception e){
			System.out.println("some exception in log entry "+e);
		}
		
		
		return status;
	
	}

	
	/*
	 * LIST OF COURSES FOR WHICH MARKS HAVE BEEN RELEASED BY DEAN REPORT	
	 */
	
	public ModelAndView coursesMarksReleasedByDean(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession(true);
		String universityId=(String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
    	
    	FinalSemesterResultStatistics input=new FinalSemesterResultStatistics();
    	input.setEntityId(request.getParameter("entityId"));
    	input.setSessionStartDate(request.getParameter("fromSession"));
    	input.setSessionEndDate(request.getParameter("toSession"));
    	input.setEntityName(request.getParameter("entityName"));
    	input.setUniversityId(session.getAttribute("universityId").toString());
    	input.setReportCode(request.getParameter("reportCode"));
    	input.setReportType(request.getParameter("reportType"));
    	input.setReportDescription(request.getParameter("reportDescription"));
    	input.setPercent(request.getParameter("semesterType"));
//    	if(request.getParameter("semesterType").equalsIgnoreCase("Even Semesters")){
//    		input.setPercent("0");
//    	}
//    	else{
//    		input.setPercent("1");
//    	}
    		String resultPdf=generateCoursesMarksReleasedByDeanPdf(request,input);
    	  
    		if(resultPdf=="true"){
    			return new ModelAndView("activitymaster/SubmitSuccesful", "message", resultPdf);

    		}
    		else{
    			String message = "false-There is some error in PDF Generation kindly view the error log for more information";
    			return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);

    		}
		
	
}

	private String generateCoursesMarksReleasedByDeanPdf(
			HttpServletRequest request, FinalSemesterResultStatistics input) {
		File file;
		String status = "false";
		String logResult="";		
		int reportGenerated = 0;
		String sep=System.getProperty("file.separator");
		HttpSession session=request.getSession(true);
		Document document=new Document(PageSize.A4.rotate());
		System.out.println(request.getParameter("semesterWise")+"semtype");
		try{

		//**********************Making directory for storing the file**************************
			String semester="";
			if(request.getParameter("semesterWise").contains("0")){
				semester="Even Semesters";
			}
			else{
				semester="Odd Semesters";
			}
			ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
					request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
					request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),semester);
			
			String reportPath = ReportPath.getPath(reportPathBean);				
			System.out.println("here in controller of consolidated chart after path generation "+reportPath);
			String filePath = this.getServletContext().getRealPath("/");
			filePath=filePath+reportPath;
			file = new File(filePath);
			file.mkdirs();

		//*************************************************************************************	
		String sessionDate  =input.getSessionStartDate().substring(0,4)+"-"+input.getSessionEndDate().substring(2,4);
			
	    PdfWriter.getInstance(document,new FileOutputStream(filePath+sep+request.getParameter("reportDescription")+".pdf"));
		Paragraph reportName=new Paragraph("                                "+"LIST OF COURSES WHOSE MARKS RELEASED BY DEAN-"+"("+semester+")"+":"+sessionDate+"\n\n",FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD,
				new Color(0, 0, 0)));
		Font cellFont1=new Font(Font.HELVETICA,11,Font.BOLD);
		





		
		Phrase headerPhrase = new Phrase();
		headerPhrase.add(reportName);
		headerPhrase.add("\n");
	
		HeaderFooter header=new HeaderFooter(headerPhrase,false);
		header.setBorder(0);
		header.setAlignment(Element.ALIGN_LEFT);
		HeaderFooter footer=new HeaderFooter(new Phrase("Page "),true);
		footer.setBorderWidth(0);
		footer.setAlignment(Element.ALIGN_RIGHT);
		
		document.setHeader(header);
		document.setFooter(footer);
		document.open();
		
			
		List<FinalSemesterResultStatistics>entityProgramList=courseWisePanelOfExaminersDao.getEntityProgramList(input);
		
		for(int i=0;i<entityProgramList.size();i++){

			input.setEntityName(entityProgramList.get(i).getEntityName());
			Phrase entityName=new Phrase(entityProgramList.get(i).getEntityName(),FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD,
					new Color(0, 0, 0)));
			entityName.add("\n");
			
			document.add(entityName);

			Chunk line=new Chunk("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			document.add(line);
			
			//add Table
			

			
			List<FinalSemesterResultStatistics>programList=courseWisePanelOfExaminersDao.getReportProgramList(input);	
			if(programList.size()==0){
				PdfPTable headerTable=new PdfPTable(new float[]{1});
				headerTable.setWidthPercentage(100f);
				headerTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
				headerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				
				PdfPCell cell=new PdfPCell();
				cell = new PdfPCell(new Phrase("No Record Found"));
				cell.setBorderWidth(0);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerTable.addCell(cell);
				document.add(headerTable);
			}
			else{
			for(int k=0;k<programList.size();k++){
				
				input.setProgramName(programList.get(k).getProgramName());
				input.setSemesterStartDate(programList.get(k).getSemesterStartDate());
				input.setSemesterEndDate(programList.get(k).getSemesterEndDate());
				input.setProgramCourseKey(programList.get(k).getProgramCourseKey());
				input.setYear(programList.get(k).getYear());
				
				List<FinalSemesterResultStatistics> courseList=null;
				courseList = courseWisePanelOfExaminersDao.getcoursesMarksReleasedByDean(input);
				
				PdfPTable headerTable=new PdfPTable(new float[]{0.5f,1,1,1,1,1,1});
				headerTable.setWidthPercentage(100f);
				headerTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
				headerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				
				PdfPCell cell=new PdfPCell();
				
				for(int s=0;s<7;s++){
					cell = new PdfPCell(new Phrase(""));
					cell.setBorderWidth(0);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					headerTable.addCell(cell);	
			}


				 cell=new PdfPCell(new Phrase("S.NO.",cellFont1));
					cell.setColspan(1);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBorderWidth(0);
					headerTable.addCell(cell);
					
				 cell=new PdfPCell(new Phrase("Prg-Br-Spl-Sem",cellFont1));
				cell.setColspan(1);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidth(0);
				headerTable.addCell(cell);

				
				cell=new PdfPCell(new Phrase("Course Code",cellFont1));
				cell.setColspan(1);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorderWidth(0);
				headerTable.addCell(cell);
				
				cell=new PdfPCell(new Phrase("Dean's Name",cellFont1));
				cell.setColspan(1);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidth(0);
				headerTable.addCell(cell);				
				
				
				cell=new PdfPCell(new Phrase("Display Date",cellFont1));
				cell.setColspan(1);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidth(0);
				headerTable.addCell(cell);
				
				cell=new PdfPCell(new Phrase("Released Date",cellFont1));
				cell.setColspan(1);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorderWidth(0);
				headerTable.addCell(cell);
				
	
				cell=new PdfPCell(new Phrase("Delay Days",cellFont1));
				cell.setColspan(1);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidth(0);
				headerTable.addCell(cell);
				
				
				headerTable.setHeaderRows(2);
				
				PdfPCell cell2 = new PdfPCell();
				

				
				for(int j=0;j<courseList.size();j++){

					cell2 = new PdfPCell(new Phrase((j+1)+""));
					cell2.setBorderWidth(0);
					cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
					headerTable.addCell(cell2);						
					
				cell2 = new PdfPCell(new Phrase(courseList.get(j).getProgramCourseKey()));
				cell2.setBorderWidth(0);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				headerTable.addCell(cell2);					
					
				cell2 = new PdfPCell(new Phrase(courseList.get(j).getCourseCode()));
				cell2.setBorderWidth(0);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerTable.addCell(cell2);


				cell2 = new PdfPCell(new Phrase(courseList.get(j).getTeacher()));
				cell2.setBorderWidth(0);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				headerTable.addCell(cell2);
				
				cell2 = new PdfPCell(new Phrase(courseList.get(j).getActualDisplayDates()));
				cell2.setBorderWidth(0);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				headerTable.addCell(cell2);
				
				cell2 = new PdfPCell(new Phrase(courseList.get(j).getYear()));
				cell2.setBorderWidth(0);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerTable.addCell(cell2);
				

				
				cell2 = new PdfPCell(new Phrase(courseList.get(j).getDelayDays()));
				cell2.setBorderWidth(0);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				headerTable.addCell(cell2);

				}
				document.add(headerTable);
			}
			}

			}

		document.close();
		status = "true";

		}
		catch(Exception e){		
			status = "false";
			ReportLogBean reportErrorBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
					request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("reportCode"),"",request.getParameter("rollNumber"),
					request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
			logResult = reportDao.insertReportErrorLog(reportErrorBean);						
			System.out.println("nupur : some exception in report generation");
			System.out.println("exception :"+e);
		}
		try{
			if(status.equalsIgnoreCase("true")){
				reportGenerated = reportGenerated+1;
				ReportLogBean reportControlBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
						request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
						request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
						request.getParameter("reportCode"),String.valueOf(reportGenerated),"No",
						request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"),
						session.getAttribute("userName").toString());
				reportControlBean.setIsGenerated(request.getParameter("isGenerated")==null?"no":"yes");			
				System.out.println("before control log insert");		
				logResult = reportDao.insertReportLog(reportControlBean);
				System.out.println("report control log result "+logResult);
				
		}
		}
		catch(Exception e){
			System.out.println("some exception in log entry "+e);
		}
		
		
		return status;

	}	
	
	
}

