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
    		
    			
	    		def grantAllocationSplitDetailsList=[]
	    		projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProjectOnly(projectsInstance.id)
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
	    		ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
	    		return ['grantAllocationSplitInstance':grantAllocationSplitInstance,
	                    'grantAllocationInstanceList':grantAllocationInstanceList,
	                    'grantAllocationSplitDetailsList':grantAllocationSplitDetailsList,
	                    'currencyFormat':currencyFormatter,'projectsInstance':projectsInstance,
	                    'grantorInstance':grantorInstance,'partyInstance':partyInstance,
	                    'grantAllocationInstanceListSize':grantAllocationInstanceList.size(),'flag':0]
	    	
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
		def accountHeadsService =  new AccountHeadsService()
		def accountHeadList = grantAllocationSplitService.getAccountHeadByNameandCode(projectsInstance.id)
		def headAllocatedAmount = grantAllocationSplitService.getAllocatedAmountByProjectId(projectsInstance.id)//sum of headwise allocated amount
		projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProjectOnly(projectsInstance.id)
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
					
			}
			else
			{
				
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
		def accountHeadsService = new AccountHeadsService()
		double amount= ((params.amount).toDouble()).doubleValue()
		double balanceAmount= ((params.balance).toDouble()).doubleValue()
		def grantAllocationSplitInstances = grantAllocationSplitService.getGrantAllocationSplitById(new Integer(params.id))
		def grantExpenseInstance = grantExpenseService.getExpenseForGrantAllocationSplit(grantAllocationSplitInstances);
		double exactBalance = (balanceAmount+(grantAllocationSplitInstances.amount).doubleValue())
		double parentSplitTotal = 0.00
		double parentExpenseTotal = 0.00
		def accountHeadChildInstanceList = []
		def balance
		def acchead
		if(grantExpenseInstance)
		{
			flash.error ="${message(code: 'default.CannotupdateHeadwiseAllocation.label')}"
				redirect(action:'edit',params:['id':grantAllocationSplitInstances.id,'UnAll':params.unAllocatedAmt,'unAllocatedAmount':grantAllocationSplitInstances.unAllocatedAmt]) 
          }
		   else 
		    {	
				if(grantAllocationSplitInstances) {
											
										
					if(params.subAccountHead != "null")
					{
						
						params.accountHead = AccountHeads.get(new Integer(params.subAccountHead))
						
						def accountHeadParentInstance = accountHeadsService.getAccountHeadsById(params.accountHead.parent.id)
						def grantAllocationSplitParentInstance = GrantAllocationSplit.findAll("from GrantAllocationSplit gas where gas.grantAllocation.id="+grantAllocationSplitInstances.grantAllocation.id+"and gas.grantPeriod.id="+params.grantPeriod.id+"and gas.accountHead.id="+accountHeadParentInstance.id)//get all child account head instance List from grant allocation split
						if(grantAllocationSplitParentInstance){
							acchead = "MainHead"
							
						}
						
					}
					if((!params.subAccountHead) && (params.subAccountHead == "null"))
					{
						params.accountHead = AccountHeads.get(new Integer(params.accountHead.id))
						def accountHeadChildList = AccountHeads.findAll("from AccountHeads ah where ah.parent.id="+params.accountHead.id)
						for(childInstance in accountHeadChildList){
							
								def accountHeadChildInstance = GrantAllocationSplit.find("from GrantAllocationSplit gas where gas.grantAllocation.id="+grantAllocationSplitInstances.grantAllocation.id+"and gas.grantPeriod.id="+params.grantPeriod.id+"and gas.accountHead.id="+childInstance.id)//get all child account head instance List from grant allocation split
								if(accountHeadChildInstance){	
									accountHeadChildInstanceList.add(accountHeadChildInstance)
										
								}
									
							}
						 if(accountHeadChildInstanceList){
							acchead = "subHead"
							
							
						}
						
					}
					if(amount > exactBalance)
		        	{
		        		flash.message= "${message(code: 'default.couldnotallocateBalanceAmount.label')}"
		        			redirect(action:'edit',params:['id':grantAllocationSplitInstances.id,'UnAll':params.unAllocatedAmt,'unAllocatedAmount':grantAllocationSplitInstances.unAllocatedAmt]) 
		        	}
		        	else{
										
						if(acchead == "MainHead"){
							flash.error= "${message(code: 'default.cantDisburseFundAlreadyAllotedToMainAccountHead.label')}"
							redirect(action:'edit',params:['id':grantAllocationSplitInstances.id,'UnAll':params.unAllocatedAmt,'unAllocatedAmount':grantAllocationSplitInstances.unAllocatedAmt])
						}
						else{
							if(acchead == "subHead"){
								flash.error= "${message(code: 'default.cantDisburseFundAlreadyAllotedToSubAccountHead.label')}"
								redirect(action:'edit',params:['id':grantAllocationSplitInstances.id,'UnAll':params.unAllocatedAmt,'unAllocatedAmount':grantAllocationSplitInstances.unAllocatedAmt])
							}
							else{
						
											def grantAllocationSplitInstanceCheck = grantAllocationSplitService.checkGrantAllocationSplit(params,grantAllocationSplitInstances.projects.id,grantAllocationSplitInstances.grantAllocation.id)
											if((!grantAllocationSplitInstanceCheck) || 	(grantAllocationSplitInstanceCheck[0].id == Long.parseLong(params.id)))
											{      
												
												if(grantAllocationSplitInstances.projects.parent != null){
													
														def subGrantSplitSum = grantAllocationSplitService.getTotalSubGrantSplit(grantAllocationSplitInstances.projects.parent.id, params.accountHead.id, params.grantPeriod.id)
													
														
														def parentGrantAllocationSplitList = GrantAllocationSplit.findAll("from GrantAllocationSplit GS where GS.projects.id ="+grantAllocationSplitInstances.projects.parent.id+"and GS.accountHead.id="+params.accountHead.id+"and GS.grantPeriod.id="+params.grantPeriod.id)
														for(parentSplitInstance in parentGrantAllocationSplitList){
															
															parentSplitTotal = parentSplitTotal+parentSplitInstance.amount
															
															def sumExpense = grantExpenseService.getTotalExpenseBySplit(parentSplitInstance.id)
															parentExpenseTotal = parentExpenseTotal+sumExpense
														}
														balance = ((parentSplitTotal-parentExpenseTotal)-subGrantSplitSum)+grantAllocationSplitInstances.amount
														
													}
													else{
														
														def splitTotal = grantAllocationSplitService.getTotalGrantSplit(grantAllocationSplitInstances.grantAllocation.id)
														
														balance = ((grantAllocationSplitInstances.grantAllocation.amountAllocated-splitTotal))+grantAllocationSplitInstances.amount
														
													}
													if(amount > balance){
														
														flash.error = "${message(code: 'default.NotEnoughFund.label')}"
														redirect(action:'edit',params:['id':grantAllocationSplitInstances.id,'UnAll':params.unAllocatedAmt,'unAllocatedAmount':grantAllocationSplitInstances.unAllocatedAmt])
													}
													else{
												
														def grantAllocationSplitInstance = grantAllocationSplitService.updateGrantAllocationSplit(params)	
														if(grantAllocationSplitInstance.isSaved){
															flash.message = "${message(code: 'default.updated.label')}"
													
															  redirect(action:'list',id:grantAllocationSplitInstances.projects.id)
														}
														else{
															redirect(action:'edit',params:['id':grantAllocationSplitInstances.id,'UnAll':params.unAllocatedAmt,'unAllocatedAmount':grantAllocationSplitInstances.unAllocatedAmt])
														}
													}
											}
											else {
									            flash.error = "${message(code: 'default.notfond.label')}"
									            redirect(action:'list',id:grantAllocationSplitInstances.projects.id)
											}
		        					}
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
        double unAllocatedAmount = ((params.UnAll).toDouble()).doubleValue()
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
	       // def subAllocatedAmount = grantAllocationSplitService.getSubAllocatedAmountByProjectId(projectsInstance)
	        if(headAllocatedAmount[0] != null)
				{
					balanceAmount = projectsInstance.totAllAmount - headAllocatedAmount[0]
						
				}
				else
				{
					balanceAmount = projectsInstance.totAllAmount
				}
			grantAllocationSplitInstance.unAllocatedAmt = new Double(balanceAmount)
	        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
	        NumberFormat formatter = new DecimalFormat("#0.00");
	        return ['grantAllocationSplitInstance':grantAllocationSplitInstance,
	                'grantAllocationInstance':grantAllocationInstance,
	                'grantAllocationInstanceList':grantAllocationInstanceList,
	                'currencyFormat':currencyFormatter,
					'unAllocatedAmount':unAllocatedAmount,'accountHeadList':accountHeadList,
					'amount':formatter.format(grantAllocationSplitInstance.amount),
					'balanceAmount':balanceAmount]
	//	}
    }

    def save = {
		def grantAllocationSplitService = new GrantAllocationSplitService()
		def grantExpenseService = new GrantExpenseService()
		def grantAllocationSplitInstance = new GrantAllocationSplit(params)
    	double amount= ((params.amount).toDouble()).doubleValue()
    	double balanceAmount= ((params.balance).toDouble()).doubleValue()
		def accountHeadChildInstanceList = []
		def duplecateAccountHead
		double parentSplitTotal = 0.0 
		double parentExpenseTotal = 0.0
		def balance
		def mainSplit = "Yes"
		def projectInstance = Projects.get(new Integer(params.projectId))
		if(!grantAllocationSplitInstance.hasErrors() ) {
	            def grantAllocationInstance = GrantAllocation.get(new Integer(params.grantAllotId))
	        	grantAllocationSplitInstance.createdBy="admin"
	        	grantAllocationSplitInstance.modifiedBy="admin"
	        	grantAllocationSplitInstance.modifiedBy="admin"
	        	grantAllocationSplitInstance.createdDate=new Date()
	            grantAllocationSplitInstance.grantAllocation=grantAllocationInstance;
				
				if(params.subAccountHead == "null")
				{
					grantAllocationSplitInstance.accountHead = AccountHeads.get(new Integer(params.accountHead.id))
					def accountHeadChildList = AccountHeads.findAll("from AccountHeads ah where ah.parent.id="+grantAllocationSplitInstance.accountHead.id)
					for(childInstance in accountHeadChildList){
						
							def accountHeadChildInstance = GrantAllocationSplit.find("from GrantAllocationSplit gas where gas.grantAllocation.id="+grantAllocationInstance.id+"and gas.grantPeriod.id="+grantAllocationSplitInstance.grantPeriod.id+"and gas.accountHead.id="+childInstance.id)//get all child account head instance List from grant allocation split
							if(accountHeadChildInstance != null){
									accountHeadChildInstanceList.add(accountHeadChildInstance)
								}
							
					}
					if(accountHeadChildInstanceList){
						duplecateAccountHead = "SubHead"
						
					}
				
				}

				if((params.subAccountHead != "null") && (params.subAccountHead))
				{
					grantAllocationSplitInstance.accountHead = AccountHeads.get(new Integer(params.subAccountHead))
					def accountHeadParentInstance = AccountHeads.find("from AccountHeads ah where ah.id="+grantAllocationSplitInstance.accountHead.parent.id)
					def grantAllocationSplitParentInstance = GrantAllocationSplit.findAll("from GrantAllocationSplit gas where gas.grantAllocation.id="+grantAllocationInstance.id+"and gas.grantPeriod.id="+grantAllocationSplitInstance.grantPeriod.id+"and gas.accountHead.id="+accountHeadParentInstance.id)//get all child account head instance List from grant allocation split
					if(grantAllocationSplitParentInstance){
						duplecateAccountHead = "MainHead"
						
					}
				}
				
				if(grantAllocationInstance.projects.parent != null){
				def grantAllocationSplitInstanceOfParent = grantAllocationSplitService.getSubGrantSplitByAccHead(grantAllocationInstance.projects.parent.id,grantAllocationSplitInstance.accountHead.id,grantAllocationSplitInstance.grantPeriod.id)
				if(grantAllocationSplitInstanceOfParent == null){
					mainSplit = "No"
				}
				}
				if(mainSplit == "No"){
					flash.error = "${message(code: 'default.DoHeadwiseForParent.label')}"
					redirect(action:list,id:params.projectId)
				}
				else
				{
					if(amount > balanceAmount)
		        	{
		        		flash.message= "${message(code: 'default.couldnotallocateBalanceAmount.label')}"
		        			redirect(action:create,params:[id:params.projectId,grantAllotId:params.grantAllotId,UnAll:params.UnAll])
		        	}
		        	else
		        	{
						
							if(duplecateAccountHead == "MainHead"){
								flash.message= "${message(code: 'default.cantDisburseFundAlreadyAllotedToMainAccountHead.label')}"
								redirect(action:list,id:params.projectId)
								
							}
							else{
									if(duplecateAccountHead == "SubHead"){
										flash.message= "${message(code: 'default.cantDisburseFundAlreadyAllotedToSubAccountHead.label')}"
										redirect(action:list,id:params.projectId)
										
									}
									else{
										
										def grantAllocationSplitInstanceCheck =grantAllocationSplitService.checkGrantAllocationSplit(grantAllocationSplitInstance,params.projectId,grantAllocationInstance.id)
										if(!grantAllocationSplitInstanceCheck){
											
												if(grantAllocationInstance.projects.parent != null){
												
													def subGrantSplitSum = GrantAllocationSplit.executeQuery("select sum(GS.amount) from GrantAllocationSplit GS where GS.grantAllocation.projects.parent.id ="+grantAllocationInstance.projects.parent.id+"and GS.accountHead.id="+grantAllocationSplitInstance.accountHead.id+"and GS.grantPeriod.id="+grantAllocationSplitInstance.grantPeriod.id)
													if(subGrantSplitSum[0] == null)
														subGrantSplitSum = 0.0
													
													def parentGrantAllocationSplitList = GrantAllocationSplit.findAll("from GrantAllocationSplit GS where GS.grantAllocation.projects.id ="+grantAllocationInstance.projects.parent.id+"and GS.accountHead.id="+grantAllocationSplitInstance.accountHead.id+"and GS.grantPeriod.id="+grantAllocationSplitInstance.grantPeriod.id)
													for(parentSplitInstance in parentGrantAllocationSplitList){
														
														parentSplitTotal = parentSplitTotal+parentSplitInstance.amount
														
														def sumExpense = grantExpenseService.getTotalExpenseBySplit(parentSplitInstance.id)
														parentExpenseTotal = parentExpenseTotal+sumExpense
													}
													balance = (parentSplitTotal-parentExpenseTotal)-subGrantSplitSum
												}
												else{
													
													def splitTotal = grantAllocationSplitService.getTotalGrantSplit(grantAllocationInstance.id)
													
													balance = (grantAllocationInstance.amountAllocated-splitTotal)
												}
												if(amount > balance){
													
													flash.error = "${message(code: 'default.NotEnoughFund.label')}"
													redirect(action:list,id:params.projectId)
												}
												else{
												grantAllocationSplitInstance =grantAllocationSplitService.saveGrantAllocationSplit(grantAllocationSplitInstance,new Integer(params.projectId))
												flash.message = "${message(code: 'default.GrantAllocated.label')}"
												redirect(action:list,id:params.projectId)
												}
									 
										}
										else
										{flash.error = "${message(code: 'default.GrantcannotAllocatedHeads.label')}"
											redirect(action:list,id:params.projectId)
											
										}
									}
							}
								
					}
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
