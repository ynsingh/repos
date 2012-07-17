/*
 * @(#) SwitchRuleImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.switchrule;

import in.ac.dei.edrp.cms.dao.switchrule.SwitchRuleConnect;
import in.ac.dei.edrp.cms.domain.switchrule.SwitchRuleInfoGetter;
import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;

import org.apache.log4j.Logger;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;
import java.util.StringTokenizer;


/**
 *This file consist of the methods used for setting up
 * the switch rule setup.
 * @author Ashish
 * @date 26 Feb 2011
 * @version 1.0
 */
public class SwitchRuleImpl extends SqlMapClientDaoSupport
    implements SwitchRuleConnect {
    private Logger loggerObject = Logger.getLogger(SwitchRuleImpl.class);

    /**
     * The method retrieves the list of rules three details
     * @param input object of bean
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<SwitchRuleInfoGetter> getRule3Details(
        SwitchRuleInfoGetter input) {
        String universityCode = input.getUserId().substring(1, 5);
        List ruleCode3List;
        String GROUP_CODE = "SWTRL3";

        UnivRoleInfoGetter beanObject = new UnivRoleInfoGetter();

        beanObject.setGroupCode(GROUP_CODE);
        beanObject.setUniversityCode(universityCode);

        ruleCode3List = getSqlMapClientTemplate()
                            .queryForList("unirolesetup.getComponentsInfo",
                beanObject);

        return ruleCode3List;
    }

    /**
     * The method retrieves the list of rules four details
     * @param input object of bean
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<SwitchRuleInfoGetter> getRule4Details(
        SwitchRuleInfoGetter input) {
        String universityCode = input.getUserId().substring(1, 5);
        List ruleCode4List;
        String GROUP_CODE = "SWTRL4";

        UnivRoleInfoGetter beanObject = new UnivRoleInfoGetter();

        beanObject.setGroupCode(GROUP_CODE);
        beanObject.setUniversityCode(universityCode);

        ruleCode4List = getSqlMapClientTemplate()
                            .queryForList("unirolesetup.getComponentsInfo",
                beanObject);

        return ruleCode4List;
    }

    /**
     * The method retrieves the list of rules five details
     * @param input object of bean
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<SwitchRuleInfoGetter> getRule5Details(
        SwitchRuleInfoGetter input) {
        String universityCode = input.getUserId().substring(1, 5);
        List ruleCode5List;
        String GROUP_CODE = "SWTRL5";

        UnivRoleInfoGetter beanObject = new UnivRoleInfoGetter();

        beanObject.setGroupCode(GROUP_CODE);
        beanObject.setUniversityCode(universityCode);

        ruleCode5List = getSqlMapClientTemplate()
                            .queryForList("unirolesetup.getComponentsInfo",
                beanObject);

        return ruleCode5List;
    }

    /**
     * The method retrieves the list of rules six details
     * @param input object of bean
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<SwitchRuleInfoGetter> getRule6Details(
        SwitchRuleInfoGetter input) {
        String universityCode = input.getUserId().substring(1, 5);
        List ruleCode6List;
        String GROUP_CODE = "SWTRL6";

        UnivRoleInfoGetter beanObject = new UnivRoleInfoGetter();

        beanObject.setGroupCode(GROUP_CODE);
        beanObject.setUniversityCode(universityCode);

        ruleCode6List = getSqlMapClientTemplate()
                            .queryForList("unirolesetup.getComponentsInfo",
                beanObject);

        return ruleCode6List;
    }

    /**
     * Method inserts a record for a rule id with the selected inputs
     * @param input object of bean
     * @return String
     */
    public String insertRuleDetails(SwitchRuleInfoGetter input) {
        int code;
        String ruleCode = null;

        SwitchRuleInfoGetter ruleCodeObject;
        
        /*
         * university id of the user
         */
        input.setRuleDesc3(input.getUserId().substring(1,5));

        try {
            if (input.getActivity().equalsIgnoreCase("insert")) {
                ruleCodeObject = (SwitchRuleInfoGetter) getSqlMapClient()
                                                            .queryForObject("switchrule.getmaxrulecode",input);

                if (ruleCodeObject.getRuleId() == null) {
                    code = 1;
                } else {
                    code = Integer.parseInt(ruleCodeObject.getRuleId()) + 1;
                }

                ruleCode = String.format("%03d", code);

                input.setRuleId(ruleCode);
               

                getSqlMapClientTemplate().insert("switchrule.insertrules", input);
                
                return "success";
            } else if (input.getActivity().equalsIgnoreCase("update")) {
                getSqlMapClientTemplate()
                    .update("switchrule.updaterulesforid", input);
                
                return "success";
            }
        } catch (Exception e) {
            loggerObject.error("exception in insertRuleDetails" + e);
        }

        return null;
    }

    /**
     * The method retrieves the list of set records in the database
     * @param input object of bean
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<SwitchRuleInfoGetter> getRuleRecordsDetails(
        SwitchRuleInfoGetter input) {
        List recordsDetailsList;
        
        /*
         * university id of the user
         */
        input.setComponentId(input.getUserId().substring(1,5));

        recordsDetailsList = getSqlMapClientTemplate()
                                 .queryForList("switchrule.getSetRecords", input);

        return recordsDetailsList;
    }

    /**
     * The method deletes the selected record for the set rule id
     * @param input object of bean
     * @param items the selected items for deletion
     * @return String
     */
    public String deleteSwitchRuleRecords(SwitchRuleInfoGetter input,
        StringTokenizer items) {
    	 /*
         * university id of the user
         */
        input.setRuleDesc3(input.getUserId().substring(1,5));
        try {
            while (items.hasMoreTokens()) {
                input.setRuleId(items.nextToken());

                getSqlMapClientTemplate()
                    .delete("switchrule.deleteSwitchReocrd", input);
            }

            return "success";
        } catch (Exception e) {
            loggerObject.error(" in deleteSwitchRuleRecords" + e);
        }

        return null;
    }
}
