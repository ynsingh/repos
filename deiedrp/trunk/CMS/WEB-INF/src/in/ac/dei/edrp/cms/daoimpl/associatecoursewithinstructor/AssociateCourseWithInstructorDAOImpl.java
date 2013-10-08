/**
 * @(#) AssociateCourseWithInstructorDAOImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.associatecoursewithinstructor;

import in.ac.dei.edrp.cms.dao.associatecoursewithinstructor.AssociateCourseWithInstructorDAO;
import in.ac.dei.edrp.cms.domain.activitymaster.ActivityMaster;
import in.ac.dei.edrp.cms.domain.associatecoursewithinstructor.AssociateCourseWithInstructor;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


/**
 * This file consist of the methods used at the
 * Associate Course With Instructor process.
 * @author Ankit Jain
 * @date 2 March 2011
 * @version 1.0
 */
public class AssociateCourseWithInstructorDAOImpl extends SqlMapClientDaoSupport implements AssociateCourseWithInstructorDAO{
	
	private Logger loggerObject = Logger.getLogger(AssociateCourseWithInstructorDAOImpl.class);
	private Locale obj = new Locale("en", "US");
	ResourceBundle resourceBundle=ResourceBundle.getBundle("in//ac//dei//edrp//cms//databasesetting//MessageProperties",obj);
	
	/**
     * This method will get Entity List
     * @return List
     */
	@SuppressWarnings("unchecked")
	public List<AssociateCourseWithInstructor> getEntityList(AssociateCourseWithInstructor associateCourseWithInstructor) {
		List<AssociateCourseWithInstructor> associateCourseWithInstructorObject=null;
		
		try{
			associateCourseWithInstructorObject=getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.entityList", associateCourseWithInstructor);
			loggerObject.info("in getEntityList");
		}
		catch (Exception e) {
			loggerObject.error("in getEntityList" + e);			
		}
		return associateCourseWithInstructorObject;
	}
	
	/**
     * Method to get the Program List.
     * @return List of Programs
     */
	@SuppressWarnings("unchecked")
	public List<AssociateCourseWithInstructor> getProgramList(AssociateCourseWithInstructor associateCourseWithInstructor) {
		List<AssociateCourseWithInstructor> associateCourseWithInstructorObject=null;
		try{
			associateCourseWithInstructorObject=getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.getProgramList", associateCourseWithInstructor);
			loggerObject.info("in getProgramList");
		}
		catch (Exception e) {
			loggerObject.error("in getProgramList" + e);			
		}
		return associateCourseWithInstructorObject;
	}
	
	/**
     * Method to get the Branch List.
     * @return List of Branch
     */
	@SuppressWarnings("unchecked")
	public List<AssociateCourseWithInstructor> getBranchList(AssociateCourseWithInstructor associateCourseWithInstructor) {
		List<AssociateCourseWithInstructor> associateCourseWithInstructorObject=null;
		try{
			associateCourseWithInstructorObject=getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.getBranchList", associateCourseWithInstructor);
			loggerObject.info("in getBranchList");
		}
		catch (Exception e) {
			loggerObject.error("in getBranchList" + e);			
		}
		return associateCourseWithInstructorObject;
	}
	
	/**
     * Method to get the specialization List.
     * @return List of specialization list
     */
	@SuppressWarnings("unchecked")
	public List<AssociateCourseWithInstructor> getSpecializationList(AssociateCourseWithInstructor associateCourseWithInstructor) {
		List<AssociateCourseWithInstructor> associateCourseWithInstructorObject=null;
		try{
			associateCourseWithInstructorObject=getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.getSpecializationList", associateCourseWithInstructor);
			loggerObject.info("in getSpecializationList");
		}
		catch (Exception e) {
			loggerObject.error("in getSpecializationList" + e);			
		}
		return associateCourseWithInstructorObject;
	}
	
	/**
     * Method to get the Semester List.
     * @return List of Semester List
     */
	@SuppressWarnings("unchecked")
	public List<AssociateCourseWithInstructor> getSemesterList(AssociateCourseWithInstructor associateCourseWithInstructor) {
		List<AssociateCourseWithInstructor> associateCourseWithInstructorObject=null;
		try{
			associateCourseWithInstructorObject=getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.getSemesterList", associateCourseWithInstructor);
			loggerObject.info("in getSemesterList");
		}
		catch (Exception e) {
			loggerObject.error("in getSemesterList" + e);			
		}
		return associateCourseWithInstructorObject;
	}
	
