import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class GrantPeriodController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
    		
    	GrailsHttpSession gh=getSession()
        gh.removeValue("Help")
       		//putting help pages in session
       	gh.putValue("Help","Grant_Period_List.htm")	
        if(!params.max) params.max = 10
        
        def grantPeriodService = new GrantPeriodService()
        def grantPeriodInstanceList = grantPeriodService.getAllGrantPeriods(params)
        [ grantPeriodInstanceList: grantPeriodInstanceList ]
    }

    def show = {
		def grantPeriodService = new GrantPeriodService()
		def grantPeriodInstance = grantPeriodService.getGrantPeriodById(new Integer( params.id ))

        if(!grantPeriodInstance) {
            flash.message = "GrantPeriod not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ grantPeriodInstance : grantPeriodInstance ] }
    }

    def delete = {
		def grantPeriodService = new GrantPeriodService()
		Integer grantPeriodId = grantPeriodService.deleteGrantPeriod(new Integer(params.id))
		
		if(grantPeriodId != null){
			flash.message = "GrantPeriod  deleted"
            redirect(action:list)
		}
		else {
            flash.message = "GrantPeriod not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
		def grantPeriodService = new GrantPeriodService()
		def grantPeriodInstance = grantPeriodService.getGrantPeriodById(new Integer( params.id ))

        if(!grantPeriodInstance) {
            flash.message = "GrantPeriod not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ grantPeriodInstance : grantPeriodInstance ]
        }
    }

    def update = {
		def grantPeriodService = new GrantPeriodService()
		def grantPeriodInstance = grantPeriodService.updateGrantPeriod(params)
		
		if(grantPeriodInstance){
			if(grantPeriodInstance.saveMode != null){
				if(grantPeriodInstance.saveMode.equals("Updated")){
					flash.message = "Grant Period  updated"
	                redirect(action:list,id:grantPeriodInstance.id)
				}
			}
			else {
                render(view:'edit',model:[grantPeriodInstance:grantPeriodInstance])
            }
		}
		else {
            flash.message = "GrantPeriod not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
    		GrailsHttpSession gh=getSession()
         def grantPeriodInstance = new GrantPeriod()
        
        
        	gh.removeValue("Help")
    		//putting help pages in session
    		gh.putValue("Help","Create_Grant_Period.htm")	
        grantPeriodInstance.properties = params
        return ['grantPeriodInstance':grantPeriodInstance]
    }

    def save = {
        def grantPeriodInstance = new GrantPeriod(params)
        if(!grantPeriodInstance.hasErrors() ) {
        	grantPeriodInstance.createdBy="admin"
    		grantPeriodInstance.modifiedBy="admin"
    		
			def grantPeriodService = new GrantPeriodService()
    		grantPeriodInstance = grantPeriodService.saveGrantPeriod(grantPeriodInstance)
			if(grantPeriodInstance.saveMode != null){
				if(grantPeriodInstance.saveMode.equals("Saved")){
					flash.message = "Grant Period created"
		            redirect(action:list,id:grantPeriodInstance.id)
				}
			}
			else {
	            render(view:'create',model:[grantPeriodInstance:grantPeriodInstance])
	        }
            
        }
        
    }
}
