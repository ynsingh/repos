/*
 * @(#) ProgramTermDetailsDaoIml.java
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
package in.ac.dei.edrp.cms.daoimpl.programmaster;

import in.ac.dei.edrp.cms.dao.programmaster.ProgramTermDetailsDao;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramTermDetailsInfoGetter;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * Implementation class for ProgramTermDetailsDao interface
 * @author Manpreet Kaur
 * @date 24-02-2011
 * @version 1.0
 */
public class ProgramTermDetailsDaoImpl extends SqlMapClientDaoSupport
    implements ProgramTermDetailsDao {
    private static Logger logObj = Logger.getLogger(ProgramTermDetailsDaoImpl.class);
    TransactionTemplate transactionTemplate = null;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * Method for inserting details of a term into database
     * @param inputObj
     * @return String
     */
    //changes by Mandeep
    public String insertTermDetails(ProgramTermDetailsInfoGetter inputObj) {
        try {
            getSqlMapClientTemplate()
                .insert("ProgTermDetail.insertTermDetails", inputObj);
        }
        catch(DataIntegrityViolationException e){
        	return "duplicateError"+e.getMessage();
        }
        catch (Exception e) {
            logObj.error(e);

            return "insertError"+e.getMessage();
        }

        return null;
    }

    /**
     * Method for getting list of all possible semesters
     * @param universityId
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<ProgramTermDetailsInfoGetter> getSemesterList(
        String universityId) {
        ProgramTermDetailsInfoGetter object = new ProgramTermDetailsInfoGetter();
        object.setProgramId(universityId);
        object.setCode("SEMCOD");

        List<ProgramTermDetailsInfoGetter> semList = getSqlMapClientTemplate()
                                                         .queryForList("ProgTermDetail.getCodeListFromSysTwo",
                object);

        return semList;
    }

    /**
     * Method for getting list of all possible semester groups
     * @param universityId
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<ProgramTermDetailsInfoGetter> getSemesterGroupList(
        String universityId) {
    	
   	
        ProgramTermDetailsInfoGetter object = new ProgramTermDetailsInfoGetter();
        object.setProgramId(universityId);
        object.setCode("SEMGRP");
        
System.out.println("coming here");
        List<ProgramTermDetailsInfoGetter> semList = getSqlMapClientTemplate()
                                                         .queryForList("ProgTermDetail.getCodeListFromSysTwo",
                object);
System.out.println("getting group list  "+semList.size());
        return semList;
    }

    /**
     * Method for getting list of sequences for a program
     * @param programId
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<ProgramTermDetailsInfoGetter> getProgSequenceList(
        String programId) {
        List<ProgramTermDetailsInfoGetter> seqList = getSqlMapClientTemplate()
                                                         .queryForList("ProgTermDetail.getSequenceNumber",
                programId);

        return seqList;
    }

    /**
     * Method for getting list of programs for manage
     * @param programId
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<ProgramTermDetailsInfoGetter> getProgListForManage(
        String programId) {
        List<ProgramTermDetailsInfoGetter> seqList = getSqlMapClientTemplate()
                                                         .queryForList("ProgTermDetail.getProgramForManage",
                programId);

        return seqList;
    }

    /**
     * Method for getting list of semesters existing in a program
     * @param programId
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<ProgramTermDetailsInfoGetter> getProgramSemList(
        String programId) {
        ProgramTermDetailsInfoGetter object = new ProgramTermDetailsInfoGetter();
        object.setProgramId(programId);
        object.setCode("SEMCOD");

        List<ProgramTermDetailsInfoGetter> semList = getSqlMapClientTemplate()
                                                         .queryForList("ProgTermDetail.getSemesterForManage",
                object);

        return semList;
    }

    /**
     * Method for getting program term details for managing purpose
     * @param object
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<ProgramTermDetailsInfoGetter> getTermDetailForManage(
        ProgramTermDetailsInfoGetter object) {
        List<ProgramTermDetailsInfoGetter> semList = getSqlMapClientTemplate()
                                                         .queryForList("ProgTermDetail.selectFromTermDetails",
                object);

        return semList;
    }

    /**
     * Method for update details of a term into database
     * @param inputObj
     * @return String
     */
    //changed by Mandeep
    public String updateTermDetails(ProgramTermDetailsInfoGetter inputObj) {
    	
    	String result = "S";
    	
        try {
        	
        	ProgramTermDetailsInfoGetter detailsInfoGetter;
        	detailsInfoGetter=(ProgramTermDetailsInfoGetter) getSqlMapClientTemplate().queryForObject("ProgTermDetail.getcountforProgram",inputObj);
        	
        	if(detailsInfoGetter!=null){
        	
        	if(!(detailsInfoGetter.getOldSemesterStartDate().equalsIgnoreCase(inputObj.getSemesterStartDate()))
        			||(!detailsInfoGetter.getOldSemesterEndDate().equalsIgnoreCase(inputObj.getSemesterEndDate()))){        		
        		result="E";
        	}else{
        	  try{
        		int value=getSqlMapClientTemplate()
                .update("ProgTermDetail.updateTermDetails", inputObj);
        		String count=Integer.toString(value);
        		return count;
        	  }
        	  catch (Exception e) {
                  logObj.error(e);
                  result= "updateError"+e.getMessage();
			}
        		

        		
        	} 
        	}else{
        		try{
        		int value=getSqlMapClientTemplate()
                .update("ProgTermDetail.updateTermDetails", inputObj);
        		String count=Integer.toString(value);
        		return count;        		
        		}
        		catch (Exception e) {
                    logObj.error(e);
                    result= "updateError"+e.getMessage();
				}
        		
        	}
        } catch (Exception e) {
            logObj.error(e);
            result= "updateError"+e.getMessage();
        }
        return result;
       }

    /**
     * Method for delete details of a term into database
     * @param inputObj
     * @return String
     * @throws Exception 
     */
    public String deleteTermDetails(ProgramTermDetailsInfoGetter inputObj) throws Exception {
        try {
           int value= getSqlMapClientTemplate()
                .delete("ProgTermDetail.deleteTermDetails", inputObj);
           String count=Integer.toString(value);
           return count;
        
		}catch(DataIntegrityViolationException e1){
        	return "Error"+e1.getMessage();
        }
        catch (Exception e) {
            logObj.error(e);
            System.out.println(e);

            return "F"+e.getMessage();
        }
    }
}
