/*
 * @(#) ExternalExaminerDetailController.java
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
package in.ac.dei.edrp.cms.controller.externalexaminercourse;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import in.ac.dei.edrp.cms.dao.externalexaminercourse.ExternalExaminarDetailDao;

import in.ac.dei.edrp.cms.domain.externalexaminercourse.ExternalExaminerCourseInfoGetter;

/**
 * This controller is designed for setting & retrieving
 * information about External Examinar Detail.
 * @author Mandeep Sodhi
 * @date 29 Dec 2011
 * @version 1.0
 */

public class ExternalExaminarDetailController extends MultiActionController{
	private ExternalExaminarDetailDao externalExaminarDetailDao;
	
	
	/**
     * The setter method of the interface associated
     * with this controller
     * @param ExternalExaminarDetail
     */
	public void setExternalExaminarDetailDao(
			ExternalExaminarDetailDao externalExaminarDetailDao) {
		this.externalExaminarDetailDao = externalExaminarDetailDao;
	}


    /**
     * Method for Inserting examinar detail
     * @param request
     * @param response
     * @return Model And View insert operation result
     * @throws Exception
     */

public ModelAndView insertExaminarDetail(HttpServletRequest request,HttpServletResponse response) throws Exception{
    HttpSession session = request.getSession(true);

    String userId = (String) session.getAttribute("userId");
    String universityId = (String) session.getAttribute("universityId");
	
	if(userId == null)
    {
    return new ModelAndView("general/SessionInactive","sessionInactive",true);
    }
	ExternalExaminerCourseInfoGetter infoBean = new ExternalExaminerCourseInfoGetter();
	infoBean.setUserId(userId);

	infoBean.setPrefix(request.getParameter("prefix"));
	infoBean.setExaminarId(request.getParameter("examinarId"));
	infoBean.setFirstName(request.getParameter("firstName"));
	infoBean.setMiddleName(request.getParameter("middleName"));
	infoBean.setLastName(request.getParameter("lastName"));
	infoBean.setGender(request.getParameter("gender"));
	infoBean.setDepartment(request.getParameter("department"));
	infoBean.setDesignation(request.getParameter("designation"));
	infoBean.setCollegeName(request.getParameter("collegeName"));
	infoBean.setCollegeAddress(request.getParameter("collegeAddress"));
	infoBean.setPermanentAddress(request.getParameter("permanentAddress"));
	infoBean.setPermState(request.getParameter("permState"));
	infoBean.setPermCity(request.getParameter("permCity"));
	infoBean.setPinCode(request.getParameter("pinCode"));
	infoBean.setCorsAddress(request.getParameter("corsAddress"));
	infoBean.setCorsState(request.getParameter("corsState"));
	infoBean.setCorsCity(request.getParameter("corsCity"));
	infoBean.setCorsPinCode(request.getParameter("corsPinCode"));
    infoBean.setPermanentAddressKey("PER");
    infoBean.setCoresAddresskey("COR");
    infoBean.setUniversityCode(universityId);
    infoBean.setOfficePhoneNumber(request.getParameter("officePhoneNumber"));
    infoBean.setMobilePhoneNumber(request.getParameter("mobilePhoneNumber"));
    infoBean.setFaxNumber(request.getParameter("faxNumber"));
	
	String resultInsertSuccess=externalExaminarDetailDao.insertExtExaminarDetail(infoBean);
	if(resultInsertSuccess=="success"){
		String examinerId="success"+infoBean.getExaminarId();
	    return new ModelAndView("preProcessChecks/preProcessResultlist",
	            "resultObject",examinerId);	
		
	}
	else{
    return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultInsertSuccess);
	}
}
/**
 * Method for getting the details of the Examinar Id of the external
 * @param request
 * @param response
 * @return  Model And View examinarIdList
 * @throws Exception
 */
public ModelAndView getExaminarId(HttpServletRequest request,HttpServletResponse response) throws Exception{
	ExternalExaminerCourseInfoGetter input = new ExternalExaminerCourseInfoGetter();
	HttpSession session = request.getSession(true);

    String universityId = (String) session.getAttribute("universityId");
    input.setUniversityCode(universityId);
	List<ExternalExaminerCourseInfoGetter>examinarIdList=new ArrayList<ExternalExaminerCourseInfoGetter>();
	examinarIdList=externalExaminarDetailDao.getExaminarIdDetail(input);
    return new ModelAndView("ExternalExaminerCourse/examinarId",
            "resultObject", examinarIdList);
}
/**
 * Method for getting the details of the concerned examinar
 * @param request
 * @param response
 * @return Model And View resultBasicDetails
 * @throws Exception
 */
