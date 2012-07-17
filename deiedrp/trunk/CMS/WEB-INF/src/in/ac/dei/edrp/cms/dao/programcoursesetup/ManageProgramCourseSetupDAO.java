/*
 * @(#) ManageProgramCourseSetupDAO.java
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
package in.ac.dei.edrp.cms.dao.programcoursesetup;

import in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails;
import in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster;
import java.util.List;
/**
 * Creation date: 12-Jan-2011
 * The behavior of this class is as an interface.
 * This interface contains those method that is used for managing program course set up.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public interface ManageProgramCourseSetupDAO {

	/** 
	 * Method getProgramCourseHeaderList is used for getting the program course header list.
	 * @param universityId university of the logged in user.
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	List<ProgramMaster> getProgramCourseHeaderList(String universityId);
	/** 
	 * Method getProgramCourseDetails is used for getting the program course list according program header.
	 * @param programCourseKey
	 * @return List of the CourseDetails.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails
	 */
	List<CourseDetails> getProgramCourseDetails(String programCourseKey);
	/** 
	 * Method getProgramCourseCtgOptGrp is used for getting the category, group and option 
	 * of program course.
	 * @param universityId university of the logged in user.
	 * @return List of the CourseDetails.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails
	 */
	List<CourseDetails> getProgramCourseCtgOptGrp(String universityId);
	/** 
	 * Method updateProgramCourseCtgOptGrp is used for updating the category, group, option 
	 * and availability of program course.
	 * @param courseCtgGrpOpt
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails
	 */
	String updateProgramCourseCtgOptGrp(CourseDetails courseCtgGrpOpt);
	/** 
	 * Method deleteDesiredProgramCourse is used for deleting the selected program course.
	 * @param programCourseKey
	 * @param courseDetails
	 */
	String deleteDesiredProgramCourse(String programCourseKey, String courseDetails);
	/** 
	 * Method changeStatusDesiredProgramCourseHeader is used for changing the status of program course.
	 * @param programCourseKey
	 * @param modifierId
	 */
	void changeStatusDesiredProgramCourseHeader(String programCourseKey,String modifierId);
}
