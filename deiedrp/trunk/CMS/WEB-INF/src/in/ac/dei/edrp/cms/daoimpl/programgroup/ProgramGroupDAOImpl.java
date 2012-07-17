/**
 * @(#) ProgramGroupDAOImpl.java
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
 * Redistribution in binary form must reproduce the above copyright
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
package in.ac.dei.edrp.cms.daoimpl.programgroup;

import in.ac.dei.edrp.cms.dao.programgroup.ProgramGroupDAO;
import in.ac.dei.edrp.cms.domain.programgroup.ProgramGroup;

import org.apache.log4j.Logger;
import java.util.List;
import java.util.StringTokenizer;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This file consist of the methods used at the
 * Activity Master process.
 * @author Ankit Jain
 * @date 26 April 2011
 * @version 1.0
 */
public class ProgramGroupDAOImpl extends SqlMapClientDaoSupport implements ProgramGroupDAO{
	
	private Logger loggerObject = Logger.getLogger(ProgramGroupDAOImpl.class);
    
	/**
     * This method will fetch program group Details.
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public List<ProgramGroup> getProgramGroupDetails(ProgramGroup programGroup)throws Exception{
		ProgramGroup programGroupObject;
		List<ProgramGroup> programGroupDetails =  null;
		
		try{
			programGroupObject=new ProgramGroup();
			ProgramGroup programCourseKeyObject=(ProgramGroup)getSqlMapClientTemplate().queryForObject("programGroup.getProgramCourseKey",programGroup);
		   	programGroupObject.setProgramCourseKey(programCourseKeyObject.getProgramCourseKey());
		   	programGroupObject.setUniversityId(programGroup.getUniversityId());
		   	programGroupDetails=getSqlMapClientTemplate().queryForList("programGroup.getProgramGroupList", programGroupObject);
		   	
		   	for(int i=0;i<(programGroupDetails.size());i++){
		   		programGroupObject.setLinkedGroupCode(programGroupDetails.get(i).getLinkedGroupCode());
		   		ProgramGroup object=(ProgramGroup) getSqlMapClientTemplate().queryForObject("programGroup.getLinkedGroupDescription", programGroupObject);
		   	  	
		   		if(object!=null){
		   			programGroupDetails.get(i).setLinkedGroupDescription(object.getLinkedGroupDescription());
		   		}
		   		else{
		   			programGroupDetails.get(i).setLinkedGroupDescription("-");
		   		}
		   		
		   	}
			loggerObject.info("in getProgramGroupDetails");
		}
		catch (Exception e) {
			loggerObject.error("in getProgramGroupDetails" + e);
		}
		return programGroupDetails;
	}
	
	/**
     * This method will fetch program list
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public List<ProgramGroup> getProgramList(ProgramGroup programGroup)throws Exception{
		List<ProgramGroup> programGroupObject=null;
		try{
			programGroupObject=getSqlMapClientTemplate().queryForList("programGroup.getProgramList", programGroup);
			loggerObject.info("in getProgramList");
		}
		catch (Exception e) {
			loggerObject.error("in getProgramList" + e);
		}
		return programGroupObject;
	}
	
	/**
     * This method will fetch branch list
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public List<ProgramGroup> getBranchList(ProgramGroup programGroup)throws Exception{
		List<ProgramGroup> programGroupObject=null;
		try{
			programGroupObject=getSqlMapClientTemplate().queryForList("programGroup.getBranchList", programGroup);
			loggerObject.info("in getBranchList");
		}
		catch (Exception e) {
			loggerObject.error("in getBranchList" + e);			
		}
		return programGroupObject;
	}
	
	/**
     * This method will fetch specialization list
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public List<ProgramGroup> getSpecializationList(ProgramGroup programGroup)throws Exception{
		List<ProgramGroup> programGroupObject=null;
		try{
			programGroupObject=getSqlMapClientTemplate().queryForList("programGroup.getSpecializationList", programGroup);
			loggerObject.info("in getSpecializationList");
		}
		catch (Exception e) {
			loggerObject.error("in getSpecializationList" + e);			
		}
		return programGroupObject;
	}
	
	/**
     * This method will fetch semester list
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public List<ProgramGroup> getSemesterList(ProgramGroup programGroup)throws Exception {
		List<ProgramGroup> programGroupObject=null;
		try{
			programGroupObject=getSqlMapClientTemplate().queryForList("programGroup.getSemesterList", programGroup);
			loggerObject.info("in getSemesterList");
		}
		catch (Exception e) {
			loggerObject.error("in getSemesterList" + e);			
		}
		return programGroupObject;
	}
	
	/**
     * This method will fetch Group Details.
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public List<ProgramGroup> getGroup(ProgramGroup programGroup)throws Exception {
		List<ProgramGroup> programGroupObject=null;
		try{
			programGroupObject=getSqlMapClientTemplate().queryForList("programGroup.getGroup", programGroup);
			loggerObject.info("in getGroup");
		}
		catch (Exception e) {
			loggerObject.error("in getGroup" + e);			
		}
		return programGroupObject;
	}
	
	
	/**
     * This method will fetch subGroup Details.
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public List<ProgramGroup> getSubGroup(ProgramGroup programGroup)throws Exception {
		List<ProgramGroup> programGroupObject=null;
		try{
			ProgramGroup programCourseKeyObject=(ProgramGroup)getSqlMapClientTemplate().queryForObject("programGroup.getProgramCourseKey",programGroup);
		   	programGroup.setProgramCourseKey(programCourseKeyObject.getProgramCourseKey());
			programGroupObject=getSqlMapClientTemplate().queryForList("programGroup.getSubgroup", programGroup);
			loggerObject.info("in getSubGroup");
		}
		catch (Exception e) {
			loggerObject.error("in getSubGroup" + e);			
		}
		return programGroupObject;
	}
	
	/**
     * This method will save the Program Group Details.
     * @param programGroup ProgramGroup object
     * @param programGroup subGroupTokens
     * @throws Exception
     * @return String
     */
	public String saveDetails(ProgramGroup programGroup, String subGroupTokens)throws Exception {
		
	   	StringTokenizer subGroups = new StringTokenizer(subGroupTokens, ",");
	   	ProgramGroup programCourseKeyObject=(ProgramGroup)getSqlMapClientTemplate().queryForObject("programGroup.getProgramCourseKey",programGroup);
	   	programGroup.setProgramCourseKey(programCourseKeyObject.getProgramCourseKey());
	    try{   	
		   	 while(subGroups.hasMoreTokens()){
		   		programGroup.setSubgroupCode(subGroups.nextToken());
		   		getSqlMapClientTemplate().insert("programGroup.saveDetails",programGroup);
		   	 }
		   	loggerObject.info("in saveDetails");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in saveDetails" + e);
	    	return "failure"+e;
		}
		return "success";
	}
	
