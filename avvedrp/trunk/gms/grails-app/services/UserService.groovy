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
	public User getUserById(Integer userId){
		def person = User.get(userId)
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
			Role.findAll().each { it.removeFromPeople(person) }
			person.delete()
				
		return person.id
	}
	
	/**
	 * Function to update user.
	 */
	public Integer updateUser(def person,def params){
		Integer userId = null
		if (person.save()) {
			Role.findAll().each { it.removeFromPeople(person) }
			addRoles(person,params)
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
			addRoles(person,params)
			addUserMap(person,params) 
			userId = person.id
		}
		return userId
	}
	/**
	 * Function to add roles to user
	 */
	public void addRoles(def person,def params) {
		System.out.println params
		
		def roleInstance = getRolebyId(params)
		//println "++++++++++++++++roleInstance.authority+++++++++" +roleInstance.authority
		if(params.usertype=="ROLE_ADMIN")
		Role.findByAuthority(roleInstance.authority).addToPeople(person)
		else
		Role.findByAuthority('ROLE_USER').addToPeople(person)
		
	}
	
	
	public void addRolesSiteAdmin(def person,def params) {
		
		Role.findByAuthority('ROLE_SITEADMIN').addToPeople(person)
		
	}
	public void addRolesPi(def person)
	{
		Role.findByAuthority('ROLE_PI').addToPeople(person)
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
		def authorityList = Role.list()
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
	public User saveNewUser(def userInstance,def params)
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
	public User saveNewPi(def userInstance)
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
	public Role getRolebyId(def params)
	{
		def roleInstance = new Role()
		roleInstance = Role.find("from Role R where R.id="+params.get("authorities"))
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
	 * Function to get user by name.
	 */
	public Integer getUserByUserName(String userName){
		println "+++++++++++++++++++++inside getUserByUserName++++++++++++++" +userName
		Integer userId = null;
		def person  = User.find("from User U where U.username='"+userName+"'");
		if(person)
			userId = person.id
		return userId
	}
}