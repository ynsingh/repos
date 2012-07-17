/*
 * @(#) CreateProgramOfferedByDAO.java
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
 * Creation date: 31-Jan-2011
 * The behavior of this class is as an interface.
 * This interface contains those method that is used for creating program offered by.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public interface CreateProgramOfferedByDAO {

	/** 
	 * Method getEntityNameList is used for getting the entity list of an university.
	 * @param universityId
	 * @return List of the EntityDetails.
	 * @see in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails
	 */
	List<EntityDetails> getEntityNameList(String universityId);
	
	/** 
	 * Method getProgramBranchSpecializationList is used for getting all the list of program,
	 * branch and specialization of an university.
	 * @param entityDetails
	 * @return List of the EntityDetails.
	 * @see in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails
	 */
	List<List<EntityDetails>> getProgramBranchSpecializationList(EntityDetails entityDetails);
	
	/** 
	 * Method insertProgramOfferedBy is used for inserting the program details with respect to entity
	 * @param programDetailList
	 * @param entityId
	 * @param creatorId
	 */
	
	String insertProgramOfferedBy(final String programDetailList,final String entityId,
			final String creatorId);
	
	/** 
	 * Method existingEntityDetails is used for getting existing entity details
	 * @param entityDetails
	 * @return List of the EntityDetails.
	 */
	List<EntityDetails> existingEntityDetails(EntityDetails entityDetails);
	
}
