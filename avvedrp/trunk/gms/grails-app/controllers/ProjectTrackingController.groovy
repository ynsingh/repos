class ProjectTrackingController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        
        def projectsService = new ProjectsService()
        def projectTrackingInstanceList = projectsService.getAllProjecTracking(params)
        [ projectTrackingInstanceList: projectTrackingInstanceList ]
    }

    def show = {
		def projectsService = new ProjectsService()
		def projectTrackingInstance = projectsService.getProjectTrackingById(new Integer( params.id ))

        if(!projectTrackingInstance) {
            flash.message = "ProjectTracking not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ projectTrackingInstance : projectTrackingInstance ] }
    }

    def delete = {
        def projectsService = new ProjectsService()
        Integer projectId = projectsService.deleteProjectTracking(new Integer(params.id))
        if(projectId != null){
            flash.message = "Project Closure deleted successfully"
        	redirect(action:create,id:projectId)
        }
        else {
            flash.message = "Project Closure not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
		def projectsService = new ProjectsService()
		def projectTrackingInstance = projectsService.getProjectTrackingById(new Integer( params.id ))

        if(!projectTrackingInstance) {
            flash.message = "Project Closure not found with id ${params.id}"
            redirect(action:list)
        }
        else {
        	/* Get project details */
        	Integer projectId = projectTrackingInstance.projects.id
    		def projectsInstance = projectsService.getProjectById(projectId)
    		
    		/* Get project tracking details if any */
    		def projectTrackingInstanceList = projectsService.getProjectTrackingByProject(projectsInstance) 
    		
            return [ projectsInstance:projectsInstance, projectTrackingInstance : projectTrackingInstance, projectTrackingInstanceList:projectTrackingInstanceList ]
        }
    }

    def update = {
		def projectsService = new ProjectsService()
		def projectTrackingInstance = projectsService.updateProjectTracking(params)
		
		if(projectTrackingInstance){
			if(projectTrackingInstance.saveMode != null){
				flash.message = "Project Closure updated successfully"
				redirect(action:create,id:projectTrackingInstance.projects.id)
			}
			else {
                render(view:'create',id:projectTrackingInstance.projects.id)
            }
		}
		else {
            flash.message = "Project Closure not found with id ${params.id}"
            redirect(action:create,id:params.projects.id)
        }
    }

    def create = {
		def projectsService = new ProjectsService()
		/* Get project details */
		def projectsInstance = projectsService.getProjectById(new Integer(params.id))
		
		/* Get project tracking details if any */
		def projectTrackingInstanceList = projectsService.getProjectTrackingByProject(projectsInstance) 
    		
        def projectTrackingInstance = new ProjectTracking()
		projectTrackingInstance.projects = projectsInstance
        projectTrackingInstance.properties = params
        return ['projectsInstance':projectsInstance, 'projectTrackingInstance':projectTrackingInstance, 'projectTrackingInstanceList':projectTrackingInstanceList]
    }

    def save = {
		params.createdBy = "admin"
    	params.createdDate = new Date()
		params.modifiedBy = "admin"
    	params.modifiedDate = new Date()
		
		def projectsService = new ProjectsService()
        def projectTrackingInstance = new ProjectTracking(params)
		projectTrackingInstance = projectsService.saveProjectTracking(projectTrackingInstance)
    	if(projectTrackingInstance.saveMode != null){
            flash.message = "Project Closure is created successfully"
            redirect(action:create,id:projectTrackingInstance.projects.id)
        }
        else {
            render(view:'create',id:projectTrackingInstance.projects.id)
        }
    }
}
