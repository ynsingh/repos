package in.ac.dei.edrp.cms.daoimpl.resultprocessing;

import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramMasterInfoGetter;
import in.ac.dei.edrp.cms.domain.registration.prestaging.TransferNORInPSTBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessCourseList;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessForResultBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreprocessTwoInfoGetter;
import in.ac.dei.edrp.cms.domain.resultprocessing.RemedialCourseBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;
import in.ac.dei.edrp.cms.utility.PreviousSemesterDetail;
import in.ac.dei.edrp.cms.utility.RegistrationFunction;
import in.ac.dei.edrp.cms.utility.StudentTrackingFunction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class RemedialProcessingImpl {
	public static final Logger logger = Logger
			.getLogger(PreProcessForResult.class);

	private SqlMapClient sqlMapClient;
	private TransactionTemplate transactionTemplate;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public RemedialProcessingImpl(SqlMapClient sqlMapClient,
			TransactionTemplate transactionTemplate) {
		this.sqlMapClient = sqlMapClient;
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * This method get list of student and done remedial processing
	 * 
	 * @param startActivityBean
	 * @return countList,Object of CountProcessRecorList containing details of
	 *         no of record processed,rejected etc.
	 */
	@SuppressWarnings("unchecked")
	public CountProcessRecorList startRemedialProcess(
			final StartActivityBean startActivityBean) {
		int totalRecords = 0;
		int processedRecords = 0;
		int errorRecords = 0;
		Boolean isCompleted = true;

		final List<UnProcessedStduent> errorList = new ArrayList<UnProcessedStduent>();

		final ResultProcessingUtility resultProcessingUtility = new ResultProcessingUtility(
				sqlMapClient, transactionTemplate);
		final InsertYTR insertYTR = new InsertYTR(sqlMapClient,
				transactionTemplate);
		final StudentTrackingFunction studentTrackingFunction = new StudentTrackingFunction(
				sqlMapClient, transactionTemplate);

		ProgramMasterInfoGetter programMaster = new ProgramMasterInfoGetter();
		programMaster.setProgramId(startActivityBean.getProgramId());
		ProgramMasterInfoGetter maxAttemptDetails = resultProcessingUtility
				.getMaxAttmptAllowed(programMaster);

		PreProcessForResultBean commonInput = new PreProcessForResultBean();
		commonInput
				.setProgramCourseKey(startActivityBean.getProgramCourseKey());
		commonInput.setUniversityId(startActivityBean.getUniversityId());
		commonInput.setProgramId(startActivityBean.getProgramId());
		commonInput.setSemesterCode(startActivityBean.getSemesterCode());
		commonInput.setEntityId(startActivityBean.getEntityId());
		commonInput.setBranchId(startActivityBean.getBranchId());
		commonInput
				.setSpecializationId(startActivityBean.getSpecializationId());

		resultProcessingUtility.getResultSystem(commonInput);
		final Boolean isFinalSem = resultProcessingUtility
				.isFinalSemester(commonInput);
		final Integer minPassGrade = Integer.parseInt(resultProcessingUtility
				.getMinPassMarks(commonInput, "MINGRD"));
		final Integer maxAttemptAllowed = Integer.parseInt(maxAttemptDetails
				.getNumberOfAttemptAllowed());
		Integer.parseInt(maxAttemptDetails.getMaxNumberOfFailSubjects());
		Integer.parseInt(maxAttemptDetails.getNumberOfTerms());
		try {
			commonInput.setSemesterStatus("REM");
			final List<PreProcessForResultBean> studentList = sqlMapClient
					.queryForList("remedialProcess.getRemStudentList",
							commonInput);
			totalRecords = studentList.size();
			System.out.println(studentList.size() + " student get");
			
			//  Variables defined by Arush 

			final PreProcessCourseList statusInput = new PreProcessCourseList();
			final PreProcessForResultBean inputBean = new PreProcessForResultBean();
			
			//final PreProcessForResultBean wrkcgpa = new PreProcessForResultBean();
			// Arush changes end here 
			
			
			for (int i = 0; i < studentList.size(); i++) {
				final String rollNumber = studentList.get(i).getRollNumber();
				final int attemptNumber = studentList.get(i).getAttemptNumber();
				final String enrollmentNumber = studentList.get(i)
						.getEnrollmentNumber();

				System.out.println("Processing Student with roll number= "
						+ rollNumber);
				Boolean status = (Boolean) transactionTemplate
						.execute(new TransactionCallback() {
							public Object doInTransaction(
									TransactionStatus tStatus) {
								Boolean result = true;
								Object savepoint = new Object();
								try {
									savepoint = tStatus.createSavepoint();

									List<String> finalFailCourseList = new ArrayList<String>();
									List<PreProcessCourseList> semesterList = sqlMapClient
											.queryForList(
													"remedialProcess.getSemesterList",
													startActivityBean);

									PreProcessForResultBean wrkcgpa =null ;
									for (int j = 0; j < semesterList.size(); j++) {
										List<String> failCourseList = new ArrayList<String>();
										String semesterCode = semesterList.get(
												j).getSemesterCode();
										String semesterStartDate = semesterList
												.get(j).getSemesterStartDate();
										String semesterEndDate = semesterList
												.get(j).getSemesterEndDate();

										System.out
												.println("Processing Student with roll number= "
														+ rollNumber
														+ " for semester code= "
														+ semesterList
																.get(j)
																.getSemesterCode());
									
										inputBean
												.setUniversityId(startActivityBean
														.getUniversityId());
										inputBean
												.setActivityId(startActivityBean
														.getActivityId());
										inputBean
												.setProcessId(startActivityBean
														.getProcessId());
										inputBean.setEntityId(startActivityBean
												.getEntityId());
										inputBean
												.setProgramId(startActivityBean
														.getProgramId());
										inputBean.setBranchId(startActivityBean
												.getBranchId());
										inputBean
												.setSpecializationId(startActivityBean
														.getSpecializationId());
										inputBean.setSemesterCode(semesterCode);
										inputBean
												.setSemesterStartDate(semesterStartDate);
										inputBean
												.setSemesterEndDate(semesterEndDate);
										inputBean
												.setSessionStartDate(startActivityBean
														.getSessionStartDate());
										inputBean
												.setSessionEndDate(startActivityBean
														.getSessionEndDate());
										inputBean.setMinPassGrade(new Double(
												minPassGrade));
										inputBean.setUserId(startActivityBean
												.getUserId());
										inputBean
												.setProgramStatus(CRConstant.ACTIVE_STATUS);
										inputBean
												.setSemesterStatus(CRConstant.STATUS_REMEDIAL);
										inputBean.setRollNumber(rollNumber);
										
										
										
										System.out.println(inputBean
												.getRollNumber());

										TransferNORInPSTBean pckObj = (TransferNORInPSTBean) sqlMapClient
												.queryForObject(
														"TransferNORInPSTBean.getProgramCourseKey",
														inputBean);

										String programCourseKey = pckObj
												.getProgramCourseKey();
										System.out.println(programCourseKey);
										inputBean
												.setProgramCourseKey(programCourseKey);

										PreProcessForResultBean student = (PreProcessForResultBean) sqlMapClient
												.queryForObject(
														"remedialProcess.getStudentDetails",
														inputBean);
										if (student != null) {
											final Integer attemptNumber = student
													.getAttemptNumber();

											final double registeredTheoryCreditExcludingAudit = student
													.getRegisteredTheoryCreditExcludingAudit();

											final double registeredPracticalCreditExcludingAudit = student
													.getRegisteredPracticalCreditExcludingAudit();

											System.out.println(student
													.getRollNumber()
													+ "\n"
													+ student
															.getAttemptNumber()
													+ "\n"
													+ student
															.getRegisteredCredit()
													+ "\n"
													+ student
															.getRegisteredTheoryCreditExcludingAudit()
													+ "\n"
													+ student
															.getRegisteredPracticalCreditExcludingAudit()
													+ "\n"
													+ student
															.getSemesterStatus());

											double theoryEarnedCredit = 0.0;
											double practicalEarnedCredit = 0.0;
											double sgpaTheoryPoint = 0.0;
											double sgpaPracticalPoint = 0.0;
											
											double earnedTheoryAudCredit = 0.0;
											double earnedPracticalAudCredit = 0.0;
											double earnedCreditsInSemester = 0.0;
											double sgpa = 0.0;
											
											double cgpaTheoryPoint = 0.0;
											double cgpaPracticalPoint = 0.0;
											double cgpaEarnedTheroyCredit = 0.0;
											double cgpaEarnedPracticalCredit = 0.0;
											
											if (student.getSemesterStatus().equalsIgnoreCase("REM")) {
											
											sqlMapClient
													.update("preprocess.updateStudentMarksSummary",
															inputBean);
											sqlMapClient
													.update("preprocess.updateStudentMarksSummaryForInternal",
															inputBean);
											sqlMapClient
													.update("preprocess.updateStudentMarksSummaryGradePoint",
															inputBean);
											sqlMapClient
													.update("preprocess.updateStudentMarksSummaryGradePointForInternal",
															inputBean);

											System.out.println("Program="
													+ inputBean.getProgramId()
													+ "\nbranch="
													+ inputBean.getBranchId()
													+ "\nspec="
													+ inputBean
															.getSpecializationId()
													+ "\nsem="
													+ inputBean
															.getSemesterCode()
													+ "\nsemStarDate="
													+ inputBean
															.getSemesterStartDate()
													+ "\nsemEndDate="
													+ inputBean
															.getSemesterEndDate()
													+ "\nRollNumber="
													+ inputBean.getRollNumber());
											}
											
											List<RemedialCourseBean> courseList = sqlMapClient
													.queryForList(
															"remedialProcess.getStudentCourseList",
															inputBean);
											System.out
													.println("here after get course list");

											for (int c = 0; c < courseList
													.size(); c++) {
												inputBean
														.setCourseCode(courseList
																.get(c)
																.getCourseCode());
												String courseStatus = courseList
														.get(c)
														.getStudentStatus();

												
												
												if (courseStatus.equals("REM"))
																				
												
														 {
													sqlMapClient
															.update("remedialProcess.updateStudentMarksSummaryForRemedial",
																	inputBean);
													sqlMapClient
															.update("remedialProcess.updateStudentMarksSummaryGradePointForRemedial",
																	inputBean);
												}
											}

											if (student.getSemesterStatus().equalsIgnoreCase("REM")) {
											sqlMapClient
													.update("preprocess.updateEarnedCredits",
															inputBean);
											sqlMapClient
													.update("preprocess.updateEarnedCreditsForAudit",
															inputBean);

											}
											
											courseList = sqlMapClient
													.queryForList(
															"remedialProcess.getStudentCourseList",
															inputBean);
											

											for (int k = 0; k < courseList
													.size(); k++) {
												String courseStatus =courseList.get(k).getStudentStatus();
												
												
												
												inputBean
														.setCourseCode(courseList
																.get(k)
																.getCourseCode());

												String studentStatus = "FAL";

												char courseClassification = resultProcessingUtility
														.getClassification(courseList
																.get(k)
																.getCourseClassification());

												char courseType = resultProcessingUtility
														.isAuditCourse(courseList
																.get(k)
																.getCourseType());

												earnedCreditsInSemester = earnedCreditsInSemester
														+ (courseList
																.get(k)
																.getEarnedCredits());
												
												

												double wrkcredit = courseList.get(k).getCredits();
												double wrkearnedCredit = courseList.get(k).getEarnedCredits();
												
												switch (courseType) {
												case 'M': {
													switch (courseClassification) {
													case 'T': {
														
												
									
														

												if (wrkcredit == wrkearnedCredit){
															studentStatus = "PAS";
															cgpaTheoryPoint += (courseList
																			.get(k)
																			.getEarnedCredits())
																	* (courseList
																			.get(k)
																			.getObtainedGrade());
															cgpaEarnedTheroyCredit += (courseList
																			.get(k)
																			.getEarnedCredits());
															theoryEarnedCredit += (courseList
																			.get(k)
																			.getEarnedCredits());

														} else {
															studentStatus = "FAL";
															failCourseList
																	.add(courseList
																			.get(k)
																			.getCourseCode());
														}
														sgpaTheoryPoint += (courseList
																		.get(k)
																		.getObtainedGrade())
																* (courseList
																		.get(k)
																		.getCredits());
														System.out
																.println("Theory Reg course "
																		+ courseList
																				.get(k)
																				.getCourseCode()
																		+ " cgpaTheoryPoint"
																		+ cgpaTheoryPoint
																		+ " cgpaEarnedTheroyCredit"
																		+ cgpaEarnedTheroyCredit);
														break;
													}

													case 'P': {

														
														if (wrkcredit == wrkearnedCredit){		
															studentStatus = "PAS";
															cgpaPracticalPoint += (courseList
																			.get(k)
																			.getEarnedCredits())
																	* (courseList
																			.get(k)
																			.getObtainedGrade());
															cgpaEarnedPracticalCredit += (courseList
																			.get(k)
																			.getCredits());
															practicalEarnedCredit += (courseList
																			.get(k)
																			.getEarnedCredits());

														} else {
															studentStatus = "FAL";
															failCourseList
																	.add(courseList
																			.get(k)
																			.getCourseCode());
														}
														sgpaPracticalPoint += (courseList
																		.get(k)
																		.getObtainedGrade())
																* (courseList
																		.get(k)
																		.getCredits());
														System.out
																.println("Practical Reg course "
																		+ courseList
																				.get(k)
																				.getCourseCode()
																		+ " cgpaPracticalPoint"
																		+ cgpaPracticalPoint
																		+ " cgpaEarnedPracticalCredit"
																		+ cgpaEarnedPracticalCredit);
														break;
													}
													}
													break;
												}

												case 'A': {
													System.out
															.println("Audit Course Found");
													switch (courseClassification) {
													case 'T': {
//														if (courseList
//																.get(k)
//																.getEarnedCredits() == courseList
//																.get(k)
//																.getCredits()) {
														
														if (wrkcredit == wrkearnedCredit){
															studentStatus = "PAS";
														earnedTheoryAudCredit += (courseList
																		.get(k)
																		.getEarnedCredits());
															theoryEarnedCredit += (courseList
																			.get(k)
																			.getEarnedCredits());

														} else {
															studentStatus = "FAL";
															failCourseList
																	.add(courseList
																			.get(k)
																			.getCourseCode());

														}
														System.out
																.println("Theory Audit course "
																		+ courseList
																				.get(k)
																				.getCourseCode()
																		+ " cgpaTheoryPoint"
																		+ cgpaTheoryPoint
																		+ " cgpaEarnedTheroyCredit"
																		+ cgpaEarnedTheroyCredit);
														break;
													}

													case 'P': {
//														if (courseList
//																.get(k)
//																.getEarnedCredits() == courseList
//																.get(k)
//																.getCredits()) {
														
														if (wrkcredit == wrkearnedCredit){
															studentStatus = "PAS";
															earnedPracticalAudCredit = earnedPracticalAudCredit
																	+ (courseList
																			.get(k)
																			.getEarnedCredits());
															practicalEarnedCredit = practicalEarnedCredit
																	+ (courseList
																			.get(k)
																			.getEarnedCredits());

														} else {
															studentStatus = "FAL";
															failCourseList
																	.add(courseList
																			.get(k)
																			.getCourseCode());

														}
														System.out
																.println("Practical Audit course "
																		+ courseList
																				.get(k)
																				.getCourseCode()
																		+ " cgpaPracticalPoint"
																		+ cgpaPracticalPoint
																		+ " cgpaEarnedPracticalCredit"
																		+ cgpaEarnedPracticalCredit);
														break;
													}
													}
													break;
												}
												}

											
												statusInput
														.setStudentStatus(studentStatus);
												statusInput
														.setUserId(startActivityBean
																.getUserId());
												statusInput
														.setProgramCourseKey(programCourseKey);
												statusInput
														.setRollNumber(rollNumber);
												statusInput
														.setSemesterStartDate(semesterStartDate);
												statusInput
														.setSemesterEndDate(semesterEndDate);
												statusInput
														.setCourseCode(courseList
																.get(k)
																.getCourseCode());
												statusInput.setEntityId(inputBean.getEntityId());
												
												if (courseStatus.equals("REM")){
												
												
												sqlMapClient
														.update("preprocess.updateCourseStatus",
																statusInput);
												}
											}

											System.out
													.println("calculating sgpa For roll number"
															+ rollNumber
															+ " Sgpa Theory point="
															+ sgpaTheoryPoint
															+ " and Sgpa Practical Point"
															+ sgpaPracticalPoint);
											
											double theorysgpa = 0;
											double practicalsgpa = 0;
											if ((registeredTheoryCreditExcludingAudit + registeredPracticalCreditExcludingAudit)>0){
											sgpa = (sgpaTheoryPoint + sgpaPracticalPoint)
													/ (registeredTheoryCreditExcludingAudit + registeredPracticalCreditExcludingAudit);
											}else{
												sgpa = 0;
											}
											if(registeredTheoryCreditExcludingAudit>0) {
											 theorysgpa =sgpaTheoryPoint/registeredTheoryCreditExcludingAudit ;
											}else{
												theorysgpa=0;
											}
											if (registeredPracticalCreditExcludingAudit>0){
											practicalsgpa =sgpaPracticalPoint/registeredPracticalCreditExcludingAudit ;
											}else{
												practicalsgpa =0;
											}
										
											
											System.out
													.println("For Roll Number="
															+ rollNumber
															+ " SGPA=" + sgpa);
											
									

											inputBean.setRollNumber(rollNumber);
											inputBean
													.setAttemptNumber(attemptNumber);
											inputBean
													.setTheoryCredit(theoryEarnedCredit);
											inputBean
													.setPracticalCredit(practicalEarnedCredit);
											inputBean.setSgpa(sgpa);
											inputBean
													.setCgpaTheoryPointSecured(cgpaTheoryPoint);
											inputBean
													.setCgpaPracticalPointSecured(cgpaPracticalPoint);
											inputBean
													.setSgpaTheoryPointSecured(sgpaTheoryPoint);
											inputBean
													.setSgpaPracticalPointSecured(sgpaPracticalPoint);

											inputBean
													.setEarnedTheoryAudCredit(earnedTheoryAudCredit);
											inputBean
													.setEarnedPracticalAudCredit(earnedPracticalAudCredit);
											inputBean
													.setRegisteredTheoryCreditExcludingAudit(registeredTheoryCreditExcludingAudit);
											inputBean
													.setRegisteredPracticalCreditExcludingAudit(registeredPracticalCreditExcludingAudit);
											inputBean
													.setNumberOfRemedials(failCourseList
															.size());

											inputBean
													.setRemarks("SGPA Updated In Remedial Process At "
															+ new Date());
											
											
											inputBean.setEarnedTheorycgpaCredit(cgpaEarnedTheroyCredit);
											inputBean.setEarnedPracticalcgpaCredit(cgpaEarnedPracticalCredit);
											
											// Calculate CGPA Arush
											
											
											 wrkcgpa = calculatecgpa(inputBean);
											
											
												inputBean.setCgpa(wrkcgpa.getCgpa());
												inputBean.setTheorycgpa(wrkcgpa.getTheorycgpa());
												inputBean.setPracticalcgpa(wrkcgpa.getPracticalcgpa());
												inputBean
												.setCgpaTheoryPointSecured(wrkcgpa.getCgpaTheoryPointSecured());
												inputBean
												.setCgpaPracticalPointSecured(wrkcgpa.getCgpaPracticalPointSecured());
												inputBean
												.setEarnedTheorycgpaCredit(wrkcgpa.getEarnedTheorycgpaCredit());
												inputBean
												.setEarnedPracticalcgpaCredit(wrkcgpa.getEarnedPracticalcgpaCredit());
												
							
										
											sqlMapClient
													.update("preprocess.updateStudentAggregate",
															inputBean);
											
											if (student.getSemesterStatus().equalsIgnoreCase("REM")){																							

											finalFailCourseList
													.addAll(failCourseList);

											if (failCourseList.size() == 0) {
												inputBean
														.setSemesterStatus("PAS");
												sqlMapClient
														.update("preprocess.updateSRSHResultStatus",
																inputBean);
											
												studentTrackingFunction.insertStudentTracking(
														inputBean.getEntityId(),
														inputBean
																.getRollNumber(),
														programCourseKey,
														inputBean
																.getSemesterStartDate(),
														inputBean
																.getSemesterEndDate(),
														inputBean
																.getSessionStartDate(),
														inputBean
																.getSessionEndDate(),
														CRConstant.STATUS_PASS,
														CRConstant.ACTIVE_STATUS,
														inputBean.getUserId(),
														inputBean
																.getActivityId(),
														inputBean
																.getProcessId());
											} else {
											}
										}
										}
										}
									RemedialCourseBean inputForCgpa=new RemedialCourseBean();
//									inputForCgpa.setCourseCode(startActivityBean.getUniversityId());
//									inputForCgpa.setResultSystem(rollNumber);
									System.out.println("Univ Id="+inputForCgpa.getCourseCode()+"\tRoll No="+inputForCgpa.getResultSystem());
							//		PreProcessForResultBean wrkcgpa = (PreProcessForResultBean) sqlMapClient
								//			.queryForObject(
										//			"remedialProcess.getCgpaPointDetailFromAggregate",
									//													inputForCgpa);

													
								//	double cgpa = wrkcgpa.getCgpa();
									
									//PreProcessForResultBean wrkcgpa = new PreProcessForResultBean(); 
													
								     					
//									System.out.println("For Roll Number="
//											+ rollNumber + " CGPA=" + cgpa);
									wrkcgpa.setCgpa(inputBean.getCgpa());
									
									wrkcgpa.setUniversityId(startActivityBean
											.getUniversityId());
									wrkcgpa.setRollNumber(rollNumber);
									wrkcgpa.setUserId(startActivityBean
											.getUserId());
									wrkcgpa.setEntityId(startActivityBean
											.getEntityId());
									wrkcgpa.setProgramId(startActivityBean
											.getProgramId());
									wrkcgpa.setBranchId(startActivityBean
											.getBranchId());
									wrkcgpa.setSpecializationId(startActivityBean
											.getSpecializationId());
									wrkcgpa.setSemesterCode(startActivityBean
											.getSemesterCode());
									wrkcgpa.setSemesterStartDate(startActivityBean
											.getSemesterStartDate());
									wrkcgpa.setSemesterEndDate(startActivityBean
											.getSemesterEndDate());
									wrkcgpa.setSessionStartDate(startActivityBean
											.getSessionStartDate());
									wrkcgpa.setSessionEndDate(startActivityBean
											.getSessionEndDate());
									wrkcgpa.setProgramCourseKey(startActivityBean
											.getProgramCourseKey());
									wrkcgpa.setActivityId(startActivityBean
											.getActivityId());
									wrkcgpa.setProcessId(startActivityBean
											.getProcessId());
									wrkcgpa.setAttemptNumber(attemptNumber);

									sqlMapClient
											.update("preprocess.updateStudentProgramCGPA",
													wrkcgpa);
									
	
									
									

									if (finalFailCourseList.size() == 0) {
										if (isFinalSem) {
											wrkcgpa.setProgramStatus(CRConstant.STATUS_PASS);
											wrkcgpa.setProgramCompletionDate(wrkcgpa
													.getSemesterEndDate());
											wrkcgpa.setOutSemester(wrkcgpa
													.getSemesterCode());
											
											
			//								System.out.println(cgpa);
											System.out.println(wrkcgpa
													.getCgpa()
													+ " from bean before status update");
											sqlMapClient
													.update("preprocess.updateStudentProgramStatus",
															wrkcgpa);
											studentTrackingFunction.insertStudentTracking(
													startActivityBean
															.getEntityId(),
													rollNumber,
													startActivityBean
															.getProgramCourseKey(),
													startActivityBean
															.getSemesterStartDate(),
													startActivityBean
															.getSemesterEndDate(),
													startActivityBean
															.getSessionStartDate(),
													startActivityBean
															.getSessionEndDate(),
													CRConstant.STATUS_PASS,
													CRConstant.STATUS_PASS,
													startActivityBean
															.getUserId(),
													startActivityBean
															.getActivityId(),
													startActivityBean
															.getProcessId());
										} else {
											System.out
													.println("Inserting YTR when pass and not final semester");
											insertYTR.insertYTRForPASS(wrkcgpa);
										}
									} else {
										for (int s = 0; s < semesterList.size(); s++) {
											PreprocessTwoInfoGetter statusBean = new PreprocessTwoInfoGetter();
											statusBean
													.setEntityId(startActivityBean
															.getEntityId());
											statusBean
													.setProgramId(startActivityBean
															.getProgramId());
											statusBean
													.setBranchId(startActivityBean
															.getBranchId());
											statusBean
													.setSpecializationId(startActivityBean
															.getSpecializationId());
											statusBean
													.setSemesterCode(semesterList
															.get(s)
															.getSemesterCode());
											statusBean.setStatus("FAL");
											statusBean
													.setModifierId(startActivityBean
															.getUserId());
											statusBean
													.setStartDate(semesterList
															.get(s)
															.getSemesterStartDate());
											statusBean.setEndDate(semesterList
													.get(s)
													.getSemesterEndDate());
											statusBean
													.setRollNumber(rollNumber);

											TransferNORInPSTBean pckinfo = (TransferNORInPSTBean) sqlMapClient
													.queryForObject(
															"TransferNORInPSTBean.getProgramCourseKey",
															statusBean);

											statusBean
													.setProgramCourseKey(pckinfo
															.getProgramCourseKey());
											statusBean
													.setProgramMode(startActivityBean
															.getActivityId());

											sqlMapClient
													.update("remedialProcess.updateSRSH",
															statusBean);

											studentTrackingFunction.insertStudentTracking(
													startActivityBean
															.getEntityId(),
													rollNumber,
													statusBean
															.getProgramCourseKey(),
													semesterList
															.get(s)
															.getSemesterStartDate(),
													semesterList
															.get(s)
															.getSemesterEndDate(),
													startActivityBean
															.getSessionStartDate(),
													startActivityBean
															.getSessionEndDate(),
													CRConstant.STATUS_FAIL,
													CRConstant.ACTIVE_STATUS,
													startActivityBean
															.getUserId(),
													startActivityBean
															.getActivityId(),
													startActivityBean
															.getProcessId());

										}

										if (attemptNumber >= maxAttemptAllowed) {
											wrkcgpa.setProgramStatus(CRConstant.STATUS_FAIL);
			//								System.out.println(cgpa);
											System.out.println(wrkcgpa
													.getCgpa()
													+ " from bean before status update");
											sqlMapClient
													.update("preprocess.updateStudentProgramStatus",
															wrkcgpa);

											studentTrackingFunction.insertStudentTracking(
													wrkcgpa.getEntityId(),
													wrkcgpa.getRollNumber(),
													wrkcgpa.getProgramCourseKey(),
													wrkcgpa.getSemesterStartDate(),
													wrkcgpa.getSemesterEndDate(),
													wrkcgpa.getSessionStartDate(),
													wrkcgpa.getSessionEndDate(),
													CRConstant.STATUS_FAIL,
													CRConstant.STATUS_FAIL,
													startActivityBean
															.getUserId(),
													startActivityBean
															.getActivityId(),
													startActivityBean
															.getProcessId());
										} else {
											System.out
													.println("Inserting YTR when attemptNumber >= maxAttemptAllowed");
											insertYTR.insertYTRForFAIL(wrkcgpa);
										}
									}
								} catch (Exception e) {

									tStatus.rollbackToSavepoint(savepoint);
									errorList.add(new UnProcessedStduent(
											rollNumber,
											"Invalid entries in database"));
									studentTrackingFunction.insertStudentErrorLogs(
											startActivityBean.getEntityId(),
											startActivityBean.getProgramId(),
											startActivityBean.getBranchId(),
											startActivityBean
													.getSpecializationId(),
											startActivityBean.getSemesterCode(),
											startActivityBean
													.getSemesterStartDate(),
											startActivityBean
													.getSemesterEndDate(),
											startActivityBean.getProcessId(),
											startActivityBean.getActivityId(),
											enrollmentNumber, rollNumber,
											rollNumber, "DBI",
											"Invalid entries in database",
											startActivityBean
													.getProcessCounter());
									e.printStackTrace();
									result = false;
								}
								return result;
							}
						});
				if (status) {
					processedRecords++;
				} else {
					errorRecords++;
				}
				isCompleted = isCompleted && status;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CountProcessRecorList(totalRecords, processedRecords,
				errorRecords, isCompleted, errorList);
	}
	
	/// calculate cgpa at  each semester level
	public  PreProcessForResultBean  calculatecgpa(PreProcessForResultBean cgpadetail) throws SQLException{
		
		  
		List<PreviousSemesterDetail>  PreviousSemesterdetail  =    getprevioussemestercgpa(cgpadetail); 
		
		PreProcessForResultBean wrkcgpa = new PreProcessForResultBean(); 
		
		double cgpa = 0.00 ;
		double totalcgpapoint = 0.0;
		double totalcgpacredit =0.0;
		
		double totalcgpatheorypoint =0.00;
		double totalcgpapracticalpoint = 0.0;
		double totalcgpatheorycredit = 0.0 ;
		double totalcgpapracticalcredit = 0.0 ;
		
		if (PreviousSemesterdetail!= null ){
			
				
		totalcgpapoint =cgpadetail.getCgpaTheoryPointSecured()+cgpadetail.getCgpaPracticalPointSecured()+
		PreviousSemesterdetail.get(0).getTheoryCGPAPoint()+PreviousSemesterdetail.get(0).getPracticalCGPAPoint();
		
		totalcgpacredit = cgpadetail.getEarnedPracticalcgpaCredit()+cgpadetail.getEarnedTheorycgpaCredit()+
		PreviousSemesterdetail.get(0).getPracticalEarnedCgpaCredit()+PreviousSemesterdetail.get(0).getTheoryEarnedCgpaCredit()
		-cgpadetail.getEarnedPracticalAudCredit()-cgpadetail.getEarnedTheoryAudCredit();
		
		totalcgpatheorypoint = cgpadetail.getCgpaTheoryPointSecured()+PreviousSemesterdetail.get(0).getTheoryCGPAPoint();
		totalcgpapracticalpoint = cgpadetail.getCgpaPracticalPointSecured()+PreviousSemesterdetail.get(0).getPracticalCGPAPoint();
		totalcgpatheorycredit = cgpadetail.getTheoryCredit()+PreviousSemesterdetail.get(0).getTheoryEarnedCgpaCredit()-cgpadetail.getEarnedTheoryAudCredit();
		totalcgpapracticalcredit = cgpadetail.getPracticalCredit()+PreviousSemesterdetail.get(0).getPracticalEarnedCgpaCredit()-cgpadetail.getEarnedPracticalAudCredit();
		} else{
			
			totalcgpapoint =cgpadetail.getCgpaTheoryPointSecured()+cgpadetail.getCgpaPracticalPointSecured();
						
			totalcgpacredit = cgpadetail.getEarnedPracticalcgpaCredit()+cgpadetail.getEarnedTheorycgpaCredit()			
			-cgpadetail.getEarnedPracticalAudCredit()-cgpadetail.getEarnedTheoryAudCredit();
			
			totalcgpatheorypoint = cgpadetail.getCgpaTheoryPointSecured();
			totalcgpapracticalpoint = cgpadetail.getCgpaPracticalPointSecured();
			totalcgpatheorycredit = cgpadetail.getTheoryCredit()-cgpadetail.getEarnedTheoryAudCredit();
			totalcgpapracticalcredit = cgpadetail.getPracticalCredit()-cgpadetail.getEarnedPracticalAudCredit();
			
		}
		
		
		if (totalcgpacredit > 0){
					
		cgpa =totalcgpapoint/totalcgpacredit ;
		}
		else {cgpa =0.0;
			
		}
		
		
		
		double wrktheorycgpa = 0.0 ;
		double wrkpracticalcgpa = 0.0 ;
		
		if (totalcgpatheorycredit>0){
			wrktheorycgpa = totalcgpatheorypoint/totalcgpatheorycredit;
				}else{
			wrktheorycgpa = 0.0;
		}
			
		if (totalcgpapracticalcredit>0){
		wrkpracticalcgpa = totalcgpapracticalpoint/totalcgpapracticalcredit ;
		}else{
			wrkpracticalcgpa =0.0;	
		}
		
		
		wrkcgpa.setCgpa(cgpa);
		wrkcgpa.setTheorycgpa(wrktheorycgpa);
		wrkcgpa.setPracticalcgpa(wrkpracticalcgpa);
		
		
		wrkcgpa.setCgpaTheoryPointSecured(totalcgpatheorypoint);
		wrkcgpa.setCgpaPracticalPointSecured(totalcgpapracticalpoint);
		wrkcgpa.setEarnedTheorycgpaCredit(totalcgpatheorycredit);
		wrkcgpa.setEarnedPracticalcgpaCredit(totalcgpapracticalcredit);
		
		return (wrkcgpa);	
		
	
		
	}
	
	
	// Get cgpa points from previous semester 
	
	List<PreviousSemesterDetail> getprevioussemestercgpa(PreProcessForResultBean studentinfo) throws SQLException{
		
		List<PreviousSemesterDetail> previouspck = sqlMapClient
		.queryForList("commonresultprocessquery.previousProgramcoursekey",
				studentinfo);
		
		
		
		
		if (previouspck.size()>0){
			

						
			PreviousSemesterDetail previousSemesterDetail=new PreviousSemesterDetail(studentinfo.getRollNumber(),studentinfo.getEntityId(),
					previouspck.get(0).getProgramCourseKey());
			
		//	PreProcessForResultBean previoussemestercgpa = new PreProcessForResultBean() ;
						
			//ResultProcessingUtility resultProcessingUtility=new ResultProcessingUtility(sqlMapClient,transactionTemplate);
						
			List<PreviousSemesterDetail> previousDetails=sqlMapClient
			.queryForList("commonresultprocessquery.PreviouspckDetailsforgrade",
					previousSemesterDetail);
			
				//resultProcessingUtility.getPreviousSemesterDetailForMarksSystem(previousSemesterDetail);
			return (previousDetails);
			
		}else{
			return null ;
		}
		
				
			}
		

		
		
	
}
