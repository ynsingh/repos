/*
 * @(#) StudentRemedialController.java
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
package in.ac.dei.edrp.cms.controller.studentmarkssummary;

/**
 * This controller is designed for retrieving information about
 * the student 
 * @author Nupur
 * @date 10 Jan 2011
 * @version 1.0
 */

import in.ac.dei.edrp.cms.dao.studentmarkssummary.StudentMarksSummaryDao;
import in.ac.dei.edrp.cms.daoimpl.studentmarkssummary.StudentMarksSummaryImpl;
import in.ac.dei.edrp.cms.domain.studentmarkssummary.StudentMarksSummaryBean;
import in.ac.dei.edrp.cms.domain.studentremedial.StudentRemedialInfoGetter;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class StudentMarksSummary extends MultiActionController {

	/** creating object of studentRemedialConnect */
	private StudentMarksSummaryDao studentMarksSummaryDao;

	/**
	 * The setter method of the interface associated with this controller
	 */
	public void setStudentMarksSummaryDao(StudentMarksSummaryDao studentMarksSummaryDao) {
		this.studentMarksSummaryDao = studentMarksSummaryDao;
	}

	private static Logger logObj = Logger.getLogger(StudentMarksSummary.class);
	/**
	 * Method for fetching roll numbers of the student on the basis of the enrollment number
	 * @param request
	 * @param response
	 * @return Model View containing student roll number detail
	 */
	
	public ModelAndView getStudentRollNumber(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inside controller");
		HttpSession session = request.getSession();
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}		
		StudentMarksSummaryBean input = new StudentMarksSummaryBean();
//		input.setEnrollmentNumber("110098");
//		input.setUniversityId("0001");
//		input.setEnrollmentNumber(request.getParameter("enrollmentNumber"));
		input.setUserId(session.getAttribute("userId").toString());
		input.setUniversityId(session.getAttribute("universityId").toString());
		System.out.println(input.getUniversityId());
		System.out.println(input.getUserId());		 
		List<StudentMarksSummaryBean> rollNumberDetails = new ArrayList<StudentMarksSummaryBean>();
		try {
			rollNumberDetails = studentMarksSummaryDao.getStudentRollNumber(input);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return new ModelAndView("studentMarksSummary/StudentMarksSummary","resultObject", rollNumberDetails);
	}

	/**
	 * Method for fetching roll numbers of the student on the basis of the enrollment number
	 * @param request
	 * @param response
	 * @return Model View containing student roll number detail
	 */
	
	public ModelAndView getSemesterDetail(HttpServletRequest request,HttpServletResponse response) {		
		HttpSession session = request.getSession();
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}		
		StudentMarksSummaryBean input = new StudentMarksSummaryBean();		
		input.setRollNumber(request.getParameter("rollNumber"));
		input.setProgramId(request.getParameter("programId"));
		input.setBranchId(request.getParameter("branchId"));
		input.setSpecializationId(request.getParameter("specializationId"));
		input.setUniversityId(session.getAttribute("universityId").toString());		
		List<StudentMarksSummaryBean> semesterDetails = new ArrayList<StudentMarksSummaryBean>();
		try {
			semesterDetails = studentMarksSummaryDao.getSemesterDetail(input);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return new ModelAndView("studentMarksSummary/StudentMarksSummary","resultObject", semesterDetails);
	}

	/**
	 * Method for fetching courses detail on the basis of the selected roll number
	 * @param request
	 * @param response
	 * @return Model View containing courses list for particular program/branch/specialization
	 */
	
	public ModelAndView getCourses(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inside controller");
		HttpSession session = request.getSession();
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}		
		StudentMarksSummaryBean input = new StudentMarksSummaryBean();
		input.setProgramCourseKey(request.getParameter("programCourseKey"));
		input.setRollNumber(request.getParameter("rollNumber"));		
		input.setUniversityId(session.getAttribute("universityId").toString());		
		List<StudentMarksSummaryBean> coursesDetails = new ArrayList<StudentMarksSummaryBean>();
		try {
			coursesDetails = studentMarksSummaryDao.getCourses(input);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return new ModelAndView("studentMarksSummary/StudentMarksSummary","resultObject", coursesDetails);
	}

	/**
	 * Method for fetching marks detail on the basis of the selected course
	 * @param request
	 * @param response
	 * @return Model View containing courses list for particular program/branch/specialization
	 */
	
	public ModelAndView getMarksDetail(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inside controller");
		HttpSession session = request.getSession();
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}		
		StudentMarksSummaryBean input = new StudentMarksSummaryBean();

		input.setCourseCode(request.getParameter("courseCode"));
		input.setRollNumber(request.getParameter("rollNumber"));		
		input.setProgramCourseKey(request.getParameter("programCourseKey"));
		input.setUniversityId(session.getAttribute("universityId").toString());
		input.setEntityId(request.getParameter("entityCode"));
		input.setSemesterStartDate(request.getParameter("semesterStartDate")); 
		input.setSemesterEndDate(request.getParameter("semesterEndDate"));
		List<StudentMarksSummaryBean> marksDetails = new ArrayList<StudentMarksSummaryBean>();
		StudentMarksSummaryBean studentMarksSummaryBean = new StudentMarksSummaryBean();
		try {
			marksDetails = studentMarksSummaryDao.getMarksDetails(input);
			if(marksDetails.size()==0){
				studentMarksSummaryBean.setMessage("No marks Detail is available");
				marksDetails.add(studentMarksSummaryBean);
				return new ModelAndView("studentMarksSummary/StudentMarksSummary","resultObject", marksDetails);
			}
			else{
				return new ModelAndView("studentMarksSummary/StudentMarksSummary","resultObject", marksDetails);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return new ModelAndView("studentMarksSummary/StudentMarksSummary","resultObject", marksDetails);
	}

	
	/**
	 * Method for fetching semester summary info of the student
	 * 
	 * @param request
	 * @param response
	 * @return Model View containing student Details	 
	 */
	public ModelAndView getSemesterSummary(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inside controller");
		HttpSession session = request.getSession();
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}		
		StudentMarksSummaryBean input = new StudentMarksSummaryBean();
//		input.setRollNumber("1111002");
//		input.setUniversityId("0001");		
		input.setRollNumber(request.getParameter("rollNumber"));		
		input.setProgramCourseKey(request.getParameter("programCourseKey"));
		input.setUniversityId(session.getAttribute("universityId").toString());
		System.out.println(input.getUniversityId());
		System.out.println(input.getRollNumber());		 
		List<StudentMarksSummaryBean> semesterSummaryDetail = new ArrayList<StudentMarksSummaryBean>();
		StudentMarksSummaryBean studentMarksSummaryBean = new StudentMarksSummaryBean();
		try {
			semesterSummaryDetail = studentMarksSummaryDao.getSemesterSummaryDetail(input);
			if(semesterSummaryDetail.size()==0){
				studentMarksSummaryBean.setTheoryCgpa("Not Available");
				studentMarksSummaryBean.setPracticalCgpa("Not Available");
				studentMarksSummaryBean.setTheorySgpa("Not Available");
				studentMarksSummaryBean.setPracticalSgpa("Not Available");
				studentMarksSummaryBean.setCgpa("Not Available");
				studentMarksSummaryBean.setSgpa("Not Available");
				semesterSummaryDetail.add(studentMarksSummaryBean);
				return new ModelAndView("studentMarksSummary/StudentMarksSummary","resultObject", semesterSummaryDetail);
			}
			else{
				return new ModelAndView("studentMarksSummary/StudentMarksSummary","resultObject", semesterSummaryDetail);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return new ModelAndView("studentMarksSummary/StudentMarksSummary","resultObject", semesterSummaryDetail);
	}
	
	/**
	 * Method for fetching path info for progress card of the student
	 * 
	 * @param request
	 * @param response
	 * @return Model View containing student Details	 
	 */
	public ModelAndView getPathProgressCard(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inside path progress card");
		HttpSession session = request.getSession();
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		StudentMarksSummaryBean input = new StudentMarksSummaryBean();
		input.setRollNumber(request.getParameter("rollNumber"));		
		input.setProgramCourseKey(request.getParameter("programCourseKey"));
		input.setUniversityId(session.getAttribute("universityId").toString());
		input.setSemesterStartDate(request.getParameter("semesterStartDate"));
		input.setSemesterEndDate(request.getParameter("semesterEndDate"));
		input.setProgramId(request.getParameter("programId"));
		input.setBranchId(request.getParameter("branchId"));
		input.setSpecializationId(request.getParameter("specializationId"));
		input.setSemesterCode(request.getParameter("semesterCode"));
		String path = null;
		try{
			StudentMarksSummaryBean parameterBean = (StudentMarksSummaryBean)studentMarksSummaryDao.getPathParameters(input);			
			input.setEntityId(parameterBean.getEntityId());
			input.setSessionStartDate(parameterBean.getSessionStartDate());
			input.setSessionEndDate(parameterBean.getSessionEndDate());			
			String reportType = request.getParameter("reportType");
			String reportCode = request.getParameter("reportCode");
			ReportPathBean reportPathBean = new ReportPathBean(input.getUniversityId(), input.getEntityId(), input.getProgramId(), input.getBranchId(),
					input.getSpecializationId(), input.getSemesterCode(), input.getSemesterStartDate(), input.getSemesterEndDate(),
					input.getEnrollmentNumber(), input.getRollNumber(), reportCode, reportType, input.getSessionStartDate(), input.getSessionEndDate(),"");
			path = ReportPath.getPath(reportPathBean);
			System.out.println("before concatenation path is "+path);
			path = path + "8-Progress-Result Card-"+input.getRollNumber()+".pdf";
			String initialPath = getServletContext().getRealPath("/");
			path = initialPath+path;
			System.out.println("After concatenation path is "+path);
		    File fileVerify = new File(path);
		    if(fileVerify.exists()){
		    	System.out.println("yes file exist now show "+path);
		    }
		    else{
		    	System.out.println("The Report is not yet Generated....");
		    	path = "false-Sorry the Progress Card is not yet Generated......";
//		    	return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);	
		    }			
		}catch (Exception e){
			logObj.error(e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("general/ReportPath","path", path);			
	}
	
	
	
	/**
	 * Method for setting request of the student for marks correction
	 * @author Ashish Mohan
	 * @param request
	 * @param response
	 * @return Model View containing status of request	 
	 */
	public ModelAndView setMarksCorrectionRequest(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		StudentMarksSummaryBean input = new StudentMarksSummaryBean();
		input.setRollNumber(request.getParameter("rollNumber"));		
		input.setProgramCourseKey(request.getParameter("programCourseKey"));
		input.setUniversityId(session.getAttribute("universityId").toString());
		input.setSessionStartDate(session.getAttribute("startDate").toString());
		input.setSessionEndDate(session.getAttribute("endDate").toString());
		input.setSemesterStartDate(request.getParameter("semStartDate"));
		input.setSemesterEndDate(request.getParameter("semEndDate"));
		input.setEntityId(request.getParameter("entityCode"));
		input.setCourseCode(request.getParameter("courseCode"));
		input.setEvaluationId(request.getParameter("evaluationId"));
		input.setMarks(request.getParameter("correctMarks"));
		input.setStatus(request.getParameter("studentRemark").toUpperCase());// student remark

		
		
		String message=studentMarksSummaryDao.setCorrectionRequest(input);
		return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
	}
}
