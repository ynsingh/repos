import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class AccountHeadsController
{
    
    def index = {}

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    /**
	 * Method to Perform the create action
	 */
	 
    def create = 
    {
    	def accountHeadsInstance = new AccountHeads()
        def accountHeadsService = new AccountHeadsService()	
    	GrailsHttpSession gh=getSession()	
    	gh.removeValue("Help")
    	
		//putting help pages in session
		gh.putValue("Help","Create_Account_Head.htm")
		accountHeadsInstance.properties = params
        String subQuery ="";
        if(params.sort != null && !params.sort.equals(""))
        	subQuery=" order by AH."+params.sort
        if(params.order != null && !params.order.equals(""))
        	subQuery =subQuery+" "+params.order
        	
        // Getting all Active Account Heads
       	def accountHeadsInstanceList = accountHeadsService.getActiveAccountHeads(subQuery)

        return ['accountHeadsInstance':accountHeadsInstance,
                'accountHeadsInstanceList': accountHeadsInstanceList]
    }
    
    /**
	 * Method to perform the save action
	 */

    def save = 
    {
       def accountHeadsInstance = new AccountHeads(params)
        def accountHeadsService = new AccountHeadsService()
        if(!accountHeadsInstance.hasErrors() ) 
        {
        	accountHeadsInstance = accountHeadsService.saveAccountHeads(accountHeadsInstance)
        	if(accountHeadsInstance.saveMode != null)
        	{
        		/*Checking whether the account head details are saved*/
        		if(accountHeadsInstance.saveMode.equals("Saved"))
        		{
        			flash.message = "${message(code: 'default.created.label')}"
    				if(accountHeadsInstance.parent !=null)
					{
						redirect(action:showSubAccountHeads,id:accountHeadsInstance.parent.id)
					}
					else	
					{
						redirect(action:create,id:accountHeadsInstance.id)
					}
        		}
        		/*Checking whether the account head with same name alresdy exists*/
        		else if(accountHeadsInstance.saveMode.equals("Duplicate"))
        		{
       				if(accountHeadsInstance.parent !=null)
					{
    					flash.message = "${message(code: 'default.AlreadyExists.label')}"
    					redirect(action:showSubAccountHeads,id:accountHeadsInstance.parent.id)
					}
    				else
    				{
    					flash.message = "${message(code: 'default.AlreadyExists.label')}"
    					redirect(action:create,id:accountHeadsInstance.id)
    				}
        		}
        	}
    				else
    				{
    					render(view:'create',model:[accountHeadsInstance:accountHeadsInstance])
    				}
        		}
        	}
    
    /**
	 * Method for performing the edit action
	 */
 
    def edit = 
    {
		def accountHeadsService = new AccountHeadsService()
        def accountHeadsInstance
        
        /*Get Account Head details based on id*/
        if(params.id)
        {
        	accountHeadsInstance = accountHeadsService.getAccountHeadsById(new Integer( params.id ))
        }
		/*Check whether the record exits or not*/
        if(!accountHeadsInstance) 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:create)
        }
        else 
        {
            return [ accountHeadsInstance : accountHeadsInstance ]
        }
    }
    
    /**
	 * Method for performing the update action
	 */

    def update = 
    {
		def accountHeadsService = new AccountHeadsService()
		
		/* Update the Account Heads details */
		def accountHeadsInstance = accountHeadsService.updateAccountHeads(params)
		
		/* Check whether any account head exists */
		if(accountHeadsInstance)
		{
			if(accountHeadsInstance.saveMode != null)
			{
				/* Check whether the account head details are updated */
				if(accountHeadsInstance.saveMode.equals( "Updated"))
				{
					/*Shows a message if the details are updated*/
					flash.message = "${message(code: 'default.updated.label')}"
					if(accountHeadsInstance.parent !=null)
					{
						redirect(action:showSubAccountHeads,id:accountHeadsInstance.parent.id)
					}
					else	
					{
						redirect(action:create,id:accountHeadsInstance.id)
					}
				}
				else if(accountHeadsInstance.saveMode.equals( "Duplicate"))
				{
					/* Shows the following message if any account head exists with the given name. */
					flash.message = "${message(code: 'default.AlreadyExists.label')}"
	    	    	render(view:'edit',model:[accountHeadsInstance:accountHeadsInstance])
				}
			}
			else
			{
				render(view:'edit',model:[accountHeadsInstance:accountHeadsInstance])
			}
		}
		else 
		{
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
        }
    }

        	
    /**
	 * Method for performing the delete action
	 */
	 
    def delete = 
    {
			def accountHeadsInstance = new AccountHeads()
    		def accountHeadsService = new AccountHeadsService()
            accountHeadsInstance.properties = params
            Integer accountHeadId = null
            def accountHeadInBudgetDetail =BudgetDetails.find("from BudgetDetails BD where BD.accountHeads.id="+params.id)
			if(accountHeadInBudgetDetail)
			{
				flash.message = "${message(code: 'default.usedinAllocated.label')}"
					 redirect(action:edit,id:params.id)
			}
			else
			{
            /* Delete the account head details */
    		accountHeadId = accountHeadsService.deleteAccountHeads(params)
			if (accountHeadId == 0)
			{
				/* Shows the following message if the account head is already in use. */
				 flash.message = "${message(code: 'default.usedinProjects.label')}"
				 redirect(action:edit,id:params.id)
			}
			else
			{
				
				if(accountHeadId != null)
				{				
					flash.message = "${message(code: 'default.deleted.label')}"			
					if(accountHeadsInstance.parent !=null)
						{					
						   redirect(action:create,id:accountHeadsInstance.parent.id)
						}
						else
						{		    		
						   redirect(action:create,id:accountHeadsInstance.id)
						}
				}    
				else 
				{
		            flash.message = "${message(code: 'default.notbedeletedAccountHead.label')}"
		            redirect(action:edit,id:params.id)
		        }
			}
			}
    }
    
    /**
	 * Method for listing sub account heads
	 */
    
        
      def showSubAccountHeads =
    {
		def accountHeadsService = new AccountHeadsService()
        def accountHeadsInstance = new AccountHeads()
		
       /* Get all active Account Heads */
		def accountHeadsList=accountHeadsService.getAccountHeadsById(new Integer(params.id))
		accountHeadsInstance.parent=accountHeadsList
		accountHeadsInstance.properties = params
		
		/* Get all active Sub Account Heads */
		def accountHeadsInstanceList=accountHeadsService.getSubAccountHeads(new Integer(params.id))
        return ['accountHeadsInstance':accountHeadsInstance,'accountHeadsInstanceList':accountHeadsInstanceList]
    
    }
   
}
