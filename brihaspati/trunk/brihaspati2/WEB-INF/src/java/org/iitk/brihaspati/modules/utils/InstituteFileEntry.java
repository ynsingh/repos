package org.iitk.brihaspati.modules.utils;
/*
 * @(#)InstituteFileEntry.java
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur.
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
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */
/**
 * This class set some value and get in templates from XML file 
 * @author: <a href="mailto:jaivirpal@gmail.com">jaivir Singh</a>
 * @author: <a href="mailto:sharad23nov@gmail.com">Sharad Singh</a>
 * @author: <a href="mailto:palseema30@gmail.com">Manorama Pal</a>
 * @author: <a href="mailto:kishore.shukla@gmail.com">Kishore kumar shukla</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @modified date: 09-11-2012, 23-04-2013
 */
public class InstituteFileEntry
{
	private String instname;
	private String instaddr;
	private String city;
	private String pcode;
	private String country;
	private String state;
        private String landlineno;
        private String instdomain;
	private String insttype;
        private String instaffiliation;
        private String website;
	private String regdate;
	private String expdate;
	private String fname;
	private String lname;
	private String email;
	private String desigation;
	private String uname;
	private String password;
	private int status;
	private int Id; 
	private String subject;
	private String message;
	private String date;
	private String attempt;
	private String langFile;
	private String attachFile;
	private String quotationId;
	private String quotationValue;

	public void setSubject(String subject)
        {
                this.subject=subject;
        }
        public String getSubject()
        {
                return subject;
        }

	public void setMessage(String message)
        {
                this.message=message;
        }
        public String getMessage()
        {
                return message;
        }

	public void setDate(String date)
        {
                this.date=date;
        }

        public String getDate()
        {
                return date;
        }
	
	public void setAttempt(String attempt){
		this.attempt = attempt;
	}
	
	public String getAttempt(){
		return attempt;
	}

	public void setLangFile(String langFile)
        {
                this.langFile=langFile;
        }

        public String getLangFile()
        {
                return langFile;
        }

	public void setInstituteEmail(String email)
        {
                this.email=email;
        }
        public String getInstituteEmail()
        {
                return email;
        }
        public void setInstituteExpDate(String expdate)
        {
                        this.expdate=expdate;
        }
        public String  getInstituteExpDate()
        {
                        return expdate;
        }
	public void setInstituteName(String instname)
	{
		this.instname=instname;
	}
	public String getInstituteName()
	{
		return instname;
	}
	public void setInstituteAddress(String instaddr)
	{
		this.instaddr=instaddr;
	}
	public String getInstituteAddress()
	{
		return instaddr; 
	}
	public void setInstituteUserName(String uname)
	{
		this.uname=uname;
	}
	public String getInstituteUserName()
	{
		return uname; 
	}
	public void setInstituteCity(String city)
	{
		this.city=city;
	}
	public String getInstituteCity()
	{
		return city;
	}
	public void setInstitutePincode(String pcode)
        {
                this.pcode=pcode;
        }
        public String getInstitutePincode()
        {
                return pcode;
        }
	public void setInstituteRegDate(String regdate)
	{
		this.regdate=regdate;
	}
	public String getInstituteRegDate()
	{
		return regdate;
	}
        public void setInstituteType(String insttype)
        {
                this.insttype=insttype;
        }
        public String getInstituteType()
        {
                return insttype; 
        }
        public void setInstituteLandLineNo(String landlineno)
        {
                this.landlineno=landlineno;
        }
        public String getInstituteLandLineNo()
        {
                return landlineno;
        }
	public void setInstituteCountry(String country)
        {
                this.country=country;
        }
        public String getInstituteCountry()
        {
                return country;
        }
	public void setInstituteState(String state)
        {
                this.state=state;
        }
        public String getInstituteState()
        {
                return state;
        }
	public void setInstituteDomain(String instdomain)
        {
                this.instdomain=instdomain;
        }
        public String  getInstituteDomain()
        {
                return instdomain;
        }
        public void setInstituteAffiliation(String instaffiliation)
        {
                this.instaffiliation=instaffiliation;
        }
        public String  getInstituteAffiliation()
        {
                return instaffiliation;
        }
        public void setInstituteWebsite(String website)
        {
                this.website=website;
        }
        public String getInstituteWebsite()
        {
                return website;
        }
        public void setInstituteFName(String fname)
        {
                this.fname=fname;
        }
        public String getInstituteFName()
        {
                return fname;
        }
        public void setInstituteLName(String lname)
        {
                this.lname=lname;
        }
        public String getInstituteLName()
        {
                return lname;
        }
        public void setInstituteDesignation(String desigation)
        {
                this.desigation=desigation;
        }
        public String getInstituteDesignation()
        {
                return desigation;
        }
        public void setInstitutePassword(String password)
        {
                this.password=password;
        }
        public String getInstitutePassword()
        {
                return password;
        }
	public void setID(int Id)
        {
                this.Id=Id;
        }
        public int getID()
        {
                return Id;
        }

	public void setAttachFile(String attachFile)
        {
                this.attachFile=attachFile;
        }
        public String getAttachFile()
        {
                return attachFile;
        }
	public void setInstituteAdminStatus(int status)
        {
                this.status=status;
        }
        public int getInstituteAdminStatus()
        {
                return status;
        }
	public void setQuotationId(String quot_id)
        {
                this.quotationId=quot_id;
        }
        public String getQuotationId()
        {
                return quotationId;
        }
	public void setQuotationValue(String quot_val)
        {
                this.quotationValue=quot_val;
        }
        public String getQuotationValue()
        {
                return quotationValue;
        }
}
