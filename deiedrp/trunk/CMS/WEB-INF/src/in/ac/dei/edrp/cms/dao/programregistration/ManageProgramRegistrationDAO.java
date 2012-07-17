/*
 * @(#) ManageProgramRegistrationDAO.java
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

import in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster;
import in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails;

import java.util.List;

/**
 * Creation date: 23-Feb-2011
 * The behavior of this class is as an interface.
 * This interface contains those method that is used for manage program registration.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public interface ManageProgramRegistrationDAO {
	
	/** 
	 * method will get the entityId 
	 * @return List of the ProgramRegistrationDetails
	 * @see in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails
	 */
	List<ProgramRegistrationDetails> getEntityList(ProgramRegistrationDetails registrationDetails);
	
	/** 
	 * Method getSessionDate is used for getting the active session dates.
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	
	ProgramRegistrationDetails getSessionDate(String universityId);
	/** 
	 * Method getProgramNameList is used for getting the program list.
	 * @param sessionDate 
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	List<ProgramMaster> getProgramNameList(ProgramRegistrationDetails sessionDate);
	/** 
	 * Method getBranchNameList is used for getting the branch list.
	 * @param programMaster
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	List<ProgramMaster> getBranchNameList(ProgramMaster programMaster);
	/** 
	 * Method getSemesterList is used for getting the semester list.
	 * @param programName object of ProgramMaster
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	List<ProgramMaster> getSemesterList(ProgramMaster programName);
	/** 
	 * Method getSpecializationList is used for getting the specialization list.
	 * @param programName object of ProgramMaster
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	List<ProgramMaster> getSpecializationList(ProgramMaster programName);
	
	/** 
	 * Method getProgramDetails is used for getting the details of desired program.
	 * @param programName object of ProgramMaster
	 * @return ProgramRegistrationDetails.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 * @see in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails
	 */
	ProgramRegistrationDetails getProgramDetails(ProgramMaster programName);
	
	/**
	 * Method changeProgramDetails is used for changing the program registration details
	 * @param programDetails
	 * @return number of records changed
	 */
	int changeProgramDetails(ProgramRegistrationDetails programDetails);
	
	/**
	 * Method viewStatus is used for viewing the status of program registration details
	 * @param programDetails
	 * @return ProgramRegistrationDetails
	 */
	ProgramRegistrationDetails viewStatus(ProgramRegistrationDetails programDetails);
	
}
