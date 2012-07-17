package in.ac.dei.edrp.cms.daoimpl.registration.mastertransferdaoimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.aspectj.util.LangUtil.ProcessController;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sun.org.apache.commons.digester.SetRootRule;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.daoimpl.registration.prestagingdaoimpl.TransfertNORInPSTFunction;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.registration.prestaging.AddressDetailBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.CourseList;

import in.ac.dei.edrp.cms.domain.registration.prestaging.MasterTransferBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.OldSemesterDetails;
import in.ac.dei.edrp.cms.domain.registration.prestaging.PersonalDetailsBean;

import in.ac.dei.edrp.cms.domain.registration.prestaging.TransferNORInPSTBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;
import in.ac.dei.edrp.cms.domain.utility.EmailTableBean;
import in.ac.dei.edrp.cms.domain.utility.ErrorLogs;
import in.ac.dei.edrp.cms.domain.utility.ErrorReason;
import in.ac.dei.edrp.cms.domain.utility.StudentTracking;
import in.ac.dei.edrp.cms.domain.utility.SystemValue;
import in.ac.dei.edrp.cms.utility.CRException;
import in.ac.dei.edrp.cms.utility.PreviousSemesterDetail;
import in.ac.dei.edrp.cms.utility.RegistrationFunction;
import in.ac.dei.edrp.cms.utility.StudentTrackingFunction;

public class TransferTempIntoMaster {

	private TransactionTemplate transactionTemplate = null;

	private SqlMapClient sqlMapClient = null;

	static final Logger logger = Logger.getLogger(TransferTempIntoMaster.class);
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public TransferTempIntoMaster(SqlMapClient sqlMapClient,
			TransactionTemplate transactionTemplate) {
		this.sqlMapClient = sqlMapClient;
		this.transactionTemplate = transactionTemplate;
	}

	public TransferTempIntoMaster() {

	}

