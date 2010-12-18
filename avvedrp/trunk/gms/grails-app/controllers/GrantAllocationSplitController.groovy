import java.text.*;
import java.util.*;

import grails.converters.*
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class GrantAllocationSplitController extends GmsController  {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']
    def list = {
    		def grantAllocationSplitService = new GrantAllocationSplitService()
    		def grantAllocationService = new GrantAllocationService()
            def grantAllocationSplitInstance = new GrantAllocationSplit()
    		GrailsHttpSession gh=getSession()
    		gh.removeValue("Help")
			//putting help pages in session
			gh.putValue("Help","Headwise_Allocation.htm")
    		
    		
            def projectsInstance = Projects.get(new Integer(params.id))
            
              def dataSecurityService = new DataSecurityService()
    		//checking  whether the user has access to the given projects
    		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.id,new Integer(getUserPartyID()))==0)
    		{
    			
    					
    					 redirect uri:'/invalidAccess.gsp'

    		}
    		else
    		{
    			
    		def grantAllocationSplitDetailsList=[]
            projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
    		def grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(params.id)
            for(int i=0;i<grantAllocationInstanceList.size();i++)
            {
            	grantAllocationSplitDetailsList.add(grantAllocationSplitService.getGrantAllocationSplitDetailsByGrantAllocation(grantAllocationInstanceList[i].id))
            }
    		def grantAllocationSplitList=grantAllocationSplitService.getGrantAllocationSplitDetailsByProject(params.id)
            grantAllocationSplitInstance.properties = params
            grantAllocationSplitInstance.projects=projectsInstance;
    		grantAllocationSplitInstance.grantAllocation=grantAllocationInstanceList[0]	 
    		ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    		return ['grantAllocationSplitInstance':grantAllocationSplitInstance,
                    'grantAllocationInstanceList':grantAllocationInstanceList,
                    'grantAllocationSplitDetailsList':grantAllocationSplitDetailsList,
                    'currencyFormat':currencyFormatter]
    		}
    }

    def show = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
        def grantAllocationSplitInstance = grantAllocationSplitService.getGrantAllocationSplitById(new Integer(params.id))
        if(!grantAllocationSplitInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ grantAllocationSplitInstance : grantAllocationSplitInstance ] }
    }

    def delete = {
        def grantAllocationSplitService = new GrantAllocationSplitService()
        def grantExpenseService = new GrantExpenseService()
        def grantReceiptService = new GrantReceiptService()
        def grantAllocationSplitInstance = grantAllocationSplitService.getGrantAllocationSplitById(new Integer(params.id))
		def grantExpenseInstance = grantExpenseService.getExpenseForGrantAllocationSplit(grantAllocationSplitInstance);
		def grantReceiptInstance = grantReceiptService.getGrantReceiptForGrantAllocationSplit(grantAllocationSplitInstance);
		if(grantExpenseInstance)
		{
			flash.message ="${message(code: 'default.CannotdeleteHeadwiseAllocation.label')}"
			redirect(action:'edit',params:['id':grantAllocationSplitInstance.id,'UnAll':params.unAllocatedAmt,'unAllocatedAmount':grantAllocationSplitInstance.unAllocatedAmt]) 

			

		}
		else if(grantReceiptInstance)
		{
			redirect(action:'edit',id:grantAllocationSplitInstance.id) 

			flash.message ="${message(code: 'default.CannotdeleteHeadwiseAllocationGrantReceived.label')}"

		}
		else
		{      
			Integer projectId = grantAllocationSplitService.deleteGrantAllocationSplit(new Integer(params.id))
			if(projectId != null){
	        	if(projectId > 0){
	        		flash.message = "${message(code: 'default.GrantHeadDeleted.label')}"
	        			  redirect(action:list,id:projectId)
	        	}
	        }
	        else{
	        	flash.message = "${message(code: 'default.notfond.label')}"
	            redirect(action:list)
	        }
		}
    }

    def edit = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
		
        def grantAllocationSplitInstance = grantAllocationSplitService.getGrantAllocationSplitById(new Integer(params.id))
        def dataSecurityService = new DataSecurityService()
		def accountHeadInstanceList 
		def unAllocatedAmount = params.UnAll
		//checking  whether the user has access to the given projects
		def accountHeadsService =  new AccountHeadsService()
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(grantAllocationSplitInstance.projects.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
	        if(!grantAllocationSplitInstance) 
	        {
	            flash.message = "${message(code: 'default.notfond.label')}"
	            redirect(action:list)
	        }
	        else 
	        {
	    		// check whether the accounthead id is exists
	    		if(grantAllocationSplitInstance.accountHead)
	            {
	    			/**
	        		 * 1. Check  whether the account head is sub account head)
	        		 * 2. If its sub find the main account head)
	        		 */
	        		if(grantAllocationSplitInstance.accountHead.parent) //if null its main accounthead.
	    			{
	    				def subAccHead =  grantAllocationSplitInstance.accountHead
	    				grantAllocationSplitInstance.subAccHead = subAccHead
	    				def accountHead = accountHeadsService.getParentAccountHead(grantAllocationSplitInstance.subAccHead)
	    				grantAllocationSplitInstance.accHead = accountHead
	    				accountHeadInstanceList = accountHeadsService.getSubAccountHeads(new Integer(accountHead.id.toString()))
	    				
	    			}
	    			else
	    			{
	    				accountHeadInstanceList = accountHeadsService.getSubAccountHeads(new Integer(grantAllocationSplitInstance.accountHead.id.toString()))
	    			}
	    		 }
	          }
			}
			if(params.UnAll)
			{
			
				grantAllocationSplitInstance.unAllocatedAmt = grantAllocationSplitInstance.amount + new Double(params.UnAll)
			}
			else
			{
				grantAllocationSplitInstance.unAllocatedAmt = grantAllocationSplitInstance.amount 
			}
			NumberFormat formatter = new DecimalFormat("#0.00");
			render(view:'edit',model:['grantAllocationSplitInstance':grantAllocationSplitInstance,
			                          'accountHeadInstanceList':accountHeadInstanceList,
			                          'amount':formatter.format(grantAllocationSplitInstance.amount),
			                          'unAllocatedAmount':grantAllocationSplitInstance.unAllocatedAmt])
    	}

    def update = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
		def grantAllocationSplitInstance = grantAllocationSplitService.updateGrantAllocationSplit(params)
		if(grantAllocationSplitInstance) {
			if((params.subAccountHead != "null") && (params.subAccountHead))
        	{
				grantAllocationSplitInstance.accountHead = AccountHeads.get(new Integer(params.subAccountHead))
        	}
			if((!params.subAccountHead) && (params.subAccountHead == "null") )
        	{
        		grantAllocationSplitInstance.accountHead = AccountHeads.get(grantAllocationSplitInstance.accountHead.id)
        	}
			
			
			if(grantAllocationSplitInstance.isSaved){
				flash.message = "${message(code: 'default.updated.label')}"
				
				  redirect(action:list,id:grantAllocationSplitInstance.projects.id)
			}
			else{
				render(view:'edit',model:[grantAllocationSplitInstance:grantAllocationSplitInstance])
			}
		}
		else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
		}
    }

    def create = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
		def grantAllocationService = new GrantAllocationService()
        def grantAllocationSplitInstance = new GrantAllocationSplit()
		def projectsInstance = Projects.get(new Integer(params.id))
        def grantAllocationInstance = GrantAllocation.get(new Integer(params.grantAllotId))
        def unAllocatedAmount = params.UnAll
        def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
         projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
		def grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(params.id)
    
        def grantAllocationSplitList=grantAllocationSplitService.getGrantAllocationSplitByProjects(params.id)
        grantAllocationSplitInstance.properties = params
        grantAllocationSplitInstance.unAllocatedAmt = new Double(params.UnAll)
        grantAllocationSplitInstance.projects=projectsInstance;
        grantAllocationSplitInstance.grantAllocation=grantAllocationInstance;
       
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
        return ['grantAllocationSplitInstance':grantAllocationSplitInstance,
                'grantAllocationInstance':grantAllocationInstance,
                'grantAllocationInstanceList':grantAllocationInstanceList,
                'currencyFormat':currencyFormatter,
				'unAllocatedAmount':grantAllocationSplitInstance.unAllocatedAmt]
		}
    }

    def save = {
    		
        def grantAllocationSplitInstance = new GrantAllocationSplit(params)
        if(!grantAllocationSplitInstance.hasErrors() ) {
            def grantAllocationInstance = GrantAllocation.get(new Integer(params.grantAllotId))
        	grantAllocationSplitInstance.createdBy="admin"
        	grantAllocationSplitInstance.modifiedBy="admin"
        	grantAllocationSplitInstance.modifiedBy="admin"
        	grantAllocationSplitInstance.createdDate=new Date()
            grantAllocationSplitInstance.grantAllocation=grantAllocationInstance;
            def grantAllocationSplitService = new GrantAllocationSplitService()
            if((!params.subAccountHead) && (params.subAccountHead == "null") )
        	{
            	 grantAllocationSplitInstance.accountHead = AccountHeads.get(new Integer(params.accountHead.id))
        	}
        	if((params.subAccountHead != "null") && (params.subAccountHead))
        	{
        		grantAllocationSplitInstance.accountHead = AccountHeads.get(new Integer(params.subAccountHead))
        		
        	}
        	def grantAllocationSplitInstanceCheck =grantAllocationSplitService.validateGrantAllocationSplit(grantAllocationSplitInstance,params.projectId)
        	if(!grantAllocationSplitInstanceCheck)
        	{
        		grantAllocationSplitInstance =grantAllocationSplitService.saveGrantAllocationSplit(grantAllocationSplitInstance,new Integer(params.projectId)) 
        	}
        	else
        	{flash.message = "${message(code: 'default.GrantcannotAllocatedHeads.label')}"}
            flash.message = "${message(code: 'default.GrantAllocated.label')}"
            	  redirect(action:list,id:params.projectId)
        }
        else {
            render(view:'create',model:[grantAllocationSplitInstance:grantAllocationSplitInstance])
        }
    }
    def updateSubAccount =
    { 
    		if(params.accountHead)
        	{
    	    	def subaccountHead = AccountHeads.findAll("from AccountHeads AH where AH.activeYesNo='Y' and AH.parent.id="+params.accountHead)
    	    	render (template:"subAccHead", model : ['accountHead' : subaccountHead])
    	    	
        	}
        	else
        	{
        		render (template:"notSelected")
        	}
    }

}
