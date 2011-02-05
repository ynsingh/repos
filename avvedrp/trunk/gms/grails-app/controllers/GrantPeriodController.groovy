import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class GrantPeriodController 
{
    
	def index ={}

	// the delete, save and update actions only accept POST requests
	def allowedMethods = [delete:'POST', save:'POST', update:'POST']

	/**
	 * Method to Perform the create action
	 */ 
	def create = 
    {
        def grantPeriodInstance = new GrantPeriod()
    	def grantPeriodService = new GrantPeriodService()
		GrailsHttpSession gh=getSession()
        gh.removeValue("Help")
    	
        /*putting help pages in session*/
    	gh.putValue("Help","Create_Grant_Period.htm")	
        grantPeriodInstance.properties = params
       
        /*Getting all active grant periods*/
        def grantPeriodInstanceList = grantPeriodService.getAllGrantPeriods()
        
        return ['grantPeriodInstance':grantPeriodInstance,
                'grantPeriodInstanceList': grantPeriodInstanceList]
    }
    
    /**
	 * Method to Perform the save action
	 */
    def save = 
    {
        def grantPeriodInstance = new GrantPeriod(params)
        def grantPeriodService = new GrantPeriodService()
        if(!grantPeriodInstance.hasErrors()) 
        {
	    	 /* getting the list of default grant period */
	         def chkDefaultGrantPeriodInstance=grantPeriodService.getDefaultGrantPeriod()
	         
	         /*Getting grant period details based on the name*/
	         def grantPeriodDuplicateInstance = grantPeriodService.getGrantPeriod(grantPeriodInstance)
	         /* check whether grant period exists with name entered */
	         if(grantPeriodDuplicateInstance)
	         {
	        	 /* Shows the following message if the grant period name exists. */
	        	 flash.message = "${message(code: 'default.AlreadyExists.label')}"
		         redirect(action:create,id:grantPeriodInstance.id)
	         }
	         else
	         {
	        	 /*Checking if any default grant period exists and the grant period entered as default*/
	        	 if(grantPeriodInstance.defaultYesNo=='Y' && chkDefaultGrantPeriodInstance.size()>0)
		         {
		        	 /* Shows the following message if any default grant period occurs. */
		        	 flash.message = "${message(code: 'default.Defaultgrantperiodmustunique.label')}"
		        	 redirect(action:create,id:grantPeriodInstance.id)
		         }
	        	 else
	        	 {
		        		/* Save grant period details*/
		        		grantPeriodInstance = grantPeriodService.saveGrantPeriod(grantPeriodInstance)
		        		if(grantPeriodInstance.saveMode != null)
		        		{
		        			if(grantPeriodInstance.saveMode.equals("Saved"))/*Checking whether the grant period details are saved*/
		        			{
		        				flash.message = "${message(code: 'default.created.label')}"
		        				redirect(action:create,id:grantPeriodInstance.id)
		        			}
		        		}
		        		else 
		        		{
		        			render(view:'create',model:[grantPeriodInstance:grantPeriodInstance])
		        		}
	        	 	}
	         	}
	        }     
    }
    
    /**
	 * Method to Perform the edit action
	 */   
    def edit =
    {
		def grantPeriodService = new GrantPeriodService()
		
		/* Getting grant period details based on id */
		def grantPeriodInstance = grantPeriodService.getGrantPeriodById(new Integer( params.id ))
        if(!grantPeriodInstance)
        {
          flash.message = "${message(code: 'default.notfond.label')}"
          redirect(action:list)
        }
        else
          return [ grantPeriodInstance : grantPeriodInstance ]
    }
    
    /**
	 * Method to Perform the update action
	 */

	def update =
	{
		def grantPeriodInstance = new GrantPeriod(params)
		def grantPeriodService = new GrantPeriodService()
	
		
		/* getting the list of default grant period */
		def chkDefaultGrantPeriodInstance=grantPeriodService.getDefaultGrantPeriod()
		/* check whether any grant period is default */
		if(chkDefaultGrantPeriodInstance)
		{
			/*Should not allow user to set the grant period as default if any default grant period exists */ 
			if((chkDefaultGrantPeriodInstance[0].id != Long.parseLong(params.id)) 
    	    		&& (chkDefaultGrantPeriodInstance.size()>=1) 
    	    		&& (grantPeriodInstance.defaultYesNo == 'Y'))
			{
    	    	 /* Shows the following message if any default grant period occurs. */
    	    	 flash.message = "${message(code: 'default.Defaultgrantperiodmustunique.label')}"
    		     redirect(action:edit,id:params.id)
			}
			else
				updateGrantPeriod(grantPeriodInstance,params)//updating grant period
		}
		
		/* if no default grant period exists */
		else
		{
			/*Should not allow user to set the grant period as default if any default grant period exists */ 
			if((chkDefaultGrantPeriodInstance.size()>=1) && (grantPeriodInstance.defaultYesNo == 'Y'))
			{
				flash.message = "${message(code: 'default.Defaultgrantperiodmustunique.label')}"
				redirect(action:edit,id:params.id)
			}
			else
				updateGrantPeriod(grantPeriodInstance,params)//updating grant period
		}
		}

		
		
	
	/*Update grant period*/
	public updateGrantPeriod(def grantPeriodInstance,params)
	{
		def grantPeriodService = new GrantPeriodService()
		
		def grantPeriodDuplicateInstance = grantPeriodService.getGrantPeriod(grantPeriodInstance)
		
		 if(grantPeriodDuplicateInstance && (grantPeriodDuplicateInstance.id != Long.parseLong(params.id)))
		 {
				 flash.message = "${message(code: 'default.AlreadyExists.label')}"
			         redirect(action:create,id:grantPeriodInstance.id)
		 }
		 else
		 {
        
        
		/* Updating Grant Period details*/
		grantPeriodInstance = grantPeriodService.updateGrantPeriod(params)
		/* Check whether any grant period exists */
		if(grantPeriodInstance)
		{
			
	         
	      if(grantPeriodInstance.saveMode != null)
			{
				/* Check whether the grant period details are updated */
				if(grantPeriodInstance.saveMode.equals("Updated"))
				{
					/* Shows a message if the details are updated */
					flash.message = "${message(code: 'default.updated.label')}"
					redirect(action:create,id:grantPeriodInstance.id)
				}
				else 
				{
					render(view:'edit',model:[grantPeriodInstance:grantPeriodInstance])
				}
			}
	        
			else
			{
				flash.message = "${message(code: 'default.notfond.label')}"
				redirect(action:edit,id:params.id)
			}
	      }
		}
		}

	
    /**
	 * Method to Perform the delete action
	 */
    def delete = 
    {
		def grantPeriodService = new GrantPeriodService()
		/* Delete grant period details */
		Integer grantPeriodId = grantPeriodService.deleteGrantPeriod(params)
		if (grantPeriodId==0)
		{
			flash.message = "${message(code: 'default.usedinProjects.label')}"        	
			redirect(action:edit,id:params.id)
		}
		else
		{	
			if(grantPeriodId != null)
			{
				flash.message = "${message(code: 'default.deleted.label')}"
        		redirect(action:create)
			}
			else 
			{
	            flash.message = "${message(code: 'default.notfond.label')}"
	            redirect(action:create)
	        }
		}
    }   
}