	public CountProcessRecorList transferTempToMaster(
			MasterTransferBean masterTransferBeanObject) {

		CountProcessRecorList countList = new CountProcessRecorList();
		List<UnProcessedStduent> studentList=new ArrayList<UnProcessedStduent>();
//		List<UnProcessedStduent> successStudentList=new ArrayList<UnProcessedStduent>();
//		successStudentList.add(new UnProcessedStduent("Enrolment number|rollnumber|Student Name",
//				"Program Id|Semester Start Date|Semester End Date|Admission Mode","Description"));
		List<UnProcessedStduent> failStudentList=new ArrayList<UnProcessedStduent>();
//		failStudentList.add(new UnProcessedStduent("Enrolment number|rollnumber",
//				"Program Id|Semester Start Date|Semester End Date|Admission Mode","Description"));
		
		RegistrationFunction registrationFunction = new RegistrationFunction(
				sqlMapClient, transactionTemplate);
		
		
		ErrorLogs errorLogs = new ErrorLogs(masterTransferBeanObject
				.getNewEntity(), masterTransferBeanObject.getNewProgram(),
				masterTransferBeanObject.getNewBranch(),
				masterTransferBeanObject.getNewSpecialization(),
				masterTransferBeanObject.getNewSemester(),
				masterTransferBeanObject.getSemesterStartDate(),
				masterTransferBeanObject.getSemesterEndDate(),
				masterTransferBeanObject.getProcessId(),
				masterTransferBeanObject.getActivityId());

		List<MasterTransferBean> normalList = new ArrayList<MasterTransferBean>();
		List<MasterTransferBean> switchList = new ArrayList<MasterTransferBean>();
		List<MasterTransferBean> newList = new ArrayList<MasterTransferBean>();

		boolean processedFlag = false;

		int recordsFailed = 0;
		int rejectedValue = 0;
		int processedStudent = 0;
		try {

			String universityId = masterTransferBeanObject.getUniveristyId();
			String userId = masterTransferBeanObject.getUserId();

			String entityId = masterTransferBeanObject.getNewEntity();
			System.out.println("Check by Dheeraj  ===="+entityId);
			
			String programId = masterTransferBeanObject.getNewProgram();
			String branchId = masterTransferBeanObject.getNewBranch();
			String specializationId = masterTransferBeanObject.getNewSpecialization();
			String semesterCode = masterTransferBeanObject.getNewSemester();
			String semesterStartDate = masterTransferBeanObject
					.getSemesterStartDate();
			String semesterEndDate = masterTransferBeanObject
					.getSemesterEndDate();
			String programCourseKey = masterTransferBeanObject
					.getProgramCourseKey();

			String sessionStartDate = masterTransferBeanObject
					.getSessionStartDate();
			String sessionEndDate = masterTransferBeanObject
					.getSessionEndDate();

			String processId = masterTransferBeanObject.getProcessId();
			String activityId = masterTransferBeanObject.getActivityId();

			int processCount = masterTransferBeanObject.getProcessCount();
			errorLogs.setProcessCounter(processCount);

			masterTransferBeanObject.setAdmissionMode(CRConstant.NORMAL_MODE);
			masterTransferBeanObject.setRegistrationStatus("G");

			MasterTransferBean masterNorrmalBeanObject = masterTransferBeanObject;
			// get list of student having admission mode NOR in
			// temp_student_program
			normalList = (List<MasterTransferBean>) sqlMapClient.queryForList(
					"masterTransfer.getListOfStudentWithAdmissionMode",
					masterNorrmalBeanObject);
			logger.info(normalList.size()+" student will be transferred in Masters with "+CRConstant.NORMAL_MODE+" admission mode");

			for (MasterTransferBean masterTransferBean : normalList) {
				String process="";
				ErrorReason errorReason = new ErrorReason("DBI","Values in database is incorrect",false);

				// List of courses of student from temp_student_courses
				masterTransferBean.setNewProgram(programId);
				masterTransferBean.setNewBranch(branchId);
				masterTransferBean.setNewSpecialization(specializationId);
				masterTransferBean.setNewSemester(semesterCode);
				masterTransferBean.setSemesterStartDate(semesterStartDate);
				masterTransferBean.setSemesterEndDate(semesterEndDate);
				masterTransferBean.setProgramCourseKey(programCourseKey);
				masterTransferBean.setUserId(userId);
				masterTransferBean.setNewEntity(entityId);
				
				System.out.println("Set Entity Dheeraj========= "+masterTransferBean.getNewEntity());
				
				List<CourseList> courseList = getCourseListForOldStudent(masterTransferBean);
				
				//List of courses:
				 System.out.println("List of courses: "+courseList+" program Course key"+programCourseKey);
				try {
					errorLogs.setEnrollmentNumber(masterTransferBean
							.getEnrollmentNumber());
					errorLogs.setStudentId(masterTransferBean.getStudentId());
					errorLogs.setStudentName(getStudentName(errorLogs));

					masterNorrmalBeanObject = new MasterTransferBean(
							universityId, entityId, programId, branchId,
							specializationId, semesterCode, semesterStartDate,
							semesterEndDate, masterTransferBean.getOldEntity(),
							masterTransferBean.getOldProgram(),
							masterTransferBean.getOldBranch(),
							masterTransferBean.getOldSpecialization(),
							masterTransferBean.getOldSemester(),
							masterTransferBean.getEnrollmentNumber(),
							masterTransferBean.getRollNumber(),
							masterTransferBean.getOldRollNumber(),
							masterTransferBean.getStudentId(),
							CRConstant.NORMAL_MODE, masterTransferBean
									.getStatusInSemester(), masterTransferBean
									.getRegisterDate(), masterTransferBean
									.getRegistrationStatus(),
							masterTransferBean.getSequenceNumber(), courseList,
							programCourseKey, userId, sessionStartDate,
							sessionEndDate, activityId, processCount,
							masterTransferBean.getRegisteredCredit(),
							masterTransferBean.getRegisteredCreditExcludingAudit(),
							masterTransferBean.getRegisteredTheoryExcludingAudit(),
							masterTransferBean.getRegisteredPracticalExcludingAudit());
					System.out.println(masterTransferBean.getProcessId());
					masterNorrmalBeanObject.setProcessId(processId);

					errorReason = validateDataForNORStudent(
							masterNorrmalBeanObject, courseList);

					if (errorReason.isValidRecord()) {

						process=transferNORStudentIntoMaster(masterNorrmalBeanObject,
								errorLogs);
						
					} else {
						process="fail";
						
					}
				} catch (Exception normalException) {
					System.out.println("rohit check"+masterTransferBean.getRollNumber());
					process="fail";
					System.out.println("rohitttttttttttttttttt"+process);
					errorReason.setDescription("Exception is "+normalException.getMessage());
					failStudentList.add(new UnProcessedStduent(masterTransferBean.getRollNumber(),errorReason.getDescription()));
				}
				if(process.equalsIgnoreCase("success")){
					processedStudent++;
//					successStudentList.add(new UnProcessedStduent(masterTransferBean.getEnrollmentNumber()+"|"+masterTransferBean.getRollNumber(),
//							masterTransferBean.getProgramCourseKey()+"|"+masterTransferBean.getSemesterStartDate()+"|"+
//							masterTransferBean.getSemesterEndDate()+"|"+CRConstant.NORMAL_MODE,""));
				}else{
					recordsFailed++;
					errorLogs.setReasonCode(errorReason.getReasonCode());
					errorLogs.setDescription(errorReason.getDescription());
					errorLogs.setProcessFlag(CRConstant.ERROR_STATUS);
					sqlMapClient.insert("studentTrackingAndLogs.insertStudentErrorLog",errorLogs);
					//failStudentList.add(new UnProcessedStduent(masterTransferBean.getRollNumber(),process));
					logger.info(masterTransferBean.getRollNumber()+" "+masterTransferBean.getSemesterStartDate()+
							" has not been successfully processed because"+errorReason.getDescription());
				}
			}//Students With NORMAL Admission Mode ends
			// get list of student having admission mode SWT in
			// temp_student_program
			MasterTransferBean masterSwitchBeanObject = masterTransferBeanObject;
			masterSwitchBeanObject.setAdmissionMode(CRConstant.SWITCH_MODE);
			switchList = (List<MasterTransferBean>) sqlMapClient.queryForList(
					"masterTransfer.getListOfStudentWithAdmissionMode",
					masterSwitchBeanObject);

			System.out.println("Switch student" + switchList.size());
			for (MasterTransferBean masterTransferBean : switchList) {
				String process="";
				ErrorReason errorReason = new ErrorReason("DBI","Values in database is incorrect",true);
				// List of courses of student from temp_student_courses
				masterTransferBean.setNewProgram(programId);
				masterTransferBean.setNewBranch(branchId);
				masterTransferBean.setNewSpecialization(specializationId);
				masterTransferBean.setNewSemester(semesterCode);
				masterTransferBean.setSemesterStartDate(semesterStartDate);
				masterTransferBean.setSemesterEndDate(semesterEndDate);
				masterTransferBean.setProgramCourseKey(programCourseKey);
				masterTransferBean.setUserId(userId);
				masterTransferBean.setNewEntity(entityId);
				
				
				List<CourseList> courseList = (List<CourseList>) getCourseListForOldStudent(masterTransferBean);

				OldSemesterDetails oldSemesterDetail = getOldProgramDetails(masterTransferBean);

				
				try {
					errorLogs.setEnrollmentNumber(masterTransferBean
							.getEnrollmentNumber());
					errorLogs.setStudentId(masterTransferBean.getStudentId());
					errorLogs.setStudentName(getStudentName(errorLogs));
					masterSwitchBeanObject = new MasterTransferBean(
							universityId, entityId, programId, branchId,
							specializationId, semesterCode, semesterStartDate,
							semesterEndDate, masterTransferBean.getOldEntity(),
							masterTransferBean.getOldProgram(),
							masterTransferBean.getOldBranch(),
							masterTransferBean.getOldSpecialization(),
							masterTransferBean.getOldSemester(),
							masterTransferBean.getEnrollmentNumber(),
							masterTransferBean.getRollNumber(),
							oldSemesterDetail.getOldRollNumber(),
							masterTransferBean.getStudentId(),
							CRConstant.SWITCH_MODE, masterTransferBean
									.getStatusInSemester(), masterTransferBean
									.getRegisterDate(), masterTransferBean
									.getRegistrationStatus(),
							masterTransferBean.getSequenceNumber(), courseList,
							programCourseKey, userId, sessionStartDate,
							sessionEndDate, activityId, processCount,
							masterTransferBean.getRegisteredCredit(),
							masterTransferBean.getRegisteredCreditExcludingAudit(),
							masterTransferBean.getRegisteredTheoryExcludingAudit(),
							masterTransferBean.getRegisteredPracticalExcludingAudit());
					masterSwitchBeanObject.setProcessId(processId);

					errorReason = validateDataForSWTStudent(
							masterSwitchBeanObject, courseList,
							oldSemesterDetail);
					if (errorReason.isValidRecord()) {

						process=transferSWTStudentIntoMaster(masterSwitchBeanObject,
								oldSemesterDetail, errorLogs);
						

					} else {
						process="fail";
						
					}
				} catch (Exception switchException) {
					process="fail";
					errorReason.setDescription("Exception is "+switchException.getMessage());
					failStudentList.add(new UnProcessedStduent(masterTransferBean.getRollNumber(),errorReason.getDescription()));
				}
				if(process.equalsIgnoreCase("success")){
					processedStudent++;
//					successStudentList.add(new UnProcessedStduent(masterTransferBean.getEnrollmentNumber()+"|"+masterTransferBean.getRollNumber(),
//							masterTransferBean.getProgramCourseKey()+"|"+masterTransferBean.getSemesterStartDate()+"|"+
//							masterTransferBean.getSemesterEndDate()+"|"+CRConstant.NORMAL_MODE,""));
				}else{
					recordsFailed++;
					errorLogs.setReasonCode("DBI");
					errorLogs.setDescription("Database inconsistancy");
					errorLogs.setProcessFlag(CRConstant.ERROR_STATUS);
					sqlMapClient.insert("studentTrackingAndLogs.insertStudentErrorLog",errorLogs);
					
				//	failStudentList.add(new UnProcessedStduent(masterTransferBean.getRollNumber(),errorLogs.getDescription()));
					
					logger.info(masterTransferBean.getRollNumber()+" "+masterTransferBean.getSemesterStartDate()+
							" has not been successfully processed because"+errorReason.getDescription());
				}
			}

			// get list of student having admission mode NEW in
			// temp_student_program
			MasterTransferBean masterNewBeanObject = masterTransferBeanObject;
			masterNewBeanObject.setAdmissionMode(CRConstant.NEW_MODE);
			newList = (List<MasterTransferBean>) sqlMapClient.queryForList(
					"masterTransfer.getListOfStudentWithAdmissionMode",
					masterTransferBeanObject);

			System.out.println("Total new stduent:" + newList.size());

			for (MasterTransferBean masterTransferBean : newList) {
				String process="";
				ErrorReason errorReason = new ErrorReason("DBI","Values in database is incorrect",true);
				PersonalDetailsBean personalDetailsBean = new PersonalDetailsBean(
						universityId, masterTransferBean.getEnrollmentNumber(),
						userId);

				// object with personal details of a student
				PersonalDetailsBean personalDetails = getPersonDetails(personalDetailsBean);

				// List of courses of student from temp_student_courses
				masterTransferBean.setNewProgram(programId);
				masterTransferBean.setNewBranch(branchId);
				masterTransferBean.setNewSpecialization(specializationId);
				masterTransferBean.setNewSemester(semesterCode);
				masterTransferBean.setSemesterStartDate(semesterStartDate);
				masterTransferBean.setSemesterEndDate(semesterEndDate);
				masterTransferBean.setProgramCourseKey(programCourseKey);
				masterTransferBean.setUserId(userId);
				masterTransferBean.setNewEntity(entityId);

				List<CourseList> courseList = getCourseList(masterTransferBean);
				
				try {
					System.out.println(sessionStartDate+" and "+sessionEndDate);
					masterNewBeanObject = new MasterTransferBean(universityId,
							entityId, programId, branchId, specializationId,
							semesterCode, semesterStartDate, semesterEndDate,

							masterTransferBean.getOldEntity(),
							masterTransferBean.getOldProgram(),
							masterTransferBean.getOldBranch(),
							masterTransferBean.getOldSpecialization(),
							masterTransferBean.getOldSemester(),

							masterTransferBean.getEnrollmentNumber(),
							masterTransferBean.getRollNumber(),

							masterTransferBean.getOldRollNumber(),
							masterTransferBean.getStudentId(),

							CRConstant.NEW_MODE, masterTransferBean
									.getStatusInSemester(),
							CRConstant.REGULAR_ADMISSION_MODE,
							masterTransferBean.getRegistrationStatus(),
							masterTransferBean.getSequenceNumber(), courseList,
							programCourseKey, userId, sessionStartDate,
							sessionEndDate, activityId, processCount,
							masterTransferBean.getRegisteredCredit(),
							masterTransferBean.getRegisteredCreditExcludingAudit(),
							masterTransferBean.getRegisteredTheoryExcludingAudit(),
							masterTransferBean.getRegisteredPracticalExcludingAudit());
					masterNewBeanObject.setProcessId(processId);
					System.out.println("Process is NEW:"+masterTransferBean.getProcessId());
					// To set mode of entry with RG: Regular
					// masterTransferBeanObject.setModeOfEntry(CRConstant.REGULAR_ADMISSION_MODE);

					personalDetails.setUniversityId(universityId);
					personalDetails.setEnrollmentNumber(masterTransferBean
							.getEnrollmentNumber());
					personalDetails.setUserId(userId);
					personalDetails.setEntityId(entityId);
					personalDetails.setRegisteredInSession(sessionStartDate
							.substring(0, 4)
							+ "-" + sessionEndDate.substring(2, 4));

					errorReason = validateDataForNEWStudent(
							masterNewBeanObject, courseList, personalDetails);

					errorLogs.setEnrollmentNumber(masterTransferBean
							.getEnrollmentNumber());
					errorLogs.setStudentId(masterTransferBean.getStudentId());
					errorLogs.setStudentName(getStudentName(errorLogs));

					if (errorReason.isValidRecord()) {

						process=transferNEWStudentIntoMaster(masterNewBeanObject,
								personalDetails, errorLogs);
					} else {
						process="fail";
					}
				} catch (Exception newException) {
					process="fail";
					errorReason.setDescription("Exception is "+newException.getMessage());
					failStudentList.add(new UnProcessedStduent(masterTransferBean.getRollNumber(),errorReason.getDescription()));
				}
				
				if(process.equalsIgnoreCase("success")){
					processedStudent++;
//					successStudentList.add(new UnProcessedStduent(masterTransferBean.getEnrollmentNumber()+"|"+masterTransferBean.getRollNumber(),
//							masterTransferBean.getProgramCourseKey()+"|"+masterTransferBean.getSemesterStartDate()+"|"+
//							masterTransferBean.getSemesterEndDate()+"|"+CRConstant.NORMAL_MODE,""));
					logger.info(masterTransferBean.getRollNumber()+" "+masterTransferBean.getSemesterStartDate()+" hasbeen successfully processed");
				}else{
					recordsFailed++;
					errorLogs.setReasonCode(errorReason.getReasonCode());
					errorLogs.setDescription(errorReason.getDescription());
					errorLogs.setProcessFlag(CRConstant.ERROR_STATUS);
					sqlMapClient.insert("studentTrackingAndLogs.insertStudentErrorLog",errorLogs);
				//	failStudentList.add(new UnProcessedStduent(masterTransferBean.getRollNumber(),errorReason.getDescription()));
					
					logger.info(masterTransferBean.getRollNumber()+" "+masterTransferBean.getSemesterStartDate()+
							" has not been successfully processed because"+errorReason.getDescription());
				}
			}

		} catch (Exception e) {
			System.out
					.println("Exception is transfer temporary to CR master database:"
							+ e);
			int actualRecords = normalList.size() + switchList.size()
			+ newList.size();
			//rejectedValue=actualRecords-(processedStudent+recordsFailed+rejectedValue);
			failStudentList.add(new UnProcessedStduent("Exception exist: All students are not processed yet",
					"Please Check Database Incomplete Records"));
		} finally {

			int actualRecords = normalList.size() + switchList.size()
					+ newList.size();
			// String processFlag=CRConstant.INACTIVE_STATUS;

			System.out.println(actualRecords + "=" + processedStudent);
			//studentList.add(successStudentList);
		//	studentList.add(failStudentList);
			if (actualRecords == processedStudent && actualRecords != 0) {
				System.out.println("Coming here");
				// processFlag=CRConstant.COMPLETE_STATUS;
				processedFlag = true;
			}

			countList = new CountProcessRecorList(actualRecords,
					processedStudent,recordsFailed,
					processedFlag,failStudentList);
		}

		//System.out.println("roht 26 nov"+countList.getRejStudentList().get(0).getRollNumber());
		return countList;

	}

	
	
	
	public String getStudentName(ErrorLogs errorLog) {
		// TODO Auto-generated method stub
		String studentName = "";
		try {
			List<ErrorLogs> nameList = sqlMapClient.queryForList(
					"studentTrackingAndLogs.getStudentNameForErrorLog",
					errorLog);
			System.out.println(errorLog.getEnrollmentNumber()
					+ errorLog.getStudentId());
			for (ErrorLogs error : nameList) {
				System.out.println("Student Name: " + error.getStudentName());
				studentName = error.getStudentName();
			}

		} catch (Exception e) {
			System.out.println("Exception in getting name" + e.getMessage());
		}
		return studentName;
	}

