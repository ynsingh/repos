/**
 * @(#) CorrectionInRegistrationImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.correctioninregistration;

import java.util.ArrayList;
import java.util.List;

import in.ac.dei.edrp.cms.dao.correctioninregistration.CorrectionInRegistrationConnect;
import in.ac.dei.edrp.cms.daoimpl.cgpadivision.CgpaDivisionImpl;
import in.ac.dei.edrp.cms.domain.correctioninregistration.CorrectionInRegistrationInfoGetter;


import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * The server side implementation of the RPC service.
 *
 * @version 1.0 3 AUG 2011
 * @author ROHIT SAXENA
 */
public class CorrectionInRegistrationImpl extends SqlMapClientDaoSupport implements CorrectionInRegistrationConnect {
	
	/** Creating object of Logger for log Maintenance */
	 private Logger logObj = Logger.getLogger(CorrectionInRegistrationImpl.class);
	 
	 /**
		* This method is used for fetching records having error
		* @param input Object of the referenced bean class
		* @return List
		*/
	@SuppressWarnings("unchecked")
	public List<CorrectionInRegistrationInfoGetter> getGridRecords(
			CorrectionInRegistrationInfoGetter input) {
		List<CorrectionInRegistrationInfoGetter> values = null;
		try{
			
		logObj.info("In getGridRecords of CorrectionInRegistrationImpl");
		values = new ArrayList<CorrectionInRegistrationInfoGetter>();
		
		values = getSqlMapClientTemplate().queryForList("correctioninregistration.getGridRecords",input);
		}
		catch (Exception e) {
			logObj.info("In getGridRecords of CorrectionInRegistrationImpl"+e.getMessage());
		}
		return values;
	}

	/**
	* This method is used for updating the correct sequnce number for an record having incorrect sequence number
	* @param input Object of the referenced bean class
	* @return String
	*/
	public String updateSeqDetails(CorrectionInRegistrationInfoGetter detail) {
		
		String res=detail.getReasoncode();
		detail.setRegStatus("N");
		try{
			logObj.info("In updateSeqDetails of CorrectionInRegistrationImpl");
		if(res.equalsIgnoreCase("SNEW")){
			
			getSqlMapClientTemplate().update("correctioninregistration.setNewSeqRecord",detail);
		
	}else if(res.equalsIgnoreCase("SSWT")){
		
		logObj.info("In updateSeqDetails of CorrectionInRegistrationImpl");
		getSqlMapClientTemplate().update("correctioninregistration.setSwtSeqRecord",detail);
	}
		}
		catch (Exception e) {
			logObj.info("In updateSeqDetails of CorrectionInRegistrationImpl"+e.getMessage());
			return "failure";
		}
		return "success";
	}

