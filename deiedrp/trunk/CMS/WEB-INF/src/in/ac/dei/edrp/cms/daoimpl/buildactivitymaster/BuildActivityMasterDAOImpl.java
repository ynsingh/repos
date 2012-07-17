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
package in.ac.dei.edrp.cms.daoimpl.buildactivitymaster;


import in.ac.dei.edrp.cms.dao.buildactivitymaster.BuildActivityMasterDAO;
import in.ac.dei.edrp.cms.domain.activitymaster.ActivityMaster;
import in.ac.dei.edrp.cms.domain.buildnextsession.BuildNextSession;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This file consist of the methods used at the
 * Activity Master process.
 * @author Ankit Jain
 * @date 14 Feb 2011
 * @version 1.0
 */
public class BuildActivityMasterDAOImpl extends SqlMapClientDaoSupport implements BuildActivityMasterDAO{

	private Logger loggerObject = Logger.getLogger(BuildActivityMasterDAOImpl.class);
	private Locale obj = new Locale("en", "US");
	ResourceBundle resourceBundle=ResourceBundle.getBundle("in//ac//dei//edrp//cms//databasesetting//MessageProperties",obj);


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
     * This method will fetch the ProgramCourse Information
     * @return List
     */
	@SuppressWarnings("unchecked")

	public String setActivityForNewSession(ActivityMaster activityMasterObject) {
		List<ActivityMaster> semesterStartEndDate=null;
		List<ActivityMaster> activityMasterDetails=null;
		List<ActivityMaster> existingEntry=null;
		loggerObject.info("in setActivityForNewSession");
		ActivityMaster activityMaster=new ActivityMaster();

		try{
			String code=activityMasterObject.getUniversityId()+"%";
			activityMasterObject.setCode(code);
			activityMasterDetails=getSqlMapClientTemplate().queryForList("buildActivityMaster.getActivityMasterDetails", activityMasterObject);
			int listSize=activityMasterDetails.size();
			int totalRecords=activityMasterDetails.size();
			int totalBuild=0;
			for(int i=0;i<activityMasterDetails.size();i++){

				activityMaster.setEntityId(activityMasterDetails.get(i).getEntityId());
				activityMaster.setProcessCode(activityMasterDetails.get(i).getProcessCode());
				activityMaster.setActivityCode(activityMasterDetails.get(i).getActivityCode());
				activityMaster.setActivitySequence(activityMasterDetails.get(i).getActivitySequence());
				activityMaster.setCreatorId(activityMasterObject.getCreatorId());
				activityMaster.setProgramCourseKey(activityMasterDetails.get(i).getProgramCourseKey());
				activityMaster.setStatus(activityMasterDetails.get(i).getStatus());
				activityMaster.setSessionStartDate(activityMasterObject.getSessionStartDate());
				activityMaster.setSessionEndDate(activityMasterObject.getSessionEndDate());

				try{
					//get new semester start and end date.
					activityMasterObject.setProgramCourseKey(activityMasterDetails.get(i).getProgramCourseKey());
					semesterStartEndDate = getSqlMapClientTemplate().queryForList("buildActivityMaster.getSemesterStartEndDate", activityMasterObject);
					activityMaster.setSemesterStartDate(semesterStartEndDate.get(0).getSemesterStartDate());
					activityMaster.setSemesterEndDate(semesterStartEndDate.get(0).getSemesterEndDate());
				}
				catch (java.lang.IndexOutOfBoundsException e) {
					//Method to set Status 'E' in yearend_process_control table
					setControlStatus(activityMasterObject.getSessionStartDate(),activityMasterObject.getSessionEndDate(),activityMasterObject.getUniversityId(),activityMasterObject.getCreatorId(),"error");
					loggerObject.error("Data is missing for session: ("+activityMaster.getSessionStartDate()+"  :  "+activityMaster.getSessionEndDate()+") and program_course_key: "+activityMaster.getProgramCourseKey());
					loggerObject.error(e);
					return "datamissing";
				}

				//get existing acticity_master details
				existingEntry = getSqlMapClientTemplate().queryForList("buildActivityMaster.checkAlreadyExistSemesterStartEndDate", activityMaster);

				if(existingEntry.size()>0){
					listSize--;
					loggerObject.error(resourceBundle.getString("alreadyCreated"));
					for(ActivityMaster am : existingEntry)
					{	//write  duplicate record into log.
						loggerObject.error(resourceBundle.getString("entityId")+" "+am.getEntityId()+" "+ resourceBundle.getString("programCourseKey")+" "+ am.getProgramCourseKey()+" "+
								resourceBundle.getString("semesterStartDate")+" "+ am.getSemesterStartDate()+ resourceBundle.getString("semesterEndDate")+" "+ am.getSemesterEndDate()+" "+
								resourceBundle.getString("sessionStartDate")+" "+ am.getSessionStartDate()+resourceBundle.getString("sessionEndDate")+ " " + am.getSessionEndDate()+" "+
								resourceBundle.getString("processCode")+" " + am.getProcessCode()+resourceBundle.getString("activityCode")+" " + am.getActivityCode()+" "+
								resourceBundle.getString("activitySequence")+" " + am.getActivitySequence()+ resourceBundle.getString("processActivityStartDate")+" " + am.getProcessActivityStartDate()+" "+
								resourceBundle.getString("processActivityEndDate")+" "+ am.getProcessActivityEndDate()+resourceBundle.getString("activityStatus")+" "+ am.getActivityStatus());
					}
				}
				else{
					getSqlMapClientTemplate().insert("buildActivityMaster.insertActivityMasterDetails", activityMaster);
					listSize--;
					totalBuild++;
				}
			}
			if(listSize==0){
				setControlStatus(activityMasterObject.getSessionStartDate(),activityMasterObject.getSessionEndDate(),activityMasterObject.getUniversityId(),activityMasterObject.getCreatorId(),"success");
			}
			return "success"+"/"+totalRecords+"/"+totalBuild+"/"+(totalRecords-totalBuild);

		}
		catch (Exception e) {
			//Method to set Status 'E' in yearend_process_control table
			setControlStatus(activityMasterObject.getSessionStartDate(),activityMasterObject.getSessionEndDate(),activityMasterObject.getUniversityId(),activityMasterObject.getCreatorId(),"error");
			loggerObject.error("in getActivityMasterDetails" + e);
			return "error";
		}
	}

