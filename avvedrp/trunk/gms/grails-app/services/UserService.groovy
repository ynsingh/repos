class UserService{
	
	/**
	 * Function to get all user map
	 */
	public List getAllUserMap(){
		def userMapInstanceList =UserMap.findAll("from UserMap P  ")
		return userMapInstanceList
	}
	
	/**
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
		
		def userMapList = UserMap.findAll("from UserMap UM where  UM.party = "+party+subQuery)
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
			println"+++params.authoritiesupdate+++"+params.authorities
			
				
			if(params.authorities == '1')
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
			}
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
			println "....params....."+params
			println"+++params.authorities+++"+params.authorities
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
			println "++++++++++++++++roleInstance.authority+++++++++" +roleInstance.authority
			println"+++params.usertype+++"+params.authorities
			
			//Authority.findByAuthority(roleInstance.authority).addToPeople(person)
			def userRole=new UserRole()
			userRole.user=person;
			userRole.role=Authority.findByAuthority(roleInstance.authority);
			userRole.save();
			
		}
	
	public void addRoles(def person,def params) {
		System.out.println params
		
		def roleInstance = getRolebyId(params)
		println "++++++++++++++++roleInstance.authority+++++++++" +roleInstance.authority
		println"+++params.usertype+++"+params.authorities
		
		Authority.findByAuthority(roleInstance.authority).addToPeople(person)
		
	}
	
public void addRolesUser(def person,def params) {
	println"+++params.usertype+++"+params.authorities
	  // if(params.authorities == 2)
		//Authority.findByAuthority('ROLE_USER').addToPeople(person)
		
	def userRole=new UserRole()
	userRole.user=person;
	userRole.role=Authority.findByAuthority('ROLE_USER');
	userRole.save();
	}
	
	
	public void addRolesSiteAdmin(def person,def params) {
		  
		// if(params.authorities == 3)
		println"+++params.usertype+++"+params.authorities
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
		
		System.out.println "in addUserMap  :"+params.get("party.id")
		System.out.println params
		def userMap=new UserMap()
		userMap.user=person
			
		def party=  Party.find("from Party  PA  where PA.id= "+params.get("party.id"))
		System.out.println "in addUserMap party :"+party
		userMap.party=party
		
		def userMapInstance= UserMap.find("from UserMap  UM  where UM.user = "+person.id)
		if(userMapInstance)
			userMapInstance.delete();
		System.out.println "inside if userMap.party"+userMap
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
		println "+++++++++++++++++++++++++saveNewUser++++++++++++++++++++++++++++"
		if(userInstance.save())
		{
			println "+++++++++++++++++++++++++after userInstance.save()++++++++++++++++++++++++++++"
			println "+++++++++++++++++++++++++call addNewUserRoles++++++++++++++++++++++++++++"
			addRolesSiteAdmin(userInstance,params)
			println "+++++++++++++++++++++++++after addNewUserRoles++++++++++++++++++++++++++++"
		}
		return userInstance
	}
	public Person saveNewPi(def userInstance)
	{
		println "userInstance for Pi"
		if(userInstance.save())
		{
			println "userInstance for Pi saved"
			addRolesPi(userInstance)
		}
		return userInstance
	}
	public Integer saveNewUserMap(def person,def params){
		Integer userId = null
		System.out.println "in saveNewUserMap  +++++++++++++:"+person.party.id
		System.out.println params
		def userMap=new UserMap()
		userMap.user=person.user
		def party=  Party.find("from Party  PA  where PA.id= "+params.get("party.id"))
		System.out.println "in addUserMap party :"+party
		userMap.party=person.party
		
		System.out.println "inside if addNewUserMap.party"+userMap
		userMap.createdBy="admin"
     	userMap.modifiedBy="admin"
        userMap.save()
        System.out.println "after save usermap  +++++++++++++:"+person.party.id
		userId=userMap.user.id
        
		return userId
	}
	public Party addParty(def partyInstance)
	{
		println "+++++++++++++++++++++inside addParty++++++++++++++"+partyInstance.nameOfTheInstitution
		println "+++++++++++++++++++++before saveParty++++++++++++++"+partyInstance
        def partyService = new PartyService()
        def party = new Party()
        party = partyService.saveParty(partyInstance)
        println "+++++++++++++++++++++after saveParty++++++++++++++"
        println "+++++++++++++++++++++Party Id++++++++++++++" +party.id
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
		def roleInstance = Authority.findAll("from Authority R where R.authority NOT IN ('ROLE_SITEADMIN','ROLE_PI') and R.activeYesNo='Y'")
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
		def person  = Person.find("from Person U where U.username='"+userName+"'");
		if(person)
			userId = person.id
		return userId
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
		 def authorityInstanceSave = updateRole(authorityInstance)
		 return authorityInstanceSave
	}
	/*
	 * Delete Role
	 */
	 public deleteRole(def authorityInstance)
	{
		 authorityInstance.activeYesNo="N"
		 def authorityInstanceDelete = updateRole(authorityInstance)
		 return authorityInstanceDelete
	}
	/*
	 * Update Role
	 */
	 public boolean updateRole(def authorityInstance)
	{
		def authorityInstanceUpdate =false 
			if(!authorityInstance.hasErrors() && authorityInstance.save()) 
			{
				authorityInstanceUpdate=true
			}
			else
			{
				println "false++++++++++++++++"
				authorityInstanceUpdate=false
			}
		return authorityInstanceUpdate
	}
	
	 /*
	  * Getting Authority details based on authority
	  */
	 public List getDuplicateRole(def authorityInstance)
	 {
		 def chkDuplicateRoleInstance
		try
		{
		 chkDuplicateRoleInstance = Authority.findAll("from Authority AT where AT.authority = '"+ authorityInstance.authority+"'")
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


}
