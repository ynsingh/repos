package in.ac.dei.edrp.cms.daoimpl.registration.prestagingdaoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.spi.RegisterableService;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.daoimpl.activitymaster.StartActivityDaoImpl;
import in.ac.dei.edrp.cms.daoimpl.resultprocessing.ResultProcessingUtility;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ActivityMasterBean;

import in.ac.dei.edrp.cms.domain.registration.prestaging.ProcessList;
import in.ac.dei.edrp.cms.domain.registration.prestaging.TransferNORInPSTBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessForResultBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;
import in.ac.dei.edrp.cms.domain.utility.ErrorLogs;
import in.ac.dei.edrp.cms.domain.utility.StudentTracking;
import in.ac.dei.edrp.cms.utility.PreviousSemesterDetail;
import in.ac.dei.edrp.cms.utility.RegistrationFunction;
import in.ac.dei.edrp.cms.utility.StudentTrackingFunction;

/**
 * This class transfers student in PreStaging with appropriate semester. It
 * transfers student for 3 cases: PASS, FAIL, REMEDIAL PAS: Moves to next
 * semester FAL: Moves to semester code (minimum sequence number of UIP.semester
 * group) REM: Moves to next semester with probable entries. If there is not
 * attempt number and student is fail, then registration semester will be
 * undefined.
 * 
 * @author Deepak
 * 
 */
public class TransfertNORInPSTFunction {

	static final Logger logger = Logger
			.getLogger(TransfertNORInPSTFunction.class);

	protected SqlMapClient sqlMapClient = null;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	TransactionTemplate transactionTemplate = null;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public TransfertNORInPSTFunction(SqlMapClient sqlMapClient,
			TransactionTemplate transactionTemplate) {
		super();
		this.sqlMapClient = sqlMapClient;
		this.transactionTemplate = transactionTemplate;
	}

	public TransfertNORInPSTFunction() {
		super();

	}

