/**
 * @(#) RemarkController.java
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
package in.ac.dei.edrp.cms.controller.remark;

import in.ac.dei.edrp.cms.dao.remark.RemarkService;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class
 * @version 1.0 
 * @date Oct 6 2012
 * @author Devendra Singhal
 */
public class RemarkController extends MultiActionController{
	/*Create RemarkService Object*/
	RemarkService service;
	
	/*Setter method to initilize RemarkService instance*/
	public void setRemarkService(RemarkService service){
		this.service=service;
	}
	
	/**
	 * Method to Get Semester Detail
	 * @param HttpServletRequest req
	 * @param HttpServletResponse res
	 * @throws IOException,ServletException
	 * @return ModelAndView containing Semester Detail
	 **/
	public ModelAndView getSemesterDetail(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		HttpSession session = req.getSession(true);
		String universityCode = (String) session.getAttribute("universityId");		
		if(universityCode == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		ProgressCardInfo input=new ProgressCardInfo();
		input.setUniversityCode(universityCode);
		input.setRollNumber(req.getParameter("rollNumber"));
        List<ProgressCardInfo> list = service.getSemesterDetail(input);        
        return new ModelAndView("remark/remarkSemesterDetail","records", list);
	}
	
	/**
	 * Method to Get Remark Detail to Manage
	 * @param HttpServletRequest req
	 * @param HttpServletResponse res
	 * @throws IOException,ServletException
	 * @return ModelAndView containing Semester Detail
	 **/
	public ModelAndView getRemarkDetail(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		HttpSession session = req.getSession(true);
		String universityCode = (String) session.getAttribute("universityId");		
		if(universityCode == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		ProgressCardInfo input=new ProgressCardInfo();
		input.setUniversityCode(universityCode);
		input.setProgramCourseKey(req.getParameter("programCourseKey"));
		input.setSemesterStartDate(req.getParameter("semesterStartDate"));
		input.setSemesterEndDate(req.getParameter("semesterEndDate"));
		input.setRollNumber(req.getParameter("rollNumber"));		
        List<ProgressCardInfo> list = service.getRemarkDetail(input);      
        return new ModelAndView("remark/remarkSemesterDetail","records", list);
	}
	
	/**
	 * Method to Get Remark Detail to Manage
	 * @param HttpServletRequest req
	 * @param HttpServletResponse res
	 * @throws IOException,ServletException
	 * @return ModelAndView containing Message i.e. Success/Failure
	 **/
	public ModelAndView saveRemarkDetail(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		HttpSession session = req.getSession(true);
		String universityCode = (String) session.getAttribute("universityId");		
		if(universityCode == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		ProgressCardInfo input=new ProgressCardInfo();
		input.setUniversityCode(universityCode);
		input.setProgramCourseKey(req.getParameter("programCourseKey"));
		input.setSemesterStartDate(req.getParameter("semesterStartDate"));
		input.setSemesterEndDate(req.getParameter("semesterEndDate"));
		input.setRollNumber(req.getParameter("rollNumber"));
		input.setRemark(req.getParameter("remark"));
        String msg = service.saveRemarkDetail(input);      
        return new ModelAndView("buildactivitymaster/Result","message", msg);
	}
}
