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
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ projectTrackingInstance : projectTrackingInstance ] }
    }

    def delete = {
        def projectsService = new ProjectsService()
        Integer projectId = projectsService.deleteProjectTracking(new Integer(params.id))
        if(projectId != null){
            flash.message = "${message(code: 'default.deleted.label')}"
        	redirect(action:create,id:projectId)
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
    }

    def edit = {
		def projectsService = new ProjectsService()
		def projectTrackingInstance = projectsService.getProjectTrackingById(new Integer( params.id ))

        if(!projectTrackingInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else {
        	/* Get project details */
        	Integer projectId = projectTrackingInstance.projects.id
    		def projectsInstance = projectsService.getProjectById(projectId)
    		/* Get project tracking details if any */
    		def projectTrackingInstanceList = projectsService.getProjectTrackingByProject(projectsInstance) 
    		for(projectStatus in projectTrackingInstanceList.projectStatus)
    		{
    			if(projectStatus=='Closed')
    			{
    				projectsInstance.status='Closed'
    			}
    		}
            
    		return [ projectsInstance:projectsInstance, projectTrackingInstance : projectTrackingInstance, projectTrackingInstanceList:projectTrackingInstanceList ]
        }
    }

    def update = {
		def projectsService = new ProjectsService()
		def projectTrackingInstance = projectsService.updateProjectTracking(params)
		
		if(projectTrackingInstance){
			if(projectTrackingInstance.saveMode != null){
				flash.message = "${message(code: 'default.updated.label')}"
				redirect(action:create,id:projectTrackingInstance.projects.id)
			}
			else {
                render(view:'create',id:projectTrackingInstance.projects.id)
            }
		}
		else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:create,id:params.projects.id)
        }
    }

    def create = {
    		def projectsService = new ProjectsService()
    		/* Get project details */
    		def projectsInstance = projectsService.getProjectById(new Integer(params.id))
    		
    		/* Get project tracking details if any */
    		def projectTrackingInstanceList = projectsService.getProjectTrackingByProject(projectsInstance) 
        		
    		for(projectStatus in projectTrackingInstanceList.projectStatus)
        		{
        			if(projectStatus=='Closed')
        			{
        				projectsInstance.status='Closed'
        			}
        		}
            def projectTrackingInstance = new ProjectTracking()
    		projectTrackingInstance.projects = projectsInstance
            projectTrackingInstance.properties = params
            return ['projectsInstance':projectsInstance, 
                    'projectTrackingInstance':projectTrackingInstance, 
                    'projectTrackingInstanceList':projectTrackingInstanceList]
        }

        def save = {
    		println"params"+params.projects.id
    		params.createdBy = "admin"
        	params.createdDate = new Date()
    		params.modifiedBy = "admin"
        	params.modifiedDate = new Date()
    		
    		def subProjectInstanceList = []
    		def subProjectTrackingList = []
    		
    		
    		    def projectsService = new ProjectsService()
    		     /* Get project details */
    			def projectsInstance = projectsService.getProjectById(new Integer(params.projects.id))
    			
    			println"projectsInstance"+projectsInstance
    if(params.projectStatus =='Closed')
    	{
    		    
    		/* Get sub project details */
    		 subProjectInstanceList = projectsService.getSubProjectsForClosingMainProject(params.projects.id)
    		 println"subProjectInstanceList"+subProjectInstanceList	
    		 def subProjectTrackingInstance 
    		 def projectTrackingInstance = new ProjectTracking()
    		    	 
 		    	 for(int i=0;i<subProjectInstanceList.size();i++)
    		    	 {
    		    		 
 		    		         println"subProjectInstanceList[i]"+subProjectInstanceList[i].id
					 subProjectTrackingInstance = new ProjectTracking()
					 subProjectTrackingInstance =  ProjectTracking.find("from ProjectTracking PT where PT.projects.id = "+subProjectInstanceList[i].id)	
					 println"subProjectTrackingInstance"+subProjectTrackingInstance
					   if(subProjectTrackingInstance)
					   subProjectTrackingList.add(subProjectTrackingInstance)
			  
    		    	 }
			   
			    if(subProjectTrackingList.size()== subProjectInstanceList.size())
			    {
			    
			    	projectsInstance.status='Closed'
			        projectTrackingInstance.projects = projectsInstance
			        projectTrackingInstance.properties = params
			        projectTrackingInstance.save()
			        flash.message = "${message(code: 'default.created.label')}"
		            redirect(action:create,params:[id:projectTrackingInstance.projects.id,projectStatus:projectTrackingInstance.projectStatus])

			        
			    }
			    else
			    {
		            flash.message = "${message(code: 'default.notAllowed.label')}"
			        redirect(action:create,id:params.projects.id)
			    }
		      }
          else
    		{
        	  def projectTrackingInstance = new ProjectTracking(params)
        	  projectTrackingInstance = projectsService.saveProjectTracking(projectTrackingInstance)
        	  if(projectTrackingInstance.saveMode != null)
        	  {
                flash.message = "${message(code: 'default.created.label')}"
                redirect(action:create,params:[id:projectTrackingInstance.projects.id,projectStatus:projectTrackingInstance.projectStatus])
              }
             else
                render(view:'create',id:projectTrackingInstance.projects.id)
               
            } 
    }
}
