/**
 * @(#) CourseMasterService.java
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
package in.ac.dei.edrp.cms.dao.coursemaster;

import in.ac.dei.edrp.cms.domain.coursemaster.CourseMasterBean;
import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;

import java.util.List;

/**
 * The client interface for Course Master.
 *
 * @version 1.0 21 FEB 2011
 * @author MOHD AMIR
 */
public interface CourseMasterService
{
	/** getting list of entities */
	public List<CourseMasterBean> getEntities(String universityCode);
	
	/** getting list of programs */
	public List<CourseMasterBean> getPrograms(String ownerEntityId);
	
	/** getting list of branches */
	public List<CourseMasterBean> getBranch(CourseMasterBean courseMasterBean);
	
	/** getting list of specializations */
	public List<CourseMasterBean> getSpecialization(CourseMasterBean courseMasterBean);
	
	/** getting components info */
	public List<UnivRoleInfoGetter> getComponents(CourseMasterBean courseMasterBean);
	
	/** getting number of duplicate record */
	public int getDuplicateCourseCount(CourseMasterBean courseMasterBean);
	
	/** setting course details 
	 * @throws Exception */
	public void setCourseDetails(CourseMasterBean courseMasterBean) throws Exception;
	
	/** getting course details */
	public List<CourseMasterBean> getCourseDetails(CourseMasterBean courseMasterBean);
	
	/** deleting course details 
	 * @throws Exception */
	public int deleteCourseDetails(CourseMasterBean courseMasterBean) throws Exception;
	
	/** updating course details 
	 * @throws Exception */
	public int updateCourseDetails(CourseMasterBean courseMasterBean) throws Exception;
}