/*
 * @(#) TempCoursePdfImpl.java
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


package in.ac.dei.edrp.cms.daoimpl.studentregistration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import in.ac.dei.edrp.cms.dao.studentregistration.TempCoursePdfDao;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

/**
 * This file consist of the methods used for getting 
 * the student temporary courses.
 * @author Ashutosh Pachauri
 * @date 06 June 2011
 * @version 1.0
 * @author Ashish Mohan
 * @date 01 Dec 2011
 * @version 2.0
 */
public class TempCoursePdfImpl extends SqlMapClientDaoSupport implements TempCoursePdfDao {

	/**
	 * Method for getting getting the student info
	 */
	@SuppressWarnings("unchecked")
	public List<StudentInfoGetter> getStudentCourses(
			StudentInfoGetter studentInfoGetter) {
		List<StudentInfoGetter> studentCourses = getSqlMapClientTemplate().queryForList("tempCoursePdf.getStudentCourses",studentInfoGetter);
		return studentCourses;
	}

	/**
	 * generating the courses as per student info.
	 */
			
	@SuppressWarnings("unchecked")
	public List<StudentInfoGetter> getStudentInfo(
			StartActivityBean startActivityBean) {
		List<StudentInfoGetter> studentList = getSqlMapClientTemplate().queryForList("tempCoursePdf.getPersonalInfo",startActivityBean);
		return studentList;
	}
	
	/**
	 * generating the courses as per student info. for temp chart
	 */
	@SuppressWarnings("unchecked")
	public List<StudentInfoGetter> getPersonalInfoForExtendedList(StudentInfoGetter studentInfoGetter) {
		List<StudentInfoGetter> studentInfo=null;
		List<StudentInfoGetter> courseList  = new ArrayList<StudentInfoGetter>();
		StudentInfoGetter previosSemesterMarks = new StudentInfoGetter();
		List<StudentInfoGetter> remedialList;
		List<StudentInfoGetter> checkList = new ArrayList<StudentInfoGetter>();	
		if(Integer.parseInt(studentInfoGetter.getSequenceNumber())==1){
			studentInfo = getSqlMapClientTemplate().queryForList("tempCoursePdf.getPersonalInfoForNewStudent",studentInfoGetter);	
		}
		else{
			studentInfo = getSqlMapClientTemplate().queryForList("tempCoursePdf.getPersonalInfoForExtendedList",studentInfoGetter);		
		}
		System.out.println("no of student"+studentInfo.size());
		boolean b;		
		for(StudentInfoGetter student : studentInfo){			
			b = true;						
			remedialList = new ArrayList<StudentInfoGetter>();		
			//course list for the new Students
			if(student.getAdmissionMode().equalsIgnoreCase("NEW")){
				studentInfoGetter.setRegistrationNumber(student.getRegistrationNumber());
				courseList = getSqlMapClientTemplate().queryForList("tempCoursePdf.getCourseListForNew",studentInfoGetter);			 
			}
			//courseListFor SWT OR NOR mode
			else if(student.getAdmissionMode().equalsIgnoreCase("SWT") || student.getAdmissionMode().equalsIgnoreCase("NOR")){
				studentInfoGetter.setEnrollmentNumber(student.getEnrollmentNumber());
				courseList = getSqlMapClientTemplate().queryForList("tempCoursePdf.getCourseListForSwtOrNor",studentInfoGetter);
			}
			
			//getting the previous semester marks for the case of SWT and NOR
			if(student.getAdmissionMode().equalsIgnoreCase("SWT") || student.getAdmissionMode().equalsIgnoreCase("NOR")){	
				if(student.getPrintAggregate().equalsIgnoreCase("TAP")){
					previosSemesterMarks = (StudentInfoGetter) getSqlMapClientTemplate().queryForObject("tempCoursePdf.getPreviousSemesterMarks",student);
				}
				else
					previosSemesterMarks = (StudentInfoGetter) getSqlMapClientTemplate().queryForObject("tempCoursePdf.getPreviousSemesterMarks1",student);									
			}	
			previosSemesterMarks = previosSemesterMarks==null?new StudentInfoGetter():previosSemesterMarks;
			System.out.println("pppppppppppp"+previosSemesterMarks);
			//for finding the remedial courses if exists
			if((student.getAdmissionMode().equalsIgnoreCase("SWT") || student.getAdmissionMode().equalsIgnoreCase("NOR"))&& student.getStatusInSemester().equalsIgnoreCase("REM")){				
				b = false;				
				remedialList = getSqlMapClientTemplate().queryForList("tempCoursePdf.getCourseWithRemedial",student);
			}
			System.out.println("nnnnnnnnnnnn"+student.getAttemptNumber());
			if(b==true){					
				checkList.add(new StudentInfoGetter(student.getEntityNumber(),student.getProgramCode(),student.getBranchId(),student.getSemesterSequence(),
				student.getRollNumber(),student.getStudentName(),
				student.getCategory(),student.getEnrollmentNumber(),student.getGender(),String.valueOf(student.getAttemptNumber()),student.getSpecializationId(),courseList,new ArrayList<StudentInfoGetter>(),
				previosSemesterMarks.getAggregateTheory(),previosSemesterMarks.getAggregatePractical()));				
			}else{				
				checkList.add(new StudentInfoGetter(student.getEntityNumber(),student.getProgramCode(),student.getBranchId(),student.getSemesterSequence(),
				student.getRollNumber(),student.getStudentName(),
				student.getCategory(),student.getEnrollmentNumber(),student.getGender(),String.valueOf(student.getAttemptNumber()),student.getSpecializationId(),courseList,remedialList,
				previosSemesterMarks.getAggregateTheory(),previosSemesterMarks.getAggregatePractical()));				
			}				
		}			
		return checkList;
	}
	
