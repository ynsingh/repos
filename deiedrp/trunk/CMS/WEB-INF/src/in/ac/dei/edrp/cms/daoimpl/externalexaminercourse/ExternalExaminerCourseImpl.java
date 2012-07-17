/*
 * @(#) ExternalExaminerCourseImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.externalexaminercourse;

import in.ac.dei.edrp.cms.dao.externalexaminercourse.ExternalExaminerCourseConnect;
import in.ac.dei.edrp.cms.domain.externalexaminercourse.ExternalExaminerCourseInfoGetter;

import org.apache.log4j.Logger;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;
import java.util.StringTokenizer;

/**
 * This class consist the implementation of methods declared in
 * ExternalExaminerCourse interface.
 * 
 * @author Ashish
 * @date 7 Mar 2011
 * @version 1.0
 * @author Ashish Mohan
 * @date 27 Dec 2011
 * @version 2.0
 */
public class ExternalExaminerCourseImpl extends SqlMapClientDaoSupport
		implements ExternalExaminerCourseConnect {
	private Logger loggerObject = Logger
			.getLogger(ExternalExaminerCourseImpl.class);

	/**
	 * The method retrieves the list of courses defined for the university
	 * 
	 * @param input
	 *            object of the bean referenced
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ExternalExaminerCourseInfoGetter> getCoursesList(
			ExternalExaminerCourseInfoGetter input) {
		List<ExternalExaminerCourseInfoGetter> coursesList;

		loggerObject.info("In getCoursesList");

		input.setUniversityCode(input.getUserId().substring(1, 5));

		coursesList = getSqlMapClientTemplate().queryForList(
				"externalExaminerCourse.getcoursesList", input);

		return coursesList;
	}

	/**
	 * Method retrieves the list of external examiners for the concerned
	 * university
	 * 
	 * @param input
	 *            object of the bean referenced
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ExternalExaminerCourseInfoGetter> getExaminerList(
			ExternalExaminerCourseInfoGetter input) {
		List<ExternalExaminerCourseInfoGetter> examinerList;

		loggerObject.info("In getExaminerList");

		input.setUniversityCode(input.getUserId().substring(1, 5));

		examinerList = getSqlMapClientTemplate().queryForList(
				"externalExaminerCourse.getexaminersList", input);

		return examinerList;
	}

	/**
	 * Method inserts the course-examiner combination into the database
	 * 
	 * @param input
	 *            object of the bean referenced
	 * @return String
	 */
	public String insertExaminerCourse(ExternalExaminerCourseInfoGetter input) {
		input.setUniversityCode(input.getUserId().substring(1, 5));

		loggerObject.info("In insertExaminerCourse");

		getSqlMapClientTemplate().insert("externalExaminerCourse.insertRecord",
				input);

		return "success";
	}

	/**
	 * Method retrieves the records defined for the selected course code
	 * 
	 * @param input
	 *            object of the bean referenced
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ExternalExaminerCourseInfoGetter> getCourseExaminerList(
			ExternalExaminerCourseInfoGetter input) {
		List<ExternalExaminerCourseInfoGetter> recordList;

		loggerObject.info("In getCourseExaminerList");

		input.setUniversityCode(input.getUserId().substring(1, 5));

		recordList = getSqlMapClientTemplate().queryForList(
				"externalExaminerCourse.getrecordsList", input);

		return recordList;
	}

	/**
	 * Method for deleting the records for the selected course
	 * 
	 * @param input
	 *            object of the bean referenced
	 * @param items
	 * @return String
	 */
	public String deleteExaminerRecord(ExternalExaminerCourseInfoGetter input,
			StringTokenizer items) {
		String result = "failure";
		try {
			input.setUniversityCode(input.getUserId().substring(1, 5));

			loggerObject.info("In deleteExaminerRecord");

			while (items.hasMoreTokens()) {
				input.setExternalExaminerId(items.nextToken());
				getSqlMapClientTemplate().delete(
						"externalExaminerCourse.deleteRecords", input);
			}
			result = "success";
		} catch (Exception e) {
			loggerObject.error(e.getMessage());
		}
		return result;
	}

	/**
	 * Method for updating the record for the selected course
	 * 
	 * @param input
	 *            object of the bean referenced
	 * @param items
	 * @return String
	 */
	public String updateExaminerRecord(ExternalExaminerCourseInfoGetter input) {

		String result = "failure";

		try {
			input.setUniversityCode(input.getUserId().substring(1, 5));

			loggerObject.info("In updateExaminerRecord");

			getSqlMapClientTemplate().update(
					"externalExaminerCourse.updateRecords", input);

			result = "success";

		} catch (Exception e) {
			loggerObject.error(e.getMessage());
		}
		return result;
	}
}
