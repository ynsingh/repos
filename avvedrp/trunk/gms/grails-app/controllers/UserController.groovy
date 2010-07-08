import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession


/**
 * User controller.
 */
class UserController extends GmsController {

	def authenticateService

	// the delete, save and update actions only accept POST requests
	static Map allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

	def index = {
		redirect action: list, params: params
	}

	def list = {
		
		GrailsHttpSession gh=getSession()
        gh.removeValue("Help")
       		//putting help pages in session
       	gh.putValue("Help","User_List.htm")	
		if (!params.max) {
			params.max = 100
		}
		def userMapList
		def userService = new UserService()
		 String subQuery ="";
        //GrailsHttpSession gh=getSession()
        if(params.sort != null && !params.sort.equals(""))
        	subQuery=" order by UM."+params.sort
        if(params.order != null && !params.order.equals(""))
        	subQuery =subQuery+" "+params.order
        //println"+++++++++params++++++++++"+params
		 if(getUserRole().equals('ROLE_ADMIN')){
		  userMapList = userService.getAllUsers(subQuery)
		 }
		  else
		  {
			  userMapList = userService.getAllUsersFromSite(getUserPartyID(),subQuery)
		  }
			  
		
		[userMapList: userMapList]
	}

	def show = {
		def userService = new UserService()
		def person = userService.getUserById(new Integer(params.id))
		if (!person) {
			flash.message = "User not found with id $params.id"
			redirect action: list
			return
		}
		
		def roleNames = userService.getRolesOfUser(person)
		[person: person, roleNames: roleNames]
	}

	/**
	 * Person delete action. Before removing an existing person,
	 * he should be removed from those authorities which he is involved.
	 */
	def delete = {

		def userService = new UserService()
		def person = userService.getUserById(new Integer(params.id))
		if (person) {
			def authPrincipal = authenticateService.principal()
			//avoid self-delete if the logged-in user is an admin
			if (!(authPrincipal instanceof String) && authPrincipal.username == person.username) {
				flash.message = "You can not delete yourself, please login as another admin and try again"
			}
			else {
				
				//first, delete this person from People_Authorities table.
				Integer userId = userService.deleteUser(person)
				flash.message = "User ${params.username} deleted."
				}
		}
		else {
			flash.message = "User not found with id $params.id"
		}

		redirect action: list
	}

	def edit = {

		def userService = new UserService()
		def person = userService.getUserById(new Integer(params.id))
		def party = userService.getParty(person)
		def grantAllocationInstance = new GrantAllocation();
		grantAllocationInstance.party=party;
		if (!person) {
			flash.message = "User not found with id $params.id"
			redirect action: list
			return
		}

		//return buildPersonModel(person)
		return [person:person, grantAllocationInstance : grantAllocationInstance ]
	}

	/**
	 * Person update action.
	 */
	def update = {

		def userService = new UserService()
		def person = userService.getUserById(new Integer(params.id))
		if (!person) {
			flash.message = "User not found with id $params.id"
			redirect action: edit, id: params.id
		}
			else
			{
				flash.message = "User ${params.username} updated successfully"
				redirect action:list,id:params.id
			}
		 return
		

		long version = params.version.toLong()
		if (person.version > version) {
			person.errors.rejectValue 'version', "person.optimistic.locking.failure",
				"Another user has updated this User while you were editing."
				render view: 'edit', model: buildPersonModel(person)
			return
		}

		def oldPassword = person.passwd
		person.properties = params
		if (!params.passwd.equals(oldPassword)) {
			person.passwd = authenticateService.encodePassword(params.passwd)
		}
		Integer personId = userService.saveUser(person,params)
		if(personId != null){
			redirect action: list
		}
		else {
			render view: 'edit', model: buildPersonModel(person)
		}
	}

	def create = {
		def userService = new UserService()
		def authorityList = userService.getRoles()
		GrailsHttpSession gh=getSession()
        gh.removeValue("Help")
       		//putting help pages in session
       	gh.putValue("Help","New_User.htm")	
		[person: new User(params), authorityList: authorityList]
	}
	

	/**
	 * Person save action.
	 */
	def save = {
		def userService = new UserService()
		
		def person = new User()
		person.properties = params
		person.passwd = authenticateService.encodePassword(params.passwd)
		person.enabled=true
		println params.get("party.id")
		if(params.get("party.id")==null)
			params.put("party.id",getUserPartyID())
	    Integer personId = userService.saveUser(person,params)
	    if(personId != null){
	    	flash.message = "User ${params.username} created"
			redirect action: list, id: personId
		}
		else {
			def authorityList = userService.getRoles()
			render view: 'create', model: [authorityList: authorityList, person: person]
		}
	}
	
