/*
 * @(#) ConsolidatedChartImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.consolidatedchart;

import in.ac.dei.edrp.cms.dao.consolidatedchart.ConsolidatedChartService;
import in.ac.dei.edrp.cms.daoimpl.resultprocessing.ResultProcessingUtility;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedChartBean;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ResultStatisticsInfo;
import in.ac.dei.edrp.cms.domain.coursemaster.CourseMasterBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.PreProcessForResultBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentNumbersInfoGetter;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * this is Server side Implementation class for consolidated chart and result
 * statistics
 * 
 * @version 1.0 8 September 2011
 * @author MOHD AMIR
 */
public class ConsolidatedChartImpl extends SqlMapClientDaoSupport implements
		ConsolidatedChartService {

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(ConsolidatedChartImpl.class);

	/** Creating object of TransactionTemplate for transaction management */
	private TransactionTemplate transactionTemplate;

	/** Defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * This method get entity list from database
	 * 
	 * @param courseMasterBean
	 *            , object of bean class CourseMasterBean
	 * @return entityList,List of type ConsolidatedChartBean containing entities
	 */
	@SuppressWarnings("unchecked")
	public List<ConsolidatedChartBean> getEntityList(
			CourseMasterBean courseMasterBean) {
		List<ConsolidatedChartBean> entityList = new ArrayList<ConsolidatedChartBean>();
		try {
			entityList = getSqlMapClientTemplate().queryForList(
					"consolidatedchart.getEntityList", courseMasterBean);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return entityList;
	}

	/**
	 * This method get consolidated chart data from database
	 * 
	 * @param chartBean
	 *            , object of bean class ConsolidatedChartBean
	 * @return dataList,List of type ConsolidatedChartBean containing
	 *         consolidated chart data
	 */
	@SuppressWarnings("unchecked")
	public List<ConsolidatedChartBean> getChartData(
			ConsolidatedChartBean chartBean) {
		List<ConsolidatedChartBean> dataList = new ArrayList<ConsolidatedChartBean>();
		try {
			StudentNumbersInfoGetter keys = new StudentNumbersInfoGetter();
			keys.setProgramId(chartBean.getProgramCode());
			keys.setBranchId(chartBean.getBranchCode());
			keys.setSpecializationId(chartBean.getSpecializationCode());
			keys.setSemesterCode(chartBean.getSemesterCode());

			keys = (StudentNumbersInfoGetter) getSqlMapClientTemplate()
					.queryForObject("studentenrollment.getprogramcoursekey",
							keys);

			PreProcessForResultBean preProcessForResultBean = new PreProcessForResultBean();
			preProcessForResultBean.setProgramCourseKey(keys
					.getProgramCourseKey());
			String resultSystem = (new ResultProcessingUtility(
					getSqlMapClient(), transactionTemplate))
					.getResultSystem(preProcessForResultBean);
			dataList = getSqlMapClientTemplate().queryForList(
					"consolidatedchart.getChartData", chartBean);

			for (int i = 0; i < dataList.size(); i++) {
				dataList.get(i).setResultSystem(resultSystem);
			}
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return dataList;
	}

	/**
	 * This method get previous semester percentage from database
	 * 
	 * @param chartBean
	 *            , object of bean class ConsolidatedChartBean
	 * @return previousSemesterPercentage,List of type ConsolidatedChartBean
	 *         containing previous semester percentage
	 */
	@SuppressWarnings("unchecked")
	public List<ConsolidatedChartBean> getPreviousSemesterPercentage(
			ConsolidatedChartBean chartBean) {
		List<ConsolidatedChartBean> previousSemesterPercentage = new ArrayList<ConsolidatedChartBean>();
		try {
			previousSemesterPercentage = getSqlMapClientTemplate()
					.queryForList("consolidatedchart.getPreviousSemPercentage",
							chartBean);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return previousSemesterPercentage;
	}

	/**
	 * This method semester and sequence number from database
	 * 
	 * @param chartBean
	 *            , object of bean class ConsolidatedChartBean
	 * @return semSeqList,List of type ConsolidatedChartBean containing semester
	 *         and sequence number
	 */
	@SuppressWarnings("unchecked")
	public List<ConsolidatedChartBean> getSemesterAndSeqNo(
			ConsolidatedChartBean chartBean) {
		List<ConsolidatedChartBean> semSeqList = new ArrayList<ConsolidatedChartBean>();
		try {
			semSeqList = getSqlMapClientTemplate().queryForList(
					"consolidatedchart.getSemSeqNo", chartBean);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return semSeqList;
	}

	/**
	 * This method get program branch specialization details for an entity from
	 * database
	 * 
	 * @param resultStatisticsInfo
	 *            , object of bean class ResultStatisticsInfo
	 * @return pbsList,List of type ResultStatisticsInfo containing program
	 *         branch specialization details
	 */
	@SuppressWarnings("unchecked")
	public List<ResultStatisticsInfo> getPBSDetails(
			ResultStatisticsInfo resultStatisticsInfo) {
		List<ResultStatisticsInfo> pbsList = new ArrayList<ResultStatisticsInfo>();
		try {
			pbsList = getSqlMapClientTemplate().queryForList(
					"consolidatedchart.getPBSInfo", resultStatisticsInfo);
		} catch (Exception e) {
			logObj.error(e.getMessage());
			System.out.println(e.getMessage());
		}
		return pbsList;
	}

	/**
	 * This method get student count from database
	 * 
	 * @param resultStatisticsInfo
	 *            , object of bean class ResultStatisticsInfo
	 * @return count,Number of student
	 */
	@SuppressWarnings("unchecked")
	public String getStudentCount(ResultStatisticsInfo resultStatisticsInfo) {
		String count = "0";
		try {
			List<ResultStatisticsInfo> countList = getSqlMapClientTemplate()
					.queryForList("consolidatedchart.getStudentCountInfo",
							resultStatisticsInfo);
			if (countList.size() > 0) {
				count = countList.get(0).getCount();
			}
		} catch (Exception e) {
			logObj.error(e.getMessage());
			System.out.println(e.getMessage());
		}
		return count;
	}

	/**
	 * This method get student count by division from database
	 * 
	 * @param resultStatisticsInfo
	 *            , object of bean class ResultStatisticsInfo
	 * @return count,Number of student
	 */
	@SuppressWarnings("unchecked")
	public String getStudentCountByDivision(
			ResultStatisticsInfo resultStatisticsInfo) {
		String count = "0";
		try {
			List<ResultStatisticsInfo> countList = getSqlMapClientTemplate()
					.queryForList("consolidatedchart.getStudentCountByDiv",
							resultStatisticsInfo);
			if (countList.size() > 0) {
				count = countList.get(0).getCount();
			}
		} catch (Exception e) {
			logObj.error(e.getMessage());
			System.out.println(e.getMessage());
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public String getStudentCountByCategoryAndGender(
			ResultStatisticsInfo resultStatisticsInfo) {
		String count = "0";
		try {
			List<ResultStatisticsInfo> countList = getSqlMapClientTemplate()
					.queryForList("registrationStats.getStudentCount",
							resultStatisticsInfo);
			count = countList.get(0).getCount();
		} catch (Exception e) {
			logObj.error(e.getMessage());
			System.out.println(e.getMessage());
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<ResultStatisticsInfo> getEntityLevelList(
			ResultStatisticsInfo resultStatisticsInfo) {
		List<ResultStatisticsInfo> dataList = new ArrayList<ResultStatisticsInfo>();
		try {
			dataList = getSqlMapClientTemplate().queryForList(
					"registrationStats.getEntityLevel", resultStatisticsInfo);
		} catch (Exception e) {
			logObj.error(e.getMessage());
			System.out.println(e.getMessage());
		}
		return dataList;
	}
}