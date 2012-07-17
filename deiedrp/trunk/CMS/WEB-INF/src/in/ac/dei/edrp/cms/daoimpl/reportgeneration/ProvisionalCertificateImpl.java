/**
 * @(#) ProvisionalCertificateImpl.java
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

import java.util.List;

import in.ac.dei.edrp.cms.dao.reportgeneration.ProvisionalCertificate;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProvisionalCertificateBean;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * This file contains methods used to Generate Provisional Certificate
 * @author Devendra Singhal
 * @date 09 Dec 2011
 * @version 1.0
 */
public class ProvisionalCertificateImpl extends SqlMapClientTemplate implements ProvisionalCertificate{
	//Create Object of Logger class to maintain log file
	private Logger loggerObject = Logger.getLogger(ProvisionalCertificateImpl.class);
	
	/**Method to Get Student Detail
	 *@param object of ProvisionalCertificate Bean
	 *@return List<ProvisionalCertificate> 
	 **/
	@SuppressWarnings("unchecked")
	public List<ProvisionalCertificateBean> getStudentDetail(
			ProvisionalCertificateBean provisionalCertificate) {
		List<ProvisionalCertificateBean>studentList=null;
		try{
			studentList=getSqlMapClient().queryForList("provisionalCertificate.getStudentDetail",provisionalCertificate);
		}
		catch(Exception e){
			loggerObject.error("Error in ProvisionalCertificateImpl inside Method getStudentDetail: "+e);
		}
		return studentList;
	}
	
	/**Method to Get Entity from emtity_master table
	 *@param object of ProvisionalCertificate Bean
	 *@return List<ProvisionalCertificate> 
	 **/
	@SuppressWarnings("unchecked")
	public List<ProvisionalCertificateBean> getEntity(
			ProvisionalCertificateBean provisionalCertificate) {
		List<ProvisionalCertificateBean>entityList=null;
		try{
			entityList=getSqlMapClient().queryForList("provisionalCertificate.getEntityList",provisionalCertificate);
		}
		catch(Exception e){
			loggerObject.error("Error in ProvisionalCertificateImpl inside Method getEntity: "+e);
		}
		return entityList;
	}
	
	/**Method to Get Description from system table two of a particular component code and group code
	 *@param object of ProvisionalCertificate Bean
	 *@return String 
	 **/
	@SuppressWarnings("unchecked")
	public String getDescription(
			ProvisionalCertificateBean provisionalCertificate) {
		String desc="";
		try{
			List<ProvisionalCertificateBean> list=getSqlMapClient().queryForList("provisionalCertificate.getDescription",provisionalCertificate);
			if(list.size()>0){
				desc=list.get(0).getComponentDescription();
			}
			else{
				desc="noDesc";
			}
		}
		catch(Exception e){
			desc="error";
			loggerObject.error("Error in ProvisionalCertificateImpl inside Method getEntity: "+e);
		}
		return desc;
	}

}
