/**
 * @(#) NameCorrectionImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.namecorrection;

import in.ac.dei.edrp.cms.dao.namecorrection.NameCorrectionConnect;
import in.ac.dei.edrp.cms.daoimpl.cgpadivision.CgpaDivisionImpl;
import in.ac.dei.edrp.cms.domain.namecorrection.NameCorrectionInfoGetter;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * The server side implementation of the RPC service.
 *
 * @version 1.0 3 AUG 2011
 * @author ROHIT SAXENA
 */
public class NameCorrectionImpl extends SqlMapClientDaoSupport implements NameCorrectionConnect {

	  private Logger logObj = Logger.getLogger(NameCorrectionImpl.class);
	  
	
	    /**
	    * This method is used for getting Records having error status
	    * @param input Object of the referenced bean class
	    * @return List
	    */
	@SuppressWarnings("unchecked")
	public List<NameCorrectionInfoGetter> getErrorRecords(
			NameCorrectionInfoGetter input) {
		List<NameCorrectionInfoGetter> values = null;
		try{
		logObj.info("In getErrorRecords of NameCorrectionImpl");
		values = new ArrayList<NameCorrectionInfoGetter>();
		
		values = getSqlMapClientTemplate().queryForList("namecorrection.getNameErrorRecords",input);
		}
		catch (Exception e) {
			logObj.info("In getErrorRecords of NameCorrectionImpl"+e.getMessage());
		}
		
		return values;
	}

    /**
	    * This method is used for update names of error record
	    * @param detail Object of the referenced bean class
	    * @return String
	    */
	public String updateNames(NameCorrectionInfoGetter detail) {
		
		
		try{
			logObj.info("In updateNames of NameCorrectionImpl");
      getSqlMapClientTemplate()
                .update("namecorrection.updateRecords",detail);
    
		}
		catch (Exception e) {
			logObj.info("In updateNames of NameCorrectionImpl"+e.getMessage());
			return "failure";
		}

        return "success";
	}

    /**
	* This method is used for fetching the program list
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<NameCorrectionInfoGetter> getProgramList(
			NameCorrectionInfoGetter input) {
		List<NameCorrectionInfoGetter> values = null;
		try{
			logObj.info("In getProgramList of NameCorrectionImpl");
		values = new ArrayList<NameCorrectionInfoGetter>();
		
		values = getSqlMapClientTemplate().queryForList("namecorrection.getPrograms",input);
		}
		catch (Exception e) {
			logObj.info("In getProgramList of NameCorrectionImpl"+e.getMessage());
		}
		
		return values;
	}

    /**
	* This method is used for fetching the branch list
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<NameCorrectionInfoGetter> getBranchList(
			NameCorrectionInfoGetter input) {
		
		List<NameCorrectionInfoGetter> values = null;
		try{
			logObj.info("In getBranchList of NameCorrectionImpl");
		values = new ArrayList<NameCorrectionInfoGetter>();
		
		values = getSqlMapClientTemplate().queryForList("namecorrection.getBranchs",input);
		}
		catch (Exception e) {
			logObj.info("In getBranchList of NameCorrectionImpl"+e.getMessage());
		}
		return values;
	}

    /**
	* This method is used for fetching the specialization list
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<NameCorrectionInfoGetter> getspecializationList(
			NameCorrectionInfoGetter input) {
		
		List<NameCorrectionInfoGetter> values = null;
		try{
			logObj.info("In getspecializationList of NameCorrectionImpl");
		values = new ArrayList<NameCorrectionInfoGetter>();
		
		values = getSqlMapClientTemplate().queryForList("namecorrection.getSpecializations",input);
		}
		catch (Exception e) {
			logObj.info("In getspecializationList of NameCorrectionImpl"+e.getMessage());
		}
		
		return values;
	}

}
