package org.iitk.brihaspati.modules.utils;

/*
 * @(#) UserUtil.java 
 *  Copyright (c) 2004-2006,2011 ETRG,IIT Kanpur. 
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
import java.util.List;
import java.util.Vector;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.apache.commons.lang.StringUtils;



/**
 * This utils class have all details of User
 * @author <a href="mailto:awadhk_t@yahoo.com">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 */


public class UserUtil
{
	/**
	 * @param userName String
	 * @return integer
	 */ 
	public static int getUID(String userName)
	{
		//List v=null;
		int uid=-1;

		Criteria crit=new Criteria();
		crit.add(TurbineUserPeer.LOGIN_NAME,userName);
		try
		{			
			List v=TurbineUserPeer.doSelect(crit);
			TurbineUser element=(TurbineUser)v.get(0);
			uid=element.getUserId();
		}
		catch(Exception e)
		{
		}
		return uid;
	}
	/**
	 * @param uid Integer
	 * @return String
	 */ 
	public static String getLoginName(int uid){
		String LoginName=null;
		try{

			Criteria crit=new Criteria();
			crit.add(TurbineUserPeer.USER_ID,uid);			
			List v=TurbineUserPeer.doSelect(crit);
			TurbineUser element=(TurbineUser)v.get(0);
			LoginName=element.getUserName().toString();
		}
		catch(Exception e){
			//log something
		}
		return LoginName;
	}
	
	/**
	 * @param uid Integer
	 * @return String full name of user
	 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit(DEI Agra)</a>
	 */ 
	public static String getFullName(int uid){
		String fullName="";
		try{

			Criteria crit=new Criteria();
			crit.add(TurbineUserPeer.USER_ID,uid);						
			List v=TurbineUserPeer.doSelect(crit);
			TurbineUser element=(TurbineUser)v.get(0);
			String LoginName= element.getUserName().toString();
			String firstName = element.getFirstName().toString();
			String lastName = element.getLastName().toString();
			fullName = firstName + " "+lastName;
			if(StringUtils.isBlank(fullName)){
				fullName=LoginName;
			}  			
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("inside exception : userutil:getFullName "+e);
			//log something
		}		
		return fullName;
	}
	
	
				
	public static User getUDetail(int uid)throws Exception
	{
		String LoginName=null;
		List v=null;
		try{
			Criteria crit=new Criteria();
			crit.add(TurbineUserPeer.USER_ID,uid);
			v=TurbineUserPeer.doSelect(crit);
		}
		catch(Exception e){
			//Log something
		}
		try{
			for(int i=0;i<v.size();i++){
			TurbineUser element=(TurbineUser)v.get(i);
			LoginName=element.getUserName().toString();
			}
		}
		catch(Exception e){
			//log something
		}
		return TurbineSecurity.getUser(LoginName);	
	}
	/**
 	 * This method gives all registered groupid for given userid 
 	 * @param uid Integer user id of the user		//Richa
 	 * @return List  
 	 */ 
	
	public static List getAllGrpId(int uid)throws Exception
	{
		List v=null;
		int nogid[]={1,4,5};
		try{
			Criteria crit = new Criteria();
			crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
			crit.addNotIn(TurbineUserGroupRolePeer.GROUP_ID,nogid);
			v=TurbineUserGroupRolePeer.doSelect(crit);
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Exception inside getAllGrpId() UserUtil.java!!"+e);
		}
	return v;
	
	}			

}