	public String transferNORStudentIntoMaster(
			final MasterTransferBean masterTransferBean,
			final ErrorLogs errorLogs) {

		String value=(String)transactionTemplate.execute(new TransactionCallback() {
			Object save = null;

			public String doInTransaction(TransactionStatus status) {
				try {
					StudentTrackingFunction studentTrackingFunction=new StudentTrackingFunction(sqlMapClient,transactionTemplate);
					
					save = status.createSavepoint();
					System.out
							.println("coming here in Nor student transaction");
					// Semester will be updated in Student Program by previous
					// semester
					int updateSP = sqlMapClient.update(
							"masterTransfer.updateStudentProgram",
							masterTransferBean);

					// Updates SRSH.status by REG for User Input
					int updatedSRSH = sqlMapClient.update(
							"masterTransfer.updateSRSHByREG",
							masterTransferBean);
					List<CourseList> list = masterTransferBean.getCourseList();
					int totalInsertedCourse=0;
					for (CourseList courseList : list) {
						// insert student in student courses
						System.out.println("inserting first course" + courseList);
						
						sqlMapClient.insert(
								"masterTransfer.insertIntoStudentCourse",
								courseList);

					}

					

					// update registration status by M
					int updateTSP = sqlMapClient.update(
							"masterTransfer.updateTempStudentProgram",
							masterTransferBean);

					int updateTSC = sqlMapClient.update(
							"masterTransfer.updateTempStudentCourse",
							masterTransferBean);

					boolean b=studentTrackingFunction.insertStudentTracking(masterTransferBean.getNewEntity(),
							masterTransferBean.getRollNumber(),
							masterTransferBean.getProgramCourseKey(),
							masterTransferBean.getSemesterStartDate(),
							masterTransferBean.getSemesterEndDate(),
							masterTransferBean.getSessionStartDate(),
							masterTransferBean.getSessionEndDate(),
							CRConstant.REGISTRATION_STATUS,
							CRConstant.ACTIVE_STATUS, masterTransferBean
									.getUserId(), masterTransferBean
									.getActivityId(), masterTransferBean
									.getProcessId());
					if(updateTSP==1&&updateTSC==list.size()&&b&&updateSP==1&&updatedSRSH==1){
						return "success";
					}else{
						throw new Exception();
					}
					
				} catch (DataAccessException e) {
					status.rollbackToSavepoint(save);
					logger.info("Exception for "+masterTransferBean.getRollNumber()+" this "+masterTransferBean.getProgramCourseKey()
							+" dates"+masterTransferBean.getSemesterStartDate()+" is :"+e.getMessage());
					throw new MyException(e.getMessage());
					//return "fail"+e.getMessage();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					status.rollbackToSavepoint(save);
					logger.info("Exception for "+masterTransferBean.getRollNumber()+" this "+masterTransferBean.getProgramCourseKey()
							+" dates"+masterTransferBean.getSemesterStartDate()+" is :"+e.getMessage());
					throw new MyException(e.getMessage());
					//return "fail"+e.getMessage();
				}
			}//

		});
		return value;
	}

