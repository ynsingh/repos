/*
 * @(#) EntityMasterDao.java
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
package in.ac.dei.edrp.cms.dao.entity;

import in.ac.dei.edrp.cms.domain.entity.EntityInfoGetter;

import java.util.List;


/**
 * This interface contains methos declaration for entity master functionality
 * @author Manpreet Kaur
 * @date 10-02-2011
 * @version 1.0
 */
public interface EntityMasterDao {
    /**
    * Method to add new entity
    *@param userId user id of the use logged in
    *@param EntityInfoGetter
    *@return String
    */
    String methodAddEntity(String userId, EntityInfoGetter entityInfo)
        throws Exception;

    /**
     * Method for populating list of entity types
     * @param userId user id of the use logged in
     * @return List containing entities
     */
    List<EntityInfoGetter> methodEntityList(String userId);

    /**
     *Method for getting list of parent entities
     *@param userId user id of the use logged in
     *@param EntityInfoGetter
     *@return List containing possible parent entities
     */
    List<EntityInfoGetter> methodParentEntityList(String userId, String level);

    /**
     * Method for populating list of existing entities of given type
     * @param userId id of the user logged in
     * @param level entity type level
     * @return list of entities
     */
    List<EntityInfoGetter> methodPopulateEntity(String userId, String entityType)
        throws Exception;

    /**
     * Method for updating information related to particular entity
     * @param userId id of the user logged in
     * @param entityInfo containing information to be updated
     * @return TODO
     */
    String methodUpdateEntity(String userId, EntityInfoGetter entityInfo)
        throws Exception;

    /**
     * Method for deletion of entity
     * @param entityId id of entity to be deleted
     * @return TODO
     */
    String methodDeleteEntity(String entity_id) throws Exception;

    /**
     * Method for getting list of possible locations for entity
     * @param userId
     * @return list of locations
     */
    List<EntityInfoGetter> methodLocationList(String user_id)
        throws Exception;
    /**
     * Method for getting list of child entities of an entity
     * @param entityId id of parent entity
     * @return list 
     */
    public List<EntityInfoGetter> getChildEntityList(String entityId);
}
