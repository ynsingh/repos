/*
 * @(#) ResultReportImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.resultreport;

import in.ac.dei.edrp.cms.dao.resultreport.ResultReportConnect;
import in.ac.dei.edrp.cms.daoimpl.employee.EmployeeMasterImpl;
import in.ac.dei.edrp.cms.domain.cgpadivision.CgpaDivisionInfoGetter;
import in.ac.dei.edrp.cms.domain.degreelist.DegreeListInfoGetter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This file consist of the methods used for setting up
 * the result reports.
 * @author Ashish
 * @date 17 Nov 2011
 * @version 1.0
 */
public class ResultReportImpl extends SqlMapClientDaoSupport implements ResultReportConnect{
	
	private Logger loggerObject = Logger.getLogger(EmployeeMasterImpl.class);

	/**
	 * The method picks up the courseKeys along with
	 * semester details for the provided inputs
	 * @param infoGetter object of the referenced bean
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> getProgramCombinationDetails(
			DegreeListInfoGetter infoGetter) {
		
		List<DegreeListInfoGetter> resultList = new ArrayList<DegreeListInfoGetter>();
		
		if(infoGetter.getProgramPrintType().toUpperCase().contains("EVEN")){			
			infoGetter.setProgramResultSystem(0);			
		}else{			
			infoGetter.setProgramResultSystem(1);		
		}
		
		try {
			resultList = getSqlMapClient().queryForList("degreeList.getProgramDetails",infoGetter);
			
		} catch (Exception e) {
			loggerObject.error("Exception in getProgramCombinationDetails"+e);
		}
		
		return resultList;
	}

	/**
	 * The method picks the students for the program combination
	 * in order to print their result
	 * @param semesterListInfoGetter
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> getStudentsForCombination(
			DegreeListInfoGetter semesterListInfoGetter) {
		
		List<DegreeListInfoGetter> resultList = new ArrayList<DegreeListInfoGetter>();
		
		try {
			resultList = getSqlMapClient().queryForList("degreeList.getCombinationStudents",semesterListInfoGetter);
		} catch (Exception e) {
			loggerObject.error("Exception in getStudentsForCombination"+e);
		}
		
		
		return resultList;
	}

	/**
	 * The methos picks the divisions details defined
	 * @param cgpaDivisionInfoGetter
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<CgpaDivisionInfoGetter> getSetDivisionRecords(
			CgpaDivisionInfoGetter cgpaDivisionInfoGetter) {
		
		cgpaDivisionInfoGetter.setUniversityCode(cgpaDivisionInfoGetter.getUserId().substring(1, 5));      

	        List<CgpaDivisionInfoGetter> recordList = new ArrayList<CgpaDivisionInfoGetter>();
	        
	        try {
	        	
	        	 recordList = getSqlMapClientTemplate()
                 .queryForList("cgpaDivision.getRecords", cgpaDivisionInfoGetter);
				
			} catch (Exception e) {
				loggerObject.error("Exception in getSetDivisionRecords"+e);
			}

	       

	        return recordList;

	}

	/**
	 * The method gets the details of the students
	 * and prints verification report
	 * @param infoGetter obejct of the reference bean
	 * @return list 
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> getStudents4Verification(
			DegreeListInfoGetter infoGetter) {
		
		List<DegreeListInfoGetter> resultList = new ArrayList<DegreeListInfoGetter>();
		
		try {
			resultList = getSqlMapClient().queryForList("degreeList.getStudents4Verification",infoGetter);
		} catch (Exception e) {
			loggerObject.error("Exception in getStudents4Verification"+e);
		}
		
		
		return resultList;
	}

	/**
	 * The method gets the details of the students
	 * for the mentioned tencode
	 * @param infoGetter object of the referenced bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> getStudentsforCode(
			DegreeListInfoGetter infoGetter) {
		
		List<DegreeListInfoGetter> resultList = new ArrayList<DegreeListInfoGetter>();
		List<DegreeListInfoGetter> getter = new ArrayList<DegreeListInfoGetter>();	
		List<DegreeListInfoGetter> finalList = new ArrayList<DegreeListInfoGetter>();
		
		try {
			
			getter = getSqlMapClient().queryForList("degreeList.getmaxcgpafortencode",infoGetter);
			
			Iterator<DegreeListInfoGetter> iterator = getter.iterator();
			
			while (iterator.hasNext()) {
				DegreeListInfoGetter degreeListInfoGetter = (DegreeListInfoGetter) iterator
						.next();
				infoGetter.setTheoryCGPA(degreeListInfoGetter.getTheoryCGPA());
				infoGetter.setProgramId(degreeListInfoGetter.getProgramId());
				
				if(infoGetter.getProgramCourseKey().equalsIgnoreCase("CE")){
					
					resultList = getSqlMapClient().queryForList("degreeList.getcretificateexamstudents",infoGetter);					
					
				}else{
				
				resultList = getSqlMapClient().queryForList("degreeList.getcretificateexamstudentsforothercodes",infoGetter);				
				}
				
				finalList.addAll(resultList);
				
			}
			
			
		} catch (Exception e) {
			loggerObject.error("Exception in getStudentsforCode"+e);
		}	
		
		return finalList;
	}

	/**
	 * The method gets the topper students for UG&PG
	 * program in the university
	 * @param infoGetter object of the referenced bean 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> getStudentsforMAXUGPG(
			DegreeListInfoGetter infoGetter) {
		List<DegreeListInfoGetter> resultList = new ArrayList<DegreeListInfoGetter>();
		List<DegreeListInfoGetter>  getter = new ArrayList<DegreeListInfoGetter>();
		List<DegreeListInfoGetter> finalList = new ArrayList<DegreeListInfoGetter>();
		
		try {
			
			getter = getSqlMapClient().queryForList("degreeList.getmaxcgpafortencodes",infoGetter);
			
			
			Iterator<DegreeListInfoGetter> iterator = getter.iterator();
			
			while (iterator.hasNext()) {
				DegreeListInfoGetter degreeListInfoGetter = (DegreeListInfoGetter) iterator
						.next();
				
				infoGetter.setTheoryCGPA(degreeListInfoGetter.getTheoryCGPA());
				infoGetter.setProgramId(degreeListInfoGetter.getProgramId());
				
				resultList = getSqlMapClient().queryForList("degreeList.getmaxinUG",infoGetter);
				
			}
			
			finalList.addAll(resultList);
			
			
		} catch (Exception e) {
			loggerObject.error("Exception in getStudentsforMAXUGPG"+e);
		}	
		
		return finalList;
	}

	/**
	 * The method gets the list of the students
	 * who has secured max sgpa in mentioned courses
	 * @param infoGetter object of the referenced bean
	 * @return list of students
	 */
	@SuppressWarnings("unchecked")
	public List<DegreeListInfoGetter> getmaxoncourses(
			DegreeListInfoGetter infoGetter) {
		
		List<DegreeListInfoGetter>  programList = new ArrayList<DegreeListInfoGetter>();
		
		List<DegreeListInfoGetter> resultList = new ArrayList<DegreeListInfoGetter>();
		List<DegreeListInfoGetter> finalList = new ArrayList<DegreeListInfoGetter>();
		
		String cgpa = "0";
			
			try {
				
				programList = getSqlMapClient().queryForList("degreeList.getmaxavgonprogram",infoGetter);
			
			Iterator<DegreeListInfoGetter> iterator = programList.iterator();
			
			while (iterator.hasNext()) {
				DegreeListInfoGetter degreeListInfoGetter = (DegreeListInfoGetter) iterator
						.next();
				
				degreeListInfoGetter.setPassedFromSession(infoGetter.getPassedFromSession());
				degreeListInfoGetter.setPassedToSession(infoGetter.getPassedToSession());
				
				resultList = getSqlMapClient().queryForList("degreeList.getmaxavgofstudent",degreeListInfoGetter);
				
				if(resultList.size()==1){
					finalList.addAll(resultList);
				}else{	
					Iterator<DegreeListInfoGetter> iterator2 = resultList.iterator();
					while (iterator2.hasNext()) {
						DegreeListInfoGetter degreeListInfoGetter2 = (DegreeListInfoGetter) iterator2
								.next();
						
						if(degreeListInfoGetter2.getCgpa().compareTo(cgpa)>0){
							
							cgpa = degreeListInfoGetter2.getCgpa();
							
							finalList.add(degreeListInfoGetter2);							
						}
						
					}					
				}
			}
				
			} catch (Exception e) {
				loggerObject.error("Exception in getmaxoncourses"+e);
			}
		
		return finalList;
	}

	/**
	 * The method picks like codes and 
	 * gives the description of the respective course
	 * @param coursesTokenizer
	 * @return name of the course
	 */
	public DegreeListInfoGetter getCourseName(DegreeListInfoGetter infoGetter) {
		
//		DegreeListInfoGetter getter = new DegreeListInfoGetter();
		//********nupur code *********************
		DegreeListInfoGetter aa = new DegreeListInfoGetter();
		List<DegreeListInfoGetter> getter = new ArrayList<DegreeListInfoGetter>();
		//**************************8
		try {
			 getter = (ArrayList<DegreeListInfoGetter>) getSqlMapClient().queryForList("degreeList.getcoursename",infoGetter);
		} catch (Exception e) {
			loggerObject.error("Exception in getCourseName"+e);
		}
		/*
		 * Nupur code to remove the exception(too many parameter returns for CEC% course code
		 */
		if(getter.size()==0){
			aa.setCourseName("not available");
			getter.add(aa);
		}
			return getter.get(0);
		
		//************************
//		return getter;
	}
	
	

}
