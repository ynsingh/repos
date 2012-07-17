/*
 * @(#) ConsolidatedChartController.java
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
package in.ac.dei.edrp.cms.controller.consolidatedchart;

import in.ac.dei.edrp.cms.dao.consolidatedchart.ConsolidatedChartService;
import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedChartBean;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ResultStatisticsInfo;
import in.ac.dei.edrp.cms.domain.coursemaster.CourseMasterBean;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * this is Server side controller class for Consolidated Chart and result
 * statistics
 * 
 * @version 1.0 8 September 2011
 * @author MOHD AMIR
 */
public class ConsolidatedChartController extends MultiActionController {
	
	public static Logger logObj=Logger.getLogger(ConsolidatedChartController.class);
	/** creating object of courseGroupService interface */
	private ConsolidatedChartService consolidatedChartService;
	
	
	/** defining setter method for object of CourseGroupService interface */
	public void setConsolidatedChartService(ConsolidatedChartService consolidatedChartService) {
		this.consolidatedChartService = consolidatedChartService;
	}
	private ReportDao reportDao;
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	private ConsolidatedChartGeneration consolidatedChartGenerator;
	public void setConsolidatedChartGenerator(ConsolidatedChartGeneration consolidatedChartGenerator){
		this.consolidatedChartGenerator = consolidatedChartGenerator;
	}
	/**
	 * This method get list of entities from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing entity List
	 */
	public ModelAndView getEntityList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		CourseMasterBean courseMasterBean = new CourseMasterBean();
		courseMasterBean.setUniversityCode(session.getAttribute("universityId")
				.toString());
		List<ConsolidatedChartBean> entityList = consolidatedChartService
				.getEntityList(courseMasterBean);
		return new ModelAndView("coursemaster/componentInfo", "detailsList",
				entityList);
	}

	/**
	 * This method get consolidated chart data from database and send to pdf
	 * generator
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing consolidated chart data
	 */
	public ModelAndView getConsolidatedChartData(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}

		ConsolidatedChartBean input = new ConsolidatedChartBean();

		input.setEntityCode(request.getParameter("entityId"));
		input.setEntity(request.getParameter("entity"));
		input.setProgramCode(request.getParameter("programId"));
		input.setProgram(request.getParameter("programName"));
		input.setBranchCode(request.getParameter("branchId"));
		input.setBranch(request.getParameter("branchName"));
		input.setSpecializationCode(request.getParameter("specializationId"));
		input.setSpecialization(request.getParameter("specializationName"));
		input.setSemesterCode(request.getParameter("semesterCode"));
		input.setSemester(request.getParameter("semesterName"));
		input.setSessionStartDate(request.getParameter("fromSession"));
		input.setSessionEndDate(request.getParameter("toSession"));

		List<ConsolidatedChartBean> dataList = consolidatedChartService.getChartData(input);

		ConsolidatedChartBean semSeqInput = new ConsolidatedChartBean();

		semSeqInput.setProgramCode(input.getProgramCode());
		semSeqInput.setSemesterCode(input.getSemesterCode());
		semSeqInput.setSemSeqNo("%");

		int currentSemesterNo = Integer.parseInt(consolidatedChartService.getSemesterAndSeqNo(semSeqInput).get(0).getSemSeqNo());
		System.out.println("current sem no "+currentSemesterNo);
		request.setAttribute("currentSemesterNo", currentSemesterNo);
		for (int i = 0; i < dataList.size(); i++) {
			int semesterNo = currentSemesterNo;
			String rs = dataList.get(i).getResultSystem();
			String[] percentages = new String[4];
			input.setResultSystem(rs);
			if (rs.equalsIgnoreCase("MK")) {
				percentages[0] = dataList.get(i).getWeighted();
			} else {
				percentages[0] = dataList.get(i).getSgpa();
			}

			int j = 0;
			while (semesterNo >= 2) {
				semesterNo--;
				semSeqInput.setSemesterCode("%");
				semSeqInput.setSemSeqNo(String.valueOf(semesterNo));

				input.setSemesterCode(consolidatedChartService.getSemesterAndSeqNo(semSeqInput).get(0).getSemesterCode());
				input.setRollNo(dataList.get(i).getRollNo());
				List<ConsolidatedChartBean> percentageList = consolidatedChartService.getPreviousSemesterPercentage(input);
				j++;
				if (percentageList.size() > 0) {
					if (rs.equalsIgnoreCase("MK")) {
						percentages[j] = percentageList.get(0).getWeighted();
					} else {
						percentages[j] = percentageList.get(0).getSgpa();
					}
				}
			}
			dataList.get(i).setPercentages(percentages);
		}
		String message = "";
		if(dataList.size()>0){
			dataList.add(input);
			try {
				message = consolidatedChartGenerator.buildPdf(dataList , request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				message = "false-Report generation failed , view the log table for further clarification";				
			}			
			//return new ModelAndView("consolidatedChartGenerator", "chartDataList",dataList);
		}
		else{
			message = "false-No Data to generate Consolidated Chart"	;		
		}
		System.out.println("message in controller "+message);
		return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
//		return new ModelAndView("buildactivitymaster/Result","message",message);
	}

	/**
	 * This method get Result Statistics Data from database and send to PDF
	 * generator
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing Result Statistics Data
	 */
	public ModelAndView getResultStatisticsData(HttpServletRequest request,
			HttpServletResponse response) {
		ResultStatisticsInfo input = new ResultStatisticsInfo();
		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		input.setUniversityId(universityId);
		input.setEntityId(request.getParameter("entityId"));
//		input.setSemesterWise(Integer.parseInt(request.getParameter("semesterWise")));
		System.out.println(request.getParameter("semesterType")+" semester type");
		String semesterType = request.getParameter("semesterType").toString();
		if(semesterType.contains("Even")){
			input.setSemesterWise(0);
		}
		else{
			input.setSemesterWise(1);
		}
//		System.out.println("nupur :::::"+input.getSemesterWise());
//		input.setSemesterWise(Integer.parseInt(request.getParameter("semesterType")));
		/*input.setSessionStartDate(request.getParameter("startDate"));
		input.setSessionEndDate(request.getParameter("endDate"));*/
		input.setSessionStartDate(request.getParameter("fromSession"));
		input.setSessionEndDate(request.getParameter("toSession"));
		input.setStatus("ACT");

		List<ResultStatisticsInfo> pBSInfo = consolidatedChartService.getPBSDetails(input);
		List<ResultStatisticsInfo> outputList = new ArrayList<ResultStatisticsInfo>();
		for (int i = 0; i < pBSInfo.size(); i++) {
			ResultStatisticsInfo tempInput = new ResultStatisticsInfo();
			tempInput.setEntityId(pBSInfo.get(i).getEntityId());
			tempInput.setProgramId(pBSInfo.get(i).getProgramId());
			tempInput.setBranchId(pBSInfo.get(i).getBranchId());
			tempInput.setSpecializationId(pBSInfo.get(i).getSpecializationId());
			tempInput.setSemesterId(pBSInfo.get(i).getSemesterId());
			tempInput.setProgramCourseKey(pBSInfo.get(i).getProgramCourseKey());
			tempInput.setSemesterStartDate(pBSInfo.get(i).getSemesterStartDate());
			tempInput.setSemesterEndDate(pBSInfo.get(i).getSemesterEndDate());
			tempInput.setSessionStartDate(input.getSessionStartDate());
			tempInput.setSessionEndDate(input.getSessionEndDate());
			tempInput.setSemesterWise(input.getSemesterWise());
			tempInput.setStatus("%");
			tempInput.setAttemptNo("%");

			ResultStatisticsInfo outputMale = new ResultStatisticsInfo();
			outputMale.setEntityId(pBSInfo.get(i).getEntityId());
			outputMale.setEntityName(pBSInfo.get(i).getEntityName());
			outputMale.setProgramId(pBSInfo.get(i).getProgramId());
			outputMale.setProgramName(pBSInfo.get(i).getProgramName());
			outputMale.setBranchId(pBSInfo.get(i).getBranchId());
			outputMale.setBranchName(pBSInfo.get(i).getBranchName());
			outputMale.setSpecializationId(pBSInfo.get(i).getSpecializationId());
			outputMale.setSpecializationName(pBSInfo.get(i).getSpecializationName());
			outputMale.setSemesterId(pBSInfo.get(i).getSemesterId());
			outputMale.setSemesterName(pBSInfo.get(i).getSemesterName());
			outputMale.setProgramCourseKey(pBSInfo.get(i).getProgramCourseKey());
			outputMale.setGender("MALES");

			tempInput.setGender("M");
			String count = consolidatedChartService.getStudentCount(tempInput);
			outputMale.setEnrolled(count);

			tempInput.setStatus("REG");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputMale.setAppeared(String.valueOf(Integer.parseInt(outputMale.getEnrolled()) - Integer.parseInt(count)));

			tempInput.setStatus("UFM");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputMale.setUfm(count);

			tempInput.setStatus("PAS");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputMale.setPassed(count);

			tempInput.setStatus("REM");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputMale.setRemedial(count);

			tempInput.setStatus("INC");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputMale.setIncomplete(count);

			tempInput.setStatus("FAL");
			tempInput.setAttemptNo("1");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputMale.setFailedAt1(count);

			tempInput.setAttemptNo("2");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputMale.setFailedAt2(count);

			Double passPercentage = 100 * Double.parseDouble(outputMale.getPassed())/ Double.parseDouble(outputMale.getAppeared());
			
			outputMale.setPassPercentage(String.valueOf(passPercentage));

			tempInput.setCgpaU("10");
			tempInput.setCgpaL("8.5");
			count = consolidatedChartService.getStudentCountByDivision(tempInput);
			outputMale.setFstDic(count);

			tempInput.setCgpaU("8.49");
			tempInput.setCgpaL("6.0");
			count = consolidatedChartService.getStudentCountByDivision(tempInput);
			outputMale.setFstDiv(count);

			tempInput.setCgpaU("5.99");
			tempInput.setCgpaL("4.5");
			count = consolidatedChartService.getStudentCountByDivision(tempInput);
			outputMale.setScndDiv(count);

			ResultStatisticsInfo outputFemale = new ResultStatisticsInfo();
			outputFemale.setEntityId(pBSInfo.get(i).getEntityId());
			outputFemale.setEntityName(pBSInfo.get(i).getEntityName());
			outputFemale.setProgramId(pBSInfo.get(i).getProgramId());
			outputFemale.setProgramName(pBSInfo.get(i).getProgramName());
			outputFemale.setBranchId(pBSInfo.get(i).getBranchId());
			outputFemale.setBranchName(pBSInfo.get(i).getBranchName());
			outputFemale.setSpecializationId(pBSInfo.get(i).getSpecializationId());
			outputFemale.setSpecializationName(pBSInfo.get(i).getSpecializationName());
			outputFemale.setSemesterId(pBSInfo.get(i).getSemesterId());
			outputFemale.setSemesterName(pBSInfo.get(i).getSemesterName());
			outputFemale.setProgramCourseKey(pBSInfo.get(i).getProgramCourseKey());
			outputFemale.setGender("FEMALES");
			tempInput.setStatus("%");
			tempInput.setAttemptNo("%");
			tempInput.setGender("F");

			count = consolidatedChartService.getStudentCount(tempInput);
			outputFemale.setEnrolled(count);

			tempInput.setStatus("REG");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputFemale.setAppeared(String.valueOf(Integer.parseInt(outputFemale.getEnrolled()) - Integer.parseInt(count)));

			tempInput.setStatus("UFM");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputFemale.setUfm(count);

			tempInput.setStatus("PAS");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputFemale.setPassed(count);

			tempInput.setStatus("REM");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputFemale.setRemedial(count);

			tempInput.setStatus("INC");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputFemale.setIncomplete(count);

			tempInput.setStatus("FAL");
			tempInput.setAttemptNo("1");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputFemale.setFailedAt1(count);

			tempInput.setAttemptNo("2");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputFemale.setFailedAt2(count);

			passPercentage = 100 * Double.parseDouble(outputFemale.getPassed()) / Double.parseDouble(outputFemale.getAppeared());
			outputFemale.setPassPercentage(String.valueOf(passPercentage));

			tempInput.setCgpaU("10");
			tempInput.setCgpaL("8.5");
			count = consolidatedChartService.getStudentCountByDivision(tempInput);
			outputFemale.setFstDic(count);

			tempInput.setCgpaU("8.49");
			tempInput.setCgpaL("6.0");
			count = consolidatedChartService.getStudentCountByDivision(tempInput);
			outputFemale.setFstDiv(count);

			tempInput.setCgpaU("5.99");
			tempInput.setCgpaL("4.5");
			count = consolidatedChartService.getStudentCountByDivision(tempInput);
			outputFemale.setScndDiv(count);

			ResultStatisticsInfo outputTotal = new ResultStatisticsInfo();
			outputTotal.setEntityId(pBSInfo.get(i).getEntityId());
			outputTotal.setEntityName(pBSInfo.get(i).getEntityName());
			outputTotal.setProgramId(pBSInfo.get(i).getProgramId());
			outputTotal.setProgramName(pBSInfo.get(i).getProgramName());
			outputTotal.setBranchId(pBSInfo.get(i).getBranchId());
			outputTotal.setBranchName(pBSInfo.get(i).getBranchName());
			outputTotal.setSpecializationId(pBSInfo.get(i).getSpecializationId());
			outputTotal.setSpecializationName(pBSInfo.get(i).getSpecializationName());
			outputTotal.setSemesterId(pBSInfo.get(i).getSemesterId());
			outputTotal.setSemesterName(pBSInfo.get(i).getSemesterName());
			outputTotal.setProgramCourseKey(pBSInfo.get(i).getProgramCourseKey());
			outputTotal.setGender("TOTAL");
			tempInput.setStatus("%");
			tempInput.setAttemptNo("%");
			tempInput.setGender("%");

			count = consolidatedChartService.getStudentCount(tempInput);
			outputTotal.setEnrolled(count);

			tempInput.setStatus("REG");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputTotal.setAppeared(String.valueOf(Integer.parseInt(outputTotal.getEnrolled()) - Integer.parseInt(count)));

			tempInput.setStatus("UFM");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputTotal.setUfm(count);

			tempInput.setStatus("PAS");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputTotal.setPassed(count);

			tempInput.setStatus("REM");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputTotal.setRemedial(count);

			tempInput.setStatus("INC");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputTotal.setIncomplete(count);

			tempInput.setStatus("FAL");
			tempInput.setAttemptNo("1");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputTotal.setFailedAt1(count);

			tempInput.setAttemptNo("2");
			count = consolidatedChartService.getStudentCount(tempInput);
			outputTotal.setFailedAt2(count);

			passPercentage = 100 * Double.parseDouble(outputTotal.getPassed()) / Double.parseDouble(outputTotal.getAppeared());
			outputTotal.setPassPercentage(String.valueOf(passPercentage));

			tempInput.setCgpaU("10");
			tempInput.setCgpaL("8.5");
			count = consolidatedChartService.getStudentCountByDivision(tempInput);
			outputTotal.setFstDic(count);

			tempInput.setCgpaU("8.49");
			tempInput.setCgpaL("6.0");
			count = consolidatedChartService.getStudentCountByDivision(tempInput);
			outputTotal.setFstDiv(count);

			tempInput.setCgpaU("5.99");
			tempInput.setCgpaL("4.5");
			count = consolidatedChartService.getStudentCountByDivision(tempInput);
			outputTotal.setScndDiv(count);

			outputList.add(outputMale);
			outputList.add(outputFemale);
			outputList.add(outputTotal);
		}
		outputList.add(input);
		try{
			System.out.println("before calling generator");
			return new ModelAndView("resultStatisticsGenerator", "outputList",outputList);
		}catch(Exception e){
			System.out.println("inside the exception of generator calling "+e);
			return new ModelAndView("activitymaster/SubmitSuccesful", "message", "true");
		}		
	}

	public ModelAndView generateRegistrationStatisticsReport(
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		ResultStatisticsInfo inputBean = new ResultStatisticsInfo();
		inputBean.setUniversityId(session.getAttribute("universityId").toString());
		inputBean.setSemesterWise(Integer.parseInt(request.getParameter("semesterWise")));
		inputBean.setSessionStartDate(request.getParameter("fromSession"));
		inputBean.setSessionEndDate(request.getParameter("toSession"));

		List<ResultStatisticsInfo> entityLevelList = consolidatedChartService.getEntityLevelList(inputBean);

		Boolean status = generateRegistrationStats(request,entityLevelList, inputBean);
		if(status){
			return new ModelAndView("activitymaster/SubmitSuccesful", "message", "true");
//			return new ModelAndView("systemtabletwo/countInfo", "count", status);
		}
		else{
			String message = "false-There is some error in PDF Generation kindly view the error log for more information";
			return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
//			return new ModelAndView("systemtabletwo/countInfo", "count", status);
		}	
		
		
	}

	public Boolean generateRegistrationStats(HttpServletRequest request, List<ResultStatisticsInfo> entityLevelList,ResultStatisticsInfo inputBean) {
		Boolean status = false;
		String logResult="";		
		int reportGenerated = 0;
		HttpSession session=request.getSession(true);
		
		/*String filePath = this.getServletContext().getRealPath(File.separator)
				+ File.separator + "RegistrationStatistics" + File.separator
				+ inputBean.getUniversityId();*/
		String reportSession = inputBean.getSessionStartDate().substring(0, 4)
				+ "-" + inputBean.getSessionEndDate().substring(0, 4);
		try {
			int semType = Integer.parseInt(request.getParameter("semesterWise"));
			String semesterType = semType==0?"Even semester":"Odd Semester";
			String sep = System.getProperty("file.separator");
			//*********nupur code *******************
			ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
					request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
					request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),semesterType);
			
			String reportPath = ReportPath.getPath(reportPathBean);				
			System.out.println("here in controller of consolidated chart after path generation "+reportPath);
			String filePath = this.getServletContext().getRealPath("/");
			filePath=filePath+reportPath;
			File file = new File(filePath);
			file.mkdirs();
			//***************************************
			
			Document document = new Document(PageSize.A4.rotate());
