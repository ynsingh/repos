package in.ac.dei.edrp.cms.daoimpl.registration.prestagingdaoimpl;

/*
 * @(#) ReadyPreStagingDAOImpl.java
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

import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.dao.registration.prestagingdao.ReadyPreStagingDAO;
import in.ac.dei.edrp.cms.daoimpl.activitymaster.StartActivityDaoImpl;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ActivityListOfProcess;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ActivityMasterBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ApplicantUserInfoBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ProcessList;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ReadyPreStagingBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.TransferNORInPSTBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.TransferPSTRDYInSTBean;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;
import in.ac.dei.edrp.cms.domain.utility.EmailTableBean;
import in.ac.dei.edrp.cms.domain.utility.ErrorLogs;
import in.ac.dei.edrp.cms.utility.CRException;
import in.ac.dei.edrp.cms.utility.ReadyPreStaging;
import in.ac.dei.edrp.cms.utility.StudentTrackingFunction;
import in.ac.dei.edrp.cms.utility.TransferInST;

import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.core.ProcessSwitchCallback;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @version 1.0 Creation date: 17-Jan-2011 Class Description: If a student have
 *          only one record per admission mode, process makes its process_status
 *          in Prestaging_table by 'RDY'. If a student has duplicate entry for
 *          any mode, then it make status of student by ERR with error reason
 *          code and description in Prestaging_table and if student have both
 *          admission mode ('NOR' and 'SWT'), it make status by INA for NOR mode
 *          and 'RDY' for SWT mode.
 * @author <a href="http://deepak2rok.blogspot.com" target="_blank">Deepak
 *         Pandey</a>
 */
public class ReadyPreStagingDAOImpl implements ReadyPreStagingDAO {
	// Object of Logger class: is used to store logs
	static final Logger logger = Logger.getLogger(ReadyPreStagingDAOImpl.class);

	protected SqlMapClient sqlMapClient = null;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	TransactionTemplate transactionTemplate = null;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * This method returns
	 */
	@SuppressWarnings("unchecked")
	public List<ActivityMasterBean> getAllActivities(
			ActivityMasterBean activityMasterBean) {
		System.out.println("Coming inside Activity");
		List<ActivityMasterBean> activityList = new ArrayList<ActivityMasterBean>();
		try {

			activityList = sqlMapClient.queryForList(
					"MakeStatusReadyPreStaging.getActivityList",
					activityMasterBean);
			System.out.println("Coming in imp" + activityList);

		} catch (Exception e) {
			System.out.println("Coming inside : " + e);
		}
		return activityList;
	}

	@SuppressWarnings("unchecked")
	public ProgressCardInfo getSemesterDates(ProgressCardInfo progressCardInfo) {
		// TODO Auto-generated method stub
		List<ProgressCardInfo> semesterDate = new ArrayList<ProgressCardInfo>();
		ProgressCardInfo progress = new ProgressCardInfo();
		try {
			StartActivityDaoImpl startActivityDaoImpl = new StartActivityDaoImpl();
			startActivityDaoImpl.setSqlMapClient(sqlMapClient);
			startActivityDaoImpl.setTransactionTemplate(transactionTemplate);

			// To get Session Start data and end date and setting this into
			// session start and end date in progressCardInfo object
			List<StartActivityBean> sessionList = startActivityDaoImpl
					.getSessionDate(progressCardInfo.getUniversityCode());
			for (StartActivityBean sessionListBean : sessionList) {
				progressCardInfo.setSessionStartDate(sessionListBean
						.getSessionStartDate());
				progressCardInfo.setSessionEndDate(sessionListBean
						.getSessionEndDate());
				System.out.println(sessionListBean.getSessionStartDate()
						+ sessionListBean.getSessionEndDate());
			}
			System.out.println("Coming inside dates"
					+ progressCardInfo.getProgramId()
					+ progressCardInfo.getSemesterId()
					+ progressCardInfo.getSessionStartDate()
					+ progressCardInfo.getSessionEndDate());
			semesterDate = sqlMapClient.queryForList(
					"progressCard.semesterDate", progressCardInfo);
			for (ProgressCardInfo progress1 : semesterDate) {
				progress = progress1;
			}

		} catch (Exception e) {
			System.out.println("Coming: " + e);
		}
		return progress;
	}