	public String transferSWTStudentIntoMaster(
			final MasterTransferBean masterTransferBean,
			final OldSemesterDetails oldSemesterDetails,
			final ErrorLogs errorLogs) {

		String value=(String)transactionTemplate.execute(new TransactionCallback() {
			// Object savepoint=null;

			
			public String doInTransaction(TransactionStatus status) {
				Object savepoint=null;
				try {
					StudentTrackingFunction studentTrackingFunction=new StudentTrackingFunction(sqlMapClient,transactionTemplate);
					
					int updateSPOld=0;
					int stSPInsert=0;
					int stSPInsert1=0;
					
					boolean c=false;
					boolean d=false;
					savepoint=status.createSavepoint();
					System.out.println("Entity id "
							+ masterTransferBean.getNewEntity() + " : "
							+ masterTransferBean.getOldRollNumber() + " "
							+ masterTransferBean.getStudentId());

					SystemValue systemValue = new SystemValue(
							masterTransferBean.getUniveristyId(),
							CRConstant.SWITCH_NUMBER_CODE);

					int sequenceNumber = 0;
					if (oldSemesterDetails.getModeOfEntry().equalsIgnoreCase(
							CRConstant.REGULAR_ADMISSION_MODE)
							&& oldSemesterDetails.getSwitchNumber() == 0) {

						int switchNumber = new RegistrationFunction(
								sqlMapClient, transactionTemplate)
						.getIncrementedId(systemValue) + 1;
						
						
						sequenceNumber = oldSemesterDetails.getSequenceNumber() + 1;

						systemValue.setValue(String.valueOf(switchNumber));
						
						// Appending year part in switch number is update by Dheeraj on 6/2/2012
						
						Calendar cal = Calendar.getInstance();
						
						String generatedSwitchNumber = String.valueOf(cal.get(Calendar.YEAR)%100) 
														+ String.format("%05d", switchNumber);
						
						System.out.println("Dheeraj : " + generatedSwitchNumber);
						
						masterTransferBean.setSwitchNumber(Integer.parseInt(generatedSwitchNumber));
						
						systemValue.setModifierId(masterTransferBean
								.getUserId());
						masterTransferBean.setSequenceNumber(sequenceNumber);

						sqlMapClient.update(
								"systemValue.updateSystemValue", systemValue);
						

					} else {
						masterTransferBean.setSwitchNumber(oldSemesterDetails
								.getSwitchNumber());
						masterTransferBean.setSequenceNumber(oldSemesterDetails
								.getSequenceNumber());
						sequenceNumber = oldSemesterDetails.getSequenceNumber();
					}

					// masterTransferBean.setOldRollNumber(oldSemesterDetails.getOldRollNumber());

					if (masterTransferBean.getOldProgram().equalsIgnoreCase(
							masterTransferBean.getNewProgram())) {
						System.out.println("Second Check By Dheeraj == Same Program Id With Same Program"
								+ oldSemesterDetails.getProgramStatus()
								+ "masterTransferBean.getOldProgram()="
								+ masterTransferBean.getOldProgram());
						if (oldSemesterDetails.getProgramStatus()
								.equalsIgnoreCase(CRConstant.ACTIVE_STATUS)
								|| oldSemesterDetails.getProgramStatus()
										.equalsIgnoreCase(
												CRConstant.STATUS_PASS)) {
							System.out.println("Same either "
									+ oldSemesterDetails.getProgramStatus());

							if (oldSemesterDetails.getProgramStatus()
									.equalsIgnoreCase(CRConstant.ACTIVE_STATUS)) {
								// update student's status by SWT and else
								masterTransferBean
										.setProgramStatus(CRConstant.SWITCH_MODE);
								RegistrationFunction registrationFunction = new RegistrationFunction(
										sqlMapClient, transactionTemplate);
								String programCourseKey = registrationFunction
										.getProgramCourseKey(new TransferNORInPSTBean(
												masterTransferBean
														.getOldProgram(),
												masterTransferBean
														.getOldBranch(),
												masterTransferBean
														.getOldSpecialization(),
												masterTransferBean
														.getOldSemester()));
								

								PreviousSemesterDetail previousSemesterData = new PreviousSemesterDetail(
										masterTransferBean.getRollNumber(),
										masterTransferBean.getNewEntity(),
										programCourseKey);
								List<PreviousSemesterDetail> previousSemesterResult = registrationFunction
										.getPreviousSessionDate(previousSemesterData);

								String semesterStartDate = "";
								String semesterEndDate = "";
								String preStatus = "";
								for (PreviousSemesterDetail previous : previousSemesterResult) {
									// System.out.println("coming inside ths ");
									semesterStartDate = previous
											.getPreviousSemesterStartDate();
									semesterEndDate = previous
											.getPreviousSemesterEndDate();
									preStatus = previous.getStatus();		// Updated By Dheeraj
									System.out.println(semesterStartDate
											+ " abc " + semesterEndDate);
								}//
								
								c=studentTrackingFunction.insertStudentTracking(masterTransferBean.getNewEntity(),
										masterTransferBean.getRollNumber(),
										programCourseKey, semesterStartDate,
										semesterEndDate, masterTransferBean
												.getSessionStartDate(),
										masterTransferBean.getSessionEndDate(),
										preStatus, CRConstant.SWITCH_MODE,
										masterTransferBean.getUserId(),
										masterTransferBean.getActivityId(),
										masterTransferBean.getProcessId());
								
								logger.info("Updated to switch");

							}
							if (oldSemesterDetails.getProgramStatus()
									.equalsIgnoreCase(CRConstant.STATUS_PASS)) {
								masterTransferBean
										.setProgramStatus(CRConstant.STATUS_PASS);
								logger.info("Updated to PAS");

							}
							logger.info(""
									+ masterTransferBean.getSwitchNumber()
									+ " : "
									+ masterTransferBean.getSequenceNumber());
							updateSPOld=sqlMapClient.update(
									"masterTransfer.updateSPForSameProgram",
									masterTransferBean);
							System.out.println("Updated same progam");
						}// If Active_status and Status_Pass condition ends
					}// If Old Program and New program is same
					else {
						if (oldSemesterDetails.getProgramStatus()
								.equalsIgnoreCase(CRConstant.ACTIVE_STATUS)
								|| oldSemesterDetails.getProgramStatus()
										.equalsIgnoreCase(
												CRConstant.STATUS_PASS)) {

							
							logger.info("Updated Different Program");
							masterTransferBean
									.setProgramStatus(oldSemesterDetails
											.getProgramStatus());
						}// If Active_status and Status_Pass condition ends
						updateSPOld=sqlMapClient.update(
								"masterTransfer.updateSPForDiffProgram",
								masterTransferBean);
					}

					masterTransferBean
							.setProgramStatus(CRConstant.ACTIVE_STATUS);
					masterTransferBean
							.setModeOfEntry(CRConstant.SWITCH_ADMISSION_MODE);

					masterTransferBean.setSequenceNumber(sequenceNumber + 1);
					int insertSP = sqlMapClient.update(
							"masterTransfer.insertStudentProgram",
							masterTransferBean);
					
					
					// Students record is inserted into STUDENT PROGRAM
					System.out.println("Semester start date"
							+ masterTransferBean.getSemesterStartDate());
					d=studentTrackingFunction.insertStudentTracking(
							masterTransferBean.getNewEntity(),
							masterTransferBean.getRollNumber(),
							masterTransferBean.getProgramCourseKey(),
							masterTransferBean.getSemesterStartDate(),
							masterTransferBean.getSemesterEndDate(),
							masterTransferBean.getSessionStartDate(),
							masterTransferBean.getSessionEndDate(),
							CRConstant.ACTIVE_STATUS,
							CRConstant.ACTIVE_STATUS, masterTransferBean
									.getUserId(), masterTransferBean
									.getActivityId(), masterTransferBean
									.getProcessId());
					
					logger.info("Inserting student program"
							+ masterTransferBean.getEnrollmentNumber()
							+ " and " + masterTransferBean.getOldRollNumber()
							+ ": " + masterTransferBean.getOldBranch() + " : "
							+ masterTransferBean.getOldProgram());
					
					int insertSRSH = sqlMapClient
					.update("masterTransfer.insertIntoSRSH",
							masterTransferBean);
					
					boolean b=studentTrackingFunction.insertStudentTracking(
							masterTransferBean.getNewEntity(),
							masterTransferBean.getRollNumber(),
							masterTransferBean.getProgramCourseKey(),
							masterTransferBean.getSemesterStartDate(),
							masterTransferBean.getSemesterEndDate(),
							masterTransferBean.getSessionStartDate(),
							masterTransferBean.getSessionEndDate(),
							CRConstant.REGISTRATION_STATUS,
							CRConstant.ACTIVE_STATUS, masterTransferBean
									.getUserId(), masterTransferBean
									.getActivityId(), masterTransferBean
									.getProcessId());
					
					List<CourseList> list = masterTransferBean.getCourseList();

					for (CourseList courseList : list) {
						// insert student in student courses
						sqlMapClient.insert(
								"masterTransfer.insertIntoStudentCourse",
								courseList);
						System.out.println("Inserting course");
					}


					if (!masterTransferBean.getOldRollNumber()
							.equalsIgnoreCase(
									masterTransferBean.getRollNumber())) {

						PersonalDetailsBean personalDetailsBean = new PersonalDetailsBean(
								masterTransferBean.getUniveristyId(),
								masterTransferBean.getEnrollmentNumber(),
								masterTransferBean.getUserId());

						// object with personal details of a student
						PersonalDetailsBean personalDetails = getPersonDetails(personalDetailsBean);

						EmailTableBean emailTable = new EmailTableBean(
								masterTransferBean.getStudentId(),
								masterTransferBean.getNewEntity(),
								masterTransferBean.getNewProgram(),
								masterTransferBean.getNewBranch(),
								masterTransferBean.getNewSpecialization(),
								masterTransferBean.getNewSemester(),
								CRConstant.USER_STUDENT_GROUP_ID,
								masterTransferBean.getRollNumber(),
								personalDetails.getPrimaryEmailId(), "UI", true,masterTransferBean.getUserId());
						sqlMapClient.insert("emailTable.insertIntoEMailTable",
								emailTable);
					}

					int updateTSP=sqlMapClient.update(
							"masterTransfer.updateTempStudentProgram",
							masterTransferBean);
					int updateTSC=sqlMapClient.update(
							"masterTransfer.updateTempStudentCourse",
							masterTransferBean);
					
					if((updateTSP==1&&updateTSC==list.size()&&b&&c&&d&&updateSPOld==1)||
							(updateTSP==1&&updateTSC==list.size()&&b&&d&&updateSPOld==1)){
						return "success";
					}else{
						throw new Exception();
					}
					
				} catch (DataAccessException e) {
					status.rollbackToSavepoint(savepoint);
					throw new MyException(e.getMessage());
				//	return "fail"+e.getMessage();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					status.rollbackToSavepoint(savepoint);
					throw new MyException(e.getMessage());
				//	return "fail"+e.getMessage();
				}
			}//

		});
			return value;
	}

