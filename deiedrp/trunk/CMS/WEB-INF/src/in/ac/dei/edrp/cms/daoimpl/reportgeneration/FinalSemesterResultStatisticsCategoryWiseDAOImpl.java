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

import in.ac.dei.edrp.cms.dao.reportgeneration.FinalSemesterResultStatisticsCategoryWiseDAO;
import in.ac.dei.edrp.cms.domain.reportgeneration.FinalSemesterResultStatisticsCategoryWise;
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
public class FinalSemesterResultStatisticsCategoryWiseDAOImpl extends
		SqlMapClientDaoSupport implements FinalSemesterResultStatisticsCategoryWiseDAO {

	private Logger loggerObject = Logger
			.getLogger(FinalSemesterResultStatisticsDAOImpl.class);

	@SuppressWarnings("unchecked")
	public List<SubjectWiseMeritList> getCurrentSession(
			SubjectWiseMeritList subjectWiseMeritList) {
		List<SubjectWiseMeritList> sessionDates = null;
		try {
			sessionDates = getSqlMapClientTemplate().queryForList(
					"finalSemesterResultStatisticsCategoryWise.getCurrentSession",
					subjectWiseMeritList);
			loggerObject.info("in getCurrentSession");
		} catch (Exception e) {
			loggerObject.error("in getCurrentSession" + e);
		}

		return sessionDates;
	}

	
	@SuppressWarnings("unchecked")
	public List<FinalSemesterResultStatisticsCategoryWise> getSessionList(
			FinalSemesterResultStatisticsCategoryWise finalSemesterResultStatisticsCategoryWise) {
		List<FinalSemesterResultStatisticsCategoryWise> sessionDateList = null;
		try {
			sessionDateList = getSqlMapClientTemplate().queryForList("finalSemesterResultStatisticsCategoryWise.getSessionList", finalSemesterResultStatisticsCategoryWise);
			loggerObject.info("in getSessionList");
		} catch (Exception e) {
			loggerObject.error("in getSessionList" + e);
		}

		return sessionDateList;
	}

	
	@SuppressWarnings("unchecked")
	public List<FinalSemesterResultStatisticsCategoryWise> getProgramDetails(
			FinalSemesterResultStatisticsCategoryWise finalSemesterResultStatisticsCategoryWise) {
		List<FinalSemesterResultStatisticsCategoryWise> programDetails = null;
		try {
			programDetails = getSqlMapClientTemplate().queryForList(
					"finalSemesterResultStatisticsCategoryWise.getProgramDetails",
					finalSemesterResultStatisticsCategoryWise);
			System.out.println("programDetails size is: "+ programDetails.size());

			loggerObject.info("in getProgramDetails");
		} catch (Exception e) {
			// loggerObject.error("in getProgramDetails" + e);
			System.out.println("in getProgramDetails" + e);
		}		
		return programDetails;
	}

	@SuppressWarnings("unchecked")
	public List<FinalSemesterResultStatisticsCategoryWise> getCompleteDetails(FinalSemesterResultStatisticsCategoryWise finalSemesterResultStatisticsCategoryWise) {

		List<FinalSemesterResultStatisticsCategoryWise> completeDetails = new ArrayList<FinalSemesterResultStatisticsCategoryWise>();
		System.out.println("program id "+finalSemesterResultStatisticsCategoryWise.getProgramId()+" branch id"+finalSemesterResultStatisticsCategoryWise.getBranchId());
		System.out.println("specialization id "+finalSemesterResultStatisticsCategoryWise.getSpecializationId()+"program course key "+finalSemesterResultStatisticsCategoryWise.getProgramCourseKey());
		System.out.println(" program course key2 "+finalSemesterResultStatisticsCategoryWise.getProgramCourseKey2()+" entity id "+finalSemesterResultStatisticsCategoryWise.getEntityId());
		System.out.println("session start date "+finalSemesterResultStatisticsCategoryWise.getSessionStartDate()+" session end date"+finalSemesterResultStatisticsCategoryWise.getSessionEndDate());
		try {
			completeDetails.addAll(getSqlMapClientTemplate().queryForList("finalSemesterResultStatisticsCategoryWise.getCompleteDetail",finalSemesterResultStatisticsCategoryWise));
			loggerObject.info("in getCompleteDetails");
		} catch (Exception e) {
			// loggerObject.error("in generatePDF" + e);
			System.out.println("in getCompleteDetails" + e);
		}
		return completeDetails;
	}	
	
	public FinalSemesterResultStatisticsCategoryWise getEntityInfo(FinalSemesterResultStatisticsCategoryWise finalSemesterResultStatisticsCategoryWise) {
		
		FinalSemesterResultStatisticsCategoryWise entityInfoObject=null;
		try{
			entityInfoObject = (FinalSemesterResultStatisticsCategoryWise) getSqlMapClientTemplate().queryForObject("finalSemesterResultStatisticsCategoryWise.getEntityInfo", finalSemesterResultStatisticsCategoryWise);
			loggerObject.info("in getEntityInfo");
		}
		catch (Exception e) {
			loggerObject.error("in getEntityInfo" + e);
		}
		return entityInfoObject;		
	}
}
