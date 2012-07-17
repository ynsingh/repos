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
package in.ac.dei.edrp.cms.daoimpl.studentremedial;

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

import in.ac.dei.edrp.cms.dao.studentremedial.StudentRemedialConnect;
import in.ac.dei.edrp.cms.domain.studentremedial.StudentRemedialInfoGetter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This file consist of the methods used at the
 * which are to be executed during student applly to take remedial exam.
 * @author Rohit
 * @date 7 April 2011
 * @version 1.0
 */

public class StudentRemedialImpl extends SqlMapClientDaoSupport implements
		StudentRemedialConnect {
	private static Logger logObj = Logger.getLogger(StudentRemedialImpl.class);
	
    /**
     * Method fetches student current details
     * @param userId user id of the user who executed the process
     * @param roll Number of student who currently logins
     * @return List of details containing 
     * @throws SQLException
     */
	@SuppressWarnings("unchecked")
	public List<StudentRemedialInfoGetter> getStudentDetails(
			StudentRemedialInfoGetter input) throws Exception {
		
		List<StudentRemedialInfoGetter> detailsList = new ArrayList<StudentRemedialInfoGetter>();
		try{
			
		detailsList = getSqlMapClientTemplate().queryForList(
				"studentRemedial.getRecords", input);

		for (int j = 0; j < detailsList.size(); j++) {
			List<StudentRemedialInfoGetter> getCurrentDates = getSqlMapClientTemplate()
					.queryForList("studentRemedial.getDates",
							detailsList.get(j));

			
			if (getCurrentDates.size() > 0) {
				detailsList.get(j).setAppliedStartDate(
						getCurrentDates.get(0).getAppliedStartDate());
				detailsList.get(j).setAppliedEndDate(
						getCurrentDates.get(0).getAppliedEndDate());
			}

		}

		return detailsList;
		}
		catch (Exception e){
			logObj.error(e.getMessage());	
			 throw e;
		}
	}

    /**
     * Method fetches student Remedials details
     * @param current program_id for student
     * @param roll Number of student who currently logins
     * @return List of details containing details about all remedials of student 
     * @throws SQLException
     */
	@SuppressWarnings("unchecked")
	public List<StudentRemedialInfoGetter> getRemedialDetails(
			StudentRemedialInfoGetter input) throws Exception {

		List<StudentRemedialInfoGetter> remedialsList;
		try
		{
			
		remedialsList = getSqlMapClientTemplate().queryForList(
				"studentRemedial.getProgramCourseKey", input);

		List<StudentRemedialInfoGetter> allRemedialsList = new ArrayList<StudentRemedialInfoGetter>();

		for (int i = 0; i < remedialsList.size(); i++) {

			StudentRemedialInfoGetter studentObject = new StudentRemedialInfoGetter();
			studentObject.setProgramCourseKey(remedialsList.get(i)
					.getProgramCourseKey());
			studentObject.setSemesterStartDate(remedialsList.get(i)
					.getSemesterStartDate());
			studentObject.setSemesterEndDate(remedialsList.get(i)
					.getSemesterEndDate());
			
			studentObject.setRollNo(remedialsList.get(i).getRollNo());

	

			List<StudentRemedialInfoGetter> getRemedialsList = getSqlMapClientTemplate()
					.queryForList("studentRemedial.getRemedialRecords",
							studentObject);
           
           
			for (int j = 0; j < getRemedialsList.size(); j++) {
				getRemedialsList.get(j).setRollNo(studentObject.getRollNo());
				System.out.println(getRemedialsList.get(j).getCourseCode()+""+getRemedialsList.get(j).getProgramCourseKey()+""+getRemedialsList.get(j).getRollNo()+""+getRemedialsList.get(j).getAttemptNumber());
				
				StudentRemedialInfoGetter getAppliedStatus = (StudentRemedialInfoGetter) getSqlMapClientTemplate()
						.queryForObject("studentRemedial.getApplied",
								getRemedialsList.get(j));
			
				System.out.println(getAppliedStatus.getApplied());
				//Boolean status = getAppliedStatus == null;

				if (Integer.valueOf(getAppliedStatus.getApplied()) == 0) {
					getRemedialsList.get(j).setApplied("NO");

				} else if (Integer.valueOf(getAppliedStatus.getApplied()) > 0) {
					getRemedialsList.get(j).setApplied("YES");
				}

				allRemedialsList.add(getRemedialsList.get(j));

			}
		}
		
		return allRemedialsList;
		}
		catch (Exception e) {
			logObj.error(e.getMessage());	
			 throw e;
		}
	}

    /**
     * Method insert student remedial details for which students applying 
     * @param program_course_key of remedial course
     * @param roll_number of student
     * @param course_code,course_status of remedial course
     * @param attempt_number for applying course
     * @param applying semster and it's start_date,end_date
     * @return String
     * @throws SQLException
     */
	public String insertRemedialDetails(StudentRemedialInfoGetter inputs) throws Exception {

		try
		{
		getSqlMapClientTemplate().insert("studentRemedial.insertRemedialRecords",inputs);
	
	
	return "Success";
		}
		catch (Exception e) {
			logObj.error(e.getMessage());
			 throw e;
		}

	}

}