	/**
	 * @param activityMasterBean
	 *            returns true if all records processed successfully else false
	 */
	public CountProcessRecorList transferNorinPST(
			ActivityMasterBean activityMasterBean) {
		// TODO Auto-generated method stub
		List<UnProcessedStduent> successStudentList=new ArrayList<UnProcessedStduent>();
		successStudentList.add(new UnProcessedStduent("Enrolment number|rollnumber",
				"Program Id|Semester Start Date|Semester End Date|Register Due Date|Attempt Number","Transferred into PreStaging"));
		List<UnProcessedStduent> failStudentList=new ArrayList<UnProcessedStduent>();
		//failStudentList.add(new UnProcessedStduent("Enrolment number|rollnumber",
				//"Program Id|Semester Start Date|Semester End Date|Register Due Date|Attempt Number|Admission Mode","Transferred into PreStaging"));
		//Use of studentList: It has all the list of students with its status 
		//i.e. it is being processed successfully or unsuccessfully
		List<List<UnProcessedStduent>> studentList=new ArrayList<List<UnProcessedStduent>>();
		
		RegistrationFunction registrationFunction = new RegistrationFunction(
				sqlMapClient, transactionTemplate);
		
		PreStagingTransactionFunction preStagingTransactionFunction = new PreStagingTransactionFunction(
				sqlMapClient, transactionTemplate);

		//This object will have following values:
		//Total Records,Processed Records,Records in error, Rejected value, process status, 
		//and list of processed students with details
		CountProcessRecorList countList = new CountProcessRecorList();

		//ErrorLogs: This object will be used to insert value in student error logs table, if there is any error found
		ErrorLogs errorLogs = new ErrorLogs(activityMasterBean.getEntityId(),
				activityMasterBean.getProgramId(), activityMasterBean
						.getBranchId(), activityMasterBean
						.getSpecializationId(), activityMasterBean
						.getSemesterCode(), activityMasterBean
						.getSemesterStartDate(), activityMasterBean
						.getSemesterEndDate(), activityMasterBean
						.getProcessId(), activityMasterBean.getActivityId());

		//actualRecords: It will contain total number of records which will be processed
		int actualRecords = 0;
		//processedStudent: Correctly processed student
		int processedStudent = 0;
		//recordsFailed: number of records which could not be processed
		int recordsFailed = 0;
		//rejectedValue: number of records which has some value missing at some field/column in database 
		//which will be used for further calculation.
		int rejectedValue = 0;
		//processFlag: If actualRecords==processedStudent, this flag will be true and process is considered as completed.
		boolean processedFlag = false;
		//studentProgramList: Has the list of student to process with required details
		List<TransferNORInPSTBean> studentProgramList = new ArrayList<TransferNORInPSTBean>();

		try {
			
			String universityId = activityMasterBean.getUniversityId();
			String entityId = activityMasterBean.getEntityId();
			String programId = activityMasterBean.getProgramId();
			String branchId = activityMasterBean.getBranchId();
			String specializationId = activityMasterBean.getSpecializationId();
			String semesterCode = activityMasterBean.getSemesterCode();
			String semesterStartDate = activityMasterBean
					.getSemesterStartDate();
			String semesterEndDate = activityMasterBean.getSemesterEndDate();
			String status = activityMasterBean.getStatus();
			String sessionStartDate = activityMasterBean.getSessionStartDate();
			String sessionEndDate = activityMasterBean.getSessionEndDate();
			String activityId = activityMasterBean.getActivityId();
			String creatorId = activityMasterBean.getUserId();

			TransferNORInPSTBean userData = new TransferNORInPSTBean(entityId,
					programId, branchId, specializationId, semesterCode,
					semesterStartDate, semesterEndDate);

			// pick up previous semester code and group
			List<TransferNORInPSTBean> registerSemesterList = (List<TransferNORInPSTBean>) sqlMapClient
					.queryForList("TransferNORInPSTBean.getNextSemester",
							userData);
			// System.out.println("Coming after
			// query:"+registerSemesterList.size());

			String registerSemester = "";
			String registerStartDay = "";
			String registerEndDay = "";
			boolean checkGroup = false;

			for (TransferNORInPSTBean registerSemesterCode : registerSemesterList) {
				registerSemester = registerSemesterCode.getNextSemesterCode();
				checkGroup = registerSemesterCode.getCheckSemesterGroup();
				registerStartDay = registerSemesterCode.getSemesterStartDate();
				registerEndDay = registerSemesterCode.getSemesterEndDate();

				// System.out.println(checkGroup+"="+registerStartDay+"="+registerEndDay);
			}

			// Pick up program course key for User input
			TransferNORInPSTBean userkeyData = new TransferNORInPSTBean(
					programId, branchId, specializationId, semesterCode);
			String programCourseKey = registrationFunction
					.getProgramCourseKey(userkeyData);
			// Pick up program course key for registration semester if student is PASS
			TransferNORInPSTBean registerKeyData = new TransferNORInPSTBean(
					programId, branchId, specializationId, registerSemester);
			String registerProgramCourseKey = registrationFunction
					.getProgramCourseKey(registerKeyData);
			String registerSemesterStartDate = "";
			String registerSemesterEndDate = "";

			// For getting registration start and end date for PAS and remedial
			// stduent
			// It gets date according to active session and registration program
			// course key
			TransferNORInPSTBean datesData = new TransferNORInPSTBean(
					registerProgramCourseKey, activityMasterBean
							.getSessionStartDate(), activityMasterBean
							.getSessionEndDate());
			List<TransferNORInPSTBean> startEndDate = sqlMapClient.queryForList("TransferNORInPSTBean.getYTRDate", datesData);
			for (TransferNORInPSTBean registerdates : startEndDate) {
				registerSemesterStartDate = registerdates.getSemesterStartDate();
				registerSemesterEndDate = registerdates.getSemesterEndDate();
				// System.out.println(registerSemesterEndDate+registerSemesterStartDate);
			}

			// Register due date for registration semester
			TransferNORInPSTBean registerDueDateData = new TransferNORInPSTBean(
					registerProgramCourseKey, registerSemesterStartDate,
					registerSemesterEndDate);
			String registerDueDate = registrationFunction
					.getRegisterDueDate(registerDueDateData);

			// Get semester code for minimum semester sequence of user semester group
			TransferNORInPSTBean minSemesterData = new TransferNORInPSTBean(
					programId, semesterCode);
			String minRegisterSemesterCode = registrationFunction
					.getMinimumSemester(minSemesterData);
			
			// pick up program course key for minimum semester
			TransferNORInPSTBean minimumCourseKeyData = new TransferNORInPSTBean(
					programId, branchId, specializationId,
					minRegisterSemesterCode);
			String minProgramCourseKey = registrationFunction
					.getProgramCourseKey(minimumCourseKeyData);
			
			// pick up max attempt number for program
			int maxAttemptAllowed = registrationFunction
					.maximumAttempNumberAllowed(new TransferNORInPSTBean(
							programId));
			
			//studentProgramData: This object will be used for picking up list of students to process.
			TransferNORInPSTBean studentProgramData = new TransferNORInPSTBean(
					entityId, programId, branchId, specializationId,
					semesterCode, semesterStartDate, semesterEndDate,
					programCourseKey, CRConstant.ACTIVE_STATUS,
					CRConstant.LOAD_REGULAR_STUDENT);

			// pick up list of students from student program
			PreProcessForResultBean preProcessForResultBean=new PreProcessForResultBean();
			preProcessForResultBean.setProgramId(programId);
			preProcessForResultBean.setSemesterCode(semesterCode);
			
			if(new ResultProcessingUtility(sqlMapClient,transactionTemplate).isFinalSemester(preProcessForResultBean)){
			studentProgramList = (List<TransferNORInPSTBean>) sqlMapClient.queryForList("TransferNORInPSTBean.getListOfStudentToProcessFinalSemester",studentProgramData);
			}else{
			studentProgramList = (List<TransferNORInPSTBean>) sqlMapClient.queryForList("TransferNORInPSTBean.getListOfStudentToProcess",studentProgramData);
			}
			for (TransferNORInPSTBean studentProgram : studentProgramList) {
				String processStatus="";
				try {

					String rollNumber = studentProgram.getRollNumber();
					int attemptNumber = studentProgram.getAttemptNumber();
				
					String registrationProgramCourseKey = registerProgramCourseKey;
					String registrationSemStartDate = registerSemesterStartDate;
					String registrationSemEndDate = registerSemesterEndDate;
					String registrationDueDate = registerDueDate;
					String registrationSemester = registerSemester;

					errorLogs.setEnrollmentNumber(studentProgram
							.getEnrollmentNumber());
					errorLogs.setStudentId(studentProgram.getStudentId());
					errorLogs.setStudentName(new StudentTrackingFunction()
							.getFullName(studentProgram.getStudentFirstName(),
									studentProgram.getStudentMiddleName(),
									studentProgram.getStudentLastName()));

					// If status of student is PAS or FAL, this code of block
					// executes.
					// In case of pass, if group are same then atte
					if (studentProgram.getStatusInSemester().equalsIgnoreCase(
							CRConstant.STATUS_PASS)
							|| studentProgram.getStatusInSemester()
									.equalsIgnoreCase(CRConstant.STATUS_FAIL)) {

						if (studentProgram.getStatusInSemester()
								.equalsIgnoreCase(CRConstant.STATUS_PASS)) {

							if (!checkGroup) {
								attemptNumber = 1;
								// System.out.println("Attempt Number
								// "+attemptNumber+" for "+rollNumber);
							}// If checkgroup ends
							// System.out.println(registerSemesterStartDate+"before
							// setting register due date");
							// System.out.println("register due date for pass:
							// "+registerDueDate);
						}// If PAS closed

						if (studentProgram.getStatusInSemester()
								.equalsIgnoreCase(CRConstant.STATUS_FAIL)) {
							// System.out.println(maxAttemptAllowed+" and
							// "+attemptNumber);

							if (attemptNumber < maxAttemptAllowed) {

								attemptNumber = attemptNumber + 1;

								PreviousSemesterDetail minimumSemesterData = new PreviousSemesterDetail(
										rollNumber, entityId,
										minProgramCourseKey);
								List<PreviousSemesterDetail> minimumSemesterResult = registrationFunction
										.getPreviousSessionDate(minimumSemesterData);
								for (PreviousSemesterDetail minSemesterdetail : minimumSemesterResult) {
									// System.out.println("coming inside ths ");
									registrationSemStartDate = minSemesterdetail
											.getPreviousSemesterStartDate();
									registrationSemEndDate = minSemesterdetail
											.getPreviousSemesterEndDate();

								}// Previous detail loop ends

								registrationSemester = minRegisterSemesterCode;

								TransferNORInPSTBean registrationDateForFail = new TransferNORInPSTBean(
										minProgramCourseKey,
										registrationSemStartDate,
										registrationSemEndDate);
								// System.out.println("register due date for
								// fail:"+registrationFunction.getRegisterDueDate(registrationDateForFail));
								
								// Commented out by Arush.No need to increment year  after YTR record has been inserted in SRSH 
//								registrationDueDate = registrationFunction
//										.getNextYear(registrationFunction
//												.getRegisterDueDate(registrationDateForFail));
//
								registrationDueDate = registrationFunction.getRegisterDueDate(registrationDateForFail) ;
								
								//								registrationSemStartDate = registrationFunction
//										.getNextYear(registrationSemStartDate);
//
//								registrationSemEndDate = registrationFunction
//										.getNextYear(registrationSemEndDate);

								// System.out.println("register due date for
								// fail:"+registrationDueDate);

								registrationProgramCourseKey = minProgramCourseKey;
							}// if attempt number ends
							else {
								attemptNumber = 00;
								registrationSemester = "NA";
								registrationDueDate = "1111-11-11";
								registrationSemStartDate = "1111-11-11";
								registrationSemEndDate = "1111-11-11";
							}// else attempt number ends
						}// if Fail ends
						TransferNORInPSTBean passOrFailPSTData = new TransferNORInPSTBean(
								entityId, programId, branchId,
								specializationId, semesterCode,
								semesterStartDate, semesterEndDate,
								sessionStartDate, sessionEndDate,
								programCourseKey,
								studentProgram.getStudentId(), studentProgram
										.getEnrollmentNumber(), studentProgram
										.getRollNumber(), studentProgram
										.getStudentFirstName(), studentProgram
										.getStudentMiddleName(), studentProgram
										.getStudentLastName(), studentProgram
										.getFatherFirstName(), studentProgram
										.getFatherMiddleName(), studentProgram
										.getFatherLastName(), studentProgram
										.getMotherFirstName(), studentProgram
										.getMotherMiddleName(), studentProgram
										.getMotherLastName(), studentProgram
										.getDateOfBirth(), studentProgram
										.getCategory(), studentProgram
										.getGender(), studentProgram
										.getPrimaryEmailId(), studentProgram
										.getStatusInSemester(), attemptNumber,
								registrationProgramCourseKey,
								registrationSemester, registrationSemStartDate,
								registrationSemEndDate, registrationDueDate,
								checkGroup, maxAttemptAllowed,
								CRConstant.NORMAL_MODE,
								CRConstant.YET_TO_REGISTER,
								CRConstant.LOAD_REGULAR_STUDENT, creatorId,
								activityId);
						// System.out.println("Coming till here");
						processStatus=preStagingTransactionFunction
								.executeQueryForPreStaging(passOrFailPSTData);
						
					}// IF PAS for FAL ends

					// If status of student is REM, then probable entries will
					// be calculated
					if (studentProgram.getStatusInSemester().equalsIgnoreCase(
							CRConstant.STATUS_REMEDIAL) || studentProgram.getStatusInSemester().equalsIgnoreCase(
									CRConstant.REGISTRATION_STATUS)) {
						String probaleSemester = minRegisterSemesterCode;
						String probableSemesterStartDate = "";
						String probableSemesterEndDate = "";
						int probableAttemptNumber = attemptNumber;
						String probableRegisterDueDate = "";

						if (!checkGroup) {
							attemptNumber = 1;
						}

						if (attemptNumber < maxAttemptAllowed) {
							probableAttemptNumber = attemptNumber + 1;
							// System.out.println("Attempt number for fail: ");

							PreviousSemesterDetail minimumSemesterData = new PreviousSemesterDetail(
									rollNumber, entityId, minProgramCourseKey);
							List<PreviousSemesterDetail> minimumSemesterResult = registrationFunction
									.getPreviousSessionDate(minimumSemesterData);
							for (PreviousSemesterDetail minSemesterdetail : minimumSemesterResult) {
								// System.out.println("coming inside ths ");
								probableSemesterStartDate = minSemesterdetail
										.getPreviousSemesterStartDate();
								probableSemesterEndDate = minSemesterdetail
										.getPreviousSemesterEndDate();
								// System.out.println(registrationSemEndDate+"
								// abc "+registrationSemStartDate);
							}// Previous detail loop ends REM

							TransferNORInPSTBean registrationDateForFail = new TransferNORInPSTBean(
									minProgramCourseKey,
									probableSemesterStartDate,
									probableSemesterEndDate);
							// System.out.println("register due date for
							// REM:"+registrationFunction.getRegisterDueDate(registrationDateForFail));
							probableRegisterDueDate = registrationFunction
									.getNextYear(registrationFunction
											.getRegisterDueDate(registrationDateForFail));

							probableSemesterStartDate = registrationFunction
									.getNextYear(probableSemesterStartDate);

							probableSemesterEndDate = registrationFunction
									.getNextYear(probableSemesterEndDate);
							
							//*Arush Changes start here  to get register Semester date  if next semester falls in next session
							if (!checkGroup){
								
							registrationSemStartDate = registrationFunction
							.getNextYear(registrationSemStartDate);

							registrationSemEndDate = registrationFunction
							.getNextYear(registrationSemEndDate);
							
							registrationDueDate = registrationSemStartDate ;
							}
							
							//* Arush Changes end here 
							// System.out.println("register due date for
							// fail:"+registrationDueDate);

						}// if attempt number ends REM
						else {
							probableAttemptNumber = 00;
							probaleSemester = "NA";
							probableRegisterDueDate = "1111-11-11";
							probableSemesterStartDate = "1111-11-11";
							probableSemesterEndDate = "1111-11-11";
						}// else attempt number endsfor REM

						TransferNORInPSTBean remedialPSTData = new TransferNORInPSTBean(
								entityId, programId, branchId,
								specializationId, semesterCode,
								semesterStartDate, semesterEndDate,
								sessionStartDate, sessionEndDate,
								programCourseKey,
								studentProgram.getStudentId(), studentProgram
										.getEnrollmentNumber(), studentProgram
										.getRollNumber(), studentProgram
										.getStudentFirstName(), studentProgram
										.getStudentMiddleName(), studentProgram
										.getStudentLastName(), studentProgram
										.getFatherFirstName(), studentProgram
										.getFatherMiddleName(), studentProgram
										.getFatherLastName(), studentProgram
										.getMotherFirstName(), studentProgram
										.getMotherMiddleName(), studentProgram
										.getMotherLastName(), studentProgram
										.getDateOfBirth(), studentProgram
										.getCategory(), studentProgram
										.getGender(), studentProgram
										.getPrimaryEmailId(), studentProgram
										.getStatusInSemester(), attemptNumber,
								registrationProgramCourseKey,
								registrationSemester, registrationSemStartDate,
								registrationSemEndDate, registrationDueDate,
								checkGroup, maxAttemptAllowed,
								CRConstant.NORMAL_MODE,
								CRConstant.YET_TO_REGISTER,
								CRConstant.LOAD_REGULAR_STUDENT,
								probaleSemester, probableRegisterDueDate,
								probableSemesterStartDate,
								probableSemesterEndDate, probableAttemptNumber,
								creatorId, activityId);
						processStatus=preStagingTransactionFunction
								.executeQueryForRemedialPreStaging(remedialPSTData);
										

					}// REM if ends
					
					if(!(studentProgram.getStatusInSemester().equalsIgnoreCase(
							CRConstant.STATUS_REMEDIAL) || studentProgram.getStatusInSemester().equalsIgnoreCase(
									CRConstant.REGISTRATION_STATUS)||
									studentProgram.getStatusInSemester().equalsIgnoreCase(CRConstant.STATUS_PASS)||
											studentProgram.getStatusInSemester().equalsIgnoreCase(CRConstant.STATUS_FAIL))){
						processStatus="fail";
					}
					
					
				} catch (Exception listException) {
					processStatus="fail";					
				}
				
				if(processStatus.equalsIgnoreCase("success")){
					processedStudent++;
					successStudentList.add(new UnProcessedStduent(studentProgram
							.getEnrollmentNumber()+"|"+ studentProgram
							.getRollNumber(),studentProgram.getProgramCourseKey()+"|"+registerProgramCourseKey+
							"|"+registerSemesterStartDate+"|"+
							registerSemesterEndDate+"|"+ registerDueDate+"|"+
							studentProgram.getAttemptNumber(),""));
				}else{
					recordsFailed++;
					errorLogs.setReasonCode("DBI");
					errorLogs.setDescription("Unknown issue");
					errorLogs.setProcessFlag(CRConstant.ERROR_STATUS);
					sqlMapClient.insert(
							"studentTrackingAndLogs.insertStudentErrorLog",
							errorLogs);
					failStudentList.add(new UnProcessedStduent(studentProgram
							.getRollNumber(),studentProgram.getProgramCourseKey()+
							"|"+registerProgramCourseKey+"|"+ registerSemesterStartDate+"|"+
							registerSemesterEndDate+"|"+ registerDueDate+"-"+
							 studentProgram.getAttemptNumber(),processStatus));
				}
			}// Loop ends

			// System.out.println("Coming till last");

		} catch (Exception e) {
			System.out.println("Exception in normal process" + e);
			
			rejectedValue = actualRecords - (processedStudent + recordsFailed);
			
			failStudentList.add(new UnProcessedStduent("Process is incomplete","There must be some database issue.A field that is required, has value NULL",
					"Error:"+e.getMessage()));
			logger.info("Exception in transfer to PST" + e.getMessage());
		} finally {

			actualRecords = studentProgramList.size();

			studentList.add(successStudentList);
			studentList.add(failStudentList);
			
			if (actualRecords == processedStudent && actualRecords != 0) {
				processedFlag = true;
			}
			
			countList = new CountProcessRecorList(actualRecords,
					processedStudent, rejectedValue, recordsFailed,
					processedFlag,studentList);
			
		}
		countList.setRejStudentList(failStudentList);
		return countList;
	}

}
