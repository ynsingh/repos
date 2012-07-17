/**
 * @(#) ProgramGroupDAO.java
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

package in.ac.dei.edrp.cms.dao.programgroup;

import in.ac.dei.edrp.cms.domain.programgroup.ProgramGroup;

import java.util.List;

/**
 * This interface consist the list of methods used
 * in ProgramGroupDAOImpl file.
 * @author Ankit Jain
 * @date 26 April 2011
 * @version 1.0
 */
public interface ProgramGroupDAO {
	
	
	/**
     * Method to get the program group details
     * @return List of program 
     */
	List<ProgramGroup> getProgramGroupDetails(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to get the program List
     * @return List of program 
     */
	List<ProgramGroup> getProgramList(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to get the branch list
     * @param programGroup ProgramGroup object
     * @return List of branch
     */
	List<ProgramGroup> getBranchList(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to get the specialization list
     * @param programGroup ProgramGroup object
     * @return List of branch
     */
	List<ProgramGroup> getSpecializationList(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to get the Semester List
     * @param programGroup ProgramGroup object
     * @return List of semester
     */
	List<ProgramGroup> getSemesterList(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to get the group List
     * @param programGroup ProgramGroup object
     * @return List of group
     */
	List<ProgramGroup> getGroup(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to get the subgroup List
     * @param programGroup ProgramGroup object
     * @return List of subgroup
     */
	List<ProgramGroup> getSubGroup(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to save the program Group Details
     * @return String
     */
	String saveDetails(ProgramGroup programGroup, String subGroupTokens)throws Exception;
	
	/**
     * Method to delete the program Group details
     * @return String
     */
	String deleteDetails(ProgramGroup programGroup, String selectedRecordsTokens)throws Exception;
	
	/**
     * Method to fetch the linked Group details
     * @return List of linked group details
     */
	List<ProgramGroup> linkedGroupDetails(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to save the linkedGroup details
     * @return String
     */
//	String saveLinkedGroup(ProgramGroup programGroup, String selectedRecordsTokens, String selectedSubGroupTokens)throws Exception;
	String saveLinkedGroup(ProgramGroup programGroup, String selectedRecordsTokens)throws Exception;
	
	/**
     * Method to update the program Group details
     * @return String
     */
	String updateDetails(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to get the existing group details
     * @return List of existing group
     */
	List<ProgramGroup> getExistingGroup(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to save Group rule details
     * @return String
     */
	String saveProgramGroupRule(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to fetch the Group Rule details
     * @return List of group rule
     */
	List<ProgramGroup> groupRuleDetails(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to delete the Group rule details
     * @param selectedRecordsTokens this is records to be deleted
     * @return String
     */
	String deleteGroupRuleDetails(ProgramGroup programGroup, String selectedRecordsTokens)throws Exception;
	
	/**
     * Method to update the Group rule details
     * @return String
     */
	String updateProgramRuleDetails(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to get group rule details
     * @return String
     */
	List<ProgramGroup> getRules(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to get the group List
     * @param programGroup ProgramGroup object
     * @return List of subgroup
     */
	List<ProgramGroup> getFirstGroup(ProgramGroup programGroup)throws Exception;
	
	/**
     * Method to get the group order
     * @param programGroup ProgramGroup object
     * @return List of subgroup
     */
	String getGroupOrder(ProgramGroup programGroup)throws Exception;
	
	
	/**
     * Method to unlink linked group details
     * @return String
     */
	String unLinkGroup(ProgramGroup programGroup, String selectedRecordsTokens)throws Exception;
	
	/**
     * Method to get the max conditional group order
     * @param programGroup ProgramGroup object
     * @return List of subgroup
     */
	String getMaxConditionalGroupOrder(ProgramGroup programGroup)throws Exception;
}
