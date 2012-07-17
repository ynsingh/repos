/*
 * @(#) EvaluationComponentImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.evaluationcomponent;

import in.ac.dei.edrp.cms.dao.evaluationcomponent.EvaluationComponentConnect;
import in.ac.dei.edrp.cms.domain.evaluationcomponent.EvaluationComponentInfoGetter;
import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;

import org.apache.log4j.Logger;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;
import java.util.StringTokenizer;


/**
 * This file consist of the methods used for setting up
 * the evaluation components.
 * @author Ashish
 * @date 1 Mar 2011
 * @version 1.0
 */
public class EvaluationComponentImpl extends SqlMapClientDaoSupport
    implements EvaluationComponentConnect {
    private Logger loggerObject = Logger.getLogger(EvaluationComponentImpl.class);

    /**
     * This method retrieves the inputs required on the interface
     * @param input object of the bean class
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<EvaluationComponentInfoGetter> getEvaluationComponentList(
        EvaluationComponentInfoGetter input) {
        String universityCode = input.getUserId().substring(1, 5);
        List detailsList;
        UnivRoleInfoGetter beanObject = new UnivRoleInfoGetter();

        beanObject.setUniversityCode(universityCode);

        if (input.getEvaluationComponents().equalsIgnoreCase("components")) {
            beanObject.setGroupCode("EVLCOM");

            detailsList = getSqlMapClientTemplate()
                              .queryForList("evaluation.getComponentsInfo",
                    beanObject);

            return detailsList;
        } else if (input.getEvaluationComponents().equalsIgnoreCase("idTypes")) {
            beanObject.setGroupCode("IDTYPE");

            detailsList = getSqlMapClientTemplate()
                              .queryForList("unirolesetup.getComponentsInfo",
                    beanObject);

            return detailsList;
        } else if (input.getEvaluationComponents()
                            .equalsIgnoreCase("displayType")) {
            beanObject.setGroupCode("DISTYP");
            detailsList = getSqlMapClientTemplate()
                              .queryForList("unirolesetup.getComponentsInfo",
                    beanObject);

            return detailsList;
        }

        return null;
    }

    /**
     * This method sets the record with the
     * selected inputs for the concerned university
     * @param input object of the bean class
     * @return String
     */
    public String setEvaluationDetails(EvaluationComponentInfoGetter input) {
        input.setUniversityCode(input.getUserId().substring(1, 5));
        
        String success="failure";

        try {
            if (input.getActivity().equalsIgnoreCase("insert")) {
                getSqlMapClientTemplate()
                    .insert("evaluation.insertComponent", input);
                
                success="success";
                
            } else if (input.getActivity().equalsIgnoreCase("update")) {
                getSqlMapClientTemplate()
                    .update("evaluation.updateComponent", input);
                
                success="success";
                
            }
        } catch (Exception e) {
            loggerObject.error(" in deleteEvaluationRecords" + e);

        }

        return success;
    }

    /**
     * The method retrieves the list of records
     * already defined for the university
     * @param input object of the bean class
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<EvaluationComponentInfoGetter> getSetRecordsDetails(
        EvaluationComponentInfoGetter input) {
        List recordList;

        input.setUniversityCode(input.getUserId().substring(1, 5));

        recordList = getSqlMapClientTemplate()
                         .queryForList("evaluation.getRecordDetails", input);

        return recordList;
    }

    /**
     * The method deletes the selected records for the concerned university
     * @param input object of the bean class
     * @param items selected evaluation id's to be deleted
     * @return String
     */
    public String deleteEvaluationRecords(EvaluationComponentInfoGetter input,
        StringTokenizer items) {
        input.setUniversityCode(input.getUserId().substring(1, 5));
        
        String success="failure";

        try {
            while (items.hasMoreTokens()) {
                input.setEvaluationId(items.nextToken());

                getSqlMapClientTemplate()
                    .delete("evaluation.deleteEvaluationReocrd", input);
                
                success="success";
            }
        } catch (Exception e) {
            loggerObject.error("in deleteEvaluationRecords" + e);
        }

        return success;
    }
}
