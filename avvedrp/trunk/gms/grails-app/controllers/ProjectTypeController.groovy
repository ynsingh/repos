import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class ProjectTypeController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list =
    {
        if(!params.max) params.max = 10
         
       
        [ projectTypeInstanceList: ProjectType.list(params) ]
    }

    def show = {
        def projectTypeInstance = ProjectType.get( params.id )

        if(!projectTypeInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ projectTypeInstance : projectTypeInstance ] }
    }

    def delete = {
        def projectTypeInstance = ProjectType.get( params.id )
        if(projectTypeInstance) {
            projectTypeInstance.delete()
            flash.message = "${message(code: 'default.deleted.label')}"
            redirect(action:create)
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:create)
        }
    }

    def edit = {
        def projectTypeInstance = ProjectType.get( params.id )

        if(!projectTypeInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:create)
        }
        else {
            return [ projectTypeInstance : projectTypeInstance ]
        }
    }

    def update = {
        def projectTypeInstance = ProjectType.get( params.id )
        if(projectTypeInstance) {
            projectTypeInstance.properties = params
            if(!projectTypeInstance.hasErrors() && projectTypeInstance.save()) {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action:create,id:projectTypeInstance.id)
            }
            else {
                render(view:'edit',model:[projectTypeInstance:projectTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
    	GrailsHttpSession gh=getSession()
    	//Puting help page into session
    	gh.putValue("Help","Project_Type.htm")
        def projectTypeInstance = new ProjectType()
        projectTypeInstance.properties = params
        def projectTypeInstanceList=ProjectType.findAll("from ProjectType PT where PT.activeYesNo='Y'")
        return ['projectTypeInstance':projectTypeInstance,
                'projectTypeInstanceList': projectTypeInstanceList]
    }

    def save = {
        def projectTypeInstance = new ProjectType(params)
        if(!projectTypeInstance.hasErrors())
        {
        	projectTypeInstance.createdBy="admin"
        	projectTypeInstance.createdDate = new Date();
        	projectTypeInstance.modifiedBy="admin"
        
	        if(projectTypeInstance.save()) 
	        {
	            flash.message = "${message(code: 'default.created.label')}"
	            redirect(action:create,id:projectTypeInstance.id)
	        }
	        
	        else {
	            render(view:'create',model:[projectTypeInstance:projectTypeInstance])
	        }
        }
        else {
            render(view:'create',model:[projectTypeInstance:projectTypeInstance])
        }
    }
}
