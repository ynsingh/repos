import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.*;
import java.util.*;
class FundAdvanceController extends GmsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def userService = new UserService()
    def grantAllocationSplitService 
    
    def create = {
    	GrailsHttpSession gh=getSession()
	gh.removeValue("Help")
	gh.putValue("Help","FundAdvance.htm")//putting help pages in session
    	def grantAllocationService = new GrantAllocationService()
        def fundAdvanceInstance = new FundAdvance()
    	def grantExpenseService = new GrantExpenseService()
    	def grantAllocationInstanceList
    	def projectInstance
    	def advanceFundList = []
        fundAdvanceInstance.properties = params
        if(params.id){
    		grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(params.id)
	        projectInstance = Projects.get(params.id)
	        projectInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectInstance.id,getUserPartyID())
	   	    advanceFundList = grantExpenseService.getAdvanceFundListByGrantAllocation(grantAllocationInstanceList)
	   	   
        }
    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
        return [fundAdvanceInstance: fundAdvanceInstance,'grantAllocationInstanceList':grantAllocationInstanceList,
                projectInstance:projectInstance,'currencyFormat':currencyFormatter,'amount':formatter.format(fundAdvanceInstance.advanceAmount),
                advanceFundList:advanceFundList]
    }

    def save = {
    	GrailsHttpSession gh=getSession()
        def dataSecurityService = new DataSecurityService()
    	def grantAllocationService = new GrantAllocationService()
    	def projectsService = new ProjectsService()
    	def grantExpenseService = new GrantExpenseService()
    	def projectsList = dataSecurityService.getProjectsForLoginUser(gh.getValue("PartyID"))
    	def userInstance = userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
    	def projectInstance
    	def grantAllocationInstanceList
    	def advanceFundList = []
    	
    	params.createdBy = userInstance.username
		params.createdDate = new Date()
    	def fundAdvanceInstance = new FundAdvance(params)
    	def cal = Calendar.instance				
    	def year = cal.get(Calendar.YEAR)
    	def month = cal.get(Calendar.MONTH)+ 1
    	def date = cal.get(Calendar.DATE)
    	def dat =year.toString() + month.toString() + date.toString() +
    	cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND)
    	fundAdvanceInstance.fundAdvanceCode = params.projectCode + dat + userInstance.id  // generate Fund Advance code
    	projectInstance = projectsService.getProjectInstanceByCode(params.projectCode)
    	grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(projectInstance.id)
        projectInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectInstance.id,getUserPartyID())
   	    
   	    ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	def grantAllcnInstance = GrantAllocation.get(params.grantAllocation.id)
    	def totalExpense = grantExpenseService.getTotalExpenseByGrantAllocation(grantAllcnInstance)
    	def totalAdvance = grantExpenseService.getTotalAdvanceByGrantAllocation(grantAllcnInstance)
    	
    	if(grantAllcnInstance.amountAllocated < (totalExpense+totalAdvance+fundAdvanceInstance.advanceAmount)){
    		flash.error = "${message(code: 'default.NotSufficientFund.message')}"
    		redirect(action: "create",id:projectInstance.id)
    	}
    	else{    	
	        if (fundAdvanceInstance.save(flush: true)) {
	        	advanceFundList = grantExpenseService.getAdvanceFundListByGrantAllocation(grantAllocationInstanceList)
	        	flash.message = "${message(code: 'default.created.label')}"
	        		redirect(action: "create",id:projectInstance.id)
	        }
	        else {
	            render(view: "create", model: [fundAdvanceInstance: fundAdvanceInstance,'currencyFormat':currencyFormatter,
	                               		    'amount':formatter.format(fundAdvanceInstance.advanceAmount),
	                                		'grantAllocationInstanceList':grantAllocationInstanceList,projectInstance:projectInstance,advanceFundList:advanceFundList])
	        }
    	}
    }

    def edit = {
    	def fundAdvanceInstance = FundAdvance.get(params.id)
        def grantAllocationService = new GrantAllocationService()
        def projectsInstance = Projects.get(fundAdvanceInstance.grantAllocation.projects.id)
        projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
	   	def grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(projectsInstance.id)  
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
        if (!fundAdvanceInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create", id: projectsInstance.id)
        }
        else {
            return [fundAdvanceInstance: fundAdvanceInstance,projectsInstance: projectsInstance,
                    'currencyFormat':currencyFormatter,'amount':formatter.format(fundAdvanceInstance.advanceAmount),
                    grantAllocationInstanceList:grantAllocationInstanceList]
        }
    }

    def update = {
    	GrailsHttpSession gh=getSession()
    	def grantExpenseService = new GrantExpenseService()
    	def userInstance = userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
    	def fundAdvanceInstance = FundAdvance.get(params.id)
    	def projectsInstance = Projects.get(fundAdvanceInstance.grantAllocation.projects.id)
        fundAdvanceInstance.modifiedBy = userInstance.username
        fundAdvanceInstance.modifiedDate = new Date()
    	 if (fundAdvanceInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (fundAdvanceInstance.version > version) {
                    
                    fundAdvanceInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'fundAdvance.label', default: 'FundAdvance')] as Object[], "Another user has updated this FundAdvance while you were editing")
                    render(view: "edit", model: [fundAdvanceInstance: fundAdvanceInstance])
                    return
                }
            }
            double advAmt = fundAdvanceInstance.advanceAmount
            double newAdvAmt = new Double(params.advanceAmount).doubleValue()
            def grantAllcnInstance = GrantAllocation.get(params.grantAllocation.id)
        	def totalExpense = grantExpenseService.getTotalExpenseByGrantAllocation(grantAllcnInstance)
        	def totalAdvance = grantExpenseService.getTotalAdvanceByGrantAllocation(grantAllcnInstance)
        	
        	if(grantAllcnInstance.id == fundAdvanceInstance.grantAllocation.id)
        	{
         		if(grantAllcnInstance.amountAllocated < ((totalExpense+totalAdvance+newAdvAmt)-advAmt)){
            		flash.error = "${message(code: 'default.NotSufficientFund.message')}"
            		redirect(action: "create", id: projectsInstance.id)
            	}
        		else
        		{
        			def advanceExpenseEntry = grantExpenseService.getGrantExpenseByAdvanseCode(fundAdvanceInstance)
                	if(advanceExpenseEntry)
                    {
                    	flash.message = "${message(code: 'default.AsExpenseisEnteredcantUpdateFundAdvance.message')}"
                    	redirect(action: "create", id: projectsInstance.id)
                    }
                    else
                    {
            		fundAdvanceInstance.properties = params
                    if (!fundAdvanceInstance.hasErrors() && fundAdvanceInstance.save(flush: true)) {
    	                flash.message = "${message(code: 'default.updated.label')}"
    	                redirect(action: "create", id: projectsInstance.id)
    	            }
    	            else {
    	                render(view: "edit", model: [fundAdvanceInstance: fundAdvanceInstance])
    	            }
            	    }
        		}
        	}
        	
        	else
        	{
         		if(grantAllcnInstance.amountAllocated < (totalExpense+totalAdvance+newAdvAmt)){
            		flash.error = "${message(code: 'default.NotSufficientFund.message')}"
            		redirect(action: "create", id: projectsInstance.id)
            	}
        		else
        		{
        		
	        		def advanceExpenseEntry = grantExpenseService.getGrantExpenseByAdvanseCode(fundAdvanceInstance)
	            	if(advanceExpenseEntry)
	                {
	                	flash.message = "${message(code: 'default.AsExpenseisEnteredcantUpdateFundAdvance.message')}"
	                	redirect(action: "create", id: projectsInstance.id)
	                }
	                else
	                {
	        		fundAdvanceInstance.properties = params
	                if (!fundAdvanceInstance.hasErrors() && fundAdvanceInstance.save(flush: true)) {
		                flash.message = "${message(code: 'default.updated.label')}"
		                redirect(action: "create", id: projectsInstance.id)
		            }
		            else {
		                render(view: "edit", model: [fundAdvanceInstance: fundAdvanceInstance])
		            }
	        	    }
        	  }
        	}
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create", id: projectsInstance.id)
        }
    }

    def delete = {
        def fundAdvanceInstance = FundAdvance.get(params.id)
        def grantExpenseService = new GrantExpenseService()
        def projectsInstance = Projects.get(fundAdvanceInstance.grantAllocation.projects.id)
        def grantExpenseInstanceList = grantExpenseService.getExpenseListAgainstFundAdvance(fundAdvanceInstance)
	    if(grantExpenseInstanceList) {
	    	flash.message = "${message(code: 'default.inuse.label')}"
	    	redirect(action: "create", id: projectsInstance.id)
	    }
	    else{
        	if (fundAdvanceInstance) {
	            try {
	                fundAdvanceInstance.delete(flush: true)
	                flash.message = "${message(code: 'default.deleted.label')}"
	                redirect(action: "create", id: projectsInstance.id)
	            }
	            catch (org.springframework.dao.DataIntegrityViolationException e) {
	                flash.message = "${message(code: 'default.inuse.label')}"
	                redirect(action: "edit", id: FundAdvance.id)
	            }
	        }
	        else {
	            flash.message = "${message(code: 'default.notfond.label')}"
	            redirect(action: "create", id: projectsInstance.id)
	        }
	    }
    }

    def expenseDetails = {
    	GrailsHttpSession gh=getSession()
	gh.removeValue("Help")
	gh.putValue("Help","Grant_Expense.htm")//putting help pages in session
    	def fundAdvanceInstance = FundAdvance.get(params.id)
    	def grantAllocationSplitService=new GrantAllocationSplitService()
    	def grantExpenseInstance = new GrantExpense()
    	def projectsService = new ProjectsService() 
    	def grantExpenseService = new GrantExpenseService()
    	def fundAdvanceWithBalanceInstance = grantExpenseService.getBalanceAmount(fundAdvanceInstance)
    	def projectsInstance = Projects.get(fundAdvanceInstance.grantAllocation.projects.id)
    	def accountHeadList=grantAllocationSplitService.getAccountHeadByProject(projectsInstance.id)  // account head listing based on default grant period
    	def grantExpenseInstanceList = grantExpenseService.getExpenseListAgainstFundAdvance(fundAdvanceInstance)
    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	return [fundAdvanceWithBalanceInstance: fundAdvanceWithBalanceInstance,projectsInstance:projectsInstance,grantExpenseInstance:grantExpenseInstance,
    	        'currencyFormat':currencyFormatter,'amount':formatter.format(grantExpenseInstance.expenseAmount),accountHeadList:accountHeadList,
    	        fundAdvanceInstance:fundAdvanceInstance,grantExpenseInstanceList:grantExpenseInstanceList]
    }
    
    def saveExpenseDetails = {
    	
    	GrailsHttpSession gh=getSession()
		def grantExpenseService = new GrantExpenseService()
		def userInstance = Person.get(gh.getValue("UserId"))
		params.createdBy=userInstance.username
		params.createdDate = new Date()
    	def grantExpenseInstance = new GrantExpense(params)
    	def grantAllocationInstance = GrantAllocation.get(params.grantAllnId) 
    	def projectsInstance = Projects.get(params.projectId)
    	def fundAdvanceInstance = FundAdvance.get(params.fundAdvanceId)
    	def grantAllocationSplitInstance = grantAllocationSplitService.getGrantAllocationSplitById(new Integer(params.grantAllocationSplit.id))
        grantExpenseInstance.grantAllocation = grantAllocationInstance
    	grantExpenseInstance.projects= projectsInstance
    	grantExpenseInstance.fundAdvanceCode = fundAdvanceInstance.fundAdvanceCode
    	double totalExpense = grantExpenseService.getTotalExpense(fundAdvanceInstance.fundAdvanceCode)
    	double totalExpenseForAnAccthd = grantExpenseService.getTotalExpenseForAnAccthd(grantAllocationSplitInstance)  		
    	      		   
    	if(fundAdvanceInstance.advanceAmount < (totalExpense+grantExpenseInstance.expenseAmount))
    	{
    		flash.error = "${message(code: 'default.NotSufficientFund.message')}"
    		redirect(action: "expenseDetails", id: fundAdvanceInstance.id)
    	}
    	else
    	{  
	    	
    		if(grantAllocationSplitInstance.amount < (totalExpenseForAnAccthd+grantExpenseInstance.expenseAmount))
	    	{
	    		flash.error = "${message(code: 'default.NotSufficientFundForAccoundHead.message')}"
    		    redirect(action: "expenseDetails", id: fundAdvanceInstance.id)
    	    }
	    	 else
	    	   {
	    		
	    		 def utilizationInstance = Utilization.findAll("from Utilization U where U.projects="+grantExpenseInstance.projects.id)
	    	    	if(utilizationInstance)
	    				   {
	    	    			   Date temp;
	    					   Date attr;
	    					   for(int i=0;i<utilizationInstance.size(); i++)
	    					   {
	    						  
	    						   temp = utilizationInstance[i].startDate
	    						   for (int j=1;j<utilizationInstance.size();j++ )
	    						   {
	    						   if(utilizationInstance[i].startDate > utilizationInstance[j].startDate)
	    						   {
	    							   temp = utilizationInstance[j].startDate
	    						   }
	    						   }
	    					   }
	    					   
	    					   for(int j=0;j<utilizationInstance.size(); j++)
	    					   {
	    						   
	    						   attr = utilizationInstance[j].endDate
	    						   for (int k=1;k<utilizationInstance.size();k++ )
	    						   {
	    							   if(utilizationInstance[j].endDate < utilizationInstance[k].endDate)
	    							   {
	    								   attr = utilizationInstance[k].endDate
	    							   }
	    						   }
	    					   }
	    						   SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy");
	    						   String strDate = sdfDestination.format(temp);
	    					       String edDate = sdfDestination.format(attr);
	    					       DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    					       Date strtingDate = df.parse(strDate)
	    					       Date endingDate = df.parse(edDate)
	    					       Date expenseDate = df.parse(params.dateOfExpense_value)
	    					        
	    					   if(( expenseDate >= strtingDate) && (endingDate >= expenseDate) )
	    					   {
	    						 	  flash.error="${message(code: 'default.expenseUtilizationCertificate.label')}"
	    						 	  redirect(action:create,id:grantExpenseInstance.projects.id,params:[expenseAmount:grantExpenseInstance.expenseAmount,
	    					    			                                     			                   grantAllocationId:grantExpenseInstance.grantAllocation.id,accountHeadId:grantExpenseInstance.grantAllocationSplit.id,
	    					    			                                     			                   modeOfPayment:grantExpenseInstance.modeOfPayment,ddNo:grantExpenseInstance.ddNo,
	    					    			                                     			                   bankName:grantExpenseInstance.bankName,ddBranch:grantExpenseInstance.ddBranch,
	    					    			                                     			                   description:grantExpenseInstance.description])
	    						     }
	    					   else
	    					   {
	    							if (grantExpenseInstance.save(flush: true)) {
	    					    		flash.message = "${message(code: 'default.created.label')}"
	    					    		redirect(action: "expenseDetails", id: fundAdvanceInstance.id)
	    					    		
	    					    	}
	    						}
	    				   }
	    				  
	    		    	else
	    		    	{
	    		    		if (grantExpenseInstance.save(flush: true)) 
	    			    		{
	    				    		flash.message = "${message(code: 'default.created.label')}"
	    				    		redirect(action: "expenseDetails", id: fundAdvanceInstance.id)
	    			    	    }
	    		    	}
	
	    		
	    	   }
    	}	
    	
    		       	
    
    }
    
    def editExpenseDetails = {
		def grantExpenseService = new GrantExpenseService()
		def grantAllocationSplitService=new GrantAllocationSplitService()
		def grantExpenseInstance = GrantExpense.get(params.id)
		def fundAdvanceInstance = grantExpenseService.getFundAdvanceByCode(grantExpenseInstance)
		def projectsInstance = Projects.get(fundAdvanceInstance.grantAllocation.projects.id)
		def fundAdvanceWithBalanceInstance = grantExpenseService.getBalanceAmount(fundAdvanceInstance)
		def accountHeadList=grantAllocationSplitService.getAccountHeadByProject(projectsInstance.id)  // account head listing based on default grant period
		ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	[fundAdvanceWithBalanceInstance: fundAdvanceWithBalanceInstance,projectsInstance:projectsInstance,grantExpenseInstance:grantExpenseInstance,
	        'currencyFormat':currencyFormatter,'amount':formatter.format(grantExpenseInstance.expenseAmount),accountHeadList:accountHeadList,
	        fundAdvanceInstance:fundAdvanceInstance]
    }
    
    def updateExpenseDetails = {
    	GrailsHttpSession gh=getSession()
		def grantExpenseService = new GrantExpenseService()
    	def fundAdvanceInstance = FundAdvance.get(params.fundAdvanceId)
    	def grantExpenseInstance = GrantExpense.get(params.grantExpenseId)
		def userInstance = Person.get(gh.getValue("UserId"))
		def grantAllocationSplitInstance = grantAllocationSplitService.getGrantAllocationSplitById(new Integer(params.grantAllocationSplit.id))
		params.modifiedBy=userInstance.username
		params.modifiedDate = new Date()
    	double expAmt = grantExpenseInstance.expenseAmount
    	double newExpAmt = new Double(params.expenseAmount).doubleValue()
    	double totalExpense = grantExpenseService.getTotalExpense(grantExpenseInstance.fundAdvanceCode)
    	double totalExpenseForAnAccthd = grantExpenseService.getTotalExpenseForAnAccthd(grantAllocationSplitInstance)  		
    	if(fundAdvanceInstance.advanceAmount < ((totalExpense+newExpAmt)-expAmt))
    	{
    		flash.error = "${message(code: 'default.NotSufficientFund.message')}"
    		redirect(action: "editExpenseDetails", id: grantExpenseInstance.id)
    	}
    	else
    	{
	    	if(grantAllocationSplitInstance.id == grantExpenseInstance.grantAllocationSplit.id)
    		   {
	    		if(grantAllocationSplitInstance.amount < ((totalExpenseForAnAccthd+newExpAmt)-expAmt))
		    	{
		    		flash.error = "${message(code: 'default.NotSufficientFundForAccoundHead.message')}"
		        	redirect(action: "editExpenseDetails", id: grantExpenseInstance.id)
		    	}
		    	else
		    	{
		    		def utilizationInstance = Utilization.findAll("from Utilization U where U.projects="+grantExpenseInstance.projects.id)
		  		   if(utilizationInstance)
		 			   {
		 				   Date temp;
		 				   Date attr;
		 				   for(int i=0;i<utilizationInstance.size(); i++)
		 				   {
		 					  
		 					   temp = utilizationInstance[i].startDate
		 					   for (int j=1;j<utilizationInstance.size();j++ )
		 					   {
		 					   if(utilizationInstance[i].startDate > utilizationInstance[j].startDate)
		 					   {
		 						   temp = utilizationInstance[j].startDate
		 					   }
		 					   }
		 				   }
		 				   
		 				   for(int j=0;j<utilizationInstance.size(); j++)
		 				   {
		 					   
		 					   attr = utilizationInstance[j].endDate
		 					   for (int k=1;k<utilizationInstance.size();k++ )
		 					   {
		 					   if(utilizationInstance[j].endDate < utilizationInstance[k].endDate)
		 					   {
		 						   attr = utilizationInstance[k].endDate
		 					   }
		 					   }
		 				   }
		 					   SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy");
		 				       String strDate = sdfDestination.format(temp);
		 			           String edDate = sdfDestination.format(attr);
		 			           DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		 			           Date strtingDate = df.parse(strDate)
		 			           Date endingDate = df.parse(edDate)
		 			           Date expenseDate = df.parse(params.dateOfExpense_value)
		 				        
		 				   if(( expenseDate >= strtingDate) && (endingDate >= expenseDate) )
		 				   {
		 					 	  flash.error="${message(code: 'default.expenseUpdateUtilizationCertificate.label')}"
		 					 	  redirect(action:create,id:grantExpenseInstance.projects.id,params:[expenseAmount:grantExpenseInstance.expenseAmount,
		 				    			                                     			                   grantAllocationId:grantExpenseInstance.grantAllocation.id,accountHeadId:grantExpenseInstance.grantAllocationSplit.id,
		 				    			                                     			                   modeOfPayment:grantExpenseInstance.modeOfPayment,ddNo:grantExpenseInstance.ddNo,
		 				    			                                     			                   bankName:grantExpenseInstance.bankName,ddBranch:grantExpenseInstance.ddBranch,
		 				    			                                     			                   description:grantExpenseInstance.description])
		 				   }
		 				   else
		 		 		   {
		 			    		grantExpenseInstance.properties = params
		 			        	if(grantExpenseInstance.save(flush: true)) {
		 				    		flash.message = "${message(code: 'default.updated.label')}"
		 				            redirect(action: "expenseDetails", id: fundAdvanceInstance.id)	
		 				    	}
		 		 		        else{
		 			    		redirect(action: "expenseDetails", id: fundAdvanceInstance.id)	
		 			    	}
		 		 		  }
		 			   }
		  		   else
		  		   {
		 	    		grantExpenseInstance.properties = params
		 	        	if(grantExpenseInstance.save(flush: true)) {
		 		    		flash.message = "${message(code: 'default.updated.label')}"
		 		            redirect(action: "expenseDetails", id: fundAdvanceInstance.id)	
		 		    	}
		  		        else{
		 	    		redirect(action: "expenseDetails", id: fundAdvanceInstance.id)	
		 	    	}
		  		  }
		    	}
    		  }
	    	else
	    	{
	    		if(grantAllocationSplitInstance.amount < (totalExpenseForAnAccthd+newExpAmt))
			    {
			    		flash.error = "${message(code: 'default.NotSufficientFundForAccoundHead.message')}"
			        	redirect(action: "expenseDetails", id: fundAdvanceInstance.id)
			    } 
	    		else
		    	{
				    		def utilizationInstance = Utilization.findAll("from Utilization U where U.projects="+grantExpenseInstance.projects.id)
				  		   if(utilizationInstance)
				 			   {
				 				   Date temp;
				 				   Date attr;
				 				   for(int i=0;i<utilizationInstance.size(); i++)
				 				   {
				 					  
				 					   temp = utilizationInstance[i].startDate
				 					   for (int j=1;j<utilizationInstance.size();j++ )
				 					   {
				 					   if(utilizationInstance[i].startDate > utilizationInstance[j].startDate)
				 					   {
				 						   temp = utilizationInstance[j].startDate
				 					   }
				 					   }
				 				   }
				 				   
				 				   for(int j=0;j<utilizationInstance.size(); j++)
				 				   {
				 					   
				 					   attr = utilizationInstance[j].endDate
				 					   for (int k=1;k<utilizationInstance.size();k++ )
				 					   {
				 					   if(utilizationInstance[j].endDate < utilizationInstance[k].endDate)
				 					   {
				 						   attr = utilizationInstance[k].endDate
				 					   }
				 					   }
				 				   }
				 					   SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy");
				 				       String strDate = sdfDestination.format(temp);
				 			           String edDate = sdfDestination.format(attr);
				 			           DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				 			           Date strtingDate = df.parse(strDate)
				 			           Date endingDate = df.parse(edDate)
				 			           Date expenseDate = df.parse(params.dateOfExpense_value)
				 				        
				 				   if(( expenseDate >= strtingDate) && (endingDate >= expenseDate) )
				 				   {
				 					 	  flash.error="${message(code: 'default.expenseUpdateUtilizationCertificate.label')}"
				 					 	  redirect(action:create,id:grantExpenseInstance.projects.id,params:[expenseAmount:grantExpenseInstance.expenseAmount,
				 				    			                                     			                   grantAllocationId:grantExpenseInstance.grantAllocation.id,accountHeadId:grantExpenseInstance.grantAllocationSplit.id,
				 				    			                                     			                   modeOfPayment:grantExpenseInstance.modeOfPayment,ddNo:grantExpenseInstance.ddNo,
				 				    			                                     			                   bankName:grantExpenseInstance.bankName,ddBranch:grantExpenseInstance.ddBranch,
				 				    			                                     			                   description:grantExpenseInstance.description])
				 				   }
				 				   else
				 		 		   {
				 			    		grantExpenseInstance.properties = params
				 			        	if(grantExpenseInstance.save(flush: true)) {
				 				    		flash.message = "${message(code: 'default.updated.label')}"
				 				            redirect(action: "expenseDetails", id: fundAdvanceInstance.id)	
				 				    	}
				 		 		        else{
				 			    		redirect(action: "expenseDetails", id: fundAdvanceInstance.id)	
				 			    	}
				 		 		  }
				 			   }
				  		   else
				  		   {
				 	    		grantExpenseInstance.properties = params
				 	        	if(grantExpenseInstance.save(flush: true)) {
				 		    		flash.message = "${message(code: 'default.updated.label')}"
				 		            redirect(action: "expenseDetails", id: fundAdvanceInstance.id)	
				 		    	}
				  		        else{
				 	    		redirect(action: "expenseDetails", id: fundAdvanceInstance.id)	
				 	    	}
				  		  }
		    		
		    	}
    		
    	}
    }
  }  
    def deleteExpenseDetails = {
    	def fundAdvanceInstance = FundAdvance.get(params.fundAdvanceId)
    	def grantExpenseInstance = GrantExpense.get(params.grantExpenseId)
    	if (grantExpenseInstance) {
    		def utilizationInstance = Utilization.findAll("from Utilization U where U.projects="+grantExpenseInstance.projects.id)
  		   if(utilizationInstance)
 			   {
 				   Date temp;
 				   Date attr;
 				   for(int i=0;i<utilizationInstance.size(); i++)
 				   {
 					  
 					   temp = utilizationInstance[i].startDate
 					   for (int j=1;j<utilizationInstance.size();j++ )
 					   {
 					   if(utilizationInstance[i].startDate > utilizationInstance[j].startDate)
 					   {
 						   temp = utilizationInstance[j].startDate
 					   }
 					   }
 				   }
 				   
 				   for(int j=0;j<utilizationInstance.size(); j++)
 				   {
 					   
 					   attr = utilizationInstance[j].endDate
 					   for (int k=1;k<utilizationInstance.size();k++ )
 					   {
 					   if(utilizationInstance[j].endDate < utilizationInstance[k].endDate)
 					   {
 						   attr = utilizationInstance[k].endDate
 					   }
 					   }
 				   }
 					   	  SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy");
 					      String strDate = sdfDestination.format(temp);
 				          String edDate = sdfDestination.format(attr);
 				          DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
 				          Date strtingDate = df.parse(strDate)
 				          Date endingDate = df.parse(edDate)
 				          Date expenseDate = df.parse(params.dateOfExpense_value)
 				        
 				   if(( expenseDate >= strtingDate) && (endingDate >= expenseDate) )
 				   {
 					 	  flash.error="${message(code: 'default.expenseDeleteUtilizationCertificate.label')}"
 					 	  redirect(action:create,id:grantExpenseInstance.projects.id,params:[expenseAmount:grantExpenseInstance.expenseAmount,
 			    			                                     			                   grantAllocationId:grantExpenseInstance.grantAllocation.id,accountHeadId:grantExpenseInstance.grantAllocationSplit.id,
 			    			                                     			                   modeOfPayment:grantExpenseInstance.modeOfPayment,ddNo:grantExpenseInstance.ddNo,
 			    			                                     			                   bankName:grantExpenseInstance.bankName,ddBranch:grantExpenseInstance.ddBranch,
 			    			                                     			                   description:grantExpenseInstance.description])
 				  	   }
 				  else
 		  		   {
 			            try {
 			            	grantExpenseInstance.delete(flush: true)
 			                flash.message = "${message(code: 'default.deleted.label')}"
 			                redirect(action: "expenseDetails", id: fundAdvanceInstance.id)
 			            }
 			            catch (org.springframework.dao.DataIntegrityViolationException e) {
 			                flash.message = "${message(code: 'default.inuse.label')}"
 			                redirect(action: "editExpenseDetails", id: grantExpenseInstance.id)
 			            }
 			        }
 			   }
  		   else
  		   {
	            try {
	            	grantExpenseInstance.delete(flush: true)
	                flash.message = "${message(code: 'default.deleted.label')}"
	                redirect(action: "expenseDetails", id: fundAdvanceInstance.id)
	            }
	            catch (org.springframework.dao.DataIntegrityViolationException e) {
	                flash.message = "${message(code: 'default.inuse.label')}"
	                redirect(action: "editExpenseDetails", id: grantExpenseInstance.id)
	            }
	        }
    	}
	        else {
	            flash.message = "${message(code: 'default.notfond.label')}"
	            redirect(action: "expenseDetails", id: fundAdvanceInstance.id)
	        }
    }
    
 def close = {
    	def grantExpenseService = new GrantExpenseService()
    	def fundAdvanceInstance = FundAdvance.get(params.id)
    	def totalExpense = grantExpenseService.getTotalExpense(fundAdvanceInstance.fundAdvanceCode)
    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	return [fundAdvanceInstance: fundAdvanceInstance,'totalExpense':totalExpense,'currencyFormat':currencyFormatter]
    }
    def closeAdvance = {
    	def fundAdvanceInstance = FundAdvance.get(params.advanceId)
    	fundAdvanceInstance.status = "Closed"
    	fundAdvanceInstance.save()
    	def projectsInstance = Projects.get(fundAdvanceInstance.grantAllocation.projects.id)
    	flash.message = "${message(code: 'default.advanceClosed.message')}"
        redirect(action: "create", id: projectsInstance.id)
    	
    }
}
