/*
 * @(#) ProogramRegistrationDate.java
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
package in.ac.dei.edrp.cms.common.utility;

import in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails;

import java.text.ParseException;

/**
 * Creation date: 12-Feb-2011
 * This class is used for getting program details.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class ProogramRegistrationDate {
	
	/**
	 * Method getProgramRegistrationObject is used for getting ProgramRegistrationObject for insertion
	 * @param programDetails
	 * @param sessionMonth
	 * @param sessionYear
	 * @param registrationPeriod
	 * @param addDropPeriod
	 * @param sessionStartDate
	 * @param sessionEndDate
	 * @param userId
	 * @param universityId
	 * @param delimeter
	 * @return ProgramRegistrationDetails
	 * @throws ParseException
	 */
	public static ProgramRegistrationDetails getProgramRegistrationObject(ProgramRegistrationDetails programDetails,
			int sessionMonth,int sessionYear,int registrationPeriod,int addDropPeriod,
			String sessionStartDate,String sessionEndDate,String userId,String universityId,
			String delimeter) throws ParseException{		
		String programCourseKey = programDetails.getProgramCourseKey();
		String entityId = programDetails.getEntityId();
		String semesterStartMonthDate = programDetails.getSemesterStartDate();		
		int semesterStartMonth = Integer.parseInt(semesterStartMonthDate.substring(0,semesterStartMonthDate.indexOf(delimeter)));
		int year = DateCalculation.getRegistrationStartDate(sessionMonth, semesterStartMonth, sessionYear);
		String registrationStartDate = year+delimeter+semesterStartMonthDate;
		String registrationEndDate = DateCalculation.getRegistrationEndDate(registrationStartDate, registrationPeriod);
		String semesterEndMonthDate = programDetails.getSemesterEndDate();
		int semesterEndMonth = Integer.parseInt(semesterEndMonthDate.substring(0,semesterEndMonthDate.indexOf(delimeter)));
		String semesterEndDate = DateCalculation.getSemesterEndDate(semesterStartMonth, semesterEndMonth, year)
						+delimeter+semesterEndMonthDate;
	
		ProgramRegistrationDetails programRegistrationDetails =
			new ProgramRegistrationDetails(universityId,programCourseKey,
					registrationStartDate,registrationEndDate,addDropPeriod,
					registrationStartDate,semesterEndDate,
					sessionStartDate,sessionEndDate,userId,
					"ACT",null,entityId);
		return programRegistrationDetails;
	}
}
