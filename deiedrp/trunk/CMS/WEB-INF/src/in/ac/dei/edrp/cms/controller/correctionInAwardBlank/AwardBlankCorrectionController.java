/*
 * @(#) AwardBlankCorrectionController.java
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

package in.ac.dei.edrp.cms.controller.correctionInAwardBlank;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.correctionInAwardBlank.AwardBlankCorrectionDao;
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import org.apache.log4j.Logger;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Controller for Award Blank Correction Screen
 * @author Dheeraj Singh
 * @date 09-APR-2012
 * @version 1.0
 */

public class AwardBlankCorrectionController extends MultiActionController{
	
	AwardBlankCorrectionDao awardBlankCorrectionDao;
	
	private static Logger logObj = Logger.getLogger(AwardBlankCorrectionController.class);
	
	/**
     * Method to initialize controller
     * @param awardBlankCorrectionDao object of AwardBlankCorrectionDao interface
     */
	public void setAwardBlankCorrectionDao(AwardBlankCorrectionDao awardBlankCorrectionDao) {
        this.awardBlankCorrectionDao = awardBlankCorrectionDao;
    }
	
	/**
     *  Method to get the list of given student
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
	public ModelAndView getCurrentDetails(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
            
        	HttpSession session = request.getSession(true);
        	AwardSheetInfoGetter input = new AwardSheetInfoGetter();
        	input.setUniversityId((String)session.getAttribute("universityId"));
        	input.setSessionStartDate((String)session.getAttribute("startDate"));
        	input.setSessionEndDate((String)session.getAttribute("endDate"));
        	input.setRollNumber(request.getParameter("rollNumber"));
        	input.setCourseCode(request.getParameter("courseCode"));
        	input.setDisplayType(request.getParameter("displayType"));
        	List<AwardSheetInfoGetter> list = awardBlankCorrectionDao.getCurrentStatus(input);
            return new ModelAndView("correctionInAwardBlank/Details", "result", list);
    }
    
	/**
     *  Method to get list of marks for given student based on given course code
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getStudentMarks(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
            
        	HttpSession session = request.getSession(true);
        	AwardSheetInfoGetter input = new AwardSheetInfoGetter();
        	input.setUniversityId((String)session.getAttribute("universityId"));
        	input.setSemesterStartDate(request.getParameter("semesterStartDate"));
        	input.setSemesterEndDate(request.getParameter("semesterEndDate"));
        	input.setRollNumber(request.getParameter("rollNumber"));
        	input.setCourseCode(request.getParameter("courseCode"));
        	input.setDisplayType(request.getParameter("displayType"));
        	input.setEntityId(request.getParameter("entityId"));
        	input.setProgramId(request.getParameter("programId"));
        	input.setSemesterCode(request.getParameter("semesterCode"));
        	input.setProgramCourseKey(request.getParameter("programCourseKey"));
        	List<AwardSheetInfoGetter> list = awardBlankCorrectionDao.getMarks(input);
            return new ModelAndView("awardsheet/MarksList", "result", list);
    }
    
    /**
     *  Method for update marks of student given student
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView saveStudentMarks(HttpServletRequest request,
    		HttpServletResponse response) throws Exception {
    	
        AwardSheetInfoGetter input = new AwardSheetInfoGetter();
        HttpSession session = request.getSession(true);
		String universityId =(String)session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		input.setUniversityId(universityId);
		input.setProgramCourseKey(request.getParameter("programCourseKey"));
		input.setCourseCode(request.getParameter("courseCode"));
		input.setSemesterStartDate(request.getParameter("semesterStartDate"));
		input.setSemesterEndDate(request.getParameter("semesterEndDate"));
		input.setDisplayType(request.getParameter("displayType"));
		input.setEmployeeId(session.getAttribute("userId").toString());
		
        StringTokenizer data = new StringTokenizer(request.getParameter("data"), ";");
        String statusValue = null;
        try {
        	statusValue = awardBlankCorrectionDao.saveStudentMarks(input, data);
        } catch (MyException e) {
            return new ModelAndView("RegistrationForm/RegisterStudent","result", e.getMessage());
        }
        return new ModelAndView("RegistrationForm/RegisterStudent", "result", statusValue);
    }
    
    /**
     *  Method for retrieving Program Courses
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getProgramCourses(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
            
        	HttpSession session = request.getSession(true);
        	AwardSheetInfoGetter input = new AwardSheetInfoGetter();
        	input.setUniversityId((String)session.getAttribute("universityId"));
        	input.setProgramId(request.getParameter("programId"));
        	input.setBranchId(request.getParameter("branchId"));
        	input.setSpecializationId(request.getParameter("specializationId"));
        	input.setSemesterCode(request.getParameter("semesterCode"));
        	List<AwardSheetInfoGetter> list = awardBlankCorrectionDao.getCourses(input);
            return new ModelAndView("correctionInAwardBlank/Details", "result", list);
    }
    
    /**
     *  Method for getting semester dates
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getSemesterDates(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
    	
        	HttpSession session = request.getSession(true);
        	AwardSheetInfoGetter input = new AwardSheetInfoGetter();
        	input.setUniversityId((String)session.getAttribute("universityId"));
        	input.setEntityId(request.getParameter("entityId"));
        	input.setProgramId(request.getParameter("programId"));
        	input.setBranchId(request.getParameter("branchId"));
        	input.setSpecializationId(request.getParameter("specializationId"));
        	input.setSemesterCode(request.getParameter("semesterCode"));
        	input.setSessionStartDate(request.getParameter("sessionStartDate"));
        	input.setSessionEndDate(request.getParameter("sessionEndDate"));
        	List<AwardSheetInfoGetter> list = awardBlankCorrectionDao.getSemesterDates(input);
            return new ModelAndView("correctionInAwardBlank/Details", "result", list);
    }
    
    /**
     *  Method for retrieving Employee Details
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getEmployeeDetail(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
            
        	HttpSession session = request.getSession(true);
        	AwardSheetInfoGetter input = new AwardSheetInfoGetter();
        	input.setUniversityId((String)session.getAttribute("universityId"));
        	input.setEntityId(request.getParameter("entityId"));
        	input.setProgramCourseKey(request.getParameter("programCourseKey"));
    		input.setCourseCode(request.getParameter("courseCode"));
			input.setDisplayType(request.getParameter("displayType"));
        	List<AwardSheetInfoGetter> list = awardBlankCorrectionDao.getEmployeeDetail(input);
            return new ModelAndView("correctionInAwardBlank/Details", "result", list);
    }
    
    /**
     *  Method for retrieving Components of courses AND generating Award Blank PDF
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getComponentDetail(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
            
        	HttpSession session = request.getSession(true);
        	AwardSheetInfoGetter input = new AwardSheetInfoGetter();
        	String path="NEW";
        	String output=ServletRequestUtils.getStringParameter(request, "output");
        	request.setAttribute("path", path);
        	input.setUniversityId((String)session.getAttribute("universityId"));
        	input.setEntityId(request.getParameter("entityId"));
        	input.setProgramId(request.getParameter("programId"));
        	input.setDisplayType(request.getParameter("displayType"));
    		input.setCourseCode(request.getParameter("courseCode"));
    		input.setProgramCourseKey(request.getParameter("programCourseKey"));
    		input.setStartDate(request.getParameter("semesterStartDate"));
    		input.setEndDate(request.getParameter("semesterEndDate"));
        	List<AwardSheetInfoGetter> componentList = awardBlankCorrectionDao.getComponents(input);
        	List<AwardSheetInfoGetter> studentMarks = awardBlankCorrectionDao.getStudentMarks(input);
        	
        	List<String> sheetHeaders = new ArrayList<String>();  
        	List<String> dataList = new ArrayList<String>();  
        	List<String> docHeaders = new ArrayList<String>();
        	docHeaders.add(request.getParameter("entityName"));
        	 docHeaders.add(request.getParameter("programName"));
             docHeaders.add(request.getParameter("branchName"));
             docHeaders.add(request.getParameter("spclName"));      
             docHeaders.add(request.getParameter("semesterName"));
             docHeaders.add(request.getParameter("courseCode"));
             docHeaders.add(request.getParameter("courseName"));
             docHeaders.add(request.getParameter("displayType"));
             docHeaders.add(request.getParameter("sessionStart"));
             docHeaders.add(request.getParameter("sessionEnd"));
             docHeaders.add(request.getParameter("employeeName"));
             docHeaders.add((String) session.getAttribute("universityName"));
        	
        	sheetHeaders.add("SR No.");
        	sheetHeaders.add("Roll No.");
        	sheetHeaders.add("Student Name");
        	System.out.println("list size is "+componentList.size());
        	for(int i=0;i<componentList.size();i++){
        		sheetHeaders.add(componentList.get(i).getEvaluationId()+"\n"+componentList.get(i).getMaximumMarks());
        		System.out.println("component is "+componentList.get(i).getEvaluationId());
        	}
        	sheetHeaders.add("Total Marks");
        	sheetHeaders.add("Grade");
        	List<String >list=new ArrayList<String>();
        	int k=0;
        	for(int i=0;i<studentMarks.size();i++){
        		if(list.indexOf(studentMarks.get(i).getRollNumber())<0){
        			list.add(studentMarks.get(i).getRollNumber());
        			k++;
        			dataList.add(String.valueOf(k));
        			dataList.add(studentMarks.get(i).getRollNumber());
            		dataList.add(studentMarks.get(i).getStudentName());
            		System.out.println(String.valueOf(i)+" "+ studentMarks.get(i).getRollNumber()+" "+studentMarks.get(i).getStudentName()+" ");
        			for(int j=i;j<studentMarks.size();j++){
        				if(studentMarks.get(i).getRollNumber().equals(studentMarks.get(j).getRollNumber())){
        						dataList.add(studentMarks.get(j).getMarks());
        						System.out.print(studentMarks.get(j).getMarks()+" ");
        					
        				}
        				
        			}
        			if(request.getParameter("displayType").equalsIgnoreCase("I") || request.getParameter("displayType").equalsIgnoreCase("R")){
            			dataList.add(studentMarks.get(i).getTotalInternal());
            			dataList.add(studentMarks.get(i).getInternalGrade());
            			System.out.print(studentMarks.get(i).getTotalInternal()+" "+studentMarks.get(i).getInternalGrade());
            		}
            		else if(request.getParameter("displayType").equalsIgnoreCase("E")){
            			dataList.add(studentMarks.get(i).getTotalExternal());
            			dataList.add(studentMarks.get(i).getExternalGrade());
            		}
        		}
        		
        	}
        	 List<List<String>> outputData = new ArrayList<List<String>>();
             outputData.add(docHeaders);
             outputData.add(sheetHeaders);
             outputData.add(dataList);
             System.out.println("inside controller");
        	 return new ModelAndView("PdfRevenueSummary", "revenueData",
                     outputData);
      	}
    
    /**
     *  Method for checking existence of report before downloading and printing
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    	public ModelAndView checkExistence(HttpServletRequest request,
            HttpServletResponse response) throws Exception { 
            
    		String initialPath=getServletContext().getRealPath("/");
        	String filePath=request.getParameter("filePath");
        	System.out.println("file path is "+filePath);
        	filePath=initialPath+filePath;
        	System.out.println("Complete Path "+filePath);
        	String count="";
        	File file = new File(filePath);
        	if(file.exists()){
        		count="Yes";
        	}else{
        		count="No";
        	}
        	return new ModelAndView("systemtabletwo/countInfo", "count", count);
    	}
   
}
