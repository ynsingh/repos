/*
 * @(#) DegreeListImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.degreeList;

import in.ac.dei.edrp.cms.dao.degreelist.DegreeListConnect;
import in.ac.dei.edrp.cms.daoimpl.employee.EmployeeMasterImpl;
import in.ac.dei.edrp.cms.domain.degreelist.DegreeListInfoGetter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This file consist of the methods used for generating
 * the degree list reports for different programs 
 * @author Ashish
 * @date 21 Aug 2011
 * @version 1.0
 */
public class DegreeListImpl extends SqlMapClientDaoSupport implements DegreeListConnect{
	
	private Logger loggerObject = Logger.getLogger(EmployeeMasterImpl.class);

	/**
	 * The method generates the list of students whose
	 * degrees are are issued in the current session for B.Ed program
	 * @param infoGetter object of the referenced bean
	 * @return list of students
	 */
	@SuppressWarnings({ "unchecked" })
	public List<DegreeListInfoGetter> printDegreeListforTAPType(
			DegreeListInfoGetter infoGetter) {
		
		List<DegreeListInfoGetter> list = new ArrayList<DegreeListInfoGetter>();
		List<DegreeListInfoGetter> distinctStudentsList = new ArrayList<DegreeListInfoGetter>();
		
		try {
			
			/*
			 * list of studens with duplicate names 
			 */
			list = getSqlMapClient().queryForList("degreeList.getduplicatestudentmasterdetails",infoGetter);
			
			/*
			 * list of students other than duplicate names
			 */
			distinctStudentsList = getSqlMapClient().queryForList("degreeList.getdistinctstudents",infoGetter);
			
			list.addAll(distinctStudentsList);
			
			/*
			 * to sort the list by desired column(roll number in this case) 
			 */
			Collections.sort(list);
			
		} catch (Exception e) {
			loggerObject.error("Exception in printDegreeListforBEd"+e);
		}	
		
		return list;
	}