	/**
     * Method to get the Semester Dates
     * @return List of Semester Dates
     */
	@SuppressWarnings("unchecked")
	public List<AssociateCourseWithInstructor> getSemesterDates(AssociateCourseWithInstructor associateCourseWithInstructor) {
		List<AssociateCourseWithInstructor> associateCourseWithInstructorObject=null;
		try{
			
			associateCourseWithInstructorObject=getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.getSemesterDates", associateCourseWithInstructor);
			loggerObject.info("in getSemesterDates");
		}
		catch (Exception e) {
			loggerObject.error("in getSemesterDates" + e);			
		}
		return associateCourseWithInstructorObject;
	}
	
	/**
     * Method to get the process details
     * @param associateCourseWithInstructor AssociateCourseWithInstructor object
     * @return List of process
     */
	@SuppressWarnings("unchecked")
	public List<AssociateCourseWithInstructor> getCourseInstructorDetails(AssociateCourseWithInstructor associateCourseWithInstructor) {
		List<AssociateCourseWithInstructor> associateCourseWithInstructorObject=null;
		List<AssociateCourseWithInstructor> existingEntry=null;
		List<AssociateCourseWithInstructor> newDesignedRecord= new ArrayList();
		 
		
		try{
			loggerObject.info("in getCourseInstructorDetails");
			associateCourseWithInstructorObject=getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.getCourseCodeAndName", associateCourseWithInstructor);
			for(int i=0;i<associateCourseWithInstructorObject.size();i++){
				associateCourseWithInstructor.setProgramCourseKey(associateCourseWithInstructorObject.get(i).getProgramCourseKey());
				associateCourseWithInstructor.setCourseCode(associateCourseWithInstructorObject.get(i).getCourseCode());
				associateCourseWithInstructor.setCourseName(associateCourseWithInstructorObject.get(i).getCourseName());
				
				//get existing Program Course Instructor details
				existingEntry = getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.checkExistingCourseDetails", associateCourseWithInstructor);
				
				if(existingEntry.size()>0){
					for(AssociateCourseWithInstructor am : existingEntry)
					{	
						//Duplicate Record
						AssociateCourseWithInstructor courseWithInstructor=new AssociateCourseWithInstructor();			
						courseWithInstructor.setProgramCourseKey(am.getProgramCourseKey());
						courseWithInstructor.setCourseCode(am.getCourseCode());
						courseWithInstructor.setCourseName(associateCourseWithInstructorObject.get(i).getCourseName());
						courseWithInstructor.setEmployeeCode(am.getEmployeeCode());
						courseWithInstructor.setEmployeeName(am.getEmployeeName());
						courseWithInstructor.setStatus(am.getStatus());
						newDesignedRecord.add(courseWithInstructor);
					}
				}
				else{
					AssociateCourseWithInstructor courseWithInstructor=new AssociateCourseWithInstructor();			
					courseWithInstructor.setProgramCourseKey(associateCourseWithInstructor.getProgramCourseKey());
					courseWithInstructor.setCourseCode(associateCourseWithInstructorObject.get(i).getCourseCode());
					courseWithInstructor.setCourseName(associateCourseWithInstructorObject.get(i).getCourseName());
					courseWithInstructor.setEmployeeCode("Not Assigned");
					courseWithInstructor.setEmployeeName("Not Assigned");
					courseWithInstructor.setStatus("Active");
					newDesignedRecord.add(courseWithInstructor);
				}
			}
			
		}
		catch (Exception e) {
			loggerObject.error("in getCourseInstructorDetails" + e);
		}
		return newDesignedRecord;
	}
	
	/**
     * Method to get course instructor details.
     * @return List of course instructor details.
     */
	@SuppressWarnings("unchecked")
	public List<AssociateCourseWithInstructor> getInstructorDetails(AssociateCourseWithInstructor associateCourseWithInstructor) {
		List<AssociateCourseWithInstructor> associateCourseWithInstructorObject=null;
		
		try{
			loggerObject.info("in getInstructorDetails");
			associateCourseWithInstructorObject=getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.getInstructorDetails", associateCourseWithInstructor);
		}
		catch (Exception e) {
			loggerObject.error("in getInstructorDetails" + e);
		}
		return associateCourseWithInstructorObject;
	}
	
