/**
 * @(#) UpdateActivityAndProcessMaster.java
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
package in.ac.dei.edrp.cms.daoimpl.activitymaster;

import java.util.List;

import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.daoimpl.registration.prestagingdaoimpl.TransfertNORInPSTFunction;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class UpdateActivityAndProcessMaster {

	static final Logger logger = Logger
			.getLogger(UpdateActivityAndProcessMaster.class);

	protected SqlMapClient sqlMapClient = null;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	TransactionTemplate transactionTemplate = null;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public UpdateActivityAndProcessMaster(SqlMapClient sqlMapClient,
			TransactionTemplate transactionTemplate) {
		this.sqlMapClient = sqlMapClient;
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * It updates process status and end date
	 *
	 * @param startActivityBean
	 */
	public boolean updateActivityStatus(StartActivityBean startActivityBean) {

		int maxNumber = getMaximumActivitySequence(startActivityBean);

		System.out.println(maxNumber + "maximum activity number ");

		try {
			// if activitySequence is 1 and only 1 activity is there and it is
			// completed, then process end date
			// and activity date and process status will be updated and has more
			// activity and it is completed, only activity end date will be
			// updated
			if (startActivityBean.getActivitySequence() == 1) {
				if (startActivityBean.getActivitySequence() == maxNumber) {
					if (startActivityBean.getActivityStatus().equalsIgnoreCase(
							CRConstant.COMPLETE_STATUS)) {
						sqlMapClient.update(
								"startActivity.updateProcessEndDate",
								startActivityBean);
						startActivityBean
								.setProcessStatus(CRConstant.COMPLETE_STATUS);
						sqlMapClient.update(
								"startActivity.updateProcessStatus",
								startActivityBean);
						sqlMapClient.update(
								"startActivity.updateActivityEndDate",
								startActivityBean);
					}
				} else {
					if (startActivityBean.getActivityStatus().equalsIgnoreCase(
							CRConstant.COMPLETE_STATUS)) {
						sqlMapClient.update(
								"startActivity.updateActivityEndDate",
								startActivityBean);
					}// if complete status ends
				}
			}// if activitySwquence=1 ends
			// if activitySequence is not 1, then if it is maximum, process and
			// activity end date with status will be updated
			// if it is not maximum, then only activity date will be updated
			else {
				if (startActivityBean.getActivitySequence() == maxNumber) {
					if (startActivityBean.getActivityStatus().equalsIgnoreCase(
							CRConstant.COMPLETE_STATUS)) {
						sqlMapClient.update(
								"startActivity.updateProcessEndDate",
								startActivityBean);
						sqlMapClient.update(
								"startActivity.updateProcessStatus",
								startActivityBean);
						sqlMapClient.update(
								"startActivity.updateActivityEndDate",
								startActivityBean);

					}// if status is completed

				}// if activity sequence is max number

				// activity staus will be updated in all conditions either by
				// ERR or COM
			}// else ends of activity sequence!=1

			startActivityBean.setActivityStatus(startActivityBean
					.getActivityStatus());
			sqlMapClient.update("startActivity.updateActivitySatus",
					startActivityBean);
			return true;
		} catch (Exception e) {
			System.out.println("Exception is:" + e.getMessage());
			return false;
		}

	}

	/**
	 * It returns max activitysequence for given input
	 *
	 * @param startActivityBean
	 * @return
	 */
	private int getMaximumActivitySequence(StartActivityBean startActivityBean) {
		// TODO Auto-generated method stub
		int activitySequence = 0;
		try {
			List<StartActivityBean> list = sqlMapClient.queryForList(
					"startActivity.getMaximumActivitySequence",
					startActivityBean);

			for (StartActivityBean maxValue : list) {

				activitySequence = maxValue.getActivitySequence();
			}
		} catch (Exception e) {
			System.out.println("Exception is:" + e.getMessage());
		}

		return activitySequence;
	}
}