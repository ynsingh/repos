/*
 * @(#) EntityMasterDaoImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.entity;

import in.ac.dei.edrp.cms.dao.entity.EntityMasterDao;
import in.ac.dei.edrp.cms.domain.entity.EntityInfoGetter;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;



import java.util.List;


/**
 * Implementation class of EntityMasterDao interface
 * @author Manpreet Kaur
 * @date 10-02-2011
 * @version 1.0
 */
public class EntityMasterDaoImpl extends SqlMapClientDaoSupport
    implements EntityMasterDao {
    private static Logger logObj = Logger.getLogger(EntityMasterDaoImpl.class);
    TransactionTemplate transactionTemplate = null;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    
    String error="";
    
    
    
    
    /**
     * Method for populating list of entity types
     * @param userId user id of the use logged in
     * @return List containing entities
     */
    @SuppressWarnings("unchecked")
    public List<EntityInfoGetter> methodEntityList(String userId) {
        try {
            String universityId = userId.substring(1, 5);

            List<EntityInfoGetter> entityList = getSqlMapClientTemplate()
                                                    .queryForList("entityInfo.entityList",
                    universityId);

            return entityList;
        } catch (Exception e) {
            logObj.error(e);
        }

        return null;
    }

    /**
     *Method for getting list of parent entities
     *@param userId user id of the use logged in
     *@param EntityInfoGetter
     *@return List containing possible parent entities
     */
    @SuppressWarnings("unchecked")
    public List<EntityInfoGetter> methodParentEntityList(String userId,
        String level) {
        EntityInfoGetter entityInfo = new EntityInfoGetter();
        List<EntityInfoGetter> resultList = null;

        try {
            String universityId = userId.substring(1, 5);

            entityInfo.setUniversityId(universityId);
            entityInfo.setLevel(level);

            if (Integer.parseInt(level) == 1) {
                resultList = getSqlMapClientTemplate()
                                 .queryForList("entityInfo.selectParentUni",
                        entityInfo);
            } else {
                resultList = getSqlMapClientTemplate()
                                 .queryForList("entityInfo.parentList",
                        entityInfo);
            }

            return resultList;
        } catch (Exception e) {
            logObj.error("parent entity list " + e);
        }

        return null;
    }
    
    
   
    

    /**
     * Method to add new entity
     *@param userId user id of the use logged in
     *@param EntityInfoGetter
     *@return String
     */
    public String methodAddEntity(final String userId,
        final EntityInfoGetter entityInfoInput) throws Exception {
    	
    	
    	
        transactionTemplate.execute(new TransactionCallback() {
                Object savepoint = null;

                public String doInTransaction(TransactionStatus status) {
                    try {
                        EntityInfoGetter entityInfo = entityInfoInput;
                        
                        String universityId = userId.substring(1, 5);

                        EntityInfoGetter entityInfo1 = (EntityInfoGetter) getSqlMapClientTemplate()
                                         .queryForObject("entityInfo.selectNextUniEntityID",
                                universityId);
                        try{
                        if(entityInfo1.getEntityId()  ==  null){
                        error="ErrSysVal";
                        	return error;
                        }
                        }catch (Exception e) {
                        	 error="ErrSysVal";
                         	return error+e;
                        	}
                        error="";
                        int nextId = Integer.parseInt(entityInfo1.getEntityId()) + 1;

                        String formatted = String.format("%08d", nextId);
                        entityInfo.setEntityId(formatted);

                       
                        entityInfo.setUniversityId(universityId);
                        entityInfo.setCreatorId(userId);
                        savepoint = status.createSavepoint();
                        getSqlMapClientTemplate()
                            .insert("entityInfo.insertNewEntity", entityInfo);
                        getSqlMapClientTemplate()
                            .update("entityInfo.updateSystemValues",
                            formatted.subSequence(4, 8));

                        getSqlMapClientTemplate().getDataSource().getConnection()
                            .commit();
                    } catch (DataIntegrityViolationException e) {
                        logObj.error(e);                       
                        error = "duplicateentitycode"+e;                       
                        status.rollbackToSavepoint(savepoint);
                    }   
                    catch (Exception e) {
                        logObj.error(e);                       
                        error = "error"+e;                       
                        status.rollbackToSavepoint(savepoint);
                    }               
                    return error;
                }
            });

        return error;
    }

    /**
     * Method for populating list of existing entities of given type
     * @param user_id id of the user logged in
     * @param level entity type level
     * @return list of entities
     */
    @SuppressWarnings("unchecked")
    public List<EntityInfoGetter> methodPopulateEntity(String user_id,
        String level) throws Exception {
        try {
            EntityInfoGetter entityObject = new EntityInfoGetter();

            String university_id = user_id.substring(1, 5);

            entityObject.setUniversityId(university_id);
            entityObject.setEntityType(level);

            List<EntityInfoGetter> entityList = null;

            if (entityObject.getEntityType().equalsIgnoreCase("1")) {
                entityList = getSqlMapClientTemplate()
                                 .queryForList("entityInfo.selectFromNameUniWise",
                        entityObject);

                return entityList;
            } else {
                entityList = getSqlMapClientTemplate()
                                 .queryForList("entityInfo.selectFromName",
                        entityObject);

                return entityList;
            }
        } catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }
    }

    /**
     * Method for updating information related to particular entity
     * @param userId id of the user logged in
     * @param entityInfo containing information to be updated
     */
    public String methodUpdateEntity(String userId, EntityInfoGetter entityInfo) {
    	int i=0;
        try {
           
           entityInfo.setModifierId(userId);
           i=  getSqlMapClientTemplate().update("entityInfo.updateEntity", entityInfo);
           //code added by ashish mohan starts
        	}
        catch(DataIntegrityViolationException e1){
        	return "duplicateentitycode"+Integer.toString(i)+e1;
        	//code added by ashish mohan ends
        } catch (Exception e) {
            logObj.error(e);
            //return value change by ashish mohan 
            return "Error"+Integer.toString(i)+e;
        }
        //return value change by ashish mohan
		return Integer.toString(i);
    }

    /**
     * Method for deletion of entity
     * @param entityId id of entity to be deleted
     * @throws MySQLIntegrityConstraintViolationException 
     */
    @SuppressWarnings("unchecked")
	public String methodDeleteEntity(String entityId) throws Exception {
    	int i=0;
    	try{
    	
    	List<EntityInfoGetter> entityList = null;

       //getting list of child entities
        entityList = getSqlMapClientTemplate().queryForList("entityInfo.getChildListForDelete",entityId);
    	i=entityList.size();
    	if(i==0){
        try {
        		i=getSqlMapClientTemplate().delete("entityInfo.deleteEntity", entityId);
        	}
        
        	catch(DataIntegrityViolationException e1){
        	  //return value change by ashish mohan 
        	return "ForiegnKeyConstraint"+Integer.toString(i)+e1;
        	}
        	catch(Exception e1){
        		 //return value change by ashish mohan 
        		return "ErrorInDelete"+Integer.toString(i)+e1;
        	}
    	}
    	else{ //return value change by ashish mohan 
    		 return "EntityHasChild"+Integer.toString(i);
    	}
    }catch (Exception e) {
        	 logObj.error(e);
        	  //return value change by ashish mohan 
        	 return "ErrorInChildQuery"+Integer.toString(i)+e;
        }
    //return value change by ashish mohan 
		return Integer.toString(i);
    }

    /**
     * Method for getting list of possible locations for entity
     * @param userId
     * @return list of locations
     */
    @SuppressWarnings("unchecked")
    public List<EntityInfoGetter> methodLocationList(String userId) {
        try {
            String universityId = userId.substring(1, 5);

            List<EntityInfoGetter> locationList = getSqlMapClientTemplate()
                                                      .queryForList("entityInfo.getLocationList",
                    universityId);

            return locationList;
        } catch (Exception e) {
            logObj.error(e);
        }

        return null;
    }
    
    
    
    
    /**
     * Method for getting list of child entities of an entity
     * @param entityId id of parent entity
     * @return list 
     */
    @SuppressWarnings("unchecked")
    public List<EntityInfoGetter> getChildEntityList(String entityId) {
        try {       

            List<EntityInfoGetter> childList = getSqlMapClientTemplate()
                                                      .queryForList("entityInfo.getChildList",
                                                    		  entityId);
            return childList;
        } catch (Exception e) {
            logObj.error(e);
        }

        return null;
    }
    
    
    
    
    
    
    
    
}
