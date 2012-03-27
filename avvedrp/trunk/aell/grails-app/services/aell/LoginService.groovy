package aell

class LoginService {

 
	boolean transactional = true

	//function to save details to user table after login
    
	
	public  AvlUserMaster saveUserDetails(def user,def sessionid,def ipaddress)
    {
		def userInstance = AvlUserMaster.find("from AvlUserMaster UM where UM.username=?",[user]);
		if(userInstance){
			def time= new Date()    
			Integer uid=   userInstance.id  
			//get the number of times same user has logged in before without logout                                                      
			def userlogInstanceList=AvlUserLoginDetails.findAll("from AvlUserLoginDetails where userId=? and  logoutStatus='E'",[uid])
			if(userlogInstanceList){
			//if the entries exist then change the staus to logout 
			def flag=0;
			for(int i=0;i<userlogInstanceList.size();i++){
				if(userlogInstanceList[i].sessionId!=sessionid)
				{
					flag=1
				    userlogInstanceList[i].logoutStatus='L'
					def logoutTime=new Date();
					Date date1 = userlogInstanceList[i].loginTime
					Date date2 = logoutTime;
					def duration =getTimeDuration(date1,date2)
				    userlogInstanceList[i].loginDuration=duration
				}
		    }
			// else save the new user entry into the login log table
			if(flag==1)
			{
			def userloginstnce=new AvlUserLoginDetails();
			userloginstnce.userId=userInstance.id
			userloginstnce.loginTime=time
			userloginstnce.sessionId=sessionid
			userloginstnce.logoutStatus='E'
			userloginstnce.loginIp=ipaddress
			userloginstnce.sessionCount = 0
			userloginstnce.save();
			}
			}
			else
			{
			def userloginstnce=new AvlUserLoginDetails();
			userloginstnce.userId=userInstance.id
			userloginstnce.loginTime=time
			userloginstnce.sessionId=sessionid
			userloginstnce.logoutStatus='E'
			userloginstnce.loginIp=ipaddress
			userloginstnce.sessionCount = 0
			userloginstnce.save();
			}
		
			//TO INCREMENT THE SESSIONCOUNT IN AVLUSERLOGINDETAILS TABLE
			def userlogInstance=AvlUserLoginDetails.find("from AvlUserLoginDetails where userId=? and  logoutStatus='L' order by login_time desc",[uid])
			def usercurrentlogInstance=AvlUserLoginDetails.find("from AvlUserLoginDetails where userId=? and logoutStatus='E'",[uid])
           	
			//when there are already records in db with 'L' status,the sessioncount is null for those records when the field is added 
			if(userlogInstance && userlogInstance.sessionCount==null)
			{
				userlogInstance.sessionCount=0
			}
			
			//for fresh database the below code is enough
			if(userlogInstance)
			{
				usercurrentlogInstance.sessionCount=userlogInstance.sessionCount+1
			}
			else 
			{
				usercurrentlogInstance.sessionCount=1
			}
			usercurrentlogInstance.save()
			
		}
		return userInstance
    }
	


	public  AvlUserMaster createUserDetails(def user,def pass, def role, def sessionid,def ipaddress, def university)
	{
		
		//println "new openid user "+user
		//println "new openid university "+university
		def userInstance = AvlUserMaster.find("from AvlUserMaster UM where UM.username=?",[user]);
		if(userInstance)
		{
			//return userInstance
			//println "alreday user exists "
		
		}
		else
		{
			//println "here in else-- login service "+user
			def usermaster=new AvlUserMaster();
			usermaster.username = user
			usermaster.password = pass
			usermaster.universityId = university
			usermaster.emailId = user
			usermaster.userStatus ='A'
			usermaster.accountExpired = 0
			usermaster.accountLocked = 1
			usermaster.enabled = 1
			usermaster.passwordExpired = 0
			usermaster.save()
			def userRoleRelInstance=new AvlRoleUserRel()
			userRoleRelInstance.user=AvlUserMaster.get(usermaster.id)
			userRoleRelInstance.role=AvlRoleMaster.get(1004)
			if( !userRoleRelInstance.save()) {
				userRoleRelInstance.errors.each {
					println it
				}
			}
			else
			{
			 return usermaster
			}
			
		}
	
	}



	// function to save logout time into userlogindetails table
	
	void logoutAction(def userid,def sessionid)
	{
		def userlogInstance=AvlUserLoginDetails.find("from AvlUserLoginDetails where userId='"+userid+"' and logoutStatus='E' and sessionId='"+sessionid+"'")
		if(userlogInstance){
			 def logoutTime=new Date();
			 Date date1 = userlogInstance.loginTime;
				Date date2 = logoutTime;
				def duration =getTimeDuration(date1,date2)
		        userlogInstance.logoutStatus='L'
			 userlogInstance.loginDuration=duration
			 userlogInstance.save();
			 
			 // FOR LOGSERVICE
			 def sessioncount=userlogInstance.sessionCount
			 def lastlogInstance=AvlLogDetails.find("from AvlLogDetails where userId='"+userid+"' and sessionCount='"+sessioncount+"' and status='A'")
			 //to check whether the user just login and dont click any subject and logout
			 if(lastlogInstance!=null)
			 {
				 Date date3=lastlogInstance.accessTime
				 lastlogInstance.duration=getlogTimeDuration(date2,date3)
				 //lastlogInstance.endTime=logoutTime
				 lastlogInstance.status='E'
			 }
		}
	}
	def getTimeDuration(Date date1,Date date2)
	{
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);
		long milliseconds1 = calendar1.getTimeInMillis();
		long milliseconds2 = calendar2.getTimeInMillis();
		long diff = milliseconds2 - milliseconds1;
		float diffSeconds = diff / 1000;
		int diffSecondsRound=Math.round(diffSeconds)
		def duration =diffSecondsRound;
	    return duration
	}
	
	def getlogTimeDuration(date2,date3)
	{
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(date2);
		calendar2.setTime(date3);
		long milliseconds1 = calendar1.getTimeInMillis();
		long milliseconds2 = calendar2.getTimeInMillis();
		long diff = milliseconds1 - milliseconds2;
		float diffSeconds = diff / 1000;
		int diffSecondsRound=Math.round(diffSeconds)
		def duration =diffSecondsRound;
		return duration
	}
	
}