	/**------------------------------------------------------------
	 * generating the courses and sgpa as per student info. for main chart
	 *-------------------------------------------------------------
	*/
	
	@SuppressWarnings("unchecked")
	public List<StudentInfoGetter> getPersonalInfoForMainList(
			StudentInfoGetter studentInfoGetter) {
	
		List<StudentInfoGetter> courseList  = new ArrayList<StudentInfoGetter>();
		StudentInfoGetter previosSemesterMarks = new StudentInfoGetter();
		List<StudentInfoGetter> remedialList;
		List<StudentInfoGetter> checkList = new ArrayList<StudentInfoGetter>();		
		List<StudentInfoGetter> studentInfo = getSqlMapClientTemplate().queryForList("tempCoursePdf.getPersonalInfoForMainExtendedList",studentInfoGetter);		
		System.out.println("no of student "+studentInfo.size());
		
		boolean b;
		
		for(StudentInfoGetter student : studentInfo){
			
			b = true;
			
	
			remedialList = new ArrayList<StudentInfoGetter>();
		
			
			//course list for the  Students from master
				studentInfoGetter.setEnrollmentNumber(student.getEnrollmentNumber());
				courseList = getSqlMapClientTemplate().queryForList("tempCoursePdf.getMainCourseList",student);
			
			
			//getting the previous semester marks for the case of SWT and NOR
			if(Integer.parseInt(student.getSemesterSequence())>1){
				if(student.getPrintAggregate().equalsIgnoreCase("TAP"))
				{
				previosSemesterMarks = (StudentInfoGetter) getSqlMapClientTemplate().queryForObject("tempCoursePdf.getPreviousSemesterMarksMain",student);
				}
				else
					previosSemesterMarks = (StudentInfoGetter) getSqlMapClientTemplate().queryForObject("tempCoursePdf.getPreviousSemesterMarksMain1",student);
					
			}
						
			//for finding the remedial courses if exists
			if((Integer.parseInt(student.getSemesterSequence())>1) && (student.getStatusInSemester().equalsIgnoreCase("REM")||student.getStatusInSemester().equalsIgnoreCase("PAS") || student.getStatusInSemester().equalsIgnoreCase("REG")) && (!student.getModeOfEntry().equalsIgnoreCase("SW"))  )
			{
				b = false;
				
				remedialList = getSqlMapClientTemplate().queryForList("tempCoursePdf.getCourseWithRemedialMain",student);
				
			}
			
			if(b==true){
				
				checkList.add(new StudentInfoGetter(student.getEntityNumber(),student.getProgramCode(),student.getBranchId(),student.getSemesterSequence(),
						student.getRollNumber(),student.getStudentName(),
						student.getCategory(),student.getEnrollmentNumber(),student.getGender(),String.valueOf(student.getAttemptNumber()),student.getNewSpecialization(),courseList,new ArrayList<StudentInfoGetter>(),
						previosSemesterMarks.getAggregateTheory(),previosSemesterMarks.getAggregatePractical()));
				
			}else{
				
				checkList.add(new StudentInfoGetter(student.getEntityNumber(),student.getProgramCode(),student.getBranchId(),student.getSemesterSequence(),
						student.getRollNumber(),student.getStudentName(),
						student.getCategory(),student.getEnrollmentNumber(),student.getGender(),String.valueOf(student.getAttemptNumber()),student.getNewSpecialization(),courseList,remedialList,
						previosSemesterMarks.getAggregateTheory(),previosSemesterMarks.getAggregatePractical()));
				
			}
			
			
			
				
		}
				
		return checkList;
	}
	
}
