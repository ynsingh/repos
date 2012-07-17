/**
 * @(#) InstructorCourseBuildDAOImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.buildinstructorcourse;

import in.ac.dei.edrp.cms.dao.buildinstructorcourse.InstructorCourseBuildDAO;
import in.ac.dei.edrp.cms.domain.buildinstructorcourse.InstructorCourseBuild;
import in.ac.dei.edrp.cms.domain.buildnextsession.BuildNextSession;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


/**
 * This file consist of the methods used at the
 * Build Instructor Course Process.
 * @author Devendra Singhal
 * @date Nov 24 2011
 * @version 1.0
 */
public class InstructorCourseBuildDAOImpl extends SqlMapClientDaoSupport implements InstructorCourseBuildDAO{

	/** Creating object of Logger for log Maintenance */
	private Logger loggerObject = Logger.getLogger(InstructorCourseBuildDAOImpl.class);
	
	private Locale obj = new Locale("en", "US");
	ResourceBundle resourceBundle=ResourceBundle.getBundle("in//ac//dei//edrp//cms//databasesetting//MessageProperties",obj);

	/**
     * Method to Build Instructor Course
     * @param object of InstructorCourseBuild
     * @return String message
     */
	@SuppressWarnings("unchecked")
	public String buildInstructorCourse(InstructorCourseBuild instructorCourseBuild) {
		List<InstructorCourseBuild> semesterStartEndDate=null;
		List<InstructorCourseBuild> instructorCourseDetails=null;
		List<InstructorCourseBuild> existingEntry=null;
		InstructorCourseBuild instructorCourseObject=new InstructorCourseBuild();
		
		String entity=instructorCourseBuild.getUniversityId()+"%";
		instructorCourseBuild.setEntityId(entity);
		try{
			int totalRecord=0;
			int recordBuild=0;
			instructorCourseDetails=getSqlMapClientTemplate().queryForList("buildInstructorCourse.getInstructorCourseDetails", instructorCourseBuild);		
			if(instructorCourseDetails.size()==0){
				return "noRecordFoundOfLastSession";
			}
			else{
				int listSize=instructorCourseDetails.size();
				totalRecord=instructorCourseDetails.size();
				for(int i=0;i<instructorCourseDetails.size();i++){
					instructorCourseObject.setEntityId(instructorCourseDetails.get(i).getEntityId());
					instructorCourseObject.setProgramCourseKey(instructorCourseDetails.get(i).getProgramCourseKey());
					instructorCourseObject.setStatus(instructorCourseDetails.get(i).getStatus());
					instructorCourseObject.setEmployeeId(instructorCourseDetails.get(i).getEmployeeId());
					instructorCourseObject.setCourseCode(instructorCourseDetails.get(i).getCourseCode());
					instructorCourseObject.setSessionStartDate(instructorCourseBuild.getSessionStartDate());
					instructorCourseObject.setSessionEndDate(instructorCourseBuild.getSessionEndDate());
					instructorCourseObject.setUniversityId(instructorCourseBuild.getUniversityId());
					instructorCourseObject.setCreatorId(instructorCourseBuild.getCreatorId());
					instructorCourseBuild.setProgramCourseKey(instructorCourseDetails.get(i).getProgramCourseKey());
					instructorCourseBuild.setEmployeeId(instructorCourseDetails.get(i).getEmployeeId());
					instructorCourseBuild.setCourseCode(instructorCourseDetails.get(i).getCourseCode());
					try{
						//get new semester start and end date.
						semesterStartEndDate = getSqlMapClientTemplate().queryForList("buildInstructorCourse.getSemesterStartEndDate", instructorCourseObject);
						instructorCourseBuild.setSemesterStartDate(semesterStartEndDate.get(0).getSemesterStartDate());
						instructorCourseBuild.setSemesterEndDate(semesterStartEndDate.get(0).getSemesterEndDate());
						instructorCourseObject.setSemesterStartDate(semesterStartEndDate.get(0).getSemesterStartDate());
						instructorCourseObject.setSemesterEndDate(semesterStartEndDate.get(0).getSemesterEndDate());
					}
					catch (java.lang.IndexOutOfBoundsException e) {
						setControlStatus(instructorCourseObject.getSessionStartDate(),instructorCourseBuild.getSessionEndDate(),instructorCourseBuild.getUniversityId(),instructorCourseBuild.getCreatorId(),"error");
						loggerObject.error("Data is missing for session: ("+instructorCourseObject.getSessionStartDate()+"  :  "+instructorCourseObject.getSessionEndDate()+") and program_course_key: "+instructorCourseObject.getProgramCourseKey());
						loggerObject.error(e);
						return "datamissing";
					}
					
					//get existing entry from instructor course				
					instructorCourseBuild.setEntityId(instructorCourseObject.getEntityId().toString().substring(0, 4)+"%");
					System.out.println("for check existing programCourseKey is "+instructorCourseBuild.getProgramCourseKey()+" : "+instructorCourseBuild.getEmployeeId());
					existingEntry = getSqlMapClientTemplate().queryForList("buildInstructorCourse.checkAlreadyExistSemesterStartEndDate", instructorCourseBuild);
					
					if(existingEntry.size()>0){
						listSize--;
						loggerObject.error(resourceBundle.getString("alreadyCreated"));
						for(InstructorCourseBuild am : existingEntry)
						{	//write  duplicate record into log.
							loggerObject.error(resourceBundle.getString("entityId")+" "+am.getEntityId()+" "+ resourceBundle.getString("programCourseKey")+" "+ am.getProgramCourseKey()+" "+
									resourceBundle.getString("semesterStartDate")+" "+ am.getSemesterStartDate()+ resourceBundle.getString("semesterEndDate")+" "+ am.getSemesterEndDate()+" "+
									resourceBundle.getString("employeeId")+" "+ am.getEmployeeId()+ resourceBundle.getString("courseCode")+" "+ am.getCourseCode()+" "+resourceBundle.getString("status")+" "+ am.getStatus());
						}
					}
					else{
						listSize--;
						recordBuild++;
						getSqlMapClientTemplate().insert("buildInstructorCourse.insertInstructorCourse", instructorCourseObject);					
					}
				}
				
				if(listSize==0){
					setControlStatus(instructorCourseBuild.getSessionStartDate(),instructorCourseBuild.getSessionEndDate(),instructorCourseBuild.getUniversityId(),instructorCourseBuild.getCreatorId(),"success");
				}
			}
			return "success"+"/"+totalRecord+"/"+recordBuild+"/"+(totalRecord-recordBuild);
				
			}
			catch (Exception e) {
				setControlStatus(instructorCourseBuild.getSessionStartDate(),instructorCourseBuild.getSessionEndDate(),instructorCourseBuild.getUniversityId(),instructorCourseBuild.getCreatorId(),"error");
				loggerObject.error("error in InstructorCourseBuildDAOImpl inside method buildInstructorCourse : " + e);
				return "error"+e.toString();
			}
		}
	
