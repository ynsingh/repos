
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession


class PartyController  extends GmsController {
	
	
	def partyService 
	def userService 
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']
    
    /**
     * Method to perform list action
     */
    def list = 
    {
		GrailsHttpSession gh=getSession()
		/*Adding help page into session*/
		gh.putValue("Help","Institution_List.htm")
		def userService = new UserService()
    	def personRoleInstance = userService.getUserRoleByUserId(session.UserId)
		def partyInstanceList
		def activepartyInstanceList
		def dataSecurityService = new DataSecurityService()
		def partyService = new PartyService()
		
		/*Getting party details based on id*/
		partyInstanceList = partyService.getPartyBasedOnId(getUserPartyID())
		
		activepartyInstanceList = partyService.getAllActiveParties()
		[ partyInstanceList: partyInstanceList , activepartyInstanceList:activepartyInstanceList , personRoleInstance:personRoleInstance[0] ]
    }
    
    /**
     * Method to perform edit action
     */
    def edit = 
    {
		GrailsHttpSession gh=getSession()
		def partyService = new PartyService()
		def userService = new UserService()
    	def personRoleInstance = userService.getUserRoleByUserId(session.UserId)
    	def userMapInstance = userService.getSuperAdminUser(params.id)
		/*getting party details based on id*/
		def partyInstance = partyService.getPartyById(new Integer(params.id ))
        if(!partyInstance)/*Checking whether the party exists or not*/
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else 
        {
        	return [ partyInstance : partyInstance,personRoleInstance : personRoleInstance,userMapInstance:userMapInstance]
        }
    }

    /**
     * Method to perform update action
     */
    def update = 
    {
		def partyService = new PartyService()
		/*Updating party details*/
		def partyInstance = partyService.updateParty(params)
		if(partyInstance)
		{
			if(partyInstance.saveMode != null)
			{
				if(partyInstance.saveMode.equals("Updated"))/*checking whether the party details are updated*/
				{
					flash.message = "${message(code: 'default.updated.label')}"
	                redirect(action:list,id:partyInstance.id)
				}
				else if(partyInstance.saveMode.equals("Duplicate")) /*Checking whether the party already exists*/
				{
					flash.message = "${message(code: 'default.AlreadyExists.label')}"
	    	    	render(view:'edit',model:[partyInstance:partyInstance])
				}
			}
			else 
                render(view:'edit',model:[partyInstance:partyInstance])
		}
		else 
		{
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
        }
    		
    }

    /**
     * Method to perform the create action
     */
    def create = 
    {
    	def partyInstance = new Party()
        GrailsHttpSession gh=getSession()
        
        //Adding Help page into session
        gh.putValue("Help","Institution.htm")
        return ['partyInstance':partyInstance]
    }

    /**
     * Method to perform the save action
     */
    def save =
    {
        def partyInstance = new Party(params)
        if(!partyInstance.hasErrors() )
        {
          	def partyService = new PartyService()
          	/*Saving the party details*/
           	partyInstance = partyService.saveParty(partyInstance)
           	if(partyInstance.saveMode != null)
           	{
           		if(partyInstance.saveMode.equals("Saved"))/*checking whether the party is saved.*/
           		{
           			flash.message = "${message(code: 'default.created.label')}"
		            redirect(action:list,id:partyInstance.id)
           		}
           		else if(partyInstance.saveMode.equals("Duplicate"))/*Checking whether the party already exists*/
           		{
           			flash.message =  "${message(code: 'default.AlreadyExists.label')}"
                    render(view:'create',model:[partyInstance:partyInstance])
           		}
           	}
           	else 
                render(view:'create',model:[partyInstance:partyInstance])
        }
        else
            render(view:'create',model:[partyInstance:partyInstance])
    }
    
    /**
     * Method to perform the delete action
     */
     
     def delete = 
     {
     		def personInstanceList = []
        	def personRoleInstance = userService.getUserRoleByUserId(session.UserId)
        	def partyInstance = partyService.getPartyById(new Integer(params.id))
        	partyInstance.activeYesNo = 'N'
         	if(partyInstance.save())
         	{
         		def partyUsersList=userService.getAllUsersByPartyID(params.id)
          	         for(int i=0;i<partyUsersList.size();i++)
         	         {
           	        	def personInstance = Person.find("from Person P where P.id="+partyUsersList[i].user.id)
         	        	personInstance.enabled = false
          	        	personInstance.activeYesNo = 'N'
         	        	personInstance.save()
         	         }
         	}
     		
     		    flash.message = "${message(code: 'default.deleted.label')}"
	            redirect(action:list)
     }
       
}
