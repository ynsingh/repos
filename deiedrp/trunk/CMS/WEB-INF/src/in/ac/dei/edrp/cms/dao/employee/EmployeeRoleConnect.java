/*
 * @(#) EmployeeRoleConnect.java
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

import in.ac.dei.edrp.cms.domain.employee.EmployeeRoleInfoGetter;

import java.util.List;


/**
 * This interface consist the list of methods used
 * in EmployeeRoleImpl file.
 * @author Ashish
 * @date 18 Jan 2011
 * @version 1.0
 */
public interface EmployeeRoleConnect {
    /**
        * Method to get details of the concerned employee
        * whose employee code is passed from the interface.
        * @param userId userId of the user who is accessing
        * the interface
        * @param employeecode code of the employee.
        * @return employee details
        */
    List<EmployeeRoleInfoGetter> getEmployeeDetails(EmployeeRoleInfoGetter input);

    /**
     * Method to assign a role to the employee of the university
     * @param userid userid of the of the user who is accessing
     * the interface
     * @param employeeCode employee code to be used as login id
     * @param roleId roleId of the role assigned to the employee
     * @param email mail id of the employee
     * @param roleDescription name of the role assigned to the employee
     * @param employeeId id of the employee of the university
     * @return EmployeeRoleInfoGetter
     */
    List<EmployeeRoleInfoGetter> setEmployeeRole(EmployeeRoleInfoGetter input);

    /**
     * Method generates the list of menus(links) for the role assigned to the user
     * @param userId userId of the concerned user
     * @param  employeeCode to be used as login id
     * @param counter counter for different processes
     * @return list of menu items for the concerned role
     */
    List<EmployeeRoleInfoGetter> getMenuOnRole(String userId,
        String employeeCode, String counter);

    /**
     * Method insert/update authorities on a link for the concerned user
     * @param userId creator id
     * @param employeeCode user whose authority is to be altered
     * @param menuItemId id of the link
     * @param secondaryauthority authority on the link
     * @param counter counter for insertion/update ping
     * @param employeeId system generated id of the employee
     * @return String
     */
    String setUserAuthoroties(EmployeeRoleInfoGetter input);
    /**
     * Method for getting the users of the application for the university
     * for redefining authorities on links associated with the concrened role.
     * @param input object of the referenced bean
     * @return list of users
     */
	List<EmployeeRoleInfoGetter> getUsers(EmployeeRoleInfoGetter input);

	/**
     * Method for getting the users of the application for the university
     * for deleting roles already assigned to them
     * @param input object of the referenced bean
     * @return list of users
     */
	List<EmployeeRoleInfoGetter> getEmployees(EmployeeRoleInfoGetter input);
	
	/**
     * Method for deleting roles already assigned to selected employee
     * @param input object of the referenced bean
     * @return Success
     */
	String deleteEmployeesRecords(
			EmployeeRoleInfoGetter input);



	String ResetEmployeePassword(EmployeeRoleInfoGetter input);
	
	/**
	 * The method retrieves the list of application users depending upon there user types
	 * @param input
	 * @return EmployeeRoleInfoGetter
	 */
	List<EmployeeRoleInfoGetter> getApplicationUserDetails(
			EmployeeRoleInfoGetter input);
}