	/**
     * Method to check whether activities are builded already.
     * @return List
     */
	@SuppressWarnings("unchecked")
	public String checkBuildComplete(
			ActivityMaster activityMasterObject) {
		List<ActivityMaster> sessionRecord=null;
		try{
			sessionRecord = getSqlMapClientTemplate().queryForList("buildActivityMaster.checkBuild", activityMasterObject);
			loggerObject.info("in getSessionDetails");
			if(sessionRecord.size()>0){
				return "EXIST";
			}
			else{
				return "NOTEXIST";
			}
		}
		catch (Exception e) {
			loggerObject.error("in getSessionDetails"+ e);
		}
		return null;
	}

	/**
     * This method will return the list of activity
     * @return List
     */
	@SuppressWarnings("unchecked")
	public List<ActivityMaster> getEntity(ActivityMaster actiMasterobj) {
		List<ActivityMaster> entityList=null;
		try{
			entityList=getSqlMapClientTemplate().queryForList("buildActivityMaster.getEntity", actiMasterobj);
			loggerObject.info("in getEntity");

		}
		catch (Exception e) {
			loggerObject.error("in getEntity" + e);
		}
		return entityList;
	}

	/**
     * This method will fetch program list
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public List<ActivityMaster> getProgramList(ActivityMaster activityMaster)throws Exception{
		List<ActivityMaster> activityMasterObject=null;
		try{
			activityMasterObject=getSqlMapClientTemplate().queryForList("buildActivityMaster.getProgramList", activityMaster);
			loggerObject.info("in getProgramList");
		}
		catch (Exception e) {
			loggerObject.error("in getProgramList" + e);
		}
		return activityMasterObject;
	}

	/**
     * This method will fetch branch list
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public List<ActivityMaster> getBranchList(ActivityMaster activityMaster)throws Exception{
		List<ActivityMaster> activityMasterObject=null;
		try{
			activityMasterObject=getSqlMapClientTemplate().queryForList("buildActivityMaster.getBranchList", activityMaster);
			loggerObject.info("in getBranchList");
		}
		catch (Exception e) {
			loggerObject.error("in getBranchList" + e);
		}
		return activityMasterObject;
	}

	/**
     * This method will fetch specialization list
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public List<ActivityMaster> getSpecializationList(ActivityMaster activityMaster)throws Exception{
		List<ActivityMaster> activityMasterObject=null;
		try{
			activityMasterObject=getSqlMapClientTemplate().queryForList("buildActivityMaster.getSpecializationList", activityMaster);
			loggerObject.info("in getSpecializationList");
		}
		catch (Exception e) {
			loggerObject.error("in getSpecializationList" + e);
		}
		return activityMasterObject;
	}

	/**
     * This method will fetch semester list
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public List<ActivityMaster> getSemesterList(ActivityMaster activityMaster)throws Exception {
		List<ActivityMaster> activityMasterObject=null;
		try{
			activityMasterObject=getSqlMapClientTemplate().queryForList("buildActivityMaster.getSemesterList", activityMaster);
			loggerObject.info("in getSemesterList");
		}
		catch (Exception e) {
			loggerObject.error("in getSemesterList" + e);
		}
		return activityMasterObject;
	}

	/**
     * This method will build activity for all programs
     * @param activityMaster ActivityMaster object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public String buildAllPrograms(ActivityMaster activityMaster)throws Exception {
		List<ActivityMaster> activityMasterObject=null;
		List<ActivityMaster> allProgramCourseKeyObject=null;
		List<ActivityMaster> semesterStartEndDateObject=null;
		try{
			// getSelected program course key
			ActivityMaster programCourseKeyObject =(ActivityMaster)getSqlMapClientTemplate().queryForObject("buildActivityMaster.getProgramCourseKey", activityMaster);
			activityMaster.setSelectedProgramCourseKey(programCourseKeyObject.getSelectedProgramCourseKey());

			// get all activity of selected program course key
			activityMasterObject=getSqlMapClientTemplate().queryForList("buildActivityMaster.getSelectedProgramActivities", activityMaster);

			if(activityMasterObject.size()==0){
				return "activityNotExist";
			}
			else{
				// get all programCourseKey excluding selected ProgramCourseKey
				allProgramCourseKeyObject=getSqlMapClientTemplate().queryForList("buildActivityMaster.getAllProgramCourseKey", activityMaster);

				// iterate over all programCourseKey
				for(ActivityMaster obj:allProgramCourseKeyObject){
					// select semesterStartDate and semesterEndDate from the program_registration table on the basis of programCourseKey
					activityMaster.setProgramCourseKey(obj.getProgramCourseKey());
					semesterStartEndDateObject=getSqlMapClientTemplate().queryForList("buildActivityMaster.getSemesterStartEndDate", activityMaster);
					if(semesterStartEndDateObject.size()==0){
						return "semesterDatesMissing";
					}
					else{
						activityMaster.setSemesterStartDate(semesterStartEndDateObject.get(0).getSemesterStartDate());
						activityMaster.setSemesterEndDate(semesterStartEndDateObject.get(0).getSemesterEndDate());
						for(ActivityMaster o:activityMasterObject){
							activityMaster.setProcessCode(o.getProcessCode());
							activityMaster.setActivityCode(o.getActivityCode());
							activityMaster.setActivitySequence(o.getActivitySequence());
							activityMaster.setProcessActivityStartDate(o.getProcessActivityStartDate());
							activityMaster.setProcessActivityEndDate(o.getProcessActivityEndDate());
							activityMaster.setActivityStatus(o.getActivityStatus());
							activityMaster.setStatus(o.getStatus());
							try{
								getSqlMapClientTemplate().insert("buildActivityMaster.buildActivityForAllPrograms", activityMaster);
							}catch (DataIntegrityViolationException e) {
								loggerObject.error("in buildAllPrograms" + e);
								return "duplicateData";
							}
						}

					}
				}
			}


			loggerObject.info("in buildAllPrograms");
		}

		catch (Exception e2) {
			loggerObject.error("in buildAllPrograms" + e2);
		}

		return null;
	}

	/**
     * This method will fetch the ProgramCourse Information
     * @return List
     */
	@SuppressWarnings("unchecked")
	public List<ActivityMaster> getProgramCourseHeaderList(ActivityMaster activityMaster) {
		List<ActivityMaster> programCourse=null;

		try{
			// getSelected program course key
			ActivityMaster programCourseKeyObject =(ActivityMaster)getSqlMapClientTemplate().queryForObject("buildActivityMaster.getProgramCourseKey", activityMaster);
			activityMaster.setSelectedProgramCourseKey(programCourseKeyObject.getSelectedProgramCourseKey());

			programCourse=getSqlMapClientTemplate().queryForList("buildActivityMaster.getProgramCourseHeaderList", activityMaster);
			loggerObject.info("in getProgramCourseHeaderList");
		}
		catch (Exception e) {
			loggerObject.error("in getProgramCourseHeaderList" + e);
		}
		return programCourse;
	}

