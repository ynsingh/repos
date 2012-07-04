import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class EvalItemNotificationMapController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def evalItemService
    def notificationService
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [evalItemNotificationMapInstanceList: EvalItemNotificationMap.list(params), evalItemNotificationMapInstanceTotal: EvalItemNotificationMap.count()]
    }

    def create = {
        def evalItemNotificationMapInstance = new EvalItemNotificationMap()
        evalItemNotificationMapInstance.properties = params
        GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","AssignEvaluationQuestion_Notification.htm")//putting help pages in session
        def notificationInstance = notificationService.getAllPublicNotificationUnderAParty(gh.getValue("Party"))
        return [evalItemNotificationMapInstance: evalItemNotificationMapInstance,notificationInstance:notificationInstance]
    }

    def save = {
    	def evalAnswerInstanceList
    	if(params.notification.id != 'null')
    	{
    		evalAnswerInstanceList = evalItemService.getEvalAnswerListByNotificationId(params.notification.id)
    	}
    	 def evalItemNotificationMapInstance
         if(!evalAnswerInstanceList)
         {
    	     evalItemNotificationMapInstance = evalItemService.saveEvalItemNotificationMapList(params)
         }
         else
         {
        	  flash.message = "${message(code: 'default.notabletoadd.message')}"
         }
         redirect(action: "listEvalItem", params:['notification.id': params.notification.id])
                
    }

    def show = {
        def evalItemNotificationMapInstance = EvalItemNotificationMap.get(params.id)
        if (!evalItemNotificationMapInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'evalItemNotificationMap.label', default: 'EvalItemNotificationMap'), params.id])}"
            redirect(action: "list")
        }
        else {
            [evalItemNotificationMapInstance: evalItemNotificationMapInstance]
        }
    }

    def edit = {
        def evalItemNotificationMapInstance = EvalItemNotificationMap.get(params.id)
        if (!evalItemNotificationMapInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'evalItemNotificationMap.label', default: 'EvalItemNotificationMap'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [evalItemNotificationMapInstance: evalItemNotificationMapInstance]
        }
    }

    def update = {
        def evalItemNotificationMapInstance = EvalItemNotificationMap.get(params.id)
        if (evalItemNotificationMapInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (evalItemNotificationMapInstance.version > version) {
                    
                    evalItemNotificationMapInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'evalItemNotificationMap.label', default: 'EvalItemNotificationMap')] as Object[], "Another user has updated this EvalItemNotificationMap while you were editing")
                    render(view: "edit", model: [evalItemNotificationMapInstance: evalItemNotificationMapInstance])
                    return
                }
            }
            evalItemNotificationMapInstance.properties = params
            if (!evalItemNotificationMapInstance.hasErrors() && evalItemNotificationMapInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'evalItemNotificationMap.label', default: 'EvalItemNotificationMap'), evalItemNotificationMapInstance.id])}"
                redirect(action: "show", id: evalItemNotificationMapInstance.id)
            }
            else {
                render(view: "edit", model: [evalItemNotificationMapInstance: evalItemNotificationMapInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'evalItemNotificationMap.label', default: 'EvalItemNotificationMap'), params.id])}"
            redirect(action: "list")
        }
    }
    /**
     * method to delete(activeYesNo = 'N') EvalItemNotificationMap
     */
    def delete = 
    {
    	//method to get EvalAnswer list by notification id
    	def evalAnswerInstanceList = evalItemService.getEvalAnswerListByNotificationId(params.notification.id)
        def evalItemNotificationMapInstance 
        if(!evalAnswerInstanceList)
        {
        	//method to inaactivate selected EvalItemNotificationMap
        	evalItemNotificationMapInstance = evalItemService.inactivateEvalItemNotificationMapList(params)
        }
        else
        {
       	  flash.message = "${message(code: 'default.notabletoadd.message')}"
        }
        redirect(action: "listEvalItem", params:['notification.id': params.notification.id])
       
    }
    /*
     * action used to list assigned eval item of a notification
     */
    def listEvalItem =
    {
    	GrailsHttpSession gh=getSession()
    	//method to get eval notification map item
    	def evalItemNotificationMapInstance = evalItemService.getEvalItemNotificationMapByNotification(params.notification.id)
    	//method to get eval item
    	def evalItemInstance =  evalItemService.getEvalItemByParty(gh.getValue("Party"))
    	//assign eval item id to a varible
    	def evalItemId=evalItemInstance.id
    	//assign evalItemNotificationMap id to a varible
    	def evalItemNotificationMapId=evalItemNotificationMapInstance.evalItem.id
    	//subtract evalItemNotificationMapId from evalItemId
    	evalItemId.removeAll(evalItemNotificationMapId)
    	def evalSet=[] as Set
    	//check if notification selected or not
    	if(params.notification.id=='null')
    	{
    		evalSet=null
    	}
    	else
    	{
    		//iterating evalItem list
    		for(evalItem in evalItemInstance)
    		{
    			//iterating evalItem id list
    			for(evalItemIdValue in evalItemId)
    			{
    				//comparing eval item id's
    				if(evalItemIdValue == evalItem.id)
    				{
    					//adding evalItem instance to a set
    					evalSet.add(evalItem)
    				}
    			}
    		}
    	}
    	
    	render (template:"listEvalItem", model : ['evalItemNotificationMapInstance' : evalItemNotificationMapInstance,'evalItemInstance':evalSet])	                                      		
    }
}
