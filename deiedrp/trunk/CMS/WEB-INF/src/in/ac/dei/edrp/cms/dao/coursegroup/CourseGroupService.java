/**
 * @(#) CourseGroupService.java
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
package in.ac.dei.edrp.cms.dao.coursegroup;

import java.util.List;

import in.ac.dei.edrp.cms.domain.coursegroup.CourseGroupBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentNumbersInfoGetter;

/**
 * The client interface for University Reservation.
 *
 * @version 1.0 19 FEB 2011
 * @author MOHD AMIR
 */
public interface CourseGroupService
{
	/** getting Program Course Key from Database */
	public List<StudentNumbersInfoGetter> getProgramCourseKey(CourseGroupBean courseGroupBean);
	
	/** getting List of CourseGroup from Database */
	public List<CourseGroupBean> getCourseGroupList(CourseGroupBean courseGroupBean);
	
	/** Getting CourseGroup Details from database */
	public List<CourseGroupBean> getCourseGroupDetails(CourseGroupBean courseGroupBean);
	
	/** adding CourseGroup details into database */
	public void setCourseGroupDetails(CourseGroupBean courseGroupBean);
	
	/** Getting duplicate record count in database */
	public int getDuplicateCourseGroupCount(CourseGroupBean courseGroupBean);
	
	/** deleting  CourseGroup details from database */
	public int deleteCourseGroupDetails(CourseGroupBean courseGroupBean);
	
	/** Updating CourseGroup details into database */
	public int updateCourseGroupDetails(CourseGroupBean courseGroupBean);
}