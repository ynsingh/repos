/**
 * @(#) StudentIssueImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.studentissue;

import in.ac.dei.edrp.cms.dao.studentissue.StudentIssueConnect;
import in.ac.dei.edrp.cms.daoimpl.updateprestaging.UpdatePrestagingImpl;
import in.ac.dei.edrp.cms.domain.studentissue.StudentIssueInfoGetter;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentNumbersInfoGetter;
import in.ac.dei.edrp.cms.domain.utility.StudentTracking;
import in.ac.dei.edrp.cms.utility.StudentTrackingFunction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.omg.CORBA.Request;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * The server side implementation of the RPC service.
 *
 * @version 1.0 05 SEP 2011
 * @author ROHIT SAXENA
 */

public class StudentIssueImpl extends SqlMapClientDaoSupport implements
		StudentIssueConnect {

	private TransactionTemplate transactionTemplate;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(UpdatePrestagingImpl.class);
	
	 /**
	* This method is used for fetching the Details of Issues
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<StudentIssueInfoGetter> getIssueDetails(
			StudentIssueInfoGetter input) {

		List<StudentIssueInfoGetter> issueList = new ArrayList<StudentIssueInfoGetter>();

		try{
		logObj.info("Function getIssueDetails in StudentIssueImpl");
		issueList = getSqlMapClientTemplate().queryForList(
				"studentIssue.getIssueRecords", input);
		}
		catch (Exception e) {
			logObj.error("Function getIssueDetails in StudentIssueImpl"+e.getMessage());
		}
		return issueList;
	}
    
	 /**
	* This method is used for deleting issues
	* @param input Object of the referenced bean class
	* @return List
	*/
//	public String deleteIssueRecords(StudentIssueInfoGetter input,
//			StringTokenizer items) {
//
//		try {
//
//			while (items.hasMoreTokens()) {
//
//				input.setIssueId(items.nextToken());
//
//				getSqlMapClientTemplate().delete("studentIssue.deleteRecords",
//						input);
//
//				return "success";
//
//			}
//
//		} catch (Exception e) {
//
//			System.out.println("exception" + e);
//		}
//		return "failure";
//	}

	/**
	* This method is used for fetching the penality codes
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<StudentIssueInfoGetter> getPenalityCodes(String userId) {
		List penalityList;

		StudentIssueInfoGetter beanobject = new StudentIssueInfoGetter();

		String GROUP_CODE = "PENCOD";
		try {
			logObj.info("Function getPenalityCodes in StudentIssueImpl");
		String uniId = userId.substring(1, 5);
		beanobject.setUniversityCode(uniId);
		beanobject.setGroupCode(GROUP_CODE);

			penalityList = (List) getSqlMapClientTemplate().queryForList(
					"studentIssue.getPenalityCodes", beanobject);

			return penalityList;
		} catch (Exception e) {
			logObj.error("Function getPenalityCodes in StudentIssueImpl"+e.getMessage());
		}

		return null;
	}

	/**
	* This method is used for fetching the course codes
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<StudentIssueInfoGetter> resultGetPenalityCourseCodes(
			StudentIssueInfoGetter input) {

		List<StudentIssueInfoGetter> courseList = new ArrayList<StudentIssueInfoGetter>();

		try{
		logObj.info("Function resultGetPenalityCourseCodes in StudentIssueImpl");
		if (input.getFlag().equalsIgnoreCase("Edit")) {
			
			courseList = getSqlMapClientTemplate().queryForList(
					"studentIssue.getCourseList", input);
		} else if (input.getFlag().equalsIgnoreCase("add")) {

			courseList = getSqlMapClientTemplate().queryForList(
					"studentIssue.getCourseLists", input);
		}
		}
		catch (Exception e) {
			logObj.error("Function resultGetPenalityCourseCodes in StudentIssueImpl"+e.getMessage());
		}
		
		return courseList;
	}

	 /**
	* This method is used for fetching the Entity Name
	* @param input Object of the referenced bean class
	* @return Object
	*/
	public Object getEntity(StudentIssueInfoGetter input) {

		StudentIssueInfoGetter entityDetail=null;
		try{
		logObj.info("Function getEntity in StudentIssueImpl");
		entityDetail = (StudentIssueInfoGetter) getSqlMapClientTemplate()
				.queryForObject("studentIssue.getEntityName", input);
		}
		catch (Exception e) {
			logObj.error("Function getEntity in StudentIssueImpl"+e.getMessage());
			}
		return entityDetail;
	}

	 /**
	* This method is used for fetching the program list
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<StudentIssueInfoGetter> getPrograms(StudentIssueInfoGetter input) {

		List<StudentIssueInfoGetter> programList = new ArrayList<StudentIssueInfoGetter>();
		try{
		logObj.info("Function getPrograms in StudentIssueImpl");
		programList = getSqlMapClientTemplate().queryForList(
				"studentIssue.getProgramList", input);
		}
		catch (Exception e) {
			logObj.error("Function getPrograms in StudentIssueImpl"+e.getMessage());
		}
		return programList;

	}

	 /**
	* This method is used for fetching the branch list
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<StudentIssueInfoGetter> getBranch(StudentIssueInfoGetter input) {

		List<StudentIssueInfoGetter> branchList = new ArrayList<StudentIssueInfoGetter>();
		try{
		logObj.info("Function getBranch in StudentIssueImpl");
		branchList = getSqlMapClientTemplate().queryForList(
				"studentIssue.getBranchList", input);
		}
		catch (Exception e) {
			logObj.error("Function getBranch in StudentIssueImpl"+e.getMessage());
		}
		return branchList;

	}

	 /**
	* This method is used for fetching the Specialization list
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<StudentIssueInfoGetter> getSpecialization(
			StudentIssueInfoGetter input) {

		List<StudentIssueInfoGetter> specializationList = new ArrayList<StudentIssueInfoGetter>();
		try{
		logObj.info("Function getSpecialization in StudentIssueImpl");
		specializationList = getSqlMapClientTemplate().queryForList(
				"studentIssue.getSpecializationList", input);
		}
		catch (Exception e) {
			logObj.error("Function getSpecialization in StudentIssueImpl"+e.getMessage());
		}
		return specializationList;
	}

	 /**
	* This method is used for fetching the Semester list
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<StudentIssueInfoGetter> getSemester(StudentIssueInfoGetter input) {

		List<StudentIssueInfoGetter> semesterList = new ArrayList<StudentIssueInfoGetter>();

		try{
		logObj.info("Function getSemester in StudentIssueImpl");
		semesterList = getSqlMapClientTemplate().queryForList(
				"studentIssue.getSemesterList", input);
		}
		catch (Exception e) {
			logObj.error("Function getSemester in StudentIssueImpl"+e.getMessage());
		}
		return semesterList;
	}

	 /**
	* This method is used for fetching the semester Dates list
	* @param input Object of the referenced bean class
	* @return List
	*/
	public StudentIssueInfoGetter getSemesterDates(StudentIssueInfoGetter input) {

		StudentIssueInfoGetter DateList= null;
		try{
		logObj.info("Function getSemesterDates in StudentIssueImpl");
		DateList = (StudentIssueInfoGetter) getSqlMapClientTemplate()
				.queryForObject("studentIssue.getDates", input);
		}
		catch (Exception e) {
			logObj.error("Function getSemesterDates in StudentIssueImpl"+e.getMessage());
		}
		return DateList;
	}

	 /**
	* This method is used for Inserting an Issue
	* @param input Object of the referenced bean class
	* @return List
	*/
	public String insertIssue(final StudentIssueInfoGetter input) {

		return (String) (transactionTemplate.execute(new TransactionCallback() {

			@SuppressWarnings("unchecked")
			public String doInTransaction(TransactionStatus arg0) {
				Object savePoint = null;

				StudentNumbersInfoGetter keys = new StudentNumbersInfoGetter();
				
				try {
					savePoint = arg0.createSavepoint();
					
					logObj.info("Function insertIssue in StudentIssueImpl");
					
				keys.setProgramId(input.getProgramId());
				keys.setBranchId(input.getBranchId());
				keys.setSpecializationId(input.getSpecializationId());
				keys.setSemesterCode(input.getSemesterId());

				keys = (StudentNumbersInfoGetter) getSqlMapClientTemplate()
						.queryForObject(
								"studentenrollment.getprogramcoursekey", keys);
			
				input.setProgramCourseKey(keys.getProgramCourseKey());

				List<StudentIssueInfoGetter> dateList = new ArrayList<StudentIssueInfoGetter>();

				dateList = getSqlMapClientTemplate().queryForList(
						"studentIssue.getDateList", input);

				if (dateList.size() != 0) {
					input.setSessionStartDate(dateList.get(0)
							.getSessionStartDate());
					input
							.setSessionEndDate(dateList.get(0)
									.getSessionEndDate());
				}

				StudentNumbersInfoGetter systemvalue = new StudentNumbersInfoGetter();

				String uniId = input.getProgramId().substring(0, 4);
				String CODE = "ISUEID";

				systemvalue.setUniversityId(uniId);
				systemvalue.setCode(CODE);

				StudentNumbersInfoGetter sysObject;

				String issueNo = null;

				String session = (input.getUnivSessionStartDate()
							.substring(2, 4));

				sysObject = (StudentNumbersInfoGetter) getSqlMapClientTemplate()
							.queryForObject("studentenrollment.sysvalue",
									systemvalue);

				int value = Integer.parseInt(sysObject.getSystemValue()) + 1;

				issueNo = String.format("%04d", value);

				systemvalue.setSystemValue(issueNo);

				getSqlMapClientTemplate().update(
							"studentenrollment.updatesysvalue", systemvalue);
					
				String s = (new java.sql.Timestamp(new Date().getTime()))
							.toString().substring(0, 19);

				input.setIssueOpenDate(s);
				input.setIssueId(session + issueNo);
				
				getSqlMapClientTemplate().insert(
							"studentIssue.insertIssueDetails", input);
					
				input.setSrshStatus(input.getIssue());
					getSqlMapClientTemplate().update(
							"studentIssue.updateSRSHStatus", input);

				StudentTracking tracking = new StudentTracking();
				tracking.setProgramCourseKey(input.getProgramCourseKey());
				tracking.setRollNumber(input.getRollNo());
				tracking.setEntityId(input.getEntityId());
				tracking.setSemesterStartDate(input.getSemesterStartDate());
				tracking.setSemesterEndDate(input.getSemesterEndDate());
					
				int seqNo = new StudentTrackingFunction(
							getSqlMapClientTemplate().getSqlMapClient(),
							transactionTemplate)
							.getStdentTrackingIssue(tracking);

				input.setSequenceNo(seqNo + 1);
					
				input.setForCreatorActivity("UFM");
					getSqlMapClientTemplate().insert(
							"studentIssue.insertInStudentTracking", input);
					
				if(input.getPenalityCode()!=null){
						
				input.setIssueStatus("CLS");
						getSqlMapClientTemplate().insert("studentIssue.updateIssueDetails",
								input);
				input.setSrshStatus("REG");
						
				getSqlMapClientTemplate()
						.update("studentIssue.updateSRSHStatus", input);
						
				if(input.getPenalityCode().equalsIgnoreCase("REM")){
									
				//input.setGradeFlag("F");
				String Codes[] = { input.getPenalityCourse1(),
										input.getPenalityCourse2(), input.getPenalityCourse3(),
										input.getPenalityCourse4() };
					
				for (int i = 0; i < Codes.length; i++) {
										
					input.setCourseCode(Codes[i]);
										
					getSqlMapClientTemplate().update(
											"studentIssue.updateFinalGrade", input);
					}
				}
						
				if(input.getPenalityCode().equalsIgnoreCase("FAL")){
									
				getSqlMapClientTemplate().update(
											"studentIssue.updateSRSHforfail", input);
									
				}
								
				if(input.getPenalityCode().equalsIgnoreCase("TRM")){
									
				getSqlMapClientTemplate().update(
												"studentIssue.updateStatusInStudentProgram", input);
										
			}
								
				tracking.setProgramCourseKey(input.getProgramCourseKey());
				tracking.setRollNumber(input.getRollNo());
				tracking.setEntityId(input.getEntityId());
				tracking.setSemesterStartDate(input.getSemesterStartDate());
				tracking.setSemesterEndDate(input.getSemesterEndDate());
							
				int seqNnumber = new StudentTrackingFunction(getSqlMapClientTemplate()
									.getSqlMapClient(), transactionTemplate)
									.getStdentTrackingIssue(tracking);

				input.setSequenceNo(seqNnumber + 1);
							
				input.setForCreatorActivity("UFM");
							getSqlMapClientTemplate().insert(
									"studentIssue.insertInStudentTracking", input);	
			    }
				return "Success";
				
			}
				catch (Exception e) {
					
					arg0.rollbackToSavepoint(savePoint);
					logObj.error("Function insertIssue in StudentIssueImpl"+e.getMessage());
					return "failure";
				}
			}
		}));
	}

	/**
	* This method is used for Closing Issue
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public String updateIssue(final StudentIssueInfoGetter input) {

		return (String)transactionTemplate.execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus ts) {
				String result="failure";
				Object savepoint = new Object();
				try {
					savepoint = ts.createSavepoint();
					logObj.info("Function updateIssue in StudentIssueImpl");
					List<StudentIssueInfoGetter> dateList = new ArrayList<StudentIssueInfoGetter>();

					dateList = getSqlMapClientTemplate().queryForList(
							"studentIssue.getDateList", input);

				if (dateList.size() != 0) {
						input.setSessionStartDate(dateList.get(0).getSessionStartDate());
						input.setSessionEndDate(dateList.get(0).getSessionEndDate());
					}

				input.setIssueStatus("CLS");

				getSqlMapClientTemplate().insert("studentIssue.updateIssueDetails",
							input);
									
				input.setSrshStatus("REG");
					
				getSqlMapClientTemplate()
							.update("studentIssue.updateSRSHStatus", input);
							
				if (input.getPenalityId().equalsIgnoreCase("REM")) {
						
						input.setGradeFlag("F");
						String Codes[] = { input.getPenalityCourse1(),
								input.getPenalityCourse2(), input.getPenalityCourse3(),
								input.getPenalityCourse4() };
						for (int i = 0; i < Codes.length; i++) {
								
							input.setCourseCode(Codes[i]);
							
							input.setPenalityCode(input.getProgramId());
							getSqlMapClientTemplate().update(
									"studentIssue.updateFinalGrade", input);
						}
					}
					
					if(input.getPenalityId().equalsIgnoreCase("FAL")){
						
					getSqlMapClientTemplate().update(
								"studentIssue.updateSRSHforfail", input);	
						}
				
					if(input.getPenalityId().equalsIgnoreCase("TRM")){
									
							getSqlMapClientTemplate().update(
									"studentIssue.updateStatusInStudentProgram", input);
						}
				

					StudentTracking tracking = new StudentTracking();
					tracking.setProgramCourseKey(input.getProgramCourseKey());
					tracking.setRollNumber(input.getRollNo());
					tracking.setEntityId(input.getEntityId());
					tracking.setSemesterStartDate(input.getSemesterStartDate());
					tracking.setSemesterEndDate(input.getSemesterEndDate());
					
					int seqNo = new StudentTrackingFunction(getSqlMapClientTemplate()
							.getSqlMapClient(), transactionTemplate)
							.getStdentTrackingIssue(tracking);

					input.setSequenceNo(seqNo + 1);
				
					input.setForCreatorActivity("UFM");
					input.setIssue("REG");
				
					getSqlMapClientTemplate().insert(
							"studentIssue.insertInStudentTracking", input);
					
					result="success";
					
				   } 
				   catch (Exception e) {
					
					ts.rollbackToSavepoint(savepoint);
					logObj.error("Function updateIssue in StudentIssueImpl"+e.getMessage());
				}
				return result;
			}
		});		
	}

	 /**
	* This method is used for fetching the roll numbers list
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<StudentIssueInfoGetter> getRollNo(StudentIssueInfoGetter input) {

		List<StudentIssueInfoGetter> rollList = new ArrayList<StudentIssueInfoGetter>();
		try{
		logObj.info("Function getRollNo in StudentIssueImpl");
		rollList = getSqlMapClientTemplate().queryForList(
				"studentIssue.getRollList", input);
		}
		catch (Exception e) {
			logObj.error("Function getRollNo in StudentIssueImpl"+e.getMessage());
		}
		return rollList;

	}

	/**
	* This method is used for fetching the Issue codes
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<StudentIssueInfoGetter> getIssues(StudentIssueInfoGetter input) {

		List issueList = new ArrayList<StudentIssueInfoGetter>();

		try {
			logObj.info("Function getIssues in StudentIssueImpl");
			issueList = (List) getSqlMapClientTemplate().queryForList(
					"studentIssue.getIssueCodes", input);

			return issueList;
		} catch (Exception e) {
			logObj.error("Function getIssues in StudentIssueImpl"+e.getMessage());

		}
		return issueList;
	}

}