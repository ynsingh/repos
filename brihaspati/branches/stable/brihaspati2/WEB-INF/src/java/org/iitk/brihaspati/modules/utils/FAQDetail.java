package org.iitk.brihaspati.modules.utils;

/*
 * @(#)FAQDetail.java
 *
 *  Copyright (c) 2010 ETRG,IIT Kanpur. 
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

 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 */

/**
* @author: <a href="mailto:seema_020504@yahoo.com">seema pal</a>
* @author: <a href="mailto:kshuklak@rediffmail.com">kishore kumar shukla</a>
*/

public class FAQDetail 
{
	private String Category;
	private String FaqId;
	private String UserId;
	private String QuesId;
	private String AnsId;
	private String ExpiryDate;
	private String PostDate;
	private String Version;
	private String Vote;
	private String GOOD;
	private String OK;
	private String WORST;

	public void setPDate(String PostDate)
	{
		this.PostDate=PostDate;
	}
	public String getPDate()
	{
		return PostDate;
	}
	public void setCategory(String Category)
	{
		this.Category=Category;
	}
	public String getCategory()
	{
		return Category;
	}
	public void setFaqId(String FaqId)
	{
		this.FaqId=FaqId;
	}
	public String getFaqId()
	{
		return FaqId;
	}
	public void setQuesId(String QuesId)
	{
		this.QuesId=QuesId;
	}
	public String getQuesId()
	{
		return QuesId;
	}
	public void setAnsId(String AnsId)
	{
		this.AnsId=AnsId;
	}
	public String getAnsId()
	{
		return AnsId;
	}
	public void setExpiryDate(String ExDate)
	{
		this.ExpiryDate=ExDate;
	}
	public String getExpiryDate()
	{
		return ExpiryDate;
	}
	
	public void setVersion(String Version)
	{
		this.Version=Version;
	}
	public String getVersion()
	{
		return Version;
	}
	public void setVote(String Vote)
	{
		this.Vote=Vote;
	}
	public String getVote()
	{
		return Vote;
	}
	public void setUserId(String UserId)
	{
		this.UserId=UserId;
	}
	public String getUserId()
	{
		return UserId;
	}
	public void setGOOD(String GOOD)
	{
		 this.GOOD=GOOD;	
	}
	public String getGOOD()
	{
		return GOOD;
	}
	public void setOK(String OK)
	{
		 this.OK=OK;
	}
	public String getOK()
	{
		return OK;
	}
	public void setWORST(String WORST)
	{
		 this.WORST=WORST;
	}
	public String getWORST()
	{
		return WORST;
	}
}
