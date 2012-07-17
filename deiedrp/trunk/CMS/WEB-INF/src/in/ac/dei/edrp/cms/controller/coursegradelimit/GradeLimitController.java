/**
 * @(#) GradeLimitController.java
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
 * Redistribution in binary form must reproducuce the above copyright
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
package in.ac.dei.edrp.cms.controller.coursegradelimit;
import in.ac.dei.edrp.cms.dao.coursegradelimit.*;
import in.ac.dei.edrp.cms.domain.coursegradelimit.*;
import in.ac.dei.edrp.cms.domain.externalexaminercourse.ExternalExaminerCourseInfoGetter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for course grade limit
 *
 * @version 1.0 
 * @date 27 FEB 2012
 * @author Ashish Mohan
 */
public class GradeLimitController extends MultiActionController {
	
	/** creating object of  interface */
	private GradeLimitDao gradeLimitDao;

	/** defining setter method for object of  interface */
	public void setGradeLimitDao(GradeLimitDao gradeLimitDao) {
		this.gradeLimitDao = gradeLimitDao;
	}

	/**
	 * This method get list of course  from database and map to a jsp
	 * @author Ashish Mohan
	 * @param request
	 * @param response
	 * @return ModelAndView containing course List
	 */
	public ModelAndView getCourseDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		GradeLimitDomain input=new GradeLimitDomain();

		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());
		input.setEmployeeId(session.getAttribute("userName").toString());
		if(request.getParameter("courseCode")==null){
			input.setCourseCode("");
		}
		else{
			input.setCourseCode(request.getParameter("courseCode"));
		}
		
		List<GradeLimitDomain> courses = gradeLimitDao.getCourseDetails(input);

		return new ModelAndView("coursegradelimit/gradeLimit", "detailsList",courses);
	}
	

	/**
	 * This method get list of course with grade limit from database and map to a jsp
	 * @author Ashish Mohan
	 * @param request
	 * @param response
	 * @return ModelAndView containing course List
	 */
	public ModelAndView getCourseGradeLimit(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		GradeLimitDomain input=new GradeLimitDomain();

		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());
		input.setEmployeeId(session.getAttribute("userName").toString());
		input.setDisplayType(request.getParameter("displayType"));
		if(request.getParameter("courseCode")==null){
			input.setCourseCode("");
		}
		else{
			input.setCourseCode(request.getParameter("courseCode"));
		}

		List<GradeLimitDomain> courses = gradeLimitDao.getCourseGradeDetails(input);

		return new ModelAndView("coursegradelimit/gradeLimit", "detailsList",courses);
	}
	
	/**
	 * This method get list of  grade and exam type from database and map to a jsp
	 * @author Ashish Mohan
	 * @param request
	 * @param response
	 * @return ModelAndView containing  List
	 */
	public ModelAndView getDisplayType(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		

		GradeLimitDomain input=new GradeLimitDomain();
		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());
		input.setUniversityCode(session.getAttribute("universityId").toString());
		
		List<GradeLimitDomain> exam = gradeLimitDao.getExamDetails(input);
		
		GradeLimitDomain gradeLimitDomain=new GradeLimitDomain();
       	gradeLimitDomain.setStartDate(input.getStartDate());
       	gradeLimitDomain.setEndDate(input.getEndDate());

		exam.add(gradeLimitDomain);

		return new ModelAndView("coursegradelimit/gradeLimit", "detailsList",exam);
	}
	
	
	/**
	 * This method insert course grade to database and map result to a jsp
	 * @author Ashish Mohan
	 * @param request
	 * @param response
	 * @return ModelAndView containing  List
	 */
	public ModelAndView insertCourseGrade(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		GradeLimitDomain input=new GradeLimitDomain();
		String result="";
		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());
		input.setUserId(session.getAttribute("userId").toString());
		input.setCourseCode(request.getParameter("courseCode"));
		input.setDisplayType(request.getParameter("displayType"));
		input.setInternalActive(request.getParameter("internalActive"));
		input.setMarksEndSemester(request.getParameter("marksEndSem"));
		input.setTotalMarks(request.getParameter("totalMarks"));
		
		StringTokenizer lowerTokenizer = new StringTokenizer(request.getParameter("lowers"), "\\|");
		StringTokenizer gradeTokenizer = new StringTokenizer(request.getParameter("grades"), "\\|");
		List<String> grades=new ArrayList<String>();
		List<String> lowers=new ArrayList<String>();
		List<String> uppers=new ArrayList<String>();
		while (gradeTokenizer.hasMoreTokens()) {
			grades.add(gradeTokenizer.nextToken());
			lowers.add(lowerTokenizer.nextToken());
		}
		
		// code start for finding upper cut point
		int externalMarks=Integer.parseInt(input.getMarksEndSemester());
		int totalMarks=Integer.parseInt(input.getTotalMarks());
		int internalMarks=totalMarks-externalMarks;
		
		if(grades.get(0).equalsIgnoreCase("A") && input.getDisplayType().equalsIgnoreCase("I") && input.getInternalActive().equalsIgnoreCase("1")){
				uppers.add(Integer.toString(internalMarks));
			}
		
		else if(grades.get(0).equalsIgnoreCase("A") && input.getDisplayType().equalsIgnoreCase("I") && input.getInternalActive().equalsIgnoreCase("0")){
			uppers.add(Integer.toString(totalMarks));
		}
			
		else if(grades.get(0).equalsIgnoreCase("A") && input.getDisplayType().equalsIgnoreCase("R")){
			uppers.add(Integer.toString(totalMarks));
			}
		
			
		else if(grades.get(0).equalsIgnoreCase("A") && input.getDisplayType().equalsIgnoreCase("E")){
			uppers.add(Integer.toString(externalMarks));
			}
			
        for (int i=0; i< lowers.size(); i++)
        {	
            uppers.add(i+1, Integer.toString(Integer.parseInt(lowers.get(i))-1));
        }
        // code ends for finding upper cut point	
        
        //set bean properties
        for (int i=0; i< lowers.size(); i++)	
        {
        	input.setGrade(grades.get(i));
			input.setMarksFrom(lowers.get(i));
			input.setMarksTo(uppers.get(i));
			result=gradeLimitDao.setCourseGradeDetails(input);
			
			//if error occurs in insert
			if(result.startsWith("e")){
				break;
			}
        }

		return new ModelAndView("preProcessChecks/preProcessResultlist","resultObject",result);
	}
		
		

	
	/**
	 * This method delete course grade from database and map result to a jsp
	 * @author Ashish Mohan
	 * @param request
	 * @param response
	 * @return ModelAndView containing  List
	 */
	public ModelAndView deleteCourseGradeLimit(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		GradeLimitDomain input=new GradeLimitDomain();
		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setUserId(session.getAttribute("userId").toString());
		input.setCourseCode(request.getParameter("courseCode"));
		input.setDisplayType(request.getParameter("displayType"));
		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());
		String	result =  gradeLimitDao.deleteCourseGradeDetails(input);
			
		return new ModelAndView("preProcessChecks/preProcessResultlist","resultObject",result);
	}
	
	
	/** Function for External,Remedial Grade Limit**/
	
	public ModelAndView getCourseDetailsExternal(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		GradeLimitDomain input=new GradeLimitDomain();

		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());
		input.setEmployeeId("external");
		input.setDisplayType(request.getParameter("displayType"));
		if(request.getParameter("courseCode")==null){
			input.setCourseCode("");
		}
		else{
			input.setCourseCode(request.getParameter("courseCode"));
		}
		
		List<GradeLimitDomain> courses = gradeLimitDao.getCourseDetails(input);

		return new ModelAndView("coursegradelimit/gradeLimit", "detailsList",courses);
	}
	
	/**
	 * This method get list of course with grade limit from database and map to a jsp
	 * @author Ashish Mohan
	 * @param request
	 * @param response
	 * @return ModelAndView containing course List
	 */
	public ModelAndView getCourseGradeLimitExternal(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		GradeLimitDomain input=new GradeLimitDomain();

		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());
		input.setEmployeeId("external");
		input.setDisplayType(request.getParameter("displayType"));
		if(request.getParameter("courseCode")==null){
			input.setCourseCode("");
		}
		else{
			input.setCourseCode(request.getParameter("courseCode"));
		}
		
		List<GradeLimitDomain> courses = gradeLimitDao.getCourseGradeDetails(input);

		return new ModelAndView("coursegradelimit/gradeLimit", "detailsList",courses);
	}
	
	/**
	 * This method insert course grade for external,remedial,core to database and map result to a jsp
	 * @author Ashish Mohan
	 * @param request
	 * @param response
	 * @return ModelAndView containing  List
	 */
	public ModelAndView insertCourseGradeExternal(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		GradeLimitDomain input=new GradeLimitDomain();
		String result="";
		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());
		input.setUserId(session.getAttribute("userId").toString());
		input.setDisplayType(request.getParameter("displayType"));
		input.setInternalActive(request.getParameter("internalActive"));
		input.setMarksEndSemester(request.getParameter("marksEndSem"));
		input.setTotalMarks(request.getParameter("totalMarks"));
		
		StringTokenizer lowerTokenizer = new StringTokenizer(request.getParameter("lowers"), "\\|");
		StringTokenizer gradeTokenizer = new StringTokenizer(request.getParameter("grades"), "\\|");
		List<String> grades=new ArrayList<String>();
		List<String> lowers=new ArrayList<String>();
		List<String> uppers=new ArrayList<String>();
		while (gradeTokenizer.hasMoreTokens()) {
			grades.add(gradeTokenizer.nextToken());
			lowers.add(lowerTokenizer.nextToken());
		}
		
		// code start for finding upper cut point
		int externalMarks=0;
		if(input.getDisplayType().equalsIgnoreCase("E")){
			externalMarks=Integer.parseInt(input.getMarksEndSemester());
		}
		int totalMarks=Integer.parseInt(input.getTotalMarks());
		
		
		if(grades.get(0).equalsIgnoreCase("A") && input.getDisplayType().equalsIgnoreCase("E")){
			uppers.add(Integer.toString(externalMarks));
		}
		else if(grades.get(0).equalsIgnoreCase("D") && input.getDisplayType().equalsIgnoreCase("R")){
			uppers.add(Integer.toString(totalMarks));
		}
		else if(grades.get(0).equalsIgnoreCase("A") && input.getDisplayType().equalsIgnoreCase("I")){
			uppers.add(Integer.toString(totalMarks));
		}
			
        for (int i=0; i< lowers.size(); i++)
        {	
            uppers.add(i+1, Integer.toString(Integer.parseInt(lowers.get(i))-1));
        }
        // code ends for finding upper cut point	
        
        StringTokenizer courseTokenizer = new StringTokenizer(request.getParameter("courseList"), "\\|");
        
        while (courseTokenizer.hasMoreTokens()) {
        	//set bean properties
        	input.setCourseCode(courseTokenizer.nextToken());
        	for (int i=0; i< lowers.size(); i++)	
        	{
        		input.setGrade(grades.get(i));
        		input.setMarksFrom(lowers.get(i));
        		input.setMarksTo(uppers.get(i));
        		result=gradeLimitDao.setCourseGradeDetails(input);
			
        		//if error occurs in insert
        		if(result.startsWith("e")){
        			break;
        		}	
        	}
        }
		return new ModelAndView("preProcessChecks/preProcessResultlist","resultObject",result);
	}
	
	
	/**
	 * This method delete external,core,remedial course grade from database and map result to a jsp
	 * @author Ashish Mohan
	 * @param request
	 * @param response
	 * @return ModelAndView containing  List
	 */
	public ModelAndView deleteCourseGradeLimitExternal(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		String result="";
		GradeLimitDomain input=new GradeLimitDomain();
        StringTokenizer courseTokenizer = new StringTokenizer(request.getParameter("courseList"), "\\|");
        
        while (courseTokenizer.hasMoreTokens()) {
		input.setCourseCode(courseTokenizer.nextToken());
		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setUserId(session.getAttribute("userId").toString());
		
		input.setDisplayType(request.getParameter("displayType"));
		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());
		result =  gradeLimitDao.deleteCourseGradeDetails(input);
        }	
		return new ModelAndView("preProcessChecks/preProcessResultlist","resultObject",result);
	}
	
	
	
	/** Function for Core courses Grade Limit**/
	
	
	/**
	 * This method get list of course with grade limit from database and map to a jsp
	 * @author Ashish Mohan
	 * @param request
	 * @param response
	 * @return ModelAndView containing course List
	 */
	public ModelAndView getCourseGradeLimitCore(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		GradeLimitDomain input=new GradeLimitDomain();

		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());
		input.setDisplayType(request.getParameter("displayType"));
		input.setEmployeeId("external");
		if(request.getParameter("courseCode")==null){
			input.setCourseCode("");
		}
		else{
			input.setCourseCode(request.getParameter("courseCode"));
		}
		List<String> courseCodeList= new ArrayList<String>();
		StringTokenizer courseTokenizer = new StringTokenizer(request.getParameter("courseList"), "\\|");
      
		while (courseTokenizer.hasMoreTokens()) {
			courseCodeList.add(courseTokenizer.nextToken());
        }
		input.setCourseCodeList(courseCodeList);
		List<GradeLimitDomain> courses = gradeLimitDao.getCourseGradeDetails(input);

		return new ModelAndView("coursegradelimit/gradeLimit", "detailsList",courses);
	}
	
	//Methods Of Course Cancellation Added By Mandeep Sodhi

	/**
	 * This method get list of course whose status is Approved by the highest order Employee
	 * @author Mandeep Sodhi
	 * @param request
	 * @param response
	 * @return ModelAndView containing courseCodes
	 */	
	public ModelAndView getApprovedCourseCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		
		GradeLimitDomain input= new GradeLimitDomain();

		input.setDisplayType(request.getParameter("teacher"));
		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setUserId(session.getAttribute("userName").toString());
		input.setCourseCode(request.getParameter("courseCode"));
		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());
		input.setGrade(request.getParameter("status"));
		List<GradeLimitDomain>courseCodes=new ArrayList<GradeLimitDomain>();

			courseCodes=gradeLimitDao.getApprovedCourseCodes(input);	
	
		return new ModelAndView("coursegradelimit/gradeLimit", "detailsList",courseCodes);
		
	}

	/**
	 * This method get Change the status of the course From APR to WDW
	 * @author Mandeep Sodhi
	 * @param request
	 * @param response
	 * @return String result
	 */	
	
	public ModelAndView updateCourseDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();		
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}		
		GradeLimitDomain input= new GradeLimitDomain();
		
		StringTokenizer entityToken=new StringTokenizer(request.getParameter("entityId"),"|");
		StringTokenizer courseCode=new StringTokenizer(request.getParameter("courseCode"),"|");
		StringTokenizer semStart=new StringTokenizer(request.getParameter("semStart"),"|");
		StringTokenizer semEnd=new StringTokenizer(request.getParameter("semEnd"),"|");
		StringTokenizer programCourseKey=new StringTokenizer(request.getParameter("programCourseKey"),"|");
		String result=null;
		
		while (entityToken.hasMoreTokens()){
			input.setOwnerEntityId(entityToken.nextToken());
			input.setCourseCode(courseCode.nextToken());
			input.setStartDate(semStart.nextToken());
			input.setEndDate(semEnd.nextToken());
			input.setCourseName(programCourseKey.nextToken());
			
			result=gradeLimitDao.CancelApprovedCourse(input);
			
		}
	    return new ModelAndView("preProcessChecks/preProcessResultlist",
	            "resultObject", result);		
		
	}
	

		//Methods Added For DelayInComponentMarks Interface
	/**
	 * This method get All the course
	 * @author Mandeep Sodhi
	 * @param request
	 * @param response
	 * @return String result
	 */		
	public ModelAndView getAllCourses(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		HttpSession session=request.getSession();		
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		
		GradeLimitDomain input= new GradeLimitDomain();

		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setUserId(session.getAttribute("userName").toString());

		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());

		List<GradeLimitDomain>courseCodes=new ArrayList<GradeLimitDomain>();

			courseCodes=gradeLimitDao.getAllCourseCode(input);	
			
		return new ModelAndView("coursegradelimit/gradeLimit", "detailsList",courseCodes);
	}
	
	/**
	 * This method get All the Details Of Component Of Each Course
	 * @author Mandeep Sodhi
	 * @param request
	 * @param response
	 * @return String result
	 */		
	public ModelAndView getComponentDetails(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		HttpSession session=request.getSession();		
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		System.out.println(request.getParameter("entityId")+request.getParameter("courseCode")+request.getParameter("semStart")+request.getParameter("semEnd")
				+request.getParameter("programCourseKey")+"ss");
		GradeLimitDomain input= new GradeLimitDomain();
		input.setOwnerEntityId(request.getParameter("entityId"));
		input.setCourseCode(request.getParameter("courseCode"));
		input.setGrade(request.getParameter("year"));
		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());
		input.setUniversityCode(session.getAttribute("universityId").toString());
		input.setCourseName(request.getParameter("programCourseKey"));
		List<GradeLimitDomain>courseComponent=new ArrayList<GradeLimitDomain>();

		courseComponent=gradeLimitDao.getAllComponentDetails(input);
			
		return new ModelAndView("coursegradelimit/gradeLimit", "detailsList",courseComponent);			
	}

	
	/**
	 * This method get All the Details Of the Courses
	 * @author Mandeep Sodhi
	 * @param request
	 * @param response
	 * @return String result
	 */		
	public ModelAndView getAllCourseDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();		
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		GradeLimitDomain input= new GradeLimitDomain();
		input.setUniversityCode(session.getAttribute("universityId").toString());		
		input.setStartDate(session.getAttribute("startDate").toString());
		input.setEndDate(session.getAttribute("endDate").toString());
		input.setCourseCode(request.getParameter("courseCode"));
		List<GradeLimitDomain>courseDetails=new ArrayList<GradeLimitDomain>();

		courseDetails=gradeLimitDao.getAllCourseDetails(input);		
		
		return new ModelAndView("coursegradelimit/gradeLimit", "detailsList",courseDetails);			
	}

}



