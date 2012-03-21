class UserService{
	
	/**
	 * Function to get all user map
	 */
	public List getAllUserMap(){
		def userMapInstanceList =UserMap.findAll("from UserMap P  ")
		return userMapInstanceList
	}
	/**
	 * Function to get all user map with active party
	 */
	public List getAllUserMapWithActiveParty(){
		def userMapInstanceList =UserMap.findAll("from UserMap P  where P.party.id in (select PA.id from Party PA where PA.activeYesNo='Y') and P.user.id in(select PR.id from Person PR where PR.activeYesNo='Y') order by P.party.id")
		return userMapInstanceList
	}
	
	/**
	 * Function to get all user map based on userId
	 */
	public  getAllUserMapByUserId(def userId ){
		def userMapInstance =UserMap.find("from UserMap P where P.user.id= "+userId)
		return userMapInstance
	}
	/**
	 * Function to get all users 
	 */
	public List getAllUsers(){
		def userMapInstanceList =Person.findAll("from Person P  ")
		return userMapInstanceList
	}
	
	
	/**getAllUsersFromSite
	 * Function to get user map by id.
	 */
	public UserMap getUserMapById(Integer userMapId){
		def userMapInstance = UserMap.get( userMapId )
		return userMapInstance
	}
	
	/**
	 * Function to delete user map.
	 */
	public Integer deleteUserMap(Integer userMapId){
		Integer userMapDeletedId = null
		def userMapInstance = getUserMapById( userMapId )
        if(userMapInstance) {
            userMapInstance.delete()
            userMapDeletedId = userMapInstance.id
        }
		return userMapDeletedId
	}
	
	/**
	 * Function to update user map.
	 */
	public UserMap updateUserMap(def userMapParams){
		def userMapInstance = getUserMapById( new Integer(userMapParams.id ))
        if(userMapInstance) {
        	Integer userMapId = checkDuplicateUserMap(userMapParams)
        	if(userMapId.intValue() == 0 || userMapId.intValue() == userMapInstance.id.intValue()){
	            userMapInstance.properties = userMapParams
	            if(!userMapInstance.hasErrors() && userMapInstance.save()) {
	            	userMapInstance.saveMode = "Updated"
	            }
        	}
        	else
        		userMapInstance.saveMode = "Duplicate"
        }
		return userMapInstance
	}
	
	/**
	 * Function to save user map.
	 */
	public UserMap saveUserMap(def userMapInstance){
		if(checkDuplicateUserMap(userMapInstance) == 0){
			userMapInstance.save()
			userMapInstance.saveMode = "Saved"
		}
		else
			userMapInstance.saveMode = "Duplicate"
			
		return userMapInstance
	}
	
	/*
	 *  Function to check for duplication in user map 
	 */
    public Integer checkDuplicateUserMap(userMapInstance){
    	Integer userMapId = 0;
    	def chkUserMapInstance = UserMap.find("from UserMap UM where UM.projects ="+userMapInstance.projects.id+" and UM.party = "+userMapInstance.party.id+" and UM.user = "+userMapInstance.user.id)
    	if(chkUserMapInstance)
    		userMapId = chkUserMapInstance.id
    		
    	return userMapId
    }
	
	/**
	 * Function to get all users.
	 */
	public List getAllUsers(String subQuery){
		
		//def userMapList = UserMap.list(params)
		//println"params"+params
		def userMapList = UserMap.findAll("from UserMap UM "+subQuery)
		//def accountHeadsInstanceList = AccountHeads.findAll( "from AccountHeads AH where AH.activeYesNo='Y' "+subQuery  )
		return userMapList
	}
	
	/**
	 * Function to get all users.
	 */
	public List getAllUsersFromSite(def party,String subQuery){
		
		def userMapList = UserMap.findAll("from UserMap UM where UM.user.id in(select PR.id from Person PR where PR.activeYesNo='Y') and UM.party = "+party+subQuery)
		return userMapList
	}
	
	/**
	 * Function to get user by id.
	 */
	public Person getUserById(Integer userId){
		def person = Person.get(userId)
		return person
	}
	
	/**
	 * Function to get roles of user.
	 */
	public List getRolesOfUser(def person){
		List roleNames = []
		for (role in person.authorities) {
			roleNames << role.authority
		}
		roleNames.sort { n1, n2 ->
			n1 <=> n2
		}
		return roleNames
	}
	
	/**
	 * Function to delete user
	 */
	public Integer deleteUser(def person){
		//deleting user from user_map
		def userInstance = UserMap.find("from UserMap UM where UM.user = "+person.id)
			userInstance.delete()
			Authority.findAll().each { it.removeFromPeople(person) }
			person.delete()
				
		return person.id
	}
	
	/**
	 * Function to update user.
	 */
	public Integer updateUser(def person,def params){
		Integer userId = null
		if (person.save()) {
			Authority.findAll().each { it.removeFromPeople(person) }
				
			if(params.authorities)
			{
				updateUserRole(person,params)
            }

			
		/*	if(params.authorities == '1')
			{
				
			addRoles(person,params)
			}
			
				if(params.authorities == '2')
			{
				
			addRolesUser(person,params)
			}
				
			if(params.authorities =='3')
			{
			addRolesSiteAdmin(person,params)
			}
			if(params.authorities =='4')
			{
			addRolesPi(person)
			} */
			userId = person.id
		}
		return userId
	}
	
	/**
	 * Function to save user
	 */
	public Integer saveUser(def person,def params){
		Integer userId = null
		if (person.save()) {
			if(params.authorities == null)
			{
				addRolesUser(person,params)
			}
			if(params.authorities)
			{
				addSelectRoles(person,params)
			}
				
		/*	if(params.authorities == '1')
			{
				
			addRoles(person,params)
			}
			
				if(params.authorities == '2')
			{
				print "admin"
			addRolesUser(person,params)
			}
				
			if(params.authorities =='3')
			{
			addRolesSiteAdmin(person,params)
			}
			if(params.authorities =='4')
			{
			addRolesPi(person)
			}*/
			addUserMap(person,params) 
			userId = person.id
		}
		return userId
	}
	/**
	 * Function to add roles to user
	 */
	 
	 public void addSelectRoles(def person,def params) {
			System.out.println params
			
			def roleInstance = getRolebyId(params)
			
			//Authority.findByAuthority(roleInstance.authority).addToPeople(person)
			def userRole=new UserRole()
			userRole.user=person;
			userRole.role=Authority.findByAuthority(roleInstance.authority);
			userRole.save();
			
		}
	
	public void addRoles(def person,def params) {
		System.out.println params
		
		def roleInstance = getRolebyId(params)
		
		Authority.findByAuthority(roleInstance.authority).addToPeople(person)
		
	}
	
	// To update User's Role
	public void updateUserRole(def person,def params) {
		UserRole.executeUpdate("update UserRole UR set UR.role= '"+params.authorities+"' where UR.user='"+params.id+"'")
		}	
    public void addRolesUser(def person,def params) {
	// if(params.authorities == 2)
		//Authority.findByAuthority('ROLE_USER').addToPeople(person)
		
	def userRole=new UserRole()
	userRole.user=person;
	userRole.role=Authority.findByAuthority('ROLE_USER');
	userRole.save();
	}
	
	
	public void addRolesSiteAdmin(def person,def params) {
		  
		// if(params.authorities == 3)
		
		//Authority.findByAuthority('ROLE_SITEADMIN').addToPeople(person)
		def userRole=new UserRole()
		userRole.user=person;
		userRole.role=Authority.findByAuthority('ROLE_SITEADMIN');
		userRole.save();
	}
	public void addRolesPi(def person)
	{
		//Authority.findByAuthority('ROLE_PI').addToPeople(person)
		
//		Authority.findByAuthority('ROLE_SITEADMIN').addToPeople(person)
		def userRole=new UserRole()
		userRole.user=person;
		userRole.role=Authority.findByAuthority('ROLE_PI');
		userRole.save();
	}
	
	public void addUserMap(def person,def params) {
			def party
		def userMap=new UserMap()
		userMap.user=person
			
			if(params.institutions)
				 party=  Party.find("from Party  PA  where PA.id= "+params.institutions)
			else
				 party=  Party.find("from Party  PA  where PA.id= "+params.get("party.id"))
		userMap.party=party
		
		def userMapInstance= UserMap.find("from UserMap  UM  where UM.user = "+person.id)
		if(userMapInstance)
			userMapInstance.delete();
		userMap.createdBy="admin"
     	userMap.modifiedBy="admin"
        userMap.save()
    
	}
	
	/**
	 * Function to get roles.
	 */
	public List getRoles(){
		def authorityList = Authority.list()
		return authorityList
	}
	
	/**
	 * Function to update password.
	 */
	public Integer updatePassword(def person){
		Integer userId = null
		if (person.save()) {
			userId = person.id
		}
		return userId
	}
	public Person saveNewUser(def userInstance,def params)
	{
		if(userInstance.save())
		{
			addRolesSiteAdmin(userInstance,params)
			
		}
		return userInstance
	}
	public Person saveNewPi(def userInstance)
	{
		if(userInstance.save())
		{
			addRolesPi(userInstance)
		}
		return userInstance
	}
	public Integer saveNewUserMap(def person,def params){
		Integer userId = null
		def userMap=new UserMap()
		userMap.user=person.user
		def party=  Party.find("from Party  PA  where PA.id= "+params.get("party.id"))
		userMap.party=person.party
		userMap.createdBy="admin"
     	userMap.modifiedBy="admin"
        userMap.save()
        userId=userMap.user.id
        return userId
	}
	public Party addParty(def partyInstance)
	{
		def partyService = new PartyService()
        def party = new Party()
        party = partyService.saveParty(partyInstance)
        return party
	}
	public Authority getRolebyId(def params)
	{
		def roleInstance = new Authority()
		roleInstance = Authority.find("from Authority R where R.id="+params.get("authorities"))
		return roleInstance
	}
	/*Function to get active roles except Siteadmin and pi*/
	public List getActiveRoles()
	{
		//def roleInstance = new Authority()
		def roleInstance = Authority.findAll("from Authority R where R.authority NOT IN ('ROLE_SITEADMIN','ROLE_PI','ROLE_SUPERADMIN') and R.activeYesNo='Y'")
		return roleInstance
	}
	public Party getParty(def person)
	{
		def party =  new Party();
		def userMap= UserMap.find("from UserMap  UM  where UM.user = "+person.id)
		party =  Party.find("from Party  PA  where PA.id= "+userMap.party.id)
		return party
	}
	/**
	 * Getting user by name.
	 */
	public Integer getUserByUserName(String userName){
		Integer userId = null;
		def person  = Person.find("from Person U where U.username='"+userName+"' and U.activeYesNo='Y'");
		if(person)
			userId = person.id
		return userId
	}
	
	/**
	 * Getting user by email.
	 */
	public Integer getUserByEmail(String email){
		Integer userEmailId = null;
		def person  = Person.find("from Person U where U.email='"+email+"'");
		if(person)
			userEmailId = person.id
		return userEmailId
	}
	
	/*
	 * Function to get user Role Id using User Id.
	 */
	 
   public UserRole getuserRole(def userId)
	{
	 def userRoleInstance = UserRole.find("from UserRole UR where UR.user.id = "+userId)
	 return userRoleInstance
	}
	
	/*
	 * Function to get Authority using Role Id.
	 */
	 
	 public Authority getauthority(def roleId)
	 {
		 def  authorityInstsnce = Authority.find("from Authority A where A.id in ("+ roleId+")")
		 return authorityInstsnce
		}
	/*
	 * Getting All Active Rolls
	 */
	 public getAllRolls()
	{
		 def authorityInstanceList=Authority.findAll("from Authority AT where AT.activeYesNo='Y'")
	     return authorityInstanceList
	}

	/*
	 * Getting Authority By Id
	 */
	 public getRoleById(def RoleId)
	{
		 def authorityInstance = Authority.get(RoleId)
	     return authorityInstance
	}
	/*
	 * Save new Role
	 */
	 public saveRole(def authorityInstance)
	{
		 authorityInstance.activeYesNo="Y"
		 def authorityInstanceSave =false 
		
		  if(!authorityInstance.hasErrors() && authorityInstance.save()) 
					{
						authorityInstanceSave=true
					}
					else
					{
						authorityInstanceSave=false
					}
				return authorityInstanceSave
		 
	}
	/*
	 * Delete Role
	 */
	 public deleteRole(def authorityInstance)
	{
		if(authorityInstance)
		{
		 authorityInstance.activeYesNo="N"
			 authorityInstance.save()
		 // def authorityInstanceDelete = updateRole(authorityInstance)
		 return authorityInstance
		}
	}
	/*
	 * Update Role
	 */
	 public boolean updateRole(def params)
	{
		def authorityInstanceUpdate =false 
		def authorityInstance = getRoleById(params.id)
		authorityInstance.properties = params
			if(!authorityInstance.hasErrors() && authorityInstance.save()) 
			{
				authorityInstanceUpdate=true
			}
			else
			{
				authorityInstanceUpdate=false
			}
		return authorityInstanceUpdate
	}
	
	 /*
	  * Getting Authority details based on authority
	  */
	 public List getDuplicateRole(def params)
	 {
		 def chkDuplicateRoleInstance
		try
		{
		 chkDuplicateRoleInstance = Authority.findAll("from Authority AT where AT.activeYesNo = 'Y' and AT.authority = '"+ params.authority+"'")
		}
		catch(Exception e)
		{
			
		}
		 return chkDuplicateRoleInstance
		
	 }
	 
	 public Person getUserName(def params)
	 {
			Integer userId = null;
			def person  = Person.find("from Person P where P.username= '"+params.oldUsrName+"'");
			
			return person
		}
	 
	 public Integer updateUserName(def person){
			Integer userId = null
			if (person.save()) {
				userId = person.id
			}
			return userId
		}
	/*
	 * Getting All users of a Party
	 */
	 public getAllUsersByPartyID(def PartyID)
	 {
		 def userMapList = UserMap.findAll("from UserMap UM where  UM.party = "+PartyID)
		 return userMapList
	 }
	
   /*
	* Getting All authorities except Site admin
	*/
	 public getAuthoritiesExceptSiteAdmin()
	 {
		 def authorityInstance = Authority.findAll("from Authority R where R.activeYesNo='Y' and not authority = 'ROLE_SITEADMIN'")
		 return authorityInstance
	 }
	
	 /*
	  * Getting All authorities except Site admin,pi and Super admin
	  */
	 public getAuthoritiesExceptSiteAdminPiAndSuperAdmin()
		 {
		       def authorityInstance =  Authority.findAll("from Authority R where R.activeYesNo='Y' and  authority NOT IN ('ROLE_SUPERADMIN')")
			 return authorityInstance
		 }
	 /*
	  * Function to get user by party and id.
	  */
	  public getUserByIdAndParty(def userId,def partyId)
	 {
		def userMapInstance = UserMap.find("from UserMap UM where UM.user.id='"+userId+"'and UM.party.id= "+partyId)
		return userMapInstance
	 }
	 
	  /*
	   * Function to get user role by userId .
	   */
	   public getUserRoleByUserId(def userId)
	  {
			def userRoleInstance  = UserRole.executeQuery("select UR.role from UserRole UR where UR.user.id="+userId)		
			return userRoleInstance
	  }
   /*
	* save user map
	*/
	 public def saveNewUserMapInstance(def userMapInstance)
	{
		userMapInstance.createdBy=""
	    userMapInstance.modifiedBy=""
		if(userMapInstance.save())
		 {
			userMapInstance.saveMode = "Saved"
		 }
		 else
		 {
			 userMapInstance.saveMode = "NotSaved"
		 }
		 return userMapInstance
	}
    /*
	 * method to get UserRole using authority and party id
	 * (get siteadmin)
	 */
	 public def getUserRoleByAuthorityAndParty(def authorityName,def partyId)
	{
		 def userRoleInstance = UserRole.find("from UserRole UR where UR.role.authority='"+authorityName+"' and UR.user.id in (select UM.user.id from UserMap UM where UM.party.id="+partyId+")")
		 return userRoleInstance
	}
    /*
     * method to get person by user name
     */
	 public List getPersonByUserName(def userName)
	 {
			Integer userId = null;
			def person  = Person.findAll("from Person P where P.username= '"+userName+"'");
			
			return person
	 }
    
    /*
     * Method to get approval authority details by person id
     */
     public getPesonInApprovalAuthorityDetail(def params)
	 {
		  def approvalAuthorityDetails = ApprovalAuthorityDetail.find("from ApprovalAuthorityDetail AAD where AAD.person.id="+params.id)
		  return approvalAuthorityDetails
	 }
     /*
	   * Function to get Authority List By Role
	   */
	   public getAuthorityListByRole(def userId)
	  {
		def authorityList = []	
    	def userRoleInstanceList  = UserRole.executeQuery("select UR.role from UserRole UR where UR.user.id="+userId)		
		if(userRoleInstanceList.size()==1){
				def authorityInstance = getauthority(userRoleInstanceList[0].id)
				if(authorityInstance.authority == 'ROLE_SITEADMIN'){
					authorityList = getAuthorityListExRoles()}
				else if(authorityInstance.authority == 'ROLE_SUPERADMIN'){
					authorityList = Authority.findAll("from Authority R where R.activeYesNo='Y' and not authority in('ROLE_SUPERADMIN')")
				}
				else{
					authorityList = getAuthorityListExRoles()}
			}else{
				authorityList = getAuthorityListExRoles()
			}
			
		return authorityList
	  }
     
	   /*
		* Function to get Authority List Excluded ROLE_SITEADMIN &ROLE_SUPERADMIN
		*/
		 public getAuthorityListExRoles(def userId)
		  {
			   def authorityList = Authority.findAll("from Authority R where R.activeYesNo='Y' and not authority in('ROLE_SITEADMIN','ROLE_SUPERADMIN')")
			   return authorityList
		  }
     /*
      * Function to get SiteAdmin of a party 
      */
      public getSiteAdminOfReceiver(def userMapList,def authorityInstance)
     {
    	 for(userInstance in userMapList){
    		 
    		def userRoleInstance = UserRole.executeQuery("select UR.user from UserRole UR where UR.user.id="+userInstance.user.id+ "and UR.role.id ="+authorityInstance.id)
    		if(userRoleInstance){
    			return userRoleInstance
    		}
    	 }
    	  return
     }
     /*
      * Function to get Super Admin 
      */
     public getSuperAdminUser(def partyId)
      {
    	 def userMapInstance = UserMap.executeQuery("select UM.party.id from UserMap UM where party.id='"+partyId+"'AND UM.user.id IN(select UR.user.id from UserRole UR where UR.role.id IN(select A.id from Authority A where A.authority='ROLE_SUPERADMIN'))")
    	 return userMapInstance
      }
}
