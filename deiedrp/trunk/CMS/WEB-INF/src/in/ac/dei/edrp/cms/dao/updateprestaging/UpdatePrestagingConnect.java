/**
 * @(#) UpdatePrestagingConnect.java
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
package in.ac.dei.edrp.cms.dao.updateprestaging;

import in.ac.dei.edrp.cms.domain.updateprestaging.UpdatePrestagingInfoGetter;

import java.util.List;

/**
 * The client interface for UpdatePrestaging
 * 
 * @version 1.0 24 MAR 2011
 * @author ROHIT SAXENA
 */
public interface UpdatePrestagingConnect {

    /**
	* Method to populate Error records from prestaging table
	* @param input object of reference bean class
	* @return list contains prestaging Error Records
	*/
	List<UpdatePrestagingInfoGetter> getRecords(UpdatePrestagingInfoGetter input);

    /**
	* Method to delete records
	* @param input object of reference bean class
	* @return String of success or failure
	*/
	String deletePrestagingRecords(List<UpdatePrestagingInfoGetter> input);

    /**
	* Method to Update an record
	* @param input object of reference bean class
	* @return String of success or failure
	*/
	String updatePrestagingDetails(UpdatePrestagingInfoGetter detail);

	
}
