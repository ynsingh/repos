/**
 * @(#) ActivityMasterDAOImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.activitymaster;

import in.ac.dei.edrp.cms.dao.activitymaster.ActivityMasterDAO;
import in.ac.dei.edrp.cms.domain.activitymaster.ActivityMaster;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.StringTokenizer;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This file consist of the methods used at the
 * Activity Master process.
 * @author Ankit Jain
 * @date 25 Jan 2011
 * @version 1.0
 */
public class ActivityMasterDAOImpl extends SqlMapClientDaoSupport implements ActivityMasterDAO{
	
	private Logger loggerObject = Logger.getLogger(ActivityMasterDAOImpl.class);
    
	/**
     * This method will fetch the ProgramCourse Information
     * @return List
     */
	@SuppressWarnings("unchecked")
	public List<ActivityMaster> getProgramCourseHeaderList(ActivityMaster activityMaster) {
		List<ActivityMaster> programCourse=null;
		
		try{
			programCourse=getSqlMapClientTemplate().queryForList("activityMaster.getProgramCourseHeaderList", activityMaster);
			loggerObject.info("in getProgramCourseHeaderList");
		}
		catch (Exception e) {
			loggerObject.error("in getProgramCourseHeaderList" + e);			
		}
		return programCourse;
	}
	
	/**
     * This method will fetch the Process Information
     * @return List
     */
	@SuppressWarnings("unchecked")
	
	public List<ActivityMaster> getProcessList(ActivityMaster activityMasterObj) {
		List<ActivityMaster> processList=null;
		try{
			processList = getSqlMapClientTemplate().queryForList("activityMaster.getProcessList", activityMasterObj);
			loggerObject.info("in Process List");
		}
		catch (Exception e) {
			loggerObject.error("in getProcessList" + e);
		}
		return processList;
	}
	
	/**
     * This method will fetch the Activity Information
     * @return List
     */
	@SuppressWarnings("unchecked")
	
	public List<ActivityMaster> getActivityList(ActivityMaster activityMasterObj) {
		List<ActivityMaster> activityMaster=null;
		try{
			activityMaster = getSqlMapClientTemplate().queryForList("activityMaster.getActivityList", activityMasterObj);
			loggerObject.info("in Activity List");
		}
		catch (Exception e) {
			loggerObject.error("in getActivityList" + e);
		}
		return activityMaster; 
	}
	
	/**
     * This method will return the semester start & end date Information
     * @return List
     */
	@SuppressWarnings("unchecked")
	
	public List<ActivityMaster> getSemesterStartEndDate(ActivityMaster actiMasterobj) {
		List<ActivityMaster> semesterStartEndDate=null;
		try{
			semesterStartEndDate = getSqlMapClientTemplate().queryForList("activityMaster.getSemesterStartEndDate", actiMasterobj);
			loggerObject.info("in getSemesterStartEndDate");
		}
		catch (Exception e) {
			loggerObject.error("in getSemesterStartEndDate" + e);
		}
		return semesterStartEndDate;
	}
	
	
	/**
     * This method will get the session start and date.
     * @return List
     */
	@SuppressWarnings("unchecked")
	
	public List<ActivityMaster> getSessionDetails(
			ActivityMaster activityMasterObject) {
		List<ActivityMaster> sessionStartEndList=null;
		try{
			sessionStartEndList = getSqlMapClientTemplate().queryForList("buildActivityMaster.getSessionStartEndDate", activityMasterObject);
			loggerObject.info("in getSessionDetails"); 
		}
		catch (Exception e) {
			loggerObject.error("in getSessionDetails"+ e);
		}
		return sessionStartEndList;
	}
	
	/**
     * This method will return the Activity Master Details
     * @return List
     */
	@SuppressWarnings("unchecked")
	
	public List<ActivityMaster> getActivityMasterDetials(ActivityMaster actiMasterobj) {
		List<ActivityMaster> programCourseLi=null;
		try{
			programCourseLi=getSqlMapClientTemplate().queryForList("activityMaster.getActivityMasterDetails", actiMasterobj);
			loggerObject.info("in getActivityMasterDetails");
		}
		catch (Exception e) {
			loggerObject.error("in getActivityMasterDetails" + e);
		}
		return programCourseLi;
	}
	
	/**
     * This method will Save the ActivityMaster Details.
     * @return void
     */
	