	/**
	 * Method to check status of build
	 * @param programDetails
	 * @return String message for status 
	 */
	@SuppressWarnings("unchecked")
	public String checkStatus(InstructorCourseBuild instructorCourseBuild) {
		String message="";
		try{
			BuildNextSession buildNextSessionObject=new BuildNextSession();
			buildNextSessionObject.setUniversityId(instructorCourseBuild.getUniversityId());
			buildNextSessionObject.setDummyFlag("4");
			List<BuildNextSession> processList=getSqlMapClientTemplate().queryForList("buildNextSession.getProcessCode", buildNextSessionObject);
			if(processList.size()>0){
				String processCod=processList.get(0).getProcessCode();
				instructorCourseBuild.setProcessCode(processCod);
				List<InstructorCourseBuild> sessionDetail = getSqlMapClientTemplate().queryForList("buildInstructorCourse.getSessionStartEndDate",instructorCourseBuild);
				instructorCourseBuild.setSessionStartDate(sessionDetail.get(0).getSessionStartDate());
				instructorCourseBuild.setSessionEndDate(sessionDetail.get(0).getSessionEndDate());
				
				List<InstructorCourseBuild> previousProcessDetail = getSqlMapClientTemplate().queryForList("buildInstructorCourse.getPreviousProcessDetail",instructorCourseBuild);
				if(previousProcessDetail.size()>0){
					instructorCourseBuild.setProcessCode(previousProcessDetail.get(0).getProcessCode());
					List<InstructorCourseBuild> controlList=getSqlMapClientTemplate().queryForList("buildInstructorCourse.getControlDetailStatus",instructorCourseBuild);
					if(controlList.size()==0){
						message="buildPrevious";
					}
					else if(controlList.get(0).getStatus().equals("E")){
						message="buildPrevious";
					}
					else if(controlList.get(0).getStatus().equals("P")){
						instructorCourseBuild.setProcessCode(processCod);
						List<InstructorCourseBuild>controllist=getSqlMapClientTemplate().queryForList("buildInstructorCourse.getControlDetailStatus",instructorCourseBuild);
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
			message="error"+ex.toString();
			logger.error("error in InstructorCourseBuildDAOImpl inside method checkStatus : "+ex);
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
		InstructorCourseBuild programDetails=new InstructorCourseBuild();
		programDetails.setUniversityId(universityId);
		programDetails.setSessionStartDate(sessionStartDate);
		programDetails.setSessionEndDate(sessionEndDate);
		programDetails.setCreatorId(userId);
		
		BuildNextSession buildNextSessionObject=new BuildNextSession();
		buildNextSessionObject.setUniversityId(universityId);
		buildNextSessionObject.setDummyFlag("4");
		List<BuildNextSession> processList=getSqlMapClientTemplate().queryForList("buildNextSession.getProcessCode", buildNextSessionObject);
		programDetails.setProcessCode(processList.get(0).getProcessCode());
		
		List<InstructorCourseBuild>controllist=getSqlMapClientTemplate().queryForList("buildInstructorCourse.getControlDetailStatus",programDetails);
		if(controllist.size()==0){
			if(status.equals("error")){
				logger.info("Some Error Occured so set status 'E' in yearend_process_control table for Process Code 'BPR' ");
				programDetails.setStatus("E");
				getSqlMapClientTemplate().insert("buildInstructorCourse.setControlDetailStatus",programDetails);
			}
			else{
				programDetails.setStatus("P");
				getSqlMapClientTemplate().insert("buildInstructorCourse.setControlDetailStatus",programDetails);
			}
		}
		else if(controllist.get(0).getStatus().equals("E")){
			if(status.equals("error")){
				logger.info("Some Error Occured so yearend_process_control table is not updated");
			}
			else{
				programDetails.setStatus("P");
				getSqlMapClientTemplate().update("buildInstructorCourse.updateControlDetailStatus",programDetails);
			}
		}
	}
	
	/**
     * Method to get the Session details
     * @param object of InstructorCourseBuild
     * @return List<AssociateCourseWithInstructor> contain session start,end date
     */
	@SuppressWarnings("unchecked")
	public List<InstructorCourseBuild> getSessionDetails(
			InstructorCourseBuild instructorCourseBuild) {
		List<InstructorCourseBuild> sessionDetails=null;
		try{
			sessionDetails = getSqlMapClientTemplate().queryForList("buildInstructorCourse.getSessionStartEndDate", instructorCourseBuild);
		}
		catch (Exception e) {
			loggerObject.error("error in InstructorCourseBuildDAOImpl inside method getSessionDetails : " + e);
		}
		return sessionDetails;
	}
	
}
