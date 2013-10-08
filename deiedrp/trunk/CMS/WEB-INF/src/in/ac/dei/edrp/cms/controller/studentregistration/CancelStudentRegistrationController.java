/*
 * @(#) CancelStudentRegistrationController.java
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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.studentregistration.CancelStudentRegistrationDao;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author Manpreet
 *
 */
public class CancelStudentRegistrationController extends MultiActionController  {

	 private CancelStudentRegistrationDao cancelRegistrationDao;

	   		/**
	     * Method to initialize controller
	     * @param studentConnect
	     */
	    public void setCancelRegistrationDao(
	    		CancelStudentRegistrationDao studentConnect) {
	        this.cancelRegistrationDao = studentConnect;
	    }
	    
	   /**
	    * Method to get student record
	    * @param request
	    * @param response
	    * @return
	    * @throws Exception
	    */
	    public ModelAndView getStudentDetails(HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
	    	
	        	HttpSession session=request.getSession(true);

	        	String userId = (String) session.getAttribute("userName");
	        	if(userId == null)
	            {
	            return new ModelAndView("general/SessionInactive","sessionInactive",true);
	            }
	        	
	        	String rollNumber = request.getParameter("roll_number");	            
	            
	            List<StudentInfoGetter> studentRecord=null;
	            try{
	             studentRecord = cancelRegistrationDao.getStudentDetails(rollNumber);
	            }catch (MyException e) {
	            	return new ModelAndView("RegistrationForm/RegisterStudent", "result",
	                        e.getMessage());
	            }       
	            return new ModelAndView("RegistrationForm/StudentRecord",
	                "studentRecord", studentRecord);
	        
	        }
	
	    
	    /**
	     * Method to cancel student registration
	     * @param request
	     * @param response
	     * @return
	     * @throws Exception
	     */
	    public ModelAndView cancelStudentRegistration(HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
	    		String result=null;
	        	HttpSession session=request.getSession(true);

	        	String userId = (String) session.getAttribute("userName");
	        	if(userId == null)
	            {
	            return new ModelAndView("general/SessionInactive","sessionInactive",true);
	            }
	        		        	
	        	StudentInfoGetter inputObj=new StudentInfoGetter();
	        	inputObj.setRollNumber(request.getParameter("regRollNumber"));
	        	inputObj.setEnrollmentNumber(request.getParameter("enrolmentNumber"));
	        	inputObj.setReason(request.getParameter("reason"));
	        	inputObj.setStudentId(request.getParameter("studentId"));
	        	inputObj.setEntityId(request.getParameter("entityId"));
	        	inputObj.setProgramId(request.getParameter("programId"));
	        	inputObj.setBranchCode(request.getParameter("branchId"));
	        	inputObj.setNewSpecialization(request.getParameter("specializationId"));
	        	inputObj.setProcessedFlag(request.getParameter("processedFlag"));
	        	inputObj.setAdmissionMode(request.getParameter("admissionMode"));
	        	inputObj.setSemesterCode(request.getParameter("semesterCode"));
	        	inputObj.setModifierId(session.getAttribute("userId").toString());	        	
	            result=cancelRegistrationDao.cancelRegistration(inputObj);
	            return new ModelAndView("RegistrationForm/RegisterStudent",
	                "result", result);
	        }
	
	    
	    
	
}
