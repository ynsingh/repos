/**
 * @(#) CourseGroupImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.coursegroup;

import java.util.List;

import in.ac.dei.edrp.cms.dao.coursegroup.CourseGroupService;
import in.ac.dei.edrp.cms.daoimpl.universityreservation.UniversityReservationServiceImpl;
import in.ac.dei.edrp.cms.domain.coursegroup.CourseGroupBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentNumbersInfoGetter;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * this is Server side Implementation class for course group
 * 
 * @version 1.0 18 FEB 2011
 * @author MOHD AMIR
 */
public class CourseGroupImpl extends SqlMapClientDaoSupport implements
		CourseGroupService {

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(CourseGroupImpl.class);

	/**
	 * This method delete the Course Group details from database
	 * 
	 * @param courseGroupBean
	 *            , object of bean class CourseGroupBean
	 * @return deleteCount,Number of record deleted
	 */
	public int deleteCourseGroupDetails(CourseGroupBean courseGroupBean) {
		try {
			int deleteCount = getSqlMapClientTemplate().delete(
					"courseGroup.deleteCourseGroupInfo", courseGroupBean);
			return deleteCount;
		} finally{
		}
	}

	/**
	 * This method retrieves all course Group Details from database
	 * 
	 * @param universityId
	 *            , object of the CourseGroupBean
	 * @return courseGroupDetails containing Course Group Details
	 */
	@SuppressWarnings("unchecked")
	public List<CourseGroupBean> getCourseGroupDetails(
			CourseGroupBean courseGroupBean) {
		try {
			List<CourseGroupBean> courseGroupDetails = getSqlMapClientTemplate()
					.queryForList("courseGroup.getCourseGroupInfo",
							courseGroupBean);
			return courseGroupDetails;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return null;
	}

	/**
	 * This method get duplicate record count from database
	 * 
	 * @param courseGroupBean
	 *            , object of bean class CourseGroupBean
	 * @return count,Number of record found
	 */
	@SuppressWarnings("unchecked")
	public int getDuplicateCourseGroupCount(CourseGroupBean courseGroupBean) {
		try {
			List<CourseGroupBean> count = getSqlMapClientTemplate()
					.queryForList("courseGroup.getDuplicateRecordCount",
							courseGroupBean);
			return count.get(0).getCount();
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * This method insert the course group details into database
	 * 
	 * @param courseGroupBean
	 *            , object of bean class CourseGroupBean
	 */
	public void setCourseGroupDetails(CourseGroupBean courseGroupBean)  {
		try {
			getSqlMapClientTemplate().insert("courseGroup.setCourseGroupInfo",
					courseGroupBean);
		} //catch (Exception e) {
			//logObj.error(e.getMessage());
		finally{
		}
	}

	/**
	 * This method update the Course Group Details from database
	 * 
	 * @param courseGroupBean
	 *            , object of bean class CourseGroupBean
	 * @return updateCount, Number of record updated
	 */
	public int updateCourseGroupDetails(CourseGroupBean courseGroupBean) {
		return getSqlMapClientTemplate().update(
					"courseGroup.updateCourseGroupInfo", courseGroupBean);
			
	}

	/**
	 * This method retrieves ProgramCourseKey from database
	 * 
	 * @param courseGroupBean
	 *            , object of the CourseGroupBean
	 * @return programCourseKeyInfo, List of type StudentNumbersInfoGetter
	 */
	@SuppressWarnings("unchecked")
	public List<StudentNumbersInfoGetter> getProgramCourseKey(
			CourseGroupBean courseGroupBean) {
		try {
			List<StudentNumbersInfoGetter> programCourseKeyInfo = getSqlMapClientTemplate()
					.queryForList("studentenrollment.getprogramcoursekey",
							courseGroupBean);
			return programCourseKeyInfo;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return null;
	}

	/**
	 * This method retrieves list of CourseGroup with their code
	 * 
	 * @param courseGroupBean
	 *            , object of the CourseGroupBean
	 * @return courseGroupList, List of type CourseGroupBean
	 */
	@SuppressWarnings("unchecked")
	public List<CourseGroupBean> getCourseGroupList(
			CourseGroupBean courseGroupBean) {
		try {
			List<CourseGroupBean> courseGroupList = getSqlMapClientTemplate()
					.queryForList("courseGroup.getCourseGroupList",
							courseGroupBean);
			return courseGroupList;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return null;
	}
}
