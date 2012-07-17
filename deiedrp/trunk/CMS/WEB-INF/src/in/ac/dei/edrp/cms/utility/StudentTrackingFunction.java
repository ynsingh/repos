
/*
 * @(#) StudentTrackingAndFunction.java
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
package in.ac.dei.edrp.cms.utility;

import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.domain.registration.prestaging.MasterTransferBean;
import in.ac.dei.edrp.cms.domain.utility.ErrorLogs;
import in.ac.dei.edrp.cms.domain.utility.StudentTracking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class StudentTrackingFunction {

	private TransactionTemplate transactionTemplate = null;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	protected SqlMapClient sqlMapClient = null;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public StudentTrackingFunction(SqlMapClient sqlMapClient,
			TransactionTemplate transactionTemplate) {
		this.sqlMapClient = sqlMapClient;
		this.transactionTemplate = transactionTemplate;
	}

	public StudentTrackingFunction() {

	}

	/**
	 * This method returns sequence number for that semester start date and end
	 * date
	 * 
	 * @param masterTransferBean
	 * @return
	 */
	public int getStdentTrackingIssue(StudentTracking studentTracking) {
		// TODO Auto-generated method stub
		try {
			List<StudentTracking> getCountForTrack = sqlMapClient.queryForList(
					"studentTrackingAndLogs.getCountInStudentTracking",
					studentTracking);
			for (StudentTracking count : getCountForTrack) {
				System.out.println("Count of student tracking "
						+ count.getSequenceNumber());
				return count.getSequenceNumber();
			}
		} catch (Exception e) {
			System.out.println("Exception getting Student Tracking:"
					+ e.getMessage());
		}
		return 0;
	}

	public int batchProcessExist(ErrorLogs errorLogs) {
		// TODO Auto-generated method stub
		List<ErrorLogs> valueList = new ArrayList<ErrorLogs>();

		int counter = 0;

		try {
			if (errorLogs.getBranchId() == null
					|| errorLogs.getSpecializationId() == null) {
				valueList = sqlMapClient
						.queryForList(
								"studentTrackingAndLogs.getBatchCounterForProgramActivity",
								errorLogs);
			} else {
				valueList = sqlMapClient.queryForList(
						"studentTrackingAndLogs.getBatchCounter", errorLogs);
			}
			for (ErrorLogs processCounter : valueList) {

				counter = processCounter.getProcessCounter();
				System.out.println("Process count=" + counter);

			}

		} catch (Exception e) {
			System.out.println("Process count exception is:" + e.getMessage());
		}

		return counter;
	}

	public boolean insertStudentErrorLogs(String entityId,
			String programId, String branchId, String specialization, String semester,
			String semesterStartDate, String semesterEndDate, String processId, String activityId,
			String enrollmentNumber,String studentId,String name,String reasonCode,
			String processStatus,int programCounter){
		try{
			
			ErrorLogs errorLogs = new ErrorLogs(entityId,
					programId, branchId, specialization, semester, semesterStartDate, semesterEndDate, 
					processId, activityId);
			
			errorLogs.setEnrollmentNumber(enrollmentNumber);
			errorLogs.setStudentId(studentId);
			errorLogs.setStudentName(name);
			errorLogs.setReasonCode(reasonCode);
			errorLogs.setDescription(processStatus);
			errorLogs.setProcessCounter(programCounter);
			errorLogs.setProcessFlag(CRConstant.ERROR_STATUS);
			sqlMapClient.insert("studentTrackingAndLogs.insertStudentErrorLog",errorLogs);
			return true;
		}catch(Exception e){
			System.out.println("Exception e");
			return false;
		}
		
	}
	
	public boolean insertStudentTracking(String entityId,
			String rollNumber,String programCourseKey,
			String semesterStartDate,String semesterEndDate,
			String sessionStartDate,String sessionEndDate,
			String oldStatus,String newStatus, String userId,
			String activityId, String processId){
		try{
			System.out.println(activityId+" "+processId);
			StudentTracking studentTracking = new StudentTracking(
					entityId,
					rollNumber,
					programCourseKey,
					semesterStartDate,
					semesterEndDate,
					sessionStartDate,
					sessionEndDate,
					oldStatus,
					newStatus, userId, activityId, processId);

			int sequence = new StudentTrackingFunction(sqlMapClient,
					transactionTemplate)
					.getStdentTrackingIssue(studentTracking);

			studentTracking.setSequenceNumber(sequence + 1);
			// Insert a student in student tracking with semester status
			// YTR and program status ACT
			int stInsert=sqlMapClient.update(
					"studentTrackingAndLogs.insertStudentTracking",
					studentTracking);
			return true;
		}catch(Exception e){
			System.out.println("Exception e"+e.getMessage());
			return false;
		}
		
	}
	
	
	// To get Full Name for students
	public String getFullName(String studentFirstName,
			String studentMiddleName, String studentLastName) {
		// TODO Auto-generated method stub

		return studentFirstName + getName(studentMiddleName)
				+ getName(studentLastName);
	}

	private String getName(String name) {
		// TODO Auto-generated method stub
		if (name == null || name.equalsIgnoreCase(null)) {
			name = "";
		}
		return name;
	}
}
