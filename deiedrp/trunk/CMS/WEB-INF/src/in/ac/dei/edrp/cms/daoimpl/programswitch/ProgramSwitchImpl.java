/*
 * @(#) ProgramSwitchImpl.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
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
package in.ac.dei.edrp.cms.daoimpl.programswitch;

import in.ac.dei.edrp.cms.dao.programswitch.ProgramSwitchConnect;
import in.ac.dei.edrp.cms.domain.programswitch.ProgramSwitchInfoGetter;
import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;

import org.apache.log4j.Logger;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * This file consist of the methods used for setting up
 * the Program Switch setup.
 * @author Ashish
 * @date 14 Mar 2011
 * @version 1.0
 */
public class ProgramSwitchImpl extends SqlMapClientDaoSupport
    implements ProgramSwitchConnect {
    private Logger loggerObject = Logger.getLogger(ProgramSwitchImpl.class);

    /**
     * Method for getting the list of programs-combinations
     * for the concerned university
     * @param input object of the referenced bean
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<ProgramSwitchInfoGetter> getInputRecords(
        ProgramSwitchInfoGetter input) {
        input.setUniversityCode(input.getUserId().substring(1, 5));
        input.setFlag(input.getUserId().substring(1, 5) + "%");

        List<ProgramSwitchInfoGetter> recordList;

        recordList = getSqlMapClientTemplate()
                         .queryForList("programSwitch.getHeaderRecords", input);

        loggerObject.info("In getInputRecords");

        return recordList;
    }

    /**
     * Method for getting the list Switch types
     * defined for the concerned university
     * @param input object of the referenced bean
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<ProgramSwitchInfoGetter> getSwitchTypes(
        ProgramSwitchInfoGetter input) {
        String universityCode = input.getUserId().substring(1, 5);
        List<ProgramSwitchInfoGetter> switchTypeList;
        String GROUP_CODE = "SWTTYP";

        UnivRoleInfoGetter beanObject = new UnivRoleInfoGetter();

        beanObject.setGroupCode(GROUP_CODE);
        beanObject.setUniversityCode(universityCode);

        switchTypeList = getSqlMapClientTemplate()
                             .queryForList("unirolesetup.getComponentsInfo",
                beanObject);

        loggerObject.info("In getSwitchTypes");

        return switchTypeList;
    }

    /**
     * Method for setting up a record
     * of the selected program combination
     * based on the activity(insert/update) for the university
     * @param input object of the referenced bean
     * @return
     */
    
    //Changes By Mandeep
    public String setProgramSwitchDetails(ProgramSwitchInfoGetter input) {
 
        if (input.getActivity().equalsIgnoreCase("insert")) {
        	try{
            getSqlMapClientTemplate().insert("programSwitch.insertRecord", input);
            return "success";
        	}
        	catch(DataIntegrityViolationException e){
        		loggerObject.info("In setProgramSwitchDetails");
        		return "DuplicateEntry"+e.getMessage();        		
        	}
        	catch (Exception e) {
                loggerObject.info("In setProgramSwitchDetails");
        		return "failure";
			}
        } else if (input.getActivity().equalsIgnoreCase("update")) {
        	try{
            int value=getSqlMapClientTemplate().update("programSwitch.updateRecord", input);
            String updateResult=Integer.toString(value);
            return updateResult;
        	}
        	catch(DataIntegrityViolationException e){
        		loggerObject.info("In setProgramSwitchDetails");
        		return "DuplicateEntry"+e.getMessage();        		
        	}
        	catch (Exception e) {
                loggerObject.info("In setProgramSwitchDetails");
        		return "failure";
			}
        }
		return "success";

    }

    /**
     * Method for getting the list of
     * defined records set for the university
     * @param input object of the referenced bean
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<ProgramSwitchInfoGetter> getSetRecords(
        ProgramSwitchInfoGetter input) {
        input.setUniversityCode(input.getUserId().substring(1, 5));
        input.setFlag(input.getUserId().substring(1, 5) + "%");

        List<ProgramSwitchInfoGetter> recordList;

        recordList = getSqlMapClientTemplate()
                         .queryForList("programSwitch.getDefinedRecords", input);

        loggerObject.info("In getSetRecords");

        return recordList;
    }

    /**
     * Method for deleting the selected records from program switch
     * @param items items referenced for deletion
     * @return
     */
    public String deleteProgramSwitchRecords(StringTokenizer items) {
        ProgramSwitchInfoGetter input = new ProgramSwitchInfoGetter();
        //Change By Mandeep Sodhi
        String result="0";
       try{
        while (items.hasMoreTokens()) {
        	input.setFromEntityId(items.nextToken());
        	input.setEntityId(items.nextToken());
            input.setOldProgramId(items.nextToken());
            input.setProgramId(items.nextToken());
            input.setOldBranchId(items.nextToken());
            input.setBranchId(items.nextToken());
            input.setOldSpecializationId(items.nextToken());
            input.setSpecializationId(items.nextToken());
            input.setSemesterCode(items.nextToken());
            input.setOldSemesterCode(items.nextToken());
            input.setSwitchTypeId(items.nextToken());
            input.setSwitchRuleId(items.nextToken());

           int value= getSqlMapClientTemplate().delete("programSwitch.deleteRecord", input);
           result=Integer.toString(value);
        }
        return result;
       }
       catch (Exception e) {
           loggerObject.info("In deleteProgramSwitchRecords");
           return "ForiegnKeyConstraint"+e.getMessage();
           
	}

    }

    /**
     * Method to get the old semesters for the program combination
     * @param input object of the referenced bean
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<ProgramSwitchInfoGetter> getOldSemesters(
			ProgramSwitchInfoGetter input) {
		
		 List<ProgramSwitchInfoGetter> recordList;
		 
		 input.setUniversityCode(input.getProgramId().substring(0,4));		 

	        recordList = getSqlMapClientTemplate()
	                         .queryForList("programSwitch.getOldSemesterRecords", input);

	        loggerObject.info("In getOldSemesters");
		
		return recordList;
	}

	/**
	 * The method picks the details on the basis of input
	 * passed
	 * @param input object of the referenced bean
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<ProgramSwitchInfoGetter> getRecords4Counter(
			ProgramSwitchInfoGetter input) {
		
		List<ProgramSwitchInfoGetter> resultList = new ArrayList<ProgramSwitchInfoGetter>();
		
		if(input.getCreatorId().equalsIgnoreCase("one")){
			
			resultList = getSqlMapClientTemplate()
            .queryForList("programSwitch.getprograms4entity", input);
			
		}else if(input.getCreatorId().equalsIgnoreCase("two")){
			
			resultList = getSqlMapClientTemplate()
            .queryForList("programSwitch.getbranch4entityprogram", input);
			
		}else if(input.getCreatorId().equalsIgnoreCase("three")){
			
			resultList = getSqlMapClientTemplate()
            .queryForList("programSwitch.getspecialization4entityprogrambranch", input);
			
		}

		return resultList;
	}
}
