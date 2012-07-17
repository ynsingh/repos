/**
 * @(#) PreStagingTransactionFunction.java
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
package in.ac.dei.edrp.cms.daoimpl.registration.prestagingdaoimpl;

//CHANGE REG
import java.sql.SQLException;

import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.daoimpl.registration.mastertransferdaoimpl.TransferTempIntoMaster;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ApplicantUserInfoBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.MasterTransferBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ReadyPreStagingBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.TransferNORInPSTBean;
import in.ac.dei.edrp.cms.domain.registration.prestaging.TransferPSTRDYInSTBean;
import in.ac.dei.edrp.cms.domain.utility.EmailTableBean;
import in.ac.dei.edrp.cms.domain.utility.StudentTracking;
import in.ac.dei.edrp.cms.utility.CRException;
import in.ac.dei.edrp.cms.utility.ReadyPreStaging;
import in.ac.dei.edrp.cms.utility.StudentTrackingFunction;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PreStagingTransactionFunction {

	static final Logger logger = Logger
			.getLogger(PreStagingTransactionFunction.class);

	private TransactionTemplate transactionTemplate = null;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	protected SqlMapClient sqlMapClient = null;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public PreStagingTransactionFunction(SqlMapClient sqlMapClient,
			TransactionTemplate transactionTemplate) {
		this.sqlMapClient = sqlMapClient;
		this.transactionTemplate = transactionTemplate;
	}

	public PreStagingTransactionFunction() {

	}

	/**
	 * This method executes following query: 1- Insert student in staging_table
	 * 2- Update process_status by PRC in pre staging table 3- Insert student in
	 * Applicant Info 4- If transaction is successful, it calls function to send
	 * mail
	 *
	 * @param objectToTransferInST:
	 *            It contains full information of a student from prestaging
	 *            table. It is inserted into staging table
	 * @param objectForUpdatePST:
	 *            This object updates status by PRC.
	 * @param objectToInsertInAI:
	 *            This object is inserted into Applicant_Info
	 * @return
	 */
	public String insertStudentInStaging(
			final TransferPSTRDYInSTBean objectToTransferInST,
			final TransferPSTRDYInSTBean objectForUpdatePST,
			final ApplicantUserInfoBean objectToInsertInAI,
			final EmailTableBean emailTableBean) {

		String value=(String)transactionTemplate.execute(new TransactionCallback() {
			// Object savepoint=null;

			public String doInTransaction(TransactionStatus status) {
				Object savepoint=null;
				try {
					savepoint=status.createSavepoint();
					// insert student in staging_table
					int x=sqlMapClient.update("TransferPSTRDYInST.insertStudentInST",
							objectToTransferInST);
					// update process_status of student in PreStaging_table
					int y=sqlMapClient.update(
							"TransferPSTRDYInST.updateStudentInPST",
							objectForUpdatePST);
					// insert student in Applicant_Info
					if (objectToTransferInST.getAdmissionMode()
							.equalsIgnoreCase(CRConstant.NORMAL_MODE)
							|| objectToTransferInST.getAdmissionMode()
									.equalsIgnoreCase(CRConstant.SWITCH_MODE)) {
						System.out
								.println("Inserting student inside applicant info");
						sqlMapClient.insert(
								"TransferPSTRDYInST.insertInApplicantInfo",
								objectToInsertInAI);
					} 
					//This code is being commented for sometime only. Once it will be dicided, how student will 
					//be ACT again these line of code will be opened.
					//Issue is: who will update INA-->ACT in applicant info.
					//According to me flow must be: once the mail has to be sent, that flag will be made 'ACT'
//					else {
//						if (objectToTransferInST.getAdmissionMode()
//								.equalsIgnoreCase(CRConstant.NEW_MODE)) {
//							sqlMapClient.insert(
//									"emailTable.insertIntoEMailTable",
//									emailTableBean);
//						}
//					}
					if(y==1){
						return "success";
					}
					else{
						throw new Exception();
					}

				} catch (DataAccessException e) {
					logger
					.info("student not sucessfully transferred into staging_table and applicant_info. Roll number:"+e.getMessage());
					status.rollbackToSavepoint(savepoint);
					return "Database updation failed  Exception :"+e;
				} catch (Exception e) {
					logger
					.info("student not sucessfully transferred into staging_table and applicant_info. Roll number:"+e.getMessage());
					status.rollbackToSavepoint(savepoint);
					return "Database updation failed  Exception :"+e;
				}
			}//

		});
		return value;
	}

	/**
	 * This method executes following query: 1- Insert Student with Pass and
	 * Fail status in prestaging_table 2- Insert student with status YTR in
	 * student registration semester header 3- Update student process status by
	 * LRG in student registration semester header
	 *
	 * @param passOrFailPSTData:
	 *            Object contains full details which is required to insert in
	 *            PreStaging table
	 */
	public String executeQueryForPreStaging(
			final TransferNORInPSTBean passOrFailPSTData) {
		// Connection con=DBConnection.getConnection();

		String s=(String)transactionTemplate.execute(new TransactionCallback() {
			// Object savepoint=null;

			
			public String doInTransaction(TransactionStatus status) {
				Object savepoint=null;
				
				try {
					savepoint=status.createSavepoint();
					// Insert suudent with its status in semester either
					// PASS/FAIL
					sqlMapClient.update(
							"TransferNORInPSTBean.insertPSTPassOrFail",
							passOrFailPSTData);

					// Update student process status with LRG into SRSH
					int updateSRSH=sqlMapClient.update(
							"TransferNORInPSTBean.updateSRSHWithLRG",
							passOrFailPSTData);

					if(updateSRSH==1){
						return "success";
					}
					else{
						throw new Exception();
					}
					
				} catch (DataAccessException e) {
					status.rollbackToSavepoint(savepoint);
					logger.error("A "+e);
					return "Database updation failed  Exception :"+e;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					status.rollbackToSavepoint(savepoint);
					logger.error("A "+e);
					return "Database updation failed  Exception :"+e;
				}
			}//

		});
		return s;
	}

	/**
	 * This method executes following query: 1- Insert Student with Pass and
	 * Fail status in prestaging_table 2- Insert student with status YTR in
	 * student registration semester header 3- Update student process status by
	 * LRG in student registration semester header
	 *
	 * @param remedialPSTData:
	 *            student having REM status with its full details
	 */
	public String executeQueryForRemedialPreStaging(
			final TransferNORInPSTBean remedialPSTData) {
		 
		String s=(String)transactionTemplate.execute(new TransactionCallback() {
	
			public String doInTransaction(TransactionStatus status) {
				Object savepoint=null;
				String b="fail";
				try {
					 savepoint=status.createSavepoint();
					// Insert student having status REM in prestaging
					int insertPST=sqlMapClient.update(
							"TransferNORInPSTBean.insertPSTRemedial",
							remedialPSTData);
                     System.out.println(insertPST +"check rohit that coming or not");
					// Update student process status with LRG into SRSH s
					int updateSRSH = sqlMapClient.update(
							"TransferNORInPSTBean.updateSRSHWithLRG",
							remedialPSTData);
					
					if(updateSRSH==1){
						return "success";
					}
					else{
						
						System.out.println("rohit throwing exception");
						throw new Exception();
					}
				} catch (DataAccessException e) {
					// System.out.println("before save anil inside data
					// access="+status.isNewTransaction());
					// Set transaction status to rollback
					status.rollbackToSavepoint(savepoint);
					logger.error("A "+e);
					return e.getMessage();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					status.rollbackToSavepoint(savepoint);
					logger.error("B "+e);
					return e.getMessage();
					// e.printStackTrace();
				}
			}//
		
		});
	//	System.out.println("before return: "+ s);
		return s;
	}

	/**
	 *
	 * @param updateNormalWithSwitch
	 * @param updateSwitchWithNormal
	 * @param updateSRSH
	 * @param b
	 * @return
	 */
	public String insertNORWithSWTInPST(
			final ReadyPreStagingBean updateNormalWithSwitch,
			final ReadyPreStagingBean updateSwitchWithNormal,
			final ReadyPreStagingBean updateSRSH, final boolean b) {

		
			String value=(String)transactionTemplate.execute(new TransactionCallback() {
				// Object savepoint=null;
				ReadyPreStaging readyPreStaging = new ReadyPreStaging(
						sqlMapClient, transactionTemplate);

				
				public String doInTransaction(
						TransactionStatus status) {
					Object savepoint=null;
					try {
						savepoint=status.createSavepoint();
						int x=sqlMapClient.update("MakeStatusReadyPreStaging.updatePSTReasonAndStatus",updateNormalWithSwitch);

						int y=sqlMapClient.update("MakeStatusReadyPreStaging.updatePSTReasonAndStatus",updateSwitchWithNormal);

						//This commented code was written because in previous requirement, we are making status YTR to INA in SRH table,
						//If that student has NOR and SWT entry.
//						if (b == true) {
//
//							sqlMapClient.update("updateSRSHStatus", updateSRSH);
//							StudentTracking studentTracking = new StudentTracking(
//									updateNormalWithSwitch.getEntityId(),
//									updateNormalWithSwitch.getRollNumber(),
//									updateNormalWithSwitch
//											.getProgramCourseKey(),
//									updateNormalWithSwitch
//											.getSemesterStartDate(),
//									updateNormalWithSwitch.getSemesterEndDate(),
//									updateNormalWithSwitch
//											.getSessionStartDate(),
//									updateNormalWithSwitch.getSessionEndDate(),
//									CRConstant.INACTIVE_STATUS,
//									CRConstant.ACTIVE_STATUS,
//									updateNormalWithSwitch.getModifierId(),
//									updateNormalWithSwitch.getActivityId(),
//									"REG");
//							int sequence = new StudentTrackingFunction(
//									sqlMapClient, transactionTemplate)
//									.getStdentTrackingIssue(studentTracking);
//
//							studentTracking.setSequenceNumber(sequence + 1);
//							sqlMapClient
//									.update(
//											"studentTrackingAndLogs.insertStudentTracking",
//											studentTracking);
//
//							studentTracking = new StudentTracking(
//									updateSwitchWithNormal.getEntityId(),
//									updateSwitchWithNormal.getRollNumber(),
//									updateSwitchWithNormal
//											.getProgramCourseKey(),
//									updateSwitchWithNormal
//											.getSemesterStartDate(),
//									updateSwitchWithNormal.getSemesterEndDate(),
//									updateSwitchWithNormal
//											.getSessionStartDate(),
//									updateSwitchWithNormal.getSessionEndDate(),
//									CRConstant.YET_TO_REGISTER,
//									CRConstant.ACTIVE_STATUS,
//									updateSwitchWithNormal.getModifierId(),
//									updateSwitchWithNormal.getActivityId(),
//									"REG");
//							sequence = new StudentTrackingFunction(
//									sqlMapClient, transactionTemplate)
//									.getStdentTrackingIssue(studentTracking);
//
//							studentTracking.setSequenceNumber(sequence + 1);
//							sqlMapClient
//									.update(
//											"studentTrackingAndLogs.insertStudentTracking",
//											studentTracking);
//						}
						if(x==1&&y==1){
							return "success";
						}
						else{
							throw new Exception();
						}

					} catch (DataAccessException e) {
						status.rollbackToSavepoint(savepoint);
						return "Database updation failed  Exception :"+e;
					} catch (Exception e) {
						status.rollbackToSavepoint(savepoint);
						return "Database updation failed  Exception :"+e;
					}
				}//

			});
		
		
	return value;
}//Method Make RDY/INA prestaging ends
	
}//class ends
