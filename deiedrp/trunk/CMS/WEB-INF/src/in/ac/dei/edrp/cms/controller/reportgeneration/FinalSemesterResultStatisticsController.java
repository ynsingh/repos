/**
 * @(#) SubjectCategoryWiseStudentListController.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
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
import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.dao.reportgeneration.FinalSemesterResultStatisticsDAO;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ResultStatisticsInfo;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.domain.reportgeneration.FinalSemesterResultStatistics;
import in.ac.dei.edrp.cms.domain.reportgeneration.FinalSemesterResultStatisticsCategoryWise;
import in.ac.dei.edrp.cms.domain.reportgeneration.SubjectWiseMeritList;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * This controller is designed for setting & retrieving
 * the activity master information 
 * @author Ankit Jain
 * @date 15 Apr 2011
 * @version 1.0
 */
public class FinalSemesterResultStatisticsController extends MultiActionController{

	private FinalSemesterResultStatisticsDAO finalSemesterResultStatisticsDAO;
	private ReportDao reportDao;
	
	public void setFinalSemesterResultStatisticsDAO(
			FinalSemesterResultStatisticsDAO finalSemesterResultStatisticsDAO) {
		this.finalSemesterResultStatisticsDAO = finalSemesterResultStatisticsDAO;
	}
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	/**
	 * Method to get the program list
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getSessionList(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		FinalSemesterResultStatistics finalSemesterResultStatistics=new FinalSemesterResultStatistics();

		finalSemesterResultStatistics.setEntityId(request.getParameter("selectedEntityId"));
		finalSemesterResultStatistics.setUniversityId(request.getParameter("selectedEntityId").substring(0, 4));

		System.out.println(finalSemesterResultStatistics.getUniversityId());

		List<FinalSemesterResultStatistics> sessionDatesList = finalSemesterResultStatisticsDAO.getSessionList(finalSemesterResultStatistics);
		return new ModelAndView("SubjectCategoryWiseStudentList/SessionDates","sessionDates", sessionDatesList);

	}

	/**
	 * this method will get the current session
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getCurrentSession(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		SubjectWiseMeritList subjectWiseMeritList=new SubjectWiseMeritList();
		subjectWiseMeritList.setUniversityId(request.getParameter("selectedEntityId").substring(0, 4));
		List<SubjectWiseMeritList> sessionDates = finalSemesterResultStatisticsDAO.getCurrentSession(subjectWiseMeritList);
		return new ModelAndView("SubjectCategoryWiseStudentList/SessionDates","sessionDates", sessionDates);			
	}


	/**
	 * Method for generate the PDF.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView generatePDF(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
				
		System.out.println("inside generatePDF merit list controller");
		HttpSession session = request.getSession();
		FinalSemesterResultStatistics finalSemesterResultStatistics=new FinalSemesterResultStatistics();
		finalSemesterResultStatistics.setEntityId(request.getParameter("entityId"));
		finalSemesterResultStatistics.setSessionStartDate(request.getParameter("fromSession"));
		finalSemesterResultStatistics.setSessionEndDate(request.getParameter("toSession"));
		finalSemesterResultStatistics.setUniversityId(session.getAttribute("universityId").toString());
		System.out.println("entityId is: "+ finalSemesterResultStatistics.getEntityId());		
		String semesterType = request.getParameter("semesterType").toString();
		if(semesterType.contains("Even")){
			finalSemesterResultStatistics.setSemesterWise("0");
		}
		else{
			finalSemesterResultStatistics.setSemesterWise("1");
		}
		
		List <FinalSemesterResultStatistics> getProgramDetail = finalSemesterResultStatisticsDAO.getProgramDetails(finalSemesterResultStatistics);
		System.out.println("siiiiize is:"+ getProgramDetail.size());
		String result = generatePDF(request,getProgramDetail);				
		if(result=="true"){
			return new ModelAndView("activitymaster/SubmitSuccesful", "message", result);
//			return new ModelAndView("ManualProcess/MyException","exception",result);
		}
		else{
			String message = "false-There is some error in PDF Generation kindly view the error log for more information";
			return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
//			return new ModelAndView("ManualProcess/MyException","exception",message);
		}			
	}

	public String generatePDF(HttpServletRequest request,List<FinalSemesterResultStatistics> programDetails){
		String result = "false";		
		String logResult="";		
		int reportGenerated = 0;
		File file;
		HttpSession session=request.getSession(true);
		try{
			List<FinalSemesterResultStatistics> completeDetails=new ArrayList<FinalSemesterResultStatistics>();		
			String sessionStartDate=request.getParameter("fromSession");
			String sessionEndDate=request.getParameter("toSession");			
			String sep = System.getProperty("file.separator");
			//*********nupur code *******************
			ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
					request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
					request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
			
			String reportPath = ReportPath.getPath(reportPathBean);				
			System.out.println("here in controller of consolidated chart after path generation "+reportPath);
			String path = this.getServletContext().getRealPath("/");
			path=path+reportPath;
			file = new File(path);
			file.mkdirs();
			//***************************************						
			Document document = new Document(PageSize.A4.rotate());	
			Phrase header1= new Phrase(session.getAttribute("universityName").toString().toUpperCase(),FontFactory.getFont(
					FontFactory.HELVETICA, 12, Font.BOLD,new Color(0, 0, 0)));
			Phrase header2= new Phrase("\nFINAL SEMESTER RESULT STATISTICS : "+ sessionStartDate.substring(0, 4)+"-"+
					sessionEndDate.substring(0, 4)+"("+request.getParameter("semesterType")+")",FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,new Color(0, 0, 0)));
			Phrase headers=new Phrase();
			headers.add(header1);
			headers.add(header2);       
			HeaderFooter header = new HeaderFooter(headers,false);
			header.setBorderWidth(0);
			header.setAlignment(Element.ALIGN_CENTER);
			Date printingDate = new Date();
			String toDay = DateFormat.getDateTimeInstance().format(printingDate);
			HeaderFooter footer = new HeaderFooter(new Phrase("Date : "+toDay),false); 
			footer.setBorderWidth(0);
			Font cellFont = new Font(Font.HELVETICA, 8);
			PdfPTable studentTable = new PdfPTable(new float[] {5,2,3,3,3,3,3,3,2,2,3,4,4,4,3});
			studentTable.setWidthPercentage(100f);		
			PdfPCell c1 = new PdfPCell(new Phrase("", cellFont));
			addCell(c1, cellFont, studentTable,"F-CL-BR-S");
			addCell(c1, cellFont, studentTable,"SEX");
			addCell(c1, cellFont, studentTable,"ENROLLED");
			addCell(c1, cellFont, studentTable,"APPEARED");
			addCell(c1, cellFont, studentTable,"UFM");
			addCell(c1, cellFont, studentTable,"INCOMPL.");
			addCell(c1, cellFont, studentTable,"PASSED");
			addCell(c1, cellFont, studentTable,"I-DIST");
			addCell(c1, cellFont, studentTable,"I");
			addCell(c1, cellFont, studentTable,"II");
			addCell(c1, cellFont, studentTable,"PASS");
			addCell(c1, cellFont, studentTable,"ELIGIBLE FOR REMEDIALS");
			addCell(c1, cellFont, studentTable,"FAILED 1ST ATTEMPT");			
			addCell(c1, cellFont, studentTable,"FAILED 2ND ATTEMPT");
			addCell(c1, cellFont, studentTable,"PASSED %");
			System.out.println("size of program details :"+programDetails.size());
			FinalSemesterResultStatistics entityObject= new FinalSemesterResultStatistics();
			FinalSemesterResultStatistics finalSemesterResultStatistics=new FinalSemesterResultStatistics();
			finalSemesterResultStatistics.setEntityId(request.getParameter("entityId"));
			entityObject=finalSemesterResultStatisticsDAO.getEntityInfo(finalSemesterResultStatistics);
			for(FinalSemesterResultStatistics singleProgram:programDetails){			
				finalSemesterResultStatistics.setProgramCourseKey(singleProgram.getProgramCourseKey());
				finalSemesterResultStatistics.setProgramId(singleProgram.getProgramId());
				finalSemesterResultStatistics.setProgramCode(singleProgram.getProgramCode());
				finalSemesterResultStatistics.setBranchId(singleProgram.getBranchId());
				finalSemesterResultStatistics.setSpecializationId(singleProgram.getSpecializationId());
				finalSemesterResultStatistics.setSemesterCode(singleProgram.getSemesterCode());			
				finalSemesterResultStatistics.setProgramCourseKey2(singleProgram.getProgramCourseKey().substring(0, 11)+"%");
				//***********************nupur - 3/11/2011******************************
				finalSemesterResultStatistics.setSemesterStartDate(singleProgram.getSemesterStartDate());
				finalSemesterResultStatistics.setSemesterEndDate(singleProgram.getSemesterEndDate());
				finalSemesterResultStatistics.setSessionStartDate(sessionStartDate);
				finalSemesterResultStatistics.setSessionEndDate(sessionEndDate);
				finalSemesterResultStatistics.setUniversityId(session.getAttribute("universityId").toString());
				//**********************nupur end ***************************************
				completeDetails=finalSemesterResultStatisticsDAO.getCompleteDetails(finalSemesterResultStatistics);
				System.out.println("size of complete details :"+completeDetails.size());			

				for(int i=0;i<completeDetails.size();i++){
					if(i==0){
						addCell(c1, cellFont, studentTable,entityObject.getEntityCode()+"-"+singleProgram.getProgramCode()+"-"+
								singleProgram.getBranchId()+"-"+singleProgram.getSemesterCode());
					}
					else{
						addCell(c1, cellFont, studentTable,"");
					}						
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getSex());
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getEnrolled());
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getAppeared());
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getUfm());
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getIncomplete());
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getPassed());
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getIDistinction());
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getIDiv());
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getIIDiv());
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getPass());
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getRemedial());
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getFailedFirstAttempt());
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getFailedSecondAttempt());
					addCell(c1, cellFont, studentTable,completeDetails.get(i).getPercent());	
				}				
			}	

			PdfWriter.getInstance(document, new FileOutputStream(path+sep+request.getParameter("reportDescription")+".pdf"));
//			PdfWriter.getInstance(document,new FileOutputStream(filePath+sep+"Final Semester Result Statistics.pdf"));
			document.setPageSize(PageSize.A4.rotate());
			document.setHeader(header);
			document.setFooter(footer);
			document.open();
			document.add(studentTable);
			document.close();		
			result = "true";		
		}catch(Exception e){
			result = "false";
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
			if(result.equalsIgnoreCase("true")){
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
		}catch(Exception e){
			System.out.println("some exception in log entry "+e);
		}
		return result;
	}

	public static final void addCell(PdfPCell c1, Font cellFont, PdfPTable chartTable, String s) {
		try {
			c1 = new PdfPCell(new Phrase(s, cellFont));
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setBorderWidth(1);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setVerticalAlignment(Element.ALIGN_TOP);
		chartTable.addCell(c1);
	}

}