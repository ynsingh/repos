import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class EvalItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def notificationService = new NotificationService()
    def evalScaleService = new EvalScaleService()
    def evalItemService = new EvalItemService()
   
    def index = {
        redirect(action: "create", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [evalItemInstanceList: EvalItem.list(params), evalItemInstanceTotal: EvalItem.count()]
    }

    def create = {
    	GrailsHttpSession gh=getSession()
        def evalItemInstance = new EvalItem()
        evalItemInstance.properties = params
        def notificationInstanceList = notificationService.getAllNotificationUnderAParty(gh.getValue("PartyID"))
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
        return [evalItemInstance: evalItemInstance,'notificationInstanceList':notificationInstanceList,'evalScaleInstanceList':evalScaleInstanceList,'evalItemInstanceList':evalItemInstanceList]
    }

    def save = {
        def evalItemInstance = new EvalItem(params)
        evalItemInstance.createdBy = "admin"
        evalItemInstance.modifiedBy = "admin"
        evalItemInstance.activeYesNo = 'Y'
        def evalAnswerProposalInstance
        def evalItemDuplicateInstance = evalItemService.toGetDuplicateEvalItem(params.item,params.notification.id,params.evalScale.id)
        	if(evalItemDuplicateInstance)
        		{
		        	flash.message = "${message(code: 'default.AlreadyExists.label')}" 
		            redirect(action: "create")
        		}
        	else
        		{
             		def evalAnswerProposalInstanceList = []
        			def proposalsForANotificationList = evalItemService.getProposalsForANotification(params.notification.id)
            			if(proposalsForANotificationList)
             				{
             					for(int i=0;i<proposalsForANotificationList.size();i++)
             						{
             							evalAnswerProposalInstance = evalItemService.getEvalAnswerForProposalId(proposalsForANotificationList.id[i])
             	            				if(evalAnswerProposalInstance)
             	            					evalAnswerProposalInstanceList.add(evalAnswerProposalInstance)
             						}
		        					if(evalAnswerProposalInstanceList)
		        					{
		        						flash.message = "${message(code: 'default.cantcreate.label')}"
		        			        	redirect(action: "create")
		        					}
		        					else
		        					{
		        						if (evalItemInstance.save(flush: true)) {
		        			        		flash.message = "${message(code: 'default.created.label')}"
		        			        		redirect(action: "create", id: evalItemInstance.id)
		        			        		}
		        				        else {
		        				            	render(view: "create", model: [evalItemInstance: evalItemInstance])
		        				           }
		        					}
        				    }
             			 else
             				{
		          				if (evalItemInstance.save(flush: true)) {
		    			        		flash.message = "${message(code: 'default.created.label')}"
		    			        		redirect(action: "create", id: evalItemInstance.id)
		    			        		}
		    				        else {
		    				            	render(view: "create", model: [evalItemInstance: evalItemInstance])
		    				           }
             				}
       		    	}
    }

    def show = {
        def evalItemInstance = EvalItem.get(params.id)
        if (!evalItemInstance) {
        	flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
          }
        else {
            [evalItemInstance: evalItemInstance]
        }
    }

    def edit = {
    	GrailsHttpSession gh=getSession()
    	def evalItemInstance = EvalItem.get(params.id)
        def notificationInstanceList = notificationService.getAllNotificationUnderAParty(gh.getValue("PartyID"))
        def evalScaleInstanceList = evalScaleService.listEvalscale(gh.getValue("Party"))
        if (!evalItemInstance) {
        	flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create")
        }
        else {
            return [evalItemInstance: evalItemInstance,'notificationInstanceList':notificationInstanceList,'evalScaleInstanceList':evalScaleInstanceList]
        }
    }

    def update = {
	   	def evalItemInstance = EvalItem.get(params.id)
		 if (evalItemInstance) {
			  if (params.version) {
			        def version = params.version.toLong()
			        if (evalItemInstance.version > version) {
			            evalItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'evalItem.label', default: 'EvalItem')] as Object[], "Another user has updated this EvalItem while you were editing")
			        render(view: "edit", model: [evalItemInstance: evalItemInstance])
			        return
		                      }
		                  }
		
		def evalItemDuplicateInstance =evalItemService.toGetDuplicateEvalItem(params.item,params.notification.id,params.evalScale.id)				       
		println"evalItemDuplicateInstance"+evalItemDuplicateInstance
		      if(evalItemDuplicateInstance)  
		      {
		    	  if((new Integer(params.id)== evalItemInstance.id)&&
		        		((evalItemInstance.item == params.item)&& 
		        				(evalItemInstance.notification.id == new Integer(params.notification.id))&& 
		        					(evalItemInstance.evalScale.id == new Integer(params.evalScale.id))))
		        	{
		        			evalItemInstance.properties = params
				            if (!evalItemInstance.hasErrors() && evalItemInstance.save(flush: true)) {
				            	flash.message = "${message(code: 'default.updated.label')}"
				                redirect(action: "create", id: evalItemInstance.id)
				            }
				            else {
				                render(view: "edit", model: [evalItemInstance: evalItemInstance])
				            }
		        	}
		    	 else
		    	 {
		    		 flash.message = "${message(code: 'default.AlreadyExists.label')}" 
				     redirect(action: "create")
		    	 }
		      }
	        else
	        {
	        	def evalItemInEvalAnswerInstance
	        	def proposalsForANotificationList = evalItemService.getProposalsForANotification(params.notification.id)
	         	def evalItemInEvalAnswerInstanceList =[]
	        	def evalItemForPrposalInstance = evalItemService.getEvalItemInstanceById(params.id)
		           	for(int i=0;i<proposalsForANotificationList.size();i++)
		         	{
		         		evalItemInEvalAnswerInstance = evalItemService.getEvalAnswerByEvalItemIdAndProposalId(evalItemForPrposalInstance.id,proposalsForANotificationList.id[i])
		  			     	if(evalItemInEvalAnswerInstance)
		  			    	 	evalItemInEvalAnswerInstanceList.add(evalItemInEvalAnswerInstance)
		         	}
		         	if(evalItemInEvalAnswerInstanceList)
		         	{
		         		 flash.message = "${message(code: 'default.cantupdate.label')}"
		                 redirect(action: "create", id: params.id)
		         	}
		         	else
		         	{
		     
			            evalItemInstance.properties = params
			            if (!evalItemInstance.hasErrors() && evalItemInstance.save(flush: true)) {
			            	flash.message = "${message(code: 'default.updated.label')}"
			                redirect(action: "create", id: evalItemInstance.id)
			            }
			            else {
			                render(view: "edit", model: [evalItemInstance: evalItemInstance])
			            }
		         	}
		        }
				    
		          }
	    else {
	    	flash.message = "${message(code: 'default.notfond.label')}"
	    	redirect(action: "create")
	    }
    }

    def delete = {
       def evalItemInstance = EvalItem.get(params.id)
	        if (evalItemInstance) {
	        	def proposalsForANotificationList = evalItemService.getProposalsForANotification(params.notification.id)
	         	def evalItemInEvalAnswerInstance
	         	def evalItemInEvalAnswerInstancList =[]
	        	def evalItemForPrposalInstance = evalItemService.getEvalItemInstanceById(params.id)
	           	for(int i=0;i<proposalsForANotificationList.size();i++)
	         	{
	         		evalItemInEvalAnswerInstance = EvalAnswer.find("from EvalAnswer EAR where EAR.evalItem = '"+evalItemForPrposalInstance.id+"' and EAR.proposal.id="+proposalsForANotificationList.id[i])
	         		 if(evalItemInEvalAnswerInstance)
	         			 evalItemInEvalAnswerInstancList.add(evalItemInEvalAnswerInstance)
	         	}
					if(evalItemInEvalAnswerInstancList)
					{
						 flash.message = "${message(code: 'default.inuse.label')}"
					     redirect(action: "create", id: params.id)
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
