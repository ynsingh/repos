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
    			 println "grantAllocationSplitDetailsList"+grantAllocationSplitDetailsList
            def grantAllocationSplitList=grantAllocationSplitService.getGrantAllocationSplitDetailsByProject(params.id)
            println "grantAllocationSplitList"+grantAllocationSplitList
            grantAllocationSplitInstance.properties = params
            grantAllocationSplitInstance.projects=projectsInstance;
    		 grantAllocationSplitInstance.grantAllocation=grantAllocationInstanceList[0]	 
    		ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    		println"grantAllocationSplitInstance"+grantAllocationInstanceList[0].party.code
            return ['grantAllocationSplitInstance':grantAllocationSplitInstance,'grantAllocationInstanceList':grantAllocationInstanceList,'grantAllocationSplitDetailsList':grantAllocationSplitDetailsList,'currencyFormat':currencyFormatter]
    		}
    }

    def show = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
        def grantAllocationSplitInstance = grantAllocationSplitService.getGrantAllocationSplitById(new Integer(params.id))

        if(!grantAllocationSplitInstance) {
            flash.message = " GrantAllocationSplit not found with id ${params.id}"
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
		println "" + grantAllocationSplitInstance
		if(grantExpenseInstance)
		{
	
			redirect(action:'edit',id:grantAllocationSplitInstance.id) 

			flash.message = "Cannot delete the Head wise Allocation as Grant Expense is entered" 

		}
		else if(grantReceiptInstance)
		{
			redirect(action:'edit',id:grantAllocationSplitInstance.id) 

			flash.message = "Cannot delete the Head wise Allocation as Grant is Received" 

		}
		else
		{      
			Integer projectId = grantAllocationSplitService.deleteGrantAllocationSplit(new Integer(params.id))
			if(projectId != null){
	        	if(projectId > 0){
	        		flash.message = "Grant Head Deleted"
	        			  redirect(action:list,id:projectId)
	        	}
	        }
	        else{
	        	flash.message = "GrantAllocationSplit not found"
	            redirect(action:list)
	        }
		}
    }

    def edit = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
        def grantAllocationSplitInstance = grantAllocationSplitService.getGrantAllocationSplitById(new Integer(params.id))
           def dataSecurityService = new DataSecurityService()
		def accountHeadInstanceList 
		
		//checking  whether the user has access to the given projects
		println "--------------params ------------------" + params
		def accountHeadsService =  new AccountHeadsService()
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(grantAllocationSplitInstance.projects.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
        if(!grantAllocationSplitInstance) 
        {
            flash.message = "GrantAllocationSplit not found with id ${params.id}"
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
        		println "==============sub accHead================"+grantAllocationSplitInstance.accountHead
    			if(grantAllocationSplitInstance.accountHead.parent) //if null its main accounthead.
    			{
    				def subAccHead =  grantAllocationSplitInstance.accountHead
    				grantAllocationSplitInstance.subAccHead = subAccHead
    				println "==============sub accHead================"+grantAllocationSplitInstance.subAccHead.code
    				
    				def accountHead = accountHeadsService.getParentAccountHead(grantAllocationSplitInstance.subAccHead)
    				println "==============accountHead================" + accountHead.id
    				grantAllocationSplitInstance.accHead = accountHead
    				accountHeadInstanceList = accountHeadsService.getSubAccountHeads(new Integer(accountHead.id.toString()))
    				println "==============after================" 
    			}
    			else
    			{
    				accountHeadInstanceList = accountHeadsService.getSubAccountHeads(new Integer(grantAllocationSplitInstance.accountHead.id.toString()))
    			}
    		
            }
        }
    }
		render(view:'edit',model:['grantAllocationSplitInstance':grantAllocationSplitInstance,'accountHeadInstanceList':accountHeadInstanceList])
    }

    def update = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
		def grantAllocationSplitInstance = grantAllocationSplitService.updateGrantAllocationSplit(params)
		if(grantAllocationSplitInstance) {
			println "==============params.getValue================" +params.subAccountHead
			if((params.subAccountHead != "null") && (params.subAccountHead))
        	{
				println "==============inside================" +params.subAccountHead
				grantAllocationSplitInstance.accountHead = AccountHeads.get(new Integer(params.subAccountHead))
        	}
			if((!params.subAccountHead) && (params.subAccountHead == "null") )
        	{
        		println "==============else================" 
        		grantAllocationSplitInstance.accountHead = AccountHeads.get(grantAllocationSplitInstance.accountHead.id)
        	}
			if(grantAllocationSplitInstance.isSaved){
				flash.message = "Grant Heads Updated"
				
				  redirect(action:list,id:grantAllocationSplitInstance.projects.id)
			}
			else{
				render(view:'edit',model:[grantAllocationSplitInstance:grantAllocationSplitInstance])
			}
		}
		else {
            flash.message = "GrantAllocationSplit not found with id ${params.id}"
            redirect(action:edit,id:params.id)
		}
    }

    def create = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
		def grantAllocationService = new GrantAllocationService()
        def grantAllocationSplitInstance = new GrantAllocationSplit()
		println "params"+params
		println "params.grantAllotId"+params.grantAllotId
		println "params.UnAll "+params.UnAll
		
        def projectsInstance = Projects.get(new Integer(params.id))
        def grantAllocationInstance = GrantAllocation.get(new Integer(params.grantAllotId))
        
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
        println "grantAllocationSplitList"+grantAllocationSplitList
        grantAllocationSplitInstance.properties = params
        grantAllocationSplitInstance.unAllocatedAmt = new Double(params.UnAll)
        grantAllocationSplitInstance.projects=projectsInstance;
        grantAllocationSplitInstance.grantAllocation=grantAllocationInstance;
       
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
        return ['grantAllocationSplitInstance':grantAllocationSplitInstance,'grantAllocationInstance':grantAllocationInstance,'grantAllocationInstanceList':grantAllocationInstanceList,'currencyFormat':currencyFormatter]
		}
    }

    def save = {
    		println "------------------------------"+params.subAccountHead
        def grantAllocationSplitInstance = new GrantAllocationSplit(params)
        if(!grantAllocationSplitInstance.hasErrors() ) {
            def grantAllocationInstance = GrantAllocation.get(new Integer(params.grantAllotId))
        	grantAllocationSplitInstance.createdBy="admin"
        	grantAllocationSplitInstance.modifiedBy="admin"
        	grantAllocationSplitInstance.modifiedBy="admin"
        	grantAllocationSplitInstance.createdDate=new Date()
            grantAllocationSplitInstance.grantAllocation=grantAllocationInstance;
            println "grantAllocationSplitInstance"+grantAllocationSplitInstance.grantPeriod
           // def grantAllocationSplitInstanceCheck = GrantAllocationSplit.findAll("from GrantAllocationSplit GAS where GAS.accountHead.id='"+grantAllocationSplitInstance.accountHead.id+"' and GAS.grantPeriod.id= '"+grantAllocationSplitInstance.grantPeriod.id+"' and GAS.projects.id="+params.projectId)
            //println "grantAllocationSplitInstanceCheck"+grantAllocationSplitInstanceCheck
            println "grantAllocationSplitInstance"+grantAllocationSplitInstance.accountHead
        	def grantAllocationSplitService = new GrantAllocationSplitService()
            println "params.subAccountHead"+params.subAccountHead
            if((!params.subAccountHead) && (params.subAccountHead == "null") )
        	{
            	 println "inside"
            	 grantAllocationSplitInstance.accountHead = AccountHeads.get(new Integer(params.accountHead.id))
        	}
        	if((params.subAccountHead != "null") && (params.subAccountHead))
        	{
        		println "inside else"
        		grantAllocationSplitInstance.accountHead = AccountHeads.get(new Integer(params.subAccountHead))
        		
        	}
        	def grantAllocationSplitInstanceCheck =grantAllocationSplitService.validateGrantAllocationSplit(grantAllocationSplitInstance,params.projectId)
        	if(!grantAllocationSplitInstanceCheck)
        	{
        	println "grantAllocationSplitInstanceCheck"+grantAllocationSplitInstanceCheck
        	grantAllocationSplitInstance =grantAllocationSplitService.saveGrantAllocationSplit(grantAllocationSplitInstance,new Integer(params.projectId)) 
        	}
        	else
        	{flash.message = "Grant can not Allocated to Heads"}
            flash.message = "Grant is Allocated to Heads"
            	  redirect(action:list,id:params.projectId)
        }
        else {
            render(view:'create',model:[grantAllocationSplitInstance:grantAllocationSplitInstance])
        }
    }
    def updateSubAccount =
    { 
    		println "---------------on call--------------------" +params
    		if(params.accountHead)
        	{
    	    	def subaccountHead = AccountHeads.findAll("from AccountHeads AH where AH.activeYesNo='Y' and AH.parent.id="+params.accountHead)
    	    	println "lllllllllllww" +subaccountHead;
    	    	render (template:"subAccHead", model : ['accountHead' : subaccountHead])
    	    	println "after" +subaccountHead;  
        	}
        	else
        	{
        		render (template:"notSelected")
        	}
    }

}