	public String transferNEWStudentIntoMaster(
			final MasterTransferBean masterTransferBean,
			final PersonalDetailsBean personalDetailsBean,
			final ErrorLogs errorLogs) {

		String value=(String)transactionTemplate.execute(new TransactionCallback() {
			// Object save=null;

			
			public String doInTransaction(TransactionStatus status) {
				Object savepoint=null;
				try {
					savepoint=status.createSavepoint();
					
					StudentTrackingFunction studentTrackingFunction=new StudentTrackingFunction(sqlMapClient,transactionTemplate);
					
					System.out.println("Master Transfer NEW"
							+ masterTransferBean.getStudentId());
					SystemValue systemValue = new SystemValue(
							masterTransferBean.getUniveristyId(),
							CRConstant.STUDENT_ID_CODE);

					List<PersonalDetailsBean> personExist = new ArrayList<PersonalDetailsBean>();

					personExist = sqlMapClient.queryForList(
							"masterTransfer.enrollmentExist",
							personalDetailsBean);

					PersonalDetailsBean person = personalDetailsBean;

					if (personExist.size() == 0) {
						int studentId = new RegistrationFunction(sqlMapClient,
								transactionTemplate)
								.getIncrementedId(systemValue) + 1;

						// Appending year part in student_id is update by Dheeraj
						
						Calendar cal = Calendar.getInstance();
						
						String generateSystemId = "S"
								+ masterTransferBean.getNewEntity()
								+ String
										.valueOf(cal.get(Calendar.YEAR)%100)
								+ String.format("%07d", studentId);
						
						System.out.println("Dheeraj : " + generateSystemId);

						systemValue.setModifierId(masterTransferBean
								.getUserId());
						systemValue.setValue(String.format("%07d", studentId));

						int updateSystemValue = sqlMapClient.update(
								"systemValue.updateSystemValue", systemValue);

						// Insert stduent into address_master and student_master

						person.setStudentId(generateSystemId);

						masterTransferBean.setStudentId(generateSystemId);

						int insertPersonDetails = sqlMapClient.update(
								"masterTransfer.insertStudentPersonalDetails",
								person);

						System.out.println("Coming inside phone:"
								+ personalDetailsBean.getHomePhone());

						int insertPermanentAddressDetails = sqlMapClient
								.update(
										"masterTransfer.insertStudentAddressDetails",
										new AddressDetailBean(
												CRConstant.USER_STUDENT_GROUP_ID,
												CRConstant.PERMANENT_ADDRESS,
												personalDetailsBean
														.getPermanentAddress(),
												personalDetailsBean
														.getPermanentCity(),
												personalDetailsBean
														.getPermanentState(),
												personalDetailsBean
														.getPermanentPinCode(),
												personalDetailsBean
														.getHomePhone(),
												personalDetailsBean
														.getExtraPhone(),
												personalDetailsBean
														.getOtherPhone(),
												personalDetailsBean.getFax(),
												generateSystemId,
												personalDetailsBean.getUserId()));

						if (personalDetailsBean.getCorrespondentAddress() != null) {
							int insertCorresPondentDetails = sqlMapClient
									.update(
											"masterTransfer.insertStudentAddressDetails",
											new AddressDetailBean(
													CRConstant.USER_STUDENT_GROUP_ID,
													CRConstant.CORRESPONDENT_ADDRESS,
													personalDetailsBean
															.getCorrespondentAddress(),
													personalDetailsBean
															.getCorrespondentCity(),
													personalDetailsBean
															.getCorrespondentState(),
													personalDetailsBean
															.getCorrespondentPinCode(),
													personalDetailsBean
															.getHomePhone(),
													personalDetailsBean
															.getExtraPhone(),
													personalDetailsBean
															.getOtherPhone(),
													personalDetailsBean
															.getFax(),
													generateSystemId,
													personalDetailsBean
															.getUserId()));
						}

					}

					int insertSP = sqlMapClient.update(
							"masterTransfer.insertNewStudentProgram",
							masterTransferBean);
					boolean b=studentTrackingFunction.insertStudentTracking(
							masterTransferBean.getNewEntity(),
							masterTransferBean.getRollNumber(),
							masterTransferBean.getProgramCourseKey(),
							masterTransferBean.getSemesterStartDate(),
							masterTransferBean.getSemesterEndDate(),
							masterTransferBean.getSessionStartDate(),
							masterTransferBean.getSessionEndDate(),
							CRConstant.ACTIVE_STATUS,
							CRConstant.ACTIVE_STATUS, masterTransferBean
									.getUserId(), masterTransferBean
									.getActivityId(), masterTransferBean
									.getProcessId());
					
					int insertSRSH = sqlMapClient
							.update("masterTransfer.insertIntoSRSH",
									masterTransferBean);
					boolean c=studentTrackingFunction.insertStudentTracking(
							masterTransferBean.getNewEntity(),
							masterTransferBean.getRollNumber(),
							masterTransferBean.getProgramCourseKey(),
							masterTransferBean.getSemesterStartDate(),
							masterTransferBean.getSemesterEndDate(),
							masterTransferBean.getSessionStartDate(),
							masterTransferBean.getSessionEndDate(),
							CRConstant.REGISTRATION_STATUS,
							CRConstant.ACTIVE_STATUS, masterTransferBean
									.getUserId(), masterTransferBean
									.getActivityId(), masterTransferBean
									.getProcessId());
					
					List<CourseList> list = masterTransferBean.getCourseList();

					for (CourseList courseList : list) {
						// insert student in student courses
						int insertSC = sqlMapClient.update(
								"masterTransfer.insertIntoStudentCourse",
								courseList);

					}

					// Change Done By Dheeraj For By-Passing The Logic of Enrollment Form
					// Starts
					/**
					EmailTableBean emailTable = new EmailTableBean(
							masterTransferBean.getStudentId(),
							masterTransferBean.getNewEntity(),
							masterTransferBean.getNewProgram(),
							masterTransferBean.getNewBranch(),
							masterTransferBean.getNewSpecialization(),
							masterTransferBean.getNewSemester(),
							CRConstant.USER_STUDENT_GROUP_ID,
							masterTransferBean.getRollNumber(),
							personalDetailsBean.getPrimaryEmailId(), "UI", 
							true,masterTransferBean.getUserId());
					**/
					EmailTableBean emailTable = new EmailTableBean(
							masterTransferBean.getStudentId(),
							masterTransferBean.getNewEntity(),
							masterTransferBean.getNewProgram(),
							masterTransferBean.getNewBranch(),
							masterTransferBean.getNewSpecialization(),
							masterTransferBean.getNewSemester(),
							CRConstant.USER_STUDENT_GROUP_ID,
							masterTransferBean.getRollNumber(),
							personalDetailsBean.getPrimaryEmailId(), "UI", 
							true,masterTransferBean.getUserId());
					// Ends
					sqlMapClient.insert("emailTable.insertIntoEMailTable",
							emailTable);

					int updateTSP = sqlMapClient.update(
							"masterTransfer.updateTempStudentProgram",
							masterTransferBean);
					int updateTSC = sqlMapClient.update(
							"masterTransfer.updateTempStudentCourse",
							masterTransferBean);

					System.out.println(updateTSP +" and "+updateTSC);
					if(updateTSP==1&&updateTSC==list.size()&&b&&c){
						return "success";
					}else{
						throw new Exception();
					}
					
				} catch (DataAccessException e) {
					status.rollbackToSavepoint(savepoint);
					logger.info("Exception for "+masterTransferBean.getRollNumber()+" this "+masterTransferBean.getProgramCourseKey()
							+" dates"+masterTransferBean.getSemesterStartDate()+" is :"+e.getMessage());
					throw new MyException(e.getMessage());
				//	return "fail"+e.getMessage();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					status.rollbackToSavepoint(savepoint);
					logger.info("Exception for "+masterTransferBean.getRollNumber()+" this "+masterTransferBean.getProgramCourseKey()
							+" dates"+masterTransferBean.getSemesterStartDate()+" is :"+e.getMessage());
					throw new MyException(e.getMessage());
				//	return "fail"+e.getMessage();
				}
			}//
		});
		return value;
	}

