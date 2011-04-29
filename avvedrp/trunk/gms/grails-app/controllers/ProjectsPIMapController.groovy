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
			println"params----"+params
			def projectsPIMapInstance = investigatorService.getProjectsPIMapById(params.id)
			println"projectsPIMapInstance"+projectsPIMapInstance
	        if(projectsPIMapInstance) 
	        {	        	
	        	projectsPIMapInstance = investigatorService.deletePIMap(projectsPIMapInstance)
	        	flash.message = "${message(code: 'default.deleted.label')}"
	            redirect(action:create,id:projectsPIMapInstance.projects.id)
	        }
	        else 
	        {
	            flash.message = "${message(code: 'default.notfond.label')}"
	            redirect(action:create,params:[id:params.projectId])
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
         	return [ projectsPIMapInstance : projectsPIMapInstance ,
                     'investigatorList':investigatorList,projectsInstance:projectsInstance]
        }
    }

    def update = {
		def projectsPIMapInstance = investigatorService.getProjectsPIMapById(params.id)
        GrailsHttpSession gh=getSession()
        def pIMapInstance = projectsService.checkPIofProject(params.projectId)
        def investigatorInstance = investigatorService.getInvestigatorById(params.investigator.id)
        def PIprojectsInstance = Projects.get(gh.getValue("ProjectID"))
        def pIMapDuplicateInstance = projectsService.checkDuplicatePIofProject(PIprojectsInstance.id,investigatorInstance.id)
        if(pIMapInstance.investigator.id == new Integer(params.investigator.id))
        {
        	redirect(action:create,model:[projectsPIMapInstance:projectsPIMapInstance],params:[id:params.projectId]) 
        	flash.message = pIMapInstance.investigator.name + "${message(code: 'default.alreadyAssignedPI.label')}"
        }
        else
        {
        	if(pIMapDuplicateInstance && pIMapDuplicateInstance.id != new Integer(params.id))
            {
            	redirect(action:create,model:[projectsPIMapInstance:projectsPIMapInstance],params:[id:PIprojectsInstance.id]) 
            	flash.message = investigatorInstance.name +" "+ "${message(code: 'default.alreadyAssignedCoPI.label')}"
            }
            else
            {
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
                redirect(action:create,params:[id:params.projectId])
            }
            else {
                render(view:'edit',model:[projectsPIMapInstance:projectsPIMapInstance],params:[id:params.projectId])
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
        }
        }
        
        }
        
    }

    def create = 
    {
		def projectsPIMapInstance = new ProjectsPIMap()
        projectsPIMapInstance.properties = params
        println"params.id"+params
        GrailsHttpSession gh=getSession()
        /*Removing the old help session and putting the current session help file*/
        gh.removeValue("Help")
        gh.putValue("Help","Assign_Projects_to_PI.htm")
        def dataSecurityService = new DataSecurityService()
		def projectsService = new ProjectsService()
		def projectsInstance = projectsService.getProjectById(new Integer(params.id))
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
        def PIprojectsInstance = projectsService.getProjectById(new Integer(params.id))
       	def pIMapInstance = projectsService.checkPIofProject(params.id)
        projectsPIMapInstance.investigator = investigatorInstance
        projectsPIMapInstance.projects = PIprojectsInstance
        projectsPIMapInstance.role = params.role
        projectsPIMapInstance.activeYesNo = "Y" //params.activeYesNo 18-11-2010
        def pIMapDuplicateInstance = projectsService.checkDuplicatePIofProject(PIprojectsInstance.id,investigatorInstance.id)
        def projectsInstance
        
        if(params.role == 'PI')
        {
	        if(!pIMapInstance)
	        {
	        	def projectPIMapSaveInstance = investigatorService.savePIMap(projectsPIMapInstance)
	            if(projectPIMapSaveInstance) 
	            {
	            	projectsInstance = projectsService.saveProjectAccessPermissionForPiMap(gh.getValue("ProjectId"),projectsPIMapInstance.investigator.id)
		            flash.message = "${message(code: 'default.created.label')}"
		            redirect(action:create,model:[projectsPIMapInstance:projectsPIMapInstance,projectsInstance:PIprojectsInstance],params:[id:PIprojectsInstance.id])
		        }
		        else
		        {
		            render(view:'create',model:[projectsPIMapInstance:projectsPIMapInstance,projectsInstance:PIprojectsInstance],params:[id:PIprojectsInstance.id])
		        }
	        }
	        else
	        {
	        	redirect(action:create,model:[projectsPIMapInstance:projectsPIMapInstance,projectsInstance:PIprojectsInstance],params:[id:PIprojectsInstance.id]) 
	        	flash.message = pIMapInstance.investigator.name +" "+ "${message(code: 'default.alreadyAssignedPI.label')}"
	        }
        }
        else
        {
        	if(!pIMapDuplicateInstance)
            {
        	def projectPIMapSaveInstance = investigatorService.savePIMap(projectsPIMapInstance)
            if(projectPIMapSaveInstance) 
            {
            	projectsService.saveProjectAccessPermissionForPiMap(PIprojectsInstance.id,projectsPIMapInstance.investigator.id)
	            flash.message = "${message(code: 'default.created.label')}"
	            redirect(action:create,model:[projectsPIMapInstance:projectsPIMapInstance,projectsInstance:PIprojectsInstance],params:[id:PIprojectsInstance.id])
	        }
	        else
	        {
	            render(view:'create',model:[projectsPIMapInstance:projectsPIMapInstance,projectsInstance:PIprojectsInstance],params:[id:PIprojectsInstance.id])
	        }
        }
      
        else
        {
        	println"pIMapDuplicateInstance.role"+pIMapDuplicateInstance.role
        	redirect(action:create,model:[projectsPIMapInstance:projectsPIMapInstance],params:[id:PIprojectsInstance.id]) 
        	if(pIMapDuplicateInstance.role == 'PI')
        	{
        		flash.message = pIMapInstance.investigator.name +" "+ "${message(code: 'default.alreadyAssignedPI.label')}"
        	}
        	else
        	{
        	flash.message = investigatorInstance.name +" "+ "${message(code: 'default.alreadyAssignedCoPI.label')}"
        	}
        }
       }
    }
   
}