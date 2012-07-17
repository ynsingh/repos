/*
 * @(#) PreprocessTwoDaoImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.resultprocessing;

import in.ac.dei.edrp.cms.daoimpl.studentregistration.StudentRegistrationFormImpl;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreprocessTwoInfoGetter;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Implementation class for PreprocessTwoDao interface
 * 
 * @author Manpreet Kaur
 * @date 08-04-2011
 * @version 1.0
 */
public class PreprocessTwoDaoImpl extends SqlMapClientDaoSupport {
	private static Logger logObj = Logger
			.getLogger(StudentRegistrationFormImpl.class);
	TransactionTemplate transactionTemplate = null;
	protected SqlMapClient sqlMapClient = null;
	List<UnProcessedStduent> studentList=new ArrayList<UnProcessedStduent>();

	int totalRecords = 0;
	int correctProcessed = 0;
	int rejectedProcessed = 0;
	int inError = 0;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * default constructor
	 */
	public PreprocessTwoDaoImpl() {

	}

	public PreprocessTwoDaoImpl(SqlMapClient sqlMapClient,
			TransactionTemplate transactionTemplate) {
		super();
		this.sqlMapClient = sqlMapClient;
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * Method for updating students' remedial status according to marks scored
	 * in re-attempt and accordingly for updating students' semester status
	 * 
	 * @param inputObj
	 * @return List<CountProcessRecorList>
	 */
	public List<CountProcessRecorList> updateStudentStatus(
			final AwardSheetInfoGetter inputObj) {
		/*
		 * this coding is for fixed credit system only
		 */

		final List<CountProcessRecorList> resultList = new ArrayList<CountProcessRecorList>();

		transactionTemplate.execute(new TransactionCallback() {
			Object savepoint = null;
			String studentSemesterAttempt = "";

			@SuppressWarnings("unchecked")
			public List<CountProcessRecorList> doInTransaction(
					TransactionStatus status) {
				try {

					System.out.println("coming in impl");

					PreprocessTwoInfoGetter maxAttemptObj = (PreprocessTwoInfoGetter) sqlMapClient
							.queryForObject(
									"preprocessTwo.getMaxNumberOfAttempts",
									inputObj);

					List<PreprocessTwoInfoGetter> semesterList = sqlMapClient
							.queryForList("preprocessTwo.getallSemesters",
									inputObj);
					System.out
							.println("sem list size= +" + semesterList.size());

					List<PreprocessTwoInfoGetter> studentList = sqlMapClient
							.queryForList("preprocessTwo.getStudentList",
									inputObj);

					totalRecords = studentList.size();

					for (int i = 0; i < studentList.size(); i++) {
						// creating savepoint
						savepoint = status.createSavepoint();
						inputObj.setRollNumber(studentList.get(i)
								.getRollNumber());
						studentSemesterAttempt = studentList.get(i)
								.getAttemptNumber();

						List<PreprocessTwoInfoGetter> courseList = sqlMapClient
								.queryForList(
										"preprocessTwo.getStudentCourseList",
										inputObj);

						int countFail = 0;

						for (int j = 0; j < courseList.size(); j++) {
							inputObj.setCourseCode(courseList.get(j)
									.getCourseCode());
							inputObj.setAttemptNumber(courseList.get(j)
									.getAttemptNumber());

							List<AwardSheetInfoGetter> compList = sqlMapClient
									.queryForList(
											"AwardSheet.getEvaluationComponent",
											inputObj);

							float marks = 0;

							for (int k = 0; k < compList.size(); k++) {
								try {
									inputObj.setEvaluationId(compList.get(k)
											.getEvaluationId());

									PreprocessTwoInfoGetter marksObj = (PreprocessTwoInfoGetter) sqlMapClient
											.queryForObject(
													"preprocessTwo.getComponentMarks",
													inputObj);
									marks = marks
											+ Float.parseFloat(marksObj
													.getMarks());
								} catch (Exception e) {
									logger.error(e);
								}

							}

							/*
							 * 70 is yet hardcoded & has to be changed later
							 * 
							 * coding for grades is to be done
							 */
							if (marks >= 70) {
								inputObj.setStatus("PAS");
							} else if (marks < 70) {
								inputObj.setStatus("FAL");
								countFail++;
							}

							sqlMapClient.update(
									"preprocessTwo.updateStudentCourse",
									inputObj);
						}

						if (countFail > 0) {
							inputObj.setStatus("FAL");
						} else {
							inputObj.setStatus("PAS");
						}
						// updating student_registration_semester_header table

						sqlMapClient.update("preprocessTwo.updateSRSH",
								inputObj);

						/*
						 * updations in student_program
						 */

						PreprocessTwoInfoGetter spInputObject = new PreprocessTwoInfoGetter();
						spInputObject.setRollNumber(inputObj.getRollNumber());
						spInputObject.setAttemptNumber(studentSemesterAttempt);
						spInputObject.setProgramId(inputObj.getProgramId());
						spInputObject.setBranchId(inputObj.getBranchId());
						spInputObject.setSpecializationId(inputObj
								.getSpecializationId());
						spInputObject.setSemesterCode(inputObj
								.getSemesterCode());
						spInputObject.setEntityId(inputObj.getEntityId());
						spInputObject.setModifierId(inputObj.getModifierId());

						updateStudentProgram(spInputObject,
								Integer.parseInt(maxAttemptObj
										.getMaxAllowedAttempt()), Integer
										.parseInt(studentSemesterAttempt),
								semesterList);

						/*
						 * updations in student_program coding ends
						 */

						// commiting one student's updates
						sqlMapClient.getDataSource().getConnection().commit();
						correctProcessed++;
					}

				} catch (Exception e) {
					inError++;
					logObj.error(e);
					status.rollbackToSavepoint(savepoint);
					// signifies failure
					// throw new MyException(e.getMessage());
				}
				resultList.add(new CountProcessRecorList(totalRecords,
						correctProcessed, rejectedProcessed, inError, true));

				System.out.println("returning from inside");
				// signifies success
				return resultList;
			}
		});
		System.out.println("returning from outside");

		// signifies success
		return resultList;
	}

	/**
	 * Method for updating student_program table according to student's semester
	 * status
	 * 
	 * @param spInputObject
	 * @param maxAttemptAllowed
	 * @param studentSemesterAttempt
	 * @param semesterList
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void updateStudentProgram(PreprocessTwoInfoGetter spInputObject,
			int maxAttemptAllowed, int studentSemesterAttempt,
			List<PreprocessTwoInfoGetter> semesterList) throws Exception {

		try {

			int totalFail = 0;
			int totalPass = 0;

			List<PreprocessTwoInfoGetter> pckObject = sqlMapClient
					.queryForList("preprocessTwo.getPCKAttempt", spInputObject);

			PreprocessTwoInfoGetter countObj = new PreprocessTwoInfoGetter();
			countObj.setRollNumber(spInputObject.getRollNumber());

			for (int a = 0; a < pckObject.size(); a++) {

				countObj.setProgramCourseKey(pckObject.get(a)
						.getProgramCourseKey());
				countObj.setAttemptNumber(pckObject.get(a).getAttemptNumber());

				PreprocessTwoInfoGetter semStatus = (PreprocessTwoInfoGetter) sqlMapClient
						.queryForObject("preprocessTwo.getEachSemStatus",
								countObj);

				// counting pass and fail semesters
				if (semStatus.getStatus().equalsIgnoreCase("PAS")) {
					totalPass++;
				} else if (semStatus.getStatus().equalsIgnoreCase("FAL")) {
					totalFail++;
				}

			}

			int updateFlag = 0;
			String updatedStatus = "";

			if (totalFail > 0) {
				// if no attempt left
				if (studentSemesterAttempt >= maxAttemptAllowed) {
					spInputObject.setStatus("FAL");
					updateFlag = sqlMapClient
							.update("preprocessTwo.updateStudentProgram",
									spInputObject);
					updatedStatus = "FAL";
				}
			} else if (totalPass == semesterList.size()) {

				spInputObject.setStatus("PAS");
				updateFlag = sqlMapClient.update(
						"preprocessTwo.updateStudentProgram", spInputObject);
				updatedStatus = "PAS";

			}

			if (updateFlag > 0) {
				PreprocessTwoInfoGetter switchNumberObj = (PreprocessTwoInfoGetter) sqlMapClient
						.queryForObject("preprocessTwo.getSwitchNumber",
								spInputObject);

				spInputObject
						.setSwitchNumber(switchNumberObj.getSwitchNumber());

				if (switchNumberObj.getSwitchNumber() != null) {

					// switch not null means any other program exists for same
					// student

					List<PreprocessTwoInfoGetter> fwdProgramList = sqlMapClient
							.queryForList("preprocessTwo.getFwdProgram",
									spInputObject);

					for (int count = 0; count < fwdProgramList.size(); count++) {
						PreprocessTwoInfoGetter object = new PreprocessTwoInfoGetter();
						object = fwdProgramList.get(count);
						object.setProgramId(object.getFromProgram());
						object.setBranchId(object.getFromBranch());
						object.setSpecializationId(object
								.getFromSpecialization());
						object.setSwitchNumber(switchNumberObj
								.getSwitchNumber());

						PreprocessTwoInfoGetter statusObj = (PreprocessTwoInfoGetter) sqlMapClient
								.queryForObject(
										"preprocessTwo.getInpProgramStatus",
										object);

						if (updatedStatus.equalsIgnoreCase("PAS")) {

							if (statusObj.getStatus().equalsIgnoreCase("HLD")) {
								object.setStatus("PAS");
								sqlMapClient
										.update(
												"preprocessTwo.updateInpStatus",
												object);
							}
						} else if (updatedStatus.equalsIgnoreCase("FAL")) {
							object.setStatus("FAL");
							sqlMapClient.update(
									"preprocessTwo.updateInpStatus", object);
						} else if (updatedStatus.equalsIgnoreCase("TER")) {
							object.setStatus("TER");
							sqlMapClient.update(
									"preprocessTwo.updateInpStatus", object);
						}

					}

					// checking for going backward
					PreprocessTwoInfoGetter modeObj = (PreprocessTwoInfoGetter) sqlMapClient
							.queryForObject("preprocessTwo.checkIntegrated",
									spInputObject);

					if (modeObj.getProgramMode().equalsIgnoreCase("I")) {
						// If integrated then go backward
						List<PreprocessTwoInfoGetter> bwdProgramList = sqlMapClient
								.queryForList("preprocessTwo.getBwdProgram",
										spInputObject);
						for (int count = 0; count < bwdProgramList.size(); count++) {
							PreprocessTwoInfoGetter object = new PreprocessTwoInfoGetter();
							object = bwdProgramList.get(count);
							object.setProgramId(object.getToProgram());
							object.setBranchId(object.getToBranch());
							object.setSpecializationId(object
									.getToSpecialization());
							object.setSwitchNumber(switchNumberObj
									.getSwitchNumber());
							PreprocessTwoInfoGetter statusObj = (PreprocessTwoInfoGetter) sqlMapClient
									.queryForObject(
											"preprocessTwo.getInpProgramStatus",
											object);

							if (updatedStatus.equalsIgnoreCase("PAS")) {
								if (statusObj.getStatus().equalsIgnoreCase(
										"ACT")) {
									spInputObject.setStatus("HLD");
									sqlMapClient
											.update(
													"preprocessTwo.updateStudentProgram",
													spInputObject);
								} else if (statusObj.getStatus()
										.equalsIgnoreCase("PAS")) {
									spInputObject.setStatus("PAS");
									sqlMapClient
											.update(
													"preprocessTwo.updateStudentProgram",
													spInputObject);
								} else if (statusObj.getStatus()
										.equalsIgnoreCase("FAL")) {
									spInputObject.setStatus("FAL");
									sqlMapClient
											.update(
													"preprocessTwo.updateStudentProgram",
													spInputObject);
								} else if (statusObj.getStatus()
										.equalsIgnoreCase("TER")) {
									spInputObject.setStatus("TER");
									sqlMapClient
											.update(
													"preprocessTwo.updateStudentProgram",
													spInputObject);
								}
							} else if (updatedStatus.equalsIgnoreCase("FAL")) {
								spInputObject.setStatus("FAL");
								sqlMapClient.update(
										"preprocessTwo.updateStudentProgram",
										spInputObject);
							} else if (updatedStatus.equalsIgnoreCase("TER")) {
								spInputObject.setStatus("TER");
								sqlMapClient.update(
										"preprocessTwo.updateStudentProgram",
										spInputObject);
							}

						}
					}

				}

			}

		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}