	public OldSemesterDetails getOldProgramDetails(
			MasterTransferBean masterTransferBean) {
		// TODO Auto-generated method stub
		OldSemesterDetails oldSemesterDetails = null;
		try {
			List<MasterTransferBean> oldDetails = (List<MasterTransferBean>) sqlMapClient
					.queryForList("masterTransfer.getOldProgramDetail",
							masterTransferBean);
			for (MasterTransferBean oldSemesterDetail : oldDetails) {				
				oldSemesterDetails = new OldSemesterDetails(oldSemesterDetail
						.getModeOfEntry(), oldSemesterDetail.getStatus(),
						oldSemesterDetail.getSwitchNumber(), oldSemesterDetail
								.getSequenceNumber(), masterTransferBean
								.getEnrollmentNumber(), oldSemesterDetail
								.getOldRollNumber());
				System.out.println("First Check get old program details ==== " + oldSemesterDetail);
			}

		} catch (Exception e) {
			logger.info("Exception for "+masterTransferBean.getRollNumber()+" while getting previous semester details");
			
		}
		return oldSemesterDetails;
	}

	public ErrorReason validateDataForNORStudent(
			MasterTransferBean masterTransferBean, List<CourseList> courseList) {
		// TODO Auto-generated method stub
		boolean b = true;
		String reasonCode = "DBI";
		String description = "Values in database is incorrect";
		try {
			if (masterTransferBean.getEnrollmentNumber() == null
					|| masterTransferBean.getOldEntity() == null
					|| masterTransferBean.getOldProgram() == null
					|| masterTransferBean.getOldBranch() == null
					|| masterTransferBean.getOldSpecialization() == null
					|| masterTransferBean.getOldSemester() == null
					|| masterTransferBean.getRollNumber() == null
					|| masterTransferBean.getStatusInSemester() == null
					) {
				b = false;
				reasonCode = "NNA";
				description = "Some required information is missing";
			}

			if (courseList.size() == 0) {
				b = false;
				reasonCode = "NCF";
				description = "No course found";
			}

		} catch (Exception e) {
			System.out.println("Some Exception is there");
			b = false;
			reasonCode = "NNA";
			description = "Some required information is missing";
			logger.info("Exception inside validating NOR"
					+ e.getMessage());
		}
		return new ErrorReason(reasonCode, description, b);
	}