	/**
     * Method to get the Employee List.
     * @return List of Employee
     */
	@SuppressWarnings("unchecked")
	public List<AssociateCourseWithInstructor> getEmployeeList(AssociateCourseWithInstructor associateCourseWithInstructor) {
		List<AssociateCourseWithInstructor> associateCourseWithInstructorObject=null;
		try{
			associateCourseWithInstructorObject=getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.getEmployeeList", associateCourseWithInstructor);
			loggerObject.info("in getEmployeeList");
		}
		catch (Exception e) {
			loggerObject.error("in getEmployeeList" + e);			
		}
		return associateCourseWithInstructorObject;
	}
	
	/**
     * Method to to assign the employee to course.
     * @return List 
     */
	public String assignEmployee(AssociateCourseWithInstructor associateCourseWithInstructor, String courseDataTokens) {
		
	   	StringTokenizer coursetokens = new StringTokenizer(courseDataTokens, ",");
	    try{   	
		   	 while(coursetokens.hasMoreTokens()){
		   		associateCourseWithInstructor.setProgramCourseKey(coursetokens.nextToken()); 
		   		associateCourseWithInstructor.setCourseCode(coursetokens.nextToken());
		   		associateCourseWithInstructor.setEmployeeCode(coursetokens.nextToken());
		   		associateCourseWithInstructor.setEntityId(coursetokens.nextToken());
		   		associateCourseWithInstructor.setSemesterStartDate(coursetokens.nextToken());
		   		associateCourseWithInstructor.setSemesterEndDate(coursetokens.nextToken());
		   		getSqlMapClientTemplate().insert("associateCourseWithInstructor.insertCourseInstructor",associateCourseWithInstructor);
		   	 }
		   	loggerObject.info("in assignEmployee");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in assignEmployee" + e);
	    	return "failure";
		}
	    return "success"; 
	}
	
	/**
     * Method to update the instructor details 
     * @return String
     */
	public String updateEmployee(AssociateCourseWithInstructor associateCourseWithInstructor) {
		List<AssociateCourseWithInstructor> associateCourseWithInstructorObject=null;
		String msg="";
		try{
			int check=getSqlMapClientTemplate().update("associateCourseWithInstructor.updateEmployee", associateCourseWithInstructor);
			loggerObject.info("in updateEmployee");
			if(check>0){
				msg= "success";
			}
			else{
				msg= "failure";
			}
		}
		catch(DataIntegrityViolationException ex) {
			msg= "DataIntegrityViolationException";
			loggerObject.error("DataIntegrityViolation Exception during updateEmployee : " + ex);
		}
		catch (Exception e) {
			msg= "error";
			loggerObject.error("Exception during updateEmployee : " + e);			
		}
		return msg;		
	}
	
	
	/**
     * Method to update the instructor details
     * @return String
     */
	public String deleteCourseInstructor(AssociateCourseWithInstructor associateCourseWithInstructor, String courseDataTokens) {
		
	   	StringTokenizer coursetokens = new StringTokenizer(courseDataTokens, ",");
	    try{   	
		   	 while(coursetokens.hasMoreTokens()){
		   		associateCourseWithInstructor.setProgramCourseKey(coursetokens.nextToken()); 
		   		associateCourseWithInstructor.setCourseCode(coursetokens.nextToken());
		   		associateCourseWithInstructor.setEmployeeCode(coursetokens.nextToken());
		   		associateCourseWithInstructor.setEntityId(coursetokens.nextToken());
		   		associateCourseWithInstructor.setSemesterStartDate(coursetokens.nextToken());
		   		associateCourseWithInstructor.setSemesterEndDate(coursetokens.nextToken());
		   		getSqlMapClientTemplate().insert("associateCourseWithInstructor.deleteCourseInstructor",associateCourseWithInstructor);
		   	 }
		   	loggerObject.info("in assignEmployee");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in assignEmployee" + e);
	    	return "failure";
		}
	    return "success"; 
	}