	/**
	 * 
	 * @param activityMasterBean
	 *            It has user input. These are entity id, program id, semester,
	 *            semester start date, semester end date, process name and
	 *            activity name
	 * @return It returns true if all students for input is processed
	 *         successfully
	 */
	@SuppressWarnings("unchecked")
	public CountProcessRecorList makePreStageReady(
			ActivityMasterBean activityMasterBean) {
		//These type of file will be used to print PDF files:
		List<UnProcessedStduent> successStudentList=new ArrayList<UnProcessedStduent>();
		successStudentList.add(new UnProcessedStduent("Enrolment number|rollnumber",
				"Program Name|Semester Start Date|Semester End Date|Admission Mode","Transferred Into Staging"));
		List<UnProcessedStduent> failStudentList=new ArrayList<UnProcessedStduent>();
		failStudentList.add(new UnProcessedStduent("Enrolment number|rollnumber",
				"Program Name|Semester Start Date|Semester End Date|Admission Mode","Transferred Into Staging"));
		List<List<UnProcessedStduent>> studentList=new ArrayList<List<UnProcessedStduent>>();
		
		CountProcessRecorList countList = new CountProcessRecorList();

		ReadyPreStaging readyPreStaging = new ReadyPreStaging(sqlMapClient,
				transactionTemplate);

		PreStagingTransactionFunction preStagingTransactionFunction = new PreStagingTransactionFunction(
				sqlMapClient, transactionTemplate);

		ErrorLogs errorLogs = new ErrorLogs(activityMasterBean.getEntityId(),
				activityMasterBean.getProgramId(), 
				activityMasterBean.getBranchId(), activityMasterBean
						.getSpecializationId(), activityMasterBean
						.getSemesterCode(), activityMasterBean
						.getSemesterStartDate(), activityMasterBean
						.getSemesterEndDate(), activityMasterBean
						.getActivityId(), activityMasterBean.getActivityId());
		// List of Normal Students havin Switch entry also
		List<ReadyPreStagingBean> studentListofNormalWithSwitch = new ArrayList<ReadyPreStagingBean>();
		// List of student's having only one entry either NORMAL or Switch
		List<ReadyPreStagingBean> studentListofNormalOrSwitchMode = new ArrayList<ReadyPreStagingBean>();
		// List of student's having NEW entry
		List<ReadyPreStagingBean> studentListofNewMode = new ArrayList<ReadyPreStagingBean>();

		int actualRecords = 0;
		int processedStudent = 0;
		int recordsFailed = 0;
		int rejectedValue = 0;

		boolean processedFlag = false;

		try {

			int processCounter = new StudentTrackingFunction(sqlMapClient,
					transactionTemplate).batchProcessExist(errorLogs);

			errorLogs.setProcessCounter(processCounter + 1);

			String entityId = activityMasterBean.getEntityId();
			String programId = activityMasterBean.getProgramId();
			String semesterCode = activityMasterBean.getSemesterCode();
			String semesterStartDate = activityMasterBean
					.getSemesterStartDate();
			String semesterEndDate = activityMasterBean.getSemesterEndDate();
			String userId = activityMasterBean.getUserId();

			String sessionStartDate = activityMasterBean.getSessionStartDate();
			String sessionEndDate = activityMasterBean.getSessionEndDate();

			String processId = CRConstant.PROGRAM_LEVEL_ACTIVITY;
			String activityId = activityMasterBean.getActivityId();

			// String currentStatus="YTR";
			String processStatusForNormal = "";
			String processStatusForSwitch = "";

			String reasonCode = "";
			String description = "";

			String branchId = "";
			String specializationId = "";

			// boolean updatePSTWithError=false;

			ReadyPreStagingBean normalStudentWithSwitch = new ReadyPreStagingBean(
					entityId, programId, semesterCode, semesterStartDate,
					semesterEndDate, CRConstant.NORMAL_MODE,
					CRConstant.SWITCH_MODE);

			studentListofNormalWithSwitch = (List<ReadyPreStagingBean>) sqlMapClient
					.queryForList(
							"MakeStatusReadyPreStaging.getNormalStudentsWithSwitch",
							normalStudentWithSwitch);
			for (ReadyPreStagingBean studentListResult : studentListofNormalWithSwitch) {
				String enrollmentNumber = studentListResult
						.getEnrollmentNumber();
				String registrationRollNumber = studentListResult
						.getRollNumber();
				String studentId = studentListResult.getStudentId();

				ReadyPreStagingBean countDuplicateNormal = new ReadyPreStagingBean(
						entityId, programId, semesterCode, semesterStartDate,
						semesterEndDate, enrollmentNumber,
						registrationRollNumber, CRConstant.NORMAL_MODE, userId);

				ReadyPreStagingBean countSwitchDuplicate = new ReadyPreStagingBean(
						entityId, programId, semesterCode, semesterStartDate,
						semesterEndDate, enrollmentNumber,
						registrationRollNumber, CRConstant.SWITCH_MODE, userId);

				ReadyPreStagingBean updateNormalWithSwitch = new ReadyPreStagingBean();
				ReadyPreStagingBean updateSwitchWithNormal = new ReadyPreStagingBean();
				ReadyPreStagingBean updateSRSH = new ReadyPreStagingBean();
				boolean b = false;

				branchId = "";
				specializationId = "";

				if (!readyPreStaging
						.checkDuplicateEnrollmentForSameMode(countDuplicateNormal)) {
					// System.out.println("Duplicate in
					// Nor"+registrationRollNumber);
					if (!readyPreStaging
							.checkDuplicateEnrollmentForSameMode(countSwitchDuplicate)) {
						reasonCode = CRConstant.DUPLICATE_NORMAL_SWITCH;
						description = CRConstant.DUPLICATE_NORMAL_SWITCH_REASON;
						logger.info(CRConstant.DUPLICATE_NORMAL_SWITCH_REASON
								+ " = " + registrationRollNumber);

					} else {
						reasonCode = CRConstant.DUPLICATE_REGISTRATION_ROLL_NUMBER;
						description = CRConstant.DUPLICATE_REGISTRATION_REASON;
					}
					processStatusForNormal = CRConstant.ERROR_STATUS;
					processStatusForSwitch = CRConstant.ERROR_STATUS;

					logger.info(CRConstant.DUPLICATE_REGISTRATION_REASON
							+ " = " + registrationRollNumber);
				} else {
					// System.out.println("No Duplicate in
					// Nor"+registrationRollNumber);
					if (!readyPreStaging
							.checkDuplicateEnrollmentForSameMode(countSwitchDuplicate)) {
						reasonCode = CRConstant.DUPLICATE_SWITCH;
						description = CRConstant.DUPLICATE_SWITCH_REASON;
						processStatusForNormal = CRConstant.ERROR_STATUS;
						processStatusForSwitch = CRConstant.ERROR_STATUS;
						logger.info(CRConstant.DUPLICATE_REGISTRATION_REASON
								+ " = " + registrationRollNumber);
					} else {
						reasonCode = CRConstant.DUPLICATE_SWITCH;
						description = CRConstant.DUPLICATE_SWITCH_REASON;
						processStatusForNormal = CRConstant.INACTIVE_STATUS;
						processStatusForSwitch = CRConstant.READY_STATUS;
						// Here student is INA for admission_mode normal and INA
						// in SRSH

//						List<ReadyPreStagingBean> getDetailsForSRSH = (List<ReadyPreStagingBean>) sqlMapClient
//								.queryForList(
//										"MakeStatusReadyPreStaging.getDetailsForSRSHupdate",
//										countDuplicateNormal);
//						for (ReadyPreStagingBean detailForSRSH : getDetailsForSRSH) {
//							branchId = detailForSRSH.getBranchId();
//							specializationId = detailForSRSH
//									.getSpecializationId();
//						}
//
//						updateSRSH = new ReadyPreStagingBean(entityId,
//								programId, branchId, specializationId,
//								semesterCode, semesterStartDate,
//								semesterEndDate, enrollmentNumber,
//								registrationRollNumber, CRConstant.NORMAL_MODE,
//								processStatusForNormal, userId,
//								CRConstant.YET_TO_REGISTER);
//						b = true;
						logger.info("Student's having switch and normal both entry ="
										+ registrationRollNumber);
					}

				}

				updateNormalWithSwitch = new ReadyPreStagingBean(entityId,
						programId, semesterCode, semesterStartDate,
						semesterEndDate, enrollmentNumber,
						registrationRollNumber, CRConstant.NORMAL_MODE,
						processStatusForNormal, reasonCode, description, userId);
				updateSwitchWithNormal = new ReadyPreStagingBean(entityId,
						programId, semesterCode, semesterStartDate,
						semesterEndDate, enrollmentNumber,
						registrationRollNumber, CRConstant.SWITCH_MODE,
						processStatusForSwitch, reasonCode, description, userId);

				// preStagingTransactionFunction.insertNORWithSWTInPST(updateNormalWithSwitch,updateSwitchWithNormal,updateSRSH,b);
				String processStatus=preStagingTransactionFunction.insertNORWithSWTInPST(
						updateNormalWithSwitch, updateSwitchWithNormal,
						updateSRSH, b);
				if (processStatus.equalsIgnoreCase("success")) {
					processedStudent++;
					successStudentList.add(new UnProcessedStduent(enrollmentNumber+"|"+registrationRollNumber,
							"programId"+"|"+semesterStartDate+"-"+semesterEndDate+"|"+CRConstant.NEW_MODE,description));
				} else {
					recordsFailed++;
					errorLogs.setEnrollmentNumber(enrollmentNumber);
					errorLogs.setStudentId(studentId);
					errorLogs.setStudentName(registrationRollNumber);
					errorLogs.setReasonCode("DBI");
					errorLogs.setDescription("Database inconsistancy");
					errorLogs.setProcessFlag(CRConstant.ERROR_STATUS);
					sqlMapClient.insert("studentTrackingAndLogs.insertStudentErrorLog",errorLogs);
					failStudentList.add(new UnProcessedStduent(enrollmentNumber+"|"+registrationRollNumber,
							"programId"+"|"+semesterStartDate+"|"+semesterEndDate+"|"+CRConstant.NEW_MODE,description));

				}

			}// Loop for student's having normal and switch ends

			// Only normal students
			System.out.println("***********After first list**********");
			studentListofNormalOrSwitchMode = (List<ReadyPreStagingBean>) sqlMapClient
					.queryForList(
							"MakeStatusReadyPreStaging.getNormalOrSwitchStudent",
							normalStudentWithSwitch);

			for (ReadyPreStagingBean studentNormalOrSwitch : studentListofNormalOrSwitchMode) {
				String enrollmentNumber = studentNormalOrSwitch
						.getEnrollmentNumber();
				String registrationRollNumber = studentNormalOrSwitch
						.getRollNumber();
				String studentId = studentNormalOrSwitch.getStudentId();
				String admissionMode = studentNormalOrSwitch.getAdmissionMode();

				ReadyPreStagingBean countNormalOrSwitchDuplicate = new ReadyPreStagingBean(
						entityId, programId, semesterCode, semesterStartDate,
						semesterEndDate, enrollmentNumber,
						registrationRollNumber, admissionMode, userId,
						studentNormalOrSwitch.getStudentName(),studentNormalOrSwitch.getFatherName(),
						studentNormalOrSwitch.getMotherName(),studentNormalOrSwitch.getDateOfBirth(),
						studentNormalOrSwitch.getGender(),studentNormalOrSwitch.getCategory());
				// readyPreStaging.updatePSTAfterCheck(countNormalOrSwitchDuplicate);
				if (readyPreStaging
						.updatePSTAfterCheck(countNormalOrSwitchDuplicate)) {
					processedStudent++;
					successStudentList.add(new UnProcessedStduent(enrollmentNumber+"|"+registrationRollNumber,
							"programId"+"|"+semesterStartDate+"|"+semesterEndDate+"|"+CRConstant.NEW_MODE,description));
				} else {
					recordsFailed++;
					errorLogs.setEnrollmentNumber(enrollmentNumber);
					errorLogs.setStudentId(studentId);
					errorLogs.setStudentName(registrationRollNumber);
					errorLogs.setReasonCode("DBI");
					errorLogs.setDescription("Database inconsistancy");
					errorLogs.setProcessFlag(CRConstant.ERROR_STATUS);
					sqlMapClient.insert("studentTrackingAndLogs.insertStudentErrorLog",errorLogs);
					failStudentList.add(new UnProcessedStduent(enrollmentNumber+"|"+registrationRollNumber,
							"programId"+"|"+semesterStartDate+"|"+semesterEndDate+"|"+CRConstant.NEW_MODE,description));
				}
			}

			// Only New Students will be processed
			ReadyPreStagingBean normalStudent = new ReadyPreStagingBean(
					entityId, programId, semesterCode, semesterStartDate,
					semesterEndDate, CRConstant.NEW_MODE);

			studentListofNewMode = (List<ReadyPreStagingBean>) sqlMapClient
					.queryForList("MakeStatusReadyPreStaging.getNewStudent",
							normalStudent);

			for (ReadyPreStagingBean studentWithNewMode : studentListofNewMode) {
				String enrollmentNumber = studentWithNewMode
						.getEnrollmentNumber();
				String registrationRollNumber = studentWithNewMode
						.getRollNumber();
				String studentId = studentWithNewMode.getStudentId();

				ReadyPreStagingBean countNewDuplicate = new ReadyPreStagingBean(
						entityId, programId, semesterCode, semesterStartDate,
						semesterEndDate, enrollmentNumber,
						registrationRollNumber, CRConstant.NEW_MODE, userId,
						studentWithNewMode.getStudentName(),studentWithNewMode.getFatherName(),
						studentWithNewMode.getMotherName(),studentWithNewMode.getDateOfBirth(),
						studentWithNewMode.getGender(),studentWithNewMode.getCategory());
				
				if (readyPreStaging.updatePSTAfterCheck(countNewDuplicate)) {
					processedStudent++;
					successStudentList.add(new UnProcessedStduent(enrollmentNumber+"|"+registrationRollNumber,
							"programId"+"|"+semesterStartDate+"|"+semesterEndDate+"|"+CRConstant.NEW_MODE,description));
				} else {
					recordsFailed++;
					errorLogs.setEnrollmentNumber(enrollmentNumber);
					errorLogs.setStudentId(studentId);
					errorLogs.setStudentName(registrationRollNumber);
					errorLogs.setReasonCode("DBI");
					errorLogs.setDescription("Database inconsistancy");
					errorLogs.setProcessFlag(CRConstant.ERROR_STATUS);
					sqlMapClient.insert("studentTrackingAndLogs.insertStudentErrorLog",errorLogs);
					failStudentList.add(new UnProcessedStduent(enrollmentNumber+"|"+registrationRollNumber,
							"programId"+"|"+semesterStartDate+"|"+semesterEndDate+"|"+CRConstant.NEW_MODE,description));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Process 5 have error and error is: " + e.getMessage());
		}
		// Validation function will be called here. If that successfully
		// executes, then only process 5 is complete
		finally {
			actualRecords = studentListofNormalWithSwitch.size()
					+ studentListofNormalOrSwitchMode.size()
					+ studentListofNewMode.size();

			rejectedValue=actualRecords-(processedStudent+recordsFailed);
			String processFlag = CRConstant.INACTIVE_STATUS;

			System.out.println(actualRecords + "=" + processedStudent);
			if (actualRecords == processedStudent && actualRecords != 0) {
				System.out.println("Coming here");
				processFlag = CRConstant.COMPLETE_STATUS;
				processedFlag = true;
			}
			
			errorLogs.setActualRecord(actualRecords);
			errorLogs.setProcessRecord(processedStudent);
			errorLogs.setFailrecord(recordsFailed + rejectedValue);
			errorLogs.setProcessFlag(processFlag);

			studentList.add(successStudentList);
			studentList.add(failStudentList);
			
			countList = new CountProcessRecorList(actualRecords,
					processedStudent, rejectedValue, recordsFailed,
					processedFlag,studentList);
			
			try {
				sqlMapClient.insert("studentTrackingAndLogs.insertBatchLogs",
						errorLogs);
			} catch (Exception e1) {
				System.out.println("Exception inside finally block"
						+ e1.getMessage());
				countList = new CountProcessRecorList(actualRecords,
						processedStudent, rejectedValue, recordsFailed,
						processedFlag,studentList);
			}

		}
		return countList;

	}

	/**
	 * PROCESS2: Student's having process_status 'RDY' in PRESTAGING_TABLE is
	 * transferred into STAGING_TABLE and process_status in PRESTAGING_TABLE is
	 * updated by 'PRC'. It transfers students on program level directly.
	 * 
	 * @param activityMasterBean
	 */
	public CountProcessRecorList transferRDYinST(
			final ActivityMasterBean activityMasterBean) {
		// TODO Auto-generated method stub
		List<UnProcessedStduent> successStudentList=new ArrayList<UnProcessedStduent>();
		successStudentList.add(new UnProcessedStduent("Enrolment number|rollnumber",
				"Program Name|Semester Start Date|Semester End Date|Admission Mode","Transferred Into Staging"));
		List<UnProcessedStduent> failStudentList=new ArrayList<UnProcessedStduent>();
		failStudentList.add(new UnProcessedStduent("Enrolment number|rollnumber",
				"Program Name|Semester Start Date|Semester End Date|Admission Mode","Transferred Into Staging"));
		List<List<UnProcessedStduent>> studentProcessedList=new ArrayList<List<UnProcessedStduent>>();
		CountProcessRecorList countList = new CountProcessRecorList();
		
		TransferInST transferInST = new TransferInST(sqlMapClient);

		ErrorLogs errorLogs = new ErrorLogs(activityMasterBean.getEntityId(),
				activityMasterBean.getProgramId(), activityMasterBean
						.getBranchId(), activityMasterBean
						.getSpecializationId(), activityMasterBean
						.getSemesterCode(), activityMasterBean
						.getSemesterStartDate(), activityMasterBean
						.getSemesterEndDate(), activityMasterBean
						.getActivityId(), activityMasterBean.getActivityId());

		// This object is responsible to call transaction function to insert or
		// update
		// insertStudentInStaging function will be called
		PreStagingTransactionFunction preStagingTransactionFunction = new PreStagingTransactionFunction(
				sqlMapClient, transactionTemplate);

		int actualRecords = 0;
		int processedStudent = 0;
		int rejectedValue = 0;
		int recordsFailed = 0;

		try {

			final String entityId = activityMasterBean.getEntityId();
			final String programId = activityMasterBean.getProgramId();
			final String semesterCode = activityMasterBean.getSemesterCode();
			final String semesterStartDate = activityMasterBean
					.getSemesterStartDate();
			final String semesterEndDate = activityMasterBean
					.getSemesterEndDate();
			String userId = activityMasterBean.getUserId();

			int processCounter = new StudentTrackingFunction(sqlMapClient,
					transactionTemplate).batchProcessExist(errorLogs);

			errorLogs.setProcessCounter(processCounter + 1);

			final TransferPSTRDYInSTBean objectToGetRDYStduent = new TransferPSTRDYInSTBean(
					entityId, programId, semesterCode, semesterStartDate,
					semesterEndDate, CRConstant.READY_STATUS);

			// Pick up list of student's with status RDY
			final List<TransferPSTRDYInSTBean> studentList = (List<TransferPSTRDYInSTBean>) sqlMapClient
					.queryForList("TransferPSTRDYInST.getReadyStudentFromPST",
							objectToGetRDYStduent);
			actualRecords = studentList.size();
			System.out.println("student list size:" + studentList.size());

			for (TransferPSTRDYInSTBean student : studentList) {

				// System.out.println("Coming here"+student.getRollNumber());

				try {
										
					TransferPSTRDYInSTBean objectToTransferInST = new TransferPSTRDYInSTBean(
							entityId, programId, semesterCode,
							semesterStartDate, semesterEndDate, student
									.getNewBranch(), student
									.getNewSpecialization(), student
									.getOldEntity(), student.getOldProgram(),
							student.getOldBranch(), student
									.getOldSpecialization(), student
									.getAdmissionMode(), student
									.getRollNumber(), student
									.getEnrollmentNumber(), student
									.getStudentId(),
							student.getOldRollNumber(), student
									.getStudentFirstName(), student
									.getStudentMiddleName(), student
									.getStudentLastName(), student
									.getFatherFirstName(), student
									.getFatherMiddleName(), student
									.getFatherLastName(), student
									.getMotherFirstName(), student
									.getMotherMiddleName(), student
									.getMotherLastName(), student
									.getCategoryCode(), student.getGender(),
							student.getDateOfBirth(), student
									.getPrimaryEmailId(), student
									.getStatusInSemester(), student
									.getRegisterDueDate(), student
									.getAttemptNumber(), student
									.getOldSemester(), student
									.getProbableSemester(), student
									.getProbableSemesterStartDate(), student
									.getProbableSemesterEndDate(), student
									.getProbableRegisterDueDate(), student
									.getProbableAttemptNumber(), userId,
							student.getPerAddress(), student.getPerCity(),
							student.getPerState(), student.getPerPincode(),
							student.getCorAddress(), student.getCorCity(),
							student.getCorState(), student.getCorPincode(),
							student.getOfficePhone(), student.getExtraPhone(),
							student.getOtherPhone(), student.getFax());
                            //****New lines added by Nupur*********
					objectToTransferInST.setRollNumberGroupCode(student.getRollNumberGroupCode());
					objectToTransferInST.setLongField(student.getLongField());
					//**************************************
					
					//To find out sequence number
					List<TransferPSTRDYInSTBean> sequenceNumber=sqlMapClient.queryForList("TransferPSTRDYInST.getSequenceNumber", objectToTransferInST);
					for(TransferPSTRDYInSTBean sequence:sequenceNumber){
						objectToTransferInST.setSequenceNumber(sequence.getSequenceNumber());
					}

					TransferPSTRDYInSTBean objectForUpdatePST = new TransferPSTRDYInSTBean(
							entityId, programId, semesterCode,
							semesterStartDate, semesterEndDate, student
									.getRollNumber(),
							CRConstant.PROCESSED_STATUS);
					objectForUpdatePST.setNewBranch(student.getNewBranch());
					objectForUpdatePST.setNewSpecialization(student.getNewSpecialization());

					String password = "";
					ApplicantUserInfoBean objectToInsertInAI = new ApplicantUserInfoBean();
					EmailTableBean emailTable = new EmailTableBean();

					// If admission mode of student is either NOR or SWT or NEW,
					// then only database query executes
					if (student.getAdmissionMode().equalsIgnoreCase(
							CRConstant.NORMAL_MODE)
							|| student.getAdmissionMode().equalsIgnoreCase(
									CRConstant.SWITCH_MODE)
							|| student.getAdmissionMode().equalsIgnoreCase(
									CRConstant.NEW_MODE)) {

						if (student.getAdmissionMode().equalsIgnoreCase(
								CRConstant.NORMAL_MODE)
								|| student.getAdmissionMode().equalsIgnoreCase(
										CRConstant.SWITCH_MODE)) {

							// Copy student from USER INFO to APPLICANT_INFO and
							// send mail
							ApplicantUserInfoBean applicantUserInfoBean = new ApplicantUserInfoBean(
									student.getRollNumber(),
									CRConstant.USER_STUDENT_GROUP_ID);
							// password=transferInST.getPasswordFromUser(applicantUserInfoBean);
							System.out.println(password + " for "
									+ student.getRollNumber());
							objectToInsertInAI = new ApplicantUserInfoBean(
									activityMasterBean.getUniversityId(),student.getStudentId(), student
											.getRollNumber(), password,
									CRConstant.USER_STUDENT_GROUP_ID,
									CRConstant.ACTIVE_STATUS, userId);

						}

						// Generate a random password for student's having NEW
						// mode and send mail
						if (student.getAdmissionMode().equalsIgnoreCase(
								CRConstant.NEW_MODE)) {
							// Insert student in APPLICANT_INFO with system
							// genrated password and send mail
							emailTable = new EmailTableBean(student
									.getStudentId(), entityId, programId,
									student.getNewBranch(), student
											.getNewSpecialization(),
									semesterCode,
									CRConstant.USER_STUDENT_GROUP_ID, student
											.getRollNumber(), student
											.getPrimaryEmailId(), "AI", true,userId);
							// password=transferInST.generatePassword(CRConstant.MIN_PASSWORD_LENGTH,CRConstant.MAX_PASSWORD_LENGTH);
							// objectToInsertInAI=new
							// ApplicantUserInfoBean(student.getStudentId(),
							// student.getRollNumber(),password,CRConstant.USER_STUDENT_GROUP_ID,CRConstant.ACTIVE_STATUS,userId);

						}
						System.out.println("Comingsdsa dsad"
								+ student.getRollNumber());
						String processStatus=preStagingTransactionFunction.insertStudentInStaging(
								objectToTransferInST, objectForUpdatePST,
								objectToInsertInAI, emailTable);
						if(processStatus.equalsIgnoreCase("success")){
							processedStudent++;
							successStudentList.add(new UnProcessedStduent(student
									.getEnrollmentNumber()+"|"+student.getRollNumber(),activityMasterBean.getProgramId()
									+"-"+semesterCode+"|"+semesterStartDate+"-"+semesterEndDate+"|"+student.getAdmissionMode(),""));
							logger.info("student sucessfully transferred into staging_table and applicant_info. Roll number:"+ student.getRollNumber());
						}else{
							recordsFailed++;
							errorLogs.setEnrollmentNumber(student.getEnrollmentNumber());
							errorLogs.setStudentId(student.getStudentId());
							errorLogs.setStudentName(new StudentTrackingFunction().getFullName
									(student.getStudentFirstName(), 
											student.getStudentMiddleName(), student.getStudentLastName()));
							errorLogs.setReasonCode("DBI");
							errorLogs.setDescription("Database inconsistancy");
							errorLogs.setProcessFlag(CRConstant.ERROR_STATUS);
							sqlMapClient.insert("studentTrackingAndLogs.insertStudentErrorLog",errorLogs);
							successStudentList.add(new UnProcessedStduent(student
									.getEnrollmentNumber()+"|"+student.getRollNumber(),activityMasterBean.getProgramId()
									+"|"+semesterCode+"|"+semesterStartDate+"|"+semesterEndDate+"|"+student.getAdmissionMode(),"Database Issue, please refer logs"));
							logger.info("student not transferred into staging_table and applicant_info. Roll number:"+ student.getRollNumber());
						}
						

					}// NOR, SWT AND NEW If closed
				} catch (Exception e) {
					logger.info("Exception while tansfering student into staging"+e.getMessage());				
					System.out.println("Exception inside transfer to STAGING"
							+ e.getMessage());
				}
			}// Loop closed for student

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception in transferRDYinPST method ReadyPreStagingDAOImpl class "
							+ e.getMessage());
		} finally {
			
			String processFlag = CRConstant.INACTIVE_STATUS;
			boolean processedFlag = false;
			rejectedValue=actualRecords-(processedStudent+recordsFailed);
			System.out.println(actualRecords + "=" + processedStudent);
			if (actualRecords == processedStudent && actualRecords != 0) {
				System.out.println("Coming here");
				processFlag = CRConstant.COMPLETE_STATUS;
				processedFlag = true;
			}
			errorLogs.setActualRecord(actualRecords);
			errorLogs.setProcessRecord(processedStudent);
			errorLogs.setFailrecord(recordsFailed + rejectedValue);
			errorLogs.setProcessFlag(processFlag);
			studentProcessedList.add(successStudentList);
			studentProcessedList.add(failStudentList);
			try {
				sqlMapClient.insert("studentTrackingAndLogs.insertBatchLogs",
						errorLogs);
			} catch (Exception e1) {
				System.out.println("Exception inside finaaly" + e1);
				countList = new CountProcessRecorList(actualRecords,
						processedStudent, rejectedValue, recordsFailed,
						processedFlag,studentProcessedList);
				e1.printStackTrace();
			}
			countList = new CountProcessRecorList(actualRecords,
					processedStudent, rejectedValue, recordsFailed,
					processedFlag,studentProcessedList);
		}

		return countList;
	}
	
	/**
	 * The method checks the status for clear temporary tables process
	 * 
	 * @param activityMasterBean
	 *            object of the referenced bean
	 * @return status as object of the bean
	 */
	public ActivityMasterBean getstatusforCTT(
			ActivityMasterBean activityMasterBean) {
		ActivityMasterBean resultBean = new ActivityMasterBean();

		try {
			resultBean = (ActivityMasterBean) sqlMapClient.queryForObject(
					"TransferPSTRDYInST.getCTTStatus", activityMasterBean);
		} catch (Exception e) {
			logger.error("error in getstatusforCTT" + e);
		}
		return resultBean;
	}

}
