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
    		def grantorInstance
    		GrailsHttpSession gh=getSession()
    		gh.removeValue("Help")
			//putting help pages in session
			gh.putValue("Help","Headwise_Allocation.htm")
			def partyInstance = Party.get(gh.getValue("Party"))
            def projectsInstance = Projects.get(new Integer(params.id))
            def dataSecurityService = new DataSecurityService()
    		
    		if(projectsInstance.parent)
    		  grantorInstance = GrantAllocation.find("from GrantAllocation GA where GA.projects.id = "+projectsInstance.parent.id+""); 
    		/* checking  whether the user has access to the given projects
		    		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.id,new Integer(getUserPartyID()))==0)
		    		{
    					 redirect uri:'/invalidAccess.gsp'
             		}
    		else
    		{ */
    			
	    		def grantAllocationSplitDetailsList=[]
	    		
    		    if(projectsInstance.parent != null)
	    			 projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProjectOnly(projectsInstance.id)
	       	    else
	                 projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
	    		
	            def grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(params.id)
	            def grantAllocationInstanceListSize
	            def flag 
	            for(int i=0;i<grantAllocationInstanceList.size();i++)
	            {
	            	grantAllocationSplitDetailsList.add(grantAllocationSplitService.getGrantAllocationSplitDetailsByGrantAllocation(grantAllocationInstanceList[i].id))
	            }
	    		def grantAllocationSplitList=grantAllocationSplitService.getGrantAllocationSplitDetailsByProject(params.id)
	            grantAllocationSplitInstance.properties = params
	            grantAllocationSplitInstance.projects=projectsInstance;
	    		grantAllocationSplitInstance.grantAllocation=grantAllocationInstanceList[0]
	    		def subAllocatedAmount = grantAllocationSplitService.getSubAllocatedAmountByProjectId(projectsInstance)
	    		if(subAllocatedAmount)
	    		{
	    			ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
	        		return ['grantAllocationSplitInstance':grantAllocationSplitInstance,
	                        'grantAllocationInstanceList':grantAllocationInstanceList,
	                        'grantAllocationSplitDetailsList':grantAllocationSplitDetailsList,
	                        'currencyFormat':currencyFormatter,'subAllocatedAmount':subAllocatedAmount[0],
	                        'projectsInstance':projectsInstance,'grantorInstance':grantorInstance,'partyInstance':partyInstance,
	                        'grantAllocationInstanceListSize':grantAllocationInstanceList.size(),'flag':0]
	    		}
	    		else
	    		{
	    		ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
	    		return ['grantAllocationSplitInstance':grantAllocationSplitInstance,
	                    'grantAllocationInstanceList':grantAllocationInstanceList,
	                    'grantAllocationSplitDetailsList':grantAllocationSplitDetailsList,
	                    'currencyFormat':currencyFormatter,'projectsInstance':projectsInstance,
	                    'grantorInstance':grantorInstance,'partyInstance':partyInstance,
	                    'grantAllocationInstanceListSize':grantAllocationInstanceList.size(),'flag':0]
	    		}
       //}
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
		def grantAllocationService = new GrantAllocationService()
        def grantAllocationSplitInstance = grantAllocationSplitService.getGrantAllocationSplitById(new Integer(params.id))
        def dataSecurityService = new DataSecurityService()
		def projectsInstance = Projects.get(grantAllocationSplitInstance.projects.id)
		def accountHeadInstanceList 
		def unAllocatedAmount = params.UnAll
		//checking  whether the user has access to the given projects
		def accountHeadsService =  new AccountHeadsService()
		def accountHeadList = grantAllocationSplitService.getAccountHeadByNameandCode(projectsInstance.id)
		def headAllocatedAmount = grantAllocationSplitService.getAllocatedAmountByProjectId(projectsInstance.id)//sum of headwise allocated amount

        def subAllocatedAmount = GrantAllocation.executeQuery("select sum(GA.amountAllocated) from GrantAllocation GA where GA.projects.parent.id="+projectsInstance.id)

		/*if(dataSecurityService.checkForAuthorisedAcsessInProjects(grantAllocationSplitInstance.projects.id,new Integer(getUserPartyID()))==0)
		   {
				 redirect uri:'/invalidAccess.gsp'
       	   }
		else
         { */
				projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
				
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
		//}
			if(params.UnAll)
			{
			
				grantAllocationSplitInstance.unAllocatedAmt = grantAllocationSplitInstance.amount + new Double(params.UnAll)
			}
			else
			{
				grantAllocationSplitInstance.unAllocatedAmt = grantAllocationSplitInstance.amount 
			}
			def balanceAmount
			if(headAllocatedAmount[0])
			{
				balanceAmount = projectsInstance.totAllAmount - headAllocatedAmount[0]
					if(subAllocatedAmount[0])
						balanceAmount = projectsInstance.totAllAmount - (headAllocatedAmount[0]+subAllocatedAmount[0])
			}
			else
			{
				if(subAllocatedAmount[0])
					balanceAmount = projectsInstance.totAllAmount - subAllocatedAmount[0]
				else
					balanceAmount = projectsInstance.totAllAmount
			}
			
				
	       
			NumberFormat formatter = new DecimalFormat("#0.00");
			render(view:'edit',model:['grantAllocationSplitInstance':grantAllocationSplitInstance,
			                          'accountHeadInstanceList':accountHeadInstanceList,
			                          'amount':formatter.format(grantAllocationSplitInstance.amount),
			                          'unAllocatedAmount':grantAllocationSplitInstance.unAllocatedAmt,
			                          'accountHeadList':accountHeadList,amount:formatter.format(grantAllocationSplitInstance.amount),
			                          'balanceAmount':balanceAmount])
    	}

    def update = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
	        def grantExpenseService = new GrantExpenseService()
		double amount= ((params.amount).toDouble()).doubleValue()
		double balanceAmount= ((params.balance).toDouble()).doubleValue()
		def grantAllocationSplitInstances = grantAllocationSplitService.getGrantAllocationSplitById(new Integer(params.id))
		def grantExpenseInstance = grantExpenseService.getExpenseForGrantAllocationSplit(grantAllocationSplitInstances);
		double exactBalance = (balanceAmount+(grantAllocationSplitInstances.amount).doubleValue())
		if(grantExpenseInstance)
		{
			flash.message ="${message(code: 'default.CannotupdateHeadwiseAllocation.label')}"
				redirect(action:'edit',params:['id':grantAllocationSplitInstances.id,'UnAll':params.unAllocatedAmt,'unAllocatedAmount':grantAllocationSplitInstances.unAllocatedAmt]) 
          }
		   else 
		    {	
			   if(grantAllocationSplitInstances) {
					if((params.subAccountHead != "null") && (params.subAccountHead))
		        	{
						params.accountHead = AccountHeads.get(new Integer(params.subAccountHead))
		        	}
					if((!params.subAccountHead) && (params.subAccountHead == "null") )
		        	{
						params.accountHead = AccountHeads.get(grantAllocationSplitInstances.accountHead.id)
		        	}	
					if(amount > exactBalance)
		        	{
		        		flash.message= "${message(code: 'default.couldnotallocateBalanceAmount.label')}"
		        			redirect(action:'edit',params:['id':grantAllocationSplitInstances.id,'UnAll':params.unAllocatedAmt,'unAllocatedAmount':grantAllocationSplitInstances.unAllocatedAmt]) 
		        	}
		        	else
		        	{
			   def grantAllocationSplitInstanceCheck = grantAllocationSplitService.validateGrantAllocationSplit(params,grantAllocationSplitInstances.projects.id)
        if((!grantAllocationSplitInstanceCheck) || 	(grantAllocationSplitInstanceCheck[0].id == Long.parseLong(params.id)))
		{     
	    def grantAllocationSplitInstance = grantAllocationSplitService.updateGrantAllocationSplit(params)	
			if(grantAllocationSplitInstance.isSaved){
				flash.message = "${message(code: 'default.updated.label')}"
				
				  redirect(action:'list',id:grantAllocationSplitInstances.projects.id)
			}
			else{
				redirect(action:'edit',id:grantAllocationSplitInstances.projects.id)
			}
		}
		else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:'list',id:grantAllocationSplitInstances.projects.id)
		}
	}
			   }
    }
    }

    def create = {
	GrailsHttpSession gh=getSession()
    		gh.removeValue("Help")
			//putting help pages in session
			gh.putValue("Help","Disburse_Fund.htm")
		def grantAllocationSplitService = new GrantAllocationSplitService()
		def grantAllocationService = new GrantAllocationService()
        def grantAllocationSplitInstance = new GrantAllocationSplit()
		def projectsInstance = Projects.get(new Integer(params.id))
        def grantAllocationInstance = GrantAllocation.get(new Integer(params.grantAllotId))
        def unAllocatedAmount = params.UnAll
        def accountHeadList = grantAllocationSplitService.getAccountHeadByNameandCode(projectsInstance.id)

        def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
		/*if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.id,new Integer(getUserPartyID()))==0)
	     {
			 redirect uri:'/invalidAccess.gsp'
 		 }
		else
        {*/
        
			 if(projectsInstance.parent != null)
			   projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProjectOnly(projectsInstance.id)
	 	     else
	 		   projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
	     	 	
		    def grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(params.id)
	    
	        def grantAllocationSplitList=grantAllocationSplitService.getGrantAllocationSplitByProjects(params.id)
	        grantAllocationSplitInstance.properties = params
	        
	        grantAllocationSplitInstance.projects=projectsInstance;
	        grantAllocationSplitInstance.grantAllocation=grantAllocationInstance;
	        def balanceAmount
	        def headAllocatedAmount = grantAllocationSplitService.getAllocatedAmountByProjectId(params.id)//sum of headwise allocated amount
	        def subAllocatedAmount = grantAllocationSplitService.getSubAllocatedAmountByProjectId(projectsInstance)
	        if(headAllocatedAmount[0])
				{
					balanceAmount = projectsInstance.totAllAmount - headAllocatedAmount[0]
						if(subAllocatedAmount[0])
							balanceAmount = projectsInstance.totAllAmount - (headAllocatedAmount[0]+subAllocatedAmount[0])
				}
				else
				{
					if(subAllocatedAmount[0])
						balanceAmount = projectsInstance.totAllAmount - subAllocatedAmount[0]
					else
						balanceAmount = projectsInstance.totAllAmount
				}
	        grantAllocationSplitInstance.unAllocatedAmt = new Double(balanceAmount)
	        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
	        NumberFormat formatter = new DecimalFormat("#0.00");
	        return ['grantAllocationSplitInstance':grantAllocationSplitInstance,
	                'grantAllocationInstance':grantAllocationInstance,
	                'grantAllocationInstanceList':grantAllocationInstanceList,
	                'currencyFormat':currencyFormatter,
					'unAllocatedAmount':grantAllocationSplitInstance.unAllocatedAmt,'accountHeadList':accountHeadList,
					'amount':formatter.format(grantAllocationSplitInstance.amount),
					'balanceAmount':balanceAmount]
	//	}
    }

    def save = {
    		def grantAllocationSplitInstance = new GrantAllocationSplit(params)
    		double amount= ((params.amount).toDouble()).doubleValue()
    		double balanceAmount= ((params.balance).toDouble()).doubleValue()
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
        	if(amount > balanceAmount)
        	{
        		flash.message= "${message(code: 'default.couldnotallocateBalanceAmount.label')}"
        			redirect(action:create,params:[id:params.projectId,grantAllotId:params.grantAllotId,UnAll:params.UnAll])
        	}
        	else
        	{
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
        }
        else {
            render(view:'create',model:[grantAllocationSplitInstance:grantAllocationSplitInstance])
        }
    }
    def updateSubAccount =
    { 
    		def grantAllocationSplitService = new GrantAllocationSplitService()
    		if(params.accountHead)
        	{
    	    	def subaccountHead = grantAllocationSplitService.getSubAccountHeadByNameandCode(params)
    	    	
    	    	render (template:"subAccHead", model : ['accountHead' : subaccountHead])
    	    	
        	}
        	else
        	{
        		render (template:"notSelected")
        	}
    }

}
