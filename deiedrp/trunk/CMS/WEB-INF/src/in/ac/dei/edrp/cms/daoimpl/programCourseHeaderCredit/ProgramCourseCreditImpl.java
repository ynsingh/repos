/*
 * @(#) ProgramCourseCreditImpl.java
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

package in.ac.dei.edrp.cms.daoimpl.programCourseHeaderCredit;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.programCourseHeaderCredit.ProgramCourseCreditDAO;
import in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster;

/**
 * This class contains methods for program course header
 * @author Upasana Kulshrestha
 * @date 10-10-2012
 * @version 1.0
 */
public class ProgramCourseCreditImpl extends SqlMapClientDaoSupport implements ProgramCourseCreditDAO {

	@SuppressWarnings("unchecked")
	public List<ProgramMaster> getProgramCourseHeaderList(String universityId) {
		List<ProgramMaster> programCourseHeaderList = null;
		try{
			programCourseHeaderList = getSqlMapClientTemplate().queryForList("ProgramCourseHeaderCredit.getProgramCourseHeaderList", universityId);
		}catch(DataAccessException dae){
			throw new MyException("Program course header list does not found.");
		}
		return programCourseHeaderList;
	}

	public String updateProgramCourseCredit(ProgramMaster programCourseCredit) {
		try{
			int value=getSqlMapClientTemplate().update("ProgramCourseHeaderCredit.updatePCHCredits",programCourseCredit);
			logger.info("after updating the program course header credits");
			String count=Integer.toString(value);
			return count;
		}
		catch(Exception e){
			logger.error("error in updateProgramCourseCredit:"+e);
			return "updateError"+e.getMessage();
		}		
	}

	@SuppressWarnings("unchecked")
	public List<ProgramMaster> getProgramTermCredit(String progCourseKey) {
		List<ProgramMaster> programCourseTermList = null;
		try{
			programCourseTermList = getSqlMapClientTemplate().queryForList("ProgramCourseHeaderCredit.getProgramTermCredit", progCourseKey);
		}catch(DataAccessException dae){
			throw new MyException("Program course Term list does not found.");
		}
		return programCourseTermList;
	}
}
