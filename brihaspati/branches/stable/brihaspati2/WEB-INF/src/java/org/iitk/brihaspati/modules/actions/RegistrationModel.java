package org.iitk.brihaspati.modules.actions;
/*
 * @(#)RegistrationModel.java        
 *
 *  Copyright (c) 2011 ETRG,IIT Kanpur. 
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met:
 * 
 *  Redistributions of source code must retain the above copyright  
 *  notice, this  list of conditions and the following disclaimer.
 * 
 *  Redistribution in binary form must reproducuce the above copyright 
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution.
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
 * 
 *  Contributors : members of ETRG, IIT Kanpur  
 *  
 */


import java.io.Serializable;
import java.util.Date;

/**
 * This class serves as the model for registration information. It can be
 * used as the domain object for saving information to the DB (through, say,
 * Hibernate or another ORM tool), and the model for the Wicket forms used
 * by this application.
 * 
 * @author Nagendra Kumar singh
 * @author http://sites.google.com/site/nksinghiitk/
 */
public class RegistrationModel implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = -2834908356868001273L;

	private String openId;
	private String fullName;
	private String emailAddress;
	private String zipCode;
	private Date dateOfBirth;
	private String favoriteColor;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
  public String getFullName() {
    return fullName;
  }
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getFavoriteColor() {
		return favoriteColor;
	}
	public void setFavoriteColor(String favoriteColor) {
		this.favoriteColor = favoriteColor;
	}

}