	/**
	* This method is used for getting Student details
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<CorrectionInRegistrationInfoGetter> getSMDetails(
			CorrectionInRegistrationInfoGetter smdetail) {
		
		List<CorrectionInRegistrationInfoGetter> value = null;
		try{
			logObj.info("In getSMDetails of CorrectionInRegistrationImpl");
        value = new ArrayList<CorrectionInRegistrationInfoGetter>();
		
		value = getSqlMapClientTemplate().queryForList("correctioninregistration.getSMRecords",smdetail);
		}
		catch (Exception e) {
			logObj.info("In getSMDetails of CorrectionInRegistrationImpl"+e.getMessage());
		}
		
		return value;
	}

	/**
	* This method is used for getting Student details from temporary table
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<CorrectionInRegistrationInfoGetter> getTSMDetails(
			CorrectionInRegistrationInfoGetter tsmdetail) {
		
		List<CorrectionInRegistrationInfoGetter> records = null;
		try{
			logObj.info("In getTSMDetails of CorrectionInRegistrationImpl");
		     records = new ArrayList<CorrectionInRegistrationInfoGetter>();
		
             records = getSqlMapClientTemplate().queryForList("correctioninregistration.getTSMRecords",tsmdetail);
		}
		catch (Exception e) {
			logObj.info("In getTSMDetails of CorrectionInRegistrationImpl"+e.getMessage());
		}
		
		return records;
	}

	/**
	* This method is used for getting Correct Enrollment No.
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<CorrectionInRegistrationInfoGetter> getEnrollNo(
			CorrectionInRegistrationInfoGetter input) {
		
		List<CorrectionInRegistrationInfoGetter> values = null;
		try{
			logObj.info("In getEnrollNo of CorrectionInRegistrationImpl");
		values = new ArrayList<CorrectionInRegistrationInfoGetter>();
		
		values = getSqlMapClientTemplate().queryForList("correctioninregistration.getEnrollValue",input);
		}
		catch (Exception e) {
			logObj.info("In getEnrollNo of CorrectionInRegistrationImpl"+e.getMessage());
		}
		return values;
	}

	/**
	* This method is used for getting Correct Roll No.
	* @param input Object of the referenced bean class
	* @return List
	*/
	public List<CorrectionInRegistrationInfoGetter> getRollNo(
			CorrectionInRegistrationInfoGetter input) {
		
		List<CorrectionInRegistrationInfoGetter> values = null;
		try{
			logObj.info("In getRollNo of CorrectionInRegistrationImpl");
		values = new ArrayList<CorrectionInRegistrationInfoGetter>();
		
		values = getSqlMapClientTemplate().queryForList("correctioninregistration.getRollValue",input);
		}
		catch (Exception e) {
			logObj.info("In getRollNo of CorrectionInRegistrationImpl"+e.getMessage());
		}
		return values;
	}

	/**
	* This method is used for setting Correct Enrollment No. And Roll No.
	* @param input Object of the referenced bean class
	* @return List
	*/
	public String setNumbers(CorrectionInRegistrationInfoGetter detail) {
		String res=detail.getReasoncode();
		detail.setRegStatus("N");
		try{
		if(res.equalsIgnoreCase("DUPS")){
			logObj.info("In setNumbers of CorrectionInRegistrationImpl");
			getSqlMapClientTemplate().update("correctioninregistration.setEnr",detail);
		
	}else if(res.equalsIgnoreCase("ENRR")){
		logObj.info("In setNumbers of CorrectionInRegistrationImpl");
		getSqlMapClientTemplate().update("correctioninregistration.setEnrRoll",detail);
	}
		}
		catch (Exception e) {
			logObj.info("In setNumbers of CorrectionInRegistrationImpl"+e.getMessage());
			return "failure";
		}
		return "success";
	}

	public String checkRecord(CorrectionInRegistrationInfoGetter detail) {
		
		String ret="failure";
		try{
			logObj.info("In checkRecord of CorrectionInRegistrationImpl");
		
			
			CorrectionInRegistrationInfoGetter obj=(CorrectionInRegistrationInfoGetter)getSqlMapClientTemplate().queryForObject("correctioninregistration.checkRecord",detail);
			
			int ss=obj.getCount();
            if(ss==0){
            	ret="0";
            }else if(ss>0){
            	ret="1";
            }
			
		}
		catch (Exception e) {
			logObj.info("In checkRecord of CorrectionInRegistrationImpl"+e.getMessage());
			
		}
		return ret;
	}

	public String rejectRecords(CorrectionInRegistrationInfoGetter detail) {
		
		String ret="failure";
		try{
			logObj.info("In rejectRecords of CorrectionInRegistrationImpl");
		
			int i=getSqlMapClientTemplate().update("correctioninregistration.rejectRecords",detail);
			
			if(i>0){
				ret="success";
			}else if(i==0){
				ret="failure";
			}
			
		}
		catch (Exception e) {
			
			logObj.info("In rejectRecords of CorrectionInRegistrationImpl"+e.getMessage());
			
		}
		return ret;
	}
	}


	

