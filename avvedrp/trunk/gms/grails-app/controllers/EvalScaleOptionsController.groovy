import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import grails.util.GrailsUtil
class EvalScaleOptionsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def evalScaleOptionsService = new EvalScaleOptionsService()
    def evalScaleService = new EvalScaleService()
    
    
    def index = {
        redirect(action: "create", params: params)
    }
   def create = {
    	GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","EvaluationScale_Options.htm")//putting help pages in session
        def evalScaleOptionsInstance = new EvalScaleOptions()
        evalScaleOptionsInstance.properties = params
        def evalScaleInstanceList= evalScaleService.listEvalscale(gh.getValue("Party"))
        def assignedEvalscaleOptionsInstanceList = []
        def evalScaleOptionsInstanceList = []
        def assignedEvalscaleOptionsInstance
        for(int i=0;i<evalScaleInstanceList.size();i++)
        {
        	assignedEvalscaleOptionsInstanceList = evalScaleOptionsService.listEvalScaleOptionsByEvalScale(evalScaleInstanceList.id[i])
	        	for(int j=0;j<assignedEvalscaleOptionsInstanceList.size();j++)
			        {
			          assignedEvalscaleOptionsInstance = evalScaleOptionsService.listEvalScaleOptionsById(assignedEvalscaleOptionsInstanceList.id[j])
			          	if(assignedEvalscaleOptionsInstance)
					        		evalScaleOptionsInstanceList.add(assignedEvalscaleOptionsInstance)
			        }
        }
           return [evalScaleOptionsInstance: evalScaleOptionsInstance,'evalScaleOptionsInstanceList':evalScaleOptionsInstanceList,'evalScaleInstanceList':evalScaleInstanceList]
    }

    def save = {
  	    def evalScaleOptionsInstance = new EvalScaleOptions(params)
        evalScaleOptionsInstance.createdBy = "admin"
        evalScaleOptionsInstance.modifiedBy = "admin"
        evalScaleOptionsInstance.activeYesNo = "Y"
        def evalScaleOptionsDuplicateInstance =  evalScaleOptionsService.alreadyUsedEvalscaleOption(params.scaleOption,params.evalScale.id)
	       if(evalScaleOptionsDuplicateInstance)
	        {
		        	flash.message = "${message(code: 'default.AlreadyExists.label')}" 
		            redirect(action: "create")
		        }
	        else
	        {
	        	def evalScaleOptionForEvalScaleList = []
	        	def evalAnswerForEvalScalOptionsInstance
	        	def evalAnswerForEvalScalOptionsInstanceList = []
	        	evalScaleOptionForEvalScaleList = evalScaleOptionsService.getEvalScaleOptionsByEvalScaleId(params.evalScale.id)
	        		for(int i=0;i<evalScaleOptionForEvalScaleList.size();i++)
	        		{
	        			evalAnswerForEvalScalOptionsInstance = evalScaleOptionsService.getEvalAnswerByEvalOptionsId(evalScaleOptionForEvalScaleList.id[i])
		        			if(evalAnswerForEvalScalOptionsInstance)
		        				evalAnswerForEvalScalOptionsInstanceList.add(evalAnswerForEvalScalOptionsInstance)
	        		}
			        	if(evalAnswerForEvalScalOptionsInstanceList)
			        	{
			        		flash.message = "${message(code: 'default.cantcreate.label')}" 
					        redirect(action: "create")
			        	}
			        	else
			        	{
				        	evalScaleOptionsInstance.properties = params  	
				        	if (evalScaleOptionsInstance.save(flush: true)) {
					                flash.message = "${message(code: 'default.created.label')}"
					                redirect(action: "create", id: evalScaleOptionsInstance.id)
					              }
				            else {
				            		render(view: "create", model: [evalScaleOptionsInstance: evalScaleOptionsInstance])
					            }
			        	}
	        }
   }

    def edit = {
        def evalScaleOptionsInstance = evalScaleOptionsService.getEvalScaleOptions(params.id)
        if (!evalScaleOptionsInstance) {
            flash.message = "${message(code: 'default.notfond.label', args: [message(code: 'evalScaleOptions.label', default: 'EvalScaleOptions'), params.id])}"
            redirect(action: "create")
        }
        else {
            return [evalScaleOptionsInstance: evalScaleOptionsInstance]
        }
    }

    def update = {
    	def evalScaleOptionsInstance = evalScaleOptionsService.getEvalScaleOptions(params.id)
        if (evalScaleOptionsInstance) {
           if (params.version) {
                def version = params.version.toLong()
                if (evalScaleOptionsInstance.version > version) {
                    
                    evalScaleOptionsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'evalScaleOptions.label', default: 'EvalScaleOptions')] as Object[], "Another user has updated this EvalScaleOptions while you were editing")
                    render(view: "edit", model: [evalScaleOptionsInstance: evalScaleOptionsInstance])
                    return
                }
            }
        	def evalScaleOptionsDuplicateInstance =  evalScaleOptionsService.alreadyUsedEvalscaleOption(params.scaleOption,evalScaleOptionsInstance.evalScale.id)
           	if(evalScaleOptionsDuplicateInstance)
            		{
            			if((new Integer(params.id)== evalScaleOptionsInstance.id)&&(params.scaleOption==evalScaleOptionsInstance.scaleOption))
            			{
            				evalScaleOptionsInstance.properties = params
            				if (!evalScaleOptionsInstance.hasErrors() && evalScaleOptionsInstance.save(flush: true)) {
            					flash.message = "${message(code: 'default.updated.label', args: [message(code: 'evalScaleOptions.label', default: 'EvalScaleOptions'), evalScaleOptionsInstance.id])}"
            					redirect(action: "create", id: evalScaleOptionsInstance.id)
            					}
            				else {
            						render(view: "edit", model: [evalScaleOptionsInstance: evalScaleOptionsInstance])
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
            			println"fgffhghg---------->"
            		def evalScaleOptionForEvalScaleList = []
    	        	def evalAnswerForEvalScalOptionsInstance
    	        	def evalAnswerForEvalScalOptionsInstanceList = []
    	        	evalScaleOptionForEvalScaleList = evalScaleOptionsService.getEvalScaleOptionsByEvalScaleId(params.evalScale.id)
    	        		for(int i=0;i<evalScaleOptionForEvalScaleList.size();i++)
    	        		{
    	        			evalAnswerForEvalScalOptionsInstance = evalScaleOptionsService.getEvalAnswerByEvalOptionsId(evalScaleOptionForEvalScaleList.id[i])
    		        			if(evalAnswerForEvalScalOptionsInstance)
    		        				evalAnswerForEvalScalOptionsInstanceList.add(evalAnswerForEvalScalOptionsInstance)
    	        		}
    			        	if(evalAnswerForEvalScalOptionsInstanceList)
    			        	{
    			        		flash.message = "${message(code: 'default.cantupdate.label')}" 
    					        redirect(action: "create")
    			        	}
    			        	else
    			        	{
    			        		evalScaleOptionsInstance.properties = params
    	            			if (!evalScaleOptionsInstance.hasErrors() && evalScaleOptionsInstance.save(flush: true)) {
    	            				flash.message = "${message(code: 'default.updated.label', args: [message(code: 'evalScaleOptions.label', default: 'EvalScaleOptions'), evalScaleOptionsInstance.id])}"
    	            				redirect(action: "create", id: evalScaleOptionsInstance.id)
    	            				}
    	            			else {
    	            					render(view: "edit", model:[evalScaleOptionsInstance: evalScaleOptionsInstance])
    	            				 }
    			        	}
          		}
        }
        else {
	            flash.message = "${message(code: 'default.notfond.label', args: [message(code: 'evalScaleOptions.label', default: 'EvalScaleOptions'), params.id])}"
	            redirect(action: "edit")
        }
    }
 
    def delete = {
   	GrailsHttpSession gh=getSession()	
    	def evalScaleOptionsInstance = evalScaleOptionsService.getEvalScaleOptions(params.id)
        if (evalScaleOptionsInstance) {
         	def usedEvalScaleOptionsInstance = evalScaleOptionsService.evalScaleOptionAssignedInEvalItem(evalScaleOptionsInstance.evalScale.id)
	        	if(usedEvalScaleOptionsInstance)
		        	{
			       		flash.message = "${message(code: 'default.inuse.label')}"
				        redirect(action: "create")
		        	}
	        	else
	        	{
	        	
		        	try {
		            	
		            	evalScaleOptionsInstance = evalScaleOptionsService.deleteEvalscaleoption(evalScaleOptionsInstance)
		                flash.message = "${message(code: 'default.deleted.label')}"
		                redirect(action: "create")
		            }
		            catch (org.springframework.dao.DataIntegrityViolationException e) {
		                flash.message = "${message(code: 'default.inuse.label')}"
		                redirect(action: "edit", id: params.id)
		            }
	        	}    
	        }
       else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "edit")
        }
    }
}
