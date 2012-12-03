import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import grails.util.GrailsUtil

class ChecklistController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }
	def userService
	def checkListService
	def attachmentsService
    def create = {
   	    GrailsHttpSession gh=getSession()
   	    gh.removeValue("Help")
		gh.putValue("Help","checklist.htm")//putting help pages in session
        def checklistInstance = new Checklist()
        checklistInstance.properties = params
        def partyInstance = Party.get(gh.getValue("Party"))
        def checkListList = checkListService.getAllCheckListByParty(partyInstance.id)
        return [checklistInstance: checklistInstance,checkListList:checkListList]
    }

    def save = {
    	GrailsHttpSession gh=getSession()
        def checklistInstance = new Checklist(params)
        def userInstance = userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
        def partyInstance = Party.get(gh.getValue("Party"))
        def checkCheckList = Checklist.find("from Checklist cl where cl.activeYesNo='Y' and cl.field='"+params.field+"' and cl.party.id="+partyInstance.id)
        if(checkCheckList)
        {
           flash.message ="${message(code: 'default.AlreadyExists.label')}"
	       redirect(action: "create", id: checklistInstance.id)
        }
        else
        {
        	checklistInstance.activeYesNo = 'Y'
	        checklistInstance.party = partyInstance
	        checklistInstance.createdBy=userInstance.username
			checklistInstance.createdDate = new Date()
			if (checklistInstance.save(flush: true)) {
	            flash.message = "${message(code: 'default.created.label', args: [message(code: 'checklist.label', default: 'Checklist'), checklistInstance.id])}"
	            redirect(action: "create", id: checklistInstance.id)
	        }
	        else {
	            render(view: "create", model: [checklistInstance: checklistInstance])
	        }
	    }
    }

   

    def edit = {
        def checklistInstance = Checklist.get(params.id)
        if (!checklistInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'checklist.label', default: 'Checklist'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [checklistInstance: checklistInstance]
        }
    }

    def update = {
        GrailsHttpSession gh=getSession()
        def checklistInstance = Checklist.get(params.id)
        def partyInstance = Party.get(gh.getValue("Party"))
        def userInstance = userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
        if (checklistInstance) 
        {
            if (params.version) 
            {
                def version = params.version.toLong()
                if (checklistInstance.version > version)
                 {
                    checklistInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'checklist.label', default: 'Checklist')] as Object[], "Another user has updated this Checklist while you were editing")
                    render(view: "edit", model: [checklistInstance: checklistInstance])
                    return
                }
            }
            def checkCheckList = Checklist.find("from Checklist cl where cl.activeYesNo='Y' and cl.field='"+params.field+"' and cl.party.id="+partyInstance.id)
            if(checkCheckList && (checkCheckList.id!= Long.parseLong(params.id)))
           {
               flash.message ="${message(code: 'default.AlreadyExists.label')}"
        	   redirect(action: "edit", id: checklistInstance.id)
           }
            else
            {
                checklistInstance.modifiedBy=userInstance.username
				checklistInstance.modifiedDate = new Date()
	            checklistInstance.properties = params
	            if (!checklistInstance.hasErrors() && checklistInstance.save(flush: true)) {
	                flash.message = "${message(code: 'default.updated.label', args: [message(code: 'checklist.label', default: 'Checklist'), checklistInstance.id])}"
	                redirect(action: "create", id: checklistInstance.id)
	            }
	            else {
	                render(view: "create", model: [checklistInstance: checklistInstance])
	            }
	        }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'checklist.label', default: 'Checklist'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def checklistInstance = Checklist.get(params.id)
        if (checklistInstance) 
        {
            try 
            {
	            def checklistMapInstance = ChecklistMap.get(checklistInstance.id)
	            println"------------checklistMapInstance------"+checklistMapInstance
	            if(checklistMapInstance)
	            {
	             flash.message ="${message(code: 'default.Alreadymappedsocantdelete.label')}"
	            redirect(action: "create")
	            }
	            else
	            {
	                checklistInstance.activeYesNo = 'N'
	                checklistInstance.save(flush: true)
	                flash.message = "${message(code: 'default.deleted.label', args: [message(code: 'checklist.label', default: 'Checklist'), params.id])}"
	                redirect(action: "create")
	            }
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'checklist.label', default: 'Checklist'), params.id])}"
                redirect(action: "create", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'checklist.label', default: 'Checklist'), params.id])}"
            redirect(action: "create")
        }
    }
    
    def checklistmap = 
    {
    	 GrailsHttpSession gh=getSession()
   	    gh.removeValue("Help")
		gh.putValue("Help","checklistmap.htm")//putting help pages in session
    	def chklist
    	def checkList=[]
    	def checklistMapInstance = new ChecklistMap()
    	def partyInstance = Party.get(gh.getValue("Party"))
    	def grantAllocationInstance = GrantAllocation.findAll("from GrantAllocation GA where GA.granter.id="+partyInstance.id+" and GA.id NOT IN (SELECT EF.grantAllocation.id from ExternalFundAllocation EF)")
    	def attachmentTypeList = attachmentsService.getattachmentTypesByDocType('Project')
    	def checkListList = checkListService.getAllCheckListByParty(partyInstance.id)
    	for(int j=0;j<checkListList.size();j++)
		{
    		chklist = checkListList[j]
    		checkList.add(chklist)
		}
    	checklistMapInstance.properties = params
    	return [checklistMapInstance:checklistMapInstance,grantAllocationInstance:grantAllocationInstance.projects,checkList:checkList,attachmentTypeList:attachmentTypeList]
    }
    
    def getChecklistName =
    {
    	if(params.projects)
		{
			GrailsHttpSession gh=getSession()
	    	def chklist
	    	def checkList=[]
	    	def partyInstance = Party.get(gh.getValue("Party"))
	    	def checkListList = checkListService.getAllCheckListByPartyandProject(partyInstance.id,params.projects)
	    	for(int j=0;j<checkListList.size();j++)
			{
	    		chklist = checkListList[j]
	    		checkList.add(chklist)
			}
			def mapcheckList = ChecklistMap.findAll("from ChecklistMap CM where  CM.projects.id="+params.projects+" and CM.activeYesNo='Y'")
			render (template:"checkNameSel" , model: ['checkList':checkList,'mapcheckList':mapcheckList])
    	}
    }
    
    def add =
    {
    println"--------------"+params
    	GrailsHttpSession gh=getSession()
		def ctx = AH.application.mainContext
 		def springSecurityService=ctx.springSecurityService
    	def projectsInstance = Projects.get(params.projects.id)
    	def mapList = params.chkselt.toString()
    	def mapPrjtList = []
    	def chkMapList=mapList.split(',')
    	if (chkMapList.length ==1) 
    	{
    		mapPrjtList.add(params.chkselt.toString())
			params.chkselt =  mapPrjtList
		}
		
		for(int i=0;i<params.chkselt.size();i++)
		{
			def checklistMapInstance = new ChecklistMap()
			def checklistInstance = Checklist.get(params.chkselt[i])
			def userInstance = userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
			checklistMapInstance.createdBy=userInstance.username
			checklistMapInstance.createdDate = new Date()
			checklistMapInstance.activeYesNo = 'Y'
			checklistMapInstance.projects = projectsInstance
			checklistMapInstance.checklist = checklistInstance
			checklistMapInstance.compulsory = params.compulsory
			checklistMapInstance.satisfied =""
			checklistMapInstance.remarks = ""
			checklistMapInstance.properties = params
			checklistMapInstance.save()
	    }
	    redirect(action: "getChecklistName",params:[projects:params.projects.id])
	}
	
	 def remove =
    {
    	GrailsHttpSession gh=getSession()
		def ctx = AH.application.mainContext
 		def springSecurityService=ctx.springSecurityService
    	def projectsInstance = Projects.get(params.projects.id)
    	def mapList = params.chkdisselt.toString()
    	def mapPrjtList = []
    	def chkMapList=mapList.split(',')
    	if (chkMapList.length ==1) 
    	{
    		mapPrjtList.add(params.chkdisselt.toString())
			params.chkdisselt =  mapPrjtList
		}
		for(int i=0;i<params.chkdisselt.size();i++)
		{
			def checklistMapInstance = ChecklistMap.get(params.chkdisselt[i])
			def checklistInstance = Checklist.get(checklistMapInstance.checklist.id)
			def userInstance = userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
			checklistMapInstance.modifiedBy=userInstance.username
			checklistMapInstance.modifiedDate = new Date()
			checklistMapInstance.activeYesNo = 'N'
			checklistMapInstance.projects = projectsInstance
			checklistMapInstance.checklist = checklistInstance
			checklistMapInstance.compulsory = params.compulsory
			checklistMapInstance.satisfied = ""
			checklistMapInstance.remarks =""
			checklistMapInstance.properties = params
			checklistMapInstance.save()
	    }
	    redirect(action: "getChecklistName",params:[projects:params.projects.id])
	}
}
