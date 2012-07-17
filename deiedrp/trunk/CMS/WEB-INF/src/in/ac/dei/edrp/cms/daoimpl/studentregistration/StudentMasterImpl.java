/*
 * StudentMasterImpl.java
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

import in.ac.dei.edrp.cms.dao.studentregistration.StudentMasterConnect;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ActivityMasterBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentNumbersInfoGetter;

import java.sql.SQLException;
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
 * This file consist of the methods used at the time of activities(number
 * generation & master transfer) which are to be executed during registration
 * process.
 *
 * @author Ashish
 * @date 18 Jan 2011
 * @version 1.0
 */
public class StudentMasterImpl implements StudentMasterConnect {
	private Logger loggerObject = Logger.getLogger(StudentMasterImpl.class);
	SqlMapClient sqlMapClient = null;
	TransactionTemplate transactionTemplate = null;

	Locale localeObject = new Locale("en", "US");
	String sep=System.getProperty("file.separator");
	ResourceBundle resourceBundle = ResourceBundle.getBundle(
			"in"+sep+"ac"+sep+"dei"+sep+"edrp"+sep+"cms"+sep+"databasesetting"+sep+"MessageProperties",
			localeObject);

	public StudentMasterImpl() {

	}

	public StudentMasterImpl(SqlMapClient sqlMapClient2,
			TransactionTemplate transactionTemplate) {
		this.sqlMapClient = sqlMapClient2;
		this.transactionTemplate = transactionTemplate;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * Method generates enrollment numbers for the students of a program
	 * combination
	 *
	 * @param activityMasterBean
	 *            the object of the referenced bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public CountProcessRecorList generateEnrollmentNumbers(
			final ActivityMasterBean activityMasterBean) {

		CountProcessRecorList processRecorLists = (CountProcessRecorList) transactionTemplate.execute(new TransactionCallback() {

			CountProcessRecorList processRecorLists1 = new CountProcessRecorList();

			Object savepoint = null;

			public CountProcessRecorList doInTransaction(
					TransactionStatus transaction) {

				boolean flagValue=false;

				StudentInfoGetter beanobject = new StudentInfoGetter(
						activityMasterBean.getEntityId(), activityMasterBean
								.getProgramId(), activityMasterBean
								.getBranchId(), activityMasterBean
								.getSpecializationId(), activityMasterBean
								.getSemesterCode(), activityMasterBean
								.getSemesterStartDate(), activityMasterBean
								.getSemesterEndDate());

				StudentNumbersInfoGetter systemvalue = new StudentNumbersInfoGetter();

				String uniId = activityMasterBean.getProgramId()
						.substring(0, 4);
				String CODE = "ENROLL";

				beanobject.setStatus("V");

				systemvalue.setUniversityId(uniId);
				systemvalue.setCode(CODE);

				/*
				 * to get the list of student whose registration status is yes
				 * for the selected combination
				 */
				List studentlist = new ArrayList<StudentInfoGetter>();

				/*
				 * to get the enrollment number(counter value) for a particular
				 * student id
				 */
				StudentNumbersInfoGetter sysObject;
				
				/*
				 * Rejected Student List
				 */
				List rejectedList = new ArrayList<StudentInfoGetter>();

				List<UnProcessedStduent> rejectedStudents = null;

				List infoGetter = new ArrayList<StudentInfoGetter>();
				
				int counter = 0;

				try {

					/*
					 * savepoint created
					 */
					savepoint = transaction.createSavepoint();

					String enrollmentNumber = null;

					String session = (activityMasterBean.getSemesterStartDate()
							.substring(2, 4));

					/*
					 * to check if any record with status other than V exists
					 */
					infoGetter = sqlMapClient.queryForList(
							"studentenrollment.getregistrationStatus",
							beanobject);

					/*
					 * don't fire the process return back
					 */
					if (infoGetter.size() != 0) {

						flagValue=false;

					} else {

						/*
						 * to get the students with no enrollment numbers &
						 * admission_mode=NEW
						 */
						studentlist = sqlMapClient.queryForList(
								"studentenrollment.getlistforenrollments",
								beanobject);


						Iterator iteratorObject = studentlist.iterator();

						while (iteratorObject.hasNext()) {
							StudentInfoGetter listObject = (StudentInfoGetter) iteratorObject
									.next();

							/*
							 * to get the value for the code for enrollment
							 * number generation
							 */
							sysObject = (StudentNumbersInfoGetter) sqlMapClient
									.queryForObject(
											"studentenrollment.sysvalue",
											systemvalue);

							int value = Integer.parseInt(sysObject
									.getSystemValue()) + 1;

							enrollmentNumber = String.format("%04d", value);

							systemvalue.setSystemValue(enrollmentNumber);

							/*
							 * update the value for the code for enrollment
							 * number generation
							 */
							sqlMapClient.update(
									"studentenrollment.updatesysvalue",
									systemvalue);

							listObject.setEnrollmentNumber(session
									+ enrollmentNumber);
							listObject.setStudentId(listObject.getStudentId());
							listObject.setStatus("V");
							listObject.setUserId(activityMasterBean.getUserId());

							/*
							 * to update the enrollment number in temporary
							 * tables for student
							 */
							counter =counter + sqlMapClient
									.update(
											"studentenrollment.updateenrollmentstables",
											listObject);
						}
						
						if(counter>0){							
							flagValue=true;							
						}
						
						
					}

				} catch (Exception e) {
					loggerObject.error("in generateEnrollmentNumbers" + e);

					/*
					 * roll back to savePoint
					 */
					transaction.rollbackToSavepoint(savepoint);
				}

				if(flagValue==true){

					processRecorLists1 = new CountProcessRecorList(studentlist
							.size(), studentlist.size(), 0, true,new ArrayList<UnProcessedStduent>());
//					processRecorLists.add(new CountProcessRecorList(studentlist
//							.size(), studentlist.size(), 0, 0, true));
				}else if(flagValue==false){
					
					try {
						rejectedList = sqlMapClient
								.queryForList(
										"studentenrollment.getregistrationStatus",
										beanobject);

						rejectedStudents = new ArrayList<UnProcessedStduent>();

						Iterator<StudentInfoGetter> iterator = rejectedList
								.iterator();

						while (iterator.hasNext()) {

							

							StudentInfoGetter studentInfoGetter = (StudentInfoGetter) iterator
									.next();

							

							rejectedStudents.add(new UnProcessedStduent(studentInfoGetter.getRollNumber(),
									"Registration status is "+studentInfoGetter.getStatus()+", not appropriate for this process"));

						}

					} catch (Exception e) {
						loggerObject
								.error("while picking up error students in preprocessCheck in Normal mode"
										+ e);
					}

					processRecorLists1 = new CountProcessRecorList(
							rejectedList
							.size(),0, rejectedList
									.size(), false, rejectedStudents);
//					processRecorLists.add(new CountProcessRecorList(0,0, 0, 0, false));

				}
				return processRecorLists1;
			}
		});

		return processRecorLists;
	}

	/**
	 * Method generates roll numbers for the students for a program combination
	 * after verifying records
	 *
	 * @param activityMasterBean
	 *            object of the referenced bean file
	 */
	public CountProcessRecorList generateRollNumbers(
			final ActivityMasterBean activityMasterBean) {

		final CountProcessRecorList processRecorLists = (CountProcessRecorList)transactionTemplate.execute(new TransactionCallback() {

			CountProcessRecorList processRecorLists1 = new CountProcessRecorList();

			Object savepoint = null;

			@SuppressWarnings("unchecked")
			public CountProcessRecorList doInTransaction(
					TransactionStatus status) {

				boolean flagValue=true;
				String CODE = "ROL";
				String year = (activityMasterBean.getSessionStartDate())
						.toString().substring(0, 4);

				StudentNumbersInfoGetter beanobject = new StudentNumbersInfoGetter(
						activityMasterBean.getEntityId(), activityMasterBean
								.getProgramId(), activityMasterBean
								.getBranchId(), activityMasterBean
								.getSpecializationId(), activityMasterBean
								.getSemesterCode(), activityMasterBean
								.getSemesterStartDate(), activityMasterBean
								.getSemesterEndDate());

				StudentInfoGetter infoStudentInfoGetter = new StudentInfoGetter(
						activityMasterBean.getEntityId(), activityMasterBean
								.getProgramId(), activityMasterBean
								.getBranchId(), activityMasterBean
								.getSpecializationId(), activityMasterBean
								.getSemesterCode(), activityMasterBean
								.getSemesterStartDate(), activityMasterBean
								.getSemesterEndDate());

				StudentNumbersInfoGetter entitysystemvalue = new StudentNumbersInfoGetter();
				StudentInfoGetter switchingobject;

				beanobject.setCode(CODE);
				beanobject.setYear(year);

				loggerObject
						.info("********************************in roll number generation*************************");

				/*
				 * to get the list of student whose registration status is yes
				 * for the selected combination
				 */
				List studentlist = new ArrayList<StudentInfoGetter>();

				/*
				 * to get the roll number(counter value) for a particular
				 * student id
				 */
				StudentNumbersInfoGetter systemObject;

				/*
				 * to check if the program is locked or unlocked
				 */
				StudentNumbersInfoGetter entitylistObject;

				/*
				 * to get switching details of the student
				 */
				StudentInfoGetter ruleCodeOneObject = new StudentInfoGetter();

				/*
				 *
				 */
				StudentInfoGetter infoGetter = new StudentInfoGetter();
				
				int counter = 0;
				
				try {
					entitysystemvalue.setEntityId(activityMasterBean
							.getEntityId());
					/*
					 * modified 
					 * @param before activityMasterBean.getProgramId()
					 * @param now "All"							
					 */
					entitysystemvalue.setProgramId("All");
					entitysystemvalue.setYear(year);
					entitysystemvalue.setModifierId(activityMasterBean
							.getUserId());

					/*
					 * modified
					 * @param before activityMasterBean.getProgramId()
					 * @param now "All"	
					 */
					StudentNumbersInfoGetter systemvalue = new StudentNumbersInfoGetter(
							"All", CODE,
							activityMasterBean.getEntityId(), year);

					savepoint = status.createSavepoint();

					/*
					 * to check if record exist for the entity in
					 * system_table_one
					 */
					entitylistObject = (StudentNumbersInfoGetter) sqlMapClient
							.queryForObject("studentenrollment.checkvalues",
									entitysystemvalue);

					if (entitylistObject == null) {
						/*
						 * modified 
						 * @param before activityMasterBean.getProgramId()
						 * @param now "All"							
						 */
						StudentNumbersInfoGetter insertbeanobject = new StudentNumbersInfoGetter(
								activityMasterBean.getEntityId(),
								"All", CODE, year,
								activityMasterBean.getUserId());

						/*
						 * insert a record in system_table_one if no record
						 * exist for the entity
						 */
						sqlMapClient.insert(
								"studentenrollment.insertprogramsystemvalue",
								insertbeanobject);
					} else if (entitylistObject.getStatusFlag()
							.equalsIgnoreCase("U")) {
						entitysystemvalue.setStatusFlag("L");

						/*
						 * if record found then update flag status before
						 * generating roll numbers for the students
						 */
						sqlMapClient.update(
								"studentenrollment.updateentitysatusflag",
								entitysystemvalue);
					} else if (entitylistObject.getStatusFlag()
							.equalsIgnoreCase("L")) {

						flagValue=false;

					}

					/*
					 * to get the list of students(for NEW & SWT mode) for roll
					 * number generation
					 */
					studentlist = sqlMapClient.queryForList(
							"studentenrollment.getlistofstudentsforrolls",
							beanobject);

					if (studentlist.size() == 0) {
						entitysystemvalue.setStatusFlag("U");

						sqlMapClient.update(
								"studentenrollment.updateentitysatusflag",
								entitysystemvalue);

						flagValue=false;

					} else {

						infoStudentInfoGetter.setActualRecords(studentlist
								.size());
						infoStudentInfoGetter.setProcessFlag("ROL");
						infoStudentInfoGetter.setProcessCounter(1);

						Iterator iteratorObject = studentlist.iterator();

						while (iteratorObject.hasNext()) {
							StudentInfoGetter numbersObject = (StudentInfoGetter) iteratorObject
									.next();

							/*
							 * get the system value for the program from
							 * system_table_one
							 */
							systemObject = (StudentNumbersInfoGetter) sqlMapClient
									.queryForObject(
											"studentenrollment.sysvalueforroll",
											systemvalue);
							/*
							 * when admission_mode==NEW
							 */
							if (numbersObject.getAdmissionMode()
									.equalsIgnoreCase("NEW")) {
								/*
								 * method called for roll numbers generation
								 */
								counter = methodUpdateRollNumbers(activityMasterBean
										.getUserId(), systemObject
										.getSystemValue(), year,
										activityMasterBean.getEntityId(),
										numbersObject.getStudentId(),
										activityMasterBean.getProgramId());

								infoStudentInfoGetter
										.setRecordsProcessed(studentlist.size());
								loggerObject
										.info("*********************************new successfull***********************");
							}

							/*
							 * when admission_mode==SWT
							 */
							if (numbersObject.getAdmissionMode()
									.equalsIgnoreCase("SWT")) {

								infoGetter.setProgramId(activityMasterBean
										.getProgramId());
								infoGetter.setBranchCode(activityMasterBean
										.getBranchId());
								infoGetter
										.setSpecializationId(activityMasterBean
												.getSpecializationId());

								switchingobject = new StudentInfoGetter(
										infoGetter.getProgramId(), infoGetter
												.getBranchCode(), infoGetter
												.getSpecializationId(),
										numbersObject.getOldProgramId(),
										numbersObject.getOldBranchCode(),
										numbersObject.getOldSpecialization(),
										numbersObject.getFromSemester(),
										numbersObject.getToSemester());
								
								switchingobject.setRollNumber(numbersObject.getRollNumber());
								switchingobject.setOldEntityId(numbersObject.getOldEntityId());
								switchingobject.setEntityId(activityMasterBean.getEntityId());
								
								infoStudentInfoGetter.setRollNumber(numbersObject.getRollNumber());

								/*
								 * get the flag value for roll number generation
								 * for the concerned switching
								 */
								ruleCodeOneObject = (StudentInfoGetter) sqlMapClient
										.queryForObject(
												"studentenrollment.getrulecodevalue",
												switchingobject);

								/*
								 * if flag==Y ie. roll numbers needs to be
								 * generated
								 */
								if (ruleCodeOneObject.getRuleCodeOne()
										.equalsIgnoreCase("Y")) {

									/*
									 * method called for roll numbers generation
									 */
									counter = methodUpdateRollNumbers(activityMasterBean
											.getUserId(), systemObject
											.getSystemValue(),
											year,
											activityMasterBean.getEntityId(),
											numbersObject.getStudentId(),
											activityMasterBean.getProgramId());
									loggerObject
											.info("********************************in Switch successful********************************");
								} else if (ruleCodeOneObject.getRuleCodeOne()
										.equalsIgnoreCase("N")){

									/*
									 * update registration status of the
									 * students for the program combination to 'G'
									 */
									sqlMapClient
											.update(
													"studentenrollment.updateregistrationstatus",
													infoStudentInfoGetter);

									flagValue=true;

								}else{
									
									status.rollbackToSavepoint(savepoint);
									
									flagValue = false;
									
									counter = 0;
									
								}
							}
						}

						entitysystemvalue.setStatusFlag("U");

						/*
						 * update flag status for the program in system_table_one
						 */
						sqlMapClient.update(
								"studentenrollment.updateentitysatusflag",
								entitysystemvalue);

						flagValue=true;
					}



				} catch (Exception e) {
					loggerObject.error("in generaterollNumbers" + e);

					/*
					 * update flag status for the program in system_table_one
					 */
					entitysystemvalue.setStatusFlag("U");
					try {
						sqlMapClient.update(
								"studentenrollment.updateentitysatusflag",
								entitysystemvalue);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

					status.rollbackToSavepoint(savepoint);
				}

				if(flagValue==true && counter>0){

					processRecorLists1=new CountProcessRecorList(studentlist
							.size(), studentlist.size(), 0, true, new ArrayList<UnProcessedStduent>());

				}else{

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
	 * method generates the roll numbers for the students
	 *
	 * @param userId
	 *            userId of the user who is accessing the application
	 * @param code
	 *            code to be binded with student roll number
	 * @param year
	 *            session year of the university
	 * @param entityId
	 *            entity id of the entity which the student is registering
	 * @param studentId
	 *            student id of the student
	 * @throws SQLException
	 */
	private int methodUpdateRollNumbers(String userId, String code,
			String year, String entityId, String studentId,
			String programId) throws SQLException {
		int value = Integer.parseInt(code) + 1;

		String rollNumber = "";
		
		int counter = 0;

		/*
		 * the object stores the entity code from entity master
		 */
		StudentNumbersInfoGetter entityCodeObject;

		entityCodeObject = (StudentNumbersInfoGetter) sqlMapClient
				.queryForObject("studentenrollment.getentityid", entityId);

		StudentNumbersInfoGetter beanObject;
		StudentNumbersInfoGetter numbersObject = new StudentNumbersInfoGetter();

		if ((Integer.parseInt(code) >= 0) && (Integer.parseInt(code) < 999)) {
			rollNumber = String.format("%03d", value);
		} else if (Integer.parseInt(code) == 999) {
			rollNumber = "1000";
		} else if (Integer.parseInt(code) > 999) {
			rollNumber = ("" + value);
		}

		/*
		 * @param before programId
		 * @param now "All"
		 */
		beanObject = new StudentNumbersInfoGetter(entityId, "All",
				rollNumber, year, "ROL", userId);

		/*
		 * Query to update roll number in system_table_one prior using the
		 * number
		 */
		sqlMapClient.update("studentenrollment.updaterollvalue", beanObject);

		numbersObject.setRollNumber(year.substring(2)
				+ entityCodeObject.getEntityCode() + rollNumber);
		numbersObject.setStudentId(numbersObject.getStudentId());
		numbersObject.setModifierId(userId);
		numbersObject.setStudentId(studentId);
		/*
		 * Query to update roll number corresponding to studentId in temporary
		 * tables
		 */
		counter = counter + sqlMapClient.update("studentenrollment.updaterollnumbers",
				numbersObject);
		
		return counter;
		
	}

}
