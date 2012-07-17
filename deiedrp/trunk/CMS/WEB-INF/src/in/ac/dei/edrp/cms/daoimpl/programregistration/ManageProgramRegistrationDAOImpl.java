/*
 * @(#) ManageProgramRegistrationDAOImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.programregistration;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.programregistration.ManageProgramRegistrationDAO;
import in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster;
import in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * Creation date: 23-Feb-2011
 * This class implements those methods that is used for manage program registration.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class ManageProgramRegistrationDAOImpl extends SqlMapClientDaoSupport implements ManageProgramRegistrationDAO{

	static final Logger logger = Logger.getLogger(ManageProgramRegistrationDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<ProgramRegistrationDetails> getEntityList(ProgramRegistrationDetails details) throws DataAccessException {
		List<ProgramRegistrationDetails> entityList = null;
		try{
			entityList = getSqlMapClientTemplate().queryForList("ManageProgramRegistration.getEntityList",details);			
		}catch(DataAccessException dae){
			logger.error("getEntityList", dae);
			throw new MyException("program list does not found");
		}
		return entityList;
	}

	
	/** 
	 * Method getSessionDate is used for getting the session date of an university.
	 * @param universityId
	 * @return List of the ProgramRegistrationDetails.
	 * @see in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails
	 */
	
	public ProgramRegistrationDetails getSessionDate(String universityId)throws DataAccessException {
		ProgramRegistrationDetails sessionDate = null;
		try{
			sessionDate = (ProgramRegistrationDetails) getSqlMapClientTemplate().queryForObject("ProgramRegistration.getSessionDate",universityId);
		}catch(DataAccessException dae){
			throw new MyException("Session date does not found");
		}
		return sessionDate;
	}
	/** 
	 * Method getProgramNameList is used for getting the program list.
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	@SuppressWarnings("unchecked")
	
	public List<ProgramMaster> getProgramNameList(ProgramRegistrationDetails sessionDate) throws DataAccessException {
		List<ProgramMaster> programList = null;
		try{
			programList = getSqlMapClientTemplate().queryForList("ManageProgramRegistration.getProgramList",sessionDate);
			System.out.println("size of progr list "+programList.size());
		}catch(DataAccessException dae){			
			throw new MyException("program list does not found");
		}
		return programList;
	}

	/** 
	 * Method getBranchNameList is used for getting the branch list.
	 * @param programName object of ProgramMaster
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	@SuppressWarnings("unchecked")
	
	public List<ProgramMaster> getBranchNameList(ProgramMaster programMaster)throws DataAccessException {
		List<ProgramMaster> branchList = null;
		try{
			branchList = getSqlMapClientTemplate().queryForList("ManageProgramRegistration.getBranchList",programMaster);
		}catch(DataAccessException dae){			
			throw new MyException("branch list does not found");
		}
		return branchList;
	}

	/** 
	 * Method getSpecializationList is used for getting the specialization list.
	 * @param programName object of ProgramMaster
	 * @return HashSet of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	@SuppressWarnings("unchecked")
	
	public List<ProgramMaster> getSpecializationList (
			ProgramMaster programMaster) throws DataAccessException{
		List<ProgramMaster> specializationList = null;
		try{
			specializationList = getSqlMapClientTemplate().queryForList("ManageProgramRegistration.getSpecializationList",programMaster);
		}catch(DataAccessException dae){
			throw new MyException("specialization list does not found");
		}
		return specializationList;
	}
	
	/** 
	 * Method getSemesterList is used for getting the semester list.
	 * @param programName object of ProgramMaster
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	@SuppressWarnings("unchecked")
	
	public List<ProgramMaster> getSemesterList(ProgramMaster programMaster) throws DataAccessException {
		List<ProgramMaster> semesterList = null;
		try{
			semesterList = getSqlMapClientTemplate().queryForList("ManageProgramRegistration.getSemesterList",programMaster);
		}catch(DataAccessException dae){
			throw new MyException("semester list does not found");
		}
		return semesterList;
	}

	/** 
	 * Method getProgramDetails is used for getting the details of desired program.
	 * @param programName object of ProgramMaster
	 * @return ProgramRegistrationDetails.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 * @see in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails
	 */
	
	public ProgramRegistrationDetails getProgramDetails(ProgramMaster programMaster) throws DataAccessException {
		ProgramRegistrationDetails programDetails;
		try{
			programDetails = (ProgramRegistrationDetails) getSqlMapClientTemplate().queryForObject("ManageProgramRegistration.getProgramToChange",programMaster);
		}catch(DataAccessException dae){
			throw new MyException("program details does not found");
		}
		return programDetails;
	}

	/**
	 * Method changeProgramDetails is used for changing the program registration details
	 * @param programDetails
	 * @return number of records changed
	 */
	
	public int changeProgramDetails(ProgramRegistrationDetails programDetails) throws DataAccessException {
		try{
			return getSqlMapClientTemplate().update("ManageProgramRegistration.updateProgramDetails",programDetails);
		}catch(DataAccessException dae){
			throw new MyException("program details could not be modified");
		}
	}

	/**
	 * Method viewStatus is used for viewing the status of program registration details
	 * @param programDetails
	 * @return ProgramRegistrationDetails status of program
	 */
	
	public ProgramRegistrationDetails viewStatus(ProgramRegistrationDetails programDetails) {
		try{
			return (ProgramRegistrationDetails) getSqlMapClientTemplate().queryForObject("ManageProgramRegistration.checkStatus",programDetails);
		}catch(DataAccessException dae){
			throw new MyException("status could not be varified");
		}
	}
}
