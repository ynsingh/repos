/*
 * @(#) preProcessImpl.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
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

import in.ac.dei.edrp.cms.dao.studentregistration.preProcessConnect;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ActivityMasterBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.MasterTransferBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * This file consist of the methods used for executing the preProcess Checks for
 * different admission modes.
 * 
 * @author Ashish
 * @date 18 Jan 2011
 * @version 1.0
 */
public class preProcessImpl implements preProcessConnect {

	SqlMapClient sqlMapClient = null;
	TransactionTemplate transactionTemplate = null;
	private Logger loggerObject = Logger.getLogger(preProcessImpl.class);
	Locale localeObject = new Locale("en", "US");
	String sep = System.getProperty("file.separator");
	ResourceBundle resourceBundle = ResourceBundle.getBundle("in" + sep + "ac"
			+ sep + "dei" + sep + "edrp" + sep + "cms" + sep
			+ "databasesetting" + sep + "MessageProperties", localeObject);

	public preProcessImpl() {

	}

	public preProcessImpl(SqlMapClient sqlMapClient2,
			TransactionTemplate transactionTemplate) {

		this.sqlMapClient = sqlMapClient2;
		this.transactionTemplate = transactionTemplate;

	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * Method is for marking the students in all respect before generating the
	 * enrollment & roll numbers.
	 * 
	 * @param activityMasterBean
	 *            object of the bean file
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public CountProcessRecorList preProcessCheck(
			final ActivityMasterBean activityMasterBean) {

		CountProcessRecorList processRecorLists = (CountProcessRecorList) transactionTemplate
				.execute(new TransactionCallback() {

					CountProcessRecorList processRecorLists1 = new CountProcessRecorList();

					Object savepoint = null;

					public CountProcessRecorList doInTransaction(
							TransactionStatus transaction) {

						StudentInfoGetter studentObject = new StudentInfoGetter();
						StudentInfoGetter errorObject = new StudentInfoGetter();

						boolean flagValue = true;

						int recordsProcessed = 0;

						StudentInfoGetter beanobject = new StudentInfoGetter(
								activityMasterBean.getEntityId(),
								activityMasterBean.getProgramId(),
								activityMasterBean.getBranchId(),
								activityMasterBean.getSpecializationId(),
								activityMasterBean.getSemesterCode(),
								activityMasterBean.getSemesterStartDate(),
								activityMasterBean.getSemesterEndDate());

						beanobject.setStatus("N");
						
						//Change done by Dheeraj on 9/2/2012 for updating status in temp_student_program table on the basis of semester dates
						errorObject.setSemesterStartDate(activityMasterBean.getSemesterStartDate());
						errorObject.setSemesterEndDate(activityMasterBean.getSemesterEndDate());
						
						/*
						 * List of students with there basic info & admission
						 * mode for the concerned inputs
						 */
						List studentList = new ArrayList<StudentInfoGetter>();

						/*
						 * To check if a student exist with the information send
						 * as input
						 */
						StudentInfoGetter recordObject = new StudentInfoGetter();

						/*
						 * To check if the from & to program details picked from
						 * master table are correct for the student or not.
						 */
						StudentInfoGetter studentprogramObject;

						/*
						 * To check if the switch is valid or not
						 */
						StudentInfoGetter studentswitchObject;
						
						
						/*
						 * to return the student status for the normal students
						 */
						List<StudentInfoGetter> studentStatusObject = new ArrayList<StudentInfoGetter>();

						/*
						 * Rejected Student List
						 */
						List rejectedList = new ArrayList<StudentInfoGetter>();

						List<UnProcessedStduent> rejectedStudents = null;

						try {

							/*
							 * savepoint created
							 */
							savepoint = transaction.createSavepoint();

							studentList = sqlMapClient.queryForList(
									"studentenrollment.getlistofstudents",
									beanobject);
							/*
							 * if result is null
							 */
							if (studentList.size() == 0) {

								flagValue = false;

							} else {

								errorObject.setModifierId(activityMasterBean
										.getUserId());

								Iterator iteratorObject = studentList
										.iterator();

								while (iteratorObject.hasNext()) {
									StudentInfoGetter studentdetailsObject = (StudentInfoGetter) iteratorObject
											.next();

									studentObject = new StudentInfoGetter(
											studentdetailsObject.getStudentId(),
											studentdetailsObject.getFirstName(),
											studentdetailsObject
													.getMiddleName(),
											studentdetailsObject.getLastName(),
											studentdetailsObject
													.getFatherName(),
											studentdetailsObject
													.getDateOfBirth(),
											studentdetailsObject.getGender(),
											studentdetailsObject.getCategory(),
											studentdetailsObject
													.getEnrollmentNumber());

									/*
									 * when admission_mode=NEW
									 */
									if (studentdetailsObject.getAdmissionMode()
											.equalsIgnoreCase("NEW")) {
										loggerObject
												.info("####### in NEW pre check process #######");

										/*
										 * update the registration_status to R
										 * if reason=REJ
										 */
										if ((studentdetailsObject
												.getReasonCode() == null)
												|| (studentdetailsObject
														.getReasonCode()
														.equalsIgnoreCase(""))) {

											/*
											 * updated as sequence number data
											 * type is string in bean file
											 */
											if (Integer
													.parseInt(studentdetailsObject
															.getSequenceNumber()) != 1) {

												errorObject
														.setStudentId(studentObject
																.getStudentId());
												errorObject
														.setReason(resourceBundle
																.getString("error_code_sequence_number"));
												errorObject
														.setDescription(resourceBundle
																.getString("reason_sequence_number"));

												recordsProcessed = sqlMapClient
														.update(
																"studentenrollment.updatereasondescriptionstatus",
																errorObject);
											} else if ((studentdetailsObject
													.getEnrollmentNumber() == null)
													|| (studentdetailsObject
															.getEnrollmentNumber()
															.equalsIgnoreCase(""))) {
												recordObject = (StudentInfoGetter) sqlMapClient
														.queryForObject(
																"studentenrollment.getmatchingresults",
																studentObject);

												if (recordObject == null) {
													errorObject
															.setStudentId(studentObject
																	.getStudentId());
													sqlMapClient
															.update(
																	"studentenrollment.updateverificationstatus",
																	errorObject);
												} else {
													errorObject
															.setStudentId(studentObject
																	.getStudentId());
													errorObject
															.setReason(resourceBundle
																	.getString("error_code_duplicate_student_found"));
													errorObject
															.setDescription(resourceBundle
																	.getString("reason_duplicate_student_found")
																	+ " "
																	+ recordObject
																			.getEnrollmentNumber());

													recordsProcessed = sqlMapClient
															.update(
																	"studentenrollment.updatereasondescriptionstatus",
																	errorObject);
												}
											} else {
												recordObject = (StudentInfoGetter) sqlMapClient
														.queryForObject(
																"studentenrollment.getmatchingwithenrollment",
																studentObject);

												if (recordObject == null) {
													errorObject
															.setStudentId(studentObject
																	.getStudentId());
													errorObject
															.setReason(resourceBundle
																	.getString("error_code_student_mismatch"));
													errorObject
															.setDescription(resourceBundle
																	.getString("reason_student_mismatch")
																	+ " "
																	+ studentdetailsObject
																			.getEnrollmentNumber());

													recordsProcessed = sqlMapClient
															.update(
																	"studentenrollment.updatereasondescriptionstatus",
																	errorObject);
												} else {
													errorObject
															.setStudentId(studentObject
																	.getStudentId());
													sqlMapClient
															.update(
																	"studentenrollment.updateverificationstatus",
																	errorObject);
												}
											}

										} else {

											errorObject
													.setStudentId(studentObject
															.getStudentId());
											errorObject
													.setReason(resourceBundle
															.getString("error_code_rejected"));
											errorObject
													.setDescription(resourceBundle
															.getString("reason_rejected"));

											sqlMapClient
													.update(
															"studentenrollment.updatereasondescriptionstatusR",
															errorObject);

										}
									} 
									/*
									 * when admission_mode=SWT
									 */
									else if (studentdetailsObject
											.getAdmissionMode()
											.equalsIgnoreCase("SWT")) {
										loggerObject
												.info("####### in SWT pre check process #######");

										if (Integer
												.parseInt(studentdetailsObject
														.getSequenceNumber()) == 0) {
											errorObject
													.setStudentId(studentObject
															.getStudentId());
											errorObject
													.setReason(resourceBundle
															.getString("error_code_sequence_number_swt"));
											errorObject
													.setDescription(resourceBundle
															.getString("reason_sequence_number_swt"));

											recordsProcessed = sqlMapClient
													.update(
															"studentenrollment.updatereasondescriptionstatus",
															errorObject);
										}

										else if ((studentdetailsObject
												.getEnrollmentNumber() == null)
												|| (studentdetailsObject
														.getRollNumber() == null)
												|| (studentdetailsObject
														.getEnrollmentNumber()
														.equalsIgnoreCase("") || (studentdetailsObject
														.getRollNumber()
														.equalsIgnoreCase("")))) {
											errorObject
													.setStudentId(studentObject
															.getStudentId());
											errorObject
													.setReason(resourceBundle
															.getString("error_code_enroll"));
											errorObject
													.setDescription(resourceBundle
															.getString("reason_enroll"));

											recordsProcessed = sqlMapClient
													.update(
															"studentenrollment.updatereasondescriptionstatus",
															errorObject);
										} else {
											recordObject = (StudentInfoGetter) sqlMapClient
													.queryForObject(
															"studentenrollment.getmatchingwithenrollment",
															studentObject);

											if (recordObject == null) {
												errorObject
														.setStudentId(studentObject
																.getStudentId());
												errorObject
														.setReason(resourceBundle
																.getString("error_code_student_mismatch"));
												errorObject
														.setDescription(resourceBundle
																.getString("reason_student_mismatch")
																+ " "
																+ studentdetailsObject
																		.getEnrollmentNumber());

												recordsProcessed = sqlMapClient
														.update(
																"studentenrollment.updatereasondescriptionstatus",
																errorObject);
											} else {

												/*
												 * check for valid switch
												 */
												studentswitchObject = (StudentInfoGetter) sqlMapClient
														.queryForObject(
																"studentenrollment.checkswitchvalidity",
																studentdetailsObject
																		.getEnrollmentNumber());

												if (studentswitchObject == null) {
													errorObject
															.setEnrollmentNumber(studentdetailsObject
																	.getEnrollmentNumber());
													errorObject
															.setReason(resourceBundle
																	.getString("error_code_program_mismatch"));
													errorObject
															.setDescription(resourceBundle
																	.getString("reason_program_mismatch")
																	+ studentdetailsObject
																			.getEnrollmentNumber());
													recordsProcessed = sqlMapClient
															.update(
																	"studentenrollment.updatereasondescriptionstatuswithenroll",
																	errorObject);
												} else {

													/*
													 * check for program
													 * details(switching
													 * details)of the student
													 */
													studentprogramObject = (StudentInfoGetter) sqlMapClient
															.queryForObject(
																	"studentenrollment.studentprogrammatchingdetails",
																	studentdetailsObject
																			.getEnrollmentNumber());

													if (studentprogramObject == null) {
														errorObject
																.setEnrollmentNumber(studentdetailsObject
																		.getEnrollmentNumber());
														errorObject
																.setReason(resourceBundle
																		.getString("error_code_program_mismatch"));
														errorObject
																.setDescription(resourceBundle
																		.getString("reason_program_mismatch")
																		+ studentdetailsObject
																				.getEnrollmentNumber());

														recordsProcessed = sqlMapClient
																.update(
																		"studentenrollment.updatereasondescriptionstatuswithenroll",
																		errorObject);
													} else {

														errorObject
																.setEnrollmentNumber(studentObject
																		.getEnrollmentNumber());
														errorObject
																.setStatus("P");

														sqlMapClient
																.update(
																		"studentenrollment.updateverificationstatuswithenroll",
																		errorObject);

													}

												}

											}
										}
									}
									/*
									 * when admission_mode=NOR
									 */
									else if(studentdetailsObject
											.getAdmissionMode()
											.equalsIgnoreCase("NOR")){
										
										studentObject = new StudentInfoGetter(
												studentdetailsObject.getStudentId(),
												studentdetailsObject.getFirstName(),
												studentdetailsObject
														.getMiddleName(),
												studentdetailsObject.getLastName(),
												studentdetailsObject
														.getFatherName(),
												studentdetailsObject
														.getDateOfBirth(),
												studentdetailsObject.getGender(),
												studentdetailsObject.getCategory(),
												studentdetailsObject
														.getEnrollmentNumber());

										if ((studentdetailsObject
												.getEnrollmentNumber() == null)
												|| (studentdetailsObject
														.getRollNumber() == null)
												|| (studentdetailsObject
														.getEnrollmentNumber()
														.equalsIgnoreCase("") || (studentdetailsObject
														.getRollNumber()
														.equalsIgnoreCase("")))) {
											errorObject.setStudentId(studentObject
													.getStudentId());
											errorObject
													.setReason(resourceBundle
															.getString("error_code_student_mismatch"));
											errorObject
													.setDescription(resourceBundle
															.getString("reason_student_mismatch"));

											recordsProcessed = sqlMapClient
													.update(
															"studentenrollment.updatereasondescriptionstatus",
															errorObject);
										} else {
											studentObject
													.setEnrollmentNumber(studentdetailsObject
															.getEnrollmentNumber());
											studentObject
													.setFromSemester(studentdetailsObject
															.getFromSemester());
											studentObject
													.setRollNumber(studentdetailsObject
															.getRollNumber());
											studentObject.setProgramId(beanobject
													.getProgramId());
											studentObject.setBranchCode(beanobject
													.getBranchCode());
											studentObject.setSpecializationId(beanobject
													.getSpecializationId());
											studentObject.setCurrentSemester(beanobject
													.getSemesterCode());

											studentStatusObject = sqlMapClient
													.queryForList(
															"studentenrollment.getstudentstatus",
															studentObject);
											
											if (studentStatusObject.size() == 0) {

												errorObject
														.setEnrollmentNumber(studentObject
																.getEnrollmentNumber());
												errorObject.setStatus("G");

												sqlMapClient
														.update(
																"studentenrollment.updateverificationstatuswithenroll",
																errorObject);

											}else{
												
												Iterator<StudentInfoGetter> iterator = studentStatusObject.iterator();
												
												while (iterator.hasNext()) {
													StudentInfoGetter studentInfoGetter = (StudentInfoGetter) iterator
															.next();
													
													if ((studentInfoGetter
															.getStatus()
															.equalsIgnoreCase("REM"))
															|| (studentInfoGetter
																	.getStatus()
																	.equalsIgnoreCase("REG"))
															
															/*
															 * code temporarily removed
															 */
															
//															
//															|| (studentInfoGetter
//																	.getStatus()
//																	.equalsIgnoreCase("INC") || (studentInfoGetter
//																	.getStatus()
//																	.equalsIgnoreCase("FAL")))
																	
													) {

														StudentInfoGetter getter = (StudentInfoGetter) getSqlMapClient()
																.queryForObject(
																		"studentenrollment.getsemestergroupflag",
																		studentObject);

														if (getter.getSemesterStatus()
																.equalsIgnoreCase("y")) {

															errorObject
																	.setEnrollmentNumber(studentObject
																			.getEnrollmentNumber());
															errorObject.setStatus("G");

															sqlMapClient
																	.update(
																			"studentenrollment.updateverificationstatuswithenroll",
																			errorObject);

														} else {
															errorObject
																	.setEnrollmentNumber(studentdetailsObject
																			.getEnrollmentNumber());
															errorObject
																	.setReason(resourceBundle
																			.getString("error_code_semester_status"));
															errorObject
																	.setDescription(resourceBundle
																			.getString("reason_semester_status"));

															recordsProcessed = sqlMapClient
																	.update(
																			"studentenrollment.updatereasondescriptionstatuswithenroll",
																			errorObject);
														}

													}
													
													break;
													
												}
											}
										}
										
									}
								}								
								flagValue = true;
							}
						} catch (Exception e) {
							loggerObject.error("in preProcessCheck" + e);

							flagValue = false;
							/*
							 * roll back to savePoint
							 */
							transaction.rollbackToSavepoint(savepoint);
						}
						if (flagValue == true && recordsProcessed == 0) {

							processRecorLists1 = new CountProcessRecorList(
									studentList.size(), studentList.size(), 0,
									true, new ArrayList<UnProcessedStduent>());

						} else if (flagValue == true && recordsProcessed > 0) {

							try {
								rejectedList = sqlMapClient
										.queryForList(
												"studentenrollment.selectstudentswitherror",
												beanobject);

								rejectedStudents = new ArrayList<UnProcessedStduent>();

								Iterator<StudentInfoGetter> iterator = rejectedList
										.iterator();

								while (iterator.hasNext()) {

									UnProcessedStduent unProcessedStduent = new UnProcessedStduent();

									StudentInfoGetter studentInfoGetter = (StudentInfoGetter) iterator
											.next();

									unProcessedStduent
											.setRollNumber(studentInfoGetter
													.getRollNumber());
									unProcessedStduent
											.setProcessed(studentInfoGetter
													.getReasonDescription());

									rejectedStudents.add(unProcessedStduent);

								}

							} catch (Exception e) {
								loggerObject
										.error("while picking up error students in preprocessCheck"
												+ e);
							}

							processRecorLists1 = new CountProcessRecorList(
									studentList.size(), studentList.size()
											- rejectedList.size(), rejectedList
											.size(), false, rejectedStudents);

						} else {

							processRecorLists1 = new CountProcessRecorList(0,
									0, 0, false,
									new ArrayList<UnProcessedStduent>());

						}
						return processRecorLists1;

					}
				});

		return processRecorLists;
	}

	/**
	 * Method is for marking the students in all respect before transferring
	 * their data into the master tables(for normal admission mode).
	 * 
	 * @param input
	 *            object of the bean file
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public CountProcessRecorList preCheckforMaster(
			final ActivityMasterBean activityMasterBean) {

		final CountProcessRecorList processRecorLists = (CountProcessRecorList) transactionTemplate
				.execute(new TransactionCallback() {

					CountProcessRecorList processRecorLists1 = new CountProcessRecorList();

					Object savepoint = null;

					public CountProcessRecorList doInTransaction(
							TransactionStatus transaction) {

						StudentInfoGetter studentObject;
						StudentInfoGetter errorObject = new StudentInfoGetter();

						StudentInfoGetter input = new StudentInfoGetter(
								activityMasterBean.getEntityId(),
								activityMasterBean.getProgramId(),
								activityMasterBean.getBranchId(),
								activityMasterBean.getSpecializationId(),
								activityMasterBean.getSemesterCode(),
								activityMasterBean.getSemesterStartDate(),
								activityMasterBean.getSemesterEndDate());

						input.setStatus("N");
						
						//Change done by Dheeraj on 9/2/2012 for updating status in temp_student_program table on the basis of semester dates
						errorObject.setSemesterStartDate(activityMasterBean.getSemesterStartDate());
						errorObject.setSemesterEndDate(activityMasterBean.getSemesterEndDate());
						/*
						 * to return the list of students
						 */
						List studentList = new ArrayList<StudentInfoGetter>();

						/*
						 * to return the student status for the normal students
						 */
						StudentInfoGetter studentStatusObject;
						
						/*
						 * Rejected Student List
						 */
						List rejectedList = new ArrayList<StudentInfoGetter>();

						List<UnProcessedStduent> rejectedStudents = null;

						boolean flagValue = true;

						int recordsProcessed = 0;

						try {
							/*
							 * savepoint created
							 */
							savepoint = transaction.createSavepoint();

							studentList = sqlMapClient
									.queryForList(
											"studentenrollment.getlistofstudentswithNORMode",
											input);

							if (studentList.size() == 0) {

								flagValue = false;

							} else {

								errorObject.setModifierId(activityMasterBean
										.getUserId());

								Iterator iteratorObject = studentList
										.iterator();

								while (iteratorObject.hasNext()) {
									StudentInfoGetter studentdetailsObject = (StudentInfoGetter) iteratorObject
											.next();

									studentObject = new StudentInfoGetter(
											studentdetailsObject.getStudentId(),
											studentdetailsObject.getFirstName(),
											studentdetailsObject
													.getMiddleName(),
											studentdetailsObject.getLastName(),
											studentdetailsObject
													.getFatherName(),
											studentdetailsObject
													.getDateOfBirth(),
											studentdetailsObject.getGender(),
											studentdetailsObject.getCategory(),
											studentdetailsObject
													.getEnrollmentNumber());

									if ((studentdetailsObject
											.getEnrollmentNumber() == null)
											|| (studentdetailsObject
													.getRollNumber() == null)
											|| (studentdetailsObject
													.getEnrollmentNumber()
													.equalsIgnoreCase("") || (studentdetailsObject
													.getRollNumber()
													.equalsIgnoreCase("")))) {
										errorObject.setStudentId(studentObject
												.getStudentId());
										errorObject
												.setReason(resourceBundle
														.getString("error_code_student_mismatch"));
										errorObject
												.setDescription(resourceBundle
														.getString("reason_student_mismatch"));

										recordsProcessed = sqlMapClient
												.update(
														"studentenrollment.updatereasondescriptionstatus",
														errorObject);
									} else {
										studentObject
												.setEnrollmentNumber(studentdetailsObject
														.getEnrollmentNumber());
										studentObject
												.setFromSemester(studentdetailsObject
														.getFromSemester());
										studentObject
												.setRollNumber(studentdetailsObject
														.getRollNumber());
										studentObject.setProgramId(input
												.getProgramId());
										studentObject.setBranchCode(input
												.getBranchCode());
										studentObject.setSpecializationId(input
												.getSpecializationId());
										studentObject.setCurrentSemester(input
												.getSemesterCode());

										studentStatusObject = (StudentInfoGetter) sqlMapClient
												.queryForObject(
														"studentenrollment.getstudentstatus",
														studentObject);

										if (studentStatusObject == null) {

											errorObject
													.setEnrollmentNumber(studentObject
															.getEnrollmentNumber());
											errorObject.setStatus("G");

											sqlMapClient
													.update(
															"studentenrollment.updateverificationstatuswithenroll",
															errorObject);

										} else if ((studentStatusObject
												.getStatus()
												.equalsIgnoreCase("REM"))
												|| (studentStatusObject
														.getStatus()
														.equalsIgnoreCase("UFM"))
												|| (studentStatusObject
														.getStatus()
														.equalsIgnoreCase("INC") || (studentStatusObject
														.getStatus()
														.equalsIgnoreCase("FAL")))) {

											StudentInfoGetter getter = (StudentInfoGetter) getSqlMapClient()
													.queryForObject(
															"studentenrollment.getsemestergroupflag",
															studentObject);

											if (getter.getSemesterStatus()
													.equalsIgnoreCase("y")) {

												errorObject
														.setEnrollmentNumber(studentObject
																.getEnrollmentNumber());
												errorObject.setStatus("G");

												sqlMapClient
														.update(
																"studentenrollment.updateverificationstatuswithenroll",
																errorObject);

											} else {
												errorObject
														.setEnrollmentNumber(studentdetailsObject
																.getEnrollmentNumber());
												errorObject
														.setReason(resourceBundle
																.getString("error_code_semester_status"));
												errorObject
														.setDescription(resourceBundle
																.getString("reason_semester_status"));

												recordsProcessed = sqlMapClient
														.update(
																"studentenrollment.updatereasondescriptionstatuswithenroll",
																errorObject);
											}

										}
									}
								}

								flagValue = true;
							}
						} catch (Exception e) {
							loggerObject.error("in preCheckforMaster" + e);

							flagValue = false;

							/*
							 * rollback to savePoint
							 */
							transaction.rollbackToSavepoint(savepoint);
						}
						if (flagValue == true && recordsProcessed == 0) {

							processRecorLists1 = new CountProcessRecorList(
									studentList.size(), studentList.size(), 0,
									true, new ArrayList<UnProcessedStduent>());
							
						} else if (flagValue == true && recordsProcessed > 0) {							
							
							try {
								rejectedList = sqlMapClient
										.queryForList(
												"studentenrollment.selectstudentswitherrorinnor",
												input);

								rejectedStudents = new ArrayList<UnProcessedStduent>();

								Iterator<StudentInfoGetter> iterator = rejectedList
										.iterator();

								while (iterator.hasNext()) {

									UnProcessedStduent unProcessedStduent = new UnProcessedStduent();

									StudentInfoGetter studentInfoGetter = (StudentInfoGetter) iterator
											.next();

									unProcessedStduent
											.setRollNumber(studentInfoGetter
													.getRollNumber());
									unProcessedStduent
											.setProcessed(studentInfoGetter
													.getReasonDescription());

									rejectedStudents.add(unProcessedStduent);

								}

							} catch (Exception e) {
								loggerObject
										.error("while picking up error students in preprocessCheck in Normal mode"
												+ e);
							}

							processRecorLists1 = new CountProcessRecorList(
									studentList.size(), studentList.size()
											- rejectedList.size(), rejectedList
											.size(), false, rejectedStudents);
						} else {
							processRecorLists1 = new CountProcessRecorList(0,
									0, 0, false,
									new ArrayList<UnProcessedStduent>());
						}
						return processRecorLists1;
					}
				});

		return processRecorLists;
	}

}