	/**
	 * The method retrieves the list of entities in the university
	 * @param input object of the referenced bean
	 * @return List of entities
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> getEntities(DegreeListInfoGetter input) {
		List entityList = null;
		
		try {
			/*
			 * list of entities in the university
			 */
			entityList = getSqlMapClient().queryForList("degreeList.getEntities",input);
		} catch (Exception e) {
			loggerObject.error("Exception in getEntities"+e);
		}
		return entityList;
	}

	/**
	 * The method retrieves the list of programs offered by the entity in the university
	 * @param input object of the referenced bean
	 * @return List of programs
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> getProgramForEntity(
			DegreeListInfoGetter input) {		
		List programList = null;
		
		try {
			/*
			 * the list of programs offered by the entity in the university
			 */
			programList = getSqlMapClient().queryForList("degreeList.getProgramsforEntity",input);
		} catch (Exception e) {
			loggerObject.error("Exception in getProgramForEntity"+e);
		}
		return programList;
	}

	/**
	 * The method retrieves the list of university session the entity in the university
	 * @param input object of the referenced bean
	 * @return List of university session
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> getUniversitySession(
			DegreeListInfoGetter input) {
		List sessionList = null;
		
		try {
			/*
			 * list of university session the entity in the university
			 */
			sessionList = getSqlMapClient().queryForList("degreeList.getUniversitySession",input);
		} catch (Exception e) {
			loggerObject.error("Exception in getUniversitySession"+e);
		}
		return sessionList;
	}

	/**
	 * The method generates the list of students whose
	 * degrees are to issued in the current session for High School
	 * @param infoGetter object of the referenced bean
	 * @return list of students
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> printDegreeListforHS(
			DegreeListInfoGetter infoGetter) {
		List<DegreeListInfoGetter> HSList = new ArrayList<DegreeListInfoGetter>();
		List<DegreeListInfoGetter> coursesList = new ArrayList<DegreeListInfoGetter>();
		
		
		String courseName = "";
		
		try {			
			
			/*
			 * list of studeents passed high school in the selected session
			 */
			HSList = getSqlMapClient().queryForList("degreeList.getHighSchoolStudents",infoGetter);
			
			/*
			 * list of course groups the students persued during the session
			 */
			coursesList = getSqlMapClient().queryForList("degreeList.getStudentsCourses",infoGetter);
			
			Iterator<DegreeListInfoGetter> iterator = HSList.iterator();
			
			Iterator<DegreeListInfoGetter> iterable = coursesList.iterator();		
			
			while (iterator.hasNext()) {
				DegreeListInfoGetter degreeListInfoGetter = (DegreeListInfoGetter) iterator
						.next();
				
				while (iterable.hasNext()) {
					DegreeListInfoGetter getter = (DegreeListInfoGetter) iterable
							.next();
					
					courseName = courseName+getter.getCourseName()+", ";					
					
				}
				
				degreeListInfoGetter.setCourseName(courseName);			
			}					
			
		} catch (Exception e) {
			
			loggerObject.error("Exception in printDegreeListforHS"+e);
			
		}
		
		return HSList;
	}

	/**
	 * The method generates the list of students whose
	 * degrees are to issued in the current session for Intermediate
	 * @param infoGetter object of the referenced bean
	 * @return list of students
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> printDegreeListforIN(
			DegreeListInfoGetter infoGetter) {
		
		List<DegreeListInfoGetter> INList = new ArrayList<DegreeListInfoGetter>();
		List<DegreeListInfoGetter> coursesList = new ArrayList<DegreeListInfoGetter>();
		
		
		String courseName = "";
		
		try {			
			
			/*
			 * list of students passed intermediate during the selected session
			 */
			INList = getSqlMapClient().queryForList("degreeList.getIntermediateStudents",infoGetter);
			
			/*
			 * list of course groups the students persued during the session
			 */
			coursesList = getSqlMapClient().queryForList("degreeList.getStudentsCourses",infoGetter);
			
			Iterator<DegreeListInfoGetter> iterator = INList.iterator();
			
			Iterator<DegreeListInfoGetter> iterable = coursesList.iterator();
			
			while (iterator.hasNext()) {
				DegreeListInfoGetter degreeListInfoGetter = (DegreeListInfoGetter) iterator
						.next();
				
				while (iterable.hasNext()) {
					DegreeListInfoGetter getter = (DegreeListInfoGetter) iterable
							.next();				
						
						courseName = courseName+getter.getCourseName()+", ";				
					
				}
				
				degreeListInfoGetter.setCourseName(courseName);			
			}					
			
		} catch (Exception e) {
			loggerObject.error("Exception in printDegreeListforIN"+e);
		}
		
		return INList;
	}

	/**
	 * The method retrieves the list of
	 * branches offered by the selected program in the university 
	 * @param infoGetter object of the referenced bean
	 * @return list of branches
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> getProgramBranches(
			DegreeListInfoGetter infoGetter) {
		List<DegreeListInfoGetter> branchList = new ArrayList<DegreeListInfoGetter>();
		try {
			/*
			 * list of branches offered by the selected program in the university
			 */
			branchList = getSqlMapClient().queryForList("degreeList.getBranchforprogram",infoGetter);			
			
		} catch (Exception e) {
			loggerObject.error("Exception in getProgramBranches"+e);
		}
		
		return branchList;
	}

	/**
	 * The method generates the list of students whose
	 * degrees are to issued in the current session for All programs
	 * @param infoGetter object of the referenced bean
	 * @return list of students
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> printDegreeListforOhterPrograms(
			DegreeListInfoGetter infoGetter) {
		
		List<DegreeListInfoGetter> list = new ArrayList<DegreeListInfoGetter>();
		List<DegreeListInfoGetter> distinctStudentsList = new ArrayList<DegreeListInfoGetter>();
		
		try {
			
			if(infoGetter.getProgramPrintType().equalsIgnoreCase("TAP")){
				
				list = printDegreeListforTAPType(infoGetter);
				
			}else if(infoGetter.getProgramPrintType().equalsIgnoreCase("SAG")){
				
				/*
				 * list of studens with duplicate names
				 */
				list = getSqlMapClient().queryForList("degreeList.getduplicatestudentdetails",infoGetter);
				
				/*
				 * list of students other than duplicate names
				 */
				distinctStudentsList = getSqlMapClient().queryForList("degreeList.getdistinctstudentdetails",infoGetter);
				
				list.addAll(distinctStudentsList);
				
			}			
			
		} catch (Exception e) {
			loggerObject.error("Exception in printDegreeListforOhterPrograms"+e);
		}	
		
		return list;
	}
	
	public String getResultSystem(DegreeListInfoGetter infoGetter) {
		try{
			int noOfGradeCourse=0;
			int noOfMarkCourse=0;
			infoGetter.setResultSystem("GR");
			DegreeListInfoGetter gradeCourseObject =(DegreeListInfoGetter) getSqlMapClient().queryForObject("degreeList.getResultSystemCount", infoGetter);
			
			noOfGradeCourse = gradeCourseObject.getProgramResultSystem();
			
			
			infoGetter.setResultSystem("MK");
			DegreeListInfoGetter marksCourseObject=(DegreeListInfoGetter) getSqlMapClient().queryForObject("degreeList.getResultSystemCount", infoGetter);
			
			noOfMarkCourse= marksCourseObject.getProgramResultSystem();			
			
			if(noOfGradeCourse>0){
				if(noOfMarkCourse==0){
					return "GR";
				}
			}
			else{
				if(noOfMarkCourse>0){
					if(noOfGradeCourse==0){
						return "MK";
					}
				}
			}			
		}catch(Exception e){
			System.out.println("Exception inside result system: "+e.getMessage());
		}
		return "XX";
	}	
}
