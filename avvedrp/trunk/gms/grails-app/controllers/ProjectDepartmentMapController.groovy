import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class ProjectDepartmentMapController {
	def grantAllocationService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
    	GrailsHttpSession gh = getSession()
    	
    	def projectDepartmentMapService=new ProjectDepartmentMapService()
    	def projectDepartmentMapInstanceList = projectDepartmentMapService.getProjectDepartmentMapList(gh.getValue("Party"))
        
        [ projectDepartmentMapInstanceList: projectDepartmentMapInstanceList ]
    }

    def show = {
        def projectDepartmentMapInstance = ProjectDepartmentMap.get( params.id )

        if(!projectDepartmentMapInstance) {
            flash.message = "ProjectDepartmentMap not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ projectDepartmentMapInstance : projectDepartmentMapInstance ] }
    }

    def delete = {
        def projectDepartmentMapInstance = ProjectDepartmentMap.get( params.id )
        if(projectDepartmentMapInstance) {
            projectDepartmentMapInstance.delete()
            flash.message = "ProjectDepartmentMap  deleted"
            redirect(action:create)
        }
        else {
            flash.message = "ProjectDepartmentMap not found with id ${params.id}"
            redirect(action:create)
        }
    }

    def edit = {
        def projectDepartmentMapInstance = ProjectDepartmentMap.get( params.id )

        if(!projectDepartmentMapInstance) {
            flash.message = "ProjectDepartmentMap not found with id ${params.id}"
            redirect(action:list)
        }
        else 
        {
        	GrailsHttpSession gh=getSession()
            def dataSecurityService = new DataSecurityService()
            def projectDepartmentMapService = new ProjectDepartmentMapService()
        	
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
    			println projectsList
    		}
            def partyDepartmentList = projectDepartmentMapService.getPartyDepartmentForUser(gh.getValue("PartyID"));

            return [ projectDepartmentMapInstance : projectDepartmentMapInstance ,
                     'projectsList':projectsList,'partyDepartmentList':partyDepartmentList]
        }
    }

    def update = {
        def projectDepartmentMapInstance = ProjectDepartmentMap.get( params.id )
        if(projectDepartmentMapInstance) {
            projectDepartmentMapInstance.properties = params
            if(!projectDepartmentMapInstance.hasErrors() && projectDepartmentMapInstance.save()) {
                flash.message = "ProjectDepartmentMap is updated"
                redirect(action:create,id:projectDepartmentMapInstance.id)
            }
            else {
                render(view:'edit',model:[projectDepartmentMapInstance:projectDepartmentMapInstance])
            }
        }
        else {
            flash.message = "ProjectDepartmentMap not found with id ${params.id}"
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
    	println "===== PartyID====== " +gh.getValue("PartyID")
    	def projectsService = new ProjectsService()
		def projectsInstance = projectsService.getProjectById(gh.getValue("ProjectId"))
    	
        def partyDepartmentList = projectDepartmentMapService.getPartyDepartmentForUser(gh.getValue("PartyID"));
    	def projectDepartmentMapInstanceList = projectDepartmentMapService.getProjectDepartmentMapList(gh.getValue("Party"))

        return ['projectDepartmentMapInstance':projectDepartmentMapInstance,
                'projectsInstance':projectsInstance,'partyDepartmentList':partyDepartmentList,
                'projectDepartmentMapInstanceList':projectDepartmentMapInstanceList]
    }

    def save = {
		println "params" +params
		def projectsService = new ProjectsService()
		GrailsHttpSession gh=getSession() 
        def projectDepartmentMapInstance = new ProjectDepartmentMap(params)
        def departmentInstance = PartyDepartment.get(new Integer(params.partyDepartment.id))
        def projectsInstance = projectsService.getProjectById(gh.getValue("ProjectId"))
        projectDepartmentMapInstance.projects = projectsInstance
        if(!projectDepartmentMapInstance.hasErrors() && projectDepartmentMapInstance.save()) {
            flash.message = "ProjectDepartmentMap created for ${projectDepartmentMapInstance.projects.code}"
            redirect(action:create,model:[id:projectDepartmentMapInstance.id,projectsInstance:projectsInstance])
        }
        else {
            render(view:'create',model:[projectDepartmentMapInstance:projectDepartmentMapInstance,projectsInstance:projectsInstance])
        }
    }
}
