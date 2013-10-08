/*
 * @(#) InstAdminRegistrationDAO.java
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
package in.ac.dei.edrp.cms.dao.registrationForInstAndAdmin;

import in.ac.dei.edrp.cms.domain.registrationForInstAndAdmin.InstAdminRegistrationInfo;

import java.util.List;
/**
 * This file consist of the methods used for setting up the institute admin registration 
 * setup.
 * 
 * @author Upasana 
 * @date 21	November 2012
 * @version 1.0
 */
public interface InstAdminRegistrationDAO {

	String setInstituteAdminProfile(InstAdminRegistrationInfo input);

	Integer updateRequestStatus(InstAdminRegistrationInfo input);

	List<InstAdminRegistrationInfo> getAdminInstituteDetails(InstAdminRegistrationInfo input);

	Integer updateAdminInstituteDetails(InstAdminRegistrationInfo input);

	String insertIntoUniversityUserDetails(InstAdminRegistrationInfo input);

}
