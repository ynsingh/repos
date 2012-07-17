/*
 * @(#) BuildProgramRegistrationDAO.java
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
package in.ac.dei.edrp.cms.dao.programregistration;

import in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails;
import java.text.ParseException;
import java.util.List;

/**
 * Creation date: 10-Feb-2011
 * The behavior of this class is as an interface.
 * This interface contains those method that is used for program registration.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public interface BuildProgramRegistrationDAO {

	/**
	 * Method for inserting the data corresponding to new session.
	 * @param sessionDate
	 * @return String acknowledgement
	 * @see in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails
	 */
	String systemBuildRegistration(ProgramRegistrationDetails sessionDate);

	/**
	 * Method getSessionDate is used for getting the session date of an university.
	 * @param universityId
	 * @return ProgramRegistrationDetails.
	 * @see in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails
	 */
	ProgramRegistrationDetails getSessionDate(String universityId);

	/**
	 * Method insertProgramRegistration is used for inserting the data.
	 * @param registrationPeriod
	 * @param addDropPeriod
	 * @param sessionStartDate
	 * @param sessionEndDate
	 * @param universityId
	 * @param flag
	 * @throws ParseException
	 */
	List<Integer> insertProgramRegistration(int registrationPeriod, int addDropPeriod,
			String sessionStartDate,String sessionEndDate,String universityId,String userId,String flag) throws ParseException;

	/**
	 * Method getProgramRegistrationDetails is used for getting all program details which
	 * are not inserted.
	 * @param sessionDate
	 * @see in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails
	 */
	List<ProgramRegistrationDetails> getProgramRegistrationDetails(ProgramRegistrationDetails sessionDate);

	/**
	 * Method insertSingleProgramRegistration is used for inserting single record
	 * @param programDetails
	 * @param registrationPeriod
	 * @param addDropPeriod
	 * @param sessionStartDate
	 * @param sessionEndDate
	 * @param universityId
	 * @param userId
	 * @throws ParseException
	 */
	void insertSingleProgramRegistration(ProgramRegistrationDetails programDetails,
			int registrationPeriod,int addDropPeriod,String sessionStartDate,
			String sessionEndDate,String universityId,String userId) throws ParseException;

	/**
	 * Method to check status of build
	 * @param programDetails
	 * @return String message for status
	 */
	String checkStatus(ProgramRegistrationDetails programDetails);
}
