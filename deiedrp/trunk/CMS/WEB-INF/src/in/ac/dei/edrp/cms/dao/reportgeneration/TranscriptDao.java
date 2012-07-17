/**
 * @(#) TranscriptDao.java
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

package in.ac.dei.edrp.cms.dao.reportgeneration;

import in.ac.dei.edrp.cms.domain.reportgeneration.FinalSemesterResultStatisticsCategoryWise;
import in.ac.dei.edrp.cms.domain.reportgeneration.SubjectWiseMeritList;
import in.ac.dei.edrp.cms.domain.reportgeneration.Transcript;

import java.util.List;

/**
 * This interface consist the list of methods used
 * in TranscriptDAOImpl file.
 * @author Nupur Dixit
 * @date 02 Dec 2011
 * @version 1.0
 */
public interface TranscriptDao {
	
	List<FinalSemesterResultStatisticsCategoryWise> getCompleteDetails(FinalSemesterResultStatisticsCategoryWise finalSemesterResultStatisticsCategoryWise);
	
	/**
     * Method to get the program course details.
     * @return entityObject this object contain entity info. 
     */
	FinalSemesterResultStatisticsCategoryWise getEntityInfo(FinalSemesterResultStatisticsCategoryWise finalSemesterResultStatisticsCategoryWise);
	
	List<Transcript> getAllProgramCourseKey(Transcript transcript);

	Transcript getProgramName(Transcript prgCourseKey);

	Transcript getSgpaCgpa(Transcript prgCourseKey);
	Transcript getPersonalDetail(Transcript beanPassed);

	List<Transcript> getMarksDetail(Transcript prgCourseKey);

	List<Transcript> getEnrollment(String attribute);	
	
}

