import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class EvalScaleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def evalScaleService = new EvalScaleService()
    def index = {
        redirect(action: "create", params: params)
    }

    def create = {
    	
    	GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","Evaluation_Scale.htm")//putting help pages in session
    	def partyInstance = Party.get(gh.getValue("Party"))
        def evalScaleInstance = new EvalScale()
        evalScaleInstance.properties = params
        def evalScaleInstanceList= evalScaleService.listEvalscale(gh.getValue("Party"))
        return [evalScaleInstance: evalScaleInstance,partyInstance:partyInstance,evalScaleInstanceList:evalScaleInstanceList]
    }

    def save = {
    	GrailsHttpSession gh=getSession()
    	def partyInstance = Party.get(gh.getValue("Party"))
        def evalScaleInstance = new EvalScale(params)
    	evalScaleInstance.createdBy = "admin"
    	evalScaleInstance.modifiedBy = "admin"
    	evalScaleInstance.activeYesNo = 'Y'
    	evalScaleInstance.party = partyInstance
         if(params.scaleTitle)
         {
        	 def evalScaleDuplicateInstance = evalScaleService.duplicateEvalScale(params.scaleTitle,gh.getValue("Party"))
        	 if(evalScaleDuplicateInstance)
        	 {
        		 
        		 flash.message = "${message(code: 'default.AlreadyExists.label')}" 
        		 redirect(action: "create", id: evalScaleInstance.id)
         	 }
        	 else
        	 {
        		 evalScaleInstance.properties = params
	 			 saveEvalScale(evalScaleInstance) 
        	 }
         }
  }

    def edit = {
    	
       	def evalScaleInstance = evalScaleService.getEvalScaleById(params.id)
          if (!evalScaleInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "edit")
        }
        else {
            return [evalScaleInstance: evalScaleInstance]
        }
    }

    def update = {
    	GrailsHttpSession gh=getSession()
    	def evalScaleInstance = evalScaleService.getEvalScaleById(params.id)
     if(evalScaleInstance)
      {
    	 if (params.version) {
             def version = params.version.toLong()
             if (evalScaleInstance.version > version) {
                 
                 evalScaleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'evalScale.label', default: 'EvalScale')] as Object[], "Another user has updated this EvalScale while you were editing")
                 render(view: "edit", model: [evalScaleInstance: evalScaleInstance])
                 return
              	}
    	 	}
    	 
    	 	if((new Integer(params.id) == evalScaleInstance.id ))
    	 		{
		   	 			if((params.scaleTitle)==(evalScaleInstance.scaleTitle))
		    	 			{
		    	 				evalScaleInstance.properties = params
		    	 				updateEvalScale(evalScaleInstance)
		    	 			}
	    	 			else
	    	 				{
		    	 				def evalScaleDuplicateInstance =evalScaleService.duplicateEvalScale(params.scaleTitle,gh.getValue("Party"))
		    	 	        		if(evalScaleDuplicateInstance)
		    	 					{
		        			   
		    	 						flash.message = "${message(code: 'default.AlreadyExists.label')}" 
		    	 						render(view: "edit", model: [evalScaleInstance: evalScaleInstance])
		        			   
	    	 				        }
		    	 					else
		    	 					{
		    	 						evalScaleInstance.properties = params
		    	    	 				updateEvalScale(evalScaleInstance)
		    	 						
		    	 					}
	    	 				}
    	 		}
      }
     else {
         flash.message = "${message(code: 'default.notfond.label')}"
         redirect(action: "create")
     }
 }
    public updateEvalScale(def evalScaleInstance)
    
    {
    	if (!evalScaleInstance.hasErrors() && evalScaleInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.updated.label')}"
            redirect(action: "create", id: evalScaleInstance.id)
        }
    	else {
            render(view: "edit", model: [evalScaleInstance: evalScaleInstance])
        }
    }
    
    public saveEvalScale(def evalScaleInstance)
    {
    	if (evalScaleInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.label')}"
            redirect(action: "create", id: evalScaleInstance.id)
        }
        else {
            render(view: "create", model: [evalScaleInstance: evalScaleInstance])
        }
     }

    def delete = {
    	GrailsHttpSession gh=getSession()
    	def evalScaleInstance = evalScaleService.getEvalScaleById(params.id)
    		if (evalScaleInstance) {
	        	def evalScaleForDelete = evalScaleService.alreadyUsedEvalscale(evalScaleInstance.id)
	        	 println"evalScaleForDelete------------------->"+evalScaleForDelete 
		        	if(evalScaleForDelete)
		        	   {
		        		  flash.message = "${message(code: 'default.inuse.label')}"
		                  redirect(action: "create", id: params.id)
		        		   
		        	   }
	        	   
	        	   else
	        	   {
	        		   try {
	                   		evalScaleInstance.properties = params 
	                   		evalScaleInstance = evalScaleService.deleteEvalscale(evalScaleInstance)
	                   		flash.message = "${message(code: 'default.deleted.label')}"
	                   		redirect(action:create)
	                   		}
	                   catch (org.springframework.dao.DataIntegrityViolationException e) {
	                       flash.message = "${message(code: 'default.inuse.label')}"
	                       redirect(action: "create", id: params.id)
	                   		}
	        	   }
             }
	   else {
		       flash.message = "${message(code: 'default.notfond.label')}"
		       redirect(action: "edit")
	      }
  }
}
