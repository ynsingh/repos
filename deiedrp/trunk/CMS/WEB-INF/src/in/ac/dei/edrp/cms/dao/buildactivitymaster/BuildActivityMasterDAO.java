/**
 * @(#) BuildActivityMasterDAO.java
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

package in.ac.dei.edrp.cms.dao.buildactivitymaster;

import in.ac.dei.edrp.cms.domain.activitymaster.ActivityMaster;
import in.ac.dei.edrp.cms.domain.associatecoursewithinstructor.AssociateCourseWithInstructor;
import in.ac.dei.edrp.cms.domain.programgroup.ProgramGroup;
import in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails;

import java.util.List;

/**
 * This interface consist the list of methods used
 * in BuildActivityMasterDAOImpl file.
 * @author Ankit Jain
 * @date 14 feb 2011
 * @version 1.0
 */
public interface BuildActivityMasterDAO {

	/**
     * Method to get the session start and end date.
     * @return List it will return the session start and end date.
     */
	List<ActivityMaster> getSessionDetails(ActivityMaster activityMasterObject);

	/**
     * Method to set the activity master for the new session.
     * @return boolean if operation successful return true otherwise return false.
     */
	String setActivityForNewSession(ActivityMaster activityMasterObject);

	/**
     * Method to check whether activities are builded already.
     * @return String it return the status.
     */
	String checkBuildComplete(ActivityMaster activityMasterObject);


	/**
     * Method to get the entity.
     * @return List it will return the session start and end date.
     */
	List<ActivityMaster> getEntity(ActivityMaster activityMasterObject);

	/**
     * Method to get the program List
     * @return List of program
     */
	List<ActivityMaster> getProgramList(ActivityMaster activityMasterObject)throws Exception;

	/**
     * Method to get the branch list
     * @param programGroup ProgramGroup object
     * @return List of branch
     */
	List<ActivityMaster> getBranchList(ActivityMaster activityMasterObject)throws Exception;

	/**
     * Method to get the specialization list
     * @param programGroup ProgramGroup object
     * @return List of branch
     */
	List<ActivityMaster> getSpecializationList(ActivityMaster activityMasterObject)throws Exception;

	/**
     * Method to get the Semester List
     * @param programGroup ProgramGroup object
     * @return List of semester
     */
	List<ActivityMaster> getSemesterList(ActivityMaster activityMasterObject)throws Exception;

	/**
     * Method to build the all programs
     * @param programGroup ProgramGroup object
     * @return List of semester
     */
	String buildAllPrograms(ActivityMaster activityMasterObject)throws Exception;

	/**
     * Method to get the program course details.
     * @return List of program Course
     */
	List<ActivityMaster> getProgramCourseHeaderList(ActivityMaster activityMaster);

	/**
     * Method to build the Selected programs
     * @param programGroup ProgramGroup object
     * @return List of semester
     */
	String buildSelectedPrograms(ActivityMaster activityMasterObject, String selectedProgramSemester)throws Exception;

	/**
     * Method to check status
     * @param object of ActivityMaster
     * @return String
     */
	String checkStatus(ActivityMaster activityMasterObject);
}