	/**
     * This method will delete program group Details.
     * @param programGroup ProgramGroup object
     * @param selectedReocrdsTokens
     * @throws Exception
     * @return String
     */
	public String deleteDetails(ProgramGroup programGroup, String selectedRecordsTokens)throws Exception {
		int i=0;
	   	StringTokenizer records = new StringTokenizer(selectedRecordsTokens, ",");
	   	
	    try{   	
		   	 while(records.hasMoreTokens()){
		   		programGroup.setProgramCourseKey(records.nextToken());
		   		programGroup.setGroupCode(records.nextToken());
		   		programGroup.setSubgroupCode(records.nextToken());
		   		i=i+getSqlMapClientTemplate().delete("programGroup.deleteDetails",programGroup);
		   	}
		   	loggerObject.info("in deleteDetails");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in deleteDetails" + e);
	    	return "failure"+e;
		}
		return "success"+i;
	}
	
	/**
     * This method will fetch linked group Details.
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<programGroup>
     */
	@SuppressWarnings("unchecked")
	public List<ProgramGroup> linkedGroupDetails(ProgramGroup programGroup)throws Exception {
		List<ProgramGroup> linkedGroup=null;
	   	try{   	
	   		linkedGroup=getSqlMapClientTemplate().queryForList("programGroup.linkedGroupList",programGroup);
		   	loggerObject.info("in linkedGroupDetails");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in linkedGroupDetails" + e);
		}
		return linkedGroup;
	}
	