public ModelAndView getExaminarDetails(HttpServletRequest request,HttpServletResponse response)throws Exception{
	
	ExternalExaminerCourseInfoGetter input = new ExternalExaminerCourseInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
        String universityId = (String) session.getAttribute("universityId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
		input.setUniversityCode(universityId);
        input.setExtCode(request.getParameter("extCode"));
        input.setExtName(request.getParameter("extName"));

        List<ExternalExaminerCourseInfoGetter> resultBasicDetails = externalExaminarDetailDao.getEmployeeDetails(input);

        return new ModelAndView("ExternalExaminerCourse/ExaminarBasicDetail",
                "resultObject", resultBasicDetails);
}
/**
 * Method for getting the Personal details of the concerned examinar
 * @param request
 * @param response
 * @return Model And View resultBasicDetails
 * @throws Exception
 */
public ModelAndView getExaminarAddressDetails(HttpServletRequest request,HttpServletResponse response)throws Exception{
	ExternalExaminerCourseInfoGetter input = new ExternalExaminerCourseInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
		input.setExaminarCode(request.getParameter("examinarCode"));

        List<ExternalExaminerCourseInfoGetter> resultBasicDetails = externalExaminarDetailDao.getExternalAddressDetails(input);

        return new ModelAndView("ExternalExaminerCourse/ExternalAddressDetails",
            "resultObject", resultBasicDetails);
}
/**
 * Method for delete the concerned Examinar Detail
 * @param request
 * @param response
 * @return Model And View delete operation result
 * @throws Exception
 */
public ModelAndView deleteExaminarRecord(HttpServletRequest request,HttpServletResponse response)throws Exception{

	HttpSession session = request.getSession(true);
    String userId = (String) session.getAttribute("userId");
    String universityId = (String) session.getAttribute("universityId");
	if(userId == null)
    {
    return new ModelAndView("general/SessionInactive","sessionInactive",true);
    }
	List<ExternalExaminerCourseInfoGetter> examinarList=new ArrayList<ExternalExaminerCourseInfoGetter>();
	
	
	StringTokenizer examinarToken=new StringTokenizer(request.getParameter("recordArrayCol"),"|");
	
	 ExternalExaminerCourseInfoGetter input = new ExternalExaminerCourseInfoGetter();
	while(examinarToken.hasMoreTokens())
	{
        
         input.setRecordArrayCol(examinarToken.nextToken());
         input.setUniversityCode(universityId);
         examinarList.add(input);
	}
	

	Boolean isDeleted=externalExaminarDetailDao.deleteExaminarRecord(examinarList,input);

    return new ModelAndView("ExternalExaminerCourse/insert",
    		"info", isDeleted);
}

/**
 * Method for update the concerned examinar Record.
 * @param request
 * @param response
 * @return Model And View update operation result
 * @throws Exception
 */
public ModelAndView updateExaminarDetail(HttpServletRequest request,HttpServletResponse response)throws Exception{
    HttpSession session = request.getSession(true);

    String userId = (String) session.getAttribute("userId");
    String universityId = (String) session.getAttribute("universityId");
	if(userId == null)
    {
    return new ModelAndView("general/SessionInactive","sessionInactive",true);
    }
	ExternalExaminerCourseInfoGetter infoBean = new ExternalExaminerCourseInfoGetter();
	System.out.println(request.getParameter("examinarId"));
	infoBean.setUserId(userId);
	System.out.println(userId+"userId");
	infoBean.setPrefix(request.getParameter("prefix"));
	infoBean.setExaminarId(request.getParameter("examinarId"));
	infoBean.setGender(request.getParameter("gender"));
	infoBean.setDepartment(request.getParameter("department"));
	infoBean.setDesignation(request.getParameter("designation"));
	infoBean.setCollegeName(request.getParameter("collegeName"));
	infoBean.setCollegeAddress(request.getParameter("collegeAddress"));
	infoBean.setPermanentAddress(request.getParameter("permanentAddress"));
	infoBean.setPermState(request.getParameter("permState"));
	infoBean.setPermCity(request.getParameter("permCity"));
	infoBean.setPinCode(request.getParameter("pinCode"));
	infoBean.setCorsAddress(request.getParameter("corsAddress"));
	infoBean.setCorsState(request.getParameter("corsState"));
	infoBean.setCorsCity(request.getParameter("corsCity"));
	infoBean.setCorsPinCode(request.getParameter("corsPinCode"));
	infoBean.setUniversityCode(universityId);
    infoBean.setPermanentAddressKey("PER");
    infoBean.setCoresAddresskey("COR");
    System.out.println(request.getParameter("permanentAddress"));
    System.out.println(request.getParameter("officePhoneNumber"));
    infoBean.setOfficePhoneNumber(request.getParameter("officePhoneNumber"));
    infoBean.setMobilePhoneNumber(request.getParameter("mobilePhoneNumber"));
    infoBean.setFaxNumber(request.getParameter("faxNumber"));
	
	String resultInsertSuccess=externalExaminarDetailDao.updateExtExaminarDetail(infoBean);
	
    return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultInsertSuccess);

}
}
