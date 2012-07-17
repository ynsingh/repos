/*
 * @(#) ProgramCourseSetupDAO.java
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
import java.util.HashSet;
import java.util.List;
/**
 * Creation date: 06-Jan-2011
 * The behavior of this class is as an interface.
 * This interface contains those method that is used for creating program course set up.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public interface ProgramCourseSetupDAO {
	
	/** 
	 * Method getProgramNameList is used for getting the program list.
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	List<ProgramMaster> getProgramNameList(String universityId);
	/** 
	 * Method getBranchNameList is used for getting the branch list.
	 * @param programName object of ProgramMaster
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	List<ProgramMaster> getBranchNameList(ProgramMaster programName);
	/** 
	 * Method getSemesterList is used for getting the semester list.
	 * @param programName object of ProgramMaster
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	List<ProgramMaster> getSemesterList(ProgramMaster programName);
	/** 
	 * Method getSpecializationList is used for getting the specialization list.
	 * @param programName object of ProgramMaster
	 * @return HashSet of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	HashSet<ProgramMaster> getSpecializationList(ProgramMaster programName);
	/** 
	 * Method getCourseList is used for getting the course list.
	 * @param programDetail object of ProgramMaster
	 * @return List list of the CourseDetails.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	List<List<CourseDetails>> getCourseList(ProgramMaster programDetail);
	/** 
	 * Method getExistingProgramCourseDetails is used for getting the course list.
	 * @param programHeader object of ProgramMaster
	 * @return List list of the CourseDetails.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	List<CourseDetails> getExistingProgramCourseDetails(ProgramMaster programHeader);
	/** 
	 * Method checkProgramHeader is used for checking the existence program header.
	 * @param programHeader object of ProgramMaster
	 * @return String key of the program header.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */	
	String checkProgramHeader(ProgramMaster programHeader);
	/** 
	 * Method gettingProgramCourseKey is used for getting the max value of key.
	 * @return String max value of the program header key.
	 */	
	String gettingProgramCourseKey();
	/** 
	 * Method insertProgramCourseHeader is used for inserting the program header details
	 * @param programHeader
	 */	
	void insertProgramCourseHeader(ProgramMaster programHeader);
	/** 
	 * Method insertProgramCourseDetail is used for inserting the course details
	 * @param courseDetails
	 * @param programCourseKey
	 * @param creatorId id of the login person
	 */
	void insertProgramCourseDetail(String courseDetails, String programCourseKey, String creatorId);
}