	public ErrorReason validateDataForSWTStudent(
			MasterTransferBean masterTransferBean, List<CourseList> courseList,
			OldSemesterDetails oldSemesterDetails) {
		// TODO Auto-generated method stub
		boolean b = true;
		String reasonCode = "DBI";
		String description = "Values in database is incorrect";
		try {
			if (masterTransferBean.getOldProgram().equalsIgnoreCase(
					masterTransferBean.getNewProgram())
					&& masterTransferBean.getOldProgram() != null) {

				if (masterTransferBean.getEnrollmentNumber() == null
						|| masterTransferBean.getOldEntity() == null
						|| masterTransferBean.getOldProgram() == null
						|| masterTransferBean.getOldBranch() == null
						|| masterTransferBean.getOldSpecialization() == null
						|| masterTransferBean.getOldSemester() == null
						|| masterTransferBean.getRollNumber() == null
						|| oldSemesterDetails.getOldRollNumber() == null) {
					b = false;
					reasonCode = "NNA";
					description = "Some required information is missing";
				} else {
					if (masterTransferBean.getEnrollmentNumber() == null
							|| masterTransferBean.getEnrollmentNumber()
									.equalsIgnoreCase(null)) {
						b = false;
						reasonCode = "NNA";
						description = "Enrollment number is missing";
					}
				}
			}

			if (courseList.size() == 0) {
				b = false;
				reasonCode = "NCF";
				description = "No course found";
			}

		} catch (Exception e) {
			b = false;
			reasonCode = "NNA";
			description = "Some required information is missing";
			logger.info("Exception inside validating SWT "
					+ e.getMessage());

		}
		return new ErrorReason(reasonCode, description, b);
	}

