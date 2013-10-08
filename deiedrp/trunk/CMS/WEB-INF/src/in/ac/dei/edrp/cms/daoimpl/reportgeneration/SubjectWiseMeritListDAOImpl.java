/**
 * @(#) SubjectWiseMeritListDAOImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.reportgeneration;

import org.apache.log4j.Logger;

import in.ac.dei.edrp.cms.dao.reportgeneration.SubjectWiseMeritListDAO;
import in.ac.dei.edrp.cms.domain.reportgeneration.SubjectWiseMeritList;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This file consist of the methods used at the
 * Activity Master process.
 * @author Ankit Jain
 * @date 25 Jan 2011
 * @version 1.0
 */
public class SubjectWiseMeritListDAOImpl extends SqlMapClientDaoSupport implements SubjectWiseMeritListDAO{
	
	private Logger loggerObject = Logger.getLogger(SubjectWiseMeritListDAOImpl.class);
    
	/**
     * This method will fetch the ProgramCourse Information
     * @return List
     */

//	public List<SubjectWiseMeritList> getProgramCourseKey(SubjectWiseMeritList subjectWiseMeritList) {
//		
//		List<SubjectWiseMeritList> studentList=null;
//		StringTokenizer semesters = new StringTokenizer(semesterTokens, ",");
//		
//		List<String> programCourseKeyList=new ArrayList<String>();
//		String programCourseKeyArray[]=new String[10];
//		
//		int i=0;
//		try{			
//	       	 while(semesters.hasMoreTokens()){
//		   		subjectWiseMeritList.setSemesterCode(semesters.nextToken()); 
//		   		SubjectWiseMeritList programCourseList=(SubjectWiseMeritList) getSqlMapClientTemplate().queryForObject("subjectWiseMeritList.getProgramCourseKey", subjectWiseMeritList);
//		   		programCourseKeyList.add(programCourseList.getProgramCourseKey());
////		   		programCourseKeyArray[i]=programCourseList.getProgramCourseKey();
//		   		i++;
//		   	}
//	       	
//	       	
//	       	subjectWiseMeritList.setProgramCourseKeyList(programCourseKeyList);
//	       	int noOfProgramCourseKey=programCourseKeyList.size();
//	       	
//	       	switch(noOfProgramCourseKey){
//		       	case 1:
//		       	{
//		       		subjectWiseMeritList.setProgramCourseKey1(programCourseKeyList.get(0));
//		       		break;
//		       	}
//		       	case 2:
//		       	{
//		       		subjectWiseMeritList.setProgramCourseKey1(programCourseKeyList.get(0));
//		       		subjectWiseMeritList.setProgramCourseKey2(programCourseKeyList.get(1));
//		       		break;
//		       	}
//		       	case 3:
//		       	{
//		       		subjectWiseMeritList.setProgramCourseKey1(programCourseKeyList.get(0));
//		       		subjectWiseMeritList.setProgramCourseKey2(programCourseKeyList.get(1));
//			       	subjectWiseMeritList.setProgramCourseKey3(programCourseKeyList.get(2));
//			       	break;
//			       	
//		       	}
//		       	case 4:
//		       	{
//		       		subjectWiseMeritList.setProgramCourseKey1(programCourseKeyList.get(0));
//		       		subjectWiseMeritList.setProgramCourseKey2(programCourseKeyList.get(1));
//			       	subjectWiseMeritList.setProgramCourseKey3(programCourseKeyList.get(2));
//			       	subjectWiseMeritList.setProgramCourseKey4(programCourseKeyList.get(3));
//			       	break;
//		       	}
//		       	case 5:
//		       	{
//		       		subjectWiseMeritList.setProgramCourseKey1(programCourseKeyList.get(0));
//		       		subjectWiseMeritList.setProgramCourseKey2(programCourseKeyList.get(1));
//			       	subjectWiseMeritList.setProgramCourseKey3(programCourseKeyList.get(2));
//			       	subjectWiseMeritList.setProgramCourseKey4(programCourseKeyList.get(3));	
//			       	subjectWiseMeritList.setProgramCourseKey5(programCourseKeyList.get(4));
//			       	break;
//		       	}
//		       	case 6:
//		       	{
//		       		subjectWiseMeritList.setProgramCourseKey1(programCourseKeyList.get(0));
//		       		subjectWiseMeritList.setProgramCourseKey2(programCourseKeyList.get(1));
//			       	subjectWiseMeritList.setProgramCourseKey3(programCourseKeyList.get(2));
//			       	subjectWiseMeritList.setProgramCourseKey4(programCourseKeyList.get(3));	
//			       	subjectWiseMeritList.setProgramCourseKey5(programCourseKeyList.get(4));
//			       	subjectWiseMeritList.setProgramCourseKey6(programCourseKeyList.get(5));
//			       	break;
//		       	}
//		       	case 7:
//		       	{
//		       		subjectWiseMeritList.setProgramCourseKey1(programCourseKeyList.get(0));
//		       		subjectWiseMeritList.setProgramCourseKey2(programCourseKeyList.get(1));
//			       	subjectWiseMeritList.setProgramCourseKey3(programCourseKeyList.get(2));
//			       	subjectWiseMeritList.setProgramCourseKey4(programCourseKeyList.get(3));	
//			       	subjectWiseMeritList.setProgramCourseKey5(programCourseKeyList.get(4));
//			       	subjectWiseMeritList.setProgramCourseKey6(programCourseKeyList.get(5));
//			       	subjectWiseMeritList.setProgramCourseKey7(programCourseKeyList.get(6));
//			       	break;
//		       	}
//		       	case 8:
//		       	{
//		       		subjectWiseMeritList.setProgramCourseKey1(programCourseKeyList.get(0));
//		       		subjectWiseMeritList.setProgramCourseKey2(programCourseKeyList.get(1));
//			       	subjectWiseMeritList.setProgramCourseKey3(programCourseKeyList.get(2));
//			       	subjectWiseMeritList.setProgramCourseKey4(programCourseKeyList.get(3));	
//			       	subjectWiseMeritList.setProgramCourseKey5(programCourseKeyList.get(4));
//			       	subjectWiseMeritList.setProgramCourseKey6(programCourseKeyList.get(5));
//			       	subjectWiseMeritList.setProgramCourseKey7(programCourseKeyList.get(6));
//			       	subjectWiseMeritList.setProgramCourseKey8(programCourseKeyList.get(7));
//			       	break;
//		       	}
//		       	case 9:
//		       	{
//		       		subjectWiseMeritList.setProgramCourseKey1(programCourseKeyList.get(0));
//		       		subjectWiseMeritList.setProgramCourseKey2(programCourseKeyList.get(1));
//			       	subjectWiseMeritList.setProgramCourseKey3(programCourseKeyList.get(2));
//			       	subjectWiseMeritList.setProgramCourseKey4(programCourseKeyList.get(3));	
//			       	subjectWiseMeritList.setProgramCourseKey5(programCourseKeyList.get(4));
//			       	subjectWiseMeritList.setProgramCourseKey6(programCourseKeyList.get(5));
//			       	subjectWiseMeritList.setProgramCourseKey7(programCourseKeyList.get(6));
//			       	subjectWiseMeritList.setProgramCourseKey8(programCourseKeyList.get(7));
//			       	subjectWiseMeritList.setProgramCourseKey9(programCourseKeyList.get(8));
//			       	break;
//		       	}
//		       	case 10:
//		       	{
//		       		subjectWiseMeritList.setProgramCourseKey1(programCourseKeyList.get(0));
//		       		subjectWiseMeritList.setProgramCourseKey2(programCourseKeyList.get(1));
//			       	subjectWiseMeritList.setProgramCourseKey3(programCourseKeyList.get(2));
//			       	subjectWiseMeritList.setProgramCourseKey4(programCourseKeyList.get(3));	
//			       	subjectWiseMeritList.setProgramCourseKey5(programCourseKeyList.get(4));
//			       	subjectWiseMeritList.setProgramCourseKey6(programCourseKeyList.get(5));
//			       	subjectWiseMeritList.setProgramCourseKey7(programCourseKeyList.get(6));
//			       	subjectWiseMeritList.setProgramCourseKey8(programCourseKeyList.get(7));
//			       	subjectWiseMeritList.setProgramCourseKey9(programCourseKeyList.get(8));
//			       	subjectWiseMeritList.setProgramCourseKey10(programCourseKeyList.get(9));
//			       	break;
//		       	}		       	
//	       	}
//	       	       	
//	       	studentList=getSqlMapClientTemplate().queryForList("subjectWiseMeritList.getGroupWiseStudent", subjectWiseMeritList);
//	       	
//	       	
////	       	subjectWiseMeritList.setProgramCourseKey1(programCourseList.get(0).getProgramCourseKey());
////	       	subjectWiseMeritList.setProgramCourseKey2(programCourseList.get(1).getProgramCourseKey());
////	       	subjectWiseMeritList.setProgramCourseKey3(programCourseList.get(2).getProgramCourseKey());
////	       	subjectWiseMeritList.setProgramCourseKey4(programCourseList.get(3).getProgramCourseKey());
////	       	subjectWiseMeritList.setProgramCourseKey5(programCourseList.get(4).getProgramCourseKey());
////	       	subjectWiseMeritList.setProgramCourseKey6(programCourseList.get(5).getProgramCourseKey());
////	       	subjectWiseMeritList.setProgramCourseKey7(programCourseList.get(6).getProgramCourseKey());
////	       	subjectWiseMeritList.setProgramCourseKey8(programCourseList.get(7).getProgramCourseKey());
////	       	subjectWiseMeritList.setProgramCourseKey9(programCourseList.get(8).getProgramCourseKey());
////	       	subjectWiseMeritList.setProgramCourseKey10(programCourseList.get(9).getProgramCourseKey());
//
//	    }
//		catch (Exception e) {
//			loggerObject.error("in getDetails" + e);
//		}
//		return studentList;
//	}

