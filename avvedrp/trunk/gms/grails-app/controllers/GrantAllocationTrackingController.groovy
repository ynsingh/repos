import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class GrantAllocationTrackingController {
	def grantAllocationService
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ grantAllocationTrackingInstanceList: GrantAllocationTracking.list( params ) ]
    }

    def show = {
        def grantAllocationTrackingInstance = GrantAllocationTracking.get( params.id )

        if(!grantAllocationTrackingInstance) {
            flash.message = "GrantAllocationTracking not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ grantAllocationTrackingInstance : grantAllocationTrackingInstance ] }
    }

    def delete = {
        def grantAllocationTrackingInstance = GrantAllocationTracking.get( params.id )
        if(grantAllocationTrackingInstance) {
            grantAllocationTrackingInstance.delete()
            flash.message = "GrantAllocationTracking ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "GrantAllocationTracking not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def grantAllocationTrackingInstance = GrantAllocationTracking.get( params.id )

        if(!grantAllocationTrackingInstance) {
            flash.message = "GrantAllocationTracking not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ grantAllocationTrackingInstance : grantAllocationTrackingInstance ]
        }
    }

    def update = {
        def grantAllocationTrackingInstance = GrantAllocationTracking.get( params.id )
        if(grantAllocationTrackingInstance) {
            grantAllocationTrackingInstance.properties = params
            if(!grantAllocationTrackingInstance.hasErrors() && grantAllocationTrackingInstance.save()) {
                flash.message = "Grant Allocation Tracking ${params.id} updated"
                redirect(action:create,id:grantAllocationTrackingInstance.grantAllocation.id)
            }
            else {
               // render(view:'create',model:[grantAllocationTrackingInstance:grantAllocationTrackingInstance])
               redirect(action:create,id:grantAllocationTrackingInstance.grantAllocation.id)
            }
        }
        else {
            flash.message = "Grant Allocation Tracking not found with id ${params.id}"
        	redirect(action:create,id:params.grantAllocation.id)
        }
    }

    def create = {
		def grantAllocationService = new GrantAllocationService()
    		
		/* Get grant allocation details. */
    	def grantAllocationInstance = grantAllocationService.getGrantAllocationById(new Integer(params.id)) 
    	println "**************createtracktype "+params.trackType
    	
    	/* Get grant allocation tracking details if any for the grant allocation */
    	def grantAllocationTrackingInstance = grantAllocationService.getGrantAllocationTrackingByGrantAllocation(grantAllocationInstance)
    	
    	if(!grantAllocationTrackingInstance){
    		grantAllocationTrackingInstance = new GrantAllocationTracking()
        	grantAllocationTrackingInstance.grantAllocation = grantAllocationInstance
            grantAllocationTrackingInstance.properties = params
    	}
    	grantAllocationTrackingInstance.trackType = params.trackType
        return ['grantAllocationInstance':grantAllocationInstance, 'grantAllocationTrackingInstance':grantAllocationTrackingInstance]
    }

    def save = {
    		
		def grantAllocationService = new GrantAllocationService()	
		params.modifiedBy = "admin"
    	params.modifiedDate = new Date()
		
		def grantAllocationTrackingInstance = grantAllocationService.saveOrUpdateGrantAllocationTracking(params)
		if(grantAllocationTrackingInstance){
			if(grantAllocationTrackingInstance.saveMode != null){
				flash.message = "Grant Allocation ${grantAllocationTrackingInstance.grantAllocationStatus} "
				redirect(action:create,id:grantAllocationTrackingInstance.grantAllocation.id,params:[trackType:params.trackType])
			}
			else{
				redirect(action:create,id:grantAllocationTrackingInstance.grantAllocation.id,params:[trackType:params.trackType])
			}
		}
    }
    
    def grantAllocationTrackingReports = {
		println params
		def dataSecurityService = new DataSecurityService()
		GrailsHttpSession gh=getSession()
		def grantAllocationInstanceList=[]
		println "**************grantAllocationInstanceList 1 "+grantAllocationInstanceList  
        try{
        	grantAllocationInstanceList = grantAllocationService.getAll()
        	println "**************grantAllocationInstanceList 222222 "+grantAllocationInstanceList 
		}
		  catch(Exception e)
		{
			
		}
		def projectsList=[]
        
        for(int i=0;i<grantAllocationInstanceList.size();i++)
        {
        
        	projectsList.add(grantAllocationInstanceList[i].projects)
        }
			
			
			
			
		
		
	    return[projectInstanceList:projectsList]
    }
    
    def showReport = {
    		println "showReport params "+params
    		return['reportListInstance':params]
    }
}
