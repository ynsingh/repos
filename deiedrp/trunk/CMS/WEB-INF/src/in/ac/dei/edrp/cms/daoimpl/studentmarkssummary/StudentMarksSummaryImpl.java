/*
 * @(#) StudentRemedialImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.studentmarkssummary;

/**********************************************************************************
 * $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      .............
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

import in.ac.dei.edrp.cms.dao.studentmarkssummary.StudentMarksSummaryDao;
import in.ac.dei.edrp.cms.domain.studentmarkssummary.StudentMarksSummaryBean;
import in.ac.dei.edrp.cms.utility.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This file consist of the methods used for student info display
 * @author Nupur Dixit
 * @date 1 Jan 2012
 * @version 1.0
 */

public class StudentMarksSummaryImpl extends SqlMapClientDaoSupport implements
		StudentMarksSummaryDao {
	private static Logger logObj = Logger.getLogger(StudentMarksSummaryImpl.class);
	
    /**
     * Method fetches student roll number for the selected enrollment number
     * @param bean containing required fields  
     * @return List of roll numbers  
     * @throws SQLException
     */
	@SuppressWarnings("unchecked")
	public List<StudentMarksSummaryBean> getStudentRollNumber(StudentMarksSummaryBean input) throws Exception {		
		List<StudentMarksSummaryBean> detailsList = new ArrayList<StudentMarksSummaryBean>();
		try{		
			detailsList = getSqlMapClientTemplate().queryForList("studentMarksSummary.selectRollNumber", input);			
			return detailsList;
		}
		catch (Exception e){
			logObj.error(e.getMessage());	
			 throw e;
		}
	}

	 /**
     * Method fetches courses for the selected semester
     * @param input bean containing required fields
     * @return List of courses 
     * @throws SQLException
     */
	@SuppressWarnings("unchecked")
	public List<StudentMarksSummaryBean> getCourses(StudentMarksSummaryBean input) throws Exception {		
		List<StudentMarksSummaryBean> courseList = new ArrayList<StudentMarksSummaryBean>();
		try{		
			courseList = getSqlMapClientTemplate().queryForList("studentMarksSummary.getCourseList", input);			
			return courseList;
		}
		catch (Exception e){
			logObj.error(e.getMessage());	
			 throw e;
		}
	}
	 /**
     * Method fetches marks detail for the selected course
     * @param input bean containing required fields
     * @return List of details containing marks info
     * @throws SQLException
     */
	@SuppressWarnings("unchecked")
	public List<StudentMarksSummaryBean> getMarksDetails(StudentMarksSummaryBean input) throws Exception {
		//for setting entityId
		input.setEntityId((String)getSqlMapClientTemplate().queryForObject("studentMarksSummary.getEntityId", input));
		
		List<StudentMarksSummaryBean> marksList = new ArrayList<StudentMarksSummaryBean>();
		String flagStatus="true";
		try{		
			marksList = getSqlMapClientTemplate().queryForList("studentMarksSummary.getFullMarks", input);
			
//      commented by ashish mohan
			
//			for(int i=0;i<marksList.size();i++){
//				if(marksList.get(i).getApprovalStatus().equalsIgnoreCase("apr")){
//					continue;
//				}
//				else{
//					marksList.get(i).setMarks("Waiting for Approval");
//					flagStatus="false";
//				}
//			}
//			
//			if(flagStatus.equalsIgnoreCase("false")){
//				for(int i=0;i<marksList.size();i++){
//					marksList.get(i).setInternalGrade("Waiting for Approval");
//					marksList.get(i).setExternalGrade("Waiting for Approval");
//					marksList.get(i).setFinalGradePoint("Waiting for Approval");					
//					marksList.get(i).setTotalInternal("Waiting for Approval");
//					marksList.get(i).setTotalExternal("Waiting for Approval");
//					marksList.get(i).setTotalMarks("Waiting for Approval");
//				}
//			}
			
			return marksList;
		}
		catch (Exception e){
			logObj.error(e.getMessage());	
			 throw e;
		}
	}
	
	 /**
     * Method fetches semester summary of student for the selected semester
     * @param input bean containing required fields
     * @return List of beans containing summary of selected semester  
     * @throws SQLException
     */
	@SuppressWarnings("unchecked")
	public List<StudentMarksSummaryBean> getSemesterSummaryDetail(StudentMarksSummaryBean input) throws Exception {		
		List<StudentMarksSummaryBean> semesterSummaryList = new ArrayList<StudentMarksSummaryBean>();		
		try{		
			semesterSummaryList = getSqlMapClientTemplate().queryForList("studentMarksSummary.getSemesterSummary", input);			
			return semesterSummaryList;
		}
		catch (Exception e){
			logObj.error(e.getMessage());	
			 throw e;
		}
	}
	
	 /**
     * Method fetches semester list of all the selected program
     * @param input bean containing required fields
     * @return List of beans containing semesters for selected program  
     * @throws SQLException
     */
	public List<StudentMarksSummaryBean> getSemesterDetail(StudentMarksSummaryBean input) throws Exception {
		List<StudentMarksSummaryBean> semesterList = new ArrayList<StudentMarksSummaryBean>();
		try{		
			semesterList = getSqlMapClientTemplate().queryForList("studentMarksSummary.getSemesterList", input);			
			return semesterList;
		}
		catch (Exception e){
			logObj.error(e.getMessage());	
			 throw e;
		}
	}
	
	
	 /**
     * Method set request of student for marks correction
     * @param input bean containing required fields
     * @return String i.e.insert or not  
     * @throws SQLException
     */
	public String setCorrectionRequest(StudentMarksSummaryBean input) {
		
		try{
			input.setEntityId((String)getSqlMapClientTemplate().queryForObject("studentMarksSummary.getEntityId", input));
			getSqlMapClientTemplate().update("studentMarksSummary.setRequestForCorrection", input);			
			return "true";
		}
		catch (Exception e){
			logObj.error(e.getMessage());	
			return "false"+e;
		}
	}

	
	/**
     * Method get status of student request for marks correction
     * @param input bean containing required fields
     * @return List  
     * @author Ashish Mohan
     */
	public List<StudentMarksSummaryBean> getCorrectionRequestStatus(
			StudentMarksSummaryBean input) {
		List<StudentMarksSummaryBean> list = new ArrayList<StudentMarksSummaryBean>();
		try{
			input.setEntityId((String)getSqlMapClientTemplate().queryForObject("studentMarksSummary.getEntityId", input));
			list = getSqlMapClientTemplate().queryForList("studentMarksSummary.getRequestDetails", input);			
		}
		catch (Exception e){
			logObj.error(e.getMessage());	
		}
		return list;
	}
	
	
	
	public StudentMarksSummaryBean getPathParameters(StudentMarksSummaryBean input) throws Exception {
		StudentMarksSummaryBean parameters = new StudentMarksSummaryBean();
		
		try{
			parameters = (StudentMarksSummaryBean)getSqlMapClientTemplate().queryForObject("studentMarksSummary.getPathParameters", input);			
		}catch (Exception e){
			logObj.error(e.getMessage());	
			 throw e;
		}
		
		return parameters;
	}
	 

}
