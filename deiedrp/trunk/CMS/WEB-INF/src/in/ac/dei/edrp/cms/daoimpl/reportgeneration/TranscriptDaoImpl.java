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

import in.ac.dei.edrp.cms.dao.reportgeneration.TranscriptDao;
import in.ac.dei.edrp.cms.domain.reportgeneration.FinalSemesterResultStatisticsCategoryWise;
import in.ac.dei.edrp.cms.domain.reportgeneration.SubjectWiseMeritList;
import in.ac.dei.edrp.cms.domain.reportgeneration.Transcript;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This file consist of the methods used at the Activity Master process.
 * 
 * @author Nupur Dixit
 * @date 02 Dec 2011
 * @version 1.0
 */
public class TranscriptDaoImpl extends SqlMapClientDaoSupport implements TranscriptDao {

	private Logger loggerObject = Logger
			.getLogger(TranscriptDaoImpl.class);

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


	@SuppressWarnings("unchecked")
	public List<Transcript> getAllProgramCourseKey(Transcript transcript) {
		List<Transcript> programCourseKeyDetails = null;
		try {
			programCourseKeyDetails = getSqlMapClientTemplate().queryForList("transcript.getProgramCourseKey",transcript);
			System.out.println("programCourseKeyDetails size is: "+ programCourseKeyDetails.size());
			loggerObject.info("in programCourseKeyDetails");
		} catch (Exception e) {			
			System.out.println("in getAllProgramCourseKey" + e);
		}		
		return programCourseKeyDetails;
	}
	
	
	public Transcript getProgramName(Transcript prgCourseKey) {
		
		Transcript transcript = (Transcript) getSqlMapClientTemplate().queryForObject("transcript.getProgramName",prgCourseKey);
		if(transcript.getProgramName().length()==0){
			transcript.setProgramName("none");
		}		
		return transcript;
	}


	public Transcript getSgpaCgpa(Transcript prgCourseKey) {
//		Transcript transcript = new Transcript();
		Transcript transcript = (Transcript) getSqlMapClientTemplate().queryForObject("transcript.getSgpaCgpa",prgCourseKey);
		if(transcript==null){
			transcript = new Transcript();
			transcript.setSgpa(99);
		}
		System.out.println("sgpa "+transcript.getSgpa());
//		if(transcript.getSgpa())		
		return transcript;
	}


	public List<Transcript> getMarksDetail(Transcript prgCourseKey) {
		List<Transcript> transcript = getSqlMapClientTemplate().queryForList("transcript.getMarksDetail",prgCourseKey);		
		return transcript;
	}


	public Transcript getPersonalDetail(Transcript beanPassed) {
		Transcript transcript = (Transcript) getSqlMapClientTemplate().queryForObject("transcript.getPersonalDetail",beanPassed);
		if(transcript==null){
			transcript = new Transcript();			
		}
		return transcript;
	}


	public List<Transcript> getEnrollment(String universityId) {
		List<Transcript> transcript =  getSqlMapClientTemplate().queryForList("transcript.getEnrollment",universityId);	
		System.out.println("size of enrollment : "+transcript.size());
		return transcript;
	}
}