	@SuppressWarnings("unchecked")
	public List<SubjectWiseMeritList> getEntityList(SubjectWiseMeritList subjectWiseMeritList) {
		List<SubjectWiseMeritList> entityList=null;
		try{
			entityList=getSqlMapClientTemplate().queryForList("subjectWiseMeritList.entityList", subjectWiseMeritList);
			loggerObject.info("in getEntityList");
		}
		catch (Exception e) {
			loggerObject.error("in getEntityList" + e);			
		}
		
		return entityList;		
	}
	
	@SuppressWarnings("unchecked")
	public List<SubjectWiseMeritList> getProgramList(SubjectWiseMeritList subjectWiseMeritList) {
		List<SubjectWiseMeritList> programList=null;
		try{
			programList=getSqlMapClientTemplate().queryForList("subjectWiseMeritList.getProgramList", subjectWiseMeritList);
			loggerObject.info("in getProgramList");
		}
		catch (Exception e) {
			loggerObject.error("in getProgramList" + e);			
		}
		
		return programList;		
	}

	@SuppressWarnings("unchecked")
	public List<SubjectWiseMeritList> getBranchList(SubjectWiseMeritList subjectWiseMeritList) {
		List<SubjectWiseMeritList> branchList=null;
		try{
			branchList=getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.getBranchList", subjectWiseMeritList);
			loggerObject.info("in getBranchList");
		}
		catch (Exception e) {
			loggerObject.error("in getBranchList" + e);			
		}
		return branchList;		
	}

