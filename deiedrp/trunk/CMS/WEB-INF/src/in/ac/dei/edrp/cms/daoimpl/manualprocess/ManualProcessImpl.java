/**
 * @(#) ManualProcessImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.manualprocess;

import in.ac.dei.edrp.cms.dao.manualprocess.ManualProcess;
import in.ac.dei.edrp.cms.domain.manualprocess.ManualProcessBean;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * @author Devendra Singhal
 * @date Sept/13/2011
 * @version 1.0
 *
 */
public class ManualProcessImpl extends SqlMapClientDaoSupport implements ManualProcess{

	/** Creating object of Logger for log Maintenance */

		private static Logger logObj = Logger.getLogger(ManualProcessImpl.class);

	/** Getting OwnerEntity Name and EntityId from database
	 * @param Object of ManualProcessBean
	 * return List<ManualProcessBean>
	*/
	public List<ManualProcessBean> getOwnerEntity(ManualProcessBean manualProcessInput) {
		List entity=null;
		try{
			entity = getSqlMapClientTemplate().queryForList("manualProcess.getOwnerEntityList",manualProcessInput);
			return entity;
		}
		catch(Exception e){
			logObj.error(e.getMessage());
		}
		return entity;
	}

	/** Getting Program Name ,Program Id and Program Code from database
	 * @param Object of ManualProcessBean manualProcessInput
	 * return List<ManualProcessBean>
	**/
	public List<ManualProcessBean> getProgram(ManualProcessBean manualProcessInput) {
		List programList=null;
		try{
			String programId=manualProcessInput.getUniversityId()+"%";
			manualProcessInput.setProgramId(programId);
			programList = getSqlMapClientTemplate().queryForList("manualProcess.getProgramNameList",manualProcessInput);
			return programList;
		}
		catch(Exception e){
			logObj.error(e.getMessage());
		}
		return programList;
	}

	/** Getting Branch Name and Branch Id from database
	 * @param Object of ManualProcessBean manualProcessInput
	 * @return List<ManualProcessBean>
	**/
	public List<ManualProcessBean> getBranch(ManualProcessBean manualProcessInput) {
		List branchList=null;
		try{
			branchList = getSqlMapClientTemplate().queryForList("manualProcess.getBranchList",manualProcessInput);
			return branchList;
		}
		catch(Exception e){
			logObj.error(e.getMessage());
		}
		return branchList;
	}

	/** Getting Semester Name and Semester Id from database
	 * @param Object of ManualProcessBean
	 * @return List<ManualProcessBean>
	**/
	public List<ManualProcessBean> getSemester(
			ManualProcessBean manualProcessInput) {
		List semesterList=null;
		try{
			semesterList = getSqlMapClientTemplate().queryForList("manualProcess.getSemesterList",manualProcessInput);
			return semesterList;
		}
		catch(Exception e){
			logObj.error(e.getMessage());
		}
		return semesterList;
	}

	/** Getting Specialization Name and Specialization Id from database
	 * @param Object of ManualProcessBean
	 * @return List<ManualProcessBean>
	**/
	public List<ManualProcessBean> getSpecialization(
			ManualProcessBean manualProcessInput) {
		List specializationList=null;
		try{
			specializationList = getSqlMapClientTemplate().queryForList("manualProcess.getSpecializationList",manualProcessInput);
			return specializationList;
		}
		catch(Exception e){
			logObj.error(e.getMessage());
		}
		return specializationList;
	}

	/** Getting Session StartDate from database
	 * @param Object of ManualProcessBean
	 * @return List<ManualProcessBean>
	**/
	public List<ManualProcessBean> getSessionStartDate(ManualProcessBean manualProcessInput) {
//		List<ManualProcessBean> sessionList=new ArrayList<ManualProcessBean>();
		List<ManualProcessBean> tempList=new ArrayList<ManualProcessBean>();
		try{
			 tempList = getSqlMapClientTemplate().queryForList("manualProcess.getSessionList",manualProcessInput);
//			java.util.Iterator itr = tempList.iterator();
//			while(itr.hasNext()){
//				ManualProcessBean manualProcess=(ManualProcessBean)itr.next();
//				String str[]=manualProcess.getSessionStartDate().split("-");
//				manualProcess.setSessionStartDate(str[0]);
//				sessionList.add(manualProcess);
//			}
//			return sessionList;
			return tempList;
		}
		catch(Exception e){
			logObj.error(e.getMessage());
		}
//		return sessionList;
		return tempList;
	}
}
