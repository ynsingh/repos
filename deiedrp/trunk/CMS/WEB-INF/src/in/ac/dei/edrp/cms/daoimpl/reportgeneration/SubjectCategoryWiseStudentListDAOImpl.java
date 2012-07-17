/**
 * @(#) SubjectCategoryWiseStudentListDAOImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.reportgeneration;

import org.apache.log4j.Logger;
import in.ac.dei.edrp.cms.dao.reportgeneration.SubjectCategoryWiseStudentListDAO;
import in.ac.dei.edrp.cms.domain.reportgeneration.SubjectWiseMeritList;

import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This file consist of the methods used at the
 * Activity Master process.
 * @author Ankit Jain
 * @date 09 Apr 2011
 * @version 1.0
 */
public class SubjectCategoryWiseStudentListDAOImpl extends SqlMapClientDaoSupport implements SubjectCategoryWiseStudentListDAO{
private Logger loggerObject = Logger.getLogger(SubjectWiseMeritListDAOImpl.class);
    

	@SuppressWarnings("unchecked")
	public List<SubjectWiseMeritList> getCurrentSession(SubjectWiseMeritList subjectWiseMeritList) {
		List<SubjectWiseMeritList> sessionDates=null;
		try{
			sessionDates=getSqlMapClientTemplate().queryForList("subjectCategoryWiseStudentList.getCurrentSession", subjectWiseMeritList);
			loggerObject.info("in getCurrentSession");
		}
		catch (Exception e) {
			loggerObject.error("in getCurrentSession" + e);			
		}
		
		return sessionDates;
	}
	
	@SuppressWarnings("unchecked")
	public List<SubjectWiseMeritList> getSemesterDates(SubjectWiseMeritList subjectWiseMeritList) {
		List<SubjectWiseMeritList> semesterDates=null;
		try{
			semesterDates=getSqlMapClientTemplate().queryForList("subjectCategoryWiseStudentList.getSemesterDates", subjectWiseMeritList);
			loggerObject.info("in getSemesterDates");
		}
		catch (Exception e) {
			loggerObject.error("in getSemesterDates" + e);			
		}
		
		return semesterDates;
	}	

	@SuppressWarnings("unchecked")
	public List<SubjectWiseMeritList> getCourseGroup(SubjectWiseMeritList subjectWiseMeritList) {
		List<SubjectWiseMeritList> courseGroupList=null;
		
		try{
		     courseGroupList=getSqlMapClientTemplate().queryForList("subjectCategoryWiseStudentList.getCourseGroupList", subjectWiseMeritList);
		      
			loggerObject.info("in getCourseGroup");
		}
		catch (Exception e) {
			loggerObject.error("in getCourseGroup" + e);			
		}
		return courseGroupList;		
	}	
	
	
	@SuppressWarnings("unchecked")
	public List<SubjectWiseMeritList> getStudentListCategoryGenderWise(SubjectWiseMeritList subjectWiseMeritList) {
		
		List<SubjectWiseMeritList> studentList=new ArrayList<SubjectWiseMeritList>();
		
		try{ 	
		     studentList.addAll(getSqlMapClientTemplate().queryForList("subjectCategoryWiseStudentList.getStudentListCategoryGenderWise", subjectWiseMeritList));
		     loggerObject.info("in getStudentListCategoryGenderWise");
		}
		catch (Exception e) {
			loggerObject.error("in getStudentListCategoryGenderWise" + e);			
		}
		return studentList;		
	}
	
	@SuppressWarnings("unchecked")
	public List<SubjectWiseMeritList> getStudentListCategoryWise(SubjectWiseMeritList subjectWiseMeritList) {
		
		List<SubjectWiseMeritList> studentList=new ArrayList<SubjectWiseMeritList>();
		try{
		studentList.addAll(getSqlMapClientTemplate().queryForList("subjectCategoryWiseStudentList.getStudentListCategoryWise", subjectWiseMeritList));
			loggerObject.info("in getStudentListCategoryWise");
		}
		catch (Exception e) {
			loggerObject.error("in getStudentListCategoryWise" + e);

		}
		return studentList;		
	}
	
	public SubjectWiseMeritList getEntityInfo(SubjectWiseMeritList subjectWiseMeritList) {
		
		SubjectWiseMeritList entityInfoObject=null;
		try{
			entityInfoObject = (SubjectWiseMeritList) getSqlMapClientTemplate().queryForObject("subjectCategoryWiseStudentList.getEntityInfo", subjectWiseMeritList);
			loggerObject.info("in getEntityInfo");
		}
		catch (Exception e) {
			loggerObject.error("in getEntityInfo" + e);
		}
		return entityInfoObject;		
	}

}