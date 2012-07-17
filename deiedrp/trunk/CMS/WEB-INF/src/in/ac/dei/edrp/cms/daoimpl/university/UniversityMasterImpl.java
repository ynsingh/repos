/*
 * @(#) UniversityMasterImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.university;

import in.ac.dei.edrp.cms.dao.university.UniversityMasterConnect;
import in.ac.dei.edrp.cms.domain.university.UniversityMasterInfoGetter;

import org.apache.log4j.Logger;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;


/**
 * This file consist of the methods used at the
 * creating and managing university setup
 * @author Ashish
 * @date 9 Feb 2011
 * @version 1.0
 */
public class UniversityMasterImpl extends SqlMapClientDaoSupport
    implements UniversityMasterConnect {
    private Logger loggerObject = Logger.getLogger(UniversityMasterImpl.class);

    /**
         * This method is used to create a university
         * @param userId id of the user who is creating the university
         * @param universityName name of the university
         * @param sessionStartDate session start date of the university
         * @param sessionEndDate session end date of the university
         * @param universityAddress address of the university
         * @param universityCity city in which the university is situated
         * @param universityState state in which the university is situated
         * @param universityPincode pin code of the university
         * @param universityPhoneNumber contact number of the university
         * @param universityOtherPhoneNumber other contact number of the university
         * @param universityFaxNumber fax number of the university
         * @param nickName nickname of the university
         * @return String
         */
    public String defineUniversityCreation(String userId,
        String universityName, String sessionStartDate, String sessionEndDate,
        String universityAddress, String universityCity,
        String universityState, String universityPincode,
        String universityPhoneNumber, String universityOtherPhoneNumber,
        String universityFaxNumber, String nickName,String country) {
        UniversityMasterInfoGetter univCodeObject;

        UniversityMasterInfoGetter beanObject = new UniversityMasterInfoGetter();

        int code;
        String universityCode = null;

        try {
            univCodeObject = (UniversityMasterInfoGetter) getSqlMapClient()
                                                              .queryForObject("universityMaster.getmaxuniversitycode");

            if (univCodeObject.getUniversityCode() == null) {
                code = 1;
            } else {
                code = Integer.parseInt(univCodeObject.getUniversityCode()) +
                    1;
            }

            universityCode = String.format("%04d", code);

            beanObject.setUniversityCode(universityCode);
            beanObject.setUniversityCreatorID(userId);
            beanObject.setUniversityName(universityName);
            beanObject.setSessionStartDate(sessionStartDate);
            beanObject.setSessionEndDate(sessionEndDate);
            beanObject.setUniversityAddress(universityAddress);
            beanObject.setUniversityCity(universityCity);
            beanObject.setUniversityState(universityState);
            beanObject.setUniversityPincode(universityPincode);
            beanObject.setUniversityPhoneNumber(universityPhoneNumber);
            beanObject.setUniversityOtherPhoneNumber(universityOtherPhoneNumber);
            beanObject.setUniversityFaxNumber(universityFaxNumber);
            beanObject.setNickName(nickName);
            beanObject.setCountryName(country);

            getSqlMapClientTemplate()
                .insert("universityMaster.createuniversity", beanObject);
        } catch (Exception e) {
            loggerObject.error("exception in defineUniversityCreation" + e);
        }

        return "success";
    }

    /**
     * This method gets the list of universities created
     * along with there details
     * @param userId userId of the user who is accessing the application
     * @param flag counter to get details of the university
     * @return list of universities
     */
    @SuppressWarnings("unchecked")
    public List<UniversityMasterInfoGetter> getUniversitiesList(String userId,
        String flag) {
        List universityDetailsList;

        try {
            if (flag.equalsIgnoreCase("one")) {
                universityDetailsList = getSqlMapClientTemplate()
                                            .queryForList("universityMaster.getUniversityswithnicknames");

                return universityDetailsList;
            } else if (flag.equalsIgnoreCase("two")) {
                universityDetailsList = getSqlMapClientTemplate()
                                            .queryForList("universityMaster.getUniversitys");

                return universityDetailsList;
            }
        } catch (Exception e) {
            loggerObject.error("in getUniversitiesList" + e);
        }

        return null;
    }

    /**
     *This method updates the basic information of the university
     * @param userId id of the user who is creating the university
     * @param universityName name of the university
     * @param sessionStartDate session start date of the university
     * @param sessionEndDate session end date of the university
     * @param universityAddress address of the university
     * @param universityCity city in which the university is situated
     * @param universityState state in which the university is situated
     * @param universityPincode pin code of the university
     * @param universityPhoneNumber contact number of the university
     * @param universityOtherPhoneNumber other contact number of the university
     * @param universityFaxNumber fax number of the university
     * @param nickName nickname of the university
     * @return String
     */
    public String updateUniversityDetails(String userId, String universityName,
        String sessionStartDate, String sessionEndDate,
        String universityAddress, String universityCity,
        String universityState, String universityPincode,
        String universityPhoneNumber, String universityOtherPhoneNumber,
        String universityFaxNumber, String universityCode, String nickName,
        String flag,String countryName) {
        UniversityMasterInfoGetter beanObject = new UniversityMasterInfoGetter();

        beanObject.setUniversityCode(universityCode);
        beanObject.setModifierID(userId);
        beanObject.setUniversityName(universityName);
        beanObject.setSessionStartDate(sessionStartDate);
        beanObject.setSessionEndDate(sessionEndDate);
        beanObject.setUniversityAddress(universityAddress);
        beanObject.setUniversityCity(universityCity);
        beanObject.setUniversityState(universityState);
        beanObject.setUniversityPincode(universityPincode);
        beanObject.setUniversityPhoneNumber(universityPhoneNumber);
        beanObject.setUniversityOtherPhoneNumber(universityOtherPhoneNumber);
        beanObject.setUniversityFaxNumber(universityFaxNumber);
        beanObject.setNickName(nickName);
        beanObject.setUniversityCreatorID(userId);
        beanObject.setCountryName(countryName);

        try {
            if (flag.equalsIgnoreCase("insert")) {
                getSqlMapClientTemplate()
                    .update("universityMaster.updateAllUniversityStatus",
                    beanObject);

                getSqlMapClientTemplate()
                    .insert("universityMaster.createuniversity", beanObject);
            } else if (flag.equalsIgnoreCase("update")) {
                getSqlMapClientTemplate()
                    .update("universityMaster.updateUniversityDetails",
                    beanObject);
            }

            return "success";
        } catch (Exception e) {
            loggerObject.error("in getUniversitiesList" + e);
        }

        return null;
    }

    /**
     *This method updates the status of the university
     * @param userId userId of the user who is accessing the application
     * @param universityCode university code whose status is being updated
     * @param status status(active/Inactive)
     * @param sessionStartDate session start date of the university
     * @return String
     */
    public String updateUniversityStatus(String userId, String universityCode,
        String status, String sessionStartDate) {
        UniversityMasterInfoGetter beanObject = new UniversityMasterInfoGetter();

        beanObject.setUniversityCode(universityCode);
        beanObject.setModifierID(userId);
        beanObject.setCurrentStatus(status);
        beanObject.setSessionStartDate(sessionStartDate);

        try {
            getSqlMapClientTemplate()
                .update("universityMaster.updateUniversityStatus", beanObject);

            return "success";
        } catch (Exception e) {
            loggerObject.error("in getUniversitiesList" + e);
        }

        return null;
    }

    /**
     * The method gets the details of the concerned university
     * @param input object of the bean file
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<UniversityMasterInfoGetter> getUniversityDetails(
        UniversityMasterInfoGetter input) {
        List universityDetailsList;
        UniversityMasterInfoGetter sessionObject;

        try {
            sessionObject = (UniversityMasterInfoGetter) getSqlMapClientTemplate()
                                                             .queryForObject("universityMaster.getUniversitySession",
                    input);

            input.setSessionStartDate(sessionObject.getSessionStartDate());

            universityDetailsList = getSqlMapClientTemplate()
                                        .queryForList("universityMaster.getUniversityDetails",
                    input);

            return universityDetailsList;
        } catch (Exception e) {
            loggerObject.error("in getUniversitiesList" + e);
        }

        return null;
    }
}