	/**
     * This method will build activity for all programs
     * @param activityMaster ActivityMaster object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public String buildSelectedPrograms(ActivityMaster activityMaster, String selectedProgramSemesterTokens)throws Exception {
		List<ActivityMaster> activityMasterObject=null;
		List<ActivityMaster> semesterStartEndDateObject=null;
		StringTokenizer tokens = new StringTokenizer(selectedProgramSemesterTokens, ",");
		try{
			// getSelected program course key
			ActivityMaster programCourseKeyObject =(ActivityMaster)getSqlMapClientTemplate().queryForObject("buildActivityMaster.getProgramCourseKey", activityMaster);
			activityMaster.setSelectedProgramCourseKey(programCourseKeyObject.getSelectedProgramCourseKey());

			// get all activity of selected program course key
			activityMasterObject=getSqlMapClientTemplate().queryForList("buildActivityMaster.getSelectedProgramActivities", activityMaster);
			if(activityMasterObject.size()==0){
				return "activityNotExist";
			}
			else{
				// iterate over all programCourseKey
				while(tokens.hasMoreTokens()){
					// select semesterStartDate and semesterEndDate from the program_registration table on the basis of programCourseKey
					activityMaster.setProgramCourseKey(tokens.nextToken());
					semesterStartEndDateObject=getSqlMapClientTemplate().queryForList("buildActivityMaster.getSemesterStartEndDate", activityMaster);
					if(semesterStartEndDateObject.size()==0){
						return "semesterDatesMissing";
					}
					else{
						activityMaster.setSemesterStartDate(semesterStartEndDateObject.get(0).getSemesterStartDate());
						activityMaster.setSemesterEndDate(semesterStartEndDateObject.get(0).getSemesterEndDate());
						for(ActivityMaster o:activityMasterObject){
							activityMaster.setProcessCode(o.getProcessCode());
							activityMaster.setActivityCode(o.getActivityCode());
							activityMaster.setActivitySequence(o.getActivitySequence());
							activityMaster.setProcessActivityStartDate(o.getProcessActivityStartDate());
							activityMaster.setProcessActivityEndDate(o.getProcessActivityEndDate());
							activityMaster.setActivityStatus(o.getActivityStatus());
							activityMaster.setStatus(o.getStatus());
							try{
								getSqlMapClientTemplate().insert("buildActivityMaster.buildActivityForAllPrograms", activityMaster);
							}catch (DataIntegrityViolationException e) {
								loggerObject.error("in buildSelectedPrograms" + e);
								return "duplicateData";
							}
						}

					}
				}
			}

			loggerObject.info("in buildSelectedPrograms");
		}

		catch (Exception e2) {
			loggerObject.error("in buildSelectedPrograms" + e2);
		}

		return null;
	}

/**
	 * Method to check status of build
	 * @param programDetails
	 * @return String for status
	 */
	@SuppressWarnings("unchecked")
	public String checkStatus(ActivityMaster activityMaster) {
		String message="";
		try{
			BuildNextSession buildNextSessionObject=new BuildNextSession();
			buildNextSessionObject.setUniversityId(activityMaster.getUniversityId());
			buildNextSessionObject.setDummyFlag("3");
			List<BuildNextSession> processList=getSqlMapClientTemplate().queryForList("buildNextSession.getProcessCode", buildNextSessionObject);
			if(processList.size()>0){
				String processCD=processList.get(0).getProcessCode();
				activityMaster.setProcessCode(processCD);
				List<ActivityMaster> sessionDetail = getSqlMapClientTemplate().queryForList("buildActivityMaster.getSessionStartEndDate",activityMaster);
				activityMaster.setSessionStartDate(sessionDetail.get(0).getSessionStartDate());
				activityMaster.setSessionEndDate(sessionDetail.get(0).getSessionEndDate());
	
				List<ActivityMaster> previousProcessDetail = getSqlMapClientTemplate().queryForList("buildActivityMaster.getPreviousProcessDetail",activityMaster);
				if(previousProcessDetail.size()>0){
					activityMaster.setProcessCode(previousProcessDetail.get(0).getProcessCode());
					List<ActivityMaster> controlList=getSqlMapClientTemplate().queryForList("buildActivityMaster.getControlDetailStatus",activityMaster);
					if(controlList.size()==0){
						message="buildPrevious";
					}
					else if(controlList.get(0).getStatus().equals("E")){
						message="buildPrevious";
					}
					else if(controlList.get(0).getStatus().equals("P")){
						activityMaster.setProcessCode(processCD);
						List<ActivityMaster>controllist=getSqlMapClientTemplate().queryForList("buildActivityMaster.getControlDetailStatus",activityMaster);
						if(controllist.size()==0){
							message="run";
						}
						else if(controllist.get(0).getStatus().equals("E")){
							message="run";
						}
						else if(controllist.get(0).getStatus().equals("P")){
							message="allreadyBuild";
						}
					}
				}
				else{
					message="noSequence";
				}
			}
			else{
				message="NOProcessCode";
			}
		}
		catch(Exception ex){
			message="error";
			logger.error("Error inside BuildActivityMasterDAOImpl method checkStatus "+ex);
		}
		return message;

	}

