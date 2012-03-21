import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class UserRoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def userService
    def index = {
        redirect(action: "create", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userRoleInstanceList: UserRole.list(params), userRoleInstanceTotal: UserRole.count()]
    }

    def create = {
    	def userRoleInstanceList =  userService.getUserRoleByUserId(params.id)
      	def personMapInstance = userService.getAllUserMapByUserId(params.id)
      	def roleList = userService.getAuthoritiesExceptSiteAdminPiAndSuperAdmin()
    	roleList.removeAll(userRoleInstanceList)
     	return ['roleList': roleList , 'userRoleInstanceList' : userRoleInstanceList,'personMapInstance':personMapInstance]
    }

   def getUserRole =
    {
     	def userRoleInstanceList =  userService.getUserRoleByUserId(params.id)
     	def personMapInstance = userService.getAllUserMapByUserId(params.id)
     	def roleList = userService.getAuthoritiesExceptSiteAdminPiAndSuperAdmin()
    	roleList.removeAll(userRoleInstanceList)
     	render (template:"assignUserRole" , model: ['roleList': roleList , 'userRoleInstanceList' : userRoleInstanceList,'personMapInstance':personMapInstance])
    } 
	 
    def assignRolesToUser =
	{
    	GrailsHttpSession gh=getSession()
    	def partyInstance = Party.get(gh.getValue("Party"))
    	def userRoleInstanceList = []
     	def userRoleInstance
    	userRoleInstanceList = params.roleId
        def userRoleList = params.roleId.toString()
        def userRoleListSplit=userRoleList.split(',')
         if(userRoleListSplit.length == 1)
           {
	        	def userRoleDet=[]
	        	userRoleDet.add(params.roleId)
	        	userRoleInstanceList=userRoleDet 
           }
    	
    	for(int i=0;i<userRoleInstanceList.size();i++)
    	{
    		    userRoleInstance  = new UserRole()
    		    def roleInstance = Authority.get(userRoleInstanceList[i])
    		    userRoleInstance.role = roleInstance
    		    def personInstance = Person.get(params.personMapInstance.user.id)
    		    userRoleInstance.user = personInstance
	    		   if(roleInstance.authority == 'ROLE_PI')
		    		   {
		    			   def investigatorInstance = new Investigator()
		    			   investigatorInstance.email = personInstance.username
		    			   investigatorInstance.name = personInstance.userRealName
		    			   investigatorInstance.userSurName = personInstance.userSurName
		    			   investigatorInstance.activeYesNo = "Y" 
		    			   investigatorInstance.party = partyInstance
		    			   investigatorInstance.address = ""
		    			   investigatorInstance.Designation = ""
		    			   investigatorInstance.save()
		    			   userRoleInstance.save()
		    	      }
	    		  else
	    		  {
	    			  userRoleInstance.save()
	    		  }
    	}
     	redirect(action: "getUserRole", id: userRoleInstance.user.id)
	}
    def removeRolesFromUser =
    {
    	def flag = 0;
    	def userRoleRemoveList = []
    	def userRoleRemoveInstanceList = []
     	def userRoleInstance
     	def userRoleRemoveInstance
    	userRoleRemoveList = params.userRoleId
        def userRoleList = params.userRoleId.toString()
        def userRoleListSplit=userRoleList.split(',')
         if(userRoleListSplit.length == 1)
           {
	        	def userRoleDet=[]
	        	userRoleDet.add(params.userRoleId)
	        	userRoleRemoveList=userRoleDet 
           }
      	for(int i=0;i<userRoleRemoveList.size();i++)
    	{
    		userRoleRemoveInstance = userService.getauthority(userRoleRemoveList[i])
    		userRoleRemoveInstanceList.add(userRoleRemoveInstance)
    	}
      
    	def userRoleInstanceList = userService.getUserRoleByUserId(params.personMapInstance.user.id)
    	if(userRoleInstanceList.size()== userRoleRemoveInstanceList.size() )
    	 {
     		flash.message = "${message(code:'default.cantdeleteallroles.label')}"	
    	    redirect(action: "getUserRole", id: params.personMapInstance.user.id)
    	 }
    	else
    	 {
    		userRoleInstanceList.removeAll(userRoleRemoveInstanceList)
		     	for(int j=0;j<userRoleRemoveInstanceList.size();j++)
		    	{
		    		if(userRoleRemoveInstanceList[j].authority == 'ROLE_PI')
		    		{
		    			flag = flag+1
		    		}
		    		else
		    		{
		     			UserRole.executeUpdate("delete UserRole UR where UR.role.id = "+userRoleRemoveInstanceList[j].id+" and UR.user.id= "+params.personMapInstance.user.id+" ")
		    		}
		    	}
			    	if(flag>0)
			    	{
				    	flash.message = "${message(code:'default.cantdeletepi.label')}"	
				     	redirect(action: "getUserRole", id: params.personMapInstance.user.id)
			    	}
			    	else
			    	{
			    		redirect(action: "getUserRole", id: params.personMapInstance.user.id)
			    	}
    	}
    }
 
    def show = {
        def userRoleInstance = UserRole.get(params.id)
        if (!userRoleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])}"
            redirect(action: "create")
        }
        else {
            [userRoleInstance: userRoleInstance]
        }
    }

    def edit = {
        def userRoleInstance = UserRole.get(params.id)
        if (!userRoleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])}"
            redirect(action: "create")
        }
        else {
            return [userRoleInstance: userRoleInstance]
        }
    }

    def update = {
        def userRoleInstance = UserRole.get(params.id)
        if (userRoleInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userRoleInstance.version > version) {
                    
                    userRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'userRole.label', default: 'UserRole')] as Object[], "Another user has updated this UserRole while you were editing")
                    render(view: "edit", model: [userRoleInstance: userRoleInstance])
                    return
                }
            }
            userRoleInstance.properties = params
            if (!userRoleInstance.hasErrors() && userRoleInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'userRole.label', default: 'UserRole'), userRoleInstance.id])}"
                redirect(action: "create", id: userRoleInstance.id)
            }
            else {
                render(view: "edit", model: [userRoleInstance: userRoleInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])}"
            redirect(action: "create")
        }
    }

    def delete = {
        def userRoleInstance = UserRole.get(params.id)
        if (userRoleInstance) {
            try {
                userRoleInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])}"
            redirect(action: "list")
        }
    }
}

    
