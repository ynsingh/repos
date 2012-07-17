/*
 * @(#) ManageProgramOfferedByDAOImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.programofferedby;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.programofferedby.ManageProgramOfferedByDAO;
import in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * Creation date: 02-Feb-2011
 * This class implements those methods that is used for managing program offered by.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class ManageProgramOfferedByDAOImpl extends SqlMapClientDaoSupport implements ManageProgramOfferedByDAO{

	static final Logger logger = Logger.getLogger(ManageProgramOfferedByDAOImpl.class);
	/** 
	 * Method getEntityNameList is used for getting the entity list.
	 * @return List of the EntityDetails.
	 * @see in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails
	 */
	@SuppressWarnings("unchecked")
	
	public List<EntityDetails> getEntityNameList(String universityId) throws DataAccessException {
		List<EntityDetails> entityList = null;
		List<EntityDetails> details = null;
		try{
			entityList = getSqlMapClientTemplate().queryForList("ManageProgramOfferedBy.manageEntityList",universityId);
			details = getSqlMapClientTemplate().queryForList("CreateProgramOfferedBy.getEntityListUniversity",universityId);
		}catch(DataAccessException dae){
			throw new MyException("Entity list does not found");
		}
		
		entityList.addAll(details);
		return entityList;
	}
	
	/** 
	 * Method getProgramNameList is used for getting the program list.
	 * @param entityId
	 * @return List of the EntityDetails.
	 * @see in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails
	 */
	@SuppressWarnings("unchecked")
	
	public List<EntityDetails> getProgramNameList(String entityId) throws DataAccessException{
		List<EntityDetails> programList = null;
		try{
			programList = getSqlMapClientTemplate().queryForList("ManageProgramOfferedBy.getProgramList",entityId);
		}catch(DataAccessException dae){
			throw new MyException("Program list of an entity does not found");
		}
		return programList;
	}

	/** 
	 * Method getProgramListOfEntity is used for getting the program list.
	 * @param entityDetails
	 * @return List of the EntityDetails.
	 * @see in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails
	 */
	@SuppressWarnings("unchecked")
	
	public List<List<EntityDetails>> getProgramListOfEntity(
			EntityDetails entityDetails) throws DataAccessException{
		List<List<EntityDetails>> combineData = new ArrayList<List<EntityDetails>>();
		try{
			List<EntityDetails> programData = getSqlMapClientTemplate().queryForList("ManageProgramOfferedBy.getProgramDetails_Entity",entityDetails);
			List<EntityDetails> empData = getSqlMapClientTemplate().queryForList("CreateProgramOfferedBy.getEmployeeList",entityDetails);
			
			combineData.add(programData);
			combineData.add(empData);
		}catch(DataAccessException dae){
			throw new MyException("Existing program list of an entity does not found");
		}
		return combineData;
	}

	/** 
	 * Method updateEntityDetails is used for updating the employee and seats of an entity
	 * @param entityDetails
	 * @see in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails
	 */
	
	public String updateEntityDetails(EntityDetails entityDetails) throws DataAccessException {
		try{
			int value=getSqlMapClientTemplate().update("ManageProgramOfferedBy.updateProgramOfferedBy",entityDetails);
			logger.info("after updating the employee and seats of an entity.");
			String count=Integer.toString(value);
			return count;
		}catch(DataAccessException dae){
			return "DataAccess"+dae.getMessage();
			//throw new MyException("Exception while updating entity details");
		}
		catch(Exception e){
    		logger.error("error in Update:"+e);
    		return"updateError"+e;

		}
	}

	/** 
	 * Method deleteDesiredEntityDetails is used for deleting the entity details
	 * @param entityId
	 * @param entityData
	 * @see in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails
	 */
	
	public String deleteDesiredEntityDetails(final String entityId, final String entityData)throws DataAccessException {
		
		String resultMessage;
		
		try{
		resultMessage = (String) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {			
	        public String doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
	        	
	        	String message = null;
	        	
		        	try{
		        		StringTokenizer entityDetail = new StringTokenizer(entityData, ";"); 
		           		executor.startBatch();
		        		while(entityDetail.hasMoreTokens()){
		        			String entityTokens=entityDetail.nextToken();
		         			StringTokenizer entitySubTokens = new StringTokenizer(entityTokens, ",");
		        			EntityDetails entityDetails = new EntityDetails();
		        			while(entitySubTokens.hasMoreTokens()){
		        				entityDetails.setEntityId(entityId);
		        				entityDetails.setProgramId(entitySubTokens.nextToken());
		        				entityDetails.setBranchId(entitySubTokens.nextToken());
		        				entityDetails.setSpecializationId(entitySubTokens.nextToken());
		
		        				executor.delete("ManageProgramOfferedBy.deleteEntityDetails", entityDetails);
		        				
		        				message = "success";
		        			}
		        		}
		           	 executor.executeBatch();
	        
		        	}catch(Exception e){
		        		logger.error("error in deleteDesiredEntityDetails:"+e);
		        		message = "deleteError"+e.getMessage();
						} 
		        	return message;
		         }
			});
		}catch(DataAccessException dae){
			throw new MyException("Exception while deleting entity details");
		}
		return resultMessage;
	}
	
	
}
