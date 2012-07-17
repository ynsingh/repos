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
import in.ac.dei.edrp.cms.dao.reportgeneration.SubjectCategoryWiseStudentListDAO;
import in.ac.dei.edrp.cms.domain.reportgeneration.SubjectWiseMeritList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This controller is designed for setting & retrieving
 * the activity master information 
 * @author Ankit Jain
 * @date 15 Apr 2011
 * @version 1.0
 */
public class SubjectCategoryWiseStudentListController extends MultiActionController{

	private SubjectCategoryWiseStudentListDAO subjectCategoryWiseStudentListDAO;
	
	/**
	 * The setter method of the interface associated
	 * with this controller
	 * @param subjectCategoryWiseStudentListDao
	 */
	public void setSubjectCategoryWiseStudentListDAO(SubjectCategoryWiseStudentListDAO SubjectCategoryWiseStudentListDao) {
		this.subjectCategoryWiseStudentListDAO = SubjectCategoryWiseStudentListDao;
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
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		subjectWiseMeritList.setUniversityId(universityId);
		List<SubjectWiseMeritList> sessionDates = subjectCategoryWiseStudentListDAO.getCurrentSession(subjectWiseMeritList);
		return new ModelAndView("SubjectCategoryWiseStudentList/SessionDates","sessionDates", sessionDates);
			
	}
	
	/**
     * Method for generate the PDF.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView generatePDF(HttpServletRequest request,HttpServletResponse response)throws Exception {
		
		SubjectWiseMeritList subjectWiseMeritList=new SubjectWiseMeritList();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		subjectWiseMeritList.setEntityId(request.getParameter("entityId"));
		subjectWiseMeritList.setUniversityId(universityId);
		subjectWiseMeritList.setProgramId(request.getParameter("programId"));
		subjectWiseMeritList.setBranchId(request.getParameter("branchId"));
		subjectWiseMeritList.setSpecializationId(request.getParameter("specializationId"));
		subjectWiseMeritList.setSemesterCode(request.getParameter("semesterCode"));
		subjectWiseMeritList.setSessionStartDate(request.getParameter("fromSession"));
		subjectWiseMeritList.setSessionEndDate(request.getParameter("toSession"));
		
		List <SubjectWiseMeritList> semesterDates = subjectCategoryWiseStudentListDAO.getSemesterDates(subjectWiseMeritList);
		System.out.println("semester date gets method " +semesterDates);
		if(semesterDates==null || semesterDates.size()==0){
			return new ModelAndView("associatecoursewithinstructor/Result","message","false-semesterDatesMissing");
		}
		session.setAttribute("semesterStartDate", semesterDates.get(0).getSemesterStartDate());
		session.setAttribute("semesterEndDate", semesterDates.get(0).getSemesterEndDate());
		
		List <SubjectWiseMeritList> courseGroupList = subjectCategoryWiseStudentListDAO.getCourseGroup(subjectWiseMeritList);
		return new ModelAndView("SubjectCategoryWiseStudentPDFView","courseGroupList", courseGroupList);		
	}
	
}