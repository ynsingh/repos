/*
 * @(#) DegreeListConnect.java
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
package in.ac.dei.edrp.cms.dao.degreelist;

import in.ac.dei.edrp.cms.domain.degreelist.DegreeListInfoGetter;

import java.util.List;

/**
 * This file consist of the methods used for setting up
 * the degree list reports.
 * @author Ashish
 * @date 21 Aug 2011
 * @version 1.0
 */
public interface DegreeListConnect {

	/**
	 * The method generates the list of students whose
	 * degrees are are issued in the current session for B.Ed program
	 * @param infoGetter object of the referenced bean
	 * @return list of students
	 */
	List<DegreeListInfoGetter> printDegreeListforTAPType(DegreeListInfoGetter infoGetter);

	/**
	 * The method retrieves the list of entities in the university
	 * @param input object of the referenced bean
	 * @return List of entities
	 */
	List<DegreeListInfoGetter> getEntities(DegreeListInfoGetter input);

	/**
	 * The method retrieves the list of programs offered by the entity in the university
	 * @param input object of the referenced bean
	 * @return List of programs
	 */
	List<DegreeListInfoGetter> getProgramForEntity(DegreeListInfoGetter input);
	
	/**
	 * The method retrieves the list of university session the entity in the university
	 * @param input object of the referenced bean
	 * @return List of university session
	 */
	List<DegreeListInfoGetter> getUniversitySession(DegreeListInfoGetter input);

	/**
	 * The method generates the list of students whose
	 * degrees are to issued in the current session for High School
	 * @param infoGetter object of the referenced bean
	 * @return list of students
	 */
	List<DegreeListInfoGetter> printDegreeListforHS(DegreeListInfoGetter infoGetter);

	/**
	 * The method generates the list of students whose
	 * degrees are to issued in the current session for Intermediate
	 * @param infoGetter object of the referenced bean
	 * @return list of students
	 */
	List<DegreeListInfoGetter> printDegreeListforIN(DegreeListInfoGetter infoGetter);

	/**
	 * The method retrieves the list of
	 * branches offered by the selected program in the university 
	 * @param infoGetter object of the referenced bean
	 * @return list of branches
	 */
	List<DegreeListInfoGetter> getProgramBranches(DegreeListInfoGetter infoGetter);

	/**
	 * The method generates the list of students whose
	 * degrees are to issued in the current session for All programs
	 * @param infoGetter object of the referenced bean
	 * @return list of students
	 */
	List<DegreeListInfoGetter> printDegreeListforOhterPrograms(
			DegreeListInfoGetter infoGetter);
	/**
	 * 
	 * @param infoGetter
	 * @return
	 */
	String getResultSystem(DegreeListInfoGetter infoGetter);

}
