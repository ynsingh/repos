import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.util.Date
import grails.util.GrailsUtil
import java.text.SimpleDateFormat
class BudgetModuleMapController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }
    def projectsService
    def grantAllocationService 
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [budgetModuleMapInstanceList: BudgetModuleMap.list(params), budgetModuleMapInstanceTotal: BudgetModuleMap.count()]
    }

    def create = {
    	GrailsHttpSession gh=getSession()
    	def budgetModMapInstance
    	def budgetModuleMapInstanceList=[]
    	def partyInstance = Party.get(gh.getValue("Party"))
    	def budgetModuleMapInstance = new BudgetModuleMap()
    	def budgetMasterService = new BudgetMasterService()
    	budgetModuleMapInstance.properties = params
    	def budgetModInstance
    	def budgetMasterInstanceList = budgetMasterService.getpartyId(partyInstance.id)
    	for(int i=0;i<budgetMasterInstanceList.size();i++ )
 	    {
 		   /* method to check All active budget Module Map*/
    	        budgetModMapInstance = budgetMasterService.getbudgetModMapInstance(budgetMasterInstanceList[i].id)
    	        for(int j=0;j<budgetModMapInstance.size();j++ )
    	 	    {
    	        	budgetModInstance = budgetModMapInstance[j]
                    budgetModuleMapInstanceList.add(budgetModInstance)
    	 	    }
   	 }
       return [budgetModuleMapInstance: budgetModuleMapInstance,budgetModuleMapInstanceList:budgetModuleMapInstanceList,budgetMasterInstanceList:budgetMasterInstanceList]
    }

    def save = 
    {
    	GrailsHttpSession gh=getSession()
    	def moduleTypeList
    	def fullProposalList=[]
    	def fullProposalInstance 
    	def budgetMasterService = new BudgetMasterService()
    	def partyInstance = Party.get(gh.getValue("Party"))
        def budgetModuleMapInstance = new BudgetModuleMap(params)
    	def budgetMasterInstance =BudgetMaster.get(params.budgetMaster.id)
    	/*if(params.moduleType=="Proposal")
    	    {
	    		/* method to get proposalcode
	    		def proposalInstance = budgetMasterService.proposalDetails(params)
	    		budgetModuleMapInstance.moduleTypeId = proposalInstance.id
            }*/
    	if(params.moduleType=="Notification")
    	{
    		/* method to get notificationTitle */
    		def notificationInstance = budgetMasterService.notificationDetails(params)
    		budgetModuleMapInstance.moduleTypeId = notificationInstance.id
        }
          
    	if(params.moduleType=="Projects")
    	{  
    	    
	        /* method to get Projectcode */
	        def projectInstance = budgetMasterService.projectDetails(params)
    		budgetModuleMapInstance.moduleTypeId = projectInstance.id
	    }
    	if(params.moduleType=="FullProposal")
    	{  
    		def proposalInstance = ProposalApplication.executeQuery("select proposal.id from ProposalApplication PA where PA.projectTitle='"+params.moduleTypeId+"'")
    		for(int i=0;i<proposalInstance.size();i++)
    		{
    			fullProposalInstance = Proposal.executeQuery("select id from Proposal P where P.id="+proposalInstance[i]+" and P.proposalType='FullProposal'")
    		}
    		budgetModuleMapInstance.moduleTypeId = fullProposalInstance[0]
    	}
    	def budgetMasterInDetails = budgetMasterService.getbudgetDetailsInstancebyBudgetMasterId(budgetMasterInstance)
		if(!budgetMasterInDetails)
		{
			flash.message ="${message(code: 'default.BudgetMasterIsNotAllocated.label')}"
	    		 redirect(action: "create",id: budgetModuleMapInstance.id)
		}
		else
		{
	    	 def checkDuplicateModMap = budgetMasterService.checkDuplicateBudgetModMap(params,budgetModuleMapInstance)
	    	//def checkDuplicateModMap = BudgetModuleMap.findAll("from BudgetModuleMap BMM where BMM.budgetMaster='"+params.budgetMaster.id+"' and BMM.moduleType='"+params.moduleType+"' and BMM.moduleTypeId='"+budgetModuleMapInstance.moduleTypeId+"'")
	    	if(checkDuplicateModMap)
	    	{
	    		 flash.message ="${message(code: 'default.AlreadyExists.label')}"
	    		 redirect(action: "create",id: budgetModuleMapInstance.id)
	    	}
	    	else
	    	{
	    		
    	budgetModuleMapInstance.activeYesNo="Y" 
    	if(budgetModuleMapInstance.save())
        {
            flash.message = "${message(code: 'default.Budgetmodulemapcreated.message', args: [message(code: 'budgetModuleMap.label', default: 'BudgetModuleMap'), budgetModuleMapInstance.id])}"
            redirect(action: "create",id: budgetModuleMapInstance.id)
        }
    	}
    	}
    }

    def show = {
        def budgetModuleMapInstance = BudgetModuleMap.get(params.id)
        if (!budgetModuleMapInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'budgetModuleMap.label', default: 'BudgetModuleMap'), params.id])}"
            redirect(action: "list")
        }
        else {
            [budgetModuleMapInstance: budgetModuleMapInstance]
        }
    }

    def edit = {
    	GrailsHttpSession gh=getSession()
    	def budgetMasterService = new BudgetMasterService()
      	def proposalInstance
      	def proposalApplicationInstance
      	def projectsList=[]
    	def moduleTypeInst
      	def projectsInstance
      	def budgetMasterInstanceList
      	def moduleTypeList=[]
    	def moduleTypeInstance=[]
      	def proposalCodeInstance=[]
      	def projectCodeInstance=[]
      	def notificationTitleInstance=[]
    	def ProposalApplicationInstance=[]
        def budgetModuleMapInstance = BudgetModuleMap.get(params.id)
        def partyInstance = Party.get(gh.getValue("Party"))
       
        /*if(budgetModuleMapInstance.moduleType=="Proposal")
    	    {
	        	 budgetMasterInstanceList = budgetMasterService.getpartyId(partyInstance.id)
	             /* method to get Proposal Id By budgetModuleMapInstance 
	        	 proposalInstance = budgetMasterService.getproposalIdbyBudgetModuleMapInstance(budgetModuleMapInstance.moduleTypeId)
	        	 /*Method to get code Of  Proposal
	        	 moduleTypeList =  budgetMasterService.getproposalList(partyInstance)
	        	 return [budgetModuleMapInstance: budgetModuleMapInstance,moduleTypeList:moduleTypeList,proposalCodeInstance: proposalInstance,moduleType:budgetModuleMapInstance.moduleType,budgetMasterInstanceList:budgetMasterInstanceList]
    	    } */
        if(budgetModuleMapInstance.moduleType=="Projects")
    	{
    		 budgetMasterInstanceList = budgetMasterService.getpartyId(partyInstance.id)
    		 def projectInstance = budgetMasterService.getprojectIdbyBudgetModuleMapInstance(budgetModuleMapInstance.moduleTypeId)
    		 def grantAllocationWithprojectsInstanceList = grantAllocationService.getGrantAllocationGroupByProjects(gh.getValue("Party"))//get the grant allocation of projects have access permission for login PI
		for(int i=0;i<grantAllocationWithprojectsInstanceList.size();i++)
        {
			projectsInstance = projectsService
							.getActiveProjectById(grantAllocationWithprojectsInstanceList[i].projects.id)
			projectsList.add(projectsInstance)
        }
    	for(int i=0;i<projectsList.size();i++)
    	{
    		moduleTypeInst = Projects.executeQuery("select code from Projects P where P.id="+projectsList[i].id+" and P.id NOT IN (select projects.id from ProjectTracking PT where PT.projectStatus='Closed')")
    		if(moduleTypeInst)
    		moduleTypeList.add(moduleTypeInst[0])
    	}
    			//moduleTypeList = budgetMasterService.getprojectsList(partyInstance)
    	
    		 //moduleTypeList = budgetMasterService.getprojectsList(partyInstance)
    		 return [budgetModuleMapInstance: budgetModuleMapInstance,moduleTypeList:moduleTypeList,projectCodeInstance: projectInstance,moduleType:budgetModuleMapInstance.moduleType,budgetMasterInstanceList:budgetMasterInstanceList]
      	}
    	if(budgetModuleMapInstance.moduleType=="FullProposal")
    	{  
    		 budgetMasterInstanceList = budgetMasterService.getpartyId(partyInstance.id)
    		 /* method to get Fullproposal Id By budgetModuleMapInstance */
    		 proposalInstance = budgetMasterService.getfullProposalIdbyBudgetModuleMapInstance(budgetModuleMapInstance.moduleTypeId)
    		 /*Method to get saved FullProposal*/
    		 def proposalList = budgetMasterService.getFullProposalList(partyInstance)
    		 for(int i=0;i<proposalList.size();i++)
    		 {	
    			budgetMasterInstanceList = budgetMasterService.getpartyId(partyInstance.id)
    		    /*Method to get project Title */
    		    proposalApplicationInstance = budgetMasterService.getprojectTitleList(proposalList[i].id)
    		    moduleTypeList.add(proposalApplicationInstance[0])
    		}
        	def proposalAppInstance = budgetMasterService.proposalApplicationInstanceByProposalId(proposalInstance)
    		return [budgetModuleMapInstance: budgetModuleMapInstance,moduleTypeList:moduleTypeList,moduleType:budgetModuleMapInstance.moduleType,proposalApplicationInstance:proposalAppInstance,budgetMasterInstanceList:budgetMasterInstanceList]
    	}
    	if(budgetModuleMapInstance.moduleType=="Notification")
    	{
    		   budgetMasterInstanceList = budgetMasterService.getpartyId(partyInstance.id)
    		   /* method to get Notification Id By budgetModuleMapInstance */
    		   def notificationInstance = budgetMasterService.getNotificationIdbyBudgetModuleMapInstance(budgetModuleMapInstance.moduleTypeId)
    		   /*Method to get NotificationTitle*/
    		   moduleTypeList = budgetMasterService.getNotificationList(partyInstance)
    		   return [budgetModuleMapInstance: budgetModuleMapInstance,notificationTitleInstance: notificationInstance,moduleType:budgetModuleMapInstance.moduleType,moduleTypeList:moduleTypeList,budgetMasterInstanceList:budgetMasterInstanceList]
    	}
   }

    def update = {
    	 GrailsHttpSession gh=getSession()
    	 def fullProposalInstance
    	 def budgetModuleMapInstance = BudgetModuleMap.get(params.id)
    	 def budgetMasterService = new BudgetMasterService()
    	 def partyInstance = Party.get(gh.getValue("Party"))
         if (budgetModuleMapInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (budgetModuleMapInstance.version > version) {
                    
                    budgetModuleMapInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'budgetModuleMap.label', default: 'BudgetModuleMap')] as Object[], "Another user has updated this BudgetModuleMap while you were editing")
                    render(view: "edit", model: [budgetModuleMapInstance: budgetModuleMapInstance])
                    return
                }
            }
           // if(params.moduleType=="Proposal")
        	//{
        		//def proposalInstance = budgetMasterService.proposalDetails(params)
        		//budgetModuleMapInstance.moduleTypeId = proposalInstance.id
        	//}
        	if(params.moduleType=="Notification")
        	{
        		def notificationInstance = budgetMasterService.notificationDetails(params)
        		 //def checkDuplicateModMap = budgetMasterService.checkDuplicateBudgetModMap(params,budgetModuleMapInstance)
            	def checkDuplicateModMap = BudgetModuleMap.findAll("from BudgetModuleMap BMM where BMM.budgetMaster='"+params.budgetMaster.id+"' and BMM.moduleType='"+params.moduleType+"' and BMM.moduleTypeId='"+notificationInstance.id+"'")
            	if(checkDuplicateModMap && (checkDuplicateModMap[0].id!= Long.parseLong(params.id)))
                {
            		 flash.message ="${message(code: 'default.AlreadyExists.label')}"
            		 redirect(action: "edit",id: budgetModuleMapInstance.id)
            	}
            	else
            	{
	            	def budgetMasterInstance = budgetMasterService.budgetMasterInstanceByBudgetMasterId(params)
	                budgetModuleMapInstance.budgetMaster = budgetMasterInstance
	                budgetModuleMapInstance.moduleType = params.moduleType
	                budgetModuleMapInstance.moduleTypeId = notificationInstance.id
	         	   if (budgetModuleMapInstance.save()) {
	                    flash.message = "${message(code: 'default.BudgetModuleMapupdated.message', args: [message(code: 'budgetModuleMap.label', default: 'BudgetModuleMap'), budgetModuleMapInstance.id])}"
	                    redirect(action: "create", id: budgetModuleMapInstance.id)
	                }
	                else {
	                    render(view: "edit", model: [budgetModuleMapInstance: budgetModuleMapInstance])
	                }
            	}
        	
            }
        	if(params.moduleType=="Projects")
        	{  
        	    
    	        def projectInstance = budgetMasterService.projectDetails(params)
    	       //def checkDuplicateModMap = budgetMasterService.checkDuplicateBudgetModMap(params,budgetModuleMapInstance)
            	def checkDuplicateModMap = BudgetModuleMap.findAll("from BudgetModuleMap BMM where BMM.budgetMaster='"+params.budgetMaster.id+"' and BMM.moduleType='"+params.moduleType+"' and BMM.moduleTypeId='"+projectInstance.id+"'")
            	if(checkDuplicateModMap && (checkDuplicateModMap[0].id!= Long.parseLong(params.id)))
                {
            		 flash.message ="${message(code: 'default.AlreadyExists.label')}"
            		 redirect(action: "edit",id: budgetModuleMapInstance.id)
            	}
            	else
            	{
	            	def budgetMasterInstance = budgetMasterService.budgetMasterInstanceByBudgetMasterId(params)
	                budgetModuleMapInstance.budgetMaster = budgetMasterInstance
	                budgetModuleMapInstance.moduleType = params.moduleType
	                budgetModuleMapInstance.moduleTypeId = projectInstance.id
	                if (budgetModuleMapInstance.save()) {
	                    flash.message = "${message(code: 'default.BudgetModuleMapupdated.message', args: [message(code: 'budgetModuleMap.label', default: 'BudgetModuleMap'), budgetModuleMapInstance.id])}"
	                    redirect(action: "create", id: budgetModuleMapInstance.id)
	                }
	                else {
	                    render(view: "edit", model: [budgetModuleMapInstance: budgetModuleMapInstance])
	                }
            	}
    	    }
        	if(params.moduleType=="FullProposal")
        	{  
        			def proposalList = budgetMasterService.getFullProposalList(partyInstance)
        			def proposalInstance = ProposalApplication.executeQuery("select proposal.id from ProposalApplication PA where PA.projectTitle='"+params.moduleTypeId+"'")
        			for(int i=0;i<proposalInstance.size();i++)
        			{
        				fullProposalInstance = Proposal.executeQuery("select id from Proposal P where P.id="+proposalInstance[i]+" and P.proposalType='FullProposal'")
        			}
        			//def checkDuplicateModMap = budgetMasterService.checkDuplicateBudgetModMap(params,budgetModuleMapInstance)
                	def checkDuplicateModMap = BudgetModuleMap.findAll("from BudgetModuleMap BMM where BMM.budgetMaster='"+params.budgetMaster.id+"' and BMM.moduleType='"+params.moduleType+"' and BMM.moduleTypeId='"+fullProposalInstance[0]+"'")
                	if(checkDuplicateModMap && (checkDuplicateModMap[0].id!= Long.parseLong(params.id)))
                    {
                		 flash.message ="${message(code: 'default.AlreadyExists.label')}"
                		 redirect(action: "edit",id: budgetModuleMapInstance.id)
                	}
                	else
                	{
	                	def budgetMasterInstance = budgetMasterService.budgetMasterInstanceByBudgetMasterId(params)
	                    budgetModuleMapInstance.budgetMaster = budgetMasterInstance
	                    budgetModuleMapInstance.moduleType = params.moduleType
	                    budgetModuleMapInstance.moduleTypeId = fullProposalInstance[0]
	                    if (budgetModuleMapInstance.save()) {
	                        flash.message = "${message(code: 'default.BudgetModuleMapupdated.message', args: [message(code: 'budgetModuleMap.label', default: 'BudgetModuleMap'), budgetModuleMapInstance.id])}"
	                        redirect(action: "create", id: budgetModuleMapInstance.id)
	                    }
	                    else {
	                        render(view: "edit", model: [budgetModuleMapInstance: budgetModuleMapInstance])
	                    }
	                }
        	}
        	
        	
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'budgetModuleMap.label', default: 'BudgetModuleMap'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def budgetModuleMapInstance = BudgetModuleMap.get(params.id)
        if (budgetModuleMapInstance) {
            try {
                budgetModuleMapInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'budgetModuleMap.label', default: 'BudgetModuleMap'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'budgetModuleMap.label', default: 'BudgetModuleMap'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'budgetModuleMap.label', default: 'BudgetModuleMap'), params.id])}"
            redirect(action: "list")
        }
    }
    
    
   def submissionType =
   {
    	GrailsHttpSession gh=getSession()
    	def budgetMasterService = new BudgetMasterService()
    	def moduleTypeList=[]
    	def projectList
    	def moduleTypeInst
    	def projectCode
    	def moduleType=[]
    	def projectsList=[]
    	def proposalApplicationInstance
    	def projectsInstance
    	def partyInstance = Party.get(gh.getValue("Party"))
       /*if(params.moduleType=="Proposal")
    	  { 
             moduleTypeList =  budgetMasterService.getproposalList(partyInstance)
        	 /*Method to get code Of  Proposal
             moduleTypeList = budgetMasterService.getproposalList()
    	     render (template:"proposalSelect", model : ['moduleTypeList' : moduleTypeList,'moduleType' : params.moduleType])
    	  }*/
    	if(params.moduleType=="Notification")
    	 {
     		/*Method to get NotificationTitle*/
    	     moduleTypeList = budgetMasterService.getNotificationList(partyInstance)
     	     render (template:"proposalSelect", model : ['moduleTypeList' : moduleTypeList,'moduleType' : params.moduleType])
		}
    	if(params.moduleType=="Projects")
    	{
   		 def grantAllocationWithprojectsInstanceList = grantAllocationService.getGrantAllocationGroupByProjects(gh.getValue("Party"))//get the grant allocation of projects have access permission for login PI
   		 for(int i=0;i<grantAllocationWithprojectsInstanceList.size();i++)
         {
 			projectsInstance = projectsService
 							.getActiveProjectById(grantAllocationWithprojectsInstanceList[i].projects.id)
 			projectsList.add(projectsInstance)
         }
   		 for(int i=0;i<projectsList.size();i++)
     	 {
     		moduleTypeInst = Projects.executeQuery("select code from Projects P where P.id="+projectsList[i].id+" and P.id NOT IN (select projects.id from ProjectTracking PT where PT.projectStatus='Closed')")
     		if(moduleTypeInst)
     		moduleTypeList.add(moduleTypeInst[0])
     	 }
   			   //moduleTypeList = budgetMasterService.getprojectsList(partyInstance)
    	   render (template:"proposalSelect", model : ['moduleTypeList' : moduleTypeList,'moduleType' : params.moduleType])
    	}
    	if(params.moduleType=="FullProposal")
    	{ 
    		/*Method to get saved FullProposal*/
    	    def proposalList = budgetMasterService.getFullProposalList(partyInstance)
    		for(int i=0;i<proposalList.size();i++)
    		{	
    		   /*Method to get project Title */
    		   proposalApplicationInstance = budgetMasterService.getprojectTitleList(proposalList[i].id)
    		   moduleTypeList.add(proposalApplicationInstance[0])
    		}
    		render (template:"proposalSelect", model : ['moduleTypeList' : moduleTypeList,'moduleType' : params.moduleType])
    	    
    	}
   }
    
   
}
