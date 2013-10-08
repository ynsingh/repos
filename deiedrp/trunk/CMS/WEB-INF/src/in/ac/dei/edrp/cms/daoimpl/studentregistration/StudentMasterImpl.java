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

					String EnrollSeqNo = null;

					
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
						String year = (activityMasterBean.getSemesterStartDate().substring(0, 4));
						String month=(activityMasterBean.getSemesterStartDate().substring(5, 7));
						ActivityMasterBean inputBean=new ActivityMasterBean();
						inputBean.setType("ENROL");
						inputBean.setUniversityId(activityMasterBean.getUniversityId());
						List<ActivityMasterBean>formatList=sqlMapClient.queryForList("studentenrollment.getRollFormatRecord", inputBean);
						int diff=4;	//Default	
						for(int i=0;i<formatList.size();i++){
							if(formatList.get(i).getFormat().equals("SN")){
								diff=(formatList.get(i).getToPosition()-formatList.get(i).getFromPosition())+1;
							}					
						}
						StudentNumbersInfoGetter temoSysObj = (StudentNumbersInfoGetter) sqlMapClient.queryForObject("studentenrollment.sysvalue",systemvalue);
						int tempValue = Integer.parseInt(temoSysObj.getSystemValue());
						String tempSeqNo=String.format("%0"+diff+"d", tempValue);						
						systemvalue.setSystemValue(tempSeqNo);
						sqlMapClient.update("studentenrollment.updatesysvalue",systemvalue);
						String entityId=activityMasterBean.getEntityId().toString();
						//Get Entity code for particular entity id
						StudentNumbersInfoGetter entityCodeObject= (StudentNumbersInfoGetter) sqlMapClient.queryForObject("studentenrollment.getentityid",entityId );
						/*
						 * to get the students with no enrollment numbers &
						 * admission_mode=NEW
						 */
						studentlist = sqlMapClient.queryForList("studentenrollment.getlistforenrollments",beanobject);
						Iterator iteratorObject = studentlist.iterator();
						while (iteratorObject.hasNext()) {
							StudentInfoGetter listObject = (StudentInfoGetter) iteratorObject.next();

							/*
							 * to get the value for the code for enrollment
							 * number generation
							 */
							sysObject = (StudentNumbersInfoGetter) sqlMapClient.queryForObject("studentenrollment.sysvalue",systemvalue);

							int value = Integer.parseInt(sysObject.getSystemValue()) + 1;
							EnrollSeqNo = String.format("%0"+diff+"d", value);
							systemvalue.setSystemValue(EnrollSeqNo);

							/*
							 * update the value for the code for enrollment
							 * number generation
							 */
							sqlMapClient.update("studentenrollment.updatesysvalue",systemvalue);
							String enrollmentNumber="";
							int index=0;
							for(int i=0;i<formatList.size();i++){
								if(formatList.get(i).getFromPosition()==index+1){
									if(formatList.get(i).getFormat().equals("YY")){
										enrollmentNumber=enrollmentNumber+year.substring(2);
									}
									else if(formatList.get(i).getFormat().equals("YYYY")){
										enrollmentNumber=enrollmentNumber+year;
									}
									else if(formatList.get(i).getFormat().equals("SN")){
										enrollmentNumber=enrollmentNumber+EnrollSeqNo;
									}
									else if(formatList.get(i).getFormat().equals("ENTCD")){
										enrollmentNumber=enrollmentNumber+entityCodeObject.getEntityCode();
									}
									else if(formatList.get(i).getFormat().equals("MM")){
										enrollmentNumber=enrollmentNumber+month;
									}
									else{
										enrollmentNumber=enrollmentNumber+formatList.get(i).getFormat();
									}
									index=formatList.get(i).getToPosition();
								}
							}
							
							listObject.setEnrollmentNumber(enrollmentNumber);
							listObject.setStudentId(listObject.getStudentId());
							listObject.setStatus("V");
							listObject.setUserId(activityMasterBean.getUserId());

							/*
							 * to update the enrollment number in temporary
							 * tables for student
							 */
							counter =counter + sqlMapClient.update("studentenrollment.updateenrollmentstables",listObject);
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
	@SuppressWarnings("unchecked")
	public CountProcessRecorList generateRollNumbers(
			final ActivityMasterBean activityMasterBean) {

		final CountProcessRecorList processRecorLists = (CountProcessRecorList)transactionTemplate.execute(new TransactionCallback() {

			CountProcessRecorList processRecorLists1 = new CountProcessRecorList();

			Object savepoint = null;
		
			public CountProcessRecorList doInTransaction(
					TransactionStatus status) {

				boolean flagValue=true;
				String CODE = "ROL";
				String year = (activityMasterBean.getSessionStartDate())
						.toString().substring(0, 4);
				String month=(activityMasterBean.getSessionStartDate())
				.toString().substring(5, 7);
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
				StudentNumbersInfoGetter systemObject = null;

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
					entitysystemvalue.setEntityId(activityMasterBean.getEntityId());
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
					entitylistObject = (StudentNumbersInfoGetter) sqlMapClient.queryForObject("studentenrollment.checkvalues",entitysystemvalue);
					ActivityMasterBean inputBean=new ActivityMasterBean();
					inputBean.setType("ROL");
					inputBean.setUniversityId(activityMasterBean.getUniversityId());
					List<ActivityMasterBean>formatList=sqlMapClient.queryForList("studentenrollment.getRollFormatRecord", inputBean);
					int diff=3;	//Default	
					for(int i=0;i<formatList.size();i++){
						if(formatList.get(i).getFormat().equals("SN")){
							diff=(formatList.get(i).getToPosition()-formatList.get(i).getFromPosition())+1;
						}					
					}
					String tempSeqNo=String.format("%0"+diff+"d", 0);	
					boolean flag=false;
					if (entitylistObject == null) {
						flag=true;
						/*
						 * modified 
						 * @param before activityMasterBean.getProgramId()
						 * @param now "All"							
						 */						
						StudentNumbersInfoGetter insertbeanobject = new StudentNumbersInfoGetter(
								activityMasterBean.getEntityId(),
								"All", CODE, year,
								activityMasterBean.getUserId());
						insertbeanobject.setSystemValue(tempSeqNo);

						/*
						 * insert a record in system_table_one if no record
						 * exist for the entity
						 */
						sqlMapClient.insert("studentenrollment.insertprogramsystemvalue",insertbeanobject);
					} else if (entitylistObject.getStatusFlag()
							.equalsIgnoreCase("U")) {
						entitysystemvalue.setStatusFlag("L");

						/*
						 * if record found then update flag status before
						 * generating roll numbers for the students
						 */
						sqlMapClient.update("studentenrollment.updateentitysatusflag",entitysystemvalue);				
						entitysystemvalue.setSystemValue(String.format("%0"+diff+"d", Integer.parseInt(entitylistObject.getSystemValue())));
						entitysystemvalue.setCode(CODE);
						sqlMapClient.update("studentenrollment.updaterollvalue",entitysystemvalue);
						System.out.println("After update value in system table one");
					} else if (entitylistObject.getStatusFlag()
							.equalsIgnoreCase("L")) {

						flagValue=false;

					}

					/*
					 * to get the list of students(for NEW & SWT mode) for roll
					 * number generation
					 */
					studentlist = sqlMapClient.queryForList("studentenrollment.getlistofstudentsforrolls",beanobject);
					//Check Roll Number Group Code in temp student program Added by Devendra
					int rollNoGroupCodeStatus=(Integer)sqlMapClient.queryForObject("studentenrollment.checkRollNumberGroupCode",beanobject);					
					
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
						
						List<String>tempList=new ArrayList<String>();//Added By Devendra
						
						
						int num=0;
						StudentNumbersInfoGetter bean=new StudentNumbersInfoGetter();
						bean.setUniversityId(activityMasterBean.getUniversityId());
						bean.setCode("ROLLBF");
						StudentNumbersInfoGetter input=(StudentNumbersInfoGetter) sqlMapClient.queryForObject("studentenrollment.sysvalue",bean);
						if(!flag){						
							num=Integer.parseInt(input.getSystemValue());
						}
						
						while (iteratorObject.hasNext()) {
							StudentInfoGetter numbersObject = (StudentInfoGetter) iteratorObject
									.next();							
							if(rollNoGroupCodeStatus==0){
								/*
								 * get the system value for the program from
								 * system_table_one
								 */
								systemObject = (StudentNumbersInfoGetter) sqlMapClient.queryForObject("studentenrollment.sysvalueforroll",systemvalue);
							}
							else{							
								if(tempList.indexOf(numbersObject.getRollNumberGroupCode())<0){	
									if(tempList.size()>0){
										num=Integer.parseInt(input.getSystemValue());
									}
									tempList.add(numbersObject.getRollNumberGroupCode());											
									systemObject = (StudentNumbersInfoGetter) sqlMapClient.queryForObject("studentenrollment.sysvalueforroll",systemvalue);
									systemObject.setSystemValue(String.valueOf(Integer.parseInt(systemObject.getSystemValue())+num));
								}
								else{										
									systemObject = (StudentNumbersInfoGetter) sqlMapClient.queryForObject("studentenrollment.sysvalueforroll",systemvalue);
								}
							}
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
										activityMasterBean.getProgramId(),formatList,month);

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
											activityMasterBean.getProgramId(),formatList,month);
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
			String programId,List<ActivityMasterBean>formatList,String month) throws SQLException {
		
		String formatStr="%0"+code.length()+"d";		
		int value = Integer.parseInt(code) + 1;
		String seqNo = "";		
		int counter = 0;

		/*
		 * the object stores the entity code from entity master
		 */
		StudentNumbersInfoGetter entityCodeObject;

		entityCodeObject = (StudentNumbersInfoGetter) sqlMapClient
				.queryForObject("studentenrollment.getentityid", entityId);

		StudentNumbersInfoGetter beanObject;
		StudentNumbersInfoGetter numbersObject = new StudentNumbersInfoGetter();

		seqNo = String.format(formatStr, value);		

		/*
		 * @param before programId
		 * @param now "All"
		 */
		beanObject = new StudentNumbersInfoGetter(entityId, "All",
				seqNo, year, "ROL", userId);

		/*
		 * Query to update roll number in system_table_one prior using the
		 * number
		 */
		sqlMapClient.update("studentenrollment.updaterollvalue", beanObject);
		String rollNumber="";
		int index=0;
		for(int i=0;i<formatList.size();i++){
			if(formatList.get(i).getFromPosition()==index+1){
				if(formatList.get(i).getFormat().equals("YY")){
					rollNumber=rollNumber+year.substring(2);
				}
				else if(formatList.get(i).getFormat().equals("YYYY")){
					rollNumber=rollNumber+year;
				}
				else if(formatList.get(i).getFormat().equals("SN")){
					rollNumber=rollNumber+seqNo;
				}
				else if(formatList.get(i).getFormat().equals("ENTCD")){
					rollNumber=rollNumber+entityCodeObject.getEntityCode();
				}
				else if(formatList.get(i).getFormat().equals("MM")){
					rollNumber=rollNumber+month;
				}
				else{
					rollNumber=rollNumber+formatList.get(i).getFormat();
				}
				index=formatList.get(i).getToPosition();
			}
		}
		numbersObject.setRollNumber(rollNumber);
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
