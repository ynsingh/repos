/*
 * @(#) ProbableToActualController.java
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
package in.ac.dei.edrp.cms.controller.studentregistration;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import in.ac.dei.edrp.cms.dao.studentregistration.ProbableToActualDao;
import in.ac.dei.edrp.cms.domain.activitymaster.ActivityMaster;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;



/**
 * Controller for enabling student to register in program's probable  semester
 * if student fails in a semester
 * @author Manpreet Kaur
 * @date 28-02-2011
 * @version 1.0
 */
public class ProbableToActualController extends MultiActionController{
ProbableToActualDao ProbableToActualDao;
	public void setProbableToActualDao(ProbableToActualDao probableToActualDao) {
	this.ProbableToActualDao=probableToActualDao;
	}
	
	/**
     *  Method to get list of semesters available in university
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView transferOfProbables(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        StudentInfoGetter progDetails=new StudentInfoGetter();
        /*
         * parameters to be taken from request 
         */
        progDetails.setProgramId("0001001");
        progDetails.setBranchCode("001");
        progDetails.setNewSpecialization("XXX");
        progDetails.setEntityId("00010001");
        progDetails.setSemesterCode("S4");
        progDetails.setSessionStartDate("2011-01-01");
        progDetails.setSessionEndDate("2011-12-31");
        progDetails.setModifierId("process");
        String result="";
//        try{
//        result = ProbableToActualDao.transferOfProbables(progDetails);
//        System.out.println(result);
        return new ModelAndView("RegistrationForm/RegisterStudent", "result", result);
//        }catch (MyException e) {
//        	System.out.println(e.getMessage());
//        return new ModelAndView("RegistrationForm/RegisterStudent", "result", e.getMessage());
//		}
       
    }
	
    
    
    
  
	
	/**
     * Method for count the number of activity
     * @param request
     * @param response
     * @return
     */
	public ModelAndView checkPassFail(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		List<String> semesterList=new ArrayList<String>();
		ActivityMaster input=new ActivityMaster();
		String a=ProbableToActualDao.checkStudentPassFail(semesterList, input, 4);
		
		System.out.println("pass fail called--"+a);
		return new ModelAndView("RegistrationForm/RegisterStudent", "result", a);
	}
	
	

}
