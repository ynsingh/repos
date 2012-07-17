/**
 * @(#) ConsolidateMarksheetService.java
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
package in.ac.dei.edrp.cms.dao.consolidatedchart;

import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedMarkSheetInfo;
import java.util.List;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedMarkSheetInfo;

/**
 * The client interface for ConsolidatedMarksheet
 * 
 * @version 1.0 07 Sep 2011
 * @author ROHIT SAXENA
 */
public interface ConsolidatedMarksheetService {
	
	/**
	* Method to populate Student list with details
	* @param input object of reference bean class
	* @return list contains students detail
	*/
	List<ConsolidatedMarkSheetInfo> getStudentDetails(ConsolidatedMarkSheetInfo consolidatedMarkSheetInfo);
	
	/**
	* Method to populate Marks details of students
	* @param input object of reference bean class
	* @return list contains student marks
	*/
	List<ConsolidatedMarkSheetInfo> getMarksDetails(ConsolidatedMarkSheetInfo consolidatedMarkSheetInfo);

	/**
	* Method to populate required entity list
	* @param input object of reference bean class
	* @return list contains entities
	*/
	List<ConsolidatedMarkSheetInfo> getEntitiesList(
			ConsolidatedMarkSheetInfo input);

	/**
	* Method to populate programs
	* @param input object of reference bean class
	* @return list contains programs
	*/
	List<ConsolidatedMarkSheetInfo> getProgramList(
			ConsolidatedMarkSheetInfo input);

	/**
	* Method to populate branchs
	* @param input object of reference bean class
	* @return list contains branchs
	*/
	List<ConsolidatedMarkSheetInfo> getBranchList(
			ConsolidatedMarkSheetInfo input);

	/**
	* Method to populate specializations
	* @param input object of reference bean class
	* @return list contains specializations
	*/
	List<ConsolidatedMarkSheetInfo> getSpecializationList(
			ConsolidatedMarkSheetInfo input);
	
	/**
	* Method to populate address detail of entity
	* @param input object of reference bean class
	* @return bean class
	*/
	ConsolidatedMarkSheetInfo getAddressInfo(ConsolidatedMarkSheetInfo input);

}
