import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.commons.ControllerArtefactHandler
import org.springframework.beans.BeanWrapper
import org.springframework.beans.PropertyAccessorFactory
import org.apache.commons.validator.EmailValidator
/**
 * User controller.
 */
 

class UserController extends GmsController {
	def authenticateService
	def grantAllocationService
	def dataSource 
    def dataSecurityService
    def notificationsEmailsService
    def userService
    
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
		def authorityList = []
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
		if(userMapList)
		  {
			 for(int i=0;i<userMapList.size();i++)		 
			 {				 				 
				 /*
				  * 1.get userRoleInstance based on the user id
				  * 2. get authorityInstsnce based on the role
				  */
				 
				  def userRoleInstance = userService.getuserRole(userMapList[i].user.id)		  
				  println "ROLE INSTANCE -->" + userRoleInstance.role
					
				  if(userRoleInstance.role)
				  {
					  def authorityInstsnce = userService.getauthority(userRoleInstance.role.id)
					  println "AUTHORITY INSTANCE -->" +authorityInstsnce
					  authorityList.add(authorityInstsnce)
				  }
			  }
		   }
		[userMapList: userMapList,authorityList:authorityList]	  
 
	}
	
	def show = {
		def userService = new UserService()
		def person = userService.getUserById(new Integer(params.id))
		if (!person) {
			flash.message = "${message(code: 'default.notfond.label')}"
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
				flash.message = "${message(code: 'default.cannotdeleteyourself.label')}"
			}
			else {
				
				//first, delete this person from People_Authorities table.
				Integer userId = userService.deleteUser(person)
				flash.message = "${message(code: 'default.deleted.label')}"
				}
		}
		else {
			flash.message = "${message(code: 'default.notfond.label')}"
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
			flash.message = "${message(code: 'default.notfond.label')}"
			redirect action: list
			return
		}
		//get active roles
		def authorityInstance = userService.getActiveRoles()
		//return buildPersonModel(person)
		return [person:person, grantAllocationInstance : grantAllocationInstance,
		        authorityInstance:authorityInstance]
	}

	/**
	 * Person update action.
	 */
	def update = {

		def userService = new UserService()
		def person = userService.getUserById(new Integer(params.id))
		def ctx = AH.application.mainContext
		def springSecurityService=ctx.springSecurityService
		

		long version = params.version.toLong()
		if (person.version > version) {
			person.errors.rejectValue 'version', "person.optimistic.locking.failure",
				"Another user has updated this User while you were editing."
				render view: 'edit', model: buildPersonModel(person)
			return
		}
		println "+++++++++++++++++++++++++params++++++++++++++++++++++++++++"+ params
		println "person.password = "+ person.password
		println "params.passwd = "+ params.password
		def oldPassword = person.password
		person.properties = params
		if (!params.password.equals(oldPassword)) {
			person.password = springSecurityService.encodePassword(params.password)
		}
		Integer personId = userService.updateUser(person,params)
		if (!personId) {
			flash.message ="${message(code: 'default.notfond.label')}"
			redirect action: edit, id: params.id
		}
			else
			{
				flash.message = "${message(code: 'default.updated.label')}"
				redirect action:list,id:params.id
			}
		
		
	}

	def create = {
		def userService = new UserService()
		def authorityList = userService.getRoles()
		def authorityInstance = userService.getActiveRoles()
		  GrailsHttpSession gh=getSession()
			gh.removeValue("Help")
				//putting help pages in session
			gh.putValue("Help","New_User.htm")	
		[person: new Person(params), authorityList: authorityList,authorityInstance:authorityInstance]

	}
	

	/**
	 * Person save action.
	 */
	def save = {
		EmailValidator emailValidator = EmailValidator.getInstance()
		if (emailValidator.isValid(params.email))
		{
		def userService = new UserService()

		
		def ctx = AH.application.mainContext
		def springSecurityService=ctx.springSecurityService
		Integer userId  = userService.getUserByUserName(params.email)
		println "+++++++++++++++++++++++++userInst++++++++++++++++++++++++++++"+ userId
		if(userId)
		{
			flash.message = "${message(code: 'default.UserNamealreadyexists.label')}"
			redirect action: create
		}
		def person = new Person()
		//person.properties = params
		person.username=params.email
		person.userRealName=params.userRealName
		person.email=params.email
		person.password=params.password
		person.password = springSecurityService.encodePassword(params.password)
		person.enabled=false
		println "paswwod"+params.password
		println params.get("party.id")
		if(params.get("party.id")==null)
			params.put("party.id",getUserPartyID())
	    Integer personId = userService.saveUser(person,params)
	    if(personId != null){
	    	String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/user/userActivation/"
	    	def emailId = notificationsEmailsService.sendMessage(params.email,params.password,params.userRealName,personId,urlPath)
	    	flash.message = "${message(code: 'default.created.label')}"
			redirect action: list, id: personId
		}
		else {
			def authorityList = userService.getRoles()
			render view: 'create', model: [authorityList: authorityList, person: person]
		}
		}
		else
		{
			flash.message = "${message(code: 'default.EntervalidEmailAddress.label')}"
				def authorityList = userService.getRoles()
			render view: 'create', model: [authorityList: authorityList, person: params]
		}
	}
	
	/**
	 * Person update password action.
	 */
	def updatePassword={
		println "params======"+params
		def userService = new UserService()
		def ctx = AH.application.mainContext
		def springSecurityService=ctx.springSecurityService
		def personInstance = new Person()
		System.out.println("*******************Update password********************")
		System.out.println("params  "+params.oldPasswd)
		def person = userService.getUserById(new Integer(params.id))
		System.out.println("person  "+person)
		if (!person) {
			flash.message ="${message(code: 'default.notfond.label')}"
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
		def oldPasswd = springSecurityService.encodePassword(params.oldPasswd)
		def oldPassword = person.password
		if(!oldPasswd.equals(oldPassword)){
			flash.message ="${message(code: 'default.Oldpasswordnotcorrect.label')}"
			redirect action: changePassword, id: person.id
		}
		else{
			person.properties = params
			if (!params.newPasswd.equals(oldPassword)) {
				person.password = springSecurityService.encodePassword(params.newPasswd)
				
				Integer personId = userService.updatePassword(person)
				if(personId != null){
					flash.message = "${message(code: 'default.Passwordchanged.label')}"
					redirect action: changePassword, id: personId
				}
				else{
					flash.message = "${message(code: 'default.Passwordnotchanged.label')}"
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
			flash.message = "${message(code: 'default.notfond.label')}"
			
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
		LinkedHashMap<Authority, Boolean> roleMap = [:]
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
			println "+++++++++++++++++++++++++params++++++++++++++++++++++++++++"+ params
			println "+++++=save new user+++++++++"
			EmailValidator emailValidator = EmailValidator.getInstance()
			if (emailValidator.isValid(params.email))
			{
			def ctx = AH.application.mainContext
			def springSecurityService=ctx.springSecurityService
			def userService = new UserService()
			
			def person = new UserMap()
			def user = new Person()
			def newParty = new Party()
			def partyInstance = new Party()
			person.properties = params
			println "+++++++++++++++++++++++++params++++++++++++++++++++++++++++"+ params
			user.username= params.email
			println "+++++++++++++++++++++++++params.user.username++++++++++++++++++++++++++++"+ params
			user.userRealName = params.userRealName
			user.password = params.password
			user.email = params.email
			user.password = springSecurityService.encodePassword(params.password)
			user.enabled=false
			Integer userId  = userService.getUserByUserName(params.email)
			println "+++++++++++++++++++++++++userInst++++++++++++++++++++++++++++"+ userId
			if(userId)
			{
				flash.message = "${message(code: 'default.UserNamealreadyexists.label')}"
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
	       			flash.message = "${message(code: 'default.InstitutionAlreadyExists.label')}"
	               redirect uri: '/user/newUserCreate.gsp'
	       		}
			else
			{
				
				
				 println " ++++++++++++++"+user.username
			        println " ++++++++++++++"+user.password
			        println " ++++++++++++++"+user.email
			        println " ++++++++++++++"+user.userRealName
			        println " ++++++++++++++"+user.email
			        println " ++++++++++++++"+user.enabled
			        println " ++++++++++++++"+user.accountExpired
			        println " ++++++++++++++"+user.accountLocked
			        println " ++++++++++++++"+user.passwordExpired
			        println " user.hasErrors :"+user.hasErrors()

				def userInstance = userService.saveNewUser(user,params)
				
				person.user = userInstance
				person.user.id = userInstance.id;
				
				
				
		       	println "party user-=-"+party	
				person.party = party
				person.party.id = party.id
				println "+++++++++++++++++++++++++person.party.id++++++++++++++++++++++++++++"+ person.party.id
				Integer personId = userService.saveNewUserMap(person,params)
			  
				//creating accespermission for all roles in new party
				 def webRootDir
				if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) 
				{
					webRootDir = servletContext.getRealPath("/")+"WEB-INF/grails-app/views/"
					//folder = webRootDir+"WEB-INF/grails-app/views"
				}
		       	if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT)) 
		       	{
		       		webRootDir = "grails-app/views/"
		       		//folder = new File("grails-app/views")
		       	}
		       //	def roleId=null
		       //	def rolePrivilegesService = new RolePrivilegesService()
	    		//def rolePrivilegesSaveStatus = rolePrivilegesService.saveRolePrivilegesForParty(party,webRootDir,roleId)
	    		//creating role privileges for each role in the new party starting
	    		def roleInstance = Authority.findAll("from Authority A")
	    		def data = []
	       		for (controller in grailsApplication.controllerClasses) {
	       			def controllerInfo = [:]
	       			controllerInfo.controller = controller.logicalPropertyName
	       			controllerInfo.controllerName = controller.fullName
	       			List actions = []
	       			BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(controller.newInstance())
	       			for (pd in beanWrapper.propertyDescriptors) {
	       				String closureClassName = controller.getPropertyOrStaticPropertyOrFieldValue(pd.name, Closure)?.class?.name
	       						if (closureClassName) 
	       							{
	       							actions << pd.name
	       								for(authorityInstance in roleInstance)
	       								{
	       									def rolePrivilegesInstance=new RolePrivileges()
	       									rolePrivilegesInstance.controllerName=controller.logicalPropertyName
	       									rolePrivilegesInstance.actionName=pd.name
	       									rolePrivilegesInstance.role=authorityInstance
	       									rolePrivilegesInstance.party=party
	       									rolePrivilegesInstance.save()
	       								}
	       							}
	       			}
	       			controllerInfo.actions = actions.sort()
	       			data << controllerInfo
	       		}
		       	//creating role privileges for each role in the new party ending
			    if(personId != null){
			    	String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/user/userActivation/"
			    	def emailId = notificationsEmailsService.sendMessage(params.email,params.password,params.userRealName,personId,urlPath)
					redirect uri: '/user/newUserConfirm.gsp'
				}
		       		 }
			}
			}
			else
			{
				flash.message = "${message(code: 'default.EntervalidEmailAddress.label')}"
				
				render(view: "newUserCreate", model: [person: params])
			}
			
		}
	
	/**
	 * Action to activate a user
	 */
	def userActivation = {
		   
			def userInstance = userService.getUserById(Integer.parseInt(params.id))
			if(userInstance.enabled==false)
			{
				userInstance.enabled=true
				userInstance.save()
				redirect uri: '/user/userConfirmation.gsp'
			}
			else
			{
				redirect(controller:'login',action:'auth')
			}
		}
	
	/**
	 * Action to change password
	 */
	def sendNewPassword = {
			if(params.email)
			{
			def userId = userService.getUserByUserName(params.email)
			def ctx = AH.application.mainContext
			def springSecurityService=ctx.springSecurityService
			
			if(userId)
			{
				def userInstance = userService.getUserById(userId)
				def subName=userInstance.username
				println userInstance.email
				println "params.email"+params.email
				String newPassword 
				println "subName.indexOf('@') "+subName.indexOf('@')
				if(subName.indexOf('@') < 0)
				{
				newPassword = subName.substring(0,1)
				println "newPassword for invalid"+newPassword
				
				}
				else
				{
					newPassword = subName.substring(0,subName.indexOf('@'))
					println "newPassword"+newPassword
					
				}
				EmailValidator emailValidator = EmailValidator.getInstance()
					if(userInstance.email)
					{
						if (emailValidator.isValid(userInstance.email))
						{
							userInstance.password=springSecurityService.encodePassword(newPassword)
							userInstance.enabled=false
							userInstance.save()
							String urlPath = request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/user/userActivation/"
							def emailId = notificationsEmailsService.sendChangePassword(userInstance.email,newPassword,userInstance.username,userInstance.id,urlPath)
							redirect uri: '/user/forgotPasswordConfirm.gsp'
						}
						else
						{
							flash.message = "${message(code: 'default.noEmailAddressAssociated.label')}" 
							render(view: "forgotPassword", model: [person: params])
						}
					}
					else
					{
						flash.message = "${message(code: 'default.noEmailAddressAssociated.label')}" 
						render(view: "forgotPassword", model: [person: params])
					}
				}
				else
				{
					flash.message = "${message(code: 'default.enterValidUserName.label')}"
					render(view: "forgotPassword", model: [person: params])
				}
			}
			else
			{
				flash.message = "${message(code: 'default.enterValidUserName.label')}"
				render(view: "forgotPassword", model: [person: params])
			}
	}
	def forgotPassword = {
			
	}
	def accessControlEntry = {
			
			List<GrantAllocation> grantAllocationInstance 	
		
	    
	    	
	    	
	    	try{
	    		grantAllocationInstance = grantAllocationService.getAll()
		
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}
	    	//def projectInstance = dataSecurityService.getAccessPermissionProjects()
			GrailsHttpSession gh=getSession()
			def userMapInstance = UserMap.findAll("from UserMap UM where UM.party.id="+gh.getValue("PartyID")+" and UM.user.id != "+gh.UserId)
			println "gh.getValuePartyID"+gh.getValue("PartyID")
			println "userMapInstance"+userMapInstance
			println "grantAllocationInstance"+grantAllocationInstance
			[grantAllocationInstance:grantAllocationInstance,userMapInstance:userMapInstance]
	}
	def getandSaveAccessPermission = {
			println "saveAccessPermission======"+params
			//def grantAllocationId = params.projectName
			//checking more than one value selected
			if(params.user.id){
			def grantAllocationId = params.projectName
			def grantList=params.projectName.toString();
    		println "emailsList"+grantList
    		def grantSplit=grantList.split(',')
    		if(grantSplit.length==1)
    		{
    			def grantAllot=[]
    			grantAllot.add(params.projectName)
    			grantAllocationId=grantAllot
    		}
    		
			
			println "dataSource"+dataSource
			
			def aclInstance = dataSecurityService.saveAccessPermisionDetails(grantAllocationId,params.user.id)
			println "aclInstance*(****_)*()_()_()_("+aclInstance
			redirect(action:getProjectName,params:[user:params.user.id])
			}
			else
			{
				
			}
	}
	
	//for listing project details of each user
	def getProjectName = {
			println "getProjectName"+params
			if(params.user)
			{
			
			List<GrantAllocation> grantAllocationInstance 	
		
	    
	    	
	    	
	    	try{
	    		grantAllocationInstance = grantAllocationService.getAll()
		
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}
	    	if(params.user != "null")
			{
	    	//def projectsInstance = dataSecurityService.getAccessPermissionProjects()
			GrailsHttpSession gh=getSession()
			def userMapInstance = UserMap.findAll("from UserMap UM where UM.party.id="+gh.getValue("PartyID"))
			println "userMapInstance"+userMapInstance
			println "params"+params.user
			println "grantAllocationInstance"+grantAllocationInstance
			def projectInstance = dataSecurityService.getAccessPermissionProjects(params.user)
			def grantAllocationList=[]
			def grantCollection=[]
			
			
			if(projectInstance)
			{
				grantCollection = new ArrayList(grantAllocationInstance.projects.id)
				grantCollection.removeAll(projectInstance.id)
				for(id in grantCollection)
				{
					def projectId = GrantAllocation.find("from GrantAllocation GA where GA.projects.id="+id)
					println "projectId"+projectId
					grantAllocationList.add(projectId)
				}
			}
			else
			{
				grantAllocationList=grantAllocationInstance
			}
			
			render (template:"projectNameSelect", model : ['grantAllocationInstance' : grantAllocationList,
			                                               'userMapInstance':userMapInstance,
			                                               'projectInstance':projectInstance])
			}
			else
			{
				println "gjghusd[][]gljdfl;jhkldfjhljdfiphdfukghdfukygukdfhi89898***"
				render (template:"projectNameSelect", model : ['grantAllocationInstance' : grantAllocationInstance])
			}
			}
			else
			{
				redirect action: accessControlEntry
			}
	}
	def deleteProjectFromAccespermision = {
			println "deleteProjectFromAccespermision=="+params
			def projectInstance = params.projectId
			def projectList=params.projectId.toString();
    		println "projectList"+projectList
    		def projectSplit=projectList.split(',')
    		if(projectSplit.length==1)
    		{
    			def projectArray=[]
    			projectArray.add(params.projectId)
    			projectInstance=projectArray
    			println "projectInstance"+projectInstance
    		}
			def aclInstance = dataSecurityService.deleteAccessPermission(projectInstance,params.user.id)
			redirect(action:getProjectName,params:[user:params.user.id])
			
	}
	
}