	/**
	 * Person update password action.
	 */
	def updatePassword={
		def userService = new UserService()
		System.out.println("*******************Update password********************")
		System.out.println("params  "+params.oldPasswd)
		def person = userService.getUserById(new Integer(params.id))
		System.out.println("person  "+person)
		if (!person) {
			flash.message = "User not found with id $params.id"
			redirect action: changePassword, id: params.id
			return
		}

		long version = params.version.toLong()
		if (person.version > version) {
			person.errors.rejectValue 'version', "person.optimistic.locking.failure",
				"Another user has updated this User while you were editing."
				render view: 'changePassword', model: [person: person]
			return
		}
		
		/* Check whether entered old password is correct or not. */
		def oldPasswd = authenticateService.encodePassword(params.oldPasswd)
		def oldPassword = person.passwd
		if(!oldPasswd.equals(oldPassword)){
			flash.message = "Old password not correct"
			redirect action: changePassword, id: person.id
		}
		else{
			person.properties = params
			if (!params.newPasswd.equals(oldPassword)) {
				person.passwd = authenticateService.encodePassword(params.newPasswd)
				
				Integer personId = userService.updatePassword(person)
				if(personId != null){
					flash.message = "Password changed"
					redirect action: changePassword, id: personId
				}
				else{
					flash.message = "Password not changed"
					render view: 'changePassword', model: [person: person]
				}
			}
		}
		
		
	}
	
	/**
	 * Person change password action
	 */
	def changePassword = {
		GrailsHttpSession gh=getSession()
		def userId = gh.getValue("UserId")
		
		def dataSecurityService = new DataSecurityService()
		def person = dataSecurityService.getUserOfLoginUser(userId)
		if (!person) {
			flash.message = "User not found with id $params.id"
			
		}
				
		[person:person] 
	}

	

	private Map buildPersonModel(person) {

		def userService = new UserService()
		List roles  = userService.getRoles()
//		List roles = Role.list()
		roles.sort { r1, r2 ->
			r1.authority <=> r2.authority
		}
		Set userRoleNames = []
		for (role in person.authorities) {
			userRoleNames << role.authority
		}
		LinkedHashMap<Role, Boolean> roleMap = [:]
		for (role in roles) {
			roleMap[(role)] = userRoleNames.contains(role.authority)
		}

		return [person: person, roleMap: roleMap]
	}
	def newUserCreate = {
			def userService = new UserService()
			def authorityList = userService.getRoles()
			[person: new UserMap(params), authorityList: authorityList]
		
	}
	def saveNewUser = {
			def userService = new UserService()
			
			def person = new UserMap()
			def user = new User()
			def newParty = new Party()
			def partyInstance = new Party()
			person.properties = params
			println "+++++++++++++++++++++++++params++++++++++++++++++++++++++++"+ params
			user.username= params.user.username
			println "+++++++++++++++++++++++++params.user.username++++++++++++++++++++++++++++"+ params
			user.userRealName = params.user.userRealName
			user.passwd = params.user.passwd
			user.email = params.user.email
			user.passwd = authenticateService.encodePassword(params.user.passwd)
			user.enabled=true
			Integer userId  = userService.getUserByUserName(params.user.username)
			println "+++++++++++++++++++++++++userInst++++++++++++++++++++++++++++"+ userId
			if(userId)
			{
				flash.message = "User Name already exists"
				redirect uri: '/user/newUserCreate.gsp'
			}
			else
			{
				println "4444444444444before ++++++++++++++"+partyInstance
		        partyInstance.createdBy="admin"
		        partyInstance.createdDate = new Date();
		        partyInstance.modifiedBy="admin"
		        partyInstance.modifiedDate = new Date();
		        partyInstance.nameOfTheInstitution = params.get("party.code")
		        partyInstance.code = params.get("party.code")
		        partyInstance.address = ""
		        partyInstance.activeYesNo = 'Y'
		       
		        partyInstance.email = ""
		        partyInstance.phone = ""
		        println " ++++++++++++++"+partyInstance.code
		        println " ++++++++++++++"+partyInstance.nameOfTheInstitution
		        println " ++++++++++++++"+partyInstance.address
		        println " ++++++++++++++"+partyInstance.phone
		        println " ++++++++++++++"+partyInstance.email
		        println " ++++++++++++++"+partyInstance.partyType
		        println " ++++++++++++++"+partyInstance.activeYesNo
		        println " ++++++++++++++"+partyInstance.createdBy
		        println " ++++++++++++++"+partyInstance.createdDate
		        println " ++++++++++++++"+partyInstance.modifiedBy
		        println " ++++++++++++++"+partyInstance.modifiedDate
		              
		       
		      
				println "+++++++++++++++++++++++++before call addParty++++++++++++++++++++++++++++" +partyInstance
				def party = userService.addParty(partyInstance)
				println "+++++++++++++++++++++++++after call addParty++++++++++++++++++++++++++++" 
			if((partyInstance.saveMode != null) && (partyInstance.saveMode.equals("Duplicate")))
	       		 {
	       			flash.message = "Institution Already Exists"
	               redirect uri: '/user/newUserCreate.gsp'
	       		}
			else
			{
				def userInstance = userService.saveNewUser(user,params)
				person.user = userInstance
				person.user.id = userInstance.id;
				person.party = party
				person.party.id = party.id
				println "+++++++++++++++++++++++++person.party.id++++++++++++++++++++++++++++"+ person.party.id
				Integer personId = userService.saveNewUserMap(person,params)
			   
			    if(personId != null){
					redirect uri: '/login/auth.gsp'
				}
		       		 }
			}
			
		}
	
}
