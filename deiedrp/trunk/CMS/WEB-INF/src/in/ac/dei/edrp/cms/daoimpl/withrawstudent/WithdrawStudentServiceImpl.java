/**
 * @(#) WithdrawStudentServiceImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.withrawstudent;

import in.ac.dei.edrp.cms.dao.studentregistration.CancelStudentRegistrationDao;
import in.ac.dei.edrp.cms.dao.withrawstudent.WithdrawStudentService;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;
import in.ac.dei.edrp.cms.domain.studentremedial.StudentRemedialInfoGetter;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * This is Server side Implementation class for Withdraw Student
 * @author Devendra Singhal
 * @version 1.0 11
 * @date April 20 2011
 */
public class WithdrawStudentServiceImpl extends SqlMapClientDaoSupport implements
		WithdrawStudentService {

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(WithdrawStudentServiceImpl.class);
	
	/** Creating object of TransactionTemplate for transaction Management */
	TransactionTemplate transactionTemplate = null;

	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}		
		
	/** Getting Program details from Database 
	 * @param Object of StudentRemedialInfoGetter been
	 * @return List<StudentRemedialInfoGetter> list
	 **/
	@SuppressWarnings("unchecked")
	public List<StudentRemedialInfoGetter> getProgramDetail(
			StudentRemedialInfoGetter studentRemedialInfoGetter)
			throws Exception {
		List<StudentRemedialInfoGetter>list=null;
		try {
			list=getSqlMapClientTemplate().queryForList("withdrawStudent.getStudentProgramDetail",studentRemedialInfoGetter);
		} catch (Exception e) {
			logObj.error("Exception in WithdrawStudentServiceImpl: inside method getProgramDetail : "+e.getMessage());
		}
		return list;
	}
	
	/** Method to Withdraw Student
	 * @param Object of StudentRemedialInfoGetter been
	 * @return List<StudentRemedialInfoGetter> list
	 **/
	@SuppressWarnings("unchecked")
	public String withrawStudent(
			final StudentRemedialInfoGetter studentRemedialInfoGetter,final CancelStudentRegistrationDao cancelRegistrationDao)
			throws Exception {
		String message="";
		try{
			//Get next session start date and end date
			List<StudentRemedialInfoGetter>nextSessionList=getSqlMapClientTemplate().queryForList("withdrawStudent.getNextSessionDates",studentRemedialInfoGetter);
			studentRemedialInfoGetter.setNextSessionStartDate(nextSessionList.get(0).getNextSessionStartDate());
			studentRemedialInfoGetter.setNextSessionEndDate(nextSessionList.get(0).getNextSessionEndDate());			
			//Get next session semester start date and next session semester end date
			List<StudentRemedialInfoGetter>nextSessionSemesterList=getSqlMapClientTemplate().queryForList("withdrawStudent.getNextSessionSemesterDates",studentRemedialInfoGetter);
			if(nextSessionSemesterList.size()>0){
				studentRemedialInfoGetter.setAppliedStartDate(nextSessionSemesterList.get(0).getAppliedStartDate());
				studentRemedialInfoGetter.setAppliedEndDate(nextSessionSemesterList.get(0).getAppliedEndDate());				
				//Transaction start
				message=(String)transactionTemplate.execute(new TransactionCallback() {					
					public Object doInTransaction(TransactionStatus tStatus) {
						//create save point object
						Object savePoint=null;
						String msg="";
						try{
							//Initiate save point
							savePoint=tStatus.createSavepoint();
							//--------------------------------SRSH-----------------------------------------------
							//Get Attempt No. AND Status from SRSH
							List<StudentRemedialInfoGetter>srshList=getSqlMapClientTemplate().queryForList("withdrawStudent.getAttemptNoFromSRSH", studentRemedialInfoGetter);
							if(srshList.get(0).getCourseStatus().equalsIgnoreCase("REG")){//Check status in SRSH
								studentRemedialInfoGetter.setAttemptNumber(String.valueOf(srshList.get(0).getAttemptNumber()));
								//Update SRSH
								getSqlMapClientTemplate().update("withdrawStudent.UpdateSRSH", studentRemedialInfoGetter);
								//YTR Entry in SRSH						
								getSqlMapClientTemplate().insert("withdrawStudent.YTREntryInSRSH", studentRemedialInfoGetter);
								//----------------------------------END------------------------------------------------
								
								//---------------------------STUDENT PROGRAM-------------------------------------------
								//Get current semester sequence number
								String semSeq=(String)getSqlMapClientTemplate().queryForObject("withdrawStudent.getSemesterSequence", studentRemedialInfoGetter);
								StudentRemedialInfoGetter updateSPBeen=new StudentRemedialInfoGetter();
								updateSPBeen.setEnrollmentNumber(studentRemedialInfoGetter.getEnrollmentNumber());
								updateSPBeen.setRollNo(studentRemedialInfoGetter.getRollNo());
								updateSPBeen.setProgramId(studentRemedialInfoGetter.getProgramId());
								updateSPBeen.setBranchId(studentRemedialInfoGetter.getBranchId());
								updateSPBeen.setSpecializationId(studentRemedialInfoGetter.getSpecializationId());
								updateSPBeen.setEntityId(studentRemedialInfoGetter.getEntityId());
								updateSPBeen.setUserId(studentRemedialInfoGetter.getUserId());
								//For Withdraw semester one
								if(Integer.parseInt(semSeq)==1){
									updateSPBeen.setSemesterId("NAN");
								}
								else{//For Withdraw Semester Greater than one
									//Get Previous semester								
									String previousSemSeqNo=(String)getSqlMapClientTemplate().queryForObject("withdrawStudent.getPreviousSemesterSequence", studentRemedialInfoGetter);								
									updateSPBeen.setSemesterId(previousSemSeqNo);								
								}							
									//Update Student Program table
									getSqlMapClientTemplate().update("withdrawStudent.updateStudentProgram", updateSPBeen);								
								//----------------------------------END------------------------------------------------
									
									//Update Student course table							
									getSqlMapClientTemplate().update("withdrawStudent.updateStudentCourse", studentRemedialInfoGetter);
									
									//Update Student marks table
									getSqlMapClientTemplate().update("withdrawStudent.updateStudentMarks", studentRemedialInfoGetter);
									
									//Get Sequence Number from student tracking 
									String seq=(String)getSqlMapClientTemplate().queryForObject("withdrawStudent.getStudentTrackSeqNo", studentRemedialInfoGetter);
									int seqNo=1;
									if(seq==null || seq.equals("")){
										seqNo=1;
									}
									else{
										seqNo=Integer.parseInt(seq)+1;
									}
									//Set sequence number in studentRemedialInfoGetter been
									studentRemedialInfoGetter.setApplied(String.valueOf(seqNo));
									
									//Insert record in Student Tracking table to track student
									getSqlMapClientTemplate().insert("withdrawStudent.entryInStudentTracking", studentRemedialInfoGetter);
									
								//--------------------------Prestaging Table-------------------------------------------
									
									//Get Student Details from Student Master
									List<StudentInfoGetter>studentList=getSqlMapClientTemplate().queryForList("withdrawStudent.getStudentDetails", studentRemedialInfoGetter);
									//Check for Temporary Tables
									String tempCount=(String)getSqlMapClientTemplate().queryForObject("withdrawStudent.checkForTempTables", studentRemedialInfoGetter);
									//Get Probable Semester
									String probableSemester=(String)getSqlMapClientTemplate().queryForObject("withdrawStudent.getProbableSemester", studentRemedialInfoGetter);
									//Get Prestaging data
									List<StudentInfoGetter>prestagingList=getSqlMapClientTemplate().queryForList("withdrawStudent.getPrestagingDetail", studentRemedialInfoGetter);							
									StudentInfoGetter inputObj=new StudentInfoGetter();
									
									inputObj.setRollNumber(studentRemedialInfoGetter.getRollNo());
						        	inputObj.setEnrollmentNumber(studentRemedialInfoGetter.getEnrollmentNumber());					        						        
						        	inputObj.setEntityId(studentRemedialInfoGetter.getEntityId());
						        	inputObj.setProgramId(studentRemedialInfoGetter.getProgramId());
						        	inputObj.setBranchCode(studentRemedialInfoGetter.getBranchId());
						        	inputObj.setNewSpecialization(studentRemedialInfoGetter.getSpecializationId());
						        	inputObj.setProcessedFlag("yes");
						        	inputObj.setModifierId(studentRemedialInfoGetter.getUserId());
						        	//Set Semester dated for cancel registration
						        	inputObj.setSemesterStartDate(studentRemedialInfoGetter.getAppliedStartDate());					        	
						        	inputObj.setSemesterEndDate(studentRemedialInfoGetter.getSemesterStartDate());					        	
						        	inputObj.setSemesterCode(studentRemedialInfoGetter.getSemesterId());	
						        	inputObj.setCreatorId(studentRemedialInfoGetter.getUserId());
						        	
						        	inputObj.setDateOfBirth(studentList.get(0).getDateOfBirth());
						        	inputObj.setCategory(studentList.get(0).getCategory());
						        	inputObj.setGender(studentList.get(0).getGender());
						        	inputObj.setStudentId(studentList.get(0).getStudentId());
						        	inputObj.setFirstName(studentList.get(0).getFirstName());
						        	inputObj.setMiddleName(studentList.get(0).getMiddleName());
						        	inputObj.setLastName(studentList.get(0).getLastName());
						        	inputObj.setFatherFirstName(studentList.get(0).getFatherFirstName());
						        	inputObj.setFatherMiddleName(studentList.get(0).getFatherMiddleName());
						        	inputObj.setFatherLastName(studentList.get(0).getFatherLastName());
						        	inputObj.setMotherFirstName(studentList.get(0).getMotherFirstName());
						        	inputObj.setMotherMiddleName(studentList.get(0).getMotherMiddleName());
						        	inputObj.setMotherLastName(studentList.get(0).getMotherLastName());
						        	inputObj.setPrimaryEmailId(studentList.get(0).getPrimaryEmailId());
						        	
						        	inputObj.setRegisterDate(studentRemedialInfoGetter.getAppliedStartDate());
						        	inputObj.setProbableSemester(probableSemester);					        	
					        		inputObj.setAttemptNumber(prestagingList.get(0).getAttemptNumber());
						        	inputObj.setProbableAttemptNumber(String.valueOf(prestagingList.get(0).getAttemptNumber()+1));
						        	inputObj.setProbableSemesterStartDate(prestagingList.get(0).getProbableSemesterStartDate());
						        	inputObj.setProbableSemesterEndDate(prestagingList.get(0).getProbableSemesterEndDate());
						        	inputObj.setProbableRegisterDueDate(prestagingList.get(0).getProbableSemesterStartDate());
						        	
						        	if(Integer.parseInt(semSeq)==1){
						        		inputObj.setOldEntityId("NAN");
						        		inputObj.setOldProgramId("NAN");
						        		inputObj.setOldBranchId("NAN");
						        		inputObj.setOldSpecializationId("NAN");
						        		inputObj.setOldSemesterCode("NAN");
						        		inputObj.setOldRollNumber("NAN");
						        	}
						        	else{
						        		inputObj.setOldEntityId(prestagingList.get(0).getOldEntityId());
						        		inputObj.setOldProgramId(prestagingList.get(0).getOldProgramId());
						        		inputObj.setOldBranchId(prestagingList.get(0).getOldBranchCode());
						        		inputObj.setOldSpecializationId(prestagingList.get(0).getOldSpecialization());
						        		inputObj.setOldSemesterCode(prestagingList.get(0).getOldSemester());
						        		inputObj.setOldRollNumber(prestagingList.get(0).getOldRollNumber());
						        	}				       					        						        						        					      					       
									if(Integer.parseInt(tempCount)>0){//For Cancel Student Registraion				
										String str=cancelRegistrationDao.cancelRegistration(inputObj);
										if(!str.equalsIgnoreCase("success")){
											msg="CancelRegistrationError";
											logObj.error("Error during cancel registration : ");
											//Roll back Transaction
											tStatus.rollbackToSavepoint(savePoint);
										}
									}
																	
									//Set Semester dated for Insertion in perstaging table
									inputObj.setSemesterStartDate(studentRemedialInfoGetter.getAppliedStartDate());
						        	inputObj.setSemesterEndDate(studentRemedialInfoGetter.getAppliedEndDate());							
						        	//Entry in Prestaging Table
									getSqlMapClientTemplate().insert("withdrawStudent.entryInPrestaging", inputObj);
									msg="success";
							//----------------------------------END------------------------------------------------
									
							}
							else{
								msg="cantWithDrawSrshStatusREG-"+srshList.get(0).getCourseStatus();
							}
														
						}
						catch(Exception e){
							msg="error";
							logObj.error("Exception in WithdrawStudentServiceImpl: inside method withrawStudent : "+e);
							//Roll back Transaction
							tStatus.rollbackToSavepoint(savePoint);
						}
						return msg;
					}
				});
			}
			else{
				message="NoNextSemesterDateInProgramRegistrationNoYTREntrySRSH";
			}
			
		}
		catch(Exception e){
			message="error";
			logObj.error("Exception in WithdrawStudentServiceImpl: inside method withrawStudent : "+e.getMessage());
		}
		return message;
	}
	
}
