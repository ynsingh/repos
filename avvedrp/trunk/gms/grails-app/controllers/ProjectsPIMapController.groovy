import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import grails.util.GrailsUtil
class ProjectsPIMapController {
	def grantAllocationService
	def projectsService
	def investigatorService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

	/* =========================== 18-11-2010==========================*/
   def delete = {
			def projectsPIMapInstance = investigatorService.getProjectsPIMapById(params.id)
	        if(projectsPIMapInstance) 
	        {	        	
	        	projectsPIMapInstance = investigatorService.deletePIMap(projectsPIMapInstance)
	        	flash.message = "${message(code: 'default.deleted.label')}"
	            redirect(action:create)
	        }
	        else 
	        {
	            flash.message = "${message(code: 'default.notfond.label')}"
	            redirect(action:create)
	        }
	    }

   /*===============================================================*/
    def edit = {
        def projectsPIMapInstance = investigatorService.getProjectsPIMapById(params.id)
        def projectsInstance
        if(!projectsPIMapInstance) 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:create)
        }
        else 
        {
        	GrailsHttpSession gh=getSession()
            def investigatorService = new InvestigatorService()
            def investigatorList=[]
         	investigatorList=investigatorService.getInvestigatorsWithParty(gh.getValue("PartyID"))
         	projectsInstance = projectsService.getProjectById(projectsPIMapInstance.projects.id)
         	println"projectsPIMapInstance.projects.id"+projectsPIMapInstance.projects.id
         	println"projectsInstance"+projectsInstance
         	return [ projectsPIMapInstance : projectsPIMapInstance ,
                     'investigatorList':investigatorList,projectsInstance:projectsInstance]
        }
    }

    def update = {
		def projectsPIMapInstance = investigatorService.getProjectsPIMapById(params.id)
        def pIMapInstance = new ProjectsPIMap()
        GrailsHttpSession gh=getSession()
        if(projectsPIMapInstance) 
        {
            projectsPIMapInstance.properties = params
            def projectPIMapUpdateInstance = investigatorService.updatePIMap(projectsPIMapInstance)
            if(projectPIMapUpdateInstance) 
            {
               if(params.activeYesNo=='N')
            			{
            				projectsService.checkFordeleteProjectAccessPermissionOfPiMap(projectsPIMapInstance.projects.id,projectsPIMapInstance.investigator.id)
            			}
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action:create,id:projectsPIMapInstance.id)
            }
            else {
                render(view:'edit',model:[projectsPIMapInstance:projectsPIMapInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
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
    	def projectsPIMapInstanceList=dataSecurityService.getProjectsPIMapForLoginUser(projectsInstance.id);
	    return ['projectsPIMapInstance':projectsPIMapInstance,
                'projectsPIMapInstanceList': projectsPIMapInstanceList,
                'projectsInstance':projectsInstance,'investigatorList':investigatorList]
    }

    def save = {
		def projectsPIMapInstance = new ProjectsPIMap()
        GrailsHttpSession gh=getSession() 
        def investigatorInstance = investigatorService.getInvestigatorById(params.investigator.id)
        //def investigatorInstance = Investigator.get(new Integer(params.investigator.id))
        def PIprojectsInstance = projectsService.getProjectById(gh.getValue("ProjectId"))
        def pIMapInstance = projectsService.checkPIofProject(gh.getValue("ProjectId"))
        projectsPIMapInstance.investigator = investigatorInstance
        projectsPIMapInstance.projects = PIprojectsInstance
        projectsPIMapInstance.role = params.role
        projectsPIMapInstance.activeYesNo = "Y" //params.activeYesNo 18-11-2010
        def pIMapDuplicateInstance = projectsService.checkDuplicatePIofProject(PIprojectsInstance.id,investigatorInstance.id)
        def projectsInstance
        if(!pIMapDuplicateInstance)
        {
        if(params.role == 'PI')
        {
	        if(!pIMapInstance)
	        {
	        	def projectPIMapSaveInstance = investigatorService.savePIMap(projectsPIMapInstance)
	            if(projectPIMapSaveInstance) 
	            {
	            	projectsInstance = projectsService.saveProjectAccessPermissionForPiMap(gh.getValue("ProjectId"),projectsPIMapInstance.investigator.id)
		            flash.message = "${message(code: 'default.created.label')}"
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
	        	flash.message = pIMapInstance.investigator.name + "${message(code: 'default.alreadyAssignedPI.label')}"
	        }
        }
        else
        {
        	def projectPIMapSaveInstance = investigatorService.savePIMap(projectsPIMapInstance)
            if(projectPIMapSaveInstance) 
            {
	        	projectsInstance = projectsService.saveProjectAccessPermissionForPiMap(gh.getValue("ProjectId"),projectsPIMapInstance.investigator.id)
	            flash.message = "${message(code: 'default.created.label')}"
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
        	flash.message = investigatorInstance.name + "${message(code: 'default.alreadyAssignedPI.label')}"
        }
    }
   
}