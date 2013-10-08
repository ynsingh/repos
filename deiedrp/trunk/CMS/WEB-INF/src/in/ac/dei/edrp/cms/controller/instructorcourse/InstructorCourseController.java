/**
 * @(#) InstructorCourseController.java
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
package in.ac.dei.edrp.cms.controller.instructorcourse;
import in.ac.dei.edrp.cms.dao.instructorcourse.InstructorCourseDao;
import in.ac.dei.edrp.cms.dao.manualprocess.ManualProcess;
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import in.ac.dei.edrp.cms.domain.instructorcourse.InstructorCourseBean;
import in.ac.dei.edrp.cms.domain.resultverification.ResultVerificationBean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This controller is designed for retrieving
 * the details of students registered for the login instructor's courses
 * @author Nupur Dixit
 * @date 8 Dec 2012
 * @version 1.0
 */

public class InstructorCourseController extends MultiActionController{
	
	private InstructorCourseDao instructorCourseDao;
	/** defining setter method for object of manualProcessDao interface */
	public void setInstructorCourseDao(InstructorCourseDao instructorCourseDao) {
		this.instructorCourseDao = instructorCourseDao;
	}
	
	 /**
     *  Method to get the list of courses of the instructor
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */

    public ModelAndView getCourseList(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inside controller");
		 List<AwardSheetInfoGetter> courseList=new ArrayList<AwardSheetInfoGetter>();
		try{
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		System.out.println("inside controller");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}    	
		AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
//        inputObj.setEntityId(session.getAttribute("userId").toString().substring(1,9));
        inputObj.setCreatorId(session.getAttribute("userId").toString());
        inputObj.setUniversityId(session.getAttribute("userId").toString().substring(1, 5));        
//Change Done By Dheeraj For Allowing Access To Examination Dept. For Entering External And Remedial Marks
//        inputObj.setDisplayType(request.getParameter("displayType"));
        courseList = instructorCourseDao.getCourseList(inputObj);       
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("error in controller :"+e.getMessage());
		}
		 return new ModelAndView("awardsheet/CourseList", "result", courseList);
    }

    /**
     *  Method to get the list of student on the basis of the selected course
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getStudentList(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inside controller");
		List<InstructorCourseBean> studentList=new ArrayList<InstructorCourseBean>();
		try{
	    	HttpSession session = request.getSession(true);
			String universityId =(String) session.getAttribute("universityId");
			if(universityId == null){
				return new ModelAndView("general/SessionInactive","sessionInactive",true);
			}    	
			InstructorCourseBean inputObj = new InstructorCourseBean();
	//        inputObj.setEntityId(session.getAttribute("userId").toString().substring(1,9));
			inputObj.setCreatorId(session.getAttribute("userId").toString());
        	inputObj.setUniversityId((String) session.getAttribute("universityId"));
        	inputObj.setProgramId(request.getParameter("programId"));
        	inputObj.setBranchId(request.getParameter("branchId"));
        	inputObj.setSpecializationId(request.getParameter("specializationId"));
        	inputObj.setSemesterCode(request.getParameter("semesterCode"));
        	inputObj.setSemesterStartDate(request.getParameter("semesterStartDate"));
        	inputObj.setSemesterEndDate(request.getParameter("semesterEndDate"));
        	inputObj.setCourseCode(request.getParameter("courseCode"));
        	studentList = instructorCourseDao.getStudentList(inputObj);       
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("error in controller :"+e.getMessage());
		}
		 return new ModelAndView("instructorcourse/InstructorCourse", "result", studentList);
    }

	/**
	 * This method set values in result verification request header and  detail
	 * @param request
	 * @param response
	 * @return ModelAndView containing info (success/Failure)
	 */               

	public ModelAndView getRequestSearchService(HttpServletRequest request,HttpServletResponse response)throws Exception {
		System.out.println("inside getRequestSearchService controller");		
		HttpSession session=request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		String universityId = (String)session.getAttribute("universityId");
		ResultVerificationBean resultVerificationBean = new ResultVerificationBean();
		resultVerificationBean.setUniversityId(universityId);
		resultVerificationBean.setRequestType(request.getParameter("requestType"));
		resultVerificationBean.setCompName((request.getParameter("companyName")==""?"%":("%"+request.getParameter("companyName")+"%")));
		resultVerificationBean.setReceiveDate((request.getParameter("receiveDate")==""?null:request.getParameter("receiveDate")));		
		System.out.println("receive date comp name "+resultVerificationBean.getReceiveDate()+" : "+resultVerificationBean.getCompName());
		List<ResultVerificationBean> requestList = new ArrayList<ResultVerificationBean>();		
//		requestList = resultVerificationDao.getRequestHeader(resultVerificationBean);
//		System.out.println("request list size "+requestList.size());
		return new ModelAndView("resultVerification/ResultVerification", "resultObject", requestList);
	}
		
	
}
