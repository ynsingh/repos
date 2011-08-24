import grails.converters.*
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.ControllerArtefactHandler
import org.springframework.beans.BeanWrapper
import org.springframework.beans.PropertyAccessorFactory

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
        def chkDuplicateRoleInstance = userService.getDuplicateRole(params)
        if(chkDuplicateRoleInstance)
        {
        	flash.message ="${message(code: 'default.RoleAlreadyExists.label')}"
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
    	def authorityInstance= new Authority(params)
    	// println"authorityInstance......"+authorityInstance
    	println"params"+params
    
        if (params.authority) 
        {
            def chkDuplicateRoleInstance = userService.getDuplicateRole(params)
                       	
            	if(chkDuplicateRoleInstance && ((chkDuplicateRoleInstance[0].id) != Long.parseLong(params.id)))
            	{
            		flash.message = "Role already exists"
            			redirect(action: "create", id: params.id)
            	}
            	else
            	{
            		    authorityInstance = userService.updateRole(params)
            		    println"authorityInstance"+authorityInstance
            		    if(authorityInstance == true)
            		    {
            			flash.message = "${message(code: 'default.updated.label')}"
                		redirect(action: "create", id: params.id)
            		    }
            		    else
            		    {
            		    	render(view:'edit',model:[params:params])
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