	public void setActivityMasterData(ActivityMaster activityMaster) {
		try{
			getSqlMapClientTemplate().insert("activityMaster.setActivityMasterDetail",activityMaster);
			
			loggerObject.info("in setActivityMasterData");
		}
		catch (Exception e) {
			loggerObject.error("in setActivityMasterData" + e);
		}
		
	}
	
	/**
     * This method will update the ActivityMaster Details.
     * @return void
     */
	
	public String updateActivityMaster(ActivityMaster activityMaster) {
		String result="failure";
		try{
			int flag=getSqlMapClientTemplate().update("activityMaster.updateActivityMaster",activityMaster);
			loggerObject.info("in updateActivityMaster");
			if(flag>0){
				result="success";
			}
		}
		catch (Exception e) {
			loggerObject.error("in updateActivityMaster" + e);			
		}	
		return result;
	}

	/**
     * This method will delete the ActivityMaster Details.
     * @return void
     */
	public void deleteActivityMasterDetails(ActivityMaster activityMaster, String activityDataTokens) {
		
	   	StringTokenizer courseCode = new StringTokenizer(activityDataTokens, ",");
	    try{   	
		   	 while(courseCode.hasMoreTokens()){
		   		activityMaster.setProgramCourseKey(courseCode.nextToken()); 
		   		activityMaster.setProcessCode(courseCode.nextToken());
		   		activityMaster.setActivityCode(courseCode.nextToken());
		   		activityMaster.setSemesterStartDate(courseCode.nextToken());
		   		activityMaster.setSemesterEndDate(courseCode.nextToken());
		   		getSqlMapClientTemplate().delete("activityMaster.deleteActivityMaster",activityMaster);
		   	 }
		   	loggerObject.info("in deleteActivityMasterDetails");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in deleteActivityMasterDetails" + e);
		}
	}

	/**
     * This method will return the existing Activity Sequence.
     * @return List 
     */
	@SuppressWarnings("unchecked")
	
	public List<ActivityMaster> getExistingActivitySequence(
			ActivityMaster activityMaster) {
		List<ActivityMaster> activitySequence=null;
		try{
			activitySequence = getSqlMapClientTemplate().queryForList("activityMaster.getExistingActivitySequence",activityMaster);
			loggerObject.info("in getExistingActivitySequence");
		}
		catch (Exception e) {
			loggerObject.error("in getExistingActivitySequence" + e);
		}
		return activitySequence;
	}

	
	/**
     * This method will change the activity status.
     * @return List 
     */
	public void changeActivityStatus(ActivityMaster activityMaster, String activityDataTokens) {
			
		   	StringTokenizer courseCode = new StringTokenizer(activityDataTokens, ",");
		    try{   	
			   	 while(courseCode.hasMoreTokens()){
			   		activityMaster.setProgramCourseKey(courseCode.nextToken()); 
			   		activityMaster.setProcessCode(courseCode.nextToken());
			   		activityMaster.setActivityCode(courseCode.nextToken());
			   		activityMaster.setSemesterStartDate(courseCode.nextToken());
			   		activityMaster.setSemesterEndDate(courseCode.nextToken());
			   		activityMaster.setStatus(courseCode.nextToken());
			   		getSqlMapClientTemplate().update("activityMaster.updateActivityStatus",activityMaster);
			   	 }
			   	loggerObject.info("in changeActivityStatus");
		    }
		    catch (Exception e) {
		    	loggerObject.error("in changeActivityStatus" + e);
			}
		}
	
	
	/**
     * This method will return the No of Activity
     * @return List
     */
	public String getActivityCount(ActivityMaster actiMasterobj) {
		ActivityMaster activityCount=null;
		String count=null;
		try{
			activityCount=(ActivityMaster)getSqlMapClientTemplate().queryForObject("activityMaster.getActivityCount", actiMasterobj);
			loggerObject.info("in getActivityCount");
			if(activityCount!=null){
				count=activityCount.getSize();
			}
		}
		catch (Exception e) {
			loggerObject.error("in getActivityCount" + e);
		}
		return count;
	}
	
	/**
     * This method will return the list of activity
     * @return List
     */
	@SuppressWarnings("unchecked")
	public List<ActivityMaster> getEntity(ActivityMaster actiMasterobj) {
		List<ActivityMaster> entityList=null;
		try{
			entityList=getSqlMapClientTemplate().queryForList("activityMaster.getEntity", actiMasterobj);
			loggerObject.info("in getEntity");
			
		}
		catch (Exception e) {
			loggerObject.error("in getEntity" + e);
		}
		return entityList;
	}

}