	public ErrorReason validateDataForNEWStudent(
			MasterTransferBean masterTransferBean, List<CourseList> courseList,
			PersonalDetailsBean personalDetailsBean) {
		// TODO Auto-generated method stub
		boolean b = true;
		String reasonCode = "DBI";
		String description = "Values in database is incorrect";
		try {
			List<PersonalDetailsBean> personExist = new ArrayList<PersonalDetailsBean>();

			personExist = sqlMapClient.queryForList(
					"masterTransfer.enrollmentExist", personalDetailsBean);
			if (personExist.size() == 0) {
				if (masterTransferBean.getEnrollmentNumber() == null
						|| masterTransferBean.getRollNumber() == null
						|| personalDetailsBean.getStudentFirstName() == null
						|| personalDetailsBean.getFatherFirstName() == null
						|| personalDetailsBean.getDateOfBirth() == null
						|| personalDetailsBean.getCategoryCode() == null
						|| personalDetailsBean.getGender() == null
						) {
					b = false;
					reasonCode = "NNA";
					description = "Some required information is missing";
				}
			}

			if (courseList.size() == 0) {
				b = false;
				reasonCode = "NCF";
				description = "No course found";
			}

		} catch (Exception e) {
			b = false;
			reasonCode = "NNA";
			description = "Some required information is missing";
			logger.info("Exception inside validating NEW "
					+ e.getMessage());
		}
		return new ErrorReason(reasonCode, description, b);
	}

	public PersonalDetailsBean getPersonDetails(
			PersonalDetailsBean personalDetailsBean) {
		PersonalDetailsBean personalDetail = new PersonalDetailsBean();
		List<AddressDetailBean> addressDetail = new ArrayList<AddressDetailBean>();
		try {
			List<PersonalDetailsBean> personalDetails = (List<PersonalDetailsBean>) sqlMapClient
					.queryForList("masterTransfer.getPersonalDetails",
							personalDetailsBean);

			for (PersonalDetailsBean personDetails : personalDetails) {
				personalDetail = personDetails;

			}

		} catch (Exception e) {
			System.out.println("Exception in getting personal details "
					+ e.getMessage());
		}

		return personalDetail;
	}

	public List<CourseList> getCourseList(MasterTransferBean masterTransferBean) {
		List<CourseList> totalCourse = new ArrayList<CourseList>();
		System.out.println("Coming inside getCourseList"
				+ masterTransferBean.getNewProgram()
				+ masterTransferBean.getRegistrationNumber()
				+ masterTransferBean.getEnrollmentNumber()
				+ masterTransferBean.getRollNumber()
				+ masterTransferBean.getNewBranch()
				+ masterTransferBean.getNewSpecialization()
				+ masterTransferBean.getSemesterStartDate()
				+ masterTransferBean.getSemesterEndDate()
				+ masterTransferBean.getNewSemester());
		try {

			List<MasterTransferBean> courses = (List<MasterTransferBean>) sqlMapClient
					.queryForList("masterTransfer.getListOfCourse",
							masterTransferBean);
			System.out.println("course:" + courses.size());
			
			// Course Name In CourseList Constructor was Added By Dheeraj on 03-03-2012 For Transferring Course Name IN student_course
			
			for (MasterTransferBean course : courses) {
				totalCourse.add(new CourseList(masterTransferBean
						.getRollNumber(), masterTransferBean
						.getProgramCourseKey(), masterTransferBean
						.getSemesterStartDate(), masterTransferBean
						.getSemesterEndDate(), course.getCourseCode(), course
						.getCourseStatus(), masterTransferBean.getUserId(),
						course.getCourseGroup(),masterTransferBean.getNewEntity(),
						course.getCourseName()));

				System.out.println("inside courses: "
						+ masterTransferBean.getRollNumber()
						+ masterTransferBean.getProgramCourseKey()
						+ masterTransferBean.getSemesterStartDate()
						+ masterTransferBean.getSemesterEndDate()
						+ course.getCourseCode() + course.getCourseStatus()
						+ masterTransferBean.getUserId()+course.getCourseGroup()
						+ masterTransferBean.getNewEntity()
						+ course.getCourseName());

			}
		} catch (Exception e) {
			System.out.println("Exception in course list" + e);
		}
		return totalCourse;
	}

	public List<CourseList> getCourseListForOldStudent(
			MasterTransferBean masterTransferBean) {
		List<CourseList> totalCourse = new ArrayList<CourseList>();

		try {

			List<MasterTransferBean> courses = (List<MasterTransferBean>) sqlMapClient
					.queryForList(
							"masterTransfer.getListOfCourseForOldStudent",
							masterTransferBean);
			System.out.println("course:" + courses.size());
			
			// Course Name In CourseList Constructor was Added By Dheeraj on 03-03-2012 For Transferring Course Name IN student_course
			
			for (MasterTransferBean course : courses) {
				totalCourse.add(new CourseList(masterTransferBean
						.getRollNumber(), masterTransferBean
						.getProgramCourseKey(), masterTransferBean
						.getSemesterStartDate(), masterTransferBean
						.getSemesterEndDate(), course.getCourseCode(), course
						.getCourseStatus(), masterTransferBean.getUserId(),course.getCourseGroup(),masterTransferBean
						.getNewEntity(),course.getCourseName()));

				System.out.println("Getting List of courses: "
						+ masterTransferBean.getRollNumber()
						+ masterTransferBean.getProgramCourseKey()
						+ masterTransferBean.getSemesterStartDate()
						+ masterTransferBean.getSemesterEndDate()
						+ course.getCourseCode() + course.getCourseStatus()
						+ masterTransferBean.getUserId()
						+" DHEERAJ " +masterTransferBean.getNewEntity()
						+ course.getCourseName());

			}
		} catch (Exception e) {
			System.out
					.println("Exception in get list for course for old student"
							+ e);
		}
		return totalCourse;
	}

}
