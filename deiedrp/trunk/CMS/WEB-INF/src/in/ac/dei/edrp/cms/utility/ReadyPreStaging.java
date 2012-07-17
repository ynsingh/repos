package in.ac.dei.edrp.cms.utility;

/*
 * @(#) ReadyPreStaging.java
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

import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.daoimpl.registration.prestagingdaoimpl.PreStagingTransactionFunction;
import in.ac.dei.edrp.cms.daoimpl.registration.prestagingdaoimpl.ReadyPreStagingDAOImpl;
import in.ac.dei.edrp.cms.domain.registration.prestaging.ReadyPreStagingBean;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @version 1.0 Class Description: It contains method for ReadyPreStagingDAOImpl
 *          Creation date: 17-Jan-2011
 * @author Deepak Pandey
 */

public class ReadyPreStaging {

	static final Logger logger = Logger.getLogger(ReadyPreStaging.class);

	private TransactionTemplate transactionTemplate = null;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	protected SqlMapClient sqlMapClient = null;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public ReadyPreStaging(SqlMapClient sqlMapClient,
			TransactionTemplate transactionTemplate) {
		// System.out.println("Coming inside registration function");
		this.sqlMapClient = sqlMapClient;
		this.transactionTemplate = transactionTemplate;
		// System.out.println(sqlMapClient);
	}

	public ReadyPreStaging() {

	}

	/**
	 * It counts enrollment number
	 * 
	 * @param readyPreStagingBean
	 * @return true if record is not duplicate and false if record is duplicate
	 * 
	 */
	public boolean checkDuplicateEnrollmentForSameMode(
			ReadyPreStagingBean readyPreStagingBean) {
		boolean b = true;
		List<ReadyPreStagingBean> checkDuplicate = new ArrayList<ReadyPreStagingBean>();
		try {

			if (readyPreStagingBean.getAdmissionMode().equalsIgnoreCase(
					CRConstant.NEW_MODE)) {
				checkDuplicate = (List<ReadyPreStagingBean>) sqlMapClient
						.queryForList(
								"MakeStatusReadyPreStaging.getCountForDuplicateNewEnrollment",
								readyPreStagingBean);

			}
			if (readyPreStagingBean.getAdmissionMode().equalsIgnoreCase(
					CRConstant.NORMAL_MODE)
					|| readyPreStagingBean.getAdmissionMode().equalsIgnoreCase(
							CRConstant.SWITCH_MODE)) {
				checkDuplicate = (List<ReadyPreStagingBean>) sqlMapClient
						.queryForList(
								"MakeStatusReadyPreStaging.getCountForDuplicateEnrollment",
								readyPreStagingBean);
			}
			for (ReadyPreStagingBean duplicateValue : checkDuplicate) {
				System.out.println("Registration: "
						+ readyPreStagingBean.getRollNumber() + "total"
						+ duplicateValue.getCount());
				
				if(readyPreStagingBean.getAdmissionMode().equalsIgnoreCase(
						CRConstant.NEW_MODE)){
					if (duplicateValue.getCount() > 1) {
						b = false;
					}					
				}
				if(readyPreStagingBean.getAdmissionMode().equalsIgnoreCase(
						CRConstant.NORMAL_MODE)
						|| readyPreStagingBean.getAdmissionMode().equalsIgnoreCase(
								CRConstant.SWITCH_MODE)){
					
					System.out.println("in normal & switch mode condition");
					
					if(checkDuplicate.size()>1){						
						b = false;						
					}
					
				}
				
				

			}
		} catch (Exception e) {
			System.out.println("Exception inside duplicate check "
					+ e.getMessage());
			logger.info(CRConstant.DUPLICATE_REGISTRATION_REASON + " = "
					+ readyPreStagingBean.getRollNumber());
		}

		return b;
	}

	/**
	 * This method first check for duplicate record for registration or roll
	 * number and then updates PST with required status
	 * 
	 * @param countStagingBean
	 */
	public boolean updatePSTAfterCheck(ReadyPreStagingBean countStagingBean) {
		String reasonCode = "";
		String description = "";
		String processStatus = "";
		String modifierId = countStagingBean.getModifierId();
		int x=0;
		try {
			if (!checkDuplicateEnrollmentForSameMode(countStagingBean)) {
				/*
				 * method returned false
				 */
				reasonCode = CRConstant.DUPLICATE_REGISTRATION_ROLL_NUMBER;
				description = CRConstant.DUPLICATE_REGISTRATION_REASON;
				processStatus = CRConstant.ERROR_STATUS;
				logger.info(CRConstant.DUPLICATE_REGISTRATION_REASON + " = "
						+ countStagingBean.getRollNumber());
				System.out.println("Process status duplicate for "
						+ countStagingBean.getRollNumber()
						+ " and admission mode"
						+ countStagingBean.getAdmissionMode());
			} else {
				/*
				 * method returned true
				 */
				reasonCode = "";
				description = "";
				processStatus = CRConstant.READY_STATUS;
				System.out.println("Process status RDY for "
						+ countStagingBean.getRollNumber()
						+ " and admission mode"
						+ countStagingBean.getAdmissionMode());
			}

			if (countStagingBean.getAdmissionMode().equalsIgnoreCase(
					CRConstant.NEW_MODE)) {

				ReadyPreStagingBean updateNew = new ReadyPreStagingBean(
						countStagingBean.getEntityId(), countStagingBean
								.getProgramId(), countStagingBean
								.getSemesterCode(), countStagingBean
								.getSemesterStartDate(), countStagingBean
								.getSemesterEndDate(), countStagingBean
								.getRollNumber(), countStagingBean
								.getAdmissionMode(), processStatus, reasonCode,
						description, modifierId);
				x=sqlMapClient
						.update(
								"MakeStatusReadyPreStaging.updatePSTReasonAndStatusForNEW",
								updateNew);
			}

			if (countStagingBean.getAdmissionMode().equalsIgnoreCase(
					CRConstant.NORMAL_MODE)
					|| countStagingBean.getAdmissionMode().equalsIgnoreCase(
							CRConstant.SWITCH_MODE)) {
				ReadyPreStagingBean updateNew = new ReadyPreStagingBean(
						countStagingBean.getEntityId(), countStagingBean
								.getProgramId(), countStagingBean
								.getSemesterCode(), countStagingBean
								.getSemesterStartDate(), countStagingBean
								.getSemesterEndDate(), countStagingBean
								.getEnrollmentNumber(), countStagingBean
								.getRollNumber(), countStagingBean
								.getAdmissionMode(), processStatus, reasonCode,
						description, modifierId);
				x=sqlMapClient.update(
						"MakeStatusReadyPreStaging.updatePSTReasonAndStatus",
						updateNew);
			}
			if(x>0 && (checkDuplicateEnrollmentForSameMode(countStagingBean))==true){
				return true;
			}else if(x>0 &&(checkDuplicateEnrollmentForSameMode(countStagingBean))==false){
				return false;
			}
			else{
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Exception inside PST after checking duplicate "
					+ e.getMessage());
			return false;
		}
	}
	
	
}