	@SuppressWarnings("unchecked")
	public String buildInstructorCourse(AssociateCourseWithInstructor associateCourseWithInstructor) {
		List<AssociateCourseWithInstructor> semesterStartEndDate=null;
		List<AssociateCourseWithInstructor> instructorCourseDetails=null;
		List<AssociateCourseWithInstructor> existingEntry=null;
		AssociateCourseWithInstructor instructorCourseObject=new AssociateCourseWithInstructor();
		loggerObject.info("in buildInstructorCourse");
		
		try{
			
			instructorCourseDetails=getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.getInstructorCourseDetails", associateCourseWithInstructor);
			System.out.println("SIzeeeeeee: "+instructorCourseDetails.size());
			if(instructorCourseDetails.size()==0){
				return "noRecordFoundOfLastSession";
			}
			else{
				for(int i=0;i<instructorCourseDetails.size();i++){
					instructorCourseObject.setEntityId(instructorCourseDetails.get(i).getEntityId());
					instructorCourseObject.setProgramCourseKey(instructorCourseDetails.get(i).getProgramCourseKey());
					instructorCourseObject.setStatus(instructorCourseDetails.get(i).getStatus());
					instructorCourseObject.setEmployeeId(instructorCourseDetails.get(i).getEmployeeId());
					instructorCourseObject.setCourseCode(instructorCourseDetails.get(i).getCourseCode());
					instructorCourseObject.setSessionStartDate(associateCourseWithInstructor.getSessionStartDate());
					instructorCourseObject.setSessionEndDate(associateCourseWithInstructor.getSessionEndDate());
					instructorCourseObject.setUniversityId(associateCourseWithInstructor.getUniversityId());
					instructorCourseObject.setCreatorId(associateCourseWithInstructor.getCreatorId());
					try{
						//get new semester start and end date.
						semesterStartEndDate = getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.getSemesterStartEndDate", instructorCourseObject);
						instructorCourseObject.setSemesterStartDate(semesterStartEndDate.get(0).getSemesterStartDate());
						instructorCourseObject.setSemesterEndDate(semesterStartEndDate.get(0).getSemesterEndDate());
					}
					catch (java.lang.IndexOutOfBoundsException e) {
						loggerObject.error("Data is missing for session: ("+instructorCourseObject.getSessionStartDate()+"  :  "+instructorCourseObject.getSessionEndDate()+") and program_course_key: "+instructorCourseObject.getProgramCourseKey());
						loggerObject.error(e);
						return "datamissing";
					}
					
					//get existing entry from instructor course
					existingEntry = getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.checkAlreadyExistSemesterStartEndDate", instructorCourseObject);
					
					if(existingEntry.size()>0){
						loggerObject.error(resourceBundle.getString("alreadyCreated"));
						for(AssociateCourseWithInstructor am : existingEntry)
						{	//write  duplicate record into log.
							loggerObject.error(resourceBundle.getString("entityId")+" "+am.getEntityId()+" "+ resourceBundle.getString("programCourseKey")+" "+ am.getProgramCourseKey()+" "+
									resourceBundle.getString("semesterStartDate")+" "+ am.getSemesterStartDate()+ resourceBundle.getString("semesterEndDate")+" "+ am.getSemesterEndDate()+" "+
									resourceBundle.getString("employeeId")+" "+ am.getEmployeeId()+ resourceBundle.getString("courseCode")+" "+ am.getCourseCode()+" "+resourceBundle.getString("status")+" "+ am.getStatus());
						}
					}
					else{
						getSqlMapClientTemplate().insert("associateCourseWithInstructor.insertInstructorCourse", instructorCourseObject);					
					}
				}
			}
			return "success";
				
			}
			catch (Exception e) {
				loggerObject.error("in buildInstructorCourse" + e);
				return "error";
			}
		}

	@SuppressWarnings("unchecked")
	public List<AssociateCourseWithInstructor> getSessionDetails(
			AssociateCourseWithInstructor associateCourseWithInstructor) {
		List<AssociateCourseWithInstructor> sessionDetails=null;
		loggerObject.info("in getSessionDetails");
		try{
			sessionDetails = getSqlMapClientTemplate().queryForList("associateCourseWithInstructor.getSessionStartEndDate", associateCourseWithInstructor);
		}
		catch (Exception e) {
			loggerObject.error("in getSessionDetails" + e);
		}
		return sessionDetails;
	}	
}
