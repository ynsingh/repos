import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class PartyGrantAgencyController 
{
    def index = {}

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    /**
     * Method to perform delete action
     */
	def delete = 
    {
		def partyService = new PartyService()
		/*Delete Grant Agency details*/
		Integer partyId = partyService.deleteParty(new Integer(params.id))
		
		if(partyId != null)
		{
			flash.message = "${message(code: 'default.deleted.label')}"
			redirect(action:create)
		}
		else 
		{
            flash.message = "${message(code: 'default.couldnotdeleteGrantAgency.label')}"
            redirect(action:create)
        }
    }
    
    /**
     * Method to perform edit action
     */
    def edit = 
    {
    	def partyService = new PartyService()
    	/*Getting party details based on id*/
    	def partyInstance = partyService.getPartyById(new Integer(params.id ))
    	if(!partyInstance)
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:create)
        }
        else 
            return [ partyInstance : partyInstance ]
    }
    
    /**
     * Method to perform update action
     */
    def update =
    {
		def partyService = new PartyService()
		/*Updating Grant agency details*/
		def partyInstance = partyService.updateGrantAgency(params)
		
		if(partyInstance)
		{
			if(partyInstance.saveMode != null)
			{
				if(partyInstance.saveMode.equals("Updated"))/*Checking whether the grant agency details updated*/
				{
					flash.message = "${message(code: 'default.updated.label')}"
	                redirect(action:create,id:partyInstance.id)
				}
				else if(partyInstance.saveMode.equals("Duplicate"))/*Checking whether the party already exists*/
				{
					flash.message =  "${message(code: 'default.AlreadyExists.label')}"
	    	    	render(view:'edit',model:[partyInstance:partyInstance])
				}
			}
			else {
                render(view:'edit',model:[partyInstance:partyInstance])
            }
		}
		else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
        }
    }

    /**
     * Method to perform delete action
     */
    def create = 
    {
        def partyInstance = new Party()
        GrailsHttpSession gh=getSession()
       	gh.removeValue("Help")
   		//putting help pages in session
   		gh.putValue("Help","Create_Grant_Agency.htm")
	
        if(!params.max) params.max = 10
    	def partyService = new PartyService()
        /*Getting all active grant agencies*/
        def partyInstanceList = partyService.getActiveGrantAgency(params)
        return ['partyInstance':partyInstance,
                'partyInstanceList': partyInstanceList]
    }

    /**
     * Method to perform save action
     */
    def save = 
    {
        def partyInstance = new Party(params)
        if(!partyInstance.hasErrors() ) 
        {
       		def partyService = new PartyService()
           	partyInstance = partyService.saveGrantAgency(partyInstance)
           	
           	if(partyInstance.saveMode != null){
           		if(partyInstance.saveMode.equals("Saved")){
           			flash.message = "${message(code: 'default.created.label')}"
		            redirect(action:create,id:partyInstance.id)
           		}
           		else if(partyInstance.saveMode.equals("Duplicate")){
           			flash.message =  "${message(code: 'default.AlreadyExists.label')}"
           				redirect(action:create,id:partyInstance.id)
           		}
           	}
           	else {
                render(view:'create',model:[partyInstance:partyInstance])
            }
        }
        else {
            render(view:'create',model:[partyInstance:partyInstance])
        }
    }
    
    
}
