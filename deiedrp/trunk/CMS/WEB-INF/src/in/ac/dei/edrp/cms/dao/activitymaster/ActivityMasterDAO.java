/**
 * @(#) ActivityMasterDAO.java
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

package in.ac.dei.edrp.cms.dao.activitymaster;

import in.ac.dei.edrp.cms.domain.activitymaster.ActivityMaster;

import java.util.List;

/**
 * This interface consist the list of methods used
 * in ActivityMasterDAOImpl file.
 * @author Ankit Jain
 * @date 25 Jan 2011
 * @version 1.0
 */
public interface ActivityMasterDAO {
	
	/**
     * Method to get the program course details.
     * @return List of program Course
     */
	List<ActivityMaster> getProgramCourseHeaderList(ActivityMaster activityMaster);
	
	/**
     * Method to get the Semester Start & End Date
     * @param activityMaster Activity Master object
     * @return List of semester Start & End Date
     */
	List<ActivityMaster> getSemesterStartEndDate(ActivityMaster activityMaster);
	
	/**
     * Method to get the process details
     * @param activityMaster Activity Master object
     * @return List of process
     */
	List<ActivityMaster> getProcessList(ActivityMaster activityMaster);
	
	/**
     * Method to get the activity details
     * @return List of activity
     */
	List<ActivityMaster> getActivityList(ActivityMaster activityMaster);
	
	/**
     * Method to get the activityMaster details
     * @return List of activity master details
     */
	List<ActivityMaster> getActivityMasterDetials(ActivityMaster activityMaster);
	
	/**
     * Method to save the activityMaster details
     * @return void
     */
	void setActivityMasterData(ActivityMaster activityMaster);
	
	/**
     * Method to save the activityMaster details
     * @return void
     */
	String updateActivityMaster(ActivityMaster activityMaster);
	
	/**
     * Method to delete the activityMaster details
     * @return void
     */
	void deleteActivityMasterDetails(ActivityMaster activityMaster, String activityDataTokens);
	
	/**
     * Method to delete the activityMaster details
     * @return void
     */
	void changeActivityStatus(ActivityMaster activityMaster, String activityDataTokens);
	
	/**
     * Method to get the Existing activity sequence 
     * @return void
     */
	List<ActivityMaster> getExistingActivitySequence(ActivityMaster activityMaster);
	
	/**
     * Method to get the No of activity 
     * @return List of no of activity
     */
	String getActivityCount(ActivityMaster activityMaster);
	
	/**
     * Method to get the session start and end date.
     * @return List it will return the session start and end date. 
     */
	List<ActivityMaster> getSessionDetails(ActivityMaster activityMasterObject);
	
	/**
     * Method to get the entity.
     * @return List it will return the session start and end date. 
     */
	List<ActivityMaster> getEntity(ActivityMaster activityMasterObject);
	
}