//			new File(filePath).mkdirs();
			String oddEven = "Even-Semester";
			if (inputBean.getSemesterWise() % 2 == 1) {
				oddEven = "Odd-Semester";
			}

			/*filePath += File.separator + "Revised-Registration-Statistics-"
					+ oddEven + "-For-" + reportSession + ".pdf";*/
			PdfWriter.getInstance(document, new FileOutputStream(filePath+sep+request.getParameter("reportDescription")+".pdf"));
//			PdfWriter.getInstance(document, new FileOutputStream(filePath));

			Paragraph universityInfo = new Paragraph(
					"REVISED REGISTRATION STATISTICS (UG/PG/PD/DH/DI/HS/IS/IA/... & CATEGORY WISE) : "
							+ reportSession
							+ " ("
							+ oddEven.replace('-', ' ').toUpperCase() + ")",
					FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,
							new Color(0, 0, 0)));

			PdfPTable headerTable = new PdfPTable(new float[] { 4, 2, 2, 2, 2,
					3, 2, 2, 2, 2, 3, 2, 2, 2, 2, 3 });
			headerTable.setWidthPercentage(100);
			headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell = new PdfPCell(new Phrase(" F - Level"));
			cell.setColspan(1);
			cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			headerTable.addCell(cell);

			cell = new PdfPCell(new Phrase(
					"< - - - - - - - - - - MALES - - - - - - - - - - >"));
			cell.setColspan(5);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerTable.addCell(cell);

			cell = new PdfPCell(new Phrase(
					"< - - - - - - - - - - FEMALES - - - - - - - - - - >"));
			cell.setColspan(5);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerTable.addCell(cell);

			cell = new PdfPCell(new Phrase(
					"< - - - - - - - - - - TOTAL - - - - - - - - - - >"));
			cell.setColspan(5);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerTable.addCell(cell);

			headerTable.addCell(new Phrase(""));

			for (int i = 0; i < 3; i++) {
				cell = new PdfPCell(new Phrase("GN"));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerTable.addCell(cell);

				cell = new PdfPCell(new Phrase("BC"));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerTable.addCell(cell);

				cell = new PdfPCell(new Phrase("SC"));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerTable.addCell(cell);

				cell = new PdfPCell(new Phrase("ST"));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerTable.addCell(cell);

				cell = new PdfPCell(new Phrase("TOTAL"));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerTable.addCell(cell);
			}

			PdfPTable contentTable = new PdfPTable(new float[] { 4, 2, 2, 2, 2,
					3, 2, 2, 2, 2, 3, 2, 2, 2, 2, 3 });
			contentTable.setWidthPercentage(100);
			contentTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			List<String> tempList = new ArrayList<String>();

			for (int j = 0; j <= entityLevelList.size(); j++) {
				String entityId = "";
				if (j < entityLevelList.size()) {
					entityId = entityLevelList.get(j).getEntityId();
				}
				if (tempList.indexOf(entityId) < 0) {
					tempList.add(entityId);
					if (j > 0) {
						inputBean.setEntityId(entityLevelList.get(j - 1)
								.getEntityId());
						inputBean.setLevel("%");
						contentTable.addCell(new Phrase(entityLevelList.get(
								j - 1).getEntityCode()
								+ "-Faculty"));

						inputBean.setGender("M");
						inputBean.setCategory("GN");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));
						inputBean.setCategory("BC");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));
						inputBean.setCategory("SC");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));
						inputBean.setCategory("ST");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));
						inputBean.setCategory("%");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));

						inputBean.setGender("F");
						inputBean.setCategory("GN");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));
						inputBean.setCategory("BC");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));
						inputBean.setCategory("SC");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));
						inputBean.setCategory("ST");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));
						inputBean.setCategory("%");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));

						inputBean.setGender("%");
						inputBean.setCategory("GN");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));
						inputBean.setCategory("BC");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));
						inputBean.setCategory("SC");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));
						inputBean.setCategory("ST");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));
						inputBean.setCategory("%");
						contentTable
								.addCell(new Phrase(
										consolidatedChartService
												.getStudentCountByCategoryAndGender(inputBean)));
					}
				}
				
				if (j < entityLevelList.size()) {
					inputBean.setEntityId(entityLevelList.get(j).getEntityId());
					inputBean.setLevel(entityLevelList.get(j).getLevel());
					contentTable.addCell(new Phrase(entityLevelList.get(j)
							.getEntityCode()
							+ "-"
							+ entityLevelList.get(j).getLevel()));

					inputBean.setGender("M");
					inputBean.setCategory("GN");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));
					inputBean.setCategory("BC");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));
					inputBean.setCategory("SC");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));
					inputBean.setCategory("ST");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));
					inputBean.setCategory("%");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));

					inputBean.setGender("F");
					inputBean.setCategory("GN");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));
					inputBean.setCategory("BC");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));
					inputBean.setCategory("SC");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));
					inputBean.setCategory("ST");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));
					inputBean.setCategory("%");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));

					inputBean.setGender("%");
					inputBean.setCategory("GN");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));
					inputBean.setCategory("BC");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));
					inputBean.setCategory("SC");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));
					inputBean.setCategory("ST");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));
					inputBean.setCategory("%");
					contentTable.addCell(new Phrase(consolidatedChartService
							.getStudentCountByCategoryAndGender(inputBean)));
				}
			}

			Phrase headerPhrase = new Phrase();
			headerPhrase.add(universityInfo);
			headerPhrase.add(headerTable);
			headerPhrase.add("\n");

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
			status = true;
		} catch (Exception e) {
			status = false;			
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
			if(status){
				reportGenerated = reportGenerated+1;
				ReportLogBean reportControlBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
						request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
						request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
						request.getParameter("reportCode"),String.valueOf(reportGenerated),"No",
						request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterWise"),
						session.getAttribute("userName").toString());
				reportControlBean.setIsGenerated(request.getParameter("isGenerated")==null?"no":"yes");			
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