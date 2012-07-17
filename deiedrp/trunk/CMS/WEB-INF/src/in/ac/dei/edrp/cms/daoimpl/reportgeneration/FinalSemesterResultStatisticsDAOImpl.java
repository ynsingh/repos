/**
 * @(#) FinalSemesterResultStatisticsDAOImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.reportgeneration;

import in.ac.dei.edrp.cms.dao.reportgeneration.FinalSemesterResultStatisticsDAO;
import in.ac.dei.edrp.cms.domain.reportgeneration.FinalSemesterResultStatistics;
import in.ac.dei.edrp.cms.domain.reportgeneration.SubjectWiseMeritList;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This file consist of the methods used at the Activity Master process.
 * 
 * @author Ankit Jain
 * @date 25 Jan 2011
 * @version 1.0
 */
public class FinalSemesterResultStatisticsDAOImpl extends
		SqlMapClientDaoSupport implements FinalSemesterResultStatisticsDAO {

	private Logger loggerObject = Logger
			.getLogger(FinalSemesterResultStatisticsDAOImpl.class);

	@SuppressWarnings("unchecked")
	public List<SubjectWiseMeritList> getCurrentSession(
			SubjectWiseMeritList subjectWiseMeritList) {
		List<SubjectWiseMeritList> sessionDates = null;
		try {
			sessionDates = getSqlMapClientTemplate().queryForList(
					"finalSemesterResultStatistics.getCurrentSession",
					subjectWiseMeritList);
			loggerObject.info("in getCurrentSession");
		} catch (Exception e) {
			loggerObject.error("in getCurrentSession" + e);
		}

		return sessionDates;
	}

	
	@SuppressWarnings("unchecked")
	public List<FinalSemesterResultStatistics> getSessionList(
			FinalSemesterResultStatistics finalSemesterResultStatistics) {
		List<FinalSemesterResultStatistics> sessionDateList = null;
		try {
			sessionDateList = getSqlMapClientTemplate().queryForList("finalSemesterResultStatistics.getSessionList", finalSemesterResultStatistics);
			loggerObject.info("in getSessionList");
		} catch (Exception e) {
			loggerObject.error("in getSessionList" + e);
		}

		return sessionDateList;
	}

	
	@SuppressWarnings("unchecked")
	public List<FinalSemesterResultStatistics> getProgramDetails(
			FinalSemesterResultStatistics finalSemesterResultStatistics) {
		List<FinalSemesterResultStatistics> programDetails = null;
		try {
			programDetails = getSqlMapClientTemplate().queryForList(
					"finalSemesterResultStatistics.getProgramDetails",
					finalSemesterResultStatistics);
			System.out.println("programDetails size is: "+ programDetails.size());

			loggerObject.info("in getProgramDetails");
		} catch (Exception e) {
			// loggerObject.error("in getProgramDetails" + e);
			System.out.println("in getProgramDetails" + e);
		}		
		return programDetails;
	}

	@SuppressWarnings("unchecked")
	public List<FinalSemesterResultStatistics> getCompleteDetails(
			FinalSemesterResultStatistics finalSemesterResultStatistics) {

		List<FinalSemesterResultStatistics> completeDetails = new ArrayList<FinalSemesterResultStatistics>();
		System.out.println("program id "+finalSemesterResultStatistics.getProgramId()+" branch id"+finalSemesterResultStatistics.getBranchId());
		System.out.println("specialization id "+finalSemesterResultStatistics.getSpecializationId()+"program course key "+finalSemesterResultStatistics.getProgramCourseKey());
		System.out.println(" program course key2 "+finalSemesterResultStatistics.getProgramCourseKey2()+" entity id "+finalSemesterResultStatistics.getEntityId());
		System.out.println("session start date "+finalSemesterResultStatistics.getSessionStartDate()+" session end date"+finalSemesterResultStatistics.getSessionEndDate());
		try {
			completeDetails
					.addAll(getSqlMapClientTemplate()
							.queryForList(
									"finalSemesterResultStatistics.getCompleteDetail",
									finalSemesterResultStatistics));
			loggerObject.info("in getCompleteDetails");
		} catch (Exception e) {
			// loggerObject.error("in generatePDF" + e);
			System.out.println("in getCompleteDetails" + e);
		}
		return completeDetails;
	}
	
	public FinalSemesterResultStatistics getEntityInfo(FinalSemesterResultStatistics finalSemesterResultStatistics) {
		
		FinalSemesterResultStatistics entityInfoObject=null;
		try{
			entityInfoObject = (FinalSemesterResultStatistics) getSqlMapClientTemplate().queryForObject("finalSemesterResultStatistics.getEntityInfo", finalSemesterResultStatistics);
			loggerObject.info("in getEntityInfo");
		}
		catch (Exception e) {
			loggerObject.error("in getEntityInfo" + e);
		}
		return entityInfoObject;		
	}
}
