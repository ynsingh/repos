import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import grails.util.GrailsUtil
class ProjectsPIMapController {
	def grantAllocationService
	def projectsService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ projectsPIMapInstanceList: ProjectsPIMap.list( params ) ]
    }

    def show = {
        def projectsPIMapInstance = ProjectsPIMap.get( params.id )

        if(!projectsPIMapInstance) {
            flash.message = "ProjectsPIMap not found with id ${params.id}"
            redirect(action:create)
        }
        else { return [ projectsPIMapInstance : projectsPIMapInstance ] }
    }

    def delete = {
        def projectsPIMapInstance = ProjectsPIMap.get( params.id )
        if(projectsPIMapInstance) {
        	def projectsInstance = projectsService.checkFordeleteProjectAccessPermissionOfPiMap(params.projects.id,params.investigator.id)
            projectsPIMapInstance.delete()
            flash.message = "Deleted Successfully"
            redirect(action:create)
        }
        else {
            flash.message = "ProjectsPIMap not found with id ${params.id}"
            redirect(action:create)
        }
    }

    def edit = {
        def projectsPIMapInstance = ProjectsPIMap.get( params.id )
        
        if(!projectsPIMapInstance) 
        {
            flash.message = "ProjectsPIMap not found with id ${params.id}"
            redirect(action:create)
        }
        else 
        {
        	GrailsHttpSession gh=getSession()
            def investigatorService = new InvestigatorService()
            def investigatorList=[]
         	investigatorList=investigatorService.getInvestigatorsWithParty(gh.getValue("PartyID"))
         	println"investigatorList"+investigatorList
         	
            return [ projectsPIMapInstance : projectsPIMapInstance ,
                     'investigatorList':investigatorList]
        }
    }

    def update = {
        def projectsPIMapInstance = ProjectsPIMap.get( params.id )
        def pIMapInstance = new ProjectsPIMap()
       
        GrailsHttpSession gh=getSession()
        if(projectsPIMapInstance) 
        {
            projectsPIMapInstance.properties = params
            if(!projectsPIMapInstance.hasErrors() && projectsPIMapInstance.save()) {
                if(params.activeYesNo=='N')
            			{
            				projectsService.checkFordeleteProjectAccessPermissionOfPiMap(projectsPIMapInstance.projects.id,projectsPIMapInstance.investigator.id)
            			}
                flash.message = "Updated Successfully"
                redirect(action:create,id:projectsPIMapInstance.id)
            }
            else {
                render(view:'edit',model:[projectsPIMapInstance:projectsPIMapInstance])
            }
        }
        else {
            flash.message = "ProjectsPIMap not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = 
    {
		def projectsPIMapInstance = new ProjectsPIMap()
        projectsPIMapInstance.properties = params
        
        GrailsHttpSession gh=getSession()
        
        /*Removing the old help session and putting the current session help file*/
        gh.removeValue("Help")
        gh.putValue("Help","Assign_Projects_to_PI.htm")
        
        def dataSecurityService = new DataSecurityService()
		def projectsService = new ProjectsService()
		def projectsInstance = projectsService.getProjectById(gh.getValue("ProjectId"))
       
		def investigatorService = new InvestigatorService()
        def investigatorList=[]
    	investigatorList=investigatorService.getInvestigatorsWithParty(gh.getValue("PartyID"))
    	println"investigatorList"+investigatorList
    	
        def projectsPIMapInstanceList=dataSecurityService.getProjectsPIMapForLoginUser(projectsInstance.id);
	    
        return ['projectsPIMapInstance':projectsPIMapInstance,
                'projectsPIMapInstanceList': projectsPIMapInstanceList,
                'projectsInstance':projectsInstance,'investigatorList':investigatorList]
    }

    def save = {
       println "params"+params
		def projectsPIMapInstance = new ProjectsPIMap()
       
        
        GrailsHttpSession gh=getSession() 
        def investigatorInstance = Investigator.get(new Integer(params.investigator.id))
        def PIprojectsInstance = projectsService.getProjectById(gh.getValue("ProjectId"))
        println "project"+gh.getValue("ProjectId")
        def pIMapInstance = projectsService.checkPIofProject(gh.getValue("ProjectId"))
        projectsPIMapInstance.investigator = investigatorInstance
        projectsPIMapInstance.projects = PIprojectsInstance
        projectsPIMapInstance.role = params.role
        projectsPIMapInstance.activeYesNo = params.activeYesNo
        println"projectsPIMapInstance"+projectsPIMapInstance
        def pIMapDuplicateInstance = projectsService.checkDuplicatePIofProject(PIprojectsInstance.id,investigatorInstance.id)
        println"projectsPIMapInstance"+pIMapDuplicateInstance
        def projectsInstance
        if(!pIMapDuplicateInstance)
        {
        if(params.role == 'PI')
        {
	        if(!pIMapInstance)
	        {
		        if(!projectsPIMapInstance.hasErrors() && projectsPIMapInstance.save())
		        {
		        	projectsInstance = projectsService.saveProjectAccessPermissionForPiMap(gh.getValue("ProjectId"),projectsPIMapInstance.investigator.id)
		            flash.message = "Created successfully"
		            redirect(action:create,model:[projectsPIMapInstance:projectsPIMapInstance,projectsInstance:PIprojectsInstance])
		        }
		        else
		        {
		            render(view:'create',model:[projectsPIMapInstance:projectsPIMapInstance,projectsInstance:PIprojectsInstance])
		        }
	        }
	        else
	        {
	        	redirect(action:create,model:[projectsPIMapInstance:projectsPIMapInstance,projectsInstance:PIprojectsInstance]) 
	        	flash.message = pIMapInstance.investigator.name + " is already assinged as PI for this project " 
	        }
        }
        else
        {
        	if(!projectsPIMapInstance.hasErrors() && projectsPIMapInstance.save())
	        {
	        	projectsInstance = projectsService.saveProjectAccessPermissionForPiMap(gh.getValue("ProjectId"),projectsPIMapInstance.investigator.id)
	            flash.message = "Created successfully"
	            redirect(action:create,model:[projectsPIMapInstance:projectsPIMapInstance,projectsInstance:PIprojectsInstance])
	        }
	        else
	        {
	            render(view:'create',model:[projectsPIMapInstance:projectsPIMapInstance,projectsInstance:PIprojectsInstance])
	        }
        }
        }
        else
        {
        	redirect(action:create,model:[projectsPIMapInstance:projectsPIMapInstance]) 
        	flash.message = investigatorInstance.name + " is already assinged as Investigator for this project " 
        }
    }
   
}