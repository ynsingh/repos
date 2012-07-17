/*
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
package in.ac.dei.edrp.cms.dao.university;

import in.ac.dei.edrp.cms.domain.university.UniversityMasterInfoGetter;

import java.util.List;


/**
 * This interface consist the list of methods used
 * in UniversityMasterImpl file.
 * @author Ashish
 * @date 18 Jan 2011
 * @version 1.0
 */
public interface UniversityMasterConnect {
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
     * @param country 
     * @return String
     */
    String defineUniversityCreation(String userId, String universityName,
        String sessionStartDate, String sessionEndDate,
        String universityAddress, String universityCity,
        String universityState, String universityPincode,
        String universityPhoneNumber, String universityOtherPhoneNumber,
        String universityFaxNumber, String nickName, String country);

    /**
     * This method gets the list of universities created
     * along with there details
     * @param userId userId of the user who is accessing the application
     * @param flag counter to get details of the university 
     * @return
     */
    List<UniversityMasterInfoGetter> getUniversitiesList(String userId, String flag);

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
     * @param flag
     * @param country 
     * @return String
     */
    String updateUniversityDetails(String userId, String universityName,
        String sessionStartDate, String sessionEndDate,
        String universityAddress, String universityCity,
        String universityState, String universityPincode,
        String universityPhoneNumber, String universityOtherPhoneNumber,
        String universityFaxNumber, String universityCode, String nickName,
        String flag, String country);

    /**
     * This method updates the status of the university
     * @param userId userId of the user who is accessing the application
     * @param universityCode university code whose status is being updated
     * @param status status(active/Inactive)
     * @param sessionStartDate 
     * @return String
     */
    String updateUniversityStatus(String userId, String universityCode,
        String status, String sessionStartDate);

    /**
     * The method gets the details of the concerned university
     * @param input object of the bean file
     * @return List
     */
    List<UniversityMasterInfoGetter> getUniversityDetails(
        UniversityMasterInfoGetter input);
}