	/**
	 * Method to set status in yearend Process Control table
	 * @param String sessionStartDate
	 * @param String sessionEndDate
	 * @param String universityId
	 * @param String status
	 */
	@SuppressWarnings("unchecked")
	public void setControlStatus(String sessionStartDate,String sessionEndDate,String universityId,String userId,String status){
		ActivityMaster programDetails=new ActivityMaster();
		programDetails.setUniversityId(universityId);
		programDetails.setSessionStartDate(sessionStartDate);
		programDetails.setSessionEndDate(sessionEndDate);
		programDetails.setCreatorId(userId);
		
		BuildNextSession buildNextSessionObject=new BuildNextSession();
		buildNextSessionObject.setUniversityId(programDetails.getUniversityId());
		buildNextSessionObject.setDummyFlag("3");
		List<BuildNextSession> processList=getSqlMapClientTemplate().queryForList("buildNextSession.getProcessCode", buildNextSessionObject);
		programDetails.setProcessCode(processList.get(0).getProcessCode());
		List<ActivityMaster>controllist=getSqlMapClientTemplate().queryForList("buildActivityMaster.getControlDetailStatus",programDetails);
		if(controllist.size()==0){
			if(status.equals("error")){
				logger.info("Some Error Occured so set status 'E' in yearend_process_control table for Process Code 'BPR' ");
				programDetails.setStatus("E");
				getSqlMapClientTemplate().insert("buildActivityMaster.setControlDetailStatus",programDetails);
			}
			else{
				programDetails.setStatus("P");
				getSqlMapClientTemplate().insert("buildActivityMaster.setControlDetailStatus",programDetails);
			}
		}
		else if(controllist.get(0).getStatus().equals("E")){
			if(status.equals("error")){
				logger.info("Some Error Occured so yearend_process_control table is not updated");
			}
			else{
				programDetails.setStatus("P");
				getSqlMapClientTemplate().update("buildActivityMaster.updateControlDetailStatus",programDetails);
			}
		}
	}
}
