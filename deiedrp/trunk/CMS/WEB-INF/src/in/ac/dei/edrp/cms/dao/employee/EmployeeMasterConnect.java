/*
 * @(#) EmployeeMasterConnect.java
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
package in.ac.dei.edrp.cms.dao.employee;

import in.ac.dei.edrp.cms.domain.employee.EmployeeMasterInfoGetter;

import java.util.List;


/**
 * This file consist of the methods used for setting up
 * the employee Master setup.
 * @author Ashish
 * @date 21 Feb 2011
 * @version 1.0
 */
public interface EmployeeMasterConnect {
    /**
     * This method retrieves the list of parent entities in the university
     * @param userId user id of the user who is accessing the application
     * @return List
     */
    List<EmployeeMasterInfoGetter> getParentEntity(String userId);

    /**
     * This methods retrieves the list of designations(active)
     * for the concerned university
     * @param userId user id of the user who is accessing the application
     * @return List
     */
    List<EmployeeMasterInfoGetter> getDesignations(String userId);

    /**
     * This method is used to define/manage the employee profile for the university
     * @param input details of the employee
     * @return String
     * @throws Exception
     */
    String setEmployeeProfile(EmployeeMasterInfoGetter input)
        throws Exception;

    /**
     * This method retrieves the details of the concerned employee
     * for managing(update basic details).
     * @param input details of the employee(employee code)
     * @return List
     */
    List<EmployeeMasterInfoGetter> getEmployeeDetails(
        EmployeeMasterInfoGetter input);

    /**
     * This method retrieves the addresses information of the concerned employee
     * for managing(updating address details)
     * @param input details of the employee(employee code)
     * @return List
     */
    List<EmployeeMasterInfoGetter> getEmployeeAddressDetails(
        EmployeeMasterInfoGetter input);
/**
 * The method retrives the categories defined for the university
 * @param input
 * @return List
 */
	List<EmployeeMasterInfoGetter> getCategoryDetails(
			EmployeeMasterInfoGetter input);
/**
 * The method retrives the genders defined for the university
 * @param input
 * @return List
 */
List<EmployeeMasterInfoGetter> getGenderDetails(EmployeeMasterInfoGetter input);
/**
 * The method retrives the pension descriptions defined for the university
 * @param input
 * @return List
 */
List<EmployeeMasterInfoGetter> getPensionDetails(EmployeeMasterInfoGetter input);

/**
 * The method retrives the genders minority groups for the university
 * @param input
 * @return List
 */
List<EmployeeMasterInfoGetter> getMinorityDetails(EmployeeMasterInfoGetter input);
}
