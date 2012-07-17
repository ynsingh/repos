/*
 * @(#) ResultReportConnect.java
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
package in.ac.dei.edrp.cms.dao.resultreport;

import in.ac.dei.edrp.cms.domain.cgpadivision.CgpaDivisionInfoGetter;
import in.ac.dei.edrp.cms.domain.degreelist.DegreeListInfoGetter;

import java.util.List;
import java.util.StringTokenizer;

/**
 * This file consist of the methods used for setting up
 * the result reports.
 * @author Ashish
 * @date 17 Nov 2011
 * @version 1.0
 */
public interface ResultReportConnect {
	
	
	/**
	 * The method picks up the courseKeys along with
	 * semester details for the provided inputs
	 * @param infoGetter object of the referenced bean
	 * @return list
	 */
	List<DegreeListInfoGetter> getProgramCombinationDetails(
			DegreeListInfoGetter infoGetter);

	/**
	 * The method picks the students for the program combination
	 * in order to print their result
	 * @param semesterListInfoGetter
	 * @return list
	 */
	List<DegreeListInfoGetter> getStudentsForCombination(
			DegreeListInfoGetter semesterListInfoGetter);

	/**
	 * The methos picks the divisions details defined
	 * @param cgpaDivisionInfoGetter
	 * @return list
	 */
	List<CgpaDivisionInfoGetter> getSetDivisionRecords(
			CgpaDivisionInfoGetter cgpaDivisionInfoGetter);
	/**
	 * The method gets the details of the students
	 * and prints verification report
	 * @param infoGetter obejct of the reference bean
	 * @return list 
	 */
	List<DegreeListInfoGetter> getStudents4Verification(
			DegreeListInfoGetter infoGetter);

	/**
	 * The method gets the details of the students
	 * for the mentioned tencode
	 * @param infoGetter object of the referenced bean 
	 * @return list
	 */
	List<DegreeListInfoGetter> getStudentsforCode(
			DegreeListInfoGetter infoGetter);
	/**
	 * The method gets the topper students for UG&PG
	 * program in the university
	 * @param infoGetter object of the referenced bean 
	 * @return list
	 */
	List<DegreeListInfoGetter> getStudentsforMAXUGPG(
			DegreeListInfoGetter infoGetter);

	/**
	 * The method gets the list of the students
	 * who has secured max sgpa in mentioned courses
	 * @param infoGetter object of the referenced bean
	 * @return list of students
	 */
	List<DegreeListInfoGetter> getmaxoncourses(DegreeListInfoGetter infoGetter);
	
	/**
	 * The method picks like codes and 
	 * gives the description of the respective course
	 * @param infoGetter object on referenced bean
	 * @return name of the course
	 */
	DegreeListInfoGetter getCourseName(DegreeListInfoGetter infoGetter );

}
