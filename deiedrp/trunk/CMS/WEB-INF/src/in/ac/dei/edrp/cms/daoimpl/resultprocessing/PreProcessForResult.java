/**
 * @(#) PreProcessForResult.java
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
package in.ac.dei.edrp.cms.daoimpl.resultprocessing;

import java.util.ArrayList;
import java.util.List;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessCourseList;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessForResultBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;
import in.ac.dei.edrp.cms.domain.utility.ErrorLogs;
import in.ac.dei.edrp.cms.utility.PreviousSemesterDetail;
import in.ac.dei.edrp.cms.utility.StudentTrackingFunction;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Main Purpose of class:
 * 1- Calculates total internal and external marks for a course
 * 2- Calculates aggregate in theory and practical
 * 3- Updates student's status in course by PAS/FAL/REM
 * 4- Update student's semester status by PAS,REM,FAL
 * @author Deepak
 */
public class PreProcessForResult {

	//Object of logger class
	public static final Logger logger = Logger.getLogger(PreProcessForResult.class);
	
	
	private SqlMapClient sqlMapClient;
	private TransactionTemplate transactionTemplate;
	
	
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}



	public PreProcessForResult(SqlMapClient sqlMapClient,TransactionTemplate transactionTemplate){
		this.sqlMapClient=sqlMapClient;
		this.transactionTemplate=transactionTemplate;
	}

	public PreProcessForResult(){
		
	}
	
	/**
	 * This method prepares list of student to process
	 * @param startActivityBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public CountProcessRecorList startPreProcess(final StartActivityBean startActivityBean){
		
		ResultProcessingUtility resultProcessingUtility=new ResultProcessingUtility(sqlMapClient,transactionTemplate);
		ProcessStudentMarks processMark=new ProcessStudentMarks(sqlMapClient,transactionTemplate);
		final ProcessStudentGrade processGrade=new ProcessStudentGrade(sqlMapClient,transactionTemplate);
		
		CountProcessRecorList countList=new CountProcessRecorList();
		
		StudentTrackingFunction studentTrackingFunction=new StudentTrackingFunction(sqlMapClient,transactionTemplate);
		
		List<UnProcessedStduent> successStudentList=new ArrayList<UnProcessedStduent>();
	//	successStudentList.add(new UnProcessedStduent("Enrolment number|rollnumber",
	//			"Program Id|Semester Start Date|Semester End Date|Register Due Date|Attempt Number","Transferred into PreStaging"));
		final List<UnProcessedStduent> failStudentList=new ArrayList<UnProcessedStduent>();
	//	failStudentList.add(new UnProcessedStduent("Enrolment number|rollnumber",
	//			"Program Id|Semester Start Date|Semester End Date|Register Due Date|Attempt Number|Admission Mode","Transferred into PreStaging"));
		
		List<List<UnProcessedStduent>> list=new ArrayList<List<UnProcessedStduent>>();
		int total=0;
		int processed=0;
		int inError=0;
		List<PreProcessForResultBean> listOfStudentTOProcess=new ArrayList<PreProcessForResultBean>();
		
		try{
			
			//String userId=startActivityBean.getUserId();
			//System.out.println("Coming here before prinitng processing");
			
			final PreProcessForResultBean preProcessForResultBean=new PreProcessForResultBean(startActivityBean.getUniversityId(),startActivityBean.getEntityId(),
					startActivityBean.getProgramId(),startActivityBean.getBranchId(),startActivityBean.getSpecializationId(),startActivityBean.getSemesterCode(),
					startActivityBean.getSemesterStartDate(),startActivityBean.getSemesterEndDate(),
					startActivityBean.getSessionStartDate(),startActivityBean.getSessionEndDate(),CRConstant.ACTIVE_STATUS,CRConstant.REGISTRATION_STATUS,
					startActivityBean.getProgramCourseKey(),startActivityBean.getActivityId(),startActivityBean.getProcessId());
			
			String resultSystem=resultProcessingUtility.getResultSystem(preProcessForResultBean);
			
			final PreProcessCourseList courseListData=new PreProcessCourseList(startActivityBean.getUniversityId(),startActivityBean.getProgramCourseKey(),
					startActivityBean.getSemesterStartDate(),startActivityBean.getSemesterEndDate(),startActivityBean.getProgramId(),
					startActivityBean.getBranchId(),startActivityBean.getSpecializationId(),startActivityBean.getSemesterCode(),startActivityBean.getEntityId());
			
			if(resultSystem.equalsIgnoreCase("MK")){
				//Min passing marks
			//int minPassMarks=Integer.parseInt(getMinPassMarks(preProcessForResultBean,"MINMAR"));
			courseListData.setMinPassMarks(Integer.parseInt(resultProcessingUtility.getMinPassMarks(preProcessForResultBean,"MINMAR")));
			
			}
			
			if(resultSystem.equalsIgnoreCase("GR")){
				
			courseListData.setMinPassGrade(Integer.parseInt(resultProcessingUtility.getMinPassMarks(preProcessForResultBean,"MINGRD")));
			courseListData.setInternalWeight(Double.parseDouble(resultProcessingUtility.getMinPassMarks(preProcessForResultBean,"INTWGT"))/100.0);
			courseListData.setExternalWeight(Double.parseDouble(resultProcessingUtility.getMinPassMarks(preProcessForResultBean,"EXTWGT"))/100.0);
			
			}
			
			//prepare list of student's to process marks
			listOfStudentTOProcess=
				sqlMapClient.queryForList("preprocess.listOfStudentToProcess",preProcessForResultBean);
			total=listOfStudentTOProcess.size();
			
					//If result system !=XX, then it should be either Marks Based or Grade Based
					//If result system=XX, this program can not be processed
					
//					if(resultSystem.equalsIgnoreCase("MK")){
//						for(PreProcessForResultBean listOfStudent:listOfStudentTOProcess){
//							//processing of a student
//							// return true if all the logic runs successfully without exception ot error
//							//else false, if there is an error
//							boolean b=processMark.processStudentMarks(preProcessForResultBean,listOfStudent,courseListData,startActivityBean);
//							if(b){
//								processed++;
//							}
//							else{
//								inError++;
//								studentTrackingFunction.insertStudentErrorLogs(startActivityBean.getEntityId(),
//										startActivityBean.getProgramId(), startActivityBean
//										.getBranchId(), startActivityBean
//										.getSpecializationId(), startActivityBean
//										.getSemesterCode(), startActivityBean
//										.getSemesterStartDate(), startActivityBean
//										.getSemesterEndDate(), startActivityBean
//										.getProcessId(), startActivityBean.getActivityId(),
//										listOfStudent.getEnrollmentNumber(),listOfStudent.getRollNumber(),listOfStudent.getRollNumber(),
//										listOfStudent.getSemesterStatus(),
//										"Student with REG status only can be processed",startActivityBean.getProcessCounter());
//							}
//						}
//					}	//If Marks Ends

						//Grade Base System
						if(resultSystem.equalsIgnoreCase("GR")){
							System.out.println("Inside Grade Based System");
							
							for(final PreProcessForResultBean listOfStudent:listOfStudentTOProcess){
					//			Boolean isCompleted=(Boolean)transactionTemplate.execute(new TransactionCallback() {
							String errstatus=(String)transactionTemplate.execute(new TransactionCallback() {
									public String doInTransaction(
											TransactionStatus ts) {	
										
										Object savepoint=new Object();
										try{
											savepoint=ts.createSavepoint();
				
											if(listOfStudent.getSemesterStatus().equalsIgnoreCase("UFM")||
													listOfStudent.getSemesterStatus().equalsIgnoreCase("INC")){
												return ("UFM or INC student status is not processed");
											}

											//Only REG status students will be processed
											if(listOfStudent.getSemesterStatus().equalsIgnoreCase("REG")){

												processGrade.processStudentGrade(preProcessForResultBean,listOfStudent,courseListData,startActivityBean);
												


											}
											return ("COM");
										}catch(MyException ex){
											failStudentList.add(new UnProcessedStduent(listOfStudent.getRollNumber(),ex.getMessage()));
											ts.rollbackToSavepoint(savepoint);
											return (ex.getMessage()); }
										catch(Exception e){
											failStudentList.add(new UnProcessedStduent(listOfStudent.getRollNumber(),e.getMessage()));
									ts.rollbackToSavepoint(savepoint);
									return (e.getMessage());
									
										}

										}
									});		
							
							
								
								if(errstatus =="COM"){
									processed++;
								successStudentList.add(new UnProcessedStduent(listOfStudent.getRollNumber(),""));
								}else{
									inError++;
									studentTrackingFunction.insertStudentErrorLogs(startActivityBean.getEntityId(),
											startActivityBean.getProgramId(), startActivityBean
											.getBranchId(), startActivityBean
											.getSpecializationId(), startActivityBean
											.getSemesterCode(), startActivityBean
											.getSemesterStartDate(), startActivityBean
											.getSemesterEndDate(), startActivityBean
											.getProcessId(), startActivityBean.getActivityId(),
											listOfStudent.getEnrollmentNumber(),listOfStudent.getRollNumber(),listOfStudent.getRollNumber(),
											listOfStudent.getSemesterStatus(),
											errstatus,startActivityBean.getProcessCounter());
									
//									failStudentList.add(new UnProcessedStduent(listOfStudent.getRollNumber(),errstatus));
								}
								
								
								
								
								
								
								
								
								}
						}
					else if(resultSystem.equalsIgnoreCase("XX")||(resultSystem.equalsIgnoreCase("MK"))) {
						//This program can't be processed because It has 2 type of course i.e Grade Based and Marks Based
						inError=listOfStudentTOProcess.size();
						failStudentList.add(new UnProcessedStduent("Result System","Please correct result system of courses in program:"+startActivityBean.getProgramId()));
					}
					
		}							
							
							catch(Exception e){
			System.out.println("Exception inside this "+e.getMessage());
			logger.info("Error while computing marks for a student");
			inError=listOfStudentTOProcess.size();
			for(PreProcessForResultBean errorList:listOfStudentTOProcess){
			failStudentList.add(new UnProcessedStduent(errorList.getRollNumber(),"ERROR"+e.getMessage()));
			System.out.println("Arush"+e.getMessage());
			}			
		}
		finally{
			
			countList.setTotalRecords(total);
			countList.setCorrectProcessed(processed);
			countList.setInError(inError);
			//countList.setRejectedProcessed(total-(processed+inError));
			list.add(successStudentList);
			list.add(failStudentList);
			if(total==processed && total>0){
				countList.setActivityCompleted(true);
			}
			else{
				countList.setActivityCompleted(false);
			}
			countList.setRejStudentList(failStudentList);
		}
		
		return countList;
		
	}




}
