import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class EvalItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def notificationService = new NotificationService()
    def evalScaleService = new EvalScaleService()
    def evalItemService = new EvalItemService()
   
    def index = {
        redirect(action: "create", params: params)
    }
    def create = {
    	GrailsHttpSession gh=getSession()
        def evalItemInstance = new EvalItem()
        evalItemInstance.properties = params
        def evalScaleInstanceList = evalScaleService.listEvalscale(gh.getValue("Party"))
        def evalItemAssignedInstance
        def evalItemInstanceList = []
    	def evalItemForEvalScaleInstanceList = []
        for(int i=0;i<evalScaleInstanceList.size();i++)
        {
        		evalItemForEvalScaleInstanceList = evalItemService.getEvalItemListByEvalScale(evalScaleInstanceList.id[i])
       	 	for(int j=0;j<evalItemForEvalScaleInstanceList.size();j++)
       	 		{
       	 			evalItemAssignedInstance = evalItemService.getEvalItemInstanceById(evalItemForEvalScaleInstanceList.id[j])
       	 			if(evalItemAssignedInstance)
        		 		evalItemInstanceList.add(evalItemAssignedInstance)
       	 		}
        }
        return [evalItemInstance: evalItemInstance,'evalScaleInstanceList':evalScaleInstanceList,'evalItemInstanceList':evalItemInstanceList]
    }
    def save = {
        def evalItemInstance = new EvalItem(params)
        evalItemInstance.createdBy = "admin"
        evalItemInstance.modifiedBy = "admin"
        evalItemInstance.activeYesNo = 'Y'
        //To get duplicate evalitem
        def evalItemDuplicateInstance = evalItemService.toGetDuplicateEvalItem(params.item,params.evalScale.id)
           if(evalItemDuplicateInstance)
           	{
	    	  flash.error = "${message(code: 'default.AlreadyExists.label')}" 
		      redirect(action: "create") 
           	}
	      else
	      	{
				if (evalItemInstance.save(flush: true)) 
					{
			    		flash.message = "${message(code: 'default.created.label')}"
			    		redirect(action: "create", id: evalItemInstance.id)
					}
		        else 
		        	{
		            	render(view: "create", model: [evalItemInstance: evalItemInstance])
		        	}
	      	}

    }
    def edit = {
    	
    	GrailsHttpSession gh=getSession()
    	def evalItemInstance = EvalItem.get(params.id)
        def evalScaleInstanceList = evalScaleService.listEvalscale(gh.getValue("Party"))
        if (!evalItemInstance) {
        	flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create")
        }
        else {
            return [evalItemInstance: evalItemInstance,'evalScaleInstanceList':evalScaleInstanceList]
        }
    }

    def update = 
    {
    	def evalItemInstance = EvalItem.get(params.id)
    	if (evalItemInstance) 
		{
    		// Evalitem which is already assigned to a notification 
    		def assignedEvalItemInstance = evalItemService.getAssignedEvalItem(params.id)
 			   if(assignedEvalItemInstance)
			   	{
 				   flash.error = "${message(code: 'default.cantupdate.label')}" 
				   redirect(action: "create") 
			   	}
			   else
			   {
		    		evalItemInstance.properties = params
					if (!evalItemInstance.hasErrors() && evalItemInstance.save(flush: true)) 
						{
							flash.message = "${message(code: 'default.updated.label')}"
						    redirect(action: "create", id: evalItemInstance.id)
						}
					else
						{
							render(view: "edit", model: [evalItemInstance: evalItemInstance])
						}
			 }
		}

    }

    def delete = 
    {
    	def evalItemInstance = EvalItem.get(params.id)
    	if (evalItemInstance) 
    	 {
			
    		//Evalitem which is already assigned to a notification 
    		def assignedEvalItemInstance = evalItemService.getAssignedEvalItem(params.id)
    		if(assignedEvalItemInstance)
    			{
    				 flash.error = "${message(code: 'default.inuse.label')}" 
	  				 redirect(action: "create") 
    			}
    		else
    			{
    		
		    		evalItemInstance.activeYesNo = "N"
					evalItemInstance.save()
					flash.message = "${message(code: 'default.deleted.label')}"
					redirect(action: "create")
    			}
    	 }
	    else 
	     {
			flash.message = "${message(code: 'default.notfond.label')}"
			redirect(action: "create")
	     }
    }
}
