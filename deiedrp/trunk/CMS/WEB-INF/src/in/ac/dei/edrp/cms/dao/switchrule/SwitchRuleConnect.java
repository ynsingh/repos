/*
 * @(#) SwitchRuleConnect.java
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
package in.ac.dei.edrp.cms.dao.switchrule;

import in.ac.dei.edrp.cms.domain.switchrule.SwitchRuleInfoGetter;

import java.util.List;
import java.util.StringTokenizer;


/**
 * This interface consist the list of methods used
 * in SwitchRuleImpl file.
 * @author Ashish
 * @date 26 Jan 2011
 * @version 1.0
 */
public interface SwitchRuleConnect {
    /**
     * The method retrieves the list of rules three details
     * @param input object of bean
     * @return List
     */
    List<SwitchRuleInfoGetter> getRule3Details(SwitchRuleInfoGetter input);

    /**
     * The method retrieves the list of rules four details
     * @param input object of bean
     * @return List
     */
    List<SwitchRuleInfoGetter> getRule4Details(SwitchRuleInfoGetter input);

    /**
     * The method retrieves the list of rules five details
     * @param input object of bean
     * @return List
     */
    List<SwitchRuleInfoGetter> getRule5Details(SwitchRuleInfoGetter input);

    /**
     * The method retrieves the list of rules six details
     * @param input object of bean
     * @return List
     */
    List<SwitchRuleInfoGetter> getRule6Details(SwitchRuleInfoGetter input);

    /**
     * Method inserts a record for a rule id with the selected inputs
     * @param input object of bean
     * @return String
     */
    String insertRuleDetails(SwitchRuleInfoGetter input);

    /**
     * The method retrieves the list of set records in the database
     * @param input object of bean
     * @return List
     */
    List<SwitchRuleInfoGetter> getRuleRecordsDetails(SwitchRuleInfoGetter input);

    /**
     * The method deletes the selected record for the set rule id
     * @param input object of bean
     * @param items the selected items for deletion
     * @return String
     */
    String deleteSwitchRuleRecords(SwitchRuleInfoGetter input,
        StringTokenizer items);
}
