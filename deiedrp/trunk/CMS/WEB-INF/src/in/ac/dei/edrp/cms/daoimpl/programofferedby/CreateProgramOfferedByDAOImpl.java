/*
 * @(#) CreateProgramOfferedByDAOImpl.java
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
import in.ac.dei.edrp.cms.dao.programofferedby.CreateProgramOfferedByDAO;
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
 * Creation date: 31-Jan-2011
 * This class implements those methods that is used for creating program offered by.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class CreateProgramOfferedByDAOImpl extends SqlMapClientDaoSupport implements CreateProgramOfferedByDAO{

	static final Logger logger = Logger.getLogger(CreateProgramOfferedByDAOImpl.class);
	/** 
	 * Method getEntityNameList is used for getting the entity list.
	 * @return List of the EntityDetails.
	 * @see in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	
	public List<EntityDetails> getEntityNameList(String universityId) throws DataAccessException {
		List<EntityDetails> entityNameList = null;
		List<EntityDetails> details = null;
		try{
			entityNameList = getSqlMapClientTemplate().queryForList("CreateProgramOfferedBy.getEntityList",universityId);
			details = getSqlMapClientTemplate().queryForList("CreateProgramOfferedBy.getEntityListUniversity",universityId);
		}catch(DataAccessException dae){
			throw new MyException("Entity list does not found");
		}
		
		entityNameList.addAll(details);
		
		return entityNameList;
	}
	
	/** 
	 * Method getProgramBranchSpecializationList is used for getting all the list of program,
	 * branch and specialization of an university and entity.
	 * @param entityId
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	
	public List<List<EntityDetails>> getProgramBranchSpecializationList(EntityDetails entityDetails) throws DataAccessException {
		
		List<List<EntityDetails>> combineData = new ArrayList<List<EntityDetails>>();
		try{
			List<EntityDetails> programData = getSqlMapClientTemplate().queryForList("CreateProgramOfferedBy.getProgramBranchSpecializationList",entityDetails);
			List<EntityDetails> empData = getSqlMapClientTemplate().queryForList("CreateProgramOfferedBy.getEmployeeList",entityDetails);
			List<EntityDetails> programTypeData = getSqlMapClientTemplate().queryForList("CreateProgramOfferedBy.getProgramType",entityDetails);
			combineData.add(programData);
			combineData.add(empData);
			combineData.add(programTypeData);			
		}catch(DataAccessException dae){
			throw new MyException("ProgramBranchSpecialization list does not found");
		}
		return combineData;
	}

	/** 
	 * Method insertProgramOfferedBy is used for inserting the program details with respect to entity
	 * @param programDetailList
	 * @param entityId
	 * @param creatorId
	 * @throws DataAccessException
	 */
	//Changes By Mandeep
	public String insertProgramOfferedBy(final String programDetailList,
			final String entityId, final String creatorId) throws DataAccessException{
		
		String message = null;
		
		try{
		message = (String) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
	         public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
	        	 
	        	 String result = "fail";
	        	 
		        	try{       		
		        		
		        		StringTokenizer programInfo = new StringTokenizer(programDetailList, ";"); 
		           		executor.startBatch();
		        		while(programInfo.hasMoreTokens()){
		        			String entityTokens=programInfo.nextToken();
		         			StringTokenizer entitySubTokens = new StringTokenizer(entityTokens, ",");
		        			EntityDetails entityDetails = new EntityDetails();
		        			while(entitySubTokens.hasMoreTokens()){
		        				entityDetails.setEntityId(entityId);
		        				entityDetails.setProgramId(entitySubTokens.nextToken());
		        				entityDetails.setBranchId(entitySubTokens.nextToken());
		        				entityDetails.setSpecializationId(entitySubTokens.nextToken());
		        				entityDetails.setEmployeeCode(entitySubTokens.nextToken());
		        				entityDetails.setProgramTypeCode(entitySubTokens.nextToken());
		        				entityDetails.setNumberOfSeats(Integer.parseInt(entitySubTokens.nextToken()));
		        				entityDetails.setCreatorId(creatorId);
		        				entityDetails.setModifierId(null);		        				
		        				executor.insert("CreateProgramOfferedBy.insertProgramOfferedBy", entityDetails);
		        			}
		        		}
		           	 executor.executeBatch();
		           	 
		           	 result = "success";
	        
		        	}catch(Exception e){
		        		logger.error("error in insertProgramOfferedBy:"+e);
						} 
		        	return result;
		         }
			});
		}catch(DataAccessException dae){
			throw new MyException("Exception in insertProgramOfferedBy method");
		}
		catch (Exception e) {
            message= "insertError"+e.getMessage();
            return message;
		}
		return message;
	}

	/** 
	 * Method existingEntityDetails is used for getting existing entity details
	 * @param entityId
	 * @return List of the EntityDetails.
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	
	public List<EntityDetails> existingEntityDetails(EntityDetails entityDetails) throws DataAccessException{
		List<EntityDetails> existingEntity = null;
		try{
			existingEntity = getSqlMapClientTemplate().queryForList("CreateProgramOfferedBy.getExistingEntityDetails",entityDetails);
		}catch(DataAccessException dae){
			throw new MyException("There is no existance of EntityDetails according to the entity");
		}
		return existingEntity;
	}

}
