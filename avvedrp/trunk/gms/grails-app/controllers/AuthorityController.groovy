class AuthorityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }
    def userService = new UserService()
    def create = {
        def authorityInstance = new Authority()
        authorityInstance.properties = params
        def authorityInstanceList = userService.getAllRolls()
        return [authorityInstance: authorityInstance,
                authorityInstanceList: authorityInstanceList, 
                authorityInstanceTotal: Authority.count()]
    }

    def save = 
    {
        def authorityInstance = new Authority(params)
        def chkDuplicateRoleInstance = userService.getDuplicateRole(authorityInstance)
        if(chkDuplicateRoleInstance)
        {
        	flash.message = "Role already exists"
        	redirect(action: "create", id: authorityInstance.id)
        }
        else
        {
        	def authorityInstanceSave = userService.saveRole(authorityInstance)
        	if(authorityInstanceSave)
        	{
        		flash.message = "${message(code: 'default.created.label')}"
        		redirect(action: "create", id: authorityInstance.id)
        	}
        	else 
        	{
            render(view: "create", model: [authorityInstance: authorityInstance])
        	}
        }
    }

    def edit = {
    	def authorityInstance = userService.getRoleById(params.id)
        if (!authorityInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
        else {
            return [authorityInstance: authorityInstance]
        }
    }

    def update = 
    {
    	def authorityInstance= new Authority()
    	authorityInstance = userService.getRoleById(params.id)
        if (authorityInstance) 
        {
            
            authorityInstance.properties = params
            println "authorityInstance "+authorityInstance.authority
            def chkDuplicateRoleInstance = userService.getDuplicateRole(authorityInstance)
            if(chkDuplicateRoleInstance)
            {
            	
            	if((chkDuplicateRoleInstance[0].id).equals(authorityInstance.id))
            	{
            		if (userService.updateRole(authorityInstance))
                	{
            			flash.message = "${message(code: 'default.updated.label')}"
                		redirect(action: "create", id: authorityInstance.id)
                	}
                	else 
                	{
                		println "same else"
                		flash.message = "Role already exists"
                		render(view: "edit", model: [authorityInstance: authorityInstance])
                	}
            	}
            	else
            	{
	            	flash.message = "Role already exists"
	            	redirect(action: "edit", id: authorityInstance.id)
            	}
            }
            else
            {
            	if (userService.updateRole(authorityInstance))
            	{
        			flash.message = "${message(code: 'default.updated.label')}"
            		redirect(action: "create", id: authorityInstance.id)
            	}
            	else 
            	{
            		flash.message = "Role already exists"
            		render(view: "edit", model: [authorityInstance: authorityInstance])
            	}
            }
        }
        else 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create")
        }
    }

    /*============================15-11-2010==========================================*/
    def delete = {
    		def userRoleInstance = UserRole.findAll("from UserRole UR where UR.role="+params.id)
    		def rolePrivilegesInstance=RolePrivileges.findAll("from RolePrivileges RP where RP.role="+params.id)
    		if (!userRoleInstance&&!rolePrivilegesInstance)
    		{	
    		  def authorityInstance = userService.getRoleById(params.id)
    	      if (authorityInstance) 
	          {	    
    	    	  	def authorityInstanceDelete = userService.deleteRole(authorityInstance)
	            	
	            	 if (authorityInstanceDelete)
	            	 {
	            		 flash.message = "${message(code: 'default.deleted.label')}"
	                     //redirect(action: "list")
	                     redirect(action: "create", id: params.id)
	            	 }
	                 else 
	                 {
	                     flash.message = "${message(code: 'default.notfond.label')}"
	                     //redirect(action: "list")
	                     redirect(action: "create", id: params.id)
	                 }
	            }
    		}
    		else
    		{	flash.message = "${message(code: 'default.UserAlreadyUse.label')}"
    			redirect(action: "edit", id: params.id)	
    		}
    }
 /*=============================== END ===============================================*/
    
}
