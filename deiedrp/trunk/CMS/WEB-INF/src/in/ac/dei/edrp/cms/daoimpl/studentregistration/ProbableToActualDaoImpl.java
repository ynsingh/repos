/*
 * @(#) ProbableToActualDaoImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.studentregistration;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.dao.studentregistration.ProbableToActualDao;
import in.ac.dei.edrp.cms.domain.activitymaster.ActivityMaster;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;
import in.ac.dei.edrp.cms.domain.utility.ErrorLogs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Implementation class of ProbableToActualDao
 * 
 * @author Manpreet Kaur
 * @date 01-03-2011
 * @version 1.0
 */
public class ProbableToActualDaoImpl implements ProbableToActualDao {
	private static Logger logObj = Logger
			.getLogger(ProbableToActualDaoImpl.class);
	TransactionTemplate transactionTemplate = null;
	protected SqlMapClient sqlMapClient = null;

	int totalRecords = 0;
	int correctProcessed = 0;
	int rejectedProcessed = 0;
	int inError = 0;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * default constructor
	 */
	public ProbableToActualDaoImpl() {

	}

	/**
	 * Method for setting TransactionTemplate bean
	 * 
	 * @param transactionTemplate
	 */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public ProbableToActualDaoImpl(SqlMapClient sqlMapClient,
			TransactionTemplate transactionTemplate) {

		this.sqlMapClient = sqlMapClient;
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * Method for transferring students probable program details to to-program
	 * details if student fails in semester he/she registered previously.
	 * 
	 * @param programObject
	 * @return String
	 */

	@SuppressWarnings("unchecked")
	public CountProcessRecorList transferOfProbables(final StudentInfoGetter programObject) {
		
		final List<UnProcessedStduent> studentList2 = new ArrayList<UnProcessedStduent>();
		System.out.println("@@@@@@@@@@@@@@@@@@@coming here@@@@@@@@@@@@@@@@@@@@@@@@");
		final ErrorLogs errorLogs = new ErrorLogs(programObject.getEntityId(),
				programObject.getProgramId(), programObject.getBranchId(),
				programObject.getSpecializationId(), programObject.getSemesterCode(), 
				programObject.getSemesterStartDate(), programObject.getSemesterEndDate(), 
				programObject.getProcessId(),programObject.getActivityId());

		Boolean processedFlag=(Boolean)transactionTemplate.execute(new TransactionCallback() {
			Object savepoint = null;

			public Boolean doInTransaction(TransactionStatus status){
				List studentList = new ArrayList<StudentInfoGetter>();
				List<StudentInfoGetter> detailsList = null;
				Boolean flag=true;
				try {
					studentList = sqlMapClient.queryForList("probableToActual.getStudentList", programObject);
					totalRecords = studentList.size();
					
					if (studentList.size() > 0) {
						Iterator<StudentInfoGetter> iterator = studentList.iterator();
						while (iterator.hasNext()) {
							StudentInfoGetter studentObj = iterator.next();
							studentObj.setModifierId(programObject.getModifierId());
							
							detailsList = sqlMapClient.queryForList("probableToActual.getStatus", studentObj);
							try{
								// creating savepoint
								savepoint = status.createSavepoint();
								if (detailsList.size() > 0) {
									if (!(detailsList.get(0).getStatus().equalsIgnoreCase("YTR"))) {
										cancelRecords(studentObj);
									}
								}
								else {
									cancelRecords(studentObj);
								}
								
							}
							catch (Exception e) {
								System.out.println(e);
								flag=false;
								// When a student is in error, inserting student's record
								// into student_error_log
								errorLogs.setEnrollmentNumber(studentObj.getEnrollmentNumber());
								errorLogs.setStudentId(studentObj.getStudentId());
								errorLogs.setStudentName(studentObj.getStudentName());
								errorLogs.setReasonCode("DBI");
								errorLogs.setDescription("Unknown issue");
								errorLogs.setProcessFlag(CRConstant.ERROR_STATUS);
								errorLogs.setSemesterStartDate(studentObj.getSessionStartDate());
								errorLogs.setSemesterEndDate(studentObj.getSessionEndDate());
								errorLogs.setProcessId("REG");
								errorLogs.setActivityId("PR9");
								inError++;
								sqlMapClient.insert("studentTrackingAndLogs.insertStudentErrorLog",	errorLogs);
								studentObj.setRegistrationNumber(studentObj.getRollNumber());
								studentList2.add(new UnProcessedStduent(studentObj.getRegistrationNumber(),"Problem: "+e));
								status.rollbackToSavepoint(savepoint);
							}
							finally {
								totalRecords = studentList.size();
								correctProcessed = totalRecords-inError;
								rejectedProcessed = inError;
							}
						}
					}
				}catch (SQLException e) {
					logObj.error(e);
					flag=false;
					// signifies failure
					throw new MyException(e.getMessage());
				} 
				catch (Exception e){ 
					logObj.error(e);
					flag=false;
					// signifies failure
					throw new MyException(e.getMessage());
				}
				return flag;
			}
		});
		System.out.println("p9 completed");

		// signifies success
		return new CountProcessRecorList(totalRecords, correctProcessed,rejectedProcessed, processedFlag,studentList2);
	}
/**
 * Method to cancel student registration
 * @param studentObj
 * @throws Exception
 */
	public void cancelRecords(StudentInfoGetter studentObj) throws Exception {
		try {
			int updateFlag=0;
			updateFlag=sqlMapClient.update("probableToActual.updateTempStdProg",studentObj);
			if(updateFlag==0){
				throw new MyException("Unable to Update, No Record Found in temp_student_program table.");
			}
			
			updateFlag=0;
			updateFlag=sqlMapClient.update("probableToActual.updateTempStdMaster",studentObj);
			if(updateFlag==0){
				throw new MyException("Unable to Update, No Record Found in temp_student_master table.");
			}
			
			updateFlag=0;
			updateFlag=sqlMapClient.update("probableToActual.updateTempStdCourse",studentObj);
			if(updateFlag==0){
				throw new MyException("Unable to Update, No Record Found in temp_student_course table.");
			}
			
			updateFlag=0;
			updateFlag=sqlMapClient.update("probableToActual.updateStaging", studentObj);
			if(updateFlag==0){
				throw new MyException("Unable to Update, No Record Found in staging_table table.");
			}
			
			sqlMapClient.insert("cancelRegistration.insertTmpStdMasterHistory", studentObj);
			sqlMapClient.insert("cancelRegistration.insertTmpStdProgHistory", studentObj);					
			sqlMapClient.insert("cancelRegistration.insertTmpStdCourseHistory", studentObj);
			
			updateFlag=0;
			updateFlag=sqlMapClient.delete("cancelRegistration.deleteTmpStdMaster", studentObj);
			if(updateFlag==0){
				throw new MyException("Unable to Delete, No Record Found in temp_student_master table.");
			}
			
			updateFlag=0;
			updateFlag=sqlMapClient.delete("cancelRegistration.deleteTmpStdProg", studentObj);
			if(updateFlag==0){
				throw new MyException("Unable to Delete, No Record Found in temp_student_program table.");
			}
			
			updateFlag=0;
			updateFlag=sqlMapClient.delete("cancelRegistration.deleteTmpStdCourse", studentObj);
			if(updateFlag==0){
				throw new MyException("Unable to delete, No Record Found in temp_student_course table.");
			}
									
			sqlMapClient.getDataSource().getConnection().commit();
			correctProcessed++;
		} catch (MyException e) {
			throw new MyException(e+"");
		}
		catch(Exception e){
			throw new Exception(e);
		}
	}

	/*
	 * Method added to check pass fail
	 * method not in use yet
	 */

	/**
	 * Method for checking whether student is pass or fail in semester
	 * 
	 * @param rollNumber
	 * @param semesterList
	 * @param inputObj
	 * @param maxFailSubjects
	 * @return
	 */
	public String checkStudentPassFail(List<String> semesterList,
			ActivityMaster inputObj, int maxFailSubjects) {
		// inputObj must contain program,branch,specialization,semester,
		// semester_start_date,semester_end_date and roll number for student

		// bean class will be changed and accordingly parameters in query

		// semesterList will contain current semester, and other semesters
		// having
		// same group as current semester and sequence less than current
		// semester

		/*
		 * Code to be removed
		 */
		// semesterList.add("SM1");
		// semesterList.add("SM2");
		// inputObj.setProgramId("0001001");
		// inputObj.setBranchId("XXX");
		// inputObj.setSpecializationId("XXX");
		// inputObj.setSemesterStartDate("2011-01-01");
		// inputObj.setSemesterEndDate("2011-05-31");
		// inputObj.setRollNumber("r1");
		// maxFailSubjects=1;

		try {
			int totalRemedial = 0;

			for (int i = 0; i < semesterList.size(); i++) {
				// change semester code on each iteration
				inputObj.setSemesterCode(semesterList.get(i));

				ActivityMaster totalRem = (ActivityMaster) sqlMapClient
						.queryForObject("common.countStudentRemedials",
								inputObj);
				String count = totalRem.getSize();
				totalRemedial = totalRemedial + Integer.parseInt(count);
			}
			System.out.println(totalRemedial);
			if (totalRemedial == 0) {
				return "PAS";
			} else if (totalRemedial > maxFailSubjects) {
				return "FAL";
			} else if (totalRemedial <= maxFailSubjects) {
				return "REM";
			}
		} catch (Exception e) {
			logObj.error(e);
		}
		return null;

	}

}
