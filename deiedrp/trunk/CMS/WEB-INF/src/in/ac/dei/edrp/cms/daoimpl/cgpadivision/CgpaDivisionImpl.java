/*
 * @(#) CgpaDivisionImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.cgpadivision;

import in.ac.dei.edrp.cms.dao.cgpadivision.CgpaDivisionConnect;
import in.ac.dei.edrp.cms.domain.cgpadivision.CgpaDivisionInfoGetter;
import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;

import org.apache.log4j.Logger;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;
import java.util.StringTokenizer;


/**
 *This file consist of the methods used for setting up
 * the CGPA division setup.
 * @author Ashish
 * @date 8 Mar 2011
 * @version 1.0
 */
public class CgpaDivisionImpl extends SqlMapClientDaoSupport
    implements CgpaDivisionConnect {
    private Logger loggerObject = Logger.getLogger(CgpaDivisionImpl.class);

    /**
    * Method retrieves list of divisions for the concerned university
    * @param input Object of the referenced bean class
    * @return List
    */
    @SuppressWarnings("unchecked")
    public List<CgpaDivisionInfoGetter> getDivisions(
        CgpaDivisionInfoGetter input) {
        List<CgpaDivisionInfoGetter> divisionList;
        
        loggerObject.info("In getDivisions");

        UnivRoleInfoGetter beanObject = new UnivRoleInfoGetter();

        beanObject.setGroupCode("DVSCOD");
        beanObject.setUniversityCode(input.getUserId().substring(1, 5));

        divisionList = getSqlMapClientTemplate()
                           .queryForList("cgpaDivision.getDistinctDivisions",
                beanObject);

        return divisionList;
    }

    /**
    * This method is used for setting up the details for the divisions
    * on the basis of activity(insert/update)
    * @param input Object of the referenced bean class
    * @return String
    */
    public String setDivisionDetails(CgpaDivisionInfoGetter input) {
        input.setUniversityCode(input.getUserId().substring(1, 5));
        
        loggerObject.info("In setDivisionDetails");

        if (input.getActivity().equalsIgnoreCase("insert")) {
            getSqlMapClientTemplate()
                .insert("cgpaDivision.insertCGPADivision", input);
        } else if (input.getActivity().equalsIgnoreCase("update")) {
            getSqlMapClientTemplate()
                .update("cgpaDivision.updateCGPADivision", input);
        }

        return "Success";
    }

    /**
    * This method retrieves the divisions already defined for the university.
    * @param input Object of the referenced bean class
    * @return List
    */
    @SuppressWarnings("unchecked")
    public List<CgpaDivisionInfoGetter> getSetDivisionRecords(
        CgpaDivisionInfoGetter input) {
        input.setUniversityCode(input.getUserId().substring(1, 5));
        
        loggerObject.info("In getSetDivisionRecords");

        List<CgpaDivisionInfoGetter> recordList;

        recordList = getSqlMapClientTemplate()
                         .queryForList("cgpaDivision.getRecords", input);

        return recordList;
    }

    /**
    * Method for deleting records(divisions)for the concerned university
    * @param input Object of the referenced bean class
    * @param items selected items to be deleted
    * @return String
    */
    public String deleteDivisionRecords(CgpaDivisionInfoGetter input,
        StringTokenizer items) {
        input.setUniversityCode(input.getUserId().substring(1, 5));
        
        loggerObject.info("In deleteDivisionRecords");

        while (items.hasMoreTokens()) {
            input.setDivisionId(items.nextToken());

            getSqlMapClientTemplate()
                .delete("cgpaDivision.deleteDivisionRecords", input);
        }

        return "success";
    }
}