	/**
     * This method will save the program group Details.
     * @param programGroup ProgramGroup object
     * @param selectedRecordsTokens
     * @throws Exception
     * @return String
     */
	public String saveLinkedGroup(ProgramGroup programGroup, String selectedRecordsTokens)throws Exception {
		
	   	StringTokenizer records = new StringTokenizer(selectedRecordsTokens, ",");
//	   	StringTokenizer subGroup = new StringTokenizer(selectedSubGroupTokens, ",");
	    try{ 
//	    	while(subGroup.hasMoreTokens()){
//	    		programGroup.setSubgroupCode(subGroup.nextToken());
			   	 while(records.hasMoreTokens()){
			   		programGroup.setProgramCourseKey(records.nextToken());
			   		programGroup.setGroupCode(records.nextToken());
			   		programGroup.setLinkedGroup(records.nextToken());
			   		getSqlMapClientTemplate().update("programGroup.updateLinkedGroup",programGroup);
			   	 }
//	    	}
		   	loggerObject.info("in saveLinkedGroup");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in saveLinkedGroup" + e);
	    	return "failure"+e;
		}
		return "success";
	}
	
	/**
     * This method will update programGroup Details.
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return String
     */
	public String updateDetails(ProgramGroup programGroup)throws Exception {
		int i=0;
		try{   	
	   		i=getSqlMapClientTemplate().update("programGroup.updateDetails",programGroup);
	   		loggerObject.info("in updateDetails");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in updateDetails" + e);
	    	return "failure"+e;
		}
		return "success"+i;
	}
	
	
	/**
     * This method will delete the ActivityMaster Details.
     * @param programGroup ProgramGroup object 
     * @return List<ProgramGroup>
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public List<ProgramGroup> getExistingGroup(ProgramGroup programGroup)throws Exception {
		List<ProgramGroup> existingGroup=null;
	   	try{   	
	   		existingGroup=getSqlMapClientTemplate().queryForList("programGroup.existingGroupList",programGroup);
		   	loggerObject.info("in getExistingGroup");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in getExistingGroup" + e);
		}
		return existingGroup;
	}
	
	
	/**
     * This method will save the Group Rule Details.
     * @param programGroup ProgramGroup object 
     * @throws Exception
     * @return String
     */
	public String saveProgramGroupRule(ProgramGroup programGroup)throws Exception {
		
		ProgramGroup programGroupObject;
	   	try{ 
	   		programGroupObject=new ProgramGroup();
	   		programGroupObject.setGroupCode(programGroup.getGroupCode());
	   		programGroupObject.setSubgroupCode(programGroup.getSubgroupCode());
	   		programGroupObject.setGroupRule(programGroup.getGroupRule());
	   		programGroupObject.setCreatorId(programGroup.getCreatorId());
	   		ProgramGroup programCourseKeyObject=(ProgramGroup)getSqlMapClientTemplate().queryForObject("programGroup.getProgramCourseKey",programGroup);
		   	programGroupObject.setProgramCourseKey(programCourseKeyObject.getProgramCourseKey());
		   	getSqlMapClientTemplate().insert("programGroup.saveProgramGroupRule",programGroupObject);
		   	loggerObject.info("in saveProgramGroupRule");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in saveProgramGroupRule" + e);
	    	return "failure"+e;
		}
		return "success";
	}
	
	/**
     * This method will fetch group rule Details.
     * @param programGroup ProgramGroup object 
     * @throws Exception
     * @return List<ProgramGroup> group rule details
     */
	@SuppressWarnings("unchecked")
	public List<ProgramGroup> groupRuleDetails(ProgramGroup programGroup)throws Exception{
		List<ProgramGroup> groupRuleDetails=null;
	   	try{   	
	   		groupRuleDetails=getSqlMapClientTemplate().queryForList("programGroup.groupRuleDetails",programGroup);
		   	loggerObject.info("in groupRuleDetails");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in groupRuleDetails" + e);
		}
		return groupRuleDetails;
	}
	
	/**
     * This method will delete the group rule Details.
     * @param programGroup ProgramGroup object 
     * @throws Exception
     * @return String
     */
	public String deleteGroupRuleDetails(ProgramGroup programGroup, String selectedRecordsTokens)throws Exception {
		int i=0;
	   	StringTokenizer records = new StringTokenizer(selectedRecordsTokens, ",");
	    try{   	
		   	 while(records.hasMoreTokens()){
		   		programGroup.setProgramCourseKey(records.nextToken());
		   		programGroup.setGroupCode(records.nextToken());
		   		programGroup.setSubgroupCode(records.nextToken());
		   		i=i+getSqlMapClientTemplate().delete("programGroup.deleteGroupRuleDetails",programGroup);
		   	 }
		   	loggerObject.info("in deleteDetails");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in deleteDetails" + e);
	    	return "failure";
		}
		return "success"+i;
	}
	
