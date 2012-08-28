package org.iitk.brihaspati.modules.actions;

/**
 * @(#)UserData.java  
 *   
 * Copyright (c) 2012 ETRG,IIT Kanpur. 
 * All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * Redistributions of source code must retain the above copyright  
 * notice, this  list of conditions and the following disclaimer.
 * 
 * Redistribution in binary form must reproducuce the above copyright 
 * notice, this list of conditions and the following disclaimer in 
 * the documentation and/or other materials provided with the 
 * distribution.
 * 
 *   
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


import org.expressme.openid.Authentication;
import java.io.Serializable;


/**
 * This class stores the user data
 * fetched from Authentication information 
 * returned from OpenID Provider.
 * The data can then be used for 
 * user authorisation.
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 */

public class UserData implements Serializable 
{
	private static final long serialVersionUID = 19871110L;
	
	private String identity;

	private String email;

	private String fullname;

	private String firstname;

	private String lastname;
	
	private String language;

	private String gender;

/**
 * Get identity.
 */
    public String getIdentity() {
        return identity;
    }

/**
 * Set identity.
 */
    public void setIdentity(String identity) {
        this.identity = identity;
    }

/**
 * Get email.
 */
    public String getEmail() {
        return email;
    }

/**
 * Set email.
 */
   public void setEmail(String email) {
        this.email = email;
    }

/**
 * Get fullname.
 */
    public String getFullname() {
        return fullname;
    }

/**
 * Set fullname.
 */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

/**
 * Get firstname.
 */
    public String getFirstname() {
        return firstname;
    }

/**
 * Set firstname.
 */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

/**
 * Get lastname.
 */
    public String getLastname() {
        return lastname;
    }

/**
 * Set lastname.
 */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

/**
 * Get language.
 */
    public String getLanguage() {
        return language;
    }

/**
 * Set language.
 */
    public void setLanguage(String language) {
        this.language = language;
    }

/**
 * Get gender.
 */
    public String getGender() {
        return gender;
    }

/**
 * Set gender.
 */
    public void setGender(String gender) {
        this.gender = gender;
    }



}//class
					
