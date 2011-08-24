import java.text.*;
import java.util.*;
import grails.converters.*;
import java.lang.Number.*;
import grails.util.GrailsUtil.*;
class BudgetDetailsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [budgetDetailsInstanceList: BudgetDetails.list(params), budgetDetailsInstanceTotal: BudgetDetails.count()]
    }

  def create = {
    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
        def budgetDetailsInstance = new BudgetDetails()
    	def budgetMasterService = new BudgetMasterService()
        budgetDetailsInstance.properties = params
        def budgetMasterInstance =BudgetMaster.get(params.budgetMasterId)
        def accountHeadsService = new AccountHeadsService()	
    	/*Method to get All active Accound Heads*/
        def accountHeadList= accountHeadsService.getAllAccountHeads()
        for(int i=0;i<accountHeadList.size();i++ )
	    {
	      accountHeadList[i].accHeadCode=accountHeadList[i].code+" -"+accountHeadList[i].name
	    }
        def budgetDetailsInstanceList = budgetMasterService.getBudgetDetailsList(budgetMasterInstance)
        //def budgetDetailsInstanceList = BudgetDetails.findAll("from BudgetDetails BD where BD.activeYesNo = 'Y' and BD.budgetMaster.id="+budgetMasterInstance.id+"")
        return [budgetDetailsInstance: budgetDetailsInstance,budgetDetailsInstanceList:budgetDetailsInstanceList,accountHeadList:accountHeadList,
                budgetMasterInstance:budgetMasterInstance,'currencyFormat':currencyFormatter,'amount':formatter.format(budgetDetailsInstance.allocatedAmount)]
    }
    
  
    def cancel = 
    {
    	  def budgetMasterInstance =BudgetMaster.get(params.budgetMasterInstance.id)
    	  def budgetDetailsInstance = BudgetDetails.get(params.id)
          redirect(controller:"budgetMaster", action:"create", id:budgetMasterInstance.id,)
    } 

    def save = {
    	def budgetMasterService = new BudgetMasterService()
    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	double amount;
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	def budgetDetailsInstance = new BudgetDetails(params)
    	def budgetMasterInstance =BudgetMaster.get(params.budgetMasterInstance.id)
    	def budgetMasterAmountInstance = budgetMasterService.getTotalBudgetAmount(budgetMasterInstance)
    	def budgetDetailAmountInstance = budgetMasterService.getAllocatedAmount(budgetMasterInstance)
        if(budgetDetailAmountInstance)
        {
        	amount = Double.valueOf(budgetMasterAmountInstance[0]) - Double.valueOf(budgetDetailAmountInstance[0])
        }
    	def subAccountHeadId
    	def subAccountHeadInstance
    	def accountHeadInstance
    	 if(params.subAccountHead == null || params.subAccountHead == "null" )
         {
      	   accountHeadInstance = AccountHeads.get(params.accountHead.id)
      	   def chkbudgetDetailsInstanceList= budgetMasterService.chkDuplicateAccountHeadInstance(budgetMasterInstance,accountHeadInstance)
      	     if(chkbudgetDetailsInstanceList)
	          {
	    		flash.message ="${message(code: 'default.DuplicateAccountHead.label')}"
	  	        redirect(action: "create", params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
	          }
	         else
	         {
	        	 
	        		if(budgetDetailsInstance.allocatedAmount > budgetMasterAmountInstance[0])
	    	    	{
	           			flash.message ="${message(code: 'default.AllocateAmountLessThanTotalAmount.label')}"
	    	      	    redirect(action: "create", params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
	    	    	}
	           		else
	           		{	
	           			if(amount!=null)
	    	       		{
	    	       			if(budgetDetailsInstance.allocatedAmount > amount)
	    	       			{
	    	       				flash.message ="${message(code: 'default.AllocateAmountLessThanTotalAllocatedAmount.label')}"
	    		      	        redirect(action: "create", params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
	    	       			}
	    	       			else
	    	       			{
	    	       			    budgetDetailsInstance.activeYesNo="Y" 
	    	    	        	budgetDetailsInstance.budgetMaster=budgetMasterInstance
	    	    	        	budgetDetailsInstance.properties = params
	    	    	        	budgetDetailsInstance.accountHeads=accountHeadInstance
	    	    	             if (budgetDetailsInstance.save())
	    	    	            {
	    	    	            	  flash.message = "${message(code: 'default.BudgetDetailscreated.message', args: [message(code: 'budgetDetails.label', default: 'BudgetDetails'), budgetDetailsInstance.id])}"
	    	    	                redirect(action: "create",params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
	    	    	           
	    	    	             }
	    	       			}
	    	       		}
	           			else
	           			{
	    	       			
			      	   		budgetDetailsInstance.activeYesNo="Y" 
				        	budgetDetailsInstance.budgetMaster=budgetMasterInstance
				        	budgetDetailsInstance.properties = params
				        	budgetDetailsInstance.accountHeads=accountHeadInstance
				             if (budgetDetailsInstance.save())
				            {
				            	  flash.message = "${message(code: 'default.BudgetDetailscreated.message', args: [message(code: 'budgetDetails.label', default: 'BudgetDetails'), budgetDetailsInstance.id])}"
				                redirect(action: "create",params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
				           
				             }
	           			}
	           		}
	        		
	        }
         }
         else
         {
   	        subAccountHeadId=params.subAccountHead
            subAccountHeadInstance = AccountHeads.get(new Integer(subAccountHeadId))
            
            
             def chkbudgetDetailsSubInstanceList= budgetMasterService.chkDuplicateSubAccountHeadInstance(budgetMasterInstance,subAccountHeadInstance)
	              if(chkbudgetDetailsSubInstanceList)
	              {
	        		flash.message ="${message(code: 'default.DuplicateSubAccountHead.label')}"
	      	        redirect(action: "create", params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
	              }
	              else
	    		  {
	            	  if(budgetDetailsInstance.allocatedAmount > budgetMasterAmountInstance[0])
		    	    	{
		           			flash.message ="${message(code: 'default.AllocateAmountLessThanTotalAmount.label')}"
		    	      	    redirect(action: "create", params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
		    	    	}
		           		else
		           		{	
		           			if(amount!=null)
		    	       		{
		    	       			if(budgetDetailsInstance.allocatedAmount > amount)
		    	       			{
		    	       				flash.message ="${message(code: 'default.AllocateAmountLessThanTotalAllocatedAmount.label')}"
		    		      	        redirect(action: "create", params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
		    	       			}
		    	       			else
		    	       			{
		    	       			    budgetDetailsInstance.activeYesNo="Y" 
		    	    	        	budgetDetailsInstance.budgetMaster=budgetMasterInstance
		    	    	        	budgetDetailsInstance.properties = params
		    	    	        	budgetDetailsInstance.accountHeads=subAccountHeadInstance
		    	    	             if (budgetDetailsInstance.save())
		    	    	            {
		    	    	            	  flash.message = "${message(code: 'default.BudgetDetailscreated.message', args: [message(code: 'budgetDetails.label', default: 'BudgetDetails'), budgetDetailsInstance.id])}"
		    	    	                redirect(action: "create",params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
		    	    	           
		    	    	             }
		    	       			}
		    	       		}
		           			else
		           			{
		    	       			
				      	   		budgetDetailsInstance.activeYesNo="Y" 
					        	budgetDetailsInstance.budgetMaster=budgetMasterInstance
					        	budgetDetailsInstance.properties = params
					        	budgetDetailsInstance.accountHeads=subAccountHeadInstance
					             if (budgetDetailsInstance.save())
					            {
					            	  flash.message = "${message(code: 'default.BudgetDetailscreated.message', args: [message(code: 'budgetDetails.label', default: 'BudgetDetails'), budgetDetailsInstance.id])}"
					                redirect(action: "create",params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
					           
					             }
		           			}
		           		}
         			
            	 
	        	}
         }
    }
         
    

    def show = {
        def budgetDetailsInstance = BudgetDetails.get(params.id)
        if (!budgetDetailsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'budgetDetails.label', default: 'BudgetDetails'), params.id])}"
            redirect(action: "list")
        }
        else {
            [budgetDetailsInstance: budgetDetailsInstance]
        }
    }

    def edit = {
    	def budgetMasterService = new BudgetMasterService()
    	def budgetDetailsInstance = BudgetDetails.get(params.id)
    	def accountHeadsService = new AccountHeadsService()	
    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	def amount
    	def value
    	def difference
    	def accountHeadsInstanceList
    	def accountHeadsInstance
    	def subAccountHeadInstance
    	def subAccountHeadsInstanceList=[]
    	def accountHeadInstanceList
    	def subAccountHeadsList
       
    	def budgetMasterInstance =BudgetMaster.get(params.budgetMasterId)
        /*Method to get All active Accound Heads*/
        def accountHeadList= accountHeadsService.getAllAccountHeads()
        for(int i=0;i<accountHeadList.size();i++ )
	    {
		   	accountHeadList[i].accHeadCode=accountHeadList[i].code+" -"+accountHeadList[i].name
	    }
        def budgetCurrenthead = budgetMasterService.getMasterIdAndAccountHeadId(params,budgetDetailsInstance)
        accountHeadsInstance=budgetMasterService.getAccountHeadIdInCurrentBudget(budgetCurrenthead)
        if(accountHeadsInstance.parent != null)
        {
        	subAccountHeadsList = budgetMasterService.getParentAccountHeadId(accountHeadsInstance)
        for(int j=0;j<subAccountHeadsList.size();j++ )
  	    {
        	  subAccountHeadsList[j].accHeadCode=subAccountHeadsList[j].code+" -"+subAccountHeadsList[j].name
  	    }
        }
         def budgetMasterAmountInstance = budgetMasterService.getTotalBudgetAmount(budgetMasterInstance)
         def budgetDetailAmountInstance = budgetMasterService.getAllocatedAmount(budgetMasterInstance)
         def budgetCurrentAmount = budgetMasterService.getBudgetCurrentAmount(budgetDetailsInstance,budgetMasterInstance)
         if(budgetDetailAmountInstance)
          {
         	amount = Double.valueOf(budgetDetailAmountInstance[0]) - Double.valueOf(budgetCurrentAmount[0])
         	difference = Double.valueOf(budgetMasterAmountInstance[0]) - Double.valueOf(amount)
            value  = currencyFormatter.ConvertToIndainRS(difference)
           }
        if (!budgetDetailsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'budgetDetails.label', default: 'BudgetDetails'), params.id])}"
            redirect(action: "list")
        }
        else {
            return ['budgetDetailsInstance': budgetDetailsInstance,'balance':value,'accountHeadList':accountHeadList,'budgetMasterInstance':budgetMasterInstance,
                    'accountHeadsInstance':accountHeadsInstance,'currencyFormat':currencyFormatter,
                    'subAccountHeadsList':subAccountHeadsList,'amount':formatter.format(budgetDetailsInstance.allocatedAmount)]
        }
    }

    def update = {
       def budgetMasterService = new BudgetMasterService()
       def budgetDetailsInstance = BudgetDetails.get(params.id)
       def budgetModuleMapInstanceList=[]
       def budgetModMapInstance
       def budgetModInstance
       double amount
       double difference
       double allocatedAmount
       allocatedAmount =Double.valueOf(params.allocatedAmount)
       def budgetMasterInstance = BudgetMaster.get(params.budgetMasterInstance.id)
       def budgetMasterAmountInstance = budgetMasterService.getTotalBudgetAmount(budgetMasterInstance)
       def budgetDetailAmountInstance = budgetMasterService.getAllocatedAmount(budgetMasterInstance)
       def budgetCurrentAmount = budgetMasterService.getBudgetCurrentAmount(budgetDetailsInstance,budgetMasterInstance)
       if(budgetDetailAmountInstance)
       {
    	   amount = Double.valueOf(budgetDetailAmountInstance[0]) - Double.valueOf(budgetCurrentAmount[0])
    	   difference = Double.valueOf(budgetMasterAmountInstance[0]) - Double.valueOf(amount)
       }
       def subAccountHeadInstance
       def subAccountHeadId
       def accountHeadInstance
       if(params.subAccountHead == null || params.subAccountHead == "null" )
       {
    	   accountHeadInstance = AccountHeads.get(params.accountHead.id)
    	   def chkbudgetDetailsInstanceList= budgetMasterService.chkDuplicateAccountHeadInstance(budgetMasterInstance,accountHeadInstance)
    	   if(chkbudgetDetailsInstanceList && (chkbudgetDetailsInstanceList.id!= Long.parseLong(params.id)))
             {
	    		flash.message ="${message(code: 'default.DuplicateAccountHead.label')}"
	  	        redirect(action: "create", params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
	          }
	          else
	               {
		        	    if(allocatedAmount > budgetMasterInstance.totalBudgetAmount)
		  
		        	 	{
		          	 			flash.message ="${message(code: 'default.AllocateAmountLessThanTotalAmount.label')}"
		          	      	    redirect(action: "create", params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
		          	    }
	          	 		else
	                    {
		          	 			if(allocatedAmount > difference)
		      	       			{
		      	       				flash.message ="${message(code: 'default.AllocateAmountLessThanTotalAllocatedAmount.label')}"
		      		      	        redirect(action: "create", params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
		      	       			}
		          	 			else
		          	 			{
		          	 				budgetDetailsInstance.activeYesNo="Y" 
		    	    	        	budgetDetailsInstance.budgetMaster=budgetMasterInstance
		    	    	        	budgetDetailsInstance.properties = params
		    	    	        	budgetDetailsInstance.accountHeads=accountHeadInstance
		    	    	             if (budgetDetailsInstance.save())
		    	    	            {
		    	    	            	 flash.message = "${message(code: 'default.BudgetDetailsupdated.message', args: [message(code: 'budgetDetail.label', default: 'BudgetDetail'), budgetDetailsInstance.id])}"
		    	    	                redirect(action: "create",params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
		    	    	           
		    	    	             }
		          	 			}
		          	 }
		        }
	       }
 		 else
         {
   	        subAccountHeadId=params.subAccountHead
            subAccountHeadInstance = AccountHeads.get(new Integer(subAccountHeadId))
            def chkbudgetDetailsSubInstanceList= budgetMasterService.chkDuplicateSubAccountHeadInstance(budgetMasterInstance,subAccountHeadInstance)
                  if(chkbudgetDetailsSubInstanceList && (chkbudgetDetailsSubInstanceList.id!= Long.parseLong(params.id)))
	              {
	        		flash.message ="${message(code: 'default.DuplicateSubAccountHead.label')}"
	      	        redirect(action: "create", params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
	              }
	              else
	    		  {

	            	  if(allocatedAmount > budgetMasterInstance.totalBudgetAmount)
	    			 {
          	 			flash.message ="${message(code: 'default.AllocateAmountLessThanTotalAmount.label')}"
          	      	    redirect(action: "create", params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
	    			 }
          	 		else
                     	{
          	 				if(allocatedAmount > difference)
	      	       			{
	      	       				flash.message ="${message(code: 'default.AllocateAmountLessThanTotalAllocatedAmount.label')}"
	      		      	        redirect(action: "create", params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
	      	       			}
	          	 			else
	          	 			{
	          	 		    budgetDetailsInstance.activeYesNo="Y" 
	    	    	        	budgetDetailsInstance.budgetMaster=budgetMasterInstance
	    	    	        	budgetDetailsInstance.properties = params
	    	    	        	budgetDetailsInstance.accountHeads=subAccountHeadInstance
	    	    	             if (budgetDetailsInstance.save())
	    	    	            {
	    	    	            	 flash.message = "${message(code: 'default.BudgetDetailsupdated.message', args: [message(code: 'budgetDetail.label', default: 'BudgetDetail'), budgetDetailsInstance.id])}"
	    	    	                redirect(action: "create",params:[id: budgetDetailsInstance.id,budgetMasterId:budgetMasterInstance.id])
	    	    	           
	    	    	             }
	          	 			}
		           		}
	    		  }
	        	}
	  }
	        	 
	        	 

    def delete = {
        def budgetDetailsInstance = BudgetDetails.get(params.id)
        if (budgetDetailsInstance) {
            try {
                budgetDetailsInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'budgetDetails.label', default: 'BudgetDetails'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'budgetDetails.label', default: 'BudgetDetails'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'budgetDetails.label', default: 'BudgetDetails'), params.id])}"
            redirect(action: "list")
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
    
    
    
    def budgetList=
    {
    	def budgetMasterService = new BudgetMasterService()
    	def budgetId = params.id
    	def budgetMasterInstance
    	def masterInstance
    	def budgetDetailList
    	def detailsInstance
    	def accountHeadInstanceList=[]
    	def budgetDetInstanceList=[]
    	def budgetDetailsInstanceList
    	def budgetMasterInstanceList=[]
    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	def budgetModMapInstance = budgetMasterService.getmodulTypeIdByModuleType(budgetId)
    	for(int i=0;i<budgetModMapInstance.size();i++)
		{
    		budgetMasterInstance = budgetMasterService.getMasterIdByModuleMap(budgetModMapInstance[i].budgetMaster.id)
    		for(int j=0;j<budgetMasterInstance.size();j++)
			{
	    		masterInstance = budgetMasterInstance[j]
	    		budgetMasterInstanceList.add(masterInstance)
			}
		}
    	for(int i=0;i<budgetModMapInstance.size();i++)
		{
    		budgetDetailsInstanceList = budgetMasterService.getBudgetDetailsByMaasterId(budgetModMapInstance[i].budgetMaster.id)
    		for(int j=0;j<budgetDetailsInstanceList.size();j++)
			{
	    		detailsInstance = budgetDetailsInstanceList[j]
	    		budgetDetInstanceList.add(detailsInstance)
			}
		}
    	[budgetModMapInstance:budgetModMapInstance,budgetMasterInstanceList:budgetMasterInstanceList,budgetDetailsInstanceList:budgetDetailsInstanceList,budgetDetInstanceList:budgetDetInstanceList,'currencyFormat':currencyFormatter]
    }
}