	@SuppressWarnings("unchecked")
	public List<SubjectWiseMeritList> getSpecializationList(SubjectWiseMeritList subjectWiseMeritList) {
		List<SubjectWiseMeritList> specializationList=null;
		try{
			specializationList=getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.getSpecializationList", subjectWiseMeritList);
			loggerObject.info("in getSpecializationList");
		}
		catch (Exception e) {
			loggerObject.error("in getSpecializationList" + e);			
		}
		return specializationList;		
	}

	@SuppressWarnings("unchecked")
	public List<SubjectWiseMeritList> getSemesterList(SubjectWiseMeritList subjectWiseMeritList) {
		List<SubjectWiseMeritList> semesterList=null;
		try{
			semesterList=getSqlMapClientTemplate().queryForList("subjectWiseMeritList.getSemesterList", subjectWiseMeritList);
			loggerObject.info("in getSemesterList");
		}
		catch (Exception e) {
			loggerObject.error("in getSemesterList" + e);			
		}
		return semesterList;
		
	}

	@SuppressWarnings("unchecked")
	public List<SubjectWiseMeritList> getCourseGroupList(SubjectWiseMeritList subjectWiseMeritList, String semesterTokens) {
		List<SubjectWiseMeritList> courseGroupList=null;
		StringTokenizer semesters = new StringTokenizer(semesterTokens, ",");
		StringTokenizer temp=new StringTokenizer(semesterTokens, ",");
		
		try{
			int noOfTokens = 0;
			 while(temp.hasMoreTokens()){
			   	temp.nextToken();
			   	noOfTokens++;
			 }
			 
			 switch(noOfTokens){
				 case 1:
				 {
					 subjectWiseMeritList.setSemester1(semesters.nextToken());
					 break;
				 }
				 case 2:
				 {
					 subjectWiseMeritList.setSemester1(semesters.nextToken());
					 subjectWiseMeritList.setSemester2(semesters.nextToken());					 
				     break;
				 }
				 case 3:
				 {
					 subjectWiseMeritList.setSemester1(semesters.nextToken());
					 subjectWiseMeritList.setSemester2(semesters.nextToken());
					 subjectWiseMeritList.setSemester3(semesters.nextToken());
					 break;
				 }
				 case 4:
				 {
					 subjectWiseMeritList.setSemester1(semesters.nextToken());
					 subjectWiseMeritList.setSemester2(semesters.nextToken());
					 subjectWiseMeritList.setSemester3(semesters.nextToken());
					 subjectWiseMeritList.setSemester4(semesters.nextToken());
					 break;
				 }
				 case 5:
				 {
					 subjectWiseMeritList.setSemester1(semesters.nextToken());
					 subjectWiseMeritList.setSemester2(semesters.nextToken());
					 subjectWiseMeritList.setSemester3(semesters.nextToken());
					 subjectWiseMeritList.setSemester4(semesters.nextToken());
					 subjectWiseMeritList.setSemester5(semesters.nextToken());
				     break;
				 }
				 case 6:
				 {
					 subjectWiseMeritList.setSemester1(semesters.nextToken());
					 subjectWiseMeritList.setSemester2(semesters.nextToken());
					 subjectWiseMeritList.setSemester3(semesters.nextToken());
					 subjectWiseMeritList.setSemester4(semesters.nextToken());
					 subjectWiseMeritList.setSemester5(semesters.nextToken());
					 subjectWiseMeritList.setSemester6(semesters.nextToken());
				     break;
				 }
				 case 7:
				 {
					 subjectWiseMeritList.setSemester1(semesters.nextToken());
					 subjectWiseMeritList.setSemester2(semesters.nextToken());
					 subjectWiseMeritList.setSemester3(semesters.nextToken());
					 subjectWiseMeritList.setSemester4(semesters.nextToken());
					 subjectWiseMeritList.setSemester5(semesters.nextToken());
					 subjectWiseMeritList.setSemester6(semesters.nextToken());
					 subjectWiseMeritList.setSemester7(semesters.nextToken());	
					 break;
				 }
				 case 8:
				 {
					 subjectWiseMeritList.setSemester1(semesters.nextToken());
					 subjectWiseMeritList.setSemester2(semesters.nextToken());
					 subjectWiseMeritList.setSemester3(semesters.nextToken());
					 subjectWiseMeritList.setSemester4(semesters.nextToken());
					 subjectWiseMeritList.setSemester5(semesters.nextToken());
					 subjectWiseMeritList.setSemester6(semesters.nextToken());
					 subjectWiseMeritList.setSemester7(semesters.nextToken());
					 subjectWiseMeritList.setSemester8(semesters.nextToken());
					 break;
				 }
				 case 9:
				 {
					 subjectWiseMeritList.setSemester1(semesters.nextToken());
					 subjectWiseMeritList.setSemester2(semesters.nextToken());
					 subjectWiseMeritList.setSemester3(semesters.nextToken());
					 subjectWiseMeritList.setSemester4(semesters.nextToken());
					 subjectWiseMeritList.setSemester5(semesters.nextToken());
					 subjectWiseMeritList.setSemester6(semesters.nextToken());
					 subjectWiseMeritList.setSemester7(semesters.nextToken());
					 subjectWiseMeritList.setSemester8(semesters.nextToken());
					 subjectWiseMeritList.setSemester9(semesters.nextToken());
					 break;
				 }
				 case 10:
				 {			
					subjectWiseMeritList.setSemester1(semesters.nextToken());
					subjectWiseMeritList.setSemester2(semesters.nextToken());
					subjectWiseMeritList.setSemester3(semesters.nextToken());
					subjectWiseMeritList.setSemester4(semesters.nextToken());
					subjectWiseMeritList.setSemester5(semesters.nextToken());
					subjectWiseMeritList.setSemester6(semesters.nextToken());
					subjectWiseMeritList.setSemester7(semesters.nextToken());
					subjectWiseMeritList.setSemester8(semesters.nextToken());
					subjectWiseMeritList.setSemester9(semesters.nextToken());
					subjectWiseMeritList.setSemester10(semesters.nextToken());
					 break;
				 }
				 default:
				 {
					 break;
				 }
			 
			 }
			 
			 courseGroupList=getSqlMapClientTemplate().queryForList("subjectWiseMeritList.getCourseGroupList", subjectWiseMeritList);
			 loggerObject.info("in getCourseGroupList");
		}
		catch (Exception e) {
			loggerObject.error("in getCourseGroupList" + e);
		}
		return courseGroupList;		
	}

	public List<SubjectWiseMeritList> getStudentDataList(SubjectWiseMeritList subjectWiseMeritList) {
		List<SubjectWiseMeritList> studentDataList = new ArrayList<SubjectWiseMeritList>();
		studentDataList = getSqlMapClientTemplate().queryForList("subjectWiseMeritList.getGroupWiseStudent", subjectWiseMeritList);
		return studentDataList;
	}
}
