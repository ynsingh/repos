/**
 * @(#) ResultVerificationDAOImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.instructorcourse;

import in.ac.dei.edrp.cms.dao.instructorcourse.InstructorCourseDao;
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import in.ac.dei.edrp.cms.domain.degreelist.DegreeListInfoGetter;
import in.ac.dei.edrp.cms.domain.instructorcourse.InstructorCourseBean;
import in.ac.dei.edrp.cms.domain.resultverification.ResultVerificationBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * This file consist of the methods used at the Staging Table Report Generation.
 * 
 * @author Nupur Dixit
 * @date 26 Sep 2012
 * @version 1.0
 */

public class InstructorCourseDaoImpl extends SqlMapClientDaoSupport implements InstructorCourseDao {

	/*Create Logger Object for maintain log file.*/	
	private Logger loggerObject = Logger.getLogger(InstructorCourseDaoImpl.class);

	 /**
     * Method for getting course list of logged in user
     * @param inputObj
     * @return List of courses
     */
    @SuppressWarnings("unchecked")
    public List<AwardSheetInfoGetter> getCourseList(AwardSheetInfoGetter inputObj) {
    	List<AwardSheetInfoGetter> courseList=null;
    	try{
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		inputObj.setExtra(dateFormat.format(new Date()));
    		System.out.println("inside course list dao impl");
    		loggerObject.info("getCourseList");
    		//Change Done By Dheeraj For Allowing Access To Examination Dept. For Entering External And Remedial Marks
    		
//    		if(inputObj.getDisplayType()==null){
//    			inputObj.setDisplayType("");
//    		}
//    		if(inputObj.getDisplayType().equalsIgnoreCase("I")){
    			courseList = getSqlMapClientTemplate().queryForList("InstructorCourse.getCourseList", inputObj);
//    		}else{
//    			courseList = getSqlMapClientTemplate().queryForList("InstructorCourse.getCourseListForRemedialAndExternal", inputObj);
//    		}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		loggerObject.error("getCourseList");
		}
        return courseList;
    }

    /**
     * Method for getting student list on the basis of selected course
     * @param inputObj
     * @return List of students
     */
    @SuppressWarnings("unchecked")
    public List<InstructorCourseBean> getStudentList(InstructorCourseBean inputObj) {
    	List<InstructorCourseBean> studentList=null;
    	try{    		
    		studentList = getSqlMapClientTemplate().queryForList("InstructorCourse.getStudentList", inputObj);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		loggerObject.error("getStudentList");
		}
        return studentList;
    }
    
    public List<ResultVerificationBean> getRequestType(ResultVerificationBean resultVerificationBean) {
		List<ResultVerificationBean> requestTypeList = new ArrayList<ResultVerificationBean>();
		try{
			requestTypeList = getSqlMapClientTemplate().queryForList("ResultVerification.getRequestType", resultVerificationBean);
		} catch(Exception e){
			System.out.println("inside get request type error");
			e.printStackTrace();
		}
		return requestTypeList;
	}
	
	/**
	 * The method gets the details of the students
	 * and prints verification report
	 * @param infoGetter obejct of the reference bean
	 * @return list 
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> getStudents4Verification(DegreeListInfoGetter infoGetter) {		
		List<DegreeListInfoGetter> resultList = new ArrayList<DegreeListInfoGetter>();		
		try {
			resultList = getSqlMapClient().queryForList("degreeList.getStudents4Verification",infoGetter);
		} catch (Exception e) {
			loggerObject.error("Exception in getStudents4Verification"+e);
		}				
		return resultList;
	}
	
	/**
	 * The method updated the header of result verification request
	 * updates process_status and process_date after successfull report generation
	 * @param obejct of the ResultVerificationBean bean
	 * @return success String (true/false) 
	 */
	public String updateResultProcessStatus(ResultVerificationBean resultVerificationBean) {
		String isUpdated="false";
		try{
			getSqlMapClientTemplate().update("ResultVerification.updateResultHeaderProcessStatus", resultVerificationBean);
			isUpdated="true";
		} catch(Exception e){
			System.out.println("inside get request type error");
			e.printStackTrace();
			isUpdated="false";
		}
		return isUpdated;
	}
}
