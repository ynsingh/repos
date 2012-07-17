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

import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;

import java.util.List;
import java.util.StringTokenizer;


/**
 * This interface consist the list of methods used
 * in UnivRoleImpl file.
 * @author Ashish
 * @date 18 Jan 2011
 * @version 1.0
 */
public interface RoleConnect {
	 /**
     * Method retieves the roles defined in the university
     * @param input object of the referenced bean
     * @return List
     */
	List<UnivRoleInfoGetter> getUniversityRoles(UnivRoleInfoGetter input);

    /**
     * Method to get the list of links(menu items)
     * @param roleId roleId of the selected role
     * @param userId userId of the application user
     * @return list of links on the application for the role
     */
    List<UnivRoleInfoGetter> getMenuList(UnivRoleInfoGetter input);

    /**
     * Method to define role authorities
     * @param items list of selected menu items
     * @param userId userId of the logged in person
     * @param roleId roleId selected from the interface
     * @return String
     */
    String setRoleAuthorities(StringTokenizer items, UnivRoleInfoGetter input);

    /**
     * Method to get the list of the menu items already defined for a role
     * @param userId userId of the logged in person
     * @param roleId roleId selected from the list
     * @return list of menu items
     */
    List<UnivRoleInfoGetter> getSetMenuList(UnivRoleInfoGetter input);

    /**
     * Method to delete menu items authorities for the selected role
     * @param items list of selected menu items
     * @param userId userId of the logged in person
     * @param roleId roleId of the above role
     * @return String
     */
    String deleteUniversityRolesDetails(StringTokenizer items, UnivRoleInfoGetter input);

    /**
     * Method for getting the list of universities from university_master
     * @return List
     */
	List<UnivRoleInfoGetter> getUniversitiesList();

	/**
	 * Method adds a default user for newly created university 
	 * @param input object of the referenced bean
	 * @return 
	 */
	String addDefaultUser(UnivRoleInfoGetter input);

	/**
	 * Method for getting the list of universities for who 
	 * login already exists in the database 
	 * @param input object of the referenced bean
	 * @return List
	 */
	List<UnivRoleInfoGetter> getUniversitieswithLogins(UnivRoleInfoGetter input);
   
}
