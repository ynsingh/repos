/*
 * @(#) ManageProgramOfferedByDAO.java
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
package in.ac.dei.edrp.cms.dao.programofferedby;

import in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails;
import java.util.List;

/**
 * Creation date: 02-Feb-2011
 * The behavior of this class is as an interface.
 * This interface contains those method that is used for managing program offered by.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public interface ManageProgramOfferedByDAO {

	/** 
	 * Method getEntityNameList is used for getting the entity list.
	 * @param universityId
	 * @return List of the EntityDetails.
	 * @see in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails
	 */
	List<EntityDetails> getEntityNameList(String universityId);
	
	/** 
	 * Method getProgramNameList is used for getting the program list.
	 * @param entityId
	 * @return List of the EntityDetails.
	 * @see in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails
	 */
	List<EntityDetails> getProgramNameList(String entityId);
	
	/** 
	 * Method getProgramListOfEntity is used for getting the program list.
	 * @param entityDetails
	 * @return List of the EntityDetails.
	 * @see in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails
	 */
	List<List<EntityDetails>> getProgramListOfEntity(EntityDetails entityDetails);
	
	/** 
	 * Method updateEntityDetails is used for updating the employee and seats of an entity
	 * @param entityDetails
	 * @see in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails
	 */
	String updateEntityDetails(EntityDetails entityDetails);
	/** 
	 * Method deleteDesiredEntityDetails is used for deleting the entity details
	 * @param entityId
	 * @param entityData
	 * @see in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails
	 */
	String deleteDesiredEntityDetails(String entityId, String entityData);
	
}
