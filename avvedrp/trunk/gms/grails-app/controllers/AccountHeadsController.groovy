import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class AccountHeadsController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
		def accountHeadsService = new AccountHeadsService()
        if(!params.max) params.max = 10
        
        String subQuery ="";
        GrailsHttpSession gh=getSession()
        if(params.sort != null && !params.sort.equals(""))
        	subQuery=" order by AH."+params.sort
        if(params.order != null && !params.order.equals(""))
        	subQuery =subQuery+" "+params.order
        	gh.removeValue("Help")
    		//putting help pages in session
    		gh.putValue("Help","Account_Head_List.htm")	
    	def accountHeadsInstanceList
       	//if(gh.getValue("Role").equals('ROLE_USER')) {
       		accountHeadsInstanceList = accountHeadsService.getActiveAccountHeads(subQuery)
   		//}
	    //else 
	    //{ 
	    //	accountHeadsInstanceList = accountHeadsService.getActiveMainAccountHeads(subQuery)
    	//}
        
        [ accountHeadsInstanceList: accountHeadsInstanceList ]
    }

    def show = {
		def accountHeadsService = new AccountHeadsService()
        def accountHeadsInstance
        
        if(params.id){
        	accountHeadsInstance = accountHeadsService.getAccountHeadsById(new Integer( params.id ))
        }

        if(!accountHeadsInstance) {
            flash.message = "AccountHead not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ accountHeadsInstance : accountHeadsInstance ] }
    }

    def delete = {
    		def accountHeadsInstance = new AccountHeads()
            accountHeadsInstance.properties = params
		def accountHeadsService = new AccountHeadsService()
		Integer accountHeadId = null
		
		println"+++++++++params+++++"+params.parentId
		if(params.id){
			accountHeadId = accountHeadsService.deleteAccountHeads(new Integer(params.id))
		}
    		
    		println"+++++++++params.id after deletion+++++"+params.id	
    		 
		if(accountHeadId != null){
			
			flash.message = "AccountHead ${params.name} deleted"
			
				if(accountHeadsInstance.parent !=null)
				{
					
					redirect(action:showSubAccountHeads,id:accountHeadsInstance.parent.id)
				}
				else	
				{
		    		
					redirect(action:list,id:accountHeadsInstance.id)
				}
			}
		
    			
    
		else {
            flash.message = "Sub AccountHead exists.Cannot be deleted"
            redirect(action:list)
        }
    }
    

    def edit = {
		def accountHeadsService = new AccountHeadsService()
        def accountHeadsInstance
        
        if(params.id){
        	accountHeadsInstance = accountHeadsService.getAccountHeadsById(new Integer( params.id ))
        }
		
        if(!accountHeadsInstance) {
            flash.message = "AccountHeads not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ accountHeadsInstance : accountHeadsInstance ]
        }
    }

    def update = {
		def accountHeadsService = new AccountHeadsService()
		def accountHeadsInstance = accountHeadsService.updateAccountHeads(params)
		
		if(accountHeadsInstance){
			if(accountHeadsInstance.saveMode != null){
				if(accountHeadsInstance.saveMode.equals( "Updated")){
					flash.message = "AccountHead ${params.name} updated"
					if(accountHeadsInstance.parent !=null)
					{
						redirect(action:showSubAccountHeads,id:accountHeadsInstance.parent.id)
					}
					else	
					{
						redirect(action:list,id:accountHeadsInstance.id)
					}
				}
				else if(accountHeadsInstance.saveMode.equals( "Duplicate")){
					flash.message = "AccountHead Already Exists"
	    	    	render(view:'edit',model:[accountHeadsInstance:accountHeadsInstance])
				}
			}
			else{
				render(view:'edit',model:[accountHeadsInstance:accountHeadsInstance])
			}
		}
		else {
            flash.message = "AccountHead not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
    		GrailsHttpSession gh=getSession()	
        def accountHeadsInstance = new AccountHeads()
        gh.removeValue("Help")
		//putting help pages in session
		gh.putValue("Help","Create_Account_Head.htm")
        accountHeadsInstance.properties = params
        return ['accountHeadsInstance':accountHeadsInstance]
    }

    def save = {
        def accountHeadsInstance = new AccountHeads(params)
        if(!accountHeadsInstance.hasErrors() ) {
        	accountHeadsInstance.createdBy="admin"
        	accountHeadsInstance.createdDate = new Date();
        	accountHeadsInstance.modifiedBy="admin"
        	
    		def accountHeadsService = new AccountHeadsService()
        	accountHeadsInstance = accountHeadsService.saveAccountHeads(accountHeadsInstance)
        	if(accountHeadsInstance.saveMode != null){
        		if(accountHeadsInstance.saveMode.equals("Saved")){
        			flash.message = "AccountHead ${accountHeadsInstance.name} created"
    				if(accountHeadsInstance.parent !=null)
					{
						redirect(action:showSubAccountHeads,id:accountHeadsInstance.parent.id)
					}
					else	
					{
						redirect(action:list,id:accountHeadsInstance.id)
					}
        		}
        		else if(accountHeadsInstance.saveMode.equals("Duplicate")){
        			flash.message = "AccountHead Already Exists"
    				if(accountHeadsInstance.parent !=null)
					{
    					redirect(action:showSubAccountHeads,id:accountHeadsInstance.parent.id)
					}
    				else
    				{
    					render(view:'create',model:[accountHeadsInstance:accountHeadsInstance])
    				}
        		}
        	}
        	else {
        		if(accountHeadsInstance.parent !=null)
				{
					redirect(action:showSubAccountHeads,id:accountHeadsInstance.parent.id)
				}
				else
				{
					render(view:'create',model:[accountHeadsInstance:accountHeadsInstance])
				}
            }
        }
        else {
        	if(accountHeadsInstance.parent !=null)
			{
				redirect(action:showSubAccountHeads,id:accountHeadsInstance.parent.id)
			}
			else
			{
				render(view:'create',model:[accountHeadsInstance:accountHeadsInstance])
			}
        }
    }
    
    def showSubAccountHeads = {
		def accountHeadsService = new AccountHeadsService()
        def accountHeadsInstance = new AccountHeads()
		
		def accountHeadsList=accountHeadsService.getAccountHeadsById(new Integer(params.id))
		accountHeadsInstance.parent=accountHeadsList
		accountHeadsInstance.properties = params
		
		def accountHeadsInstanceList=accountHeadsService.getSubAccountHeads(new Integer(params.id))
        return ['accountHeadsInstance':accountHeadsInstance,'accountHeadsInstanceList':accountHeadsInstanceList]
    
    }
   
}
