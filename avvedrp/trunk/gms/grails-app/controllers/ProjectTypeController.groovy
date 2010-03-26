class ProjectTypeController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ projectTypeInstanceList: ProjectType.list( params ) ]
    }

    def show = {
        def projectTypeInstance = ProjectType.get( params.id )

        if(!projectTypeInstance) {
            flash.message = "ProjectType not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ projectTypeInstance : projectTypeInstance ] }
    }

    def delete = {
        def projectTypeInstance = ProjectType.get( params.id )
        if(projectTypeInstance) {
            projectTypeInstance.delete()
            flash.message = "ProjectType ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "ProjectType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def projectTypeInstance = ProjectType.get( params.id )

        if(!projectTypeInstance) {
            flash.message = "ProjectType not found with id ${params.id}"
            redirect(action:list)
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
                flash.message = "ProjectType ${params.id} updated"
                redirect(action:list,id:projectTypeInstance.id)
            }
            else {
                render(view:'edit',model:[projectTypeInstance:projectTypeInstance])
            }
        }
        else {
            flash.message = "ProjectType not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def projectTypeInstance = new ProjectType()
        projectTypeInstance.properties = params
        return ['projectTypeInstance':projectTypeInstance]
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
	            flash.message = "ProjectType ${projectTypeInstance.type} created"
	            redirect(action:list,id:projectTypeInstance.id)
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
