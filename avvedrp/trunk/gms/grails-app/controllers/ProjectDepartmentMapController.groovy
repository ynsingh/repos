import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class ProjectDepartmentMapController {
	def grantAllocationService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']
	def delete = {
			def projectDepartmentMapService = new ProjectDepartmentMapService()
			def projectDepartmentMapInstance = projectDepartmentMapService.getProjectDepartmentMapById(params.id)
			if(projectDepartmentMapInstance) 
			{
				projectDepartmentMapInstance = projectDepartmentMapService.deleteDepartmentMap(projectDepartmentMapInstance)
	        	flash.message = "${message(code: 'default.deleted.label')}"
	            redirect(action:create)
	        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:create)
        }
    }

    def edit = {
			def projectDepartmentMapService = new ProjectDepartmentMapService()
			def projectDepartmentMapInstance = projectDepartmentMapService.getProjectDepartmentMapById(params.id)
			if(!projectDepartmentMapInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else 
        {
        	GrailsHttpSession gh=getSession()
            def dataSecurityService = new DataSecurityService()
            List<GrantAllocation> grantAllocationInstance 	
    		try{
        		grantAllocationInstance = grantAllocationService.getGrantAllocationGroupByProjects(gh.getValue("Party"))
    	
        	}
        	catch(Exception e)
        	{
        		
        	}
        	def projectsList=[]
    		for(int i=0;i<grantAllocationInstance.size();i++)
    		{
    			projectsList.add(grantAllocationInstance[i].projects)
    			
    		}
            def partyDepartmentList = projectDepartmentMapService.getPartyDepartmentForUser(gh.getValue("PartyID"));

            return [ projectDepartmentMapInstance : projectDepartmentMapInstance ,
                     'projectsList':projectsList,'partyDepartmentList':partyDepartmentList]
        }
    }

    def update = {
			def projectDepartmentMapService = new ProjectDepartmentMapService()
			def projectDepartmentMapInstance = projectDepartmentMapService.getProjectDepartmentMapById(params.id)
			if(projectDepartmentMapInstance) 
			{
	            projectDepartmentMapInstance.properties = params
	            def projectDepartmentMapUpdateInstance = projectDepartmentMapService.updateDepartmentMap(projectDepartmentMapInstance)
	            if(projectDepartmentMapUpdateInstance) 
	            {
	                flash.message ="${message(code: 'default.updated.label')}"
	                redirect(action:create,id:projectDepartmentMapInstance.id)
	            }
	            else {
	                render(view:'edit',model:[projectDepartmentMapInstance:projectDepartmentMapInstance])
	            }
	        }
	        else {
	            flash.message = "${message(code: 'default.notfond.label')}"
	            redirect(action:edit,id:params.id)
	        }
    }

    def create = {
        def projectDepartmentMapInstance = new ProjectDepartmentMap()
        projectDepartmentMapInstance.properties = params
       	GrailsHttpSession gh=getSession()
       	//Adding help page into session
       	gh.putValue("Help","Project_Dept_Map.htm")
        def dataSecurityService = new DataSecurityService()
        def projectDepartmentMapService = new ProjectDepartmentMapService()
    	def projectsService = new ProjectsService()
		def projectsInstance = projectsService.getProjectById(gh.getValue("ProjectId"))
    	def partyDepartmentList = projectDepartmentMapService.getPartyDepartmentForUser(gh.getValue("PartyID"));
    	def projectDepartmentMapInstanceList = projectDepartmentMapService.getProjectDepartmentMapList(gh.getValue("Party"))
    	return ['projectDepartmentMapInstance':projectDepartmentMapInstance,
                'projectsInstance':projectsInstance,'partyDepartmentList':partyDepartmentList,
                'projectDepartmentMapInstanceList':projectDepartmentMapInstanceList]
    }

    def save = 
    {
		def projectsService = new ProjectsService()
		GrailsHttpSession gh=getSession() 
		def projectDepartmentMapService = new ProjectDepartmentMapService()
    	def projectDepartmentMapInstance = new ProjectDepartmentMap(params)
        def departmentInstance = PartyDepartment.get(new Integer(params.partyDepartment.id))
        def projectsInstance = projectsService.getProjectById(gh.getValue("ProjectId"))
        projectDepartmentMapInstance.projects = projectsInstance
        def projectDepartmentMapDuplicateInstance = projectDepartmentMapService.chkDuplicatePDMap(projectDepartmentMapInstance)
        if(projectDepartmentMapDuplicateInstance)
        {
        	flash.message = "The department is already mapped to same project"
        		redirect(action:create,model:[id:projectDepartmentMapInstance.id,projectsInstance:projectsInstance])
        }
        else
        {
        	def projectDepartmentMapSaveInstance = projectDepartmentMapService.saveDepartmentMap(projectDepartmentMapInstance)
        	if(projectDepartmentMapSaveInstance) 
        	{
            flash.message = "${message(code: 'default.created.label')}"
            redirect(action:create,model:[id:projectDepartmentMapInstance.id,projectsInstance:projectsInstance])
        	}
        	else 
        	{
            render(view:'create',model:[projectDepartmentMapInstance:projectDepartmentMapInstance,projectsInstance:projectsInstance])
        	}
        }
    }
}