	/**
     * This method will update the program rule Details.
     * @param programGroup ProgramGroup object
     * @throws Exception 
     * @return String
     */
	public String updateProgramRuleDetails(ProgramGroup programGroup)throws Exception {
		int i=0;
		try{   	
	   		i=getSqlMapClientTemplate().update("programGroup.updateProgramRuleDetails",programGroup);
	   		loggerObject.info("in updateProgramRuleDetails");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in updateProgramRuleDetails" + e);
	    	return "failure"+e;
		}
		return "success"+i;
	}

	/**
     * This method will update the program rule Details.
     * @param programGroup ProgramGroup object
     * @throws Exception 
     * @return String
     */
	@SuppressWarnings("unchecked")
	public List<ProgramGroup> getRules(ProgramGroup programGroup) throws Exception {
		List<ProgramGroup> ruleDetails=null;
		try{ 
			ruleDetails=getSqlMapClientTemplate().queryForList("programGroup.getRules",programGroup);
			loggerObject.info("in getRules");
		}
		catch (Exception e) {
	    	loggerObject.error("in getRules" + e);
		}
		return ruleDetails;
	}
	
	/**
     * This method will fetch subGroup Details.
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	@SuppressWarnings("unchecked")
	public List<ProgramGroup> getFirstGroup(ProgramGroup programGroup)throws Exception {
		List<ProgramGroup> programGroupObject=null;
		try{
			programGroupObject=getSqlMapClientTemplate().queryForList("programGroup.getFirstGroup", programGroup);
			loggerObject.info("in getFirstGroup");
		}
		catch (Exception e) {
			loggerObject.error("in getFirstGroup" + e);			
		}
		return programGroupObject;
	}
	
	/**
     * This method will take Group Order.
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	public String getGroupOrder(ProgramGroup programGroup)throws Exception {
		ProgramGroup object=null;
		String groupOrder = null;
		try{
			object=(ProgramGroup)getSqlMapClientTemplate().queryForObject("programGroup.getGroupOrder", programGroup);
			loggerObject.info("in getGroupOrder");
			if(object!=null){
				groupOrder=object.getGroupOrder();
			}
			else{
				groupOrder="0";
			}
		}
		catch (Exception e) {
			loggerObject.error("in getGroupOrder" + e);			
		}
		return groupOrder;
	}
	
	/**
     * This method will delete program group Details.
     * @param programGroup ProgramGroup object
     * @param selectedReocrdsTokens
     * @throws Exception
     * @return String
     */
	public String unLinkGroup(ProgramGroup programGroup, String selectedRecordsTokens)throws Exception {
		
	   	StringTokenizer records = new StringTokenizer(selectedRecordsTokens, ",");
	   	
	    try{   	
		   	 while(records.hasMoreTokens()){
		   		programGroup.setProgramCourseKey(records.nextToken());
		   		programGroup.setGroupCode(records.nextToken());
		   		programGroup.setSubgroupCode(records.nextToken());
		   		getSqlMapClientTemplate().update("programGroup.unlinkLinkedGroup",programGroup);
		   	 }
		   	loggerObject.info("in unLinkGroup");
	    }
	    catch (Exception e) {
	    	loggerObject.error("in unLinkGroup" + e);
	    	return "failure";
		}
		return "success";
	}

	/**
     * This method will take max Group Order.
     * @param programGroup ProgramGroup object
     * @throws Exception
     * @return List<ProgramGroup>
     */
	public String getMaxConditionalGroupOrder(ProgramGroup programGroup)throws Exception {
		ProgramGroup object=null;
		String maxGroupOrder = null;
		try{
			object=(ProgramGroup)getSqlMapClientTemplate().queryForObject("programGroup.getMaxConditionalGroupOrder", programGroup);
			loggerObject.info("in getMaxConditionalGroupOrder");
			if(object!=null){
				maxGroupOrder=object.getGroupOrder();
			}
			else{
				maxGroupOrder="0";
			}
		}
		catch (Exception e) {
			loggerObject.error("in getMaxConditionalGroupOrder" + e);			
		}
		return maxGroupOrder;
	}
}
