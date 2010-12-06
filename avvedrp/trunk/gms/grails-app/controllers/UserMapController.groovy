import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class UserMapController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        def userMapInstanceList
        def userService = new UserService()
        def dataSecurityService = new DataSecurityService()
        GrailsHttpSession gh=getSession()
        if(gh.getValue("Role").equals('ROLE_USER'))
        {
        	userMapInstanceList=dataSecurityService.getUserMapByProjectsAndPartiesOfLoginUser(gh.getValue("ProjectID"),gh.getValue("PartyID"))
        }
        else
        {
        	userMapInstanceList = userService.getAllUserMap()
        }
//        UserMap.list( params )
        [ userMapInstanceList: userMapInstanceList ]
    }

    def show = {
		def userService = new UserService()
		def userMapInstance = userService.getUserMapById(new Integer(params.id))

        if(!userMapInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ userMapInstance : userMapInstance ] }
    }

    def delete = {
        def userService = new UserService()
        Integer userMapId = userService.deleteUserMap(new Integer(params.id))
        if(userMapId != null){
            flash.message = "${message(code: 'default.deleted.label')}"
            redirect(action:create)
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
    }

    def edit = {
		def userService = new UserService()
		def userMapInstance = userService.getUserMapById(new Integer(params.id))

        if(!userMapInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else {
            return [ userMapInstance : userMapInstance ]
        }
    }

    def update = {
		def userService = new UserService()
		def userMapInstance = userService.updateUserMap(params)
		if(userMapInstance){
			if(userMapInstance.saveMode != null){
				if(userMapInstance.saveMode.equals("Updated")){
					flash.message = "${message(code: 'default.updated.label')}"
	                redirect(action:create,id:userMapInstance.id)
				}
				else if(userMapInstance.saveMode.equals("Duplicate")){
					flash.message = "${message(code: 'default.UserMapexists.label')}"
					render(view:'edit',model:[userMapInstance:userMapInstance])
				}
			}
			else {
                render(view:'edit',model:[userMapInstance:userMapInstance])
            }
		}
		else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def userMapInstance = new UserMap()
        userMapInstance.properties = params
        
        GrailsHttpSession gh=getSession()
        def dataSecurityService = new DataSecurityService()
        
        /* Get users who are allocated to the selected project and not allocated to any project */
        def userInstanceList = dataSecurityService.getUsersOfAssignedProjects(gh.getValue("Role"),gh.getValue("ProjectID"))

        /* Get projects which are allocated to the login user */
       // def projectsInstanceList = dataSecurityService.getProjectsAndSubProjectsOfLoginUser(gh.getValue("Role"),gh.getValue("ProjectID"))

        /* Get parties which are allocated to the login user */
        def partyInstanceList = dataSecurityService.getPartiesAndSubPartiesOfLoginUser(gh.getValue("Role"),gh.getValue("PartyID"))

        /* Get users assigned to projects */
        def userMapInstanceList = dataSecurityService.getUsersAssignedToProjects(gh.getValue("Role"),gh.getValue("ProjectID"))
        
        return ['userMapInstance':userMapInstance,userMapInstanceList: userMapInstanceList,
                userInstanceList:userInstanceList,projectsInstanceList:projectsInstanceList,
                partyInstanceList:partyInstanceList]
    }

    def save = {
        def userMapInstance = new UserMap(params)
        println("userMapInstance = " + userMapInstance )
        if(!userMapInstance.hasErrors() ) {
            flash.message ="${message(code: 'default.UserMappedtoProject.label')}"
            
    	    userMapInstance.createdBy="admin"
    		userMapInstance.modifiedBy="admin"
    		
			def userService = new UserService()
    		userMapInstance = userService.saveUserMap(userMapInstance)
			if(userMapInstance.saveMode != null){
    			if(userMapInstance.saveMode.equals("Saved")){
    				userMapInstance.save()
    			}
    			else{
        			flash.message = "${message(code: 'default.UserAlreadyMappedtoProject.label')}"
        		}
    		}
			redirect(action:create)
        }
        else {
            render(view:'create',model:[userMapInstance:userMapInstance])
        }
    }
    
    
}
