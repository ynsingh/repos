/**
 * @(#) UpdatePrestagingImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.updateprestaging;

import in.ac.dei.edrp.cms.dao.updateprestaging.UpdatePrestagingConnect;
import in.ac.dei.edrp.cms.domain.updateprestaging.UpdatePrestagingInfoGetter;

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
public class UpdatePrestagingImpl extends SqlMapClientDaoSupport implements UpdatePrestagingConnect {
	
	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(UpdatePrestagingImpl.class);

    /**
	* This method is used for fetching the program list
	* @param input Object of the referenced bean class
	* @return List
	*/
	@SuppressWarnings("unchecked")
	public List<UpdatePrestagingInfoGetter> getRecords(
			UpdatePrestagingInfoGetter input) {
		List<UpdatePrestagingInfoGetter> values =null;
		try{
		UpdatePrestagingInfoGetter val =new UpdatePrestagingInfoGetter();
		val.setUniversityId(input.getUniversityId());
		logObj.info("Function getRecords in UpdatePrestagingImpl");
		values = new ArrayList<UpdatePrestagingInfoGetter>();
		
		values = getSqlMapClientTemplate().queryForList("updateprestaging.getErrorRecords",val);
		}
		catch (Exception e) {
			logObj.info("Function deletePrestagingRecords in UpdatePrestagingImpl"+e.getMessage());
		}
		
		return values;
	}
	
	/**
	    * Method for deleting records(divisions)for the concerned university
	    * @param input Object of the referenced bean class
	    * @param items selected items to be deleted
	    * @return String
	    */
	    public String deletePrestagingRecords(List<UpdatePrestagingInfoGetter> input) {
	    	int del=0;
	    	try{
	    		logObj.info("Function deletePrestagingRecords in UpdatePrestagingImpl");
	    			for(int i=0;i<input.size();i++){
	    				
	    				getSqlMapClientTemplate()
		                .delete("updateprestaging.deletePrestageRecords",input.get(i));
	    				del=del+1;
	    			}
	    	}
	    	catch (Exception e) {
	    		logObj.info("Function deletePrestagingRecords in UpdatePrestagingImpl"+e.getMessage());
			}
	                
	    			if(del!=0){
	         return "success";
	    			}
	    			else{
	    		return "failure";		
	    			}
	    }

	    /**
		* This method is used for fetching the program list
		* @param input Object of the referenced bean class
		* @return List
		*/
	public String updatePrestagingDetails(UpdatePrestagingInfoGetter detail) {
			
			
			try{
				logObj.info("Function updatePrestagingDetails in UpdatePrestagingImpl");
          getSqlMapClientTemplate()
	                .update("updateprestaging.updatePrestageRecords",detail);
         
			}
			catch (Exception e) {
				logObj.info("Function updatePrestagingDetails in UpdatePrestagingImpl"+e.getMessage());
				return "failure";
			}

	        return "success";
	
	}

	

}
