/**
 * @(#) RemarkImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.remark;

import in.ac.dei.edrp.cms.controller.admissionIntegration.AdmissionIntegrationController;
import in.ac.dei.edrp.cms.dao.remark.RemarkService;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * Class contain implementation Methods of RemarkService Interface
 * @version 1.0 
 * @date Oct 6 2012
 * @author Devendra Singhal
 */
public class RemarkImpl extends SqlMapClientDaoSupport implements RemarkService {
	/*Create Logger Instance to maintain log Files*/
	private static Logger logObj = Logger.getLogger(RemarkImpl.class);

	/**
	 * Method to get Semester Detail
	 * @param Object of ProgressCardInfo Been
	 * @return List<ProgressCardInfo> list
	 **/
	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> getSemesterDetail(ProgressCardInfo input) {
		List<ProgressCardInfo>list=null;
		try{
			list=getSqlMapClient().queryForList("remark.getSemesterDetail", input);
		}
		catch(Exception e){
			logObj.error("Error During Getting Semester Detail inside RemarkImpl : "+e);
		}
		return list;
	}

	/**
	 * Method to get Remark Detail to manage
	 * @param Object of ProgressCardInfo Been
	 * @return List<ProgressCardInfo> list
	 **/
	@SuppressWarnings("unchecked")
	public List<ProgressCardInfo> getRemarkDetail(ProgressCardInfo input) {
		List<ProgressCardInfo>list=null;
		try{
			list=getSqlMapClient().queryForList("remark.getRemarkDetail", input);
		}
		catch(Exception e){
			logObj.error("Error During Getting Remark Detail for manage inside RemarkImpl : "+e);
		}
		return list;
	}

	/**
	 * Method to Save Remark Detail
	 * @param Object of ProgressCardInfo Been
	 * @return String message
	 **/
	public String saveRemarkDetail(ProgressCardInfo input) {
		String msg="";
		try{
			getSqlMapClient().update("remark.updateRemark", input);
			msg="success";
		}
		catch(Exception e){
			msg="error";
			logObj.error("Error During Save Remark Detail inside class RemarkImpl : "+e);
		}
		return msg;
	}
	
}
