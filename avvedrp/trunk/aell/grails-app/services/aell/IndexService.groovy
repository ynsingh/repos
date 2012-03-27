
package aell

class IndexService {

    boolean transactional = true
    // function to get role name using session.userid
    public String getRoleName(def uid)
	{
		
		def rolename
		def roleuserinstance=AvlRoleUserRel.find("from AvlRoleUserRel where user='"+uid+"'");
		if(roleuserinstance)
		{
		   def roleinstnce=AvlRoleMaster.find("from AvlRoleMaster where id=?",[roleuserinstance.role.id]);
		   if(roleinstnce)
		    {
			  rolename=roleinstnce.authority;
		    }
		}
		return rolename
	}
	
	
	// function to find AvlUserMaster instance by using user name
	public AvlUserMaster getUsermasterDetails(def userNameRef) 
	{
		def avlUserMasterInstance=AvlUserMaster.find("from AvlUserMaster where username=?",[userNameRef]);
		return avlUserMasterInstance
	}
	
	
	// check whether user exist or not in usermaster table after logged in as referal_url=1
	// if not exist save that user into the usermaster table
	def saveUser(def username,utyId,def al_roleid)
	{
		def flag =0;
		def avlUserMasterInstance
		def avlRoleMasterInstance
		def avlRoleUserRelInstance
		   def avlNewUserMasterInstance = AvlUserMaster.findAll();
				if(username)
				{
						for(int i=0;i<avlNewUserMasterInstance.size();i++)
						{
							 if(username == avlNewUserMasterInstance[i].username )
							  {
								  flag = 0
							  }
							 else
							  {
								 flag++;
							  }
						}
				if(flag == avlNewUserMasterInstance.size())
				{
					avlUserMasterInstance = new AvlUserMaster()
					avlUserMasterInstance.username = username
					avlUserMasterInstance.password = 'amma'
					avlUserMasterInstance.emailId = 'amma@gmail.com'
					avlUserMasterInstance.userStatus = 'A'
					avlUserMasterInstance.universityId = utyId
					avlUserMasterInstance.accountExpired = false
					avlUserMasterInstance.accountLocked = false
					avlUserMasterInstance.passwordExpired = false
					avlUserMasterInstance.enabled = true
					avlUserMasterInstance.save()
					avlRoleUserRelInstance = new aell.AvlRoleUserRel()
					avlRoleUserRelInstance.user = avlUserMasterInstance
					avlRoleMasterInstance=AvlRoleMaster.find("from AvlRoleMaster where id= "+al_roleid);
					avlRoleUserRelInstance.role = avlRoleMasterInstance
					avlRoleUserRelInstance.save();
					
				}
				}
				return avlUserMasterInstance
	}
	
	//function to get the current session status
	def getCurrentSessionStatus(def sessionId){
	def sessionStatus
	def userLogInstance=AvlUserLoginDetails.find("from AvlUserLoginDetails where sessionId='"+sessionId+"'")
	if(userLogInstance){
		if(userLogInstance.logoutStatus=='L')
		  sessionStatus="yes"
		else
		  sessionStatus="no"
	  }//userlog instance exist
	return sessionStatus
	}
}
